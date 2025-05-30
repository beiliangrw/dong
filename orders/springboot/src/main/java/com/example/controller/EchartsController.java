package com.example.controller;

import cn.hutool.json.JSONObject;
import com.example.common.Result;
import com.example.dao.CommentInfoDao;
import com.example.dao.OrderGoodsRelDao;
import com.example.dao.OrderInfoDao;
import com.example.dao.UserInfoDao;
import com.example.vo.EchartsData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private CommentInfoDao commentInfoDao;
    @Resource
    private OrderInfoDao orderInfoDao;
    @Resource
    private OrderGoodsRelDao orderGoodsRelDao;

    @GetMapping("/getTotal")
    Result<Map<String, Object>> getTotal() {
        Map<String, Object> map = new HashMap<>(4);
        // 获取用户总数
        map.put("totalUser", userInfoDao.count() == null ? 0 : userInfoDao.count());
        // 获取评论总数
        map.put("totalComment", commentInfoDao.count() == null ? 0 : commentInfoDao.count());
        // 获取总销售额
        map.put("totalPrice", orderInfoDao.totalPrice() == null ? 0 : orderInfoDao.totalPrice());
        // 获取总销量
        map.put("totalShopping", orderGoodsRelDao.totalShopping() == null ? 0 : orderGoodsRelDao.totalShopping());
        return Result.success(map);
    }


    @GetMapping("/get/shopping")
    Result<List<EchartsData>> getShoppingEchartsData() {
        List<EchartsData> list = new ArrayList<>();
        List<Map<String, Object>> typePriceList = orderInfoDao.getTypeCount();
        Map<String, Double> typeMap = new HashMap<>();
        for (Map<String, Object> map : typePriceList) {
            typeMap.put((String)map.get("name"), ((BigDecimal)map.get("count")).doubleValue());
        }

        // 处理按月统计总销量
        List<Map<String, Object>> timeList = orderInfoDao.getMonthCount();
        Map<String, Double> timeMap = new HashMap<>();
        for (Map<String, Object> map : timeList) {
            timeMap.put((String)map.get("time"), Double.parseDouble(map.get("count").toString()));
        }

        // 给timeMap里面的数据排个序
        LinkedHashMap<String, Double> map =
                timeMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey()).collect(
                                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new));


        getPieData("分类总销量", list, typeMap);
        getBarData("分类总销量", list, typeMap);
        getLineData("按月总销售额", list,map);
        return Result.success(list);
    }

    @GetMapping("/get/price")
    Result<List<EchartsData>> getPriceEchartsData() {
        List<EchartsData> list = new ArrayList<>();
        List<Map<String, Object>> typePriceList = orderInfoDao.getTypePrice();
        Map<String, Double> typeMap = new HashMap<>();
        for (Map<String, Object> map : typePriceList) {
            typeMap.put((String)map.get("name"), (Double)map.get("price"));
        }

        // 处理按年统计总销量
        List<Map<String, Object>> timeList = orderInfoDao.getYearCount();
        Map<String, Double> timeMap = new HashMap<>();
        for (Map<String, Object> map : timeList) {
            timeMap.put((String)map.get("time"), Double.parseDouble(map.get("count").toString()));
        }

        // 给timeMap里面的数据排个序
        LinkedHashMap<String, Double> map =
                timeMap.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey()).collect(
                                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal, newVal) -> oldVal, LinkedHashMap::new));


        getPieData("分类总销售额", list, typeMap);
        getBarData("分类总销售额", list, typeMap);
        getLineData("按年总销售额", list, map);
        return Result.success(list);
    }

    private void getPieData(String name, List<EchartsData> pieList, Map<String, Double> dataMap) {
        EchartsData pieData = new EchartsData();
        EchartsData.Series series = new EchartsData.Series();

        Map<String, String> titleMap = new HashMap<>(2);
        titleMap.put("text", name);
        pieData.setTitle(titleMap);

        series.setName(name + "比例");
        series.setType("pie");
        series.setRadius("55%");

        List<Object> objects = new ArrayList<>();
        List<Object> legendList = new ArrayList<>();
        for (String key : dataMap.keySet()) {
            Double value = dataMap.get(key);
            objects.add(new JSONObject().putOpt("name", key).putOpt("value", value));
            legendList.add(key);
        }
        series.setData(objects);

        pieData.setSeries(Collections.singletonList(series));
        Map<String, Boolean> map = new HashMap<>();
        map.put("show", true);
        pieData.setTooltip(map);

        Map<String, Object> legendMap = new HashMap<>(4);
        legendMap.put("orient", "vertical");
        legendMap.put("x", "left");
        legendMap.put("y", "center");
        legendMap.put("data", legendList);
        pieData.setLegend(legendMap);

        pieList.add(pieData);
    }

    private void getBarData(String name, List<EchartsData> barList, Map<String, Double> dataMap) {
        EchartsData barData = new EchartsData();
        EchartsData.Series series = new EchartsData.Series();

        List<Object> seriesObjs = new ArrayList<>();
        List<Object> xAxisObjs = new ArrayList<>();
        for (String key : dataMap.keySet()) {
            Double value = dataMap.get(key);
            xAxisObjs.add(key);
            seriesObjs.add(value);
        }

        series.setType("bar");
        series.setName(name);
        series.setData(seriesObjs);
        barData.setSeries(Collections.singletonList(series));

        Map<String, Object> xAxisMap = new HashMap<>(1);
        xAxisMap.put("data", xAxisObjs);
        barData.setxAxis(xAxisMap);

        barData.setyAxis(new HashMap<>());

        Map<String, Object> legendMap = new HashMap<>(1);
        legendMap.put("data", Collections.singletonList(name));
        barData.setLegend(legendMap);

        Map<String, Boolean> map = new HashMap<>(1);
        map.put("show", true);
        barData.setTooltip(map);

        Map<String, String> titleMap = new HashMap<>(1);
        titleMap.put("text", name);
        barData.setTitle(titleMap);

        barList.add(barData);
    }




//    折线图
private void getLineData(String name, List<EchartsData> lineList, Map<String, Double> dataMap) {
    EchartsData lineData = new EchartsData();
    EchartsData.Series series = new EchartsData.Series();

    List<Object> seriesObjs = new ArrayList<>();
    List<Object> xAxisObjs = new ArrayList<>();
    for (String key : dataMap.keySet()) {
        Double value = dataMap.get(key);
        xAxisObjs.add(key);
        seriesObjs.add(value);
    }

    series.setType("line");
    series.setName(name);
    series.setData(seriesObjs);
    lineData.setSeries(Collections.singletonList(series));

    Map<String, Object> xAxisMap = new HashMap<>(1);
    xAxisMap.put("type", "category");
    xAxisMap.put("data", xAxisObjs);
    lineData.setxAxis(xAxisMap);

    Map<String, Object> yAxisMap = new HashMap<>(1);
    yAxisMap.put("type", "value");
    lineData.setyAxis(yAxisMap);

    Map<String, String> titleMap = new HashMap<>(1);
    titleMap.put("text", name);
    lineData.setTitle(titleMap);

    lineList.add(lineData);
}

}
