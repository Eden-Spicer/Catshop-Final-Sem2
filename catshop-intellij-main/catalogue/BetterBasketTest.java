package catalogue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BetterBasketTest extends BetterBasket {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Test
	void testAddProduct() {
		BetterBasket b = new BetterBasket();
		Product p1 = new Product("0001", "Camera", 12, 1);
		Product p2 = new Product("0001", "Camera", 12, 1);
		Product p3 = new Product("0002", "Phone", 14, 1);
		Product p4 = new Product("0002", "Phone", 14, 2);
		Product p5 = new Product("0005", "Biscuits", 3, 1);
		Product p6 = new Product("0004", "Cat", 20, 5);
		Product p7 = new Product("0002", "Phone", 14, 1);
		Product p8 = new Product("0003", "TV", 300, 1);
		
		assertTrue(b.isEmpty(), "Basket empty");
		b.add(p1);
		b.add(p2);
		assertEquals(1, b.size(), "Duplicate products did not merge - Basket size incorrect");
		assertEquals(2, b.get(0).getQuantity(), "Duplicate products did not merge - Product quantity incorrect");
		
		b.add(p3);
		b.add(p4);
		
		assertEquals(2, b.size(), "Duplicate products did not merge - Basket size incorrect");
		assertEquals(3, b.get(1).getQuantity(), "Duplicate products did not merge - Product quantity incorrect");
		
		b.add(p5);
		assertEquals("0005", b.get(2).getProductNum(), "Basket did not sort correctly");
		
		b.add(p6);
		assertEquals("0005", b.get(3).getProductNum(), "Basket did not sort correctly");
		
		b.add(p7);
		assertEquals(4,b.get(1).getQuantity(), "Bakset products got combined");

		
		b.add(p8);
		assertEquals("0003",b.get(2).getProductNum(),"Basket got sorted it");
		
		
	}

	@Test
	void testRemoveProduct() {
		BetterBasket b = new BetterBasket();
		Product p1 = new Product("0001", "Camera", 12, 1);
		Product p2 = new Product("0001", "Camera", 12, 1);
		Product p3 = new Product("0002", "Phone", 14, 1);
		Product p4 = new Product("0002", "Phone", 14, 2);
		
		b.remove(p1);
		assertTrue(b.isEmpty());
		
		b.add(p1);
		b.add(p2);
		b.remove(p2);
		assertEquals(1,b.get(0).getQuantity(), "Removed product");
		
		b.remove(p1);
		System.out.print(b);
		assertTrue(b.isEmpty());
		
		b.add(p1);
		b.add(p3);
		b.add(p4);
		b.remove(p4);
		System.out.print(b);
		assertEquals(2,b.get(1).getQuantity(), "Remove 1 product from previous item");
		
		assertEquals(2,b.size());

		
		
	}

}
