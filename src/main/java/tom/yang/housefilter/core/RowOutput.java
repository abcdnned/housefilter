package tom.yang.housefilter.core;

import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class RowOutput {

	public String printHouseRows(List<HouseRow> rows) throws IOException {
		StringBuilder sb = new StringBuilder();
		CSVPrinter printer = new CSVPrinter(sb, CSVFormat.EXCEL);
		for (HouseRow row : rows) {
			for (HouseCell cell : row.getCells()) {
				printer.print(cell.getValue());
			}
			printer.println();
		}
		return sb.toString();
	}
}
