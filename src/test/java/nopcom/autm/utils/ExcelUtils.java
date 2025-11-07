package nopcom.autm.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static List<String[]> readExcel(String resourcePath) {
        List<String[]> rows = new ArrayList<>();
        try (InputStream is = ExcelUtils.class.getClassLoader().getResourceAsStream(resourcePath);
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            boolean firstRow = true;
            for (Row row : sheet) {
                if (firstRow) { firstRow = false; continue; } // skip header
                List<String> cells = new ArrayList<>();
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    Cell c = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    c.setCellType(CellType.STRING);
                    cells.add(c.getStringCellValue());
                }
                rows.add(cells.toArray(new String[0]));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to read excel file: " + resourcePath, e);
        }
        return rows;
    }
}
