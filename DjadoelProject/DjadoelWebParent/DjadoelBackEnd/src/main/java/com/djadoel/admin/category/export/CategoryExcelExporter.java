package com.djadoel.admin.category.export;

import java.io.IOException;
import java.util.List;

import com.djadoel.common.entity.Category;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

public class CategoryExcelExporter extends AbstractExporter {

	private XSSFWorkbook workbook;

	private XSSFSheet sheet;

	public CategoryExcelExporter() {
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {

		sheet = workbook.createSheet("Category");

		XSSFRow row = sheet.createRow(0);

		XSSFCellStyle cellStyle = workbook.createCellStyle();

		XSSFFont font = workbook.createFont();

		font.setBold(true);

		font.setFontHeight(16);

		cellStyle.setFont(font);

		createCell(row, 0, "Category ID", cellStyle);
		createCell(row, 1, "Category Name", cellStyle);
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

	public void writeDataLine(List<Category> listCategories) {
		int rowIndex = 1;

		XSSFCellStyle cellStyle = workbook.createCellStyle();

		XSSFFont font = workbook.createFont();

		font.setFontHeight(14);

		cellStyle.setFont(font);

		for (Category category : listCategories) {
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;
			category.setName(category.getName().replaceAll("➔➔", "    "));
			createCell(row, columnIndex++, category.getId(), cellStyle);
			createCell(row, columnIndex++, category.getName(), cellStyle);
		}
	}

	public void export(List<Category> listCategories, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "application/octet-stream", ".xlsx", "categories_");

		writeHeaderLine();

		writeDataLine(listCategories);

		ServletOutputStream outputStream = response.getOutputStream();

		workbook.write(outputStream);

		workbook.close();

		outputStream.close();
	}
}
