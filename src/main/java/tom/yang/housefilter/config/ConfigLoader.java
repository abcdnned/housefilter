package tom.yang.housefilter.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import tom.yang.housefilter.condition.WeightCondition;
import tom.yang.housefilter.core.Constants;
import tom.yang.housefilter.core.WeightItem;
import tom.yang.housefilter.factory.PluginFactory;
import tom.yang.housefilter.rowfilter.IHouseRowFilter;
import tom.yang.housefilter.weight.ICellWeight;
import tom.yang.housefilter.weight.NumberWeight;

public class ConfigLoader {

	public static final String FILTER = "FILTER";

	public static final String WEIGHT = "WEIGHT";

	public static final String HEAD="HEAD";

	private String[] getPluginNameAndArg(final String s) {
		final int lenclose = s.indexOf("(");
		if (lenclose != -1) {
			final String name = s.substring(0, lenclose);
			final int renclose = s.lastIndexOf(")");
			if (renclose != -1) {
				final String arg = s.substring(lenclose + 1, renclose);
				return new String[] { name, arg };
			} else {
				return new String[] { name };
			}
		} else {
			return new String[] { s };
		}
	}

	public WeightConfig load(final File f) throws FileNotFoundException, IOException {
		final WeightConfig config = new WeightConfig();
		final List<IHouseRowFilter> filters = new ArrayList<IHouseRowFilter>();

		final List<WeightItem> wis = new ArrayList<WeightItem>();

		config.setFilters(filters);
		config.setWeightItems(wis);

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.openInputStream(f),"GB2312"))) {
			reader.lines().forEach(line -> {
				if (line.startsWith(FILTER)) {
					filters.add(parseFilterLine(line));
				} else if (line.startsWith(WEIGHT)) {
					wis.add(parseWeightItem(line));
				} else if(line.startsWith(HEAD)){
					config.setHead(parseHead(line));
				}
			});
		}
		return config;
	}

	private String[] parseHead(final String line) {
		final String heads=line.substring(HEAD.length()+1, line.length());
		return heads.split(",");
	}

	private IHouseRowFilter parseFilterLine(final String line) {
		final String filter = line.substring(FILTER.length() + 1, line.length());
		final int lenclose = filter.indexOf("(");
		if (lenclose != -1) {
			final String name = filter.substring(0, lenclose);
			final int renclose = filter.lastIndexOf(")");
			if (renclose != -1) {
				final String arg = filter.substring(lenclose + 1, renclose);
				return PluginFactory.FILTER_FACTORY.create(name, arg);
			} else {
				return PluginFactory.FILTER_FACTORY.create(name, null);
			}
		} else {
			return PluginFactory.FILTER_FACTORY.create(filter, null);
		}
	}

	private List<WeightCondition> parseWeightConditions(final String[] split) {
		final List<WeightCondition> cons=new ArrayList<WeightCondition>();
		for (int i = -1; ++i < split.length;) {
			final String naa[] = getPluginNameAndArg(split[i]);
			if(naa.length>1){
				cons.add(PluginFactory.CONDITION_FACTORY.create(naa[0], naa[1]));
			} else {
				cons.add(PluginFactory.CONDITION_FACTORY.create(naa[0], null));
			}
		}
		return cons;
	}

	private WeightItem parseWeightItem(String line) {
		line=line.substring(7,line.length());
		final String weight=line.substring(0,line.indexOf(' '));
		final String conditions=line.substring(line.indexOf(' ')+4,line.length());
		final String[] split = conditions.split(Constants.CONDITION_SPLIT);
		final List<WeightCondition> wcons=parseWeightConditions(split);

		final WeightItem item=new WeightItem();
		item.setConditions(wcons);
		try{
			final int w=Integer.valueOf(weight);
			item.setWeight(new NumberWeight(w));
		}catch(final Throwable ignore){
			final String[] naa = getPluginNameAndArg(weight);
			if (naa.length > 1) {
				final ICellWeight w = PluginFactory.WEIGHT_FACTORY.create(naa[0], naa[1]);
				item.setWeight(w);
			} else {
				final ICellWeight w = PluginFactory.WEIGHT_FACTORY.create(naa[0], null);
				item.setWeight(w);
			}
		}
		return item;
	}
}
