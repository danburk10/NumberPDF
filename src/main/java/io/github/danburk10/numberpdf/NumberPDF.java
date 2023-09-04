/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package io.github.danburk10.numberPDF;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import java.io.File;

public class NumberPDF {

    public static void main(String args[]) throws Exception {

        //JFileChooser jfc = new JFileChooser("user.home" + "\\Downloads");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PDF", "pdf");
        String userhome = System.getProperty("user.home");
        JFileChooser jfc = new JFileChooser(userhome + "\\Downloads");
        jfc.setFileFilter(filter);
        //JFileChooser fileChooser = new JFileChooser(userhome + "\\Downloads");

        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();

            String src = selectedFile.getAbsolutePath();
            new NumberPDF().manipulatePdf(src);
        }

    }

    protected void manipulatePdf(String src) throws Exception {

        String dest = src.substring(0, src.length() - 4) + "-out.pdf"; // remove .ext
        System.out.println("src:" + src);
        System.out.println("dest:" + dest);
        File file = new File(dest);

        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        int numberOfPages = pdfDoc.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {

            // Write aligned text to the specified by parameters point
            doc.showTextAligned(new Paragraph(String.format("page %s of %s", i, numberOfPages)),
                    568, 20, i, TextAlignment.RIGHT, VerticalAlignment.TOP, 0);
        }

        doc.close();
    }
}
