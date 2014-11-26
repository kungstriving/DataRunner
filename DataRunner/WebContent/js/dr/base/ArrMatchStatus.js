//define ArrMatchStatus module

define(
		["dojo/_base/declare"],
		function(declare) {
			return declare(null, {
				matchedStatus:"",
				v1Array:null,
				cmpArray:null,
				v2Array:null,
				
				/************* methods **************/
				oo:function(p1,p2,val) {
					if (val > p1 && val < p2) {
						return true;
					}
					return false;
				},
				oc:function(p1,p2,val) {
					if (val > p1 && val <= p2) {
						return true;
					}
					return false;
				},
				co:function(p1,p2,val) {
					if (val >= p1 && val < p2) {
						return true;
					}
					return false;
				},
				cc:function(p1,p2,val) {
					if (val >= p1 && val <= p2) {
						return true;
					}
					return false;
				},
				
				isMatch:function(newVal) {
					var length = this.v1Array.length;
					for(var i = 0; i < length; i++) {
						var v1 = this.v1Array[i];
						var v2 = this.v2Array[i];
						var cmpType = this.cmpArray[i];
						if (this[cmpType](v1,v2,newVal)) {
							return true;
						}
					}
					
					return false;
				},
				
				constructor:function(pStatusObj) {
					/*
					 {
							"status":"on",
							"v1array":[0,2,4],
							"cmparray":["oo","oo","oc"],
							"v2array":[2,4,Number.MAXVALUE]
						}
					{"statusarr":["red","yellow"],
								"v1array":[-9999999999,50],
								"cmparray":["oc","oc"],
								"v2array":[50,9999999999]}
					 */
					this.matchedStatus = pStatusObj.status;
					this.v1Array = pStatusObj.v1array;
					this.v2Array = pStatusObj.v2array;
					this.cmpArray = pStatusObj.cmparray; 
				}
			});
		}
);