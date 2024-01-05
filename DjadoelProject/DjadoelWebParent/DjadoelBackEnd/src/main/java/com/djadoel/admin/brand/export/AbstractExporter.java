package com.djadoel.admin.brand.export;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpServletResponse;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

public class AbstractExporter {

	// Metode ini digunakan untuk mengatur header respons HTTP untuk file yang akan
	// diekspor.
	public void setResponseHeader(HttpServletResponse response, String contentType, String extension, String prefix)
			throws IOException {

		// Mengambil tanggal dan waktu saat ini untuk membuat nama file yang unik.
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

		String timestamp = dateFormatter.format(new Date());

		// Membuat nama file dengan format "prefix_tanggal_waktu.ekstensi".
		String fileName = prefix + timestamp + extension;

		response.setContentType(contentType);

		// Mengatur header respons HTTP untuk mengindikasikan bahwa ini adalah file yang
		// akan diunduh.
		String headerKey = "Content-Disposition";

		String headerValue = "attachment; filename=" + fileName;

		response.setHeader(headerKey, headerValue);
	}
}
