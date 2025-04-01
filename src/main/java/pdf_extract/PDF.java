package pdf_extract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDF {

    public void extractPDF(String pdfPath, String locationCSV, String locationCSVZip) {
        System.out.println("Extraindo PDF ");

        
        StringBuilder sb = new StringBuilder();

        try {
            PDDocument document = Loader
            .loadPDF(new File(pdfPath));


            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String [] texts = pdfTextStripper.getText(document)
                            .replace("OD", "SEG. ODONTOLOGICA")
                            .replace("AMB", "SEG AMBULATÓRIO")
                            .split("\t");

        for(String text : texts){       
        sb.append(text);

        }
        document.close();

    } catch (IOException e){
        System.out.println("PDF não encontrado: "); 
        e.printStackTrace();  
    }



    try {

        FileWriter writer = new FileWriter(locationCSV);
        writer.write(sb.toString());
        writer.close();

        String [] regex = locationCSV.split("/");
        String name = regex[regex.length - 1];
        
        FileOutputStream fos = new FileOutputStream(locationCSVZip.replace( name , "Teste_Eduardo.zip" ));
        ZipOutputStream zos = new ZipOutputStream(fos);
        File csvFile = new File(locationCSV);

        ZipEntry zipEntry = new ZipEntry((csvFile.getName()));

        zos.putNextEntry(zipEntry);

        byte[] bytes = Files.readAllBytes(csvFile.toPath());
        zos.write(bytes, 0,bytes.length);

        zos.closeEntry();   
        zos.close();
        
    } catch (IOException e){
        System.out.println("Arquivo não encontrado: "); 
        e.printStackTrace();  
    }
        
     
 }

}
