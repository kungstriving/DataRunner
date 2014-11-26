//define ArrMatchTable module
//功能与MatchTable基本一致 但是MatchStatus以数组表示

define(
		["dojo/_base/declare",
		 "dr/base/MatchStatus"],
		function(declare,
				MatchStatus) {
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
					
					console.log("[warning] 没有状态匹配成功[pTriggerField="
							+ pTriggerField + "newVal=" + newVal + "]");
					throw "no matching status";
				},
				
				constructor:function(pArrMatchTableJson) {
					/*
					 [{"trigger_field":"light_color",
				"matchstatus":{"statusarr":["red","yellow"],
								"v1array":[-9999999999,50],
								"cmparray":["oc","oc"],
								"v2array":[50,9999999999]}},
				{"trigger_field":"light_bright",
				"matchstatus":{"statusarr":["1","2","3","4","5"],
								"v1array":[-9999999999,10,20,30,40],
								"cmparray":["oc","oc","oc","oc","oc"],
								"v2array":[10,20,30,40,9999999999]}},
				{"trigger_field":"power",
				"matchstatus":{"statusarr":["on","off","on"],
								"v1array":[-9999999999,50,100],
								"cmparray":["oc","oc"],
								"v2array":[50,100,9999999999]}}]
					 */
					this.triggerMap = {};
					if (pArrMatchTableJson == undefined || pArrMatchTableJson == "") {
						return;
					}
					
					var matchObj = JSON.parse(pArrMatchTableJson);
					var length = matchObj.length;
					for(var i = 0; i < length; i++) {
						var tmpObj = matchObj[i];
						var statusObj = tmpObj.matchstatus;	//matchstatus 对象
						var statusObjOri = {};	//将一个状态的多个条件组合
						var statusTmpArr = [];
						var statusArr = statusObj.statusarr;
						for (var j = 0; j < statusArr.length; j++) {
							var statusStr = statusArr[j];
							if (statusObjOri[statusStr] == undefined || statusObjOri[statusStr] == null) {
								var tempObj = {};
								tempObj.status = statusStr;
								tempObj.v1array = [statusObj.v1array[j]];
								tempObj.cmparray = [statusObj.cmparray[j]];
								tempObj.v2array = [statusObj.v2array[j]];
								statusObjOri[statusStr] = tempObj;
								statusTmpArr.push(statusStr);
							} else {
								statusObjOri[statusStr].v1array.push(statusObj.v1array[j]);
								statusObjOri[statusStr].cmparray.push(statusObj.cmparray[j]);
								statusObjOri[statusStr].v2array.push(statusObj.v2array[j]);
							}
						}
						
						var matchStatus = null;
						
						var triggerArr = this.triggerMap[tmpObj.trigger_field];
						if (triggerArr == null || triggerArr == undefined) {
							var matchStatusArr = new Array();
							this.triggerMap[tmpObj.trigger_field] = matchStatusArr;
						}
						
						for (var m = 0; m < statusTmpArr.length; m++) {
							var matchStatusObj = statusObjOri[statusTmpArr[m]];
							matchStatus = new MatchStatus(matchStatusObj);
							this.triggerMap[tmpObj.trigger_field].push(matchStatus);
						}
					}
				}
			});
		}
);