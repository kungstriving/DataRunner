//define module is like utils
define(
		function() {
			return {
				getContextPath:function() {
					var pathname = window.location["pathname"];
					var indexPages = pathname.indexOf("pages");
					var returnPath;
					if (indexPages == 1) {
						returnPath = "";
					} else {
						returnPath = pathname.substring(0, indexPages);
					}
					return returnPath;
				},
				
				NAME_SEP:"_",
				SVG_XMLNS:"http://www.w3.org/2000/svg"
			};
		}
);