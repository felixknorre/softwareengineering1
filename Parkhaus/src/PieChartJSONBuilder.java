import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class PieChartJSONBuilder extends ChartJSONBuilder{
	
	List<String> labels;
	List<Integer> values;
	
	public PieChartJSONBuilder() {
		this.labels = new ArrayList<String>();
		this.values = new ArrayList<Integer>();
	}

	@Override
	protected void createXList(List<AutoIF> liste) {
		this.labels = null;
		this.labels = liste.stream().map(i -> i.getCategory()).distinct().collect(Collectors.toList());
		
	}

	@Override
	protected void createYList(List<AutoIF> liste) {
		this.values = null;
		this.values =  (List<Integer>) this.labels.stream()
				.map(i -> liste.stream().filter(d -> d.getCategory().equals(i))
				.collect(Collectors.toList()).size()).collect(Collectors.toList());
		
	}

	@Override
	protected JsonArrayBuilder createXArray() {
		// build label json array
		JsonArrayBuilder jsonCategory = Json.createArrayBuilder();
		for(int i = 0; i < this.labels.size(); i++) {
			jsonCategory.add(this.labels.get(i));
		}

		return jsonCategory;
	}

	@Override
	protected JsonArrayBuilder createYArray() {
		// build value json array
		JsonArrayBuilder jsonCount = Json.createArrayBuilder();
		for(int i = 0; i < this.values.size(); i++) {
			jsonCount.add(this.values.get(i));
		}

		return jsonCount;
	}

	@Override
	protected JsonObject createJSON(JsonArrayBuilder x, JsonArrayBuilder y) {
		JsonObject root = Json.createObjectBuilder()
					.add("data", Json.createArrayBuilder()
							.add(Json.createObjectBuilder()
									.add("labels", x.build())
									.add("values", y.build())
									.add("type", "pie"))
							).build();

		return root;
	}



}
