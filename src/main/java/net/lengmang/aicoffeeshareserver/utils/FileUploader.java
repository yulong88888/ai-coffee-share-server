package net.lengmang.aicoffeeshareserver.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;

public class FileUploader {

    private boolean isDev;

    private FileUploader(boolean isDev) {
        this.isDev = isDev;
    }

    private void upload(byte[] file, String fileName) throws Exception {
        String filePath = getFilePath();
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    private String getFilePath() {
        String basePath = System.getProperty("user.dir");
        if (isDev) {
            basePath = basePath + "\\target\\";
        } else {
            int flag = basePath.lastIndexOf("\\");
            basePath = basePath.substring(0, flag) + "\\webapps\\";
        }
        String resultPath = basePath + "ai-coffee-share-static-resources\\";
        System.out.println(resultPath);
        return resultPath;
    }

    private static FileUploader fileUploader = null;

    public static void uploadFile(byte[] file, String fileName, boolean isDev) throws Exception {
        if (fileUploader == null) {
            fileUploader = new FileUploader(isDev);
        }
        fileUploader.upload(file, fileName);
    }
}
