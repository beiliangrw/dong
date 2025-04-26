package com.example.service;

import com.example.dao.AddressInfoDao;
import com.example.entity.AddressInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AddressInfoService {

    @Resource
    private AddressInfoDao addressInfoDao;

    public AddressInfo add(AddressInfo addressInfo) {
        addressInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        addressInfoDao.insertSelective(addressInfo);
        return addressInfo;
    }

    public void delete(Long id) {
        addressInfoDao.deleteByPrimaryKey(id);
    }

    public void update(AddressInfo addressInfo) {
        addressInfo.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        addressInfoDao.updateByPrimaryKeySelective(addressInfo);
    }

    public AddressInfo findById(Long id) {
        return addressInfoDao.selectByPrimaryKey(id);
    }

    public List<AddressInfo> findAll(Long userId) {
        return addressInfoDao.findByUserId(userId);
    }
    public List<AddressInfo> findAll() {
        return addressInfoDao.selectAll();
    }

    public PageInfo<AddressInfo> findPage(String name, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(addressInfoDao.findByName(name));
    }
}
