package tom.yang.housefilter;

import java.io.File;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import tom.yang.housefilter.core.HouseRow;
import tom.yang.housefilter.core.RowProvider;
import tom.yang.housefilter.rowfilter.FirstRowIndexFilter;

public class RowProviderTest {
	@Rule
	public ExpectedException thrown= ExpectedException.none();

	@Test
	public void testFilterRow(){
		final RowProvider provider=new RowProvider(new File("file"));

		final FirstRowIndexFilter filter=new FirstRowIndexFilter();
		provider.addFilter(filter);

		final XSSFWorkbook wb=new XSSFWorkbook();
		final Sheet sheet1=wb.createSheet("sheet1");
		for(int i=0;i<3;i++){
			final Row row = sheet1.createRow(i);
			row.createCell(0).setCellValue("k"+i);
			row.createCell(1).setCellValue("v"+i);
		}
		final Row row=sheet1.createRow(3);
		row.createCell(0).setCellValue("123");
		row.createCell(1).setCellValue("value");

		final List<HouseRow> rows=provider.parseRows(wb);
		Assert.assertEquals(1, rows.size());

		Assert.assertEquals("123",rows.get(0).getCells().get(0).getValue());
		Assert.assertEquals("value",rows.get(0).getCells().get(1).getValue());
	}


	@Test
	public void testNullWorkbook(){
		thrown.expect(IllegalArgumentException.class);

		final RowProvider provider=new RowProvider(new File("file"));
		provider.parseRows(null);
	}

	@Test
	public void testProvideRow(){
		final RowProvider provider=new RowProvider(new File("file"));

		final XSSFWorkbook wb=new XSSFWorkbook();
		final Sheet sheet1=wb.createSheet("sheet1");
		for(int i=0;i<3;i++){
			final Row row = sheet1.createRow(i);
			row.createCell(0).setCellValue("k"+i);
			row.createCell(1).setCellValue("v"+i);
		}
		final List<HouseRow> rows=provider.parseRows(wb);

		Assert.assertEquals(3, rows.size());

		for(int i=0;i<rows.size();++i){
			Assert.assertEquals("k" + i, rows.get(i).getCells().get(0).getValue());
			Assert.assertEquals("v" + i, rows.get(i).getCells().get(1).getValue());
		}
	}
}
