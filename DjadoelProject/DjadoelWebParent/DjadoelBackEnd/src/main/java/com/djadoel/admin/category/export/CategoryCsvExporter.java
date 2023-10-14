package com.djadoel.admin.category.export;

import java.io.IOException;
import java.util.List;

import com.djadoel.common.entity.Category;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import jakarta.servlet.http.HttpServletResponse;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

public class CategoryCsvExporter extends AbstractExporter {

	// Metode export() digunakan untuk mengekspor daftar category ke dalam format
	// CSV.
	public void export(List<Category> listCategories, HttpServletResponse response) throws IOException {

		// Mengatur header tanggapan HTTP untuk tipe konten dan ekstensi file.
		super.setResponseHeader(response, "text/csv", ".csv", "categories_");

		// Membuat CsvBeanWriter untuk menulis data CSV ke HttpServletResponse.
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		// Mendefinisikan header kolom CSV.
		String[] csvHeader = { "Category ID", "Category Name"};

		// Mendefinisikan pemetaan kolom antara atribut objek Category dan Header CSV.
		String[] fieldMapping = { "id", "name"};

		// Menulis header kolom ke file CSV.
		csvWriter.writeHeader(csvHeader);

		// Menulis data category ke file CSV berdasarkan pemetaan kolom.
		for (Category category : listCategories) {
			category.setName(category.getName().replaceAll("➔➔", "    "));
			csvWriter.write(category, fieldMapping);
		}

		// Menutup CsvBeanWriter untuk menyelesaikan proses pengeksporan.
		csvWriter.close();
	}
}
