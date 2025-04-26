package com.example.dao;

import com.example.entity.AddressInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AddressInfoDao extends Mapper<AddressInfo> {
    List<AddressInfo> findByName(@Param("name") String name);

    @Select("select * from address_info where uid = #{userId}")
    List<AddressInfo> findByUserId(Long userId);
}
