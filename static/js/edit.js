$(function(){
	// 常量
	var date = 210611
	
	// 监听编辑,同步生成预览
	$(".val").bind('input propertychange click', function() {
		var id = 'pre_' + $(this).attr('id')
		// console.log("val: " + $(this).val() + "   id: " + id)
		// 计算条码
		if(id.endsWith("price") || id.endsWith("kuanhao")){
			var price = "", kuanhao = "", temp = "", qrcode = ""
			if(id.endsWith("price")){
				price = $(this).val()
				kuanhao = $("#pre_kuanhao").text()
				$('#'+ id).text($(this).val())
				$(this).css("border", "none");
			}else{ // id.endsWith("kuanhao")
				price = $("#price").val()
				kuanhao = $("#kuanhao_prefix").text() + $(this).val()
				$('#'+ id).text(kuanhao)
				$(this).css("border", "none");
			}
			if(price.length == 3){
				temp = price.substr(-1,1)
				temp += price.substr(-2,1)
				temp += price.substr(-3,1)
				qrcode = date + '0' + temp + '0' + kuanhao.substr(-3,3)
			}else if(price.length == 4){
				temp = price.substr(-1,1)
				temp += price.substr(-2,1)
				temp += price.substr(-3,1)
				temp += price.substr(-4,1)
				qrcode = date + '0' + temp + '0' + kuanhao.substr(-2,2)
			}
			$("#qrcode").text(qrcode)
			JsBarcode('#canvascode', qrcode, {
				format: "CODE128",//选择要使用的条形码类型
				width:1,//设置条之间的宽度
				height:40,//高度
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
		}else{
			// 非计算条码,填充预览视图
			$('#'+ id).text($(this).val())
			$(this).css("border", "none");
		}
		
	})
	
	// 监听尺码按钮
	$("#mySwitch").on("click", function(){
		if($(this).hasClass("mui-active")){
			// 已开启
			$(".size").removeClass("visibility")
		}else{
			// 已关闭
			$(".size").addClass("visibility")
			
		}
	})
	
	
	// 监听重置/删除
	$("#but_res").on("click", function(){
		
	})
	
	// 提交保存
	$("#but_sub").on("click", function(){
		Base64.encode('china is so nb')
		var kuanhao,size,color,zxbz,cplb,mianliao,liliao,price,sell,barcode,name,checker,level;
		var size_Flag = false;
		if($("#mySwitch").hasClass("mui-active")){	// 尺码开启再判断
			size_Flag = true;
		}
		kuanhao = $("#pre_kuanhao").text() 
		if(kuanhao.endsWith("--------")){
			layer.msg('款号未确认', function(){});
			return;
		}
		kuanhao = Base64.encode($("#pre_kuanhao").text())
		
		if(size_Flag){
			size = $("#pre_size").text()
			if(size.endsWith("--------")){
				layer.msg('尺码未确认', function(){});
				return;
			}
			size = Base64.encode($("#pre_size").text())
		}
		
		color = $("#pre_color").text()
		if(color.endsWith("--------")){
			layer.msg('颜色未确认', function(){});
			return;
		}
		color = Base64.encode($("#pre_color").text())
		
		zxbz = $("#pre_zxbz").text()
		if(zxbz.endsWith("--------")){
			layer.msg('执行标准未确认', function(){});
			return;
		}
		zxbz = Base64.encode($("#pre_zxbz").text())
		
		cplb = $("#pre_cplb").text()
		if(cplb.endsWith("--------")){
			layer.msg('产品类别未确认', function(){});
			return;
		}
		cplb = Base64.encode($("#pre_cplb").text())
		
		mianliao = $("#pre_mianliao").text()
		if(mianliao.endsWith("--------")){
			layer.msg('面料未确认', function(){});
			return;
		}
		mianliao = Base64.encode($("#pre_mianliao").text())
		
		liliao = $("#pre_liliao").text()
		if(liliao.endsWith("--------")){
			layer.msg('里料未确认', function(){});
			return;
		}
		liliao = Base64.encode($("#pre_liliao").text())
		
		kuanhao = $("#pre_kuanhao").text()
		if(kuanhao.endsWith("--------")){
			layer.msg('款号未确认', function(){});
			return;
		}
		kuanhao = Base64.encode($("#pre_kuanhao").text())
		
		// console.log(RegExp(/none/).test($("#price").css("border")))
		price = $("#price").val()
		if(!RegExp(/none/).test($("#price").css("border"))){ // 检测进价border是否不为none
			layer.msg('进价未确认', function(){});
			return;
		}
		price = Base64.encode($("#price").val())
		
		sell = $("#pre_sell").text()
		if(sell.endsWith("--------")){
			layer.msg('售价未确认', function(){});
			return;
		}
		sell = Base64.encode("零售价 : " + $("#pre_sell").text())
		
		layer.load(0); 
		barcode = Base64.encode($("#qrcode").text())
		name = Base64.encode("皮 裙")
		checker = Base64.encode("08")
		level = Base64.encode("合格品")
		intprice = Base64.encode($("#price").val())
		$.ajax({
			type: 'POST',
			url: url + "/edit/Doublecoat",
			data: {name:name,kuanhao:kuanhao,size:size,color:color,cplb:cplb,zxbz:zxbz,outfabric:mianliao,intfabric:liliao,
			checker:checker,level:level,barcode:barcode,sell:sell,intprice:intprice},
			xhrFields: {
				withCredentials: true
			},
			// crossDomain: true,
			dataType: "json",
			success: function(data){
				layer.closeAll();
				if(data.code == 200){
					// 添加成功
					console.log("添加成功")
				}else{
					// 添加失败
					console.log("添加失败")
					// layer.msg("<span style='color: white'>" + data.message + "</span>",function(){})
				}
			}
		});
	})
})