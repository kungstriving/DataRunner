//define the DNode module

define(["dojo/_base/declare"],
	function(declare) {
		return declare(null, {
			
			/****************** fields *****************************/
			rawNode:null,		//the raw HTML/SVG document node
			nType:"",		//node type eg:Rect/Circle/Point
			name:"",		//name for the node
			id:"",			//id for the node
			testK:"",
			/******************* methods *************************/
			
			constructor:function(pRawNode, pType,pName,pID) {
				this.rawNode = pRawNode;
				this.nType = pType;
				this.name = pName;
				this.id = pID;
			}
		});
});