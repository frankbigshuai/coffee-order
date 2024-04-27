package edu.iu.habahram.coffeeorder;

import edu.iu.habahram.coffeeorder.model.OrderData;
import edu.iu.habahram.coffeeorder.model.Receipt;
import edu.iu.habahram.coffeeorder.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class CoffeeOrderApplicationTests{

	private OrderRepository orderRepository;

	@BeforeEach
	public void setUp() {
		orderRepository = new OrderRepository();
	}

	@Test
	public void testAddDecaf() throws Exception {
		OrderData orderData = new OrderData("Decaf", List.of());
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Decaf", receipt.description());
		assertEquals(1.28F, receipt.cost());
	}
	@Test
	public void testAddEspresso() throws Exception {
		OrderData orderData = new OrderData("Espresso", List.of());
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Espresso", receipt.description());
		assertEquals(1.34F, receipt.cost(), 0.01F); // Use a delta of 0.01F
	}

	@Test
	public void testAddHouseBlend() throws Exception {
		OrderData orderData = new OrderData("HouseBlend", List.of());
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("HouseBlend", receipt.description());
		assertEquals(1.65F, receipt.cost(), 0.01F); // Use a delta of 0.01F
	}

	@Test
	public void testAddDarkRoast() throws Exception {
		OrderData orderData = new OrderData("Dark Roast", List.of());
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Dark roast", receipt.description());
		assertEquals(1.99F, receipt.cost(), 0.01F); // Use a delta of 0.01F
	}


	@Test
	public void testAddMilkToDecaf() throws Exception {
		OrderData orderData = new OrderData("Decaf", List.of("Milk"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Decaf, Milk", receipt.description());
		assertEquals(1.68F, receipt.cost());
	}

	@Test
	public void testAddMochaToEspresso() throws Exception {
		OrderData orderData = new OrderData("Espresso", List.of("Mocha"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Espresso, Mocha", receipt.description());
		assertEquals(1.34F + 0.3F, receipt.cost(), 0.01F); // Use a delta of 0.01F
	}

	@Test
	public void testAddSoyToHouseBlend() throws Exception {
		OrderData orderData = new OrderData("HouseBlend", List.of("Soy"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("HouseBlend, Soy", receipt.description());
		assertEquals(1.65F + 0.27F, receipt.cost(), 0.01F); // Use a delta of 0.01F
	}

	@Test
	public void testAddWhipToDarkRoast() throws Exception {
		OrderData orderData = new OrderData("Dark Roast", List.of("Whip"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Dark roast, Whip", receipt.description());
		assertEquals(1.99F + 0.25F, receipt.cost(), 0.01F); // Use a delta of 0.01F
	}


	@Test
	public void testAddMochaAndWhipToEspresso() throws Exception {
		OrderData orderData = new OrderData("Espresso", List.of("Mocha", "Whip"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Espresso, Mocha, Whip", receipt.description());
		assertEquals(1.34F + 0.3F + 0.25F, receipt.cost());
	}

	@Test
	public void testAddMilkToEspresso() throws Exception {
		OrderData orderData = new OrderData("Espresso", List.of("Milk"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Espresso, Milk", receipt.description());
		assertEquals(1.34F + 0.4F, receipt.cost(), 0.01F);
	}

	@Test
	public void testAddSoyAndMochaToHouseBlend() throws Exception {
		OrderData orderData = new OrderData("HouseBlend", List.of("Soy", "Mocha"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("HouseBlend, Soy, Mocha", receipt.description());
		assertEquals(1.65F + 0.27F + 0.3F, receipt.cost(), 0.01F);
	}

	@Test
	public void testAddWhipAndMochaToDarkRoast() throws Exception {
		OrderData orderData = new OrderData("Dark Roast", List.of("Whip", "Mocha"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Dark roast, Whip, Mocha", receipt.description());
		assertEquals(1.99F + 0.25F + 0.3F, receipt.cost(), 0.01F);
	}

	@Test
	public void testAddSoyMilkAndWhipToDecaf() throws Exception {
		OrderData orderData = new OrderData("Decaf", List.of("Soy", "Milk", "Whip"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Decaf, Soy, Milk, Whip", receipt.description());
		assertEquals(1.28F + 0.27F + 0.4F + 0.25F, receipt.cost(), 0.01F);
	}

	@Test
	public void testAddMilkAndMochaToEspresso() throws Exception {
		OrderData orderData = new OrderData("Espresso", List.of("Milk", "Mocha"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Espresso, Milk, Mocha", receipt.description());
		assertEquals(1.34F + 0.4F + 0.3F, receipt.cost(), 0.01F);
	}

	@Test
	public void testAddSoyAndWhipToHouseBlend() throws Exception {
		OrderData orderData = new OrderData("HouseBlend", List.of("Soy", "Whip"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("HouseBlend, Soy, Whip", receipt.description());
		assertEquals(1.65F + 0.27F + 0.25F, receipt.cost(), 0.01F);
	}

	@Test
	public void testAddMochaToDarkRoast() throws Exception {
		OrderData orderData = new OrderData("Dark Roast", List.of("Mocha"));
		Receipt receipt = orderRepository.add(orderData);
		assertEquals("Dark roast, Mocha", receipt.description());
		assertEquals(1.99F + 0.3F, receipt.cost(), 0.01F);
	}




}
