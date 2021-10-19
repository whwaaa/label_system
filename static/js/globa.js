// var url = "http://192.168.8.110"
// var url = "http://2539604i5x.qicp.vip:31832"
var url = ""
var url1 = "http://192.168.8.110"
var url2 = "http://2539604i5x.qicp.vip:31832"
var timeOut = 100
$(function(){
    
    $.ajax({
        type: "GET",
        data: "",
        cache: false,
        url: url1 + "/pingTest",
        xhrFields: {
          withCredentials: true
        },
        crossDomain: true,
        success: function () {
            console.log(url1 + "网址有效，url选用:" + url1);
            url = url1
        },
        error: function () {
            console.log(url1 + "无效网址，url选用:" + url2);
            url = url2
        }
    });
    
	// 监听菜单
	$(".dot").on("click", function(){
		if($(".lnr").hasClass("lnr-chevron-up")){
			$(".lnr").removeClass("lnr-chevron-up")
			$(".lnr").addClass("lnr-chevron-down")
			$(".footer").slideDown()
		}else{
			$(".lnr").addClass("lnr-chevron-up")
			$(".lnr").removeClass("lnr-chevron-down")
			$(".footer").slideUp()
		}
	})
})