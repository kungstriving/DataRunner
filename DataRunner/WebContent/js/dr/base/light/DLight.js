//define the light_light module

define(["dojo/_base/declare",
        "dojo/dom-attr",
        "dojo/query",
        "dojo/on",
        "dr/base/DNode","dr/base/ArrMatchTable","dr/base/DSystem",
        "./js/snap.svg.js"],
	function(declare, domAttr, query,on,
			DNode,ArrMatchTable,DSystem) {
		return declare(DNode, {
			
			/****************** fields *****************************/
			//这边的属性为自身内部使用的属性，凡是暴露给用户，可以进行动画操作的，都需要在‘元素属性’中定义
			f_x:"",
			f_y:"",
			f_light_color:"",
			f_light_bright:"",
			f_power:false,
			
			eventMap:null,	
			matchTable:null,
			
			/******************* methods *************************/

			/////////////////////元素配置方法 ////////////////////////
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
			
			registerSelf:function() {
				//设置选中的状态标示
				//元素外围高亮闪光0.5s
				//元素外围绘制stroke-width = 2的橘色线条
			},
			
			//////////////////// 元素属性 /////////////////////////////

			x:function(newVal) {
				domAttr.set(this.rawNode, "x", newVal);
				console.log('set x new ' + newVal);
			},
			
			y:function(newVal) {
				domAttr.set(this.rawNode, "y", newVal);
				console.log('set y new ' + newVal);
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