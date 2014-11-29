//define the light_light module

define(["dojo/_base/declare",
        "dojo/dom",
        "dojo/dom-attr",
        "dojo/dom-style",
        "dojo/query",
        "dojo/on",
        "dojo/_base/lang",
        "dojo/topic",
        "dojox/gesture/tap", 
        
        "dr/logger",
        "dr/base/DNode",
        "dr/base/ArrMatchTable",
        "dr/base/DSystem",
        "./js/snap.svg.js"],
	function(declare, 
			dom, 
			domAttr, 
			domStyle,
			query,
			on,
			lang,
			topic,
			tap,
			
			logger,
			DNode,
			ArrMatchTable,
			DSystem) {
		return declare(DNode, {
			
			/****************** fields *****************************/
			//这边的属性为自身内部使用的属性，凡是暴露给用户，可以进行动画操作的，都需要在‘元素属性’中定义
			//f_ 为每个属性前缀，后面的property名称，可以进行set操作
			f_x:"",
			f_y:"",
			f_light_color:"",
			f_light_bright:"",
			f_power:false,
			f_lr:"",		//light radius
			eventMap:null,	
			matchTable:null,
			
			/******************* methods *************************/

			/////////////////////元素配置方法 ////////////////////////
			
			_init:function() {
				this._snapLightCircle = Snap("#" + this.id + "_DCFBD31E-D55B-96B8-A695-E2DB27CE764F");
			},
			
			//此处方法由DNodeFactory调用，定制化设置不同的元素
			handleEvent:function() {
				this.eventMap = {};
				var eventConfigJson = domAttr.get(this.rawNode,"ec");
				this.eventMap = JSON.parse(eventConfigJson);
			},
			
			handleMatchTable:function() {
				var matchTableJson = domAttr.get(this.rawNode, "mt");
				this.matchTable = new ArrMatchTable(matchTableJson);
				
			},
			
			/**
			 * 处理固定值的属性设置
			 */
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
			
			registerSelf:function() {
				//设置选中的状态标示
				var lightID = this.id + "_DCFBD31E-D55B-96B8-A695-E2DB27CE764F";
				on(dom.byId(lightID), "click", lang.hitch(this, function() {
					//cx cy 加5是因为light的半径并没有填充满整个svg
					var selectBoxInfo = {
							cx:(parseFloat(this.f_x) + parseFloat(this.f_lr) + 5),
							cy:(parseFloat(this.f_y) + parseFloat(this.f_lr) + 5),
							r:this.f_lr};
					topic.publish("lightpage/select", selectBoxInfo);
					return true;
				}));
				//元素外围高亮闪光0.5s
				//元素外围绘制stroke-width = 2的橘色线条
			},
			
			//////////////////// 元素属性 /////////////////////////////

			x:function(newVal) {
				domAttr.set(this.rawNode, "x", newVal);
				this.f_x = newVal;
				logger.log('set x new ' + newVal);
			},
			
			y:function(newVal) {
				domAttr.set(this.rawNode, "y", newVal);
				this.f_y = newVal;
				logger.log('set y new ' + newVal);
			},
			lr:function(newVal) {
				var lrID = this.id + "_DCFBD31E-D55B-96B8-A695-E2DB27CE764F";
				domAttr.set(lrID, "r", newVal);
				this.f_lr = newVal;
				logger.log("set light radius new value = " + newVal);
			},
			
			light_color:function(newValue) {
				//设置灯光颜色
				
			},
			
			light_bright:function(newValue) {
				//设置灯亮度
			},
			
			power:function(newValue) {
				//开关灯
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
			}
		});
});