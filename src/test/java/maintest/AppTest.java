package maintest;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

import compactor.Compactor;
import scraping.Scraping;

public class AppTest {
    private Compactor compactor = new Compactor();
    private Scraping scraping = new Scraping();
    private Path path = Path.of("src/test/java/filestest/dummy.pdf");

    @Test
    public void testDownloadFile() throws IOException, URISyntaxException {
        
        String fileUrl = "https://www.unirio.br/cchs/eb/arquivos/tccs-acima-de-9/TCC-%20Ana%20Paula%20Oliveira%20Jacques.pdf/at_download/file";
       
        scraping.downloadFile(fileUrl, path);
        assertTrue(Files.exists(path));

        // Comente a linha abaixo para ver o arquivo guardado na pasta
        Files.deleteIfExists(path);
    }

    @Test
    public void testToZip() throws IOException{
        String stringPath = path.toString();
        compactor.toZipPDF(stringPath, stringPath + ".zip");

        assertTrue(Files.exists(Path.of(path + ".zip")));

         // Comente a linha abaixo para ver o arquivo guardado na pasta
         Files.deleteIfExists(path);
    
    }
}
