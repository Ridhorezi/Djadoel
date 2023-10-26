/*
	Edit & Create js 
*/

function checkPasswordMatch(confirmPassword) {
	if (confirmPassword.value != $("#password").val()) {
		confirmPassword.setCustomValidity("Password do not match!");
	} else {
		confirmPassword.setCustomValidity("");
	}
}

function showImageThumbnail(fileInput) {
	var file = fileInput.files[0];
	var reader = new FileReader();
	reader.onload = function(e) {
		$('#thumbnail').attr("src", e.target.result);
	}
	reader.readAsDataURL(file);
}


$(document).ready(function() {
	$('#buttonCancel').on("click", function() {
		window.location = moduleURL;
	});

	$('#fileImage').change(function() {
		var fileInput = this;
		var fileSize = fileInput.files[0].size;
		var maxSize = 1048576; // 1MB

		if (fileSize > maxSize) {
			this.setCustomValidity("File size is too large. You must choose an image less than 1MB!")
			this.reportValidity();
		} else {
			this.setCustomValidity("")
			showImageThumbnail(fileInput);
		}
	});
});


/*
	Index js
*/

const Toast = Swal.mixin({
	toast: true,
	position: 'top-end',
	showConfirmButton: false,
	timer: 3000,
	timerProgressBar: true,
	didOpen: (toast) => {
		toast.addEventListener('mouseenter', Swal.stopTimer)
		toast.addEventListener('mouseleave', Swal.resumeTimer)
	}
})


$(document).ready(function() {
	// Event delegation untuk tombol delete
	$('#datatable').on('click', '.btn.btn-danger.btn-sm', function(e) {
		e.preventDefault();
		var deleteLink = $(this).attr('href');
		Swal.fire({
			title: 'Are you sure?',
			text: "You won't be able to revert this!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: 'Yes, delete it!'
		}).then((result) => {
			if (result.isConfirmed) {
				window.location.href = deleteLink;
			}
		});
	});
});

$('#csvButton').click(function(e) {
	e.preventDefault();
	var exportLink = $(this).attr('href');
	Swal.fire({
		title: 'Export CSV',
		text: 'Are you sure you want to export CSV?',
		icon: 'question',
		showCancelButton: true,
		confirmButtonText: 'Yes, export it!',
		cancelButtonText: 'Cancel',
		showLoaderOnConfirm: true,
		preConfirm: () => {
			return new Promise((resolve) => {
				setTimeout(() => {
					resolve();
				}, 2000);
			});
		}
	}).then((result) => {
		if (result.isConfirmed) {
			window.location.href = exportLink;
		}
	});
});

$('#excelButton').click(function(e) {
	e.preventDefault();
	var exportLink = $(this).attr('href');
	Swal.fire({
		title: 'Export Excel',
		text: 'Are you sure you want to export Excel?',
		icon: 'question',
		showCancelButton: true,
		confirmButtonText: 'Yes, export it!',
		cancelButtonText: 'Cancel',
		showLoaderOnConfirm: true,
		preConfirm: () => {
			return new Promise((resolve) => {
				setTimeout(() => {
					resolve();
				}, 2000);
			});
		}
	}).then((result) => {
		if (result.isConfirmed) {
			window.location.href = exportLink;
		}
	});
});

$('#pdfButton').click(function(e) {
	e.preventDefault();
	var exportLink = $(this).attr('href');
	Swal.fire({
		title: 'Export PDF',
		text: 'Are you sure you want to export PDF?',
		icon: 'question',
		showCancelButton: true,
		confirmButtonText: 'Yes, export it!',
		cancelButtonText: 'Cancel',
		showLoaderOnConfirm: true,
		preConfirm: () => {
			return new Promise((resolve) => {
				setTimeout(() => {
					resolve();
				}, 2000);
			});
		}
	}).then((result) => {
		if (result.isConfirmed) {
			window.location.href = exportLink;
		}
	});
});

// get current URL
var currentUrl = window.location.pathname;

// Find all link with class "sidebar-link"
var sidebarLinks = $('a.sidebar-link');

// Loop through the links
sidebarLinks.each(function() {
	var linkHref = $(this).attr('href');

	/* Check if the current URL matches or starts with the link's href */
	if (currentUrl === linkHref || currentUrl.startsWith(linkHref + '/')) {
		$(this).addClass('active');
	}
});