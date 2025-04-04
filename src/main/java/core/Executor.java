package core;

import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static core.config.ScrapingConfig.*;

public class Executor {
       private static final Logger LOGGER = Logger.getLogger(Executor.class.getName());
    
    private final Scraping scraper;
    private final Compactor compactor;
    private final PDF pdfProcessor;
    
    public Executor(Scraping scraper, Compactor compactor, PDF pdfProcessor) {
        this.scraper = scraper;
        this.compactor = compactor;
        this.pdfProcessor = pdfProcessor;
    }
    
    public void execute() {
        try {
            downloadRolAttachments();
            downloadFinancialData();
            processPdfToCSV();
            LOGGER.info("Processamento concluído com sucesso");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro durante o processamento: " + e.getMessage(), e);
        }
    }
    
    private void downloadRolAttachments() {
        LOGGER.info("Iniciando download dos anexos do ROL");
        List<Path> rolFiles = scraper.scraping(
                ROL_URL,
                PDF_DIR,
                PDF_SELECTOR
        );
        LOGGER.info("Baixados " + rolFiles.size() + " arquivos PDF");
        Path zipPath = compactor.toZipPDF(
                PDF_DIR.toString(),
                PDF_DIR + ".zip"
        );
        LOGGER.info("Arquivos compactados em: " + zipPath);
    }
    
    private void downloadFinancialData() {
        LOGGER.info("Baixando dados financeiros de 2023");
        scraper.scraping(
                DATA_URL + "/2023",
                DATA_DIR,
                ZIP_SELECTOR
        );

        LOGGER.info("Baixando dados financeiros de 2024");
        scraper.scraping(
                DATA_URL + "/2024",
                DATA_DIR,
                ZIP_SELECTOR
        );
        
        LOGGER.info("Baixando dados de operadoras");
        scraper.scraping(
                OPERATORS_URL,
                DATA_DIR,
                CSV_SELECTOR
        );
    }
    
    private void processPdfToCSV() {
        LOGGER.info("Processando PDF para CSV");
        String pdfFileName = "Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";
        Path pdfFile = PDF_DIR.resolve(pdfFileName);
        
        pdfProcessor.extractPDF(
                pdfFile.toString(),
                CSV_FILE.toString(),
                CSV_FILE.toString()
        );
        
        LOGGER.info("PDF processado com sucesso");
    }
}
