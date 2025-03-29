package main;

import java.nio.file.Path;

import compactor.Compactor;
import scraping.Scraping;

public class App {
   public static void main( String[] args ){
        String path = "src/main/java/files";
        Scraping scraping = new Scraping();
        Compactor comp = new Compactor();
        
        scraping.scraping("https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos", Path.of(path));

        comp.toZip(path, path);
   
        System.out.println("hello world");
    }
}
