//define the basichim-NostText module

define(["dojo/_base/declare",
        "dojo/dom-attr",
        "dojo/html", 
        "dojo/query",
        "dr/base/DNode","dr/base/common",
        "dr/logger"],
	function(declare, domAttr, html, query,
			DNode,DCommon,
			logger) {
		var flashAnimID = "ba8ddb2e-c935-414b-b119-262ddd4a18d7";
		
		return declare(DNode, {
			/****************** fields *****************************/
			animsMap:null,
			
			/******************* methods *************************/
			
			///////////////////元素配置方法 ///////////////////////
			handleAnims:function() {
				//处理anims属性内容
				this.animsMap = {};
				var animsMapJson = domAttr.get(this.rawNode,"anims");
				if (animsMapJson == "" || animsMapJson == undefined) {
					return;
				}
				
				this.animsMap = JSON.parse(animsMapJson);
				//flash
				if (this.animsMap.flash != undefined) {
					var flashProps = this.animsMap.flash;
					var firstColor = flashProps.aColor,
						secondColor = flashProps.bColor,
						fInterval = flashProps.interval;
					this.setFlash(firstColor,secondColor,fInterval);
				}
				this.beginFlash();
			},
			////////////////// 元素属性 //////////////////////////
			x:function(newVal) {
				domAttr.set(this.rawNode, "x", newVal);
				logger.log('set x new ' + newVal);
			},
			y:function(newVal) {
				domAttr.set(this.rawNode, "y", newVal);
				logger.log('set y new ' + newVal);
			},
			fill:function(newVal) {
				var newColor = "orange";
				if (newVal > 0 && newVal < 100) {
					newColor = "brown";
				} else if (newVal >= 100 && newVal < 1000) {
					newColor = "DeepPink";
				}
				domAttr.set(this.rawNode,"fill",newColor);
				logger.log('set fill new ' + newVal);
			},
			text:function(newVal) {
				html.set(this.rawNode,newVal + "");
//				domAttr.set(this.rawNode,"text",newVal);
				logger.log('set text new ' + newVal);
				//this.beginFlash();
			},
			
			///////////////////////// 动画 //////////////////////

			beginFlash:function() {
				var rawAnim = query("#" + flashAnimID, this.rawNode.parentNode)[0];
				rawAnim.beginElement();
			},
			endFlash:function() {
				var rawAnim = query("#" + flashAnimID, this.rawNode.parentNode)[0];
				rawAnim.endElement();
			},
			setFlash:function(firstColor, secColor, fInterval) {
				var animateObj = document.createElementNS(DCommon.SVG_XMLNS, "animate");
				animateObj.setAttribute("attributeName","fill");
				animateObj.setAttribute("begin","indefinite");
				animateObj.setAttribute("dur",fInterval+"");
				animateObj.setAttribute("from",firstColor + "");
				animateObj.setAttribute("to",secColor + "");
				animateObj.setAttribute("fill","remove");
				animateObj.setAttribute("calcMode","discrete");
				animateObj.setAttribute("repeatCount","indefinite");
				animateObj.setAttribute("id",flashAnimID);
				//append animate obj to parent <g> element
				this.rawNode.parentNode.appendChild(animateObj);
			},
			
			//////////////////////// 设置属性值方法 /////////////////////
			set:function(field, newValue) {
				this[field](newValue);
			}
		});
});