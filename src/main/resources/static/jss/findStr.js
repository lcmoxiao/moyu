;function strDispose(str){
			var pattern = '([@])([0-9]+)';
			var res = str.match(pattern);
			if(res!=null)
			{
				var sid = res[2];
				var old = res[0];
				var newS = '<a href="javacript:void(0);" onmouseover="onStrHover('+sid+')">' + old + '</a>';
				return str.replace(old,newS);
			} else {
				return str;
			}
		}
		
		function onStrHover(sid){
			layui.use('layer', function(){
			    var layer = layui.layer;
			  
				$.ajax({
					url : 'http://192.168.110.119/str/' + sid,
					method : 'get',
					success : function(res){
						var infoType = res['infoType'];
						var tmp = $("#tmpStr");
						var c;
						console.log(res);
						tmp.css('visibility','visible');
						if(infoType===0){
							c = res.bs[0];
							
							$("#publishname").html(c.bpublishname);
							$("#publishtime").html(c.bpublishtime);
							$("#great").html(c.bgreat);
							$("#ip").html(c.bpubliship);
							$("#sid").html(c.bid);
							$("#content").html(c.bcontent);
							layer.open({
								title:'串预览',
								type: 1,
								content: tmp,
								cancel: function(index, layero){ 
								    layer.close(index);
									tmp.css('visibility','hidden');
								}   
							}); 
						}else if(infoType===1){
							c = res.ps[0];
							$("#publishname").html(c.ppublishname);
							$("#publishtime").html(new Date(c.ppublishtime).Format("yyyy-M-d h:m:s"));
							$("#great").html(c.pgreat);
							$("#ip").html(c.ppubliship);
							$("#sid").html(c.pid);
							let img = '<img width=270 src="http://192.168.110.119/file/photo/'+ c.pcontent +'"></img>'
							$("#content").html(img);
							layer.open({
								title:'串预览',
								type: 1,
								content: tmp,
								cancel: function(index, layero){ 
								    layer.close(index);
									tmp.css('visibility','hidden');
								}   
							}); 
						}else if(infoType==2){
							c = res.ms[0];
							$("#publishname").html(c.mpublishname);
							$("#publishtime").html(new Date(c.mpublishtime).Format("yyyy-M-d h:m:s"));
							$("#great").html(c.mgreat);
							$("#ip").html(c.mpubliship);
							$("#sid").html(c.mid);
							if(c.mfatherid == c.mid){
								let img = '<img width=270 src="http://192.168.110.119/file/movie/'+ c.mdesc +'"></img>'
								$("#content").html(img);
							} else {
								$("#content").html(c.mcontent);
							}
							
							
							layer.open({
								title:'串预览',
								type: 1,
								content: tmp,
								cancel: function(index, layero){ 
								    layer.close(index);
									tmp.css('visibility','hidden');
								}   
							}); 
						}
					}
				})
			
			});  
		}
		
		Date.prototype.Format = function(fmt)   
		{ //author: meizz   
		  var o = {   
		    "M+" : this.getMonth()+1,                 //月份   
		    "d+" : this.getDate(),                    //日   
		    "h+" : this.getHours(),                   //小时   
		    "m+" : this.getMinutes(),                 //分   
		    "s+" : this.getSeconds(),                 //秒   
		    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
		    "S"  : this.getMilliseconds()             //毫秒   
		  };   
		  if(/(y+)/.test(fmt))   
		    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		  for(var k in o)   
		    if(new RegExp("("+ k +")").test(fmt))   
		  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		  return fmt;  
		}