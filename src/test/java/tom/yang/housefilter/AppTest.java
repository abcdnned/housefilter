package tom.yang.housefilter;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class AppTest {
	public static void main(final String[] args) throws EncryptedDocumentException, FileNotFoundException, InvalidFormatException, ParseException, IOException {
		App.main(new String[] { "-o", "C:/test/test.csv", "-c", "C:/test/conf.txt", "-i", "C:/test/in.xls" });
	}
}
