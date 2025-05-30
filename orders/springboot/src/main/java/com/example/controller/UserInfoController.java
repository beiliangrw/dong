package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.UserInfo;
import com.example.exception.CustomException;
import com.example.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @PostMapping
    public Result<UserInfo> add(@RequestBody UserInfo userInfo) {
        userInfo.setAccount(0D);
        userInfoService.add(userInfo);
        return Result.success(userInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        userInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody UserInfo userInfo) {
        userInfoService.update(userInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<UserInfo> detail(@PathVariable Long id) {
        UserInfo userInfo = userInfoService.findById(id);
        return Result.success(userInfo);
    }

    @GetMapping
    public Result<List<UserInfo>> all() {
        return Result.success(userInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<UserInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @PathVariable String name) {
        return Result.success(userInfoService.findPage(pageNum, pageSize, name));
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public Result<UserInfo> register(@RequestBody UserInfo userInfo) {
        if (StrUtil.isBlank(userInfo.getName()) || StrUtil.isBlank(userInfo.getPassword())) {
            throw new CustomException(ResultCode.PARAM_ERROR);
        }
        return Result.success(userInfoService.add(userInfo));
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result<UserInfo> login(@RequestBody UserInfo userInfo) {
        if (StrUtil.isBlank(userInfo.getName()) || StrUtil.isBlank(userInfo.getPassword())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return Result.success(userInfoService.login(userInfo.getName(), userInfo.getPassword()));
    }

    /**
     * 重置密码为123456
     */
    @PutMapping("/resetPassword")
    public Result<UserInfo> resetPassword(@RequestParam String name) {
        return Result.success(userInfoService.resetPassword(name));
    }

    /**
     * 修改密码
     */
    @PutMapping("/changePassword")
    public Result<Boolean> changePassword(@RequestParam Long id,
                                          @RequestParam String newPassword) {
        return Result.success(userInfoService.changePassword(id, newPassword));
    }

    /**
     * 批量通过excel添加信息
     * @param file excel文件
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {

        List<UserInfo> infoList = ExcelUtil.getReader(file.getInputStream()).readAll(UserInfo.class);
        if (!CollectionUtil.isEmpty(infoList)) {
            // 处理一下空数据
            List<UserInfo> resultList = infoList.stream().filter(x -> ObjectUtil.isNotEmpty(x.getName())).collect(Collectors.toList());
            for (UserInfo info : resultList) {
                userInfoService.add(info);
            }
        }
        return Result.success();
    }

    @GetMapping("/getExcelModel")
    public void getExcelModel(HttpServletResponse response) throws IOException {
        // 1. 生成excel
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("name", "张天志");
        row.put("password", "123456");
        row.put("nickname", "老张");
        row.put("sex", "男");
        row.put("age", 22);
        row.put("phone", "18843232356");
        row.put("address", "上海市");
        row.put("level", 3);

        List<Map<String, Object>> list = CollUtil.newArrayList(row);

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename=userInfoModel.xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }
}
