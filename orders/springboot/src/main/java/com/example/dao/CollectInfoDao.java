package com.example.dao;

import com.example.entity.CollectInfo;
import com.example.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CollectInfoDao extends Mapper<CollectInfo> {

    List<CollectInfo> findByName(@Param("name") String name);

    CollectInfo findByUser(CollectInfo collectInfo);

    List<CollectInfo> findByEndUserId(Long userId, Integer level);
}
