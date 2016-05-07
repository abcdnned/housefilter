package tom.yang.housefilter.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.io.FileUtils;

public class RowOutput {

	public void printHouseRows(final List<HouseRow> rows,final File output,final String[] header) throws IOException {
		try(CSVPrinter printer = new CSVPrinter(new BufferedWriter(new OutputStreamWriter(FileUtils.openOutputStream(output),"GB2312")),CSVFormat.EXCEL.withHeader(header))){
			for (final HouseRow row : rows) {
				for (final HouseCell cell : row.getCells()) {
					printer.print(cell.getValue());
				}
				printer.println();
			}
		}
	}
}
