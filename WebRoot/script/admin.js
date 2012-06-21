function refreshSystemTime() {
	var dateTime = new Date();
	var hour = dateTime.getHours();
	var minute = dateTime.getMinutes();
	var timeStr = hour+":"+minute;
	document.getElementById("systemTimeSpan").innerHTML = timeStr;
}

function clickSubMenu(menuItem) {
	$(".menu_item_selected")[0].className = "menu_item";
	menuItem.className = "menu_item_selected";
}