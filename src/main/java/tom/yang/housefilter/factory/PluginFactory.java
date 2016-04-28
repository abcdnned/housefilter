package tom.yang.housefilter.factory;

import java.util.Set;

import org.reflections.Reflections;

import tom.yang.housefilter.condition.WeightCondition;
import tom.yang.housefilter.rowfilter.IHouseRowFilter;
import tom.yang.housefilter.weight.ICellWeight;

public class PluginFactory<T> {

	public static PluginFactory<WeightCondition> CONDITION_FACTORY = new PluginFactory<WeightCondition>(
			WeightCondition.class);

	public static PluginFactory<IHouseRowFilter> FILTER_FACTORY = new PluginFactory<IHouseRowFilter>(
			IHouseRowFilter.class);

	public static PluginFactory<ICellWeight> WEIGHT_FACTORY = new PluginFactory<ICellWeight>(ICellWeight.class);

	private final Reflections reflections;

	final Class<T> typeParameterClass;

	{
		reflections = new Reflections("tom.yang.housefilter");
	}

	public PluginFactory(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}

	public T create(String name, String... args) throws InstantiationException, IllegalAccessException {
		Set<Class<? extends T>> types = reflections.getSubTypesOf(typeParameterClass);
		for (Class<? extends T> type : types) {
			if (type.getName().endsWith(name)) {
				if (args.length > 0) {

				} else {
					return type.newInstance();
				}
			}
		}
		return null;
	}
}
