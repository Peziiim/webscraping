package core;

import java.io.*;
import java.util.zip.*;

public class Compactor {

    private static final int BUFFER_KB = 4096;

    public void toZipPDF(String folderPath, String zipPath) {
        System.out.println("Compactando arquivo ");
        
        File folder = new File(folderPath);
 
            if (!folder.exists()) {
                System.out.println("Erro: pasta não encontrada ");
         
            } else {

                File zipFile = new File(zipPath);
                zipFile.getParentFile().mkdirs();
    
                try {
                    
                    FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
                    ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fileOutputStream));
                    
                    zipFolder(folder, folder.getName(), zipOutputStream); 

                } catch(IOException e){
                    System.out.println("Erro ao encontrar o arquivo");
                    e.printStackTrace();
                }
            }  
    }



    private void zipFolder(File folder, String basePath, ZipOutputStream zipOutputStream) {
       
        File[] files = folder.listFiles();
        byte[] buffer = new byte[BUFFER_KB];

        if (files == null) {return;} 

        for (File file : files) {
            String zipEntryName = basePath + "/" + file.getName();

            if (file.isDirectory()) {
                zipFolder(file, zipEntryName, zipOutputStream);

            } else 
                {
                    try {

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

                    } catch (IOException e) {
                        System.out.println("Arquivo não encontrado: ");
                        e.printStackTrace();
                    }

                }
            }

}

}