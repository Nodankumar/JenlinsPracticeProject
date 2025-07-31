package org.grogu.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {

    public static List<Map<String, String>> getXlsxData(String filePath) throws InvalidFormatException, IOException {

        System.out.println("Filepath: " + filePath);

        List<Map<String, String>> loginData = new ArrayList<>();

         File workBookFile = new File(System.getProperty("user.dir")+"//src//test//resources//"+filePath);
          if (!workBookFile.exists()) {
              throw new IOException("Excel file not found at: " + filePath);
          }

        InputStream fin = ClassLoader.getSystemResourceAsStream(filePath);
        if(fin==null) {
            throw new IOException("Excel file not found at: " + filePath);
        }


        try (XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fin)) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            int rows = xssfSheet.getLastRowNum();

            for (int i = 0; i <= rows; i++) {
                Map<String, String> data = new HashMap<>();
                data.put("username", xssfSheet.getRow(i).getCell(0).getStringCellValue());
                data.put("password", xssfSheet.getRow(i).getCell(1).getStringCellValue());
                loginData.add(data);
            }
        }

        return loginData;
    }
}

