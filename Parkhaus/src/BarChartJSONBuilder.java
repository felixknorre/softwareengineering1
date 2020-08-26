import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class BarChartJSONBuilder extends ChartJSONBuilder{
	
	List<String> labels;
	List<String> values;
	
	public BarChartJSONBuilder() {
		this.labels = new ArrayList<String>();
		this.values = new ArrayList<String>();
	}

	@Override
	protected void createXList(List<AutoIF> liste) {
		// reset list;
		this.labels = null;
		this.labels = liste.stream().map(t -> "Car_" + t.getNr()).collect(Collectors.toList()); // steam to list
		
	}

	@Override
	protected void createYList(List<AutoIF> liste) {
		this.values = null;
		this.values = liste.stream().map(t -> t.getDuration()).collect(Collectors.toList()); // steam to list
		
	}

	@Override
	protected JsonArrayBuilder createXArray() {
		// build label json array
		JsonArrayBuilder jsonLabel = Json.createArrayBuilder();
		for(int i = 0; i < this.labels.size(); i++) {
				jsonLabel.add(this.labels.get(i));
			}

		return jsonLabel;
	}

	@Override
	protected JsonArrayBuilder createYArray() {
		// build value json array
		JsonArrayBuilder jsonValues = Json.createArrayBuilder();
		for(int i = 0; i < this.values.size(); i++) {
			jsonValues.add(this.values.get(i));
		}

		return jsonValues;
	}

	@Override
	protected JsonObject createJSON(JsonArrayBuilder x, JsonArrayBuilder y) {
		JsonObject root = Json.createObjectBuilder()
						.add("data", Json.createArrayBuilder()
								.add(Json.createObjectBuilder()
									.add("x", x.build())
									.add("y", y.build())
									.add("type", "bar"))
									
						).build();

		return root;
	}
	





}
