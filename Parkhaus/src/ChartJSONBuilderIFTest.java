import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChartJSONBuilderIFTest {
	
	ParkhausIF ph;
	AutoIF a1, a2, a3;
	ChartJSONBuilderIF cjb;

	
	@BeforeEach
	void setUp() {
		ph = new Parkhaus();
		
		a1 = new Auto("1", "12234", "20", "200", "hbdfjsbdf", "#color1", "3", "any");
		a2 = new Auto("2", "323432", "14", "300", "kjfdbvs", "#color2", "4", "any");
		a3 = new Auto("3", "323432", "23", "300", "kjfdbvs", "#color2", "4", "Family");
		
		ph.addAuto(a1);
		ph.addAuto(a2);
		ph.addAuto(a3);
		
		ph.removeAuto(a1);
		ph.removeAuto(a2);
		ph.removeAuto(a3);
	
		cjb = new ChartJSONBuilder();		
	}

	@Test
	@DisplayName("Build json for bar chart")
	void testBuildJSON() throws IOException {
		String expected = "{" + "\"data\":[" + "{" + "\"x\":[" + "\"Car_1\"," + "\"Car_2\"," + "\"Car_3\"" + "]," + "\"y\":[" + "\"20\"," + "\"14\"," + "\"23\"" + "]," + "\"type\":\"bar\"" + "}" + "]" + "}";
		assertEquals(expected, cjb.buildJSON(ph, "bar"));
	}
	
	@Test
	@DisplayName("Build json for pie chart")
	void test_builder_jsonPie() throws IOException {
		String expected = "{" + "\"data\":[" + "{" + "\"labels\":[" + "\"any\"," + "\"Family\"" + "]," + "\"values\":[" + "2," + "1" + "]," + "\"type\":\"pie\"" + "}" + "]" + "}";
		assertEquals(expected, cjb.buildJSON(ph, "pie"));
	}

}
