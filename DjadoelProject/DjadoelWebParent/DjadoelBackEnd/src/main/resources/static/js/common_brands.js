/*
	Edit & Create js 
*/

$(document).ready(function() {
	dropdownCategories = $("#categories");
	chosenCategories = $("#chosen");

	dropdownCategories.change(function() {
		chosenCategories.empty();
		showChosenCategories();
	});

	showChosenCategories();
});

function showChosenCategories() {
	dropdownCategories.children("option:selected").each(function() {
		selectedCategory = $(this);
		categoryId = selectedCategory.val();
		categoryName = selectedCategory.text().replace(/➔/g, "");

		chosenCategories.append("<span class='badge bg-primary-subtle text-primary rounded-3 fw-semibold fs-2 m-1'>" + categoryName + "</span>")
	});
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
		var maxSize = 102400; // 100 KB

		if (fileSize > maxSize) {

			this.setCustomValidity("File size is too large. You must choose an image less than 100KB!")
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

function exportData(exportLink, fileType) {
	Swal.fire({
		title: 'Export ' + fileType,
		text: 'Are you sure you want to export ' + fileType + '?',
		icon: 'question',
		showCancelButton: true,
		confirmButtonText: 'Yes, export it!',
		cancelButtonText: 'Cancel',
		showLoaderOnConfirm: true,
		preConfirm: () => {
			return new Promise((resolve) => {
				$.ajax({
					url: exportLink,
					type: 'GET',
					success: function(response) {
						resolve();
					},
					error: function(xhr, status) {
						Swal.fire({
							icon: 'error',
							title: 'Oops...',
							text: 'Export ' + fileType + ' failed! Error: ' + xhr.status
						});
					}
				});
			});
		}
	}).then((result) => {
		if (result.isConfirmed) {
			window.location.href = exportLink;
			setTimeout(function() {
				Swal.fire({
					icon: 'success',
					title: 'Success',
					text: "Successfuly " + ' Export ' + fileType
				});
			}, 500);
		}
	});
}

// Event listener untuk tombol ekspor Excel
$('#csvButton').click(function(e) {
	e.preventDefault();
	var exportLink = $(this).attr('href');
	exportData(exportLink, 'CSV');
});

// Event listener untuk tombol ekspor Excel
$('#excelButton').click(function(e) {
	e.preventDefault();
	var exportLink = $(this).attr('href');
	exportData(exportLink, 'Excel');
});

// Event listener untuk tombol ekspor PDF
$('#pdfButton').click(function(e) {
	e.preventDefault();
	var exportLink = $(this).attr('href');
	exportData(exportLink, 'PDF');
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