package core.config;

import java.nio.file.Path;

public class ScrapingConfig {
    public static final String BASE_PATH = "src/main/java/files";
    public static final Path PDF_DIR = Path.of(BASE_PATH, "pdfs");
    public static final Path CSV_FILE = Path.of(BASE_PATH, "csv", "tabela.csv");
    public static final Path DATA_DIR = Path.of(BASE_PATH, "mysql_files", "csvPath");

    public static final String ROL_URL = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
    public static final String DATA_URL = "https://dadosabertos.ans.gov.br/FTP/PDA/demonstracoes_contabeis/";
    public static final String OPERATORS_URL = "https://dadosabertos.ans.gov.br/FTP/PDA/operadoras_de_plano_de_saude_ativas/";

    public static final String PDF_SELECTOR = "a.internal-link[href*=Anexo_][href$=.pdf]";
    public static final String ZIP_SELECTOR = "[href$=.zip]";
    public static final String CSV_SELECTOR = "[href$=.csv]";
}
