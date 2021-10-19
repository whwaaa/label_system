$(function(){
	
	var date = "210611";
	var zimu = "";
	
	// 更改进货日期
	function updateDate(){
		layer.open({
		    id:1,
		    type: 1,
		    title:'输入进货日期',
		    style: 'width:80%;height:auto;',
		    content: "<div><input type='date' style='margin: 10px 20px;' id='area' ></input></div>",
		    btn:['保存','取消'],
		    yes:function (index,layero) {
		         //获取输入框里面的值
		        var closeContent = top.$("#area").val() || $("#area").val();
		        if(closeContent){
		            console.log(closeContent);
					date = closeContent.substr(2,2) + closeContent.substr(5,2) + closeContent.substr(8,2)
					$(".date").text("进货日期:" + date)
		        }
		        layer.close(index);
		        // 在这里提交数据
		    },
		    no:function (index,layero) {
		         layer.close(index);
		    }
		});
	}
	// 监听更改进货日期
	$(".date").on("click",function(){
		updateDate();
	})
	
	// 查询历史记录
		// 查询历史记录
	setTimeout(function() {queryHistory()}, timeOut);
	var historyList
	function queryHistory(){
		$.ajax({
			type: 'GET',
			url: url + "/editLabel/label/history",
			data: {},
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			dataType: "json",
			success: function(data){
				if(data.code == 200){
					console.log(data.obj)
					// 处理结果
					historyList = data.obj
					$.each(historyList['zxbzList'], function(){
						console.log(this.toString())
					})
				}
			},
			error: function(){
				layer.closeAll();
				layer.msg("服务器内部出错",function(){})
			}
		})
	}
	
	// 查询创建过的模板
	setTimeout(function() {queryData()}, timeOut);
	function queryData(){
		$.ajax({
			type: 'GET',
			url: url + "/template",
			data: {},
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			dataType: "json",
			success: function(data){
				layer.closeAll();
				if(data.code == 200){
					// 遍历结果
					if(data.obj != null){
						$(data.obj).each(function(){
							// console.log($(this))
							// 渲染模板
							drawingShow($(this))
						});
						// 生成二维码
						createBarcode(0.5,20)
						// 监听标签点击
						lisen_showbox()
					}
				}else if(data.code == 400){
					// 查询失败
					layer.msg(data.msg,function(){})
				}
			},
			error: function(){
				layer.closeAll();
				layer.msg("服务器内部出错",function(){})
			}
		});
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

	// 渲染标签
	var num = 1
	function drawingShow(template){
		var size = (template[0].styleId & (1<<3))>0?true:false
		var liliao = (template[0].styleId & (1<<2))?true:false
		var filler = (template[0].styleId & (1<<1))?true:false
		var guide = (template[0].styleId & 1)?true:false
		// console.log(size + " " + liliao + " " + filler + " " + guide)
		num *= -1
		var con = "";
		if(num == -1){   
			con += "<div class=\"showbox col-xs-offset-1 col-xs-5 col-md-2\">\n";
		}else{
			con += "<div class=\"showbox col-xs-5 col-md-2\">\n";
		}
		con += "\t\t\t\t\t<p class=\"p_typeName\" style=\"text-align: center;\">"+template[0].typeName+"</p>\n" +
				"<input class=\"hidden_styleId\" type=\"hidden\" value=\""+template[0].styleId+"\">" + 
				"<input class=\"templateId\" type=\"hidden\" value=\""+template[0].templateId+"\">" + 
				"\t\t\t\t\t<div class=\"show_box\">\n" +
				"\t\t\t\t\t\t<div class=\"label_title\" style=\"margin: 6px auto 6px auto; font-size: 1.6rem;text-align: center;\">合格证</div>\n" +
				"\t\t\t\t\t\t<div class=\"preview\" >\n" +
				"\t\t\t\t\t\t\t<div class=\"preview_title\">品牌:</div>\n" +
				"\t\t\t\t\t\t\t<div class=\"preview_content\">彩绸</div>\n" +
				"\t\t\t\t\t\t</div>\n" +
				"\t\t\t\t\t\t<div class=\"preview\" >\n" +
				"\t\t\t\t\t\t\t<div class=\"preview_title\">品名:</div>\n" +
				"\t\t\t\t\t\t\t<div class=\"pre_typeName preview_content\">"+template[0].typeName+"</div>\n" +
				"\t\t\t\t\t\t</div>\n" +
				"\t\t\t\t\t\t<div class=\"preview\" > \n" +
				"\t\t\t\t\t\t\t<div class=\"preview_title\">款号:</div>\n" +
				"\t\t\t\t\t\t\t<div class=\"pre_kuanhao preview_content\" >--------</div>";
		if(size==true){
			con += "<div class=\"preview_title size visibility\" style=\"margin-left:10px;\">尺码:</div>\n" +
					"\t\t\t\t\t\t\t\t<div class=\"pre_size preview_content size visibility\">--------</div>";
		}
		con += "</div>\n" +
				"\t\t\t\t\t\t<div class=\"preview\" >\n" +
				"\t\t\t\t\t\t\t<div class=\"preview_title\">颜色:</div>\n" +
				"\t\t\t\t\t\t\t<div class=\"pre_color preview_content\" >--------</div>\n" +
				"\t\t\t\t\t\t</div>\n" +
				"\t\t\t\t\t\t<div class=\"preview\" >\n" +
				"\t\t\t\t\t\t\t<div class=\"preview_title\">执行标准:</div>\n" +
				"\t\t\t\t\t\t\t<div class=\"pre_zxbz preview_content select_max_width_M\" >----------------</div>\n" +
				"\t\t\t\t\t\t</div>\n" +
				"\t\t\t\t\t\t<div class=\"preview\" >\n" +
				"\t\t\t\t\t\t\t<div class=\"preview_title\">产品类别:</div>\n" +
				"\t\t\t\t\t\t\t<div class=\"pre_cplb preview_content select_max_width_M\">----------------</div>\n" +
				"\t\t\t\t\t\t</div>\n" +
				"\t\t\t\t\t\t<div class=\"preview\" >\n" +
				"\t\t\t\t\t\t\t<div class=\"preview_title\">面料:</div>\n" +
				"\t\t\t\t\t\t\t<div class=\"pre_mianliao preview_content select_max_width_L\">--------</div>\n" +//33.8%聚酯纤维 33.2%腈纶 33%氨纶
				"\t\t\t\t\t\t</div>"
		if(liliao==true){
			con += "<div class=\"preview\" >\n" +
					"\t\t\t\t\t\t\t\t<div class=\"preview_title\">里料:</div>\n" +
					"\t\t\t\t\t\t\t\t<div class=\"preview_content pre_liliao select_max_width_L\">--------</div>\n" +
					"\t\t\t\t\t\t\t</div>"
		}
		if(filler==true){
			con += "<div class=\"preview\" >\n" +
					"\t\t\t\t\t\t\t<div class=\"preview_title\">填充物:</div>\n" +
					"\t\t\t\t\t\t\t<div class=\"pre_filler preview_content\">--------</div>\n" +
					"\t\t\t\t\t\t</div>"
		}
		if(guide==true){
			con += "<div class=\"preview\" >\n" +
					"\t\t\t\t\t\t\t<div class=\"preview_title\">检验员:</div>\n" +
					"\t\t\t\t\t\t\t<div class=\"pre_checker preview_content\">08</div>\n" +
					"\t\t\t\t\t\t</div>\n" +
					"\t\t\t\t\t\t<div class=\"preview\" >\n" +
					"\t\t\t\t\t\t\t<div class=\"preview_title\">等级:</div>\n" +
					"\t\t\t\t\t\t\t<div class=\"pre_level preview_content\">合格品</div>\n" +
					"\t\t\t\t\t\t</div>\n" +
					"\t\t\t\t\t\t<div class=\"\">\n" +
					"\t\t\t\t\t\t\t<div class=\"preview\" >\n" +
					"\t\t\t\t\t\t\t\t<div class=\"preview_title\">统一零售价:</div>\n" +
					"\t\t\t\t\t\t\t\t<div class=\"pre_guide preview_content\">--------</div>\n" +
					"\t\t\t\t\t\t\t</div>\n" +
					"\t\t\t\t\t\t</div>\n" +
					"\t\t\t\t\t\t<p style=\"margin:0 0 0 10%; font-size: 0.7rem;\">(此条码仅供内部使用)</p>\n" +
					"\t\t\t\t\t\t<canvas class=\"canvascode\"></canvas>\n" +
					"\t\t\t\t\t\t<div class=\"per_barcode\" style=\"margin:0 0 0 10%;\">----------------</div>\n" +
					"\t\t\t\t\t\t\n" +
					"\t\t\t\t\t\t<div style=\"margin: 2px auto 6px auto; font-size: 1.5rem; text-align: center;\">\n" +
					"\t\t\t\t\t\t\t<div class=\"pre_sell\">工厂直销价 : --------</div>\n" +
					"\t\t\t\t\t\t</div>\n" +
					"\t\t\t\t\t</div>\n" +
					"\t\t\t\t</div>"
		}else{
			con += "\t\t\t\t\t\t<div class=\"preview\" >\n" +
					"\t\t\t\t\t\t\t<div class=\"preview_title\">检验员:</div>\n" +
					"\t\t\t\t\t\t\t<div class=\"pre_checker preview_content\">08</div>\n" +
					"\t\t\t\t\t\t</div>\n" +
					"\t\t\t\t\t\t<div class=\"preview\" >\n" +
					"\t\t\t\t\t\t\t<div class=\"preview_title\">等级:</div>\n" +
					"\t\t\t\t\t\t\t<div class=\"pre_level preview_content\">合格品</div>\n" +
					"\t\t\t\t\t\t</div>\n" +
					"\t\t\t\t\t\t<p style=\"margin:0 0 0 10%; font-size: 0.7rem;\">(此条码仅供内部使用)</p>\n" +
					"\t\t\t\t\t\t<canvas class=\"canvascode\"></canvas>\n" +
					"\t\t\t\t\t\t<div class=\"per_barcode\" style=\"margin:0 0 0 10%;\">----------------</div>\n" +
					"\t\t\t\t\t\t\n" +
					"\t\t\t\t\t\t<div class=\"sell_one_price\" style=\"margin: 2px auto 6px auto; font-size: 1.5rem; text-align: center;\">\n" +
					"\t\t\t\t\t\t\t<div class=\"pre_sell\">零售价 : --------</div>\n" +
					"\t\t\t\t\t\t</div>\n" +
					"\t\t\t\t\t</div>\n" +
					"\t\t\t\t</div>"
		}
		$("#content_box").append($(con))
	}
	
	
	// 获取汉字拼音字母
	function getZimu(nameType){
		$.ajax({
			type: 'GET',
			url: url + "/editLabel/font",
			data: {nameType:nameType},
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			dataType: "json",
			success: function(data){
				if(data.code == 200){
					// 遍历结果
					zimu = data.obj.toUpperCase()
				}
			},
			error: function(){
				layer.msg("服务器内部出错,请联系管理员",function(){})
			}
		});
	}
		
	// 监听标签选择
	var hidden_styleId;
	var size_option;
	var liliao_option;
	var filler_option;
	var guide_option;
	function lisen_showbox(){
		$(".showbox").on("click", function(){
			hidden_styleId = $(this).find(".hidden_styleId").val()
			size_option = (hidden_styleId & (1<<3))>0?true:false
			liliao_option = (hidden_styleId & (1<<2))?true:false
			filler_option = (hidden_styleId & (1<<1))?true:false
			guide_option = (hidden_styleId & 1)?true:false
			// var typeName = $(this).find(".p_typeName").text()
			// console.log($(this))
			// 获取品名首字母
			getZimu($(this).find(".pre_typeName").text())
			var $_this = $(this)
			$_this.removeClass("col-xs-offset-1  col-md-2 col-xs-5 col-md-2")
			$_this.addClass("col-xs-offset-1 col-xs-10 col-md-3")
			$_this.find(".label_title").css({"font-size": "2rem"})	
			$_this.find(".sell_one_price").css({"font-size": "2rem"})
			$(this).find(".p_typeName").remove()
			$(".showbox").prepend("<h4 style=\"text-align:center;\">预览区</h4><hr>")
			$(".preview").addClass("preview_add")
			$(".preview_title").addClass("preview_title_add")
			$(".preview_content").addClass("preview_content_add")
			$(".canvascode").addClass("canvascode_add")
			$(".showbox").addClass("showbox_add")
			$(".showbox").find(".pre_size").text("M")
			$(".showbox").find(".select_max_width_M").removeClass("max_width_M")
			$(".showbox").find(".select_max_width_M").addClass("max_width_M_add")
			$(".showbox").find(".select_max_width_L").removeClass("max_width_L")
			$(".showbox").find(".select_max_width_L").addClass("max_width_L_add")
			
			$("#content_box").empty()
			var edit_box = "<div class=\"col-xs-offset-1 col-xs-10 col-md-4\">\n" +
							"<h4 style=\"text-align:center;\">编辑区</h4><hr>" +
							"<div class=\"edit_box\">\n" +
							"\t\t\t\t\t<div class=\"oneinput\">\n" +
							"\t\t\t\t\t\t<label for=\"zxbz\">执行标准</label>\n" +
							"\t\t\t\t\t\t<div class='input-box'><input class=\"edit_input\" type=\"text\" id=\"zxbz\" placeholder=\"请输入执行标准\"><br></div>\n" +							
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t<div class=\"oneinput\">\n" +
							"\t\t\t\t\t\t<label for=\"cplb\" >产品类别</label>\n" +
							"\t\t\t\t\t\t<div class='input-box'><input class=\"edit_input\" type=\"text\" id=\"cplb\" placeholder=\"请输入产品类别\"><br></div>\n" +
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t<div class=\"oneinput\">\n" +
							"\t\t\t\t\t\t<label for=\"mianliao\" >面&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;料</label>\n" +
							"\t\t\t\t\t\t<div class='input-box'><input class=\"edit_input\" type=\"text\" id=\"mianliao\" placeholder=\"请输入面料\"><br></div>\n" +
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t<div class=\"liliao_option oneinput\">\n" +
							"\t\t\t\t\t\t<label for=\"liliao\" >里&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;料</label>\n" +
							"\t\t\t\t\t\t<div class='input-box'><input class=\"edit_input\" type=\"text\" id=\"liliao\" placeholder=\"请输入里料\"><br></div>\n" +
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t<div class=\"filler_option oneinput\">\n" +
							"\t\t\t\t\t\t<label for=\"filler\" >填&nbsp;充&nbsp;&nbsp;物</label>\n" +
							"\t\t\t\t\t\t<div class='input-box'><input class=\"edit_input\" type=\"text\" id=\"filler\" placeholder=\"请输入填充物\"><br></div>\n" +
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t<div class=\"oneinput\">\n" +
							"\t\t\t\t\t\t<label for=\"kuanhao\" >款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>\n" +
							"\t\t\t\t\t\t<div class='input-box'><input class=\"edit_input\" type=\"text\" id=\"kuanhao\" placeholder=\"请输入款号\"><br></div>\n" +
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t<div class=\"oneinput\">\n" +
							"\t\t\t\t\t\t<label for=\"buy_price\" >进&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价</label>\n" +
							"\t\t\t\t\t\t<div class='input-box'><input class=\"edit_input\" type=\"text\" id=\"buy_price\" placeholder=\"请输入进价\"><br></div>\n" +
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t<div class=\"oneinput\">\n" +
							"\t\t\t\t\t\t<label for=\"sell\" >售&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价</label>\n" +
							"\t\t\t\t\t\t<div class='input-box'><input class=\"edit_input\" type=\"text\" id=\"sell\" placeholder=\"请输入售价\"><br></div>\n" +
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t<div class=\"guide_option oneinput\">\n" +
							"\t\t\t\t\t\t<label for=\"guide\" >指&nbsp;导&nbsp;&nbsp;价</label>\n" +
							"\t\t\t\t\t\t<div class='input-box'><input class=\"edit_input\" type=\"text\" id=\"guide\" placeholder=\"请输入指导价\"><br></div>\n" +
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t<div class=\"oneinput\">\n" +
							"\t\t\t\t\t\t<label for=\"color\" >颜&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色</label>\n" +
							"\t\t\t\t\t\t<div class='input-box'><input class=\"edit_input\" type=\"text\" id=\"color\" placeholder=\"请输入颜色\"><br></div>\n" +
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t<div class=\"size_option oneinput\">\n" +
							"\t\t\t\t\t\t<label for=\"size\" style=\"float: left;\">尺&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>\n" +
							"\t\t\t\t\t\t<div class=\"size_check\">\n" +
							"\t\t\t\t\t\t\t<label class=\"my-checkbox\" style=\"margin-left: 0px;\">\n" +
							"\t\t\t\t\t\t\t\t<input class=\"checkbox\" type=\"checkbox\" name=\"option\" id=\"S\" value=\"S\"><label for=\"S\">S</label>\n" +
							"\t\t\t\t\t\t\t</label>\n" +
							"\t\t\t\t\t\t\t<label class=\"my-checkbox\">\n" +
							"\t\t\t\t\t\t\t\t<input class=\"checkbox\" checked=\"true\" type=\"checkbox\" name=\"option\" id=\"M\" value=\"M\"><label for=\"M\">M</label>\n" +
							"\t\t\t\t\t\t\t</label> \n" +
							"\t\t\t\t\t\t\t<label class=\"my-checkbox\">\n" +
							"\t\t\t\t\t\t\t\t<input class=\"checkbox\" checked=\"true\" type=\"checkbox\" name=\"option\" id=\"L\" value=\"L\"><label for=\"L\">L</label>\n" +
							"\t\t\t\t\t\t\t</label> \n" +
							"\t\t\t\t\t\t\t<label class=\"my-checkbox\" style=\"margin-left: 0px;\">\n" +
							"\t\t\t\t\t\t\t\t<input class=\"checkbox\" checked=\"true\" type=\"checkbox\" name=\"option\" id=\"XL\" value=\"XL\"><label for=\"XL\">XL</label>\n" +
							"\t\t\t\t\t\t\t</label> \n" +
							"\t\t\t\t\t\t\t<label class=\"my-checkbox\">\n" +
							"\t\t\t\t\t\t\t\t<input class=\"checkbox\" type=\"checkbox\" name=\"option\" id=\"_2XL\" value=\"2XL\"><label for=\"_2XL\">2XL</label>\n" +
							"\t\t\t\t\t\t\t</label> \n" +
							"\t\t\t\t\t\t\t<label class=\"my-checkbox\">\n" +
							"\t\t\t\t\t\t\t\t<input class=\"checkbox\" type=\"checkbox\" name=\"option\" id=\"_3XL\" value=\"3XL\"><label for=\"_3XL\">3XL</label>\n" +
							"\t\t\t\t\t\t\t</label> \n" +
							"\t\t\t\t\t\t\t<label class=\"my-checkbox\" style=\"margin-left: 0px;\">\n" +
							"\t\t\t\t\t\t\t\t<input class=\"checkbox\" type=\"checkbox\" name=\"option\" id=\"_4XL\" value=\"4XL\"><label for=\"_4XL\">4XL</label>\n" +
							"\t\t\t\t\t\t\t</label> \n" +
							"\t\t\t\t\t\t\t<label class=\"my-checkbox\">\n" +
							"\t\t\t\t\t\t\t\t<input class=\"checkbox\" type=\"checkbox\" name=\"option\" id=\"_5XL\" value=\"5XL\"><label for=\"_5XL\">5XL</label>\n" +
							"\t\t\t\t\t\t\t</label> \n" +
							"\t\t\t\t\t\t\t<label class=\"my-checkbox\">\n" +
							"\t\t\t\t\t\t\t\t<input class=\"checkbox\" type=\"checkbox\" name=\"option\" id=\"_6XL\" value=\"6XL\"><label for=\"_6XL\">6XL</label>\n" +
							"\t\t\t\t\t\t\t</label>\n" +
							"\t\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t\t<br>\n" +
							"\t\t\t\t\t<div class=\"mybuttons\">\n" +
							"\t\t\t\t\t\t<button type=\"button\" class=\"save btn btn-default\" >保存</button>\n" +
							"\t\t\t\t\t\t<button type=\"button\" class=\"cancel btn btn-default\" >取消</button>\n" +
							"\t\t\t\t\t</div>\n" +
							"\t\t\t\t</div><div class='delet-but-box'><button type=\"button\" class=\"delet-btn btn btn-default\" >删除模板</button></div></div>"
			var $_edit_box = $(edit_box)	
			
			// 加载历史选择数据
			historyDataUtil($_edit_box,"zxbz","zxbzList")
			historyDataUtil($_edit_box,"cplb","cplbList")
			historyDataUtil($_edit_box,"mianliao","mianliaoList")
			historyDataUtil($_edit_box,"liliao","liliaoList")
			historyDataUtil($_edit_box,"filler","fillerList")
			historyDataUtil($_edit_box,"kuanhao","kuanHaoList")
			historyDataUtil($_edit_box,"buy_price","buyPriceList")
			historyDataUtil($_edit_box,"sell","sellPriceList")
			historyDataUtil($_edit_box,"guide","guidePriceList")
			historyDataUtil($_edit_box,"color","colorList")
			
			// 根据模板选项移除多余选项
			if(!size_option){
				$_edit_box.find(".size_option").remove()
				$_edit_box.find(".mybuttons").css({"margin-top":"20px"})
			}
			if(!filler_option)
				$_edit_box.find(".filler_option").remove()
			if(!liliao_option)
				$_edit_box.find(".liliao_option").remove()
			if(!guide_option)
				$_edit_box.find(".guide_option").remove()
				
			$("#content_box").append($_edit_box)
			$("#content_box").append($("<div class=\"col-xs-offset-1 col-xs-10 col-md-4 visible-xs\" style=\"margin-top:100px;\"></div>"))
			$("#content_box").append($_this)
			
			
			
			// 注册监听编辑,同步生成预览
			bindEdit()
			
			// 注册监听保存和取消按钮
			lisenSaveAndCancelBut()
			
			// 监听删除模板
			deleteTempLate()
			
			// 监听input点击后下拉
			inpuptDown()
		})
	}
	
	// 加载历史选择数据
	function historyDataUtil($_edit_box,id,indexName){
		var con = "<ul class='input-box-ul'>"
		$.each(historyList[indexName], function(){
			con += "<li class='input-box-down'><p>"+this.toString()+"</p></li>"
		})
		con += "</ul>"
		$_edit_box.find("#"+id).parents(".input-box").append($(con))
	}
	
	
	// 监听input点击后下拉
	function inpuptDown(){
		$(".input-box").on("click",function(){
			var $sub = $(this).children(".input-box-ul")
			// 2.1下拉二级菜单
			if($sub.css("display").endsWith('none')){
				// 停止当前动画
				$sub.stop();
				$(".input-box-ul").slideUp(1)
				$sub.slideDown(1)
			}else{
				// 2.2收缩二级菜单
				// 停止当前动画
				$sub.stop();
				$sub.slideUp(1);
			}
		})
		$(".input-box-down").on("click",function(){
			$(this).parents(".input-box").find(".edit_input").val($(this).text())
		})
	}
	

	// 监听编辑,同步生成预览
	function bindEdit(){
		$(".edit_input").bind('input propertychange click', function() {
			
			// 颜色文字自动空格
			// if($(this).attr('id').endsWith())
			
			var id = 'pre_' + $(this).attr('id')
			// console.log("val: " + $(this).val() + "   id: " + id)
			// 计算条码
			var barcode = "";
			if(id.endsWith("price") || id.endsWith("kuanhao")){
				var price = "", kuanhao = "", temp = "", qrcode = ""
				if(id.endsWith("price")){
					price = $(this).val()
					kuanhao = $(".pre_kuanhao").text()
					$(this).css("border-color", "#ccc");
				}else{ // id.endsWith("kuanhao")
					price = $("#buy_price").val()
					kuanhao = $(this).val()
					$('.'+ id).text(date.substr(0,2) + zimu + $(this).val())
					$(this).css("border-color", "#ccc");
				}
				if(price.length == 3){
					temp = price.substr(-1,1)
					temp += price.substr(-2,1)
					temp += price.substr(-3,1)
					barcode = date + '0' + temp + '0' + kuanhao.substr(-3,3)
				}else if(price.length == 4){
					temp = price.substr(-1,1)
					temp += price.substr(-2,1)
					temp += price.substr(-3,1)
					temp += price.substr(-4,1)
					barcode = date + '0' + temp + '0' + kuanhao.substr(-2,2)
				}
				if(barcode != "")
					$(".per_barcode").text(barcode);
				createBarcode(1,40);
			}else{
				
				// 非计算条码,填充预览视图
				if(id.endsWith("sell")){
					if($("#guide").length > 0){// 有指导价
						$('.'+ id).text("工厂直销价 : "+$(this).val()+".00")
					}else{	// 无指导价
						$('.'+ id).text(" 零售价 : "+$(this).val()+".00")
					}
				}else if(id.endsWith("guide")){
					$('.' + id).text($(this).val() + ".00");
				}else{
					$('.'+ id).text($(this).val())
				}
				$(this).css("border-color", "#ccc");
			}
		})
		// 监听尺码按钮
		editSize()
	}
	
	// 监听尺码复选框
	var size = "M,L,XL";
	function editSize(){
		$(".checkbox").on("click", function(){
			var sizeArr = new Array(9);
			var arrName = new Array("S","M","L","XL","2XL","3XL","4XL","5XL","6XL")
			// 尺码填充(预览最小号)
			size = "";
			for(var i=0; i<9; i++){
				sizeArr[i] = $(".checkbox").eq(i).prop("checked")
				// console.log(i + ":" + sizeArr[i])
				if(sizeArr[i])
					size += arrName[i] + ","
			}
			for(var i=0; i<9; i++){
				sizeArr[i] = $(".checkbox").eq(i).prop("checked")
				if(sizeArr[i] != false){
					$(".pre_size").text(arrName[i])
					return;
					// switch(i){
					// 	case 0:
					// 		$(".pre_size").text("S")
					// 		break;
					// 	case 1:
					// 		$(".pre_size").text("M")
					// 		break;
					// 	case 2:
					// 		$(".pre_size").text("L")
					// 		break;
					// 	case 3:
					// 		$(".pre_size").text("XL")
					// 		break;
					// 	case 4:
					// 		$(".pre_size").text("2XL")
					// 		break;
					// 	case 5:
					// 		$(".pre_size").text("3XL")
					// 		break;
					// 	case 6:
					// 		$(".pre_size").text("4XL")
					// 		break;
					// 	case 7:
					// 		$(".pre_size").text("5XL")
					// 		break;
					// 	case 8:
					// 		$(".pre_size").text("6XL")
					// 		break;
					// }
					
				}
			}
			$(".pre_size").text("-----")
		})
	}
	
	// 监听保存和取消按钮
	function lisenSaveAndCancelBut(){
		$(".save").on("click",function(){
			// 检测是否确认
			var warningColor = 'rgb(255, 0, 0)'
			if($("#zxbz").css("border-color") == warningColor){
				layer.msg("请确认执行标准",function(){})
				return;
			}
			if($("#cplb").css("border-color") == warningColor){
				layer.msg("请确认产品类别",function(){})
				return;
			}
			if($("#mianliao").css("border-color") == warningColor){
				layer.msg("请确认面料",function(){})
				return;
			}
			if($("#liliao").css("border-color") == warningColor){
				layer.msg("请确认里料",function(){})
				return;
			}
			if($("#filler").css("border-color") == warningColor){
				layer.msg("请确认填充物",function(){})
				return;
			}
			if($("#kuanhao").css("border-color") == warningColor){
				layer.msg("请确认款号",function(){})
				return;
			}
			if($("#buy_price").css("border-color") == warningColor){
				layer.msg("请确认进价",function(){})
				return;
			}
			if($("#sell").css("border-color") == warningColor){
				layer.msg("请确认售价",function(){})
				return;
			}
			if($("#guide").css("border-color") == warningColor){
				layer.msg("请确认指导价",function(){})
				return;
			}
			if($("#color").css("border-color") == warningColor){
				layer.msg("请确认颜色",function(){})
				return;
			}
			if(size == ""){
				layer.msg("请确认尺码",function(){})
				return;
			}
			
			// 整理待发送数据
			var typeName = $(".pre_typeName").text()
			var kuanHao = $(".pre_kuanhao").text()
			if(!size_option)
				size = ""
			var color = $(".pre_color").text()
			var zxbz = $(".pre_zxbz").text()
			var cplb = $(".pre_cplb").text()
			var outFabric = $(".pre_mianliao").text()
			var intFabric;
			if(!liliao_option){
				intFabric = ""
			}else{
				intFabric = $(".pre_liliao").text()
			}
			var filler;
			if(!filler_option){
				filler = ""
			}else{
				filler = $(".pre_filler").text()
			}
			var checker = $(".pre_checker").text()
			var level = $(".pre_level").text()
			var barCode = $(".per_barcode").text() 
			var guidePrice;
			if(!guide_option){
				guidePrice = ""
			}else{
				guidePrice = Math.floor($(".pre_guide").text())
			}
			var sellPrice = Math.floor($(".pre_sell").text().substr($(".pre_sell").text().indexOf(':')+2))
			var buyPrice = $("#buy_price").val()
			
			// 发送数据
			$.ajax({
				type: 'POST',
				url: url + "/editLabel/label",
				data: {typeName:typeName,kuanHao:kuanHao,size:size,color:color,cplb:cplb,
				zxbz:zxbz,outFabric:outFabric,intFabric:intFabric,filler:filler,
				checker:checker,level:level,barCode:barCode,guidePrice:guidePrice,
				sellPrice:sellPrice,buyPrice:buyPrice,templateId:$(".hidden_styleId").val()},
				xhrFields: {
					withCredentials: true
				},
				crossDomain: true,
				dataType: "json",
				success: function(data){
					layer.closeAll();
					if(data.code == 200){
						// 添加成功
						layer.msg(data.msg)
						// 复位数据
						reset(data.obj);
					}else{
						// 添加失败
						console.log(data.msg,function(){})
						// layer.msg("<span style='color: white'>" + data.message + "</span>",function(){})
					}
				}
			});
		})
		$(".cancel").on("click",function(){

		}) 
	}
	
	
	// 重置或保存成功后
	function reset(label){
		$(".edit_input").css("border-color", "#ff0000");
		if(label != undefined || label != null){
			$("#zxbz").text(label.zxbz)
			$("#cplb").text(label.zxbz)
			$("#mianliao").text(label.zxbz)
			if(liliao_option)
				$("#liliao").text(label.zxbz)
			if(!filler_option)
				$("#filler").text(label.zxbz)
			$("#kuanhao").text(label.zxbz)
			$("#buy_price").text(label.zxbz)
			$("#sell").text(label.zxbz)
			if(!guide_option)
				$("#guide").text(label.zxbz)
			$("#color").text(label.zxbz)
			// if(size_option)
			
		}
	}
	 
	 
	// 监听删除模板
	function deleteTempLate(){
		$(".delet-btn").on("click", function(){
			layer.confirm("确认删除模板?",{
				btn: ['确认', '取消']
			}, function () {
				templateId = $(".showbox").find(".templateId").val()
				layer.closeAll()
				layer.load(0)
				$.ajax({
					type: 'POST',
					url: url + "/template/delete",
					data: {tempLateId:templateId},
					xhrFields: {
						withCredentials: true
					},
					crossDomain: true,
					dataType: "json",
					success: function(data){
						layer.closeAll()
						if(data.code == 200){
							// 遍历结果
							layer.msg(data.msg)
							location.reload()
						}
					},
					error: function(){
						layer.msg("服务器内部出错,请联系管理员",function(){})
					}
				});
			})
		})
	}
})