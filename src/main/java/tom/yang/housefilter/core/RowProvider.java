package tom.yang.housefilter.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import tom.yang.housefilter.rowfilter.IHouseRowFilter;

public class RowProvider {
	private static final String XLS_SUFFIX=".xls";
	private static final String XLSX_SUFFIX=".xlsx";

	private final List<IHouseRowFilter> filters=new ArrayList<IHouseRowFilter>();

	private final File file;

	public RowProvider(final File file){
		if(file==null){
			throw new NullPointerException();
		}
		this.file=file;
	}

	public void addFilter(final IHouseRowFilter f){
		if(f!=null){
			filters.add(f);
		}
	}

	public List<HouseRow> getRows() throws EncryptedDocumentException, InvalidFormatException, IOException{
		final Workbook wb=WorkbookFactory.create(file);
		return parseRows(wb);
	}

	public List<HouseRow> parseRows(final Workbook wb){
		if(wb==null){
			throw new IllegalArgumentException("Workbook can't be null.");
		}

		final List<HouseRow> result=new ArrayList<HouseRow>();
		for(final Sheet sheet:wb){
			for(final Row row:sheet){
				final HouseRow hr=new HouseRow();
				for(final Cell cell:row){
					hr.getCells().add(cell.getStringCellValue());
				}
				if(hr.getCells().size()>0){
					if(filters.size()>0){
						for(final IHouseRowFilter filter:filters){
							if(filter.filterHouseRow(hr)){
								result.add(hr);
							}
						}
					}else{
						result.add(hr);
					}
				}
			}
		}
		return result;
	}

}
