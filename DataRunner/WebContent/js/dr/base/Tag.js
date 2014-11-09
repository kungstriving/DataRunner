//define tag module

define(
		["dojo/_base/declare"],
		function(declare) {
			return declare(null, {
				tagname:"",
				refexps:null,		//[0,1,2]
				tagval:"",
				
				constructor:function(pTagName) {
					this.tagname = pTagName;
					this.refexps = [];
				}
			});
		}
);