package tom.yang.housefilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import tom.yang.housefilter.config.ConfigLoader;
import tom.yang.housefilter.config.WeightConfig;
import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.core.RowOutput;
import tom.yang.housefilter.core.RowProvider;
import tom.yang.housefilter.core.RowSorter;
import tom.yang.housefilter.core.WeightCaculator;
import tom.yang.housefilter.core.WeightItem;
import tom.yang.housefilter.rowfilter.IHouseRowFilter;


/**
 * Hello world!
 *
 */
public class App
{

	public static final String CONF_FILE="conf.hf";
	public static final String OUTPUT_FILE="output.csv";

	public static void main( final String[] args ) throws ParseException, FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException
	{
		final String usrdir = System.getProperty("user.dir");

		final Options options=createOptions();

		final CommandLine cmd=new DefaultParser().parse(options, args);

		final File conf=cmd.hasOption('c')?FileUtils.getFile(cmd.getOptionValue('c')):FileUtils.getFile(usrdir,CONF_FILE);
		final File output=cmd.hasOption('o')?FileUtils.getFile(cmd.getOptionValue('o')):FileUtils.getFile(usrdir,OUTPUT_FILE);
		final File input=FileUtils.getFile(cmd.getOptionValue('i'));

		final ConfigLoader loader=new ConfigLoader();

		final WeightConfig wc = loader.load(conf);

		final RowProvider provider=new RowProvider(input);
		for(final IHouseRowFilter f:wc.getFilters()){
			if(f!=null){
				provider.addFilter(f);
			}
		}

		final List<HouseRow> rows = provider.getRows();

		final List<WeightItem> wis = wc.getWeightItems();
		final WeightCaculator caculator=new WeightCaculator();
		for(final HouseRow row:rows){
			final int w=caculator.caculatorRow(wis, row);
			row.setRowWeight(w);
			row.addCell(String.valueOf(w));
		}

		new RowSorter().sortRows(rows);

		new RowOutput().printHouseRows(rows, output,wc.getHead());
	}

	private static Options createOptions() {
		final Options options=new Options();
		options.addOption("o", true, "output file path.");
		options.addOption(Option.builder("i").desc("input excel file path.").hasArg().required().build());
		options.addOption("c", true, "output config file path.");
		return options;
	}
}
