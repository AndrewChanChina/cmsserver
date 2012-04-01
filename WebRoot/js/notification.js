
var Notify = function(){}  
//判断是否支持webkitNotifications  
Notify.prototype.isSupport = function(){  
  return !!window.webkitNotifications;  
}  
//需要向用户申请权限来确定是否支持webkitNotifications，如果得到权限，就会执行callback，参数为true.  
Notify.prototype.getPermission = function(callback){  
  window.webkitNotifications.requestPermission(function(){  
    if (callback) {  
      callback(this.checkPermission());  
    }  
  });  
}  
//查看是否得到权限  
Notify.prototype.checkPermission = function(){  
  return !!(window.webkitNotifications.checkPermission() == 0);  
}  
//声明一个webkitNotifications实例，并且使用show方法触发桌面提示框  
Notify.prototype.show = function(icon, title, body){  
  //声明webkitNotifications实例  
  var _notify = window.webkitNotifications.createNotification(icon, title, body);  
  _notify.show();  
} 

Notify.prototype.showhtml = function(url){
	window.webkitNotifications.createHTMLNotification(url).show();
}
 