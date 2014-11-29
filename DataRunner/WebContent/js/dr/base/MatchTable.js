//define MatchTable module

define(
		["dojo/_base/declare",
		 "dr/base/MatchStatus",
		 "dr/logger"],
		function(declare,
				MatchStatus,
				logger) {
			return declare(null, {
				triggerMap:null, //{watch_value:msObj}
				
				/************* methods **************/
				getStatus:function(pTriggerField,newVal) {
					var matchArr = this.triggerMap[pTriggerField];
					for(var i = 0; i < matchArr.length; i++) {
						var statusObj = matchArr[i];
						//逐个状态 判断条件是否符合
						if (statusObj.isMatch(newVal)) {
							return statusObj.matchedStatus;
						}
					}
					
					logger.warn("[warning] 没有状态匹配成功[pTriggerField="
							+ pTriggerField + "newVal=" + newVal + "]");
					throw "no matching status";
				},
				
				constructor:function(pMatchTableJson) {
					/*
					 [
					 {"trigger_field":"watch_value",
						"matchstatus":{
								"status":"on",
								"v1array":["50"],
								"cmparray":["co"],
								"v2array":["Number.MAXVALUE"]}
							},
					{"trigger_field":"watch_value",
						"matchstatus":{
								"status":"off",
								"v1array":["Number.MINVALUE"],
								"cmparray":["oo"],
								"v2array":[50]}}
					]
					 */
					this.triggerMap = {};
					if (pMatchTableJson == undefined || pMatchTableJson == "") {
						return;
					}
					
					var matchObj = JSON.parse(pMatchTableJson);
					var length = matchObj.length;
					for(var i = 0; i < length; i++) {
						var tmpObj = matchObj[i];
						var statusArr = tmpObj.matchstatus;
						var matchStatusObj = new MatchStatus(statusArr);
						var triggerArr = this.triggerMap[tmpObj.trigger_field];
						if (triggerArr == null || triggerArr == undefined) {
							var matchStatusArr = new Array();
							this.triggerMap[tmpObj.trigger_field] = matchStatusArr;
						}
						
						this.triggerMap[tmpObj.trigger_field].push(matchStatusObj);
						
					}
					
				}
			});
		}
);