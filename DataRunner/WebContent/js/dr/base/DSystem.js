//define NSystem module is like utils
define(
		["dojo/request",
		 "dr/base/common",
		 "dr/logger"],
		function(request, common,
				logger) {
			return {
				showSysInfo:function() {
					alert('sysinfo');
				},
				openPage:function(pageName) {
					logger.info("openPage -- " + pageName);
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
					logger.info("send control command : " + cmdJson);
					request.post(requestURL, {
						data:{
							"action":"control",
							"cmds":cmdJson
						},
						handleAs:"json"
					}).then(
							function(response) {
								logger.info(response);
							},
							function(error) {
								logger.info(error);
							}
					);
				}
			};
		}
);