/**
 * 作品JS
 */
$(function(){
	artWorks.initial();
});

var artWorks = {
		
	//初始化
	initial : function(){
		this.mediaTool();
		this.artTitleFocus();
		this.artTitleBlur();
		this.artContentFocus();
		this.artContentBlur();
		this.listenerKeyEvent();
	},
	//作品-标题
	artTitleFocus : function(){
		$("#art-title").focus(function(){
			var title = $(this).text();
			if(title=="请输入标题"){
				$(this).text("");
			}
		});
	},
	artTitleBlur : function(){
		$("#art-title").blur(function(){
			var title = $(this).text();
			if($.trim(title)==""){
				$(this).text("请输入标题");
			}
		});
	},
	//作品-正文
	artContentFocus : function(){
		$(".art-content").focus(function(){
			var title = $(this).text();
			if(title=="请输入正文"){
				$(this).text("");
			}
		});
	},
	artContentBlur : function(){
		$(".art-content").blur(function(){
			var title = $(this).text();
			if($.trim(title)==""){
				$(this).text("请输入正文");
			}
		});
	},
	//右侧多媒体工具栏
	mediaTool : function(){
		$(".tool-file").change(function(){
			var $file = $(this);
	        var fileObj = $file[0];
	        var windowURL = window.URL || window.webkitURL;
	        var dataURL;
	        var fileType;
	        if(fileObj && fileObj.files && fileObj.files[0]){
		        dataURL = windowURL.createObjectURL(fileObj.files[0]);
		        fileType = fileObj.files[0].type;
		        var boxHtml = "";
		        if(fileType.indexOf("image/")>-1){
		        	boxHtml = "<div data-type='image'><img src='"+dataURL+"' style='width:100%;heigth:auto;'/></div>";
		        	artWorks.artBoxTextRemove();
			        $("#content-box").append(boxHtml);
			        artWorks.artBoxTextAdd();
		        }else if(fileType.indexOf("video/")>-1){
		        	boxHtml = "<div data-type='video'><video src='"+dataURL+"' controls='controls' style='width:100%;heigth:auto;' /></video></div>";
		        	artWorks.artBoxTextRemove();
			        $("#content-box").append(boxHtml);
			        artWorks.artBoxTextAdd();
		        }else if(fileType.indexOf("audio/")>-1){
		        	boxHtml ="<span>背景音乐："+fileObj.files[0].name+"</span>";
		        	$("#background-music").html(boxHtml);
		        }
	        }
	        $file.val("");
		});
	},
	//处理添加
	artBoxTextRemove : function(){
		var $box = $("#content-box").find("div:last");
		if($box.length>0){
			var dataType = $box.data("type");
			if(dataType=='text'){//文本
				if($box.text()=='' || $box.text()=='请输入正文'){
					$box.remove();
				}
			}
		}
		
	},
	artBoxTextAdd : function(){
		var boxHtml = "<div contentEditable='true' style='font-size: 14px;text-indent:2em;' class='art-content' data-type='text' ></div>";
		$("#content-box").append(boxHtml);
		$("#content-box").find("div:last").focus();
	},
	//监听键盘的BACKSPACE
	listenerKeyEvent : function(){
		$("#content-box").delegate(".art-content","keydown",function(event){
			var v = $(this).text();
			if (event.keyCode && event.keyCode === 8){
				var $pBox = $(this).prev();
				if(v.length==0 && $pBox.length>0){
					var type = $pBox.data("type");
					$(this).remove();
					if(type=="text"){
						artWorks.placeCaretAtEnd($pBox.prev());
					}else{
						$pBox.remove();
					}
				}
			} 
        });
	},
	//将光标定位到最后
	placeCaretAtEnd : function(el){
		el.focus();
	    if (typeof window.getSelection != "undefined" && typeof document.createRange != "undefined") {
	        var range = document.createRange();
	        range.selectNodeContents(el);
	        range.collapse(false);
	        var sel = window.getSelection();
	        sel.removeAllRanges();
	        sel.addRange(range);
	    } else if (typeof document.body.createTextRange != "undefined") {
	        var textRange = document.body.createTextRange();
	        textRange.moveToElementText(el);
	        textRange.collapse(false);
	        textRange.select();
	    }
	},
	//预览
}
