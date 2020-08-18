import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


public class ChartJSONBuilder implements ChartJSONBuilderIF {
	
	
	
	public ChartJSONBuilder() {

	}
	
	public String testBuild() throws IOException {
		JsonObject root;
		
		String result = "";
		
		JsonArrayBuilder jsonLabel = Json.createArrayBuilder();
		jsonLabel.add("Car_1");
		jsonLabel.add("Car_2");
		jsonLabel.add("Car_3");
		JsonArrayBuilder jsonValues = Json.createArrayBuilder();
		jsonValues.add(20);
		jsonValues.add(14);
		jsonValues.add(23);
		
		root = Json.createObjectBuilder()
				.add("data", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
							.add("x", jsonLabel.build())
							.add("y", jsonValues.build())
							.add("type", "bar"))
							
				).build();
		
		String jsonString;
		try(Writer writer = new StringWriter()) {
		    Json.createWriter(writer).write(root);
		    jsonString = writer.toString();
		}
		
		
		return jsonString;
	}
	

	@Override
	public String buildJSON(ParkhausIF ph, String type) throws IOException {
		
		JsonObject root;
		
		// return string
		String result  = "";
		
		switch (type) {
		case "bar":
			
			// get car number
			List<String> number = ph.getHistory().stream().map(t -> "Car_" + t.getNr()).collect(Collectors.toList()); // steam to list
			
			// build label json array
			JsonArrayBuilder jsonLabel = Json.createArrayBuilder();
			for(int i = 0; i < number.size(); i++) {
				jsonLabel.add(number.get(i));
			}
			
			
			// get duration
			List<String> duration = ph.getHistory().stream().map(t -> t.getDuration()).collect(Collectors.toList()); // steam to list
			
			
			// build value json array
			JsonArrayBuilder jsonValues = Json.createArrayBuilder();
			for(int i = 0; i < duration.size(); i++) {
				jsonValues.add(duration.get(i));
			}
			
			
			root = Json.createObjectBuilder()
				.add("data", Json.createArrayBuilder()
						.add(Json.createObjectBuilder()
							.add("x", jsonLabel.build())
							.add("y", jsonValues.build())
							.add("type", "bar"))
							
				).build();
			
		
			try(Writer writer = new StringWriter()) {
			    Json.createWriter(writer).write(root);
			    result = writer.toString();
			}
			
			break;
		case "pie":
			
			// list categories
			List<String> categories = ph.getHistory().stream().map(i -> i.getCategory()).distinct().collect(Collectors.toList());
			
			
			// list categories
			List<Integer> count = (List<Integer>) categories.stream()
					.map(i -> ph.getHistory().stream().filter(d -> d.getCategory().equals(i))
					.collect(Collectors.toList()).size()).collect(Collectors.toList());

			
			
			// build label json array
			JsonArrayBuilder jsonCategory = Json.createArrayBuilder();
			for(int i = 0; i < categories.size(); i++) {
				jsonCategory.add(categories.get(i));
			}
			
			
			// build value json array
			JsonArrayBuilder jsonCount = Json.createArrayBuilder();
			for(int i = 0; i < count.size(); i++) {
				jsonCount.add(count.get(i));
			}
			
			root = Json.createObjectBuilder()
			.add("data", Json.createArrayBuilder()
					.add(Json.createObjectBuilder()
						.add("labels", jsonCategory.build())
						.add("values", jsonCount.build())
						.add("type", "pie"))
						
			).build();
		
			try(Writer writer = new StringWriter()) {
			    Json.createWriter(writer).write(root);
			    result = writer.toString();
			}
			
			
			break;
		default:
			
			break;
		}
		return result;
	}

}
