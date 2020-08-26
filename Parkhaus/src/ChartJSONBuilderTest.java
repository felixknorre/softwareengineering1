import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChartJSONBuilderTest {
	
	ChartJSONBuilder bar, pie;
	ParkhausIF ph;
	AutoIF a1, a2, a3;

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
		
		bar = new BarChartJSONBuilder();
		pie = new PieChartJSONBuilder();

	}

	@Test
	@DisplayName("BarChart JSON Test")
	void test_bar_json_builder() {
		String expected = "{" + "\"data\":[" + "{" + "\"x\":[" + "\"Car_1\"," + "\"Car_2\"," + "\"Car_3\"" + "]," + "\"y\":[" + "\"20\"," + "\"14\"," + "\"23\"" + "]," + "\"type\":\"bar\"" + "}" + "]" + "}";
		assertEquals(expected, bar.build(ph.getHistory()));
	}
	
	@Test
	@DisplayName("PieChart JSON Test")
	void test_pie_json_builder() {
		String expected = "{" + "\"data\":[" + "{" + "\"labels\":[" + "\"any\"," + "\"Family\"" + "]," + "\"values\":[" + "2," + "1" + "]," + "\"type\":\"pie\"" + "}" + "]" + "}";
		assertEquals(expected, pie.build(ph.getHistory()));
	}

}
