//define the basichmi-pump module

define(["dojo/_base/declare",
        "dojo/dom-attr",
        "dojo/query",
        "dojo/on",
        "dr/base/DNode",
        "dr/base/MatchTable",
        "dr/base/DSystem",
        "dr/logger",
        "./js/snap.svg.js"],
	function(declare, 
			domAttr, 
			query,
			on,
			DNode,MatchTable,DSystem,
			logger) {
		return declare(DNode, {
			
			/****************** fields *****************************/
			pumpon:true,		//默认开关是关的
			eventMap:null,		//事件map
			matchTable:null,	//匹配表
			pumpLeaf:null,		//叶片控制单元
			
			/******************* methods *************************/

			/////////////////////////////// 元素配置方法///////////////////////////
			handleEvent:function() {
				this.eventMap = {};
				var eventConfigJson = domAttr.get(this.rawNode,"ec");
				this.eventMap = JSON.parse(eventConfigJson);
			},
			
			handleMatchTable:function() {
				var matchTableJson = domAttr.get(this.rawNode, "mt");
				this.matchTable = new MatchTable(matchTableJson);
				
			},
			
			handleAnimateObjs:function() {
				//获取所要操作的动画对象
				this.pumpLeaf = query("#" + this.id + "_f9f69de0-c1f3-49a0-863f-15ac0573dba5", this.rawNode)[0];
			},
			
			////////////////////////////元素属性////////////////////////////////
			x:function(newVal) {
				domAttr.set(this.rawNode, "x", newVal);
				logger.log('set x new ' + newVal);
			},
			
			y:function(newVal) {
				domAttr.set(this.rawNode, "y", newVal);
				logger.log('set y new ' + newVal);
			},
			
			//转速属性
			pumpSpeed:function(newVal) {
				//转速值应该是每分钟多少转，换算为相应的tranform值后设置动画
				//线性变量通过函数进行控制
				if(this.pumpon == true) {
					//当前开关打开状态 换算转速
					var durSec = (60/newVal);
					
					var domPumpLeafAnim = query("#" + this.id + "_a52995c3-59e1-4b04-b8f6-cd573c43a7c7", this.rawNode)[0];
					domPumpLeafAnim.setAttribute("dur",durSec);
					domPumpLeafAnim.endElement();
					domPumpLeafAnim.beginElement();
					
//					var snPumpLeaf = Snap(this.pumpLeaf);
//					function doAnimate(pAngle) {
//						snPumpLeaf.animate({transform:"r" + pAngle + ",50,45"},1000,mina.linear,doAnimate(pAngle));
//					};
//					doAnimate(angle);
					logger.log('got new pump speed ' + newVal);
				}
			},
			//开关属性
			pumpSwitch:function(newVal) {
				//离散变量 由matchtable控制
				logger.log('got new pump switch ' + newVal);
				//this.pumpon = !this.pumpon;
			},
			
			///////////////////////////元素动画////////////////////////////////
			
			//////////////////////////////////// 设置属性值方法///////////////
			set:function(field, newValue) {
				this[field](newValue);
			}
		});
});