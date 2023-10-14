package com.djadoel.admin.category.export;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.djadoel.common.entity.Category;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

public class CategoryPdfExporter extends AbstractExporter {

	public void writeTableHeader(PdfPTable table) {

		PdfPCell cell = new PdfPCell();

		cell.setBackgroundColor(Color.decode("#FFAE1F"));

		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.COURIER);

		font.setColor(Color.decode("#FFFFFF"));

		cell.setPhrase(new Phrase("Category ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Category Name", font));
		table.addCell(cell);
	}

	public void writeTableData(PdfPTable table, List<Category> listCategories) {
		for (Category category : listCategories) {
			category.setName(category.getName().replaceAll("➔➔", "    "));
			table.addCell(String.valueOf(category.getId()));
			table.addCell(category.getName());
		}
	}

	public void export(List<Category> listCategories, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "aplication/pdf", ".pdf", "categories_");

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 16, Color.black);

		Paragraph paragraph = new Paragraph("List of Category :", font);

		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(2);

		table.setWidthPercentage(100f);

		table.setSpacingBefore(10);

		table.setWidths(new float[] { 1.0f, 3.5f});

		writeTableHeader(table);

		writeTableData(table, listCategories);

		document.add(table);

		document.close();
	}
}
