package com.example.controller;

import com.example.common.Result;
import com.example.entity.AddressInfo;
import com.example.service.AddressInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/addressInfo")
public class AddressInfoController {
    @Resource
    private AddressInfoService addressInfoService;

    @PostMapping
    public Result<AddressInfo> add(@RequestBody AddressInfo addressInfo, HttpServletRequest request) {
        addressInfoService.add(addressInfo);
        return Result.success(addressInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        addressInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody AddressInfo addressInfo) {
        addressInfoService.update(addressInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<AddressInfo> detail(@PathVariable Long id) {
        AddressInfo addressInfo = addressInfoService.findById(id);
        return Result.success(addressInfo);
    }

    @GetMapping
    public Result<List<AddressInfo>> all() {
        return Result.success(addressInfoService.findAll());
    }

    @GetMapping("/all/{userId}")
    public Result<List<AddressInfo>> all(@PathVariable Long userId) {
        return Result.success(addressInfoService.findAll(userId));
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<AddressInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                              @PathVariable String name) {
        return Result.success(addressInfoService.findPage( name, pageSize, pageNum));
    }
}
