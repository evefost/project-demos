////window.onload  dom加载完毕之后回调
//$(function(){
//	//执行
//	//找到所有需要点击的菜单的对象
//	var $menus = $('#menu ul.list li a');
//	//绑定点击事件
//	$menus.click(function(){
//		$(this).next('ul').slideToggle();//.parent().siblings().find("ul:first").slideToggle();
//	});
//	//$menus.eq(0).trigger('click');
//});

$(function(){
	$("#tree").LazyMenu({
		url:"/api/menus/selectMenuList"
	});
});