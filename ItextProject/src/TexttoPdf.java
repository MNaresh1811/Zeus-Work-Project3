
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Table;
import com.itextpdf.test.annotations.WrapToTest;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@WrapToTest
	public class TexttoPdf {
	 
	    public static final String TEXT
	        = "D:/Zeus2.txt";
	    public static final String DEST
	        = "D:/text2pdf.pdf";
	 
	    public static void main(String[] args) throws DocumentException, IOException {
	    	File file = new File(DEST);
	        file.getParentFile().mkdirs();
	        new TexttoPdf().createPdf(DEST);
	    }
	 
	    public  void createPdf(String dest)
		throws DocumentException, IOException {
	       Document document = new Document();
	        // PdfWriter writer = new PdfWriter(DEST);
	      //   PdfDocument pdf = new PdfDocument(writer);    
	       //  Document document = new Document(pdf, PageSize.A4.rotate());
	         PdfWriter.getInstance(document, new FileOutputStream(dest));
	        
	        document.open();
	        document.setMargins(20, 20, 20, 20);
	      //  PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
	     //  PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
	        Table table = new Table(new float[]{1,1,1,1,1,1,1,1,1,1,1});
	        table.setWidthPercent(100);
	        BufferedReader br = new BufferedReader(new FileReader(TEXT));
	        String line = br.readLine();
	        Paragraph p;
	        
	        Font normal = new Font(FontFamily.TIMES_ROMAN, 12);
	        Font bold1 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	        process(table, line, bold1, true);
	        boolean title = true;
	        while ((line = br.readLine()) != null) {
	            p = new Paragraph(line, title ? bold1 : normal);
	            p.setAlignment(Element.ALIGN_JUSTIFIED);
	            title = line.isEmpty();
	          process(table, line, bold1, false);
	            document.add(p);
	        }
	        document.close();
	    }
	    
	    
	    public void process(Table table , String line , Font font1 , boolean isHeader) {
            StringTokenizer tokenizer = new StringTokenizer(line, " ");
            while (tokenizer.hasMoreTokens()) {
                if (isHeader) {
                    table.addHeaderCell(
                        new Cell().add((IBlockElement) new Paragraph(tokenizer.nextToken())));
                } else {
                    table.addCell(
                        new Cell().add((IBlockElement) new Paragraph(tokenizer.nextToken())));
                }
            }
        }
	        
	        
	    }

	
