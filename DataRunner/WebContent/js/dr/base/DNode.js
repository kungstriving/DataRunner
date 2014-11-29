//define the DNode module

define(["dojo/_base/declare",
        "./js/snap.svg.js"],
	function(declare) {
		return declare(null, {
			
			/******************************************************/
			/**  Field                                          **/
			/******************************************************/
			
			/*
			 * Field和Property对应，结合C#语言中对象的特性
			 * Field就是一个简单值，一个Property就是对该属性的set方法，由Property确定字面名称，Field在前面加“f_”
			 * 比如 fill(填充色)，fill:function(){} 就是它的Property定义，表明该对象有一个fill属性
			 * 调用该函数将直接设置fill属性；对应的Field就是f_fill，它表明当前的填充色的值#999999.可以通过getFill()获取
			 */
			rawNode:null,		//the raw HTML/SVG document node
			snapRawNode:null,	//the snap wrapped node
			nType:"",		//node type eg:Rect/Circle/Point
			name:"",		//name for the node
			id:"",			//id for the node
			testK:"",
			
			/******************************************************/
			/**  method                                          **/
			/******************************************************/
			
			constructor:function(pRawNode, pType,pName,pID) {
				this.rawNode = pRawNode;
				this.nType = pType;
				this.name = pName;
				this.id = pID;
				this.snapRawNode = Snap("#" + this.id);
				this._init();
			},
//			addSelectBox:function() {
//				var snapRawNode = Snap("#"+this.id);
//				var bbox = snapRawNode.getBBox();
//				
//				return true;
//			},
			/******************* 空方法 子类实现 ******************/
			/**
			 * 子类的初始化方法 可以实例化snap对象等
			 */
			_init:function() {},
			/**
			 * 解析元素所配置的事件处理函数
			 */
			handleEvent:function() {},
			/**
			 * 解析元素所配置的匹配表内容
			 */
			handleMatchTable:function() {},
			
			/**
			 * 获取设置属性过程中，所要操作的元素自身动画对象
			 */
			handleAnimateObjs:function() {},
			
			/**
			 * 处理固定值的属性设置
			 */
			handleSetProps:function() {},
			
			/**
			 * 注册元素自身的一些事件，比如开关的用户点击事件、灯的选中控制标识等
			 */
			registerSelf:function() {},
			
			/**
			 * 处理元素的动画定义 由anims定义，比如闪烁动画、路径动画等
			 */
			handleAnims:function() {},
			
			//////////////////////////////////// 设置属性值方法///////////////
			set:function(field, newValue) {
				this[field](newValue);
			},
			
			/******************************************************/
			/**  Property                                        **/
			/******************************************************/
			/*
			 * 这里定义每一个属性的set方法，该方法中将根据所绑定的表达式的最新值
			 * 1.修改元素状态（可能有预设的动画）
			 * 2.更新该属性对应的Field值
			 * 一般情况下，修改元素状态需要结合matchtable来进行，首先从matchtable中获取该值所对应
			 * 的属性状态，然后再把该状态设置到元素上；像text这种元素就不需要与matchtable进行比对，直接设置即可
			 */
		});
});