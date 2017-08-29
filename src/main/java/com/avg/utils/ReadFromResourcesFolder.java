package com.avg.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.IOUtils;

public class ReadFromResourcesFolder {

    private File file;

    public ReadFromResourcesFolder() {
        super();
    }

    public ReadFromResourcesFolder(String fileName) {
        super();

        this.file = _getFile(fileName);
    }

    public String getContent(String fileName) {

        String result = "";
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private File _getFile(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        return new File(classLoader.getResource(fileName).getFile());

    }

    public File getFile(){
        return this.file;
    }

}