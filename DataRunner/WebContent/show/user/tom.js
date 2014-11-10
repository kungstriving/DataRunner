
function pump1switch_open(system) {
	alert('pump1switch-open');
}

function pump1switch_close(system) {
	alert('pump1switch-close');
	system.writeValue("rand_tag1","value","1");
}

function pump2switch_open(system) {
	alert('pump2switch-open');
}

function pump2switch_close(system) {
	alert('pump2switch-close');
}