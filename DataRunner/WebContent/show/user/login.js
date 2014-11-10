
require(["dojo/dom",
         "dojo/_base/fx",
         "dojo/fx/easing",
         "dojo/on",
         "dojo/request",
         "dojo/request/notify",
         "dojo/dom-form",
         "dr/base/common",
         "../../js/jquery-2.1.0.js",
         "../../js/jquery.mobile-1.4.2.js",
         "dojo/domReady!"],
	function(dom, 
			baseFx, 
			easingType, 
			on, 
			request, 
			reqNotify,
			domForm,
			common) {
	var loginPanel = dom.byId("loginpanel"),
		loginButton = dom.byId("loginbtn");
	
	//register the user login click
	on(loginButton,"click",login);
	//register the ajax request events
	reqNotify("start", function() {
		$.mobile.loading( "show", {
			text: "加载中...",
			textVisible: true,
			theme: "a",
			html: ""
			});
	});
	
	reqNotify("done", function(data) {
		$.mobile.loading("hide");
	});
	
	baseFx.animateProperty({
		node:loginPanel,
		properties:{
			marginTop:120,
			opacity:1
		},
		easing:easingType.bounceOut,
		duration:2000
	}).play();
	
	///////////user events//////////////////
	function login(evt) {
		console.log('user login');
		var username = domForm.fieldToObject("un"),
			password = domForm.fieldToObject("pwd");
		//encrypt the password
		
		//post data to server
		var requestURL = "../../drd";
		request.post(requestURL, {
			data:{
				"action":"pageLogin",
				"lessee":"user",		//lessee = user
				"username":username,
				"secretPwd":password
			},
			handleAs:"json"
		}).then(
				function(response) {
					//resolve the response and access to the init page
					
					var msgObj = JSON.parse(response.msg);
					var pageName = msgObj.initPage;
					
					window.location.href = requestURL + "?action=loadPage&pageName=" + pageName;
				},
				function(error) {
					//alert the error message
					console.log(error);
				}
		);
	}
});
