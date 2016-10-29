package ru.garf.ff.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import ru.garf.ff.objects.User;

public class PdfBuilder extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

		User user = (User) model.get("user");

		doc.add(new Paragraph(user.getName()));
		doc.add(new Paragraph(user.getPassword()));

	}

}
