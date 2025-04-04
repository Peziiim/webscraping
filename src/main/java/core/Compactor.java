package core;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.*;

public class Compactor {
    private static final Logger LOGGER = Logger.getLogger(Compactor.class.getName());
    private static final int BUFFER_SIZE = 4096;

    public Path toZipPDF(String folderPath, String zipPath) {
        LOGGER.info("Compactando diretório: " + folderPath + " para " + zipPath);

        if (folderPath == null || folderPath.isEmpty()) {
            throw new IllegalArgumentException("Caminho da pasta não pode ser nulo ou vazio");
        }
        if (zipPath == null || zipPath.isEmpty()) {
            throw new IllegalArgumentException("Caminho do ZIP não pode ser nulo ou vazio");
        }

        Path folder = Paths.get(folderPath);
        Path zipFile = Paths.get(zipPath);

        if (!Files.exists(folder)) {
            LOGGER.severe("Erro: diretório não encontrado: " + folderPath);
            throw new IllegalArgumentException("Diretório não encontrado: " + folderPath);
        }

        try {
            Files.createDirectories(zipFile.getParent());

            try (FileOutputStream fileOutputStream = new FileOutputStream(zipFile.toFile());
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream)) {

                zipFolder(folder.toFile(), folder.getFileName().toString(), zipOutputStream);
                zipOutputStream.flush();

                LOGGER.info("Compactação concluída com sucesso: " + zipPath);
                return zipFile;
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao compactar arquivos: " + e.getMessage(), e);
            throw new RuntimeException("Falha ao compactar arquivos: " + e.getMessage(), e);
        }
    }

    private void zipFolder(File folder, String basePath, ZipOutputStream zipOutputStream) throws IOException {

        File[] files = folder.listFiles();
        byte[] buffer = new byte[BUFFER_SIZE];

        if (files == null) {
            LOGGER.warning("Diretório vazio ou inacessível: " + folder.getAbsolutePath());
            return;
        }

        for (File file : files) {
            final String zipEntryName = basePath + "/" + file.getName();

            if (file.isDirectory()) {
                zipFolder(file, zipEntryName, zipOutputStream);
                continue;
            }

            LOGGER.fine("Adicionando arquivo ao ZIP: " + file.getAbsolutePath());
            try (FileInputStream fileInputStream = new FileInputStream(file);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, BUFFER_SIZE)) {

                ZipEntry entry = new ZipEntry(zipEntryName);
                zipOutputStream.putNextEntry(entry);

                int count;
                while ((count = bufferedInputStream.read(buffer)) != -1) {
                    zipOutputStream.write(buffer, 0, count);
                }

                zipOutputStream.closeEntry();
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Erro ao adicionar arquivo ao ZIP: " + file.getAbsolutePath(), e);
                throw e;
            }

        }

    }
}