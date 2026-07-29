package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utilidad simple para leer datos desde CSV o XLSX.
 * Añadida para pruebas rápidas. No cubre formatos complejos ni tipos avanzados.
 */
public class ExcelReaderUtil {

    public static List<Map<String, String>> readCsv(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        List<Map<String, String>> rows = new ArrayList<>();
        if (lines.isEmpty()) return rows;
        String[] headers = lines.get(0).split(",");
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",", -1);
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < headers.length; j++) {
                String key = headers[j].trim();
                String val = j < parts.length ? parts[j].trim() : "";
                map.put(key, val);
            }
            rows.add(map);
        }
        return rows;
    }

    public static List<Map<String, String>> readXlsx(Path path) throws IOException {
        List<Map<String, String>> rows = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(path.toFile());
                XSSFWorkbook wb = new XSSFWorkbook(fis)) {
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> it = sheet.iterator();
            if (!it.hasNext()) return rows;
            Row headerRow = it.next();
            List<String> headers = new ArrayList<>();
            for (Cell c : headerRow) {
                headers.add(c.getStringCellValue().trim());
            }
            while (it.hasNext()) {
                Row r = it.next();
                Map<String, String> map = new HashMap<>();
                for (int ci = 0; ci < headers.size(); ci++) {
                    Cell cell = r.getCell(ci);
                    String val = "";
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                val = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                val = String.valueOf(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                val = String.valueOf(cell.getBooleanCellValue());
                                break;
                            default:
                                val = cell.toString();
                        }
                    }
                    map.put(headers.get(ci), val.trim());
                }
                rows.add(map);
            }
        }
        return rows;
    }

    // Método de ejemplo: leer CSV o XLSX según la extensión
    public static List<Map<String, String>> readAny(Path path) throws IOException {
        String n = path.getFileName().toString().toLowerCase();
        if (n.endsWith(".csv")) return readCsv(path);
        if (n.endsWith(".xlsx") || n.endsWith(".xls")) return readXlsx(path);
        throw new IllegalArgumentException("Formato no soportado: " + n);
    }

}
