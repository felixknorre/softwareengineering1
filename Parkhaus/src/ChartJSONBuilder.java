import java.util.List;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public abstract class ChartJSONBuilder {
	
	final public String build(List<AutoIF> liste) {
		// create x list
		createXList(liste);
		// create y list
		createYList(liste);
		// create json array for labels,x
		JsonArrayBuilder jsonLabel = createXArray();
		// create json array for values,y
		JsonArrayBuilder jsonValues = createYArray();
		// create json object for plotly
		JsonObject root = createJSON(jsonLabel, jsonValues);
		// return json as string
		return root.toString();
	}
	protected abstract void createXList(List<AutoIF> liste);
	protected abstract void createYList(List<AutoIF> liste);
	protected abstract JsonArrayBuilder createXArray();
	protected abstract JsonArrayBuilder createYArray();
	protected abstract JsonObject createJSON(JsonArrayBuilder x, JsonArrayBuilder y);

}
