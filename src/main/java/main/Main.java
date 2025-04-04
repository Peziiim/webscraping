package main;

import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

import core.Compactor;
import core.Executor;
import core.PDF;
import core.Scraping;

public class Main {
       
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            configureLogging();

            final Scraping scraper = new Scraping();
            final Compactor compactor = new Compactor();
            final PDF pdfProcessor = new PDF();
            final Executor executor = new Executor(scraper, compactor, pdfProcessor);

            executor.execute();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro durante a execução: " + e.getMessage(), e);
        }
    }

    private static void configureLogging() {
        final Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.INFO);
    }
}
