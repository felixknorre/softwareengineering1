import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;


public class ChartJSONBuilder implements ChartJSONBuilderIF {
	
	String[] labels;
	String[] values;
	String chartType;
	
	
	public ChartJSONBuilder(String[] labels, String[] values, String chartType) {
		this.labels = labels;
		this.values = values;
		this.chartType = chartType;
	}
	

	@Override
	public String buildJSON() {
		
		JsonObject root;
		
		// return string
		String result  = "";
		
		// build label json array
		JsonArrayBuilder jsonLabel = Json.createArrayBuilder();
		for(int i = 0; i < this.labels.length; i++) {
			jsonLabel.add(this.labels[i]);
		}
		
		// build value json array
		JsonArrayBuilder jsonValues = Json.createArrayBuilder();
		for(int i = 0; i < this.values.length; i++) {
			jsonValues.add(this.values[i]);
		}
		
		switch (this.chartType) {
		case "bar":
			root = Json.createObjectBuilder()
				.add("data", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
							.add("x", jsonLabel.build())
							.add("y", jsonValues.build())
							.add("type", "bar"))
							
				).build();
			
			result = root.toString();
			
			break;
		case "pie":
			root = Json.createObjectBuilder()
			.add("data", Json.createArrayBuilder()
					.add(Json.createObjectBuilder()
						.add("x", jsonLabel.build())
						.add("y", jsonValues.build())
						.add("type", "pie"))
						
			).build();
		
		result = root.toString();
			
			
			break;
		default:
			
			break;
		}
		return result;
	}

}
