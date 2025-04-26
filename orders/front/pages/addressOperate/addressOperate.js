import {request} from "../../request/index.js";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    addressInfo:{
      id: '',
      name: '',
      phone: '',
      address: '',
      uid:'',
    },
    id: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let id = options['id'];
    var that = this;
    that.data.id = id;
    if(that.data.id){
      that.getData(id);
    }
  },
   /**
   * 根据id查询当前桌号详情
   */
  getData: function(id){
    let url = "/addressInfo/" + id;
    request({url: url}).then(res => {
      if (res) {
        this.setData({
          addressInfo: res.data
        })
      } else {
        console.log(res.data.msg)
      }
    })
  },
  onInput: function(e){
    let typename = e.currentTarget.dataset.typename;
    let value = e.detail.value;
    this.setMyData(typename,value);
  },

  setMyData: function(key,value){
    let formData = this.data.addressInfo;
    formData[key] = value;
    this.setData({
      addressInfo: formData
    })
  },

  /**
   * 提交
   */
  operate: function(){
    var that = this;
    let user = wx.getStorageSync('user');
    this.setMyData("uid",user.id)
    let formData = that.data.addressInfo;
    if(!formData.id){
    request({url:"/addressInfo", data:formData, method:"POST"}).then(res => {
      if (res.code === "0") {
        wx.showToast({
          title: '',
        })
        wx.navigateBack({
          delta: 1
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: "none"
        })
      }
    })
  }else{
    request({url:"/addressInfo", data:formData, method:"PUT"}).then(res => {
      if (res.code === "0") {
        wx.showToast({
          title: '',
        })
        wx.navigateBack({
          delta: 1
        })
      } else {
        wx.showToast({
          title: res.msg,
          icon: "none"
        })
      }
    })
  }
  }

})