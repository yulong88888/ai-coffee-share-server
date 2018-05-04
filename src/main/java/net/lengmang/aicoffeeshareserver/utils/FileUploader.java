package net.lengmang.aicoffeeshareserver.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class FileUploader {

    private static FileUploader fileUploader = null;

    @Value("${isDev}")
    private boolean isDev;

    private FileUploader() {
    }

    @PostConstruct
    private void init() {
        fileUploader = this;
        fileUploader.isDev = isDev;
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

    private void delFile(String fileName) {
        File file = new File(getFilePath() + "/" + fileName);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
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

    public static void uploadFile(byte[] file, String fileName) throws Exception {
        if (fileUploader == null) {
            fileUploader = new FileUploader();
        }
        fileUploader.upload(file, fileName);
    }

    public static void deleteFile(String needDelIconFile) {
        if (fileUploader == null) {
            fileUploader = new FileUploader();
        }
        fileUploader.delFile(needDelIconFile);
    }
}
