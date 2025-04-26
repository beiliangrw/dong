package com.example.service;

import cn.hutool.core.collection.CollUtil;
import com.example.dao.CollectInfoDao;
import com.example.entity.CollectInfo;
import com.example.entity.GoodsInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollectInfoService {

    @Resource
    private CollectInfoDao collectInfoDao;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private GoodsInfoService goodsInfoService;

    public CollectInfo add(CollectInfo collectInfo) {
        CollectInfo collectInfo1 = collectInfoDao.findByUser(collectInfo);
        if(collectInfo1 == null) {
            collectInfoDao.insertSelective(collectInfo);
            return collectInfo;
        }else {
            return null;
        }
    }

    public void delete(Long id) {
        collectInfoDao.deleteByPrimaryKey(id);
    }

    public void update(CollectInfo collectInfo) {
        collectInfoDao.updateByPrimaryKeySelective(collectInfo);
    }

    public CollectInfo findById(Long id) {
        return collectInfoDao.selectByPrimaryKey(id);
    }

    public List<CollectInfo> findAll() {
        return collectInfoDao.selectAll();
    }

    public PageInfo<CollectInfo> findPage(Integer pageNum, Integer pageSize, String name) {
        PageHelper.startPage(pageNum, pageSize);
        List<CollectInfo> all = collectInfoDao.findByName(name);
        return PageInfo.of(all);
    }

    public PageInfo<CollectInfo> findFrontPages(Long userId, Integer level, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CollectInfo> collectInfos;
        if (userId != null){
            collectInfos = collectInfoDao.findByEndUserId(userId, level);
        } else {
            collectInfos = new ArrayList<>();
        }
        for (CollectInfo collectInfo : collectInfos) {
            packOrder(collectInfo);
        }
        return PageInfo.of(collectInfos);
    }
    /**
     * 包装收藏的用户和商品信息
     */
    private void packOrder(CollectInfo collectInfo) {
        collectInfo.setUserInfo(userInfoService.findById(collectInfo.getUserId()));
        List<GoodsInfo> goodsList = CollUtil.newArrayList();
        collectInfo.setGoodsList(goodsList);
        GoodsInfo goodsDetailInfo = goodsInfoService.findById(collectInfo.getForeignId());
        if (goodsDetailInfo != null) {
            goodsList.add(goodsDetailInfo);
        }
    }
}
