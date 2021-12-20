$(function(){
    // 判断设备类型
    let ua = navigator.userAgent.toLowerCase();
    let isAndroid = ua.indexOf('android') != -1;
    let isIos = (ua.indexOf('iphone') != -1) || (ua.indexOf('ipad') != -1);
	
    // 样式补丁
	$(".box").css("margin-top","39px")
	$(".box").css("margin-bottom",0)
	console.log(document.body.clientHeight)
	$(".index-box").css("height",document.body.clientHeight-80)
    
	
	// 搜索条件文字间自动加空格
	$("input[name='q-typeName']").on('blur', function() {
		var val = $(this).val().replace(/\s/g,"");
		console.log(val)
		var newVal = "";
		for(var i=0; i<val.length; i++){
			newVal += val.substr(i,1)+" ";
		}
		newVal = newVal.trim()
		$(this).val(newVal)
	})
	
	// 监听查询
	$(".myquery").on("click", function(){
		$(".index-box").empty();
		getAllData();
	})
	
	var kuanHaoNameList;
	var kuanHaoMapList;
	
	// 获取所有数据
	function getAllData(){
		let download = $("select[name='q-download']").val()
		let kuanHao = $("input[name='q-kuanHao']").val()
		let createTime = $("input[name='q-date']").val()
		let typeName = $("input[name='q-typeName']").val()
	    layer.load(0)
	    $.ajax({
    		type: 'GET',
    		url: url + "/editLabel/label",
    		data: {blank1:download,createTime:createTime,typeName:typeName,kuanHao:kuanHao},
    		xhrFields: {
    			withCredentials: true
    		},
    		crossDomain: true,
    		dataType: "json",
    		success: function(data){
				console.log(data)
    			layer.closeAll();
    			if(data.code == 200){
    				kuanHaoNameList = data.obj.kuanHaoName
    				kuanHaoMapList = data.obj.kuanHaoMap
					alreadyDownMapList = data.obj.alreadyDownMap
					console.log(kuanHaoMapList)
    				$.each(kuanHaoNameList, function(i, v){
						var dirName = kuanHaoMapList[v][0].typeName.replaceAll(" ","")+"_"+kuanHaoMapList[v][0].kuanHao;
						var $li = '';
						if(isAndroid || isIos){	// 移动端
							$li = $("<li class='samKuanHaoLi "+kuanHaoMapList[v][0].kuanHao+"' style='position: relative;'><p kuanhao='"+kuanHaoMapList[v][0].kuanHao+"' class='sameKuanhao_title'>"+dirName+"</p><ul class='sub'></ul></li>")
							$li.prepend("<div class='mobile-edte' onclick=\"location.href='editLabel.html?kh="+kuanHaoMapList[v][0].kuanHao+"'\">重录</div>")
						}else{	// PC端
							$li = $("<li class='samKuanHaoLi "+kuanHaoMapList[v][0].kuanHao+"' style='position: relative;'><div class='down-dot'><form action='"+url+"/editLabel/label/download?fileName="+kuanHaoMapList[v][0].typeName.replaceAll(" ","")+"_"+kuanHaoMapList[v][0].kuanHao+"' method='post' enctype='multipart/form-data'><button class='visible-lg lnr lnr-download'></button></form></div><p kuanhao='"+kuanHaoMapList[v][0].kuanHao+"' class='sameKuanhao_title'>"+dirName+"</p><ul class='sub'></ul></li>")
							$li.prepend("<div class='pc-edte' onclick=\"location.href='editLabel.html?kh="+kuanHaoMapList[v][0].kuanHao+"'\">重录</div>");
							if(kuanHaoMapList[v][0].blank1 == '1'){
								$li.prepend("<div class='already_down-dot visible-lg'>已下载</div>")
							}else{
								$li.prepend("<div class='already_down-dot visible-lg'>未下载</div>")
							}
							// if(alreadyDownMapList[dirName+'.zip'] != undefined){
							// 	// console.log("已存在目录" + dirName)
							// 	$li.prepend("<div class='already_down-dot visible-lg'>已下载</div>")
							// }
						}	
						
						// $li = $("<li class='samKuanHaoLi "+kuanHaoMapList[v][0].kuanHao+"' style='position: relative;'><div class='down-dot'><form action='"+url+"/editLabel/label/download?fileName="+kuanHaoMapList[v][0].typeName.replaceAll(" ","")+"_"+kuanHaoMapList[v][0].kuanHao+"' method='post' enctype='multipart/form-data'><button class='visible-lg lnr lnr-download'></button></form></div><p kuanhao='"+kuanHaoMapList[v][0].kuanHao+"' class='sameKuanhao_title'>"+dirName+"</p><ul class='sub'></ul></li>")
						// if(alreadyDownMapList[dirName+'.zip'] != undefined){
						// 	// console.log("已存在目录" + dirName)
						// 	$li.prepend("<div class='mobile-edte'><span class=''>编辑</span></div>")
						// }
						
						
    					$.each(kuanHaoMapList[v], function(){
    						$li.find(".sub").append($("<div class='subbox'><i class='icon'></i><li id='li_"+this.id+"'>"+this.typeName.replaceAll(" ","")+"_"+this.kuanHao+"_"+this.color.replaceAll(" ","")+"_"+this.size+"_"+this.buyPrice+"_"+this.sellPrice+"</li></div>"))	
    					})    
        
    					$li.find(".sub").append($("<div style='margin-top:10px;margin-left: -10px;padding-left: 0px;' class='visible-xs show-box col-xs-12 col-md-0'><div class='row'></div></div>"))
    					$(".index-box").append($li)
    				})
    				mouserMove();
    			}else{
    				// 查询失败
    				console.log(data.msg,function(){})
    				// layer.msg("<span style='color: white'>" + data.message + "</span>",function(){})
    			}
    		},
    		error: function(){
    			layer.closeAll();
    			layer.msg("服务器内部出错",function(){})
    		}
	    });
	}
	
	
	// 监听鼠标点击标题
	function mouserMove(){
		$(".sameKuanhao_title").on("click",function(){
			// $(this).siblings(".mobile-edte").addClass("active")
			// $(this).parent().siblings().find(".mobile-edte").removeClass("active")
			var kuanhao = $(this).attr("kuanhao")
			var $sub = $(this).parent().children(".sub")
			// 2.1下拉二级菜单
			if($sub.css("display").endsWith('none')){
				// 停止当前动画
				$sub.stop();
				$(".sub").slideUp(700)
				$sub.slideDown(700)
				// 渲染标签预览模块
				loadLabelBox(kuanhao,$(this).parents("li").index())
			}else{
				// 2.2收缩二级菜单
				// 停止当前动画
				$sub.stop();
				$sub.slideUp(700);
				$(".sameKuanhao_title").parents("li").css("margin-top",0)
			}
		})
	}
		
		
	// 加载标签预览
	function loadLabelBox(kuanhao,index){
		var i = 1;
		var windowWidth = $(window).width();
		$(".show-box").find(".row").empty()
		$(".show-box-lg").find(".row").empty()
		$(".sameKuanhao_title").parents("li").css("margin-top",0)
		$.each(kuanHaoMapList[kuanhao], function(){
			// console.log(this)
			var hidden_styleId = this.blank2;
			var size_option = (hidden_styleId & (1<<3))>0?true:false
			var liliao_option = (hidden_styleId & (1<<2))?true:false
			var filler_option = (hidden_styleId & (1<<1))?true:false
			var guide_option = (hidden_styleId & 1)?true:false
			
			i *= -1;
			var showbox = "";
			if(i > 0 && windowWidth > 640){	// 偶数项
				showbox += "<div class='label col-md-offset-0 col-md-5'>"
			}else{
				showbox += "<div class='label col-md-offset-1 col-md-5'>"
			}
			showbox += "<div class='show_box' id='barcode"+this.barCode+"'>\n" +
						"<input type='hidden' class='templateId' value='"+this.templateId+"'/>" +
						"<input type='hidden' class='id' value='"+this.id+"' />" +
						"<input type='hidden' class='buyPrice' value='"+this.buyPrice+"' />" +
						"\t\t\t\t\t\t\t\t<div class='label_title' style='margin: 6px auto 6px auto; font-size: 1.6rem;text-align: center;'>合格证</div>\n" +
						"\t\t\t\t\t\t\t\t<div class='preview'>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_title'>品牌:</div>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_content'>彩绸</div>\n" +
						"\t\t\t\t\t\t\t\t</div>\n" +
						"\t\t\t\t\t\t\t\t<div class='preview'>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_title'>品名:</div>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='pre_typeName preview_content'>"+this.typeName+"</div>\n" +
						"\t\t\t\t\t\t\t\t</div>\n" +
						"\t\t\t\t\t\t\t\t<div class='preview'> \n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_title'>款号:</div>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='pre_kuanhao preview_content'>"+this.kuanHao+"</div>\n" + 
						"<div class='preview_title size visibility' style='margin-left:10px;'>尺码:</div>\n";
			if(size_option){
				showbox +=
						"\t\t\t\t\t\t\t\t\t<input style='max-width:36px' class='pre_size preview_content size visibility' value='"+this.size+"'/>\n";
			}else{
				showbox +=
						"\t\t\t\t\t\t\t\t\t<input style='max-width:36px' class='pre_size preview_content size visibility' value='均码'/>\n";
			}
				
			showbox += 			
						"\t\t\t\t\t\t\t\t</div>\n" +
						"\t\t\t\t\t\t\t\t<div class='preview'>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_title'>颜色:</div>\n" +
						"\t\t\t\t\t\t\t\t\t<input style='max-width:120px' class='pre_color preview_content' value='"+this.color+"'/>\n" +
						"\t\t\t\t\t\t\t\t</div>\n" +
						"\t\t\t\t\t\t\t\t<div class='preview'>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_title'>执行标准:</div>\n" +
						"\t\t\t\t\t\t\t\t\t<input style='max-width:160px' class='pre_zxbz preview_content select_max_width_M' value='"+this.zxbz+"'/>\n" +
						"\t\t\t\t\t\t\t\t</div>\n" +
						"\t\t\t\t\t\t\t\t<div class='preview'>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_title'>产品类别:</div>\n" +
						"\t\t\t\t\t\t\t\t\t<input style='max-width:160px' class='pre_cplb preview_content select_max_width_M' value='"+this.cplb+"'/>\n" +
						"\t\t\t\t\t\t\t\t</div>\n" +
						"\t\t\t\t\t\t\t\t<div class='preview'>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_title'>面料:</div>\n" +
						"\t\t\t\t\t\t\t\t\t<input class='pre_mianliao preview_content select_max_width_L' value='"+this.outFabric+"'/>\n" +
						"\t\t\t\t\t\t\t\t</div>\n"
			if(liliao_option)
				showbox += 
						"\t\t\t\t\t\t\t\t<div class='preview'>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_title'>里料:</div>\n" +
						"\t\t\t\t\t\t\t\t\t<input class='preview_content pre_liliao select_max_width_L' value='"+this.intFabric+"'/>\n" +
						"\t\t\t\t\t\t\t\t</div>\n"
			if(filler_option)
				showbox += 			
						"\t\t\t\t\t\t\t\t<div class='preview'>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_title'>填充物:</div>\n" +
						"\t\t\t\t\t\t\t\t\t<input style='max-width:100px' class='pre_filler preview_content' value='"+this.filler+"'/>\n" +
						"\t\t\t\t\t\t\t\t</div>\n"
			showbox += 		
						"\t\t\t\t\t\t\t\t<div class='preview'>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_title'>检验员:</div>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='pre_checker preview_content'>"+this.checker+"</div>\n" +
						"\t\t\t\t\t\t\t\t</div>\n" +
						"\t\t\t\t\t\t\t\t<div class='preview'>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview_title'>等级:</div>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='pre_level preview_content'>"+this.level+"</div>\n" +
						"\t\t\t\t\t\t\t\t</div>\n"
			if(guide_option){
				showbox += 	
						"\t\t\t\t\t\t\t\t<div class=''>\n" +
						"\t\t\t\t\t\t\t\t\t<div class='preview'>\n" +
						"\t\t\t\t\t\t\t\t\t\t<div class='preview_title'>统一零售价:</div>\n" +
						"\t\t\t\t\t\t\t\t\t\t<input style='max-width:100px' class='pre_guide preview_content' value='"+this.guidePrice+"'/>\n" +
						"\t\t\t\t\t\t\t\t\t</div>\n" +
						"\t\t\t\t\t\t\t\t</div>\n" +
						"\t\t\t\t\t\t\t\t<p style='margin:0 0 0 10%; font-size: 0.7rem;'>(此条码仅供内部使用)</p>\n" +
						"\t\t\t\t\t\t\t\t<canvas class='canvascode' width='810' height='20'></canvas>\n" +
						"\t\t\t\t\t\t\t\t<div class='per_barcode' style='margin:0 0 0 10%;'>"+this.barCode+"</div>\n" +
						"\t\t\t\t\t\t\t\t\n" +
						"\t\t\t\t\t\t\t\t<div style='margin: 2px auto 6px auto; font-size: 1.5rem; text-align: center;'>\n" +
						"\t\t\t\t\t\t\t\t\t&nbsp;工厂直销价 : <input style='max-width:80px' class='pre_sell' value='"+this.sellPrice+"'/>\n" +
						"\t\t\t\t\t\t\t\t</div>\n" +
						"\t\t\t\t\t\t\t</div>"
			}else{
				showbox +=
						"\t\t\t\t\t\t\t\t<p style='margin:0 0 0 10%; font-size: 0.7rem;'>(此条码仅供内部使用)</p>\n" +
						"\t\t\t\t\t\t\t\t<canvas class='canvascode' width='810' height='20'></canvas>\n" +
						"\t\t\t\t\t\t\t\t<div class='per_barcode' style='margin:0 0 0 10%;'>"+this.barCode+"</div>\n" +
						"\t\t\t\t\t\t\t\t\n" +
						"\t\t\t\t\t\t\t\t<div style='margin: 2px auto 6px auto; font-size: 1.5rem; text-align: center;'>\n" +
						"\t\t\t\t\t\t\t\t\t&nbsp;零售价 : <input style='max-width:80px' class='pre_sell' value='"+this.sellPrice+"'/>\n" +
						"\t\t\t\t\t\t\t\t</div>\n" +
						"\t\t\t\t\t\t\t</div>"
			}
					
			if(windowWidth > 640){
				$(".show-box-lg").find(".row").append($(showbox))
			}else{
				$(".show-box").eq(index).find(".row").append($(showbox))
			}
			
			
			
		})
		if(windowWidth < 640){
			// console.log($(".show-box").eq(index).height())
			var height = $(".show-box").eq(index).height()
			$(".sameKuanhao_title").eq(index+1).parents("li").css("margin-top",height)
		}
			
		// 生成二维码
		createBarcode(1,40)
		// 注册监听标签选择
		lisen_showbox_slaveList();
	}
	
	
	// 生成二维码
	function createBarcode(w,h){
		JsBarcode('.canvascode', $(".per_barcode").text(), {
			format: "CODE128",//选择要使用的条形码类型
			width:w,//设置条之间的宽度
			height:h,//高度
			displayValue:false,//是否在条形码下方显示文字
			// text:"456",//覆盖显示的文本
			fontOptions:"bold italic",//使文字加粗体或变斜体
			font:"fantasy",//设置文本的字体
			textAlign:"right",//设置文本的水平对齐方式
			textPosition:"top",//设置文本的垂直位置
			textMargin:5,//设置条形码和文本之间的间距
			fontSize:1,//设置文本的大小
			background:"#fff",//设置条形码的背景
			lineColor:"#000",//设置条和文本的颜色。
			margin:0//设置条形码周围的空白边距
		})
	}


	// 监听标签选择
	var native_size
	var native_color
	var native_zxbz
	var native_cplb
	var native_mianliao
	var native_liliao
	var native_filler
	var native_guide
	var native_sell
	var flag_id
	function lisen_showbox_slaveList(){
		$(".show_box").on("click",function(){
			if(flag_id != $(this).find(".id").val()){
				console.log("更新id")
				flag_id = $(this).find(".id").val()
				native_size = $(this).find(".pre_size").val()
				native_color = $(this).find(".pre_color").val()
				native_zxbz = $(this).find(".pre_zxbz").val()
				native_cplb = $(this).find(".pre_cplb").val()
				native_mianliao = $(this).find(".pre_mianliao").val()
				native_liliao = $(this).find(".pre_liliao").val()
				native_filler = $(this).find(".pre_filler").val()
				native_guide = $(this).find(".pre_guide").val()
				native_sell = $(this).find(".pre_sell").val()
				
				$(".show_box").css("margin-top","5px");
				$(".show_box").css("border-color", "black")
				$(".show_box").find(".dot-box").remove()
				$(this).css("margin-top","20px");
				$(this).css("border-color", "red")
				$(this).append($("<div class='dot-box update-dot'><span class='lnr lnr-checkmark-circle'></span></div>"))
				$(this).append($("<div class='dot-box cancel-dot'><span class='lnr lnr-cross-circle'></span></div>"))
				$(this).append($("<div class='dot-box delete-dot'><span class='lnr lnr-trash'></span></div>"))
				
				// 监听修改,删除,取消按钮
				update_delete_cancel_dot($(this));
			}
		})
	}
	
	// 监听修改,删除,取消按钮
	function update_delete_cancel_dot($_this){
		$(".update-dot").on("click",function(){
			var typeName = $_this.find(".pre_typeName").text()
			var kuanHao = $_this.find(".pre_kuanhao").text()
			var buyPrice = $_this.find(".buyPrice").val()
			var size = $_this.find(".pre_size").val()
			var color = $_this.find(".pre_color").val()
			var zxbz = $_this.find(".pre_zxbz").val()
			var cplb = $_this.find(".pre_cplb").val()
			var mianliao = $_this.find(".pre_mianliao").val()
			var liliao = $_this.find(".pre_liliao").val()
			var filler = $_this.find(".pre_filler").val()
			var guide = $_this.find(".pre_guide").val()
			var sellPrice = $_this.find(".pre_sell").val()
			var barCode = $_this.find("per_barcode").val()
			var id = $_this.parent().find(".id").val();
			layer.load(0)
			// 发送数据
			$.ajax({
				type: 'POST',
				url: url + "/editLabel/label/update",
				data: {size:size,color:color,zxbz:zxbz,cplb:cplb,
				outFabric:mianliao,intFabric:liliao,filler:filler,
				guidePrice:guide,sellPrice:sellPrice,barCode:barCode,id:id},
				xhrFields: {
					withCredentials: true
				},
				crossDomain: true,
				dataType: "json",
				success: function(data){
					console.log(data)
					layer.closeAll();
					if(data.code == 200){
						// 修改成功
						layer.msg(data.msg)
						// 更新目录
						$("#li_" + id).text(typeName+"_"+kuanHao+"_"+color+"_"+size+"_"+buyPrice+"_"+sellPrice)
						$_this.css("margin-top","5px");
						$_this.css("border-color", "black");
						$_this.find(".dot-box").remove();
						flag_id=null
					}else{
						// 修改失败
						console.log(data.msg,function(){})
						// layer.msg("<span style='color: white'>" + data.message + "</span>",function(){})
					}
				}
			});
		})
	
		$(".delete-dot").on("click",function(){
			layer.confirm("确认删除标签?",{
				btn: ['确认', '取消']
			}, function () {
				// 按钮1的事件
				var id = $_this.parent().find(".id").val();
				layer.load(0)
				$.ajax({
					type: 'POST',
					url: url + "/editLabel/label/delete",
					data: {id:id},
					xhrFields: {
						withCredentials: true
					},
					crossDomain: true,
					dataType: "json",
					success: function(data){
						layer.closeAll();
						if(data.code == 200){
							// 删除成功
							layer.msg(data.msg)
							// 更新目录
							$("#li_" + id).parents(".subbox").remove()
							$_this.remove()
							flag_id=null
						}else{
							// 修改失败
							console.log(data.msg,function(){})
						}
					}
				});
			}, function(){
				// 按钮2的事件
			});
		})
		
		$(".cancel-dot").on("click",function(){
			$_this.find(".pre_size").val(native_size)
			$_this.find(".pre_color").val(native_color)
			$_this.find(".pre_zxbz").val(native_zxbz)
			$_this.find(".pre_cplb").val(native_cplb)
			$_this.find(".pre_mianliao").val(native_mianliao)
			$_this.find(".pre_liliao").val(native_liliao)
			$_this.find(".pre_filler").val(native_filler)
			$_this.find(".pre_guide").val(native_guide)
			$_this.find(".pre_sell").val(native_sell)
			$_this.css("margin-top","5px");
			$_this.css("border-color", "black");
			$_this.find(".dot-box").remove();
			setTimeout(function(){
				flag_id=null
			}, 100)
		})
	}
	
	// 解析条码
	barCodeScan()
	function barCodeScan(){
		// 得到扫描 结果
		var qr = getQueryString("qrresult");
		if (qr) {
			$("select[name='q-download']").val("-1")
		    try{
				setTimeout(function(){
					var qrString = qr.split(",")[1];
					for(var key in kuanHaoMapList){
						$.each(kuanHaoMapList[key], function(){
							var sbarcode = this.barCode;
							if(qrString == sbarcode){
								var scrolltopValue = $("."+key).position().top
								$("."+key).find(".sameKuanhao_title").trigger("click")
								setTimeout(function(){
									console.log(scrolltopValue)
									$(".index-box").scrollTop(scrolltopValue-10)
								},100)
								return false;
							}
						})
					}
				},1000)
				
		    }catch(exception){
		
		    }
		}
		// 获取所有数据
		getAllData()
	}
	// 解析扫描结果
	function getQueryString(name) {
	    var reg = new RegExp("\\b" + name + "=([^&]*)");
		var r = location.href.match(reg);
		if (r != null)
			return decodeURIComponent(r[1]);
	}
})