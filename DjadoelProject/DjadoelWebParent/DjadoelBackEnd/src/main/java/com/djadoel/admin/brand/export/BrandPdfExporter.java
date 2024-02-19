package com.djadoel.admin.brand.export;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.djadoel.common.entity.Brand;

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

public class BrandPdfExporter extends AbstractExporter {

	public void writeTableHeader(PdfPTable table) {

		PdfPCell cell = new PdfPCell();

		cell.setBackgroundColor(Color.decode("#FFAE1F"));

		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.COURIER);

		font.setColor(Color.decode("#FFFFFF"));

		cell.setPhrase(new Phrase("Brand ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Logo", font));
		table.addCell(cell);
	}

	public void writeTableData(PdfPTable table, List<Brand> listBrands) {
		for (Brand brand : listBrands) {
			table.addCell(String.valueOf(brand.getId()));
			table.addCell(brand.getName());
			table.addCell(brand.getLogo());
		}
	}

	public void export(List<Brand> listBrands, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "aplication/pdf", ".pdf", "brands_");

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.COURIER_BOLD, 16, Color.black);

		Paragraph paragraph = new Paragraph("List of Brand :", font);

		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(3);

		table.setWidthPercentage(100f);

		table.setSpacingBefore(10);

		table.setWidths(new float[] { 1.0f, 3.5f, 3.5f });

		writeTableHeader(table);

		writeTableData(table, listBrands);

		document.add(table);

		document.close();
	}
}
