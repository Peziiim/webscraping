package compactor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Compactor {

    static final int BUFFER_KB = 4096;

    public void toZip(String arquiveEntry, String arquiveOut) {

        int count;
        byte[] data = new byte[BUFFER_KB];

        BufferedInputStream origin = null;
        FileInputStream entryStream = null;
        FileOutputStream destiny = null;
        ZipOutputStream out = null;
        ZipEntry entry = null;

        try {

            destiny = new FileOutputStream(new File(arquiveOut));
            out = new ZipOutputStream(new BufferedOutputStream(destiny));
            File file = new File(arquiveEntry);
            entryStream = new FileInputStream(file);
            origin = new BufferedInputStream(entryStream, BUFFER_KB);
            entry = new ZipEntry(file.getName());
            out.putNextEntry(entry);

            while ((count = origin.read(data, 0, BUFFER_KB)) != -1) {
            out.write(data, 0, count);
            }

            origin.close();
            out.close();

        } catch (IOException e) {
            System.out.println("Erro tentando encontrar o arquivo: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
