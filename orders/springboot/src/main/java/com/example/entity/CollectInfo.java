package com.example.entity;

import javax.persistence.*;
import java.util.List;


@Table(name = "collect_info")
public class CollectInfo {
    /**
     * 自增id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 所属用户
     */
    @Column(name = "userId")
    private Long userId;
    private Integer level;
    @Column(name = "foreignId")
    private Long foreignId;
    @Transient
    private UserInfo userInfo;

    @Transient
    private List<GoodsInfo> goodsList;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<GoodsInfo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsInfo> goodsList) {
        this.goodsList = goodsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getForeignId() {
        return foreignId;
    }

    public void setForeignId(Long foreignId) {
        this.foreignId = foreignId;
    }
}
