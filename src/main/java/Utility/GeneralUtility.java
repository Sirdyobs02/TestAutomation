package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GeneralUtility {

	public Object[][] getTestData(String filename) {
		XSSFWorkbook workbook = null;
		Sheet sheet;
		String testDataPath = System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\";
		FileInputStream file = null;
		try {
			file = new FileInputStream(testDataPath + filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = workbook.getSheet("Sheet1");
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int row = 0; row < sheet.getLastRowNum(); row++) {
			for (int col = 0; col < sheet.getRow(row).getLastCellNum(); col++) {
				if (sheet.getRow(row + 1).getCell(col).toString() != null)
					data[row][col] = sheet.getRow(row + 1).getCell(col).toString();
			}
		}
		return data;
	}

	public Object[][] getJSonData(String filename) {
		JSONParser parser = new org.json.simple.parser.JSONParser();
		Map<String, JSONObject> dataMap = new HashMap<String, JSONObject>();
		Iterator entriesIterator = null;
		try {
			JSONObject contentObj = (JSONObject) parser.parse(new FileReader(
					new File(System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\" + filename)));
			JSONObject dataObj = (JSONObject) contentObj.get("Transaction");
			dataObj.keySet().forEach(key -> {
				dataMap.put(String.valueOf(key), (JSONObject) dataObj.get(key));
			});
			Set entries = dataObj.entrySet();
			entriesIterator = entries.iterator();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Object[][] arr = new Object[dataMap.size()][2];
		int i = 0;
		while (entriesIterator.hasNext()) {
			Map.Entry mapping = (Map.Entry) entriesIterator.next();
			arr[i][0] = mapping.getKey();
			arr[i][1] = mapping.getValue();
			i++;
		}

		return arr;
	}

	public String get_current_datetime() {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		Date newDate = subtractDays(date, 2);
		return formatter.format(newDate).toString();
	}

	public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
		return cal.getTime();
	}
}
