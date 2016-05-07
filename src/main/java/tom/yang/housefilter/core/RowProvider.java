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
	private final File file;

	private final List<IHouseRowFilter> filters=new ArrayList<IHouseRowFilter>();

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
					cell.getColumnIndex();
					final HouseCell hc = new HouseCell();
					hc.setCol(cell.getColumnIndex());
					hc.setValue(getCellValue(cell));
					hr.getCells().add(hc);
				}
				boolean add=true;
				if(hr.getCells().size()>0){
					if(filters.size()>0){
						for(final IHouseRowFilter filter:filters){
							if(!filter.filterHouseRow(hr)){
								add=false;
								break;
							}
						}
						if(add){
							result.add(hr);
						}
					}else{
						result.add(hr);
					}
				}
			}
		}
		return result;
	}

	private String getCellValue(final Cell cell){
		switch(cell.getCellType()){
		case Cell.CELL_TYPE_NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		default:
			return cell.getStringCellValue();
		}
	}

}
