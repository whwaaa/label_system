$(function(){
    // 样式补丁
    $(".box").css("margin-top","52px")
    
	JsBarcode('#canvascode', $("#barcode").text(), {
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
	
	// $("#selectAll").prop("checked");
	// $("#username").attr("baseURL")
	var styleId = 15;	// 初始值: 1111
	$(".checkbox").on("click",function(){
		
		var liliao = $(".checkbox").eq(0).prop("checked")==true?1:0
		var filler = $(".checkbox").eq(1).prop("checked")==true?1:0
		var guide = $(".checkbox").eq(2).prop("checked")==true?1:0
		var size = $(".checkbox").eq(3).prop("checked")==true?0:1
		styleId = (size << 3) + (liliao << 2) + (filler << 1) + guide
		// console.log(styleId)
		// console.log("尺寸:" + size + " 里料:" + liliao + " 填充物:" + filler + " 指导价:" + guide) 
		//  尺码 里料 填充物 指导价		1111	15
		//  尺码 里料 填充物 -----		1110	14
		//  尺码 里料 ----- 指导价		1101	13
		//  尺码 里料 ----- -----		1100	12
		//  尺码 ----- 填充物 指导价		1011	**不合理**
		//  尺码 ----- 填充物 -----		1010	**不合理**
		//  尺码 ----- ----- 指导价		1001	9
		//  尺码 ----- ----- -----		1000	8
		//  ----- 里料 填充物 指导价		0111	7
		//  ----- 里料 填充物 -----		0110	6
		//  ----- 里料 ----- 指导价		0101	5
		//  ----- 里料 ----- -----		0100	4
		// ------ ----- 填充物 指导价	0011	**不合理**
		// ----- ----- 填充物 -----		0010	**不合理**
		// ----- ----- ----- 指导价		0001	1	
		// ----- ----- ----- -----		0000	0
		if(size == 1){
			$("#pre_size").text("--------")
		}else{
			$("#pre_size").text("均码")
		}
		if(liliao == 1){
			$("#show_liliao").removeClass("hidden")
		}else{
			$("#show_liliao").addClass("hidden")
		}
		if(filler == 1){
			$("#show_filler").removeClass("hidden")
		}else{
			$("#show_filler").addClass("hidden")
		}
		if(guide == 1){
			$("#show_guide").removeClass("hidden")
			$("#sellPriceName").text("工厂直销价:--------")
		}else{
			$("#show_guide").addClass("hidden")
			$("#sellPriceName").text("零售价:--------")
		}
	})
	
	
	// 监听提交按钮
	$("#submit").on("click",function(){
		if($("#typeName").val().trim()==''){
			layer.msg("请先输入有效品名", function(){})
			return;
		}
		layer.load(0); 
		var typeName = $("#typeName").val().trim()
		$.ajax({
			type: 'POST',
			url: url + "/template",
			data: {styleId:styleId,typeName:typeName},
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			dataType: "json",
			success: function(data){
				layer.closeAll();
				if(data.code == 200){
					// 添加成功
					layer.msg("添加成功")
				}else if(data.code == 500){
					// 添加失败
					layer.msg(data.msg,function(){})
				}
			},
			error: function(){
				layer.closeAll();
				layer.msg("服务器内部出错",function(){})
			}
		});
	})
	
	// 监听输入框
	$("#typeName").on('blur', function() {
		var val = $(this).val().replace(/\s/g,"");
		console.log(val)
		var newVal = "";
		for(var i=0; i<val.length; i++){
			newVal += val.substr(i,1)+" ";
		}
		newVal = newVal.trim()
		$(this).val(newVal)
	})
	
})