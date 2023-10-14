function removeLogoutParam() {
	// Menghapus parameter 'logout' dari URL
	const url = new URL(window.location.href);
	url.searchParams.delete("logout");
	history.replaceState(null, "", url);
}

function removeErrorParam() {
	const url = new URL(window.location.href);
	url.searchParams.delete("error");
	history.replaceState(null, "", url);
}

function removeLoginParam() {
	const url = new URL(window.location.href);
	url.searchParams.delete("continue");
	history.replaceState(null, "", url);
}

const passwordInput = document.getElementById("password");
const showPasswordCheckbox = document.getElementById("showPassword");

showPasswordCheckbox.addEventListener("change", function() {
	if (showPasswordCheckbox.checked) {
		passwordInput.type = "text";
	} else {
		passwordInput.type = "password";
	}
});