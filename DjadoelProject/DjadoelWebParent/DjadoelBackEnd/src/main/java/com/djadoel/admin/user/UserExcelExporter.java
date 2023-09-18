package com.djadoel.admin.user;

import java.io.IOException;
import java.util.List;

import com.djadoel.common.entity.User;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Kelas utilitas untuk mengekspor daftar pengguna ke dalam format Excel (XLSX).
// Kode:
//  - UserExcelExporter(): Konstruktor untuk inisialisasi objek XSSFWorkbook.
//  - writeHeaderLine(): Metode untuk menulis baris header dalam lembar kerja Excel.
//  - createCell(XSSFRow row, int columnIndex, Object value, CellStyle style): Metode untuk membuat sel dalam baris dengan nilai dan gaya tertentu.
//  - writeDataLine(List<User> listUsers): Metode untuk menulis data pengguna ke dalam lembar kerja Excel.
//  - export(List<User> listUsers, HttpServletResponse response): Metode untuk mengekspor data pengguna ke dalam file Excel (XLSX).

public class UserExcelExporter extends AbstractExporter {

	private XSSFWorkbook workbook;

	private XSSFSheet sheet;

	public UserExcelExporter() {
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Users");

		XSSFRow row = sheet.createRow(0);

		XSSFCellStyle cellStyle = workbook.createCellStyle();

		XSSFFont font = workbook.createFont();

		font.setBold(true);

		font.setFontHeight(16);

		cellStyle.setFont(font);

		createCell(row, 0, "User Id", cellStyle);
		createCell(row, 1, "Email", cellStyle);
		createCell(row, 2, "Fist Name", cellStyle);
		createCell(row, 3, "Last Name", cellStyle);
		createCell(row, 4, "Roles", cellStyle);
		createCell(row, 5, "Enabled", cellStyle);
	}

	private void createCell(XSSFRow row, int columnIndex, Object value, CellStyle style) {
		XSSFCell cell = row.createCell(columnIndex);

		sheet.autoSizeColumn(columnIndex);

		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}

		cell.setCellStyle(style);
	}

	public void writeDataLine(List<User> listUsers) {
		int rowIndex = 1;

		XSSFCellStyle cellStyle = workbook.createCellStyle();

		XSSFFont font = workbook.createFont();

		font.setFontHeight(14);

		cellStyle.setFont(font);

		for (User user : listUsers) {
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;
			createCell(row, columnIndex++, user.getId(), cellStyle);
			createCell(row, columnIndex++, user.getEmail(), cellStyle);
			createCell(row, columnIndex++, user.getFirstName(), cellStyle);
			createCell(row, columnIndex++, user.getLastName(), cellStyle);
			createCell(row, columnIndex++, user.getRoles().toString(), cellStyle);
			createCell(row, columnIndex++, user.isEnabled(), cellStyle);
		}
	}

	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "application/octet-stream", ".xlsx");

		writeHeaderLine();

		writeDataLine(listUsers);

		ServletOutputStream outputStream = response.getOutputStream();

		workbook.write(outputStream);

		workbook.close();

		outputStream.close();
	}
}