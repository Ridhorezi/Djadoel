$(document).ready(function() {
	$("#logoutLinkHeader").on("click", function(e) {
		e.preventDefault();
		document.logoutForm.submit();
	});
});

$(document).ready(function() {
	$("#logoutLinkSidebar").on("click", function(e) {
		e.preventDefault();
		document.logoutForm.submit();
	});
});