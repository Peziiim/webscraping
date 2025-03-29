package compactor;

import java.io.*;
import java.util.zip.*;

public class Compactor {

    private static final int BUFFER_KB = 4096;

    public void toZip(String folderPath, String zipPath) {
        
        File folder = new File(folderPath);
        
        try {
            if (!folder.exists()) {
                System.out.println("Erro: pasta não encontrada ");
                throw new IOException();
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        File zipFile = new File(zipPath);
        zipFile.getParentFile().mkdirs();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
            zipFolder(folder, folder.getName(), zipOutputStream);

        } catch (IOException e) {
            System.out.println("Falha ao criar arquivo " + e.getMessage());
        }
    }

    private void zipFolder(File folder, String basePath, ZipOutputStream zipOutputStream) throws IOException {
        File[] files = folder.listFiles();
        if (files == null) return; 

        byte[] buffer = new byte[BUFFER_KB];

        for (File file : files) {
            String zipEntryName = basePath + "/" + file.getName();
            if (file.isDirectory()) {
                zipFolder(file, zipEntryName, zipOutputStream); 
            } else {
                try{ 
                    FileInputStream fileInputStream = new FileInputStream(file);
                    BufferedInputStream origin = new BufferedInputStream(fileInputStream, BUFFER_KB);
                    ZipEntry entry = new ZipEntry(zipEntryName);
                    zipOutputStream.putNextEntry(entry);

                    int count;
                    while ((count = origin.read(buffer)) != -1) {
                        zipOutputStream.write(buffer, 0, count);
                    } 

                    } catch (IOException e) {
                     System.out.println("Erro ao compactar a pasta " + e.getMessage());

                    } finally {
                        zipOutputStream.closeEntry();
                    }
            }
        }
    }
}
