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
		assertEquals("150", pb.calculatedPrice("any", "10", "10"));
		assertEquals("100", pb.calculatedPrice("Frau", "10", "10"));
		assertEquals("100", pb.calculatedPrice("Familie", "10", "10"));
	}


	@Test
	@DisplayName("Schließe erfolgreich Abo ab")
	void test_create_Subscription_success() {
		pb.createSubscription();
		pb.subscription("3");
		assertEquals("1500", pb.calculatedPrice("any", "100", "5")); // 15 * 100
		assertEquals("0", pb.calculatedPrice("any", "100", "3")); // 0
	}
	
	@Test
	@DisplayName("Schließe nicht erfolgreich Abo ab")
	void test_create_Subscription_fail() {
		pb.subscription("3");
		assertEquals("1500", pb.calculatedPrice("any", "100", "3")); // 0
	}

}
