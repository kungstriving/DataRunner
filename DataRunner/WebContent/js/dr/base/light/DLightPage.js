//define the LightPage module

define(["dojo/_base/declare",
        "dojo/on",
        "dojox/gesture/swipe",
        "dojox/gesture/tap",
        
        "dr/base/Page"
        ],
	function(declare, 
			on,
			swipe,
			tap,
			
			Page) {
	
	return declare(Page, {
		_swipeH:function(evt) {
			console.log("swipe~~~~~~~~~~~~~~~~");
			return true;
		},
		_swipeE:function(evt) {
			if (evt.dx >= 0) {
				alert('right');
				console.log('right');
			} else {
				alert('left');
				console.log('left');
			}
			if (evt.dy >= 0) {
				alert('down');
				console.log('down');
			} else {
				alert('up');
				console.log('up');
			}
			return true;
		},
		_tapOn:function(evt) {
			console.log('tap');
			return true;
		},
		init:function() {
			this.inherited(arguments);
			//add the light selection logic
			
			
			//register the event
			on(this.rootPad, swipe, this._swipeH);
			on(this.rootPad, swipe.end, this._swipeE);
			on(this.rootPad, tap, this._tapOn);
		}
	});
});