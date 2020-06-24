import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChartJSONBuilderIFTest {
	
	ChartJSONBuilderIF cjBar;
	ChartJSONBuilderIF cjPie;
	
	@BeforeEach
	void setUp() {
		String[] labels = {"Car_1", "Car_2", "Car_3"};
		String[] values = {"20", "14", "23"};
		cjBar = new ChartJSONBuilder(labels, values, "bar");
		cjPie = new ChartJSONBuilder(labels, values, "pie");
		
	}

	@Test
	void testBuildJSON() {
		System.out.println(cjBar.buildJSON());
		String expected = "{" + " \"data\": [" + " {" + " \"x\": [" + " \"Car_1\"," + " \"Car_2\"," + " \"Car_3\"" + " ]," + " \"y\": [" + " 20," + " 14," + " 23" + " ]," + " \"type\": \"bar\"" + " }" + " ]" + "}";
		assertEquals(expected, cjBar.buildJSON());
	}
	
	@Test
	void test_builder_jsonPie() {
		System.out.println(cjPie.buildJSON());
		String expected = "{\n" + " \"data\": [\n" + " {\n" + " \"labels\": [\n" + " \"Car_1\",\n" + " \"Car_2\",\n" + " \"Car_3\"\n" + " ],\n" + " \"values\": [\n" + " 20,\n" + " 14,\n" + " 23\n" + " ],\n" + " \"type\": \"bar\"\n" + " }\n" + " ]\n" + "}";
		assertEquals(expected, cjPie.buildJSON());
	}

}
