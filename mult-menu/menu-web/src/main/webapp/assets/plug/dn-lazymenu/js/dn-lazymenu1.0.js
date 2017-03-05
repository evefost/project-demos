(function(root,factory,plugName){
	//调用工厂函数
	factory(root.jQuery,plugName);
})(window,function($, plugName){

	var __DEFAULT__ = {
			url : "",//请求菜单数据的URL
			target : "_blank",
			effect : "slow"//滑动效果延迟时间
	};
	var __PROTOTYPE__ = {
			_init : function(){
				this._data(this,this.url);//填充数据到menu
				this._handle();//绑定菜单事件
			},
			_data : function(dom,url){
				var _this = this;
				$.getJSON(url,function(data){
					var dataList = data.data;
					if(dataList.lenght==0)return;

					var _html = "<ul class=\""+(dataList[0].parentId==0?"list":"submenu")+"\">";
					for(var i=0;i<dataList.length;i++){
						_html += (dataList[i].parentId==0?"<li class=\"item\">":"<li>");
						if(dataList[i].url){
							_html +="<a href=\""+dataList[i].url+"\" target=\""+_this.target+"\"><b class=\"icon iconfont\">"+dataList[i].icon+"</b>"+dataList[i].menuName+"</a>";
						}else{
							_html +="<a><b class=\"icon iconfont\">"+dataList[i].icon+"</b>"+dataList[i].menuName+"</a>";
						}
						_html +="<div data-id=\""+dataList[i].id+"\"></div>";
						_html += "</li>";
					}
					_html += "</ul>";
					dom.html(_html);
				});
			},
			_handle : function(){
				var _this = this;
				this.on("click","ul li a",function(){
					var $this = $(this);
					var $dom = $this.next();
					var parentId = $dom.data("id");//data-xx
					var isLoad = Boolean($dom.html());
					if(isLoad){
						$dom.find("ul:first").slideToggle();
					}else{
						var url = _this.url+"?parentId="+parentId;//子菜单的URL
						_this._data($dom,url);
					}
				});
			}
	};
	//生成jquery插件，插件的名字是plugName
	$.fn[plugName] = function(options){
		$.extend(this,__DEFAULT__,options,__PROTOTYPE__);
		this._init();
	}
},"LazyMenu");