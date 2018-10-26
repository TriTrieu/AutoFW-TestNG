package com.bosch.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {


    public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    public static void zipFile(String sourceFile, String newFileName){
        try {
            FileOutputStream fos = new FileOutputStream(newFileName);
            ZipOutputStream zipOut = new ZipOutputStream(fos);
            File fileToZip = new File(sourceFile);
            zipFile(fileToZip, fileToZip.getName(), zipOut);
            zipOut.close();
            fos.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void deleteFiles(File folder) {
        try {
            File[] files = folder.listFiles();
            for(File file: files){
                if(file.isFile()){
                    file.delete();
                }else if(file.isDirectory()) {
                    deleteFiles(file);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteFiles(File folder, String fileName) {
        try {
            File[] files = folder.listFiles();
            for(File file: files){
                if(file.isFile() && file.getName().equalsIgnoreCase(fileName)){
                    file.delete();
                    return;
                }else if(file.isDirectory()) {
                    deleteFiles(file,fileName);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
