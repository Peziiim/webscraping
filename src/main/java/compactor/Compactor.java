package compactor;

import java.io.*;
import java.util.zip.*;

public class Compactor {

    private static final int BUFFER_KB = 4096;

    public void toZipPDF(String folderPath, String zipPath) throws IOException {
        
        File folder = new File(folderPath);
 
            if (!folder.exists()) {
                System.out.println("Erro: pasta não encontrada ");
         
            } else {

                File zipFile = new File(zipPath);
                zipFile.getParentFile().mkdirs();
    
    
                FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
                ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
                zipFolder(folder, folder.getName(), zipOutputStream); }  
    }



    private void zipFolder(File folder, String basePath, ZipOutputStream zipOutputStream) throws IOException {
       
        File[] files = folder.listFiles();
        if (files == null) {return;} 
        byte[] buffer = new byte[BUFFER_KB];

        for (File file : files) {
            String zipEntryName = basePath + "/" + file.getName();

            if (file.isDirectory()) {
                zipFolder(file, zipEntryName, zipOutputStream);

            } else 
                {

                    FileInputStream fileInputStream = new FileInputStream(file);
                    BufferedInputStream origin = new BufferedInputStream(fileInputStream, BUFFER_KB);
                    ZipEntry entry = new ZipEntry(zipEntryName);
                    zipOutputStream.putNextEntry(entry);


                    int count;
                    while ((count = origin.read(buffer)) != -1) {
                        zipOutputStream.write(buffer, 0, count);
                    }

                    origin.close();
                    zipOutputStream.closeEntry();

                }
            }

}

}