package tom.yang.housefilter.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import tom.yang.housefilter.condition.WeightCondition;
import tom.yang.housefilter.core.WeightItem;
import tom.yang.housefilter.factory.PluginFactory;
import tom.yang.housefilter.rowfilter.IHouseRowFilter;
import tom.yang.housefilter.weight.ICellWeight;
import tom.yang.housefilter.weight.NumberWeight;
import tom.yang.tlog.Tlog;

public class ConfigLoader {

	public static final String FILTER = "FILTER";

	public static final String WEIGHT = "WEIGHT";

	private String[] getPluginNameAndArg(String s) {
		int lenclose = s.indexOf("(");
		if (lenclose != -1) {
			String name = s.substring(0, lenclose);
			int renclose = s.lastIndexOf(")");
			if (renclose != -1) {
				String arg = s.substring(lenclose + 1, renclose);
				return new String[] { name, arg };
			} else {
				return new String[] { name };
			}
		} else {
			return new String[] { s };
		}
	}

	public WeightConfig load(File f) throws FileNotFoundException, IOException {
		WeightConfig config = new WeightConfig();
		List<IHouseRowFilter> filters = new ArrayList<IHouseRowFilter>();

		List<WeightItem> wis = new ArrayList<WeightItem>();

		config.setFilters(filters);
		config.setWeightItems(wis);

		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
			reader.lines().forEach(line -> {
				if (line.startsWith(FILTER)) {
					filters.add(parseFilterLine(line));
				} else if (line.startsWith(WEIGHT)) {
					wis.add(parseWeightItem(line));
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
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e) {
			Tlog.fastLog("create filter faild : " + line + e.getMessage());
		}
		return null;
	}

	private List<WeightCondition> parseWeightConditions(String[] split) {
		List<WeightCondition> cons=new ArrayList<WeightCondition>();
		for (int i = -1; ++i < split.length;) {
			String naa[] = getPluginNameAndArg(split[i]);
			try {
				if(naa.length>1){
					cons.add(PluginFactory.CONDITION_FACTORY.create(naa[0], naa[1]));
				} else {
					cons.add(PluginFactory.CONDITION_FACTORY.create(naa[0], null));
				}
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
					| IllegalArgumentException | InvocationTargetException e) {
				Tlog.fastLog("create condition failed : " + split[i] + e.getMessage());
			}
		}
		if (cons.size() == 0) {
			return null;
		}
		return cons;
	}

	private WeightItem parseWeightItem(String line) {
		try {
			line=line.substring(7,line.length());
			String weight=line.substring(0,line.indexOf(' '));
			String conditions=line.substring(line.indexOf(' ')+4,line.length());
			String[] split = conditions.split(",");
			List<WeightCondition> wcons=parseWeightConditions(split);

			WeightItem item=new WeightItem();
			item.setConditions(wcons);
			try{
				int w=Integer.valueOf(weight);
				item.setWeight(new NumberWeight(w));
			}catch(Throwable ignore){
				String[] naa = getPluginNameAndArg(weight);
				if (naa.length > 1) {
					ICellWeight w = PluginFactory.WEIGHT_FACTORY.create(naa[0], naa[1]);
					item.setWeight(w);
				} else {
					ICellWeight w = PluginFactory.WEIGHT_FACTORY.create(naa[0], null);
					item.setWeight(w);
				}
			}
			return item;
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e) {
			Tlog.fastLog("create weightItem faild : " + line + e.getMessage());
		}
		return null;
	}
}
