var url = "https://locallabel.jumaojiang.top"
var timeOut = 0
$(function(){
    
    // $.ajax({
    //     type: "GET",
    //     data: "",
    //     cache: false,
    //     url: url1 + "/pingTest",
    //     xhrFields: {
    //       withCredentials: true
    //     },
    //     crossDomain: true,
    //     success: function () {
    //         console.log(url1 + "网址有效，url选用:" + url1);
    //         url = url1
    //     },
    //     error: function () {
    //         console.log(url1 + "无效网址，url选用:" + url2);
    //         url = url2
    //     }
    // });
    
	// 监听菜单
	$(".dot").on("click", function(){
		if(location.href.indexOf(("saveList.html"))!=-1){
			if($(".lnr").hasClass("lnr-chevron-up")){
				$(".footer2").slideUp()
				setTimeout(function(){
					$(".lnr").removeClass("lnr-chevron-up")
					$(".lnr").addClass("lnr-chevron-down")
					$(".footer").slideDown()
				},250)
			}else{
				$(".lnr").addClass("lnr-chevron-up")
				$(".lnr").removeClass("lnr-chevron-down")
				$(".footer").slideUp()
				setTimeout(function(){
					var myhref = location.href.split("//")[1]
					console.log(myhref)
					$(".footer2>div").empty()
					$(".footer2>div").append($("<a href='http://sao315.com/w/api/saoyisao?redirect_uri="+myhref+"'><div class='footer-box footer-createTemplate col-xs-12'>条码扫描</div></a>"))
					$(".footer2").slideDown()
				},250)
			}
		}else{
			if($(".lnr").hasClass("lnr-chevron-up")){
				$(".lnr").removeClass("lnr-chevron-up")
				$(".lnr").addClass("lnr-chevron-down")
				$(".footer").slideDown()
			}else{
				$(".lnr").addClass("lnr-chevron-up")
				$(".lnr").removeClass("lnr-chevron-down")
				$(".footer").slideUp()
			}
		}
	})
})