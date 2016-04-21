package tom.yang.housefilter.core;

import tom.yang.housefilter.rowfilter.WeightConfiguration;
import tom.yang.tlog.Tlog;

public class WeightCaculator {

	private final WeightConfiguration conf;

	public WeightCaculator(final WeightConfiguration conf) {
		if(conf==null){
			throw new IllegalArgumentException("weight configuration can't be null.");
		}
		this.conf=conf;
	}

	public int caculatorRow(final HouseRow row){
		if(row==null){
			throw new IllegalArgumentException("cells can't be null");
		}
		if(conf.getWeights()==null){
			return 0;
		}
		int result=0;
		final StringBuilder log=new StringBuilder();
		log.append(" row : ").append(row.getId());
		for(final String cell:row.getCells()){
			final CellWeight cellWeight = conf.getWeights().get(cell);
			if(cellWeight==null){
				continue;
			}
			if(cellWeight.getWc().match(row)){
				final int w = cellWeight.getWeight();
				result+=w;
				log.append("cell : ").append(cell).append("weight : ").append(w);
			}
		}
		log.append("weight caculated ").append(result);
		Tlog.fastLog(log.toString());
		return result;
	}
}
