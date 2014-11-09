//define the Page module

define(["dojo/_base/declare",
        "dojo/store/Memory",
        "dojo/store/Observable",
        "dr/base/common"],
	function(declare, Memory, Observable, 
			common) {
		return declare(null, {
			
			/****************** fields *****************************/
			node:null,		//NostNode object
			field:"",		//avoid reference error use null
			
			/******************* methods *************************/
			
			set:function(newValue) {
				this.node.set(this.field, newValue);
//				var fieldFun = this.node["set"+field];
//				fieldFun(newValue);
			},
			
			constructor:function(pNode, pField) {
				this.node = pNode;
				this.field = pField;
			}
		});
});