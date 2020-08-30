import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PreisberechnerIFTest {
	
	PreisberechnerIF pb;
	
	@BeforeEach
	void setUp() {
		pb = new Preisberechner(15, 10, 10);
	}

	@Test
	@DisplayName("Berechne Preise")
	void testCalculatedPrice() {
		assertEquals("150.0", pb.calculatedPrice("any", "10000", "10"));
		assertEquals("100.0", pb.calculatedPrice("Frau", "10000", "10"));
		assertEquals("100.0", pb.calculatedPrice("Familie", "10000", "10"));
	}


	@Test
	@DisplayName("Schließe erfolgreich Abo ab")
	void test_create_Subscription_success() {
		pb.createSubscription();
		pb.subscription("3");
		assertEquals("150.0", pb.calculatedPrice("any", "10000", "5")); // 15 * 100
		assertEquals("0.0", pb.calculatedPrice("any", "10000", "3")); // 0
	}
	
	@Test
	@DisplayName("Schließe nicht erfolgreich Abo ab")
	void test_create_Subscription_fail() {
		pb.subscription("3");
		assertEquals("150.0", pb.calculatedPrice("any", "10000", "3")); // 0
	}
	
	@Test
	@DisplayName("Teste Preisauflistung")
	void test_toString() {
		assertEquals("Frauen: 10 Euro, Familie: 10 Euro, Andere: 15 Euro (pro Sekunde)", pb.toString());
	}

}
