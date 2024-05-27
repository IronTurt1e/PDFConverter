import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReadPDF {
    public static void main(String[] args) {
        File pdfFolder = new File("C:\\Users\\71940\\OneDrive\\Документы\\PDFSamples");
        File[] pdfFiles = pdfFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (pdfFiles != null) {
            for (File pdfFile : pdfFiles) {
                convertPdfToTxt(pdfFile);
            }
        }
    }

    private static void convertPdfToTxt(File pdfFile) {
        PDDocument pdfDocument = null;
        try {
            pdfDocument = PDDocument.load(pdfFile);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(pdfDocument);

            String txtFilePath = pdfFile.getAbsolutePath().replace(".pdf", ".txt");
            try (FileWriter writer = new FileWriter(txtFilePath)) {
                writer.write(text);
            }

            System.out.println("Converted: " + pdfFile.getName() + " to " + txtFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pdfDocument != null) {
                try {
                    pdfDocument.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
