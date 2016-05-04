package tom.yang.housefilter.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import tom.yang.housefilter.factory.PluginFactory;
import tom.yang.housefilter.rowfilter.IHouseRowFilter;
import tom.yang.tlog.Tlog;

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
        String filter = line.substring(FILTER.length() + 1, line.length());
        int lenclose = filter.indexOf("(");
        try {
            if (lenclose != -1) {
                String name = filter.substring(0, lenclose);
                int renclose = filter.lastIndexOf(")");
                if (renclose != -1) {
                    String arg = filter.substring(lenclose + 1, renclose);
                    return PluginFactory.FILTER_FACTORY.create(name, arg);
                } else {
                    return PluginFactory.FILTER_FACTORY.create(name, null);
                }
            } else {
                return PluginFactory.FILTER_FACTORY.create(filter, null);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException
                | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            Tlog.fastLog("create filter faild : " + line + e.getMessage());
        }
        return null;
    }
}
