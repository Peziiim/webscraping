package pdf_extract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import compactor.Compactor;


public class PDF {
    private Compactor compactor = new Compactor();

    public void extractPDF(String pdfPath, String locationCSV, String locationCSVZip) throws IOException {
        String filePath = "src/main/java/files/csv/Teste_Eduardo.zip";

        PDDocument document = Loader
                            .loadPDF(new File(pdfPath));
        

        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String [] texts = pdfTextStripper.getText(document)
                                            .replace("OD", "SEG. ODONTOLOGICA")
                                            .replace("AMB", "SEG AMBULATÓRIO")
                                            .split(",");


        StringBuilder sb = new StringBuilder();
        
        for(String text : texts){    

            sb.append(text);
        }
        
            FileWriter writer = new FileWriter(locationCSV);
            writer.write(sb.toString());
            writer.close();

            FileOutputStream fos = new FileOutputStream(locationCSVZip);
            ZipOutputStream zos = new ZipOutputStream(fos);

            File csvFile = new File(locationCSV);
            ZipEntry zipEntry = new ZipEntry(("Teste_Eduardo.zip"));
            zos.putNextEntry(zipEntry);

            byte[] bytes = Files.readAllBytes(csvFile.toPath());
            zos.write(bytes, 0,bytes.length);
            zos.closeEntry();

            document.close();
    }

        
    }
    
