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

        String[] parseString = getParseString(fileContent);

        String name = getValueByKey(parseString, "Name");
        String email = getValueByKey(parseString, "Email");
        Long phone = Long.parseLong(getValueByKey(parseString, "Phone"));
        Integer age = Integer.parseInt(getValueByKey(parseString, "Age"));


        return new Profile(name, age, email, phone);
    }


    private String[] getParseString(String inputString) {
        if (inputString != null) {
            return inputString.split(System.lineSeparator());
        }
        String[] empty = new String[]{"empty"};
        return empty;

    }

    private String getValueByKey(String[] inputString, String key) {
        if (inputString != null && key != null) {
            for (int i = 0; i < inputString.length; i++) {
                if (inputString[i].contains(key)) {
                    return inputString[i].substring(key.length() + 2);
                }
            }
            return key;
        }
        return "error";


    }
}
