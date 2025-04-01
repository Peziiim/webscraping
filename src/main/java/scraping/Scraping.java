package scraping;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraping {

    public void scraping(String url, Path savePath, String cssQuery) {
        try {

            Document doc = Jsoup.connect(url).get();
            Elements elements = doc
                    .select(cssQuery);

            for (Element element : elements) {
                String fileUrl = element.absUrl("href");

                String fileName = fileUrl.substring(fileUrl
                        .lastIndexOf('/') + 1);

                Path filePath = savePath.resolve(fileName);

                downloadFile(fileUrl, filePath);
            }

        } catch (IOException | URISyntaxException e) {
            System.out.println("Erro ao acessar a URL: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void downloadFile(String fileUrl, Path savePath) throws IOException, URISyntaxException {

        Files.createDirectories(savePath.getParent());

        Files.copy(new URI(fileUrl).toURL()
                                    .openStream(),
                                    savePath,
                                    StandardCopyOption.REPLACE_EXISTING);

    }

}