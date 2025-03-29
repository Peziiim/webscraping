package maintest;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

import scraping.Scraping;

public class AppTest {

    private Scraping scraping = new Scraping();
    private  Path savePath = Path.of("src/test/java/filestest/dummy.pdf");

    @Test
    public void testDownloadFile() throws IOException, URISyntaxException {
        
        String fileUrl = "https://www.unirio.br/cchs/eb/arquivos/tccs-acima-de-9/TCC-%20Ana%20Paula%20Oliveira%20Jacques.pdf/at_download/file";
       
        scraping.downloadFile(fileUrl, savePath);
        assertTrue(Files.exists(savePath));

        // Comente a linha abaixo para ver o arquivo guardado na pasta
        Files.deleteIfExists(savePath);
    }
}
