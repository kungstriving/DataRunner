window.onload = function() {
	var testsvg = document.getElementById("switchtest");
	var snapO = Snap(testsvg).select("#a6e58d51-9592-4c03-bc31-b747713f092f");
//	var snapO = document.getElementById("a6e58d51-9592-4c03-bc31-b747713f092f");
	Snap.animate(0,-45,function(val){
		snapO.attr({transform:"translate(" + val +")"});
	},5000);
//	snapO.animate({transform:"translate(-45)"},500);
	snapO.click(function() {
//		throw "this is an error test";
		console.log("after the error");
	});
	
	setInterval(function() {
		console.log("bi");
	}, 1000);
	
};