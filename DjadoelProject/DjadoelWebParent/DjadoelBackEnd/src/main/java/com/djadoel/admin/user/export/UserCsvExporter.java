package com.djadoel.admin.user.export;

import java.io.IOException;
import java.util.List;

import com.djadoel.common.entity.User;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import jakarta.servlet.http.HttpServletResponse;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Kelas utilitas untuk mengekspor daftar pengguna ke dalam format CSV.
// Kode:
//  - export(List<User> listUsers, HttpServletResponse response): Metode ini digunakan untuk mengekspor daftar pengguna ke dalam format CSV.
//  - listUsers: Parameter berisi daftar pengguna yang akan diekspor.
//  - response: Parameter yang mewakili objek HttpServletResponse untuk mengirim tanggapan HTTP.

public class UserCsvExporter extends AbstractExporter {

	// Metode export() digunakan untuk mengekspor daftar pengguna ke dalam format
	// CSV.
	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {

		// Mengatur header tanggapan HTTP untuk tipe konten dan ekstensi file.
		super.setResponseHeader(response, "text/csv", ".csv");

		// Membuat CsvBeanWriter untuk menulis data CSV ke HttpServletResponse.
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		// Mendefinisikan header kolom CSV.
		String[] csvHeader = { "User ID", "Email", "First Name", "Last Name", "Roles", "Enabled" };

		// Mendefinisikan pemetaan kolom antara atribut objek User dan header CSV.
		String[] fieldMapping = { "id", "email", "firstName", "lastName", "roles", "enabled" };

		// Menulis header kolom ke file CSV.
		csvWriter.writeHeader(csvHeader);

		// Menulis data pengguna ke file CSV berdasarkan pemetaan kolom.
		for (User user : listUsers) {
			csvWriter.write(user, fieldMapping);
		}

		// Menutup CsvBeanWriter untuk menyelesaikan proses pengeksporan.
		csvWriter.close();
	}
}
