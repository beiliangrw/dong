package com.example.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.entity.CollectInfo;
import com.example.entity.OrderInfo;
import com.example.service.CollectInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/collectInfo")
public class CollectInfoController {
    @Resource
    private CollectInfoService collectInfoService;

    @PostMapping
    public Result<CollectInfo> add(@RequestBody CollectInfo collectInfo) {
        CollectInfo collectInfo1 =collectInfoService.add(collectInfo);
        if(collectInfo1  == null){
            return Result.error("500","请勿重复收藏");
        }
        return Result.success(collectInfo);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        collectInfoService.delete(id);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody CollectInfo collectInfo) {
        collectInfoService.update(collectInfo);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<CollectInfo> detail(@PathVariable Long id) {
        CollectInfo collectInfo = collectInfoService.findById(id);
        return Result.success(collectInfo);
    }

    /**
     * 查询所有信息（分页）
     */
    @GetMapping("/page/front")
    public Result<PageInfo<CollectInfo>> findFrontPage(@RequestParam(required = false) Long userId,
                                                     @RequestParam(required = false) Integer level,
                                                     @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        return Result.success(collectInfoService.findFrontPages(userId, level, pageNum, pageSize));
    }
    @GetMapping
    public Result<List<CollectInfo>> all() {
        return Result.success(collectInfoService.findAll());
    }

    @GetMapping("/page/{name}")
    public Result<PageInfo<CollectInfo>> page(@RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "10") Integer pageSize,
                                           @PathVariable String name) {
        return Result.success(collectInfoService.findPage(pageNum, pageSize, name));
    }
}
