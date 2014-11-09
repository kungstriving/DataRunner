//define Expr module

define(
		["dojo/_base/declare"],
		function(declare) {
			return declare(null, {
				exp:"",			//expression
				cus:null,		//contunit array [{node:node, field:field},{}]
				val:"",
				comp:null,		//compiled expression
				
				constructor:function(pExp) {
					this.exp = pExp;
					this.cus = [];
					this.val = 0;		//init 0
				}
			});
		}
);