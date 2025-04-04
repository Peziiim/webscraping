package core;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import exceptions.DownloadException;
import exceptions.ScraperException;

public class Scraping {
    private static final Logger LOGGER = Logger.getLogger(Scraping.class.getName());
    private static final int DEFAULT_TIMEOUT = 30000;

    public List<Path> scraping(String url, Path savePath, String cssQuery) {

        LOGGER.info("Extraindo dados em " + url);
        List<Path> downloadedFiles = new ArrayList<>();

        try {
            Files.createDirectories(savePath);

            final Document doc = Jsoup.connect(url)
                    .timeout(DEFAULT_TIMEOUT)
                    .userAgent("Mozilla/5.0")
                    .get();

            final Elements elements = doc.select(cssQuery);
            LOGGER.info("Encontrados " + elements.size() + " links para download");

            for (Element element : elements) {
                final String fileUrl = element.absUrl("href");
                if (fileUrl.isEmpty()) {
                    LOGGER.warning("Link vazio encontrado, pulando...");
                    continue;
                }

                final String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
                final Path filePath = savePath.resolve(fileName);

                try {
                    downloadFile(fileUrl, filePath);
                    downloadedFiles.add(filePath);
                } catch (DownloadException e) {
                    LOGGER.log(Level.WARNING, "Falha ao baixar arquivo: " + fileUrl + ": " + e.getMessage(), e);
                }
            }
            return downloadedFiles;

        } catch (IOException e) {
            throw new ScraperException("Erro ao acessar a URL: " + url, e);
        }
    }

    private Path downloadFile(String fileUrl, Path savePath) throws DownloadException {
        LOGGER.info("Baixando arquivo: " + fileUrl);

        try {
            Files.createDirectories(savePath.getParent());

            Files.copy(
                    new URI(fileUrl).toURL().openStream(),
                    savePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            LOGGER.info("Download concluído: " + savePath.getFileName());
            return savePath;
        } catch (URISyntaxException e) {
            throw new DownloadException("URI inválida: " + fileUrl, e);
        } catch (IOException e) {
            throw new DownloadException("Erro de E/S ao baixar arquivo: " + fileUrl, e);
        }
    }
}