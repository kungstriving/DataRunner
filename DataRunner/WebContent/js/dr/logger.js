define(

function() {
	return {
		logLevel:1,		//0-debug 1-log 2-info 3-warn 4-error

		debug:function(info) {
			if (this.logLevel <= 0) {
				console.debug(info);
			}
		},
		log:function(info) {
			if (this.logLevel <= 1) {
				console.log(info);
			}
		},
		info:function(info) {
			if (this.logLevel <= 2) {
				console.info(info);
			}
		},
		warn:function(info) {
			if (this.logLevel <= 3) {
				console.warn(info);
			}
		},
		error:function(info) {
			if (this.logLevel <= 4) {
				console.error(info);
			}
		}
		/*
		 * console.log();
		 * 
		 * console.debug();
		 * 
		 * console.error();
		 */
	};
});