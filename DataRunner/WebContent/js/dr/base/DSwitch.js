//define the basichmi-switch module

define(["dojo/_base/declare",
        "dojo/dom-attr",
        "dojo/query",
        "dojo/on",
        "dr/base/DNode","dr/base/MatchTable","dr/base/DSystem",
        "dr/logger",
        "./js/snap.svg.js"],
	function(declare, domAttr, query,on,
			DNode,MatchTable,DSystem,
			logger) {
		return declare(DNode, {
			
			/****************** fields *****************************/
			switchon:false,		//默认开关是关的
			eventMap:null,	
			matchTable:null,
			switchBlock:null,
			/******************* methods *************************/

			/////////////////////元素配置方法 ////////////////////////
			handleEvent:function() {
				this.eventMap = {};
				var eventConfigJson = domAttr.get(this.rawNode,"ec");
				this.eventMap = JSON.parse(eventConfigJson);
			},
			
			handleMatchTable:function() {
				var matchTableJson = domAttr.get(this.rawNode, "mt");
				this.matchTable = new MatchTable(matchTableJson);
				
			},
			
			registerSelf:function() {
				var blockID = "#" + this.id+"_a6e58d51-9592-4c03-bc31-b747713f092f";
				this.switchBlock = query(blockID, this.rawNode)[0];
				var thisSwitch = this;
				on(this.switchBlock,"click",function(evt) {
					if (thisSwitch.switchon == true) {
						//关闭开关
						//调用用户定义的关闭开关函数
						var funcOffHandle = thisSwitch.eventMap["switchoff"];
						if (funcOffHandle == null || funcOffHandle == "undefined") {
							logger.warn("user switchoff event handler not defined");
						} else {
							//invoke user function
//							funcOffHandle();
							var tempFuncName = funcOffHandle + "(DSystem)"; 
							eval(tempFuncName);
							//close the switch , if the user's function got error, then you will not 
							//get here
							thisSwitch.closeSwitch();
						}
					} else {
						//打开开关
						//调用用户定义的打开开关函数
						var funcOnHandle = thisSwitch.eventMap["switchon"];
						if (funcOnHandle == null || funcOnHandle == "undefined") {
							logger.log("user switchon event handler not defined");
						} else {
							//invoke user function
//							funcOnHandle();
							var tempFuncName = funcOnHandle + "(DSystem)"; 
							eval(tempFuncName);
							thisSwitch.openSwitch();
						}
					}
				});
			},
			
			//////////////////// 元素属性 /////////////////////////////

			x:function(newVal) {
				domAttr.set(this.rawNode, "x", newVal);
				logger.log('set x new ' + newVal);
			},
			
			y:function(newVal) {
				domAttr.set(this.rawNode, "y", newVal);
				logger.log('set y new ' + newVal);
			},
			watch_value:function(newVal) {
				//结合mt(match table)判断如何设定当前控件的表现形态
				var status = this.matchTable.getStatus("watch_value",newVal);
				if (status == "on") {
					this.openSwitch();
				} else if (status == "off") {
					this.closeSwitch();
				}
			},
			
			///////////////////// 元素动画 ////////////////////////////
			openSwitch:function() {
				//打开开关
				if (this.switchon == true) {
					return;
				}
//				var sanpNode = Snap(this.rawNode);
				if (this.switchBlock == null || this.switchBlock == undefined) {
					this.switchBlock = query("#" + this.id + "_a6e58d51-9592-4c03-bc31-b747713f092f", this.rawNode)[0];
				}
				var thisSwitch = this;
				Snap.animate(0,45,function(val){
					domAttr.set(thisSwitch.switchBlock,{transform:"translate(" + val + ")"});
				},1000);
				
				this.switchon = true;
			},
			closeSwitch:function() {
				//关闭开关
				if (this.switchon == false) {
					return;
				}
				if (this.switchBlock == null || this.switchBlock == undefined) {
					this.switchBlock = query("#" + this.id + "_a6e58d51-9592-4c03-bc31-b747713f092f", this.rawNode)[0];
				}
				var thisSwitch = this;
				Snap.animate(45,0,function(val){
					domAttr.set(thisSwitch.switchBlock,{transform:"translate(" + val + ")"});
				},1000);
				this.switchon = false;
			},

			set:function(field, newValue) {
				this[field](newValue);
			}
		});
});