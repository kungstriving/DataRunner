//define the LightPage module

define(["dojo/_base/declare",
        "dojo/on",
        "dojo/dom",
        "dojox/gesture/swipe",
        "dojox/gesture/tap",
        "dojo/_base/lang",
        "dojo/topic",
        
        "dr/logger",
        "dr/base/Page",
        "./js/snap.svg.js",
        "./js/touch-0.2.14.min.js"
        ],
	function(declare, 
			on,
			dom,
			swipe,
			tap,
			lang,
			topic,
			
			logger,
			Page) {
	
	return declare(Page, {
		
		lightSnap:null,
		currentStatus:"normal",		//normal selected
		selSwipeHandle:null,
		tapHandle:null,
		
		_swipeStart:function(evt) {
			logger.log("swipe");

			return true;
		},
		_swipeEnd:function(evt) {
			if (evt.dx >= 0) {
				logger.log('right');
			} else {
				logger.log('left');
			}
			if (evt.dy >= 0) {
				logger.log('down');
			} else {
				logger.log('up');
			}
			return true;
		},
		_pageClick:function(evt) {
			
			var evtcx = evt.pageX / this.scaleRatio,
				evtcy = evt.pageY / this.scaleRatio;
			//alert('tap@' + cx + " " +cy);
//			var snap = Snap("#sketchpad");
			this._tabCircle.attr({opacity:1, visibility:"visible", cx:evtcx, cy:evtcy})
				.animate({opacity:0}, 600,mina.easeinout);
			this.currentStatus = "normal";
			topic.publish("lightpage/noselect");
			return true;
		},
		
		/**
		 * 当前页面没有选中任何元素的回调
		 */
		_noSelection:function() {
			this._selectBox.attr({visibility:"hidden"});
			if (this.selSwipeHandle != null) {
				this.selSwipeHandle.remove();
			}
		},
		
		_addSelection:function(bbox) {
			var boxcx = bbox.cx,
				boxcy = bbox.cy,
				boxr = bbox.r;
//			var selPath = "M" + boxcx + "," + (boxcy-boxr) 
//			+ " A" + boxr + "," + boxr + " 0 1,1 " + boxcx + "," + (boxcy-boxr);
//			selPath="M20,20 A45,45 0 1,0 20,20 z";
//			this.lightSnap.paper.path(selPath).attr({
//						stroke:"red",
//						strokeWidth:5,
//						fill:"none"
//					});
			this._selectBox.attr({visibility:"visible",cx:boxcx, cy:boxcy,r:boxr});
			if (this.currentStatus == "normal") {
				
				//选中状态下启动滑动事件监听
				this.selSwipeHandle = on(dom.byId("padBG"), swipe.end, lang.hitch(this, this._swipeEnd));
				var tempTapHandle = on(dom.byId("padBG"),tap, lang.hitch(this, function(evt) {
					this.selSwipeHandle.remove();
					tempTapHandle.remove();
					this._selectBox.attr({visibility:"hidden"});
					this.currentStatus = "normal";
				}));
				this.currentStatus = "selected";
			}

//			touch.on(dom.byId("padBG"), "touchstart", function(ev) {
//				ev.preventDefault();
//			});
			
			//on(dom.byId("padBG"), "click", lang.hitch(this, this._pageClick));
		},
		
		init:function() {
			this.inherited(arguments);
			//add the user tap logic.
			this.lightSnap = Snap("#sketchpad");
			//create the tap circle
			var circleF = this.lightSnap.paper.filter(Snap.filter.blur(5));
			this._tabCircle = this.lightSnap.paper.circle(0,0,50).attr({
				fill:"white",
				strokeWidth:0,
				filter:circleF,
				visibility:"hidden"
			});
			
			//fill:none 不填充 底部的元素不会被遮住，任然可以响应事件；如果使用fill-opacity:0,会遮蔽事件
			this._selectBox = this.lightSnap.paper.circle(0,0,50).attr({
				fill:"none",
				strokeWidth:5,
				stroke:"#CC6D39",
				visibility:"hidden"
			});
			//register the event
//			on(this.rootPad, swipe, this._swipeH);
//			on(this.rootPad, swipe.end, this._swipeE);
			on(dom.byId("padBG"), "click", lang.hitch(this, this._pageClick));
			topic.subscribe("lightpage/select", lang.hitch(this, this._addSelection));
			topic.subscribe("lightpage/noselect", lang.hitch(this, this._noSelection));
		}
	});
});