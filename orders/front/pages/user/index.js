import {request} from "../../request/index.js";
Page({
  data: {
    isLogin: 0,
    userinfo:{},
    price:0
  },
  onShow(){
    let user = wx.getStorageSync('user');
    request({url:"/userInfo/" + user.id}).then(res => {
      // 有该用户
      if (res.code === "0") {
        this.setData({
          price: res.data.account,
          isLogin: 1,
        userinfo: user
        })
      } else {
        this.setData({
          isLogin: 0,
          userinfo: {},
          price: 0
        })
      }
    })
  },

  logout(e) {
    // 清空localStorage
    wx.removeStorageSync('user');
    let user = wx.getStorageSync('user');
    if (!user) {
      wx.showToast({
        title: '退出成功',
        mask: true
      })
    }
    setTimeout(() => {
      this.onShow();
    }, 1000);
  },

  recharge() {
    let user = wx.getStorageSync('user');
    if (!user) {
      wx.showToast({
        title: '请先登录',
        icon: 'none'
      })
    } else {
      // 跳转到 充值页面
      wx.navigateTo({
        url: '/pages/pay/index'
      });
    }
  },

  navigateToOrder(e) {
    let user = wx.getStorageSync('user');
    if (!user) {
      wx.showToast({
        title: '请先登录',
        icon: 'none'
      })
    } else {
      let status = e.currentTarget.dataset.status;
      // 跳转到 订单页面
      wx.navigateTo({
        url: '/pages/orderInfo/index?status=' + status
      });
    }
  }
})