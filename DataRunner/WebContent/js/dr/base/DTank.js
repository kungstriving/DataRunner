//define the basichmi-tank module

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
			
			animBackStopColor:null,
			animBackStopOffset:null,
			animFrontStopColor:null,
			animFrontStopOffset:null,
			
			eventMap:null,		//事件map
			matchTable:null,	//匹配表
			
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
				//获取背景色
				this.animBackStopColor = query("#" + this.id + "_5e6cb560-9ea8-4954-8e87-f79d8303f6ad", this.rawNode)[0];
				//获取前景色
				this.animFrontStopColor = query("#" + this.id + "_f98e9dfd-7a0c-4411-a17d-b8b8661792ea", this.rawNode)[0];
				
				//获取背景比例
				this.animBackStopOffset = query("#" + this.id + "_e98dd2cc-cdf1-47fc-9cde-7da7d7682f52", this.rawNode)[0];
				
				//获取前景比例
				this.animFrontStopOffset = query("#" + this.id + "_a60f03de-4d9d-4414-81b5-ac67d200400a", this.rawNode)[0];
			},
			
			//处理固定值配置
			handleSetProps:function() {
				var setPropArrJson = domAttr.get(this.rawNode,"sps");	//array
				var setPropArr = JSON.parse(setPropArrJson);
				var i = 0,
					propLength = setPropArr.length,
					propObj = null;
				for(; i < propLength; i++) {
					propObj = setPropArr[i];
					var propName = propObj.set_prop;
					var propVal = propObj.set_value;
					this[propName](propVal);
				}
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
			
			//设置填充百分比 newVal 0~1
			fillPercent:function(newVal) {
				var realPerc = 1 - newVal;
				var oldPerc = domAttr.get(this.animBackStopOffset, "to");
				domAttr.set(this.animBackStopOffset,"from",oldPerc);
				domAttr.set(this.animBackStopOffset, "to", realPerc);
				domAttr.set(this.animFrontStopOffset,"from",oldPerc);
				domAttr.set(this.animFrontStopOffset, "to", realPerc);
				this.animBackStopOffset.beginElement();
				this.animFrontStopOffset.beginElement();
			},
			
			/**
			 * 填充前景色属性
			 */
			fillFrontColor:function(newVal) {
				domAttr.set(this.animFrontStopColor, "to", newVal);
				this.animFrontStopColor.beginElement();
			},
			/**
			 * 填充背景色属性
			 */
			fillBackColor:function(newVal) {
				domAttr.set(this.animBackStopColor, "to", newVal);
				this.animBackStopColor.beginElement();
			},
			
			///////////////////////////元素动画////////////////////////////////
		});
});