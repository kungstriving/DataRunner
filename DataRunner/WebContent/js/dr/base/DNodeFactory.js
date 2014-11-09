//define the NostNodeFactory module

define(["dr/base/common","dr/base/DSwitch","dr/base/DText","dr/base/DPump","dr/base/DTank",
        "dojo/dom-attr"],
	function(common,DSwitch,DText,DPump,DTank,domAttr) {
		return {
			
			/****************** fields *****************************/
			basichmi:{
				//针对不同类型的元素在这里进行初始化配置
				"switch":function(pNode,pNType,pNodeName,pNodeID) {
					var switchObj = new DSwitch(pNode, pNType, pNodeName,pNodeID);
					//config the switch
					switchObj.handleEvent();
					switchObj.handleMatchTable();
					switchObj.registerSelf();
					return switchObj;
				},
				"text":function(pNode,pNType,pNodeName,pNodeID) {
					var textObj = new DText(pNode,pNType,pNodeName,pNodeID);
					//config the text
					textObj.handleAnims();
					return textObj;
				},
				"pump":function(pNode,pNType,pNodeName,pNodeID) {
					var pumpObj = new DPump(pNode,pNType,pNodeName,pNodeID);
					pumpObj.handleEvent();
					pumpObj.handleMatchTable();
					pumpObj.handleAnimateObjs();
					return pumpObj;
				},
				"tank":function(pNode,pNType,pNodeName,pNodeID) {
					var tankObj = new DTank(pNode,pNType,pNodeName,pNodeID);
					tankObj.handleEvent();
					tankObj.handleMatchTable();
					tankObj.handleAnimateObjs();
					tankObj.handleSetProps();
					return tankObj;
				}
			},
			/******************* methods *************************/
			
			getNostNodeByType:function(pNType,pNode) {
				var firstType = pNType.split(common.NAME_SEP)[0];
				var secType = pNType.split(common.NAME_SEP)[1];
				var nodeName = domAttr.get(pNode,"name");
				var nodeID = domAttr.get(pNode,"id");
				
				return this[firstType][secType](pNode,pNType,nodeName,nodeID);
			}
		};
});