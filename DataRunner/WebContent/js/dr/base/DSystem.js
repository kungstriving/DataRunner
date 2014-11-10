//define NSystem module is like utils
define(
		["dojo/request",
		 "dr/base/common"],
		function(request, common) {
			return {
				showSysInfo:function() {
					alert('sysinfo');
				},
				openPage:function(pageName) {
					console.log("openPage -- " + pageName);
					var requestURL = "drd";
					window.location.href = requestURL + "?action=loadPage&pageName=" + pageName;
				},
				writeValue:function(tagFullName, pField, pValue) {
					//发送控制命令请求
					var controlCmd = {};
					controlCmd.name=tagFullName;
					controlCmd.field = pField;
					controlCmd.value = pValue;
					var cmdJson = JSON.stringify(controlCmd);
					
					//post the request
					var requestURL = "drd";
					console.log("send control command : " + cmdJson);
					request.post(requestURL, {
						data:{
							"action":"control",
							"cmds":cmdJson
						},
						handleAs:"json"
					}).then(
							function(response) {
								console.log(response);
							},
							function(error) {
								console.log(error);
							}
					);
				}
			};
		}
);