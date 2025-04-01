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
 
    String pathROI = "src/main/java/files/pdfs";
    String pdfPath = "src/main/java/files/pdfs/";
    String csvPath = "src/main/java/files/csv/tabela.csv";
    String csvZip = "src/main/java/files/csv/Teste_Eduardo.zip";
    String dataPath = "src/main/java/files/mysql_files/";
    
    String ROIurl = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
    String dataUrl = "https://dadosabertos.ans.gov.br/FTP/PDA/demonstracoes_contabeis/";
    String operatorsURL = "https://dadosabertos.ans.gov.br/FTP/PDA/operadoras_de_plano_de_saude_ativas/";


    scraping.scraping(ROIurl, Path.of(pathROI), "a.internal-link[href*=Anexo_][href$=.pdf]");

    scraping.scraping(dataUrl + "/2023", Path.of(dataPath + "/csvZip"), "[href$=.zip]");
    scraping.scraping(dataUrl + "/2024", Path.of(dataPath + "/csvZip"), "[href$=.zip]");
    scraping.scraping(operatorsURL, Path.of(dataPath + "/csvZip"), "[href$=.csv]");
    
    try{
        comp.toZipPDF(pathROI, pathROI + ".zip");    
        pdf.extractPDF(pdfPath + "Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf", csvPath, csvZip);

    } catch (IOException e){
        e.printStackTrace();
    }
        System.out.println("The program has finished");
    }
}
