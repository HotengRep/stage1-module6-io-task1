package com.epam.mjc.io;

import java.io.File;
import java.io.IOException;


public class FileReader {

    public Profile getDataFromFile(File file) {

        String fileContent = null;
        int symbol;
        try (java.io.FileReader inputStream = new java.io.FileReader(file.getAbsolutePath())) {
            while ((symbol = inputStream.read()) != -1) {
                fileContent = fileContent == null ? String.valueOf((char) symbol) : fileContent + (char) symbol;
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }

        String[] parseString = fileContent.split(System.lineSeparator());
        ;

        String name = getValueByKey(parseString, "Name");
        String email = getValueByKey(parseString, "Email");
        Long phone = Long.parseLong(getValueByKey(parseString, "Phone"));
        Integer age = Integer.parseInt(getValueByKey(parseString, "Age"));


        return new Profile(name, age, email, phone);
    }


    private String getValueByKey(String[] inputString, String key) {
        if (inputString == null || key == null) {
            return "error";
        } else {
            for (int i = 0; i < inputString.length; i++) {
                if (inputString[i].contains(key)) {
                    return inputString[i].substring(key.length() + 2);
                }
            }
            return key;

        }
    }
}

