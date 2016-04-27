package edu.towson.cis.cosc603.project5.coffeemaker;

import junit.framework.TestCase;

/**
 *
 */
public class CoffeeMakerTest extends TestCase {
	private CoffeeMaker cm;
	private Inventory i;
	private Recipe r1;
	
	int amtCoffee;
	int amtMilk;
	int amtSugar;
	int amtChocolate;

	public void setUp() {
		cm = new CoffeeMaker();
		i = cm.checkInventory();

		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setPrice(50);
		r1.setAmtCoffee(6);
		r1.setAmtMilk(1);
		r1.setAmtSugar(1);
		r1.setAmtChocolate(0);
		
		amtCoffee = 15;
		amtMilk = 10;
		amtSugar = 12;
		amtChocolate = 20;
	}

	public void testAddRecipe1() {
		assertTrue(cm.addRecipe(r1));
	}
	
	public void testAddRecipe2(){
		Recipe r2 = new Recipe();
		
		r2.setName("Negative Ammount");
		r2.setPrice(-1);
		r2.setAmtCoffee(-1);
		r2.setAmtMilk(-1);
		r2.setAmtSugar(-1);
		r2.setAmtChocolate(-1);
		
		assertSame("Negative Ammount", r2.toString());
		assertEquals(0, r2.getPrice());
		assertEquals(0, r2.getAmtCoffee());
		assertEquals(0, r2.getAmtMilk());
		assertEquals(0, r2.getAmtSugar());
		assertEquals(0, r2.getAmtChocolate());
	}

	public void testDeleteRecipe1() {
		cm.addRecipe(r1);
		assertTrue(cm.deleteRecipe(r1));
	}

	public void testEditRecipe1() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r1;
		newRecipe.setAmtSugar(2);
		assertTrue(cm.editRecipe(r1, newRecipe));
	}
	
	public void testAddInventory1(){
		i.setCoffee(0);
		i.setMilk(0);
		i.setSugar(0);
		i.setChocolate(0);
		
		assertTrue(cm.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate));

	}
	
	public void testCheckInventory1(){
		i.setCoffee(0);
		i.setMilk(0);
		i.setSugar(0);
		i.setChocolate(0);
		
		cm.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate);
		i.toString();
		
		assertEquals(amtCoffee, i.getCoffee());
		assertEquals(amtMilk, i.getMilk());
		assertEquals(amtSugar, i.getSugar());
		assertEquals(amtChocolate, i.getChocolate());
	}
	
	public void testCheckInventory2(){
		assertEquals(15, i.getCoffee());
		assertEquals(15, i.getMilk());
		assertEquals(15, i.getSugar());
		assertEquals(15, i.getChocolate());
	}
	
	public void testCheckInventory3(){
		i.setCoffee(-1);
		i.setMilk(-1);
		i.setSugar(-1);
		i.setChocolate(-1);
		
		assertEquals(0, i.getCoffee());
		assertEquals(0, i.getMilk());
		assertEquals(0, i.getSugar());
		assertEquals(0, i.getChocolate());

	}
	
	public void testPurchaseBeverage1(){
		cm.addRecipe(r1);
		assertEquals(0, cm.makeCoffee(r1, 50));
	}
	
	public void testPurchaseBeverage2(){
		cm.addRecipe(r1);
		assertEquals(30, cm.makeCoffee(r1, 30));
	}
	
	public void testPurchaseBeverage3(){
		cm.addRecipe(r1);
		i.setCoffee(0);
		assertEquals(50, cm.makeCoffee(r1, 50));
	}
	
	public void testPurchaseBeverage4(){
		cm.addRecipe(r1);
		i.setMilk(0);
		assertEquals(50, cm.makeCoffee(r1, 50));
	}
}