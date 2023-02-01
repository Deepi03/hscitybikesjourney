package com.assingment.hscitybikesjourney.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

/**
 * read csv file and add it to the list
 * 
 * @return list of list of strings
 */
@Service
public class CSVHelper {

    List<List<String>> records = new ArrayList<>();

    public List<List<String>> readFile() {
        try (Scanner scanner = new Scanner(new File(
                "/Users/vijaip/Documents/interview-assignments/java /solita/hscitybikesjourney/src/main/resources/static/assets/my_file.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(getStringFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found ::" + e.getLocalizedMessage());
        }
        return records;
    }

    private List<String> getStringFromLine(String line) {
        String[] tokenArray = line.split("\\s*,\\s*");
        return Arrays.asList(tokenArray);
    }

}
