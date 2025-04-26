package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.UserInfo;
import com.example.entity.Account;
import com.example.exception.CustomException;
import com.example.service.UserInfoService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AccountController {

    @Resource
    private UserInfoService userInfoService;

    /**
     * 生成图形验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // png类型
        SpecCaptcha captcha = new SpecCaptcha(135, 33, 4);
        captcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        CaptchaUtil.out(captcha, request, response);

    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);
        return Result.success();
    }

    @GetMapping("/auth")
    public Result getAuth(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if(user == null) {
            return Result.error("401", "未登录");
        }
        return Result.success((UserInfo)user);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        UserInfo info = userInfoService.findByName(userInfo.getName());
        if (info != null) {
            return Result.error("1001", "该用户名已存在");
        }
        userInfo.setAccount(0D);
        UserInfo register = userInfoService.add(userInfo);

        // 初始化一下个人信息
        register.setAddress(userInfo.getAddress());
        register.setPhone(userInfo.getPhone());
        register.setAge(userInfo.getAge());

        userInfoService.update(register);
        return Result.success(register);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserInfo userInfo, HttpServletRequest request) {
        if (StrUtil.isBlank(userInfo.getName()) || StrUtil.isBlank(userInfo.getPassword())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        UserInfo user = userInfoService.findByName(userInfo.getName());
        if (user.getId() == 1 && StrUtil.isEmpty(userInfo.getVerCode())) {
            return Result.error("1001", "您是管理员，请登录管理后台");
        }
        // 登录接口里加上，仅对后台管理员有效
        if (user.getId() == 1 && !CaptchaUtil.ver(userInfo.getVerCode(), request)) {
            // 清除session中的验证码
            CaptchaUtil.clear(request);
            return Result.error("1001", "验证码不正确");
        }
        UserInfo login = userInfoService.login(userInfo.getName(), userInfo.getPassword());
        HttpSession session = request.getSession();
        session.setAttribute("user", login);
        session.setMaxInactiveInterval(120 * 60);
        return Result.success(login);
    }

    /**
     * 重置密码为123456
     */
    @PutMapping("/resetPassword")
    public Result<UserInfo> resetPassword(@RequestParam String username) {
        return Result.success(userInfoService.resetPassword(username));
    }

    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody UserInfo info, HttpServletRequest request) {
        UserInfo account = (UserInfo) request.getSession().getAttribute("user");
        if (account == null) {
            return Result.error(ResultCode.USER_NOT_EXIST_ERROR.code, ResultCode.USER_NOT_EXIST_ERROR.msg);
        }
        String oldPassword = SecureUtil.md5(info.getPassword());
        if (!oldPassword.equals(account.getPassword())) {
            return Result.error(ResultCode.PARAM_PASSWORD_ERROR.code, ResultCode.PARAM_PASSWORD_ERROR.msg);
        }
        account.setPassword(SecureUtil.md5(info.getNewPassword()));
        userInfoService.update(account);

        // 清空session，让用户重新登录
        request.getSession().setAttribute("user", null);
        return Result.success();
    }


    /**
     * 修改密码
     */
    @PutMapping("/changePassword")
    public Result<Boolean> changePassword(@RequestParam Long id,
                                          @RequestParam String newPassword) {
        return Result.success(userInfoService.changePassword(id, newPassword));
    }

    @GetMapping("/getSession")
    public Result<Map<String, String>> getSession(HttpServletRequest request) {
        UserInfo account = (UserInfo) request.getSession().getAttribute("user");
        if (account == null) {
            return Result.success(new HashMap<>(1));
        }
        Map<String, String> map = new HashMap<>(1);
        map.put("username", account.getName());
        return Result.success(map);
    }
}
