import java.io.IOException;

public interface ChartJSONBuilderIF {
	
	public String buildJSON(ParkhausIF ph, String type) throws IOException;

}
