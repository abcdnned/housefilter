package tom.yang.housefilter.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tom.yang.housefilter.rowfilter.IHouseRowFilter;

public class ConfigLoader {

	public static final String FILTER = "FILTER";

	public static final String WEIGHT = "WEIGHT";

	public WeightConfig load(File f) throws FileNotFoundException, IOException {
		WeightConfig config = new WeightConfig();
		List<IHouseRowFilter> filters = new ArrayList<IHouseRowFilter>();
		config.setFilters(filters);

		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
			reader.lines().forEach(line -> {
				if (line.startsWith(FILTER)) {
					filters.add(parseFilterLine(line));
				} else if (line.startsWith(WEIGHT)) {

				} else {

				}
			});
		}
		return config;
	}

	private IHouseRowFilter parseFilterLine(String line) {
		return null;
	}
}
