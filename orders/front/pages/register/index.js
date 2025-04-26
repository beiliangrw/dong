import {request} from "../../request/index.js";
Page({
  data: {
    username: "",
    password: "",

    phone: "",

    url: "",
    isTabBar: 0
  },

  onLoad: function (options) {
    let url = options.url.replace("$", "?").replace("-","=");
    let isTabBar = options.isTabBar;
    this.setData({
      username: "",
      password: "",

      phone: "",

      url: url,
      isTabBar: isTabBar
    })
  },

  onName(e) {
    this.setData({
      username: e.detail.value
    })
  },

  onPassword(e) {
    this.setData({
      password: e.detail.value
    })
  },



  onPhone(e) {
    this.setData({
      phone: e.detail.value
    })
  },


  register(e) {
    let url = this.data.url;
    let isTabBar = this.data.isTabBar;
   const { username, password, phone, age, address } = this.data;
    if (!username.trim() || !password.trim() || !phone.trim()) {
      return wx.showToast({
        title: "信息填写不完整",
        icon: "none", // 显示纯文本提示
        duration: 1500
      });
    }
    const phoneReg = /^1[3-9]\d{9}$/;
    if (!phoneReg.test(phone)) {
      return wx.showToast({
        title: "手机号格式错误",
        icon: "none",
        duration: 1500
      });
    }
    const data = { name: username, password, phone, age, address };
    request({url:"/register", data:data, method:"POST"}).then(res => {
      if (res.code === "0") {
        wx.showToast({
          title: '注册成功',
          mask: true
        })
        // 存到localStorage里
        wx.setStorageSync('user', res.data)
        setTimeout(() => {
          if (isTabBar === "0") {
            wx.navigateTo({
              url: url,
            })
          } else {
            wx.switchTab({
              url: url,
            })
          }
        }, 1500);
      } else {
        wx.showToast({
          title: res.msg,
          mask: false
        })
      }
    })
  }
})