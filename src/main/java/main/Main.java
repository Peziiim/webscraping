package main;

import java.io.IOException;
import java.nio.file.Path;

import compactor.Compactor;
import pdf_extract.PDF;
import scraping.Scraping;

public class Main {
   public static void main(String[] args){
       
    Scraping scraping = new Scraping();
    Compactor comp = new Compactor();
    PDF pdf = new PDF();  
 
    String path = "src/main/java/files/pdfs";
    String pdfPath = "src/main/java/files/pdfs/";
    String csvPath = "src/main/java/files/csv/tabela.csv";
    String csvZip = "src/main/java/files/csv/Teste_Eduardo.zip";
    String url = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";

    scraping.scraping(url, Path.of(path));
    
    try{
        comp.toZipPDF(path, path + ".zip");    
        pdf.extractPDF(pdfPath + "Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf", csvPath, csvZip);

    } catch (IOException e){
        e.printStackTrace();
    }
    
    System.out.println("hello world");
    }
}
