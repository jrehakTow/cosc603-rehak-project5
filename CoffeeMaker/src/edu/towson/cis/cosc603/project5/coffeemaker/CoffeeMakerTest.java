package edu.towson.cis.cosc603.project5.coffeemaker;

import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class CoffeeMakerTest.
 */
public class CoffeeMakerTest extends TestCase {
	
	/** The cm. */
	private CoffeeMaker cm;
	
	/** The i. */
	private Inventory i;
	
	/** The r1. */
	private Recipe r1;
	
	/** The amt coffee. */
	int amtCoffee;
	
	/** The amt milk. */
	int amtMilk;
	
	/** The amt sugar. */
	int amtSugar;
	
	/** The amt chocolate. */
	int amtChocolate;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	public void setUp() {
		cm = new CoffeeMaker();
		i = cm.checkInventory();

		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setPrice(50);
		r1.setAmtCoffee(3);
		r1.setAmtMilk(1);
		r1.setAmtSugar(1);
		r1.setAmtChocolate(0);
		
		amtCoffee = 15;
		amtMilk = 10;
		amtSugar = 12;
		amtChocolate = 20;
	}
	
	public void tearDown(){
		cm = null;
		i = null;
		r1 = null;
	}

	/**
	 * Test add recipe1.
	 */
	public void testAddRecipe1(){
		assertTrue(cm.addRecipe(r1));
		assertNotSame(r1, cm.getRecipeForName(" "));
		assertSame(r1, cm.getRecipeForName("Coffee"));
	}
	
	/**
	 * Test add recipe2. Test double add recipe. 
	 */
	public void testAddRecipe2(){ //double add
		assertTrue(cm.addRecipe(r1));
		assertFalse(cm.addRecipe(r1));
	}

	/**
	 * Test delete recipe1.
	 */
	public void testDeleteRecipe1() {
		cm.addRecipe(r1);
		assertTrue(cm.deleteRecipe(r1));
	}
	
	/**
	 * Test delete recipe2. Test delete recipe not in array. 
	 */
	public void testDeleteRecipe2() {
		assertFalse(cm.deleteRecipe(r1));
	}

	/**
	 * Test edit recipe1.
	 */
	public void testEditRecipe1() {
		cm.addRecipe(r1);
		Recipe newRecipe = new Recipe();
		newRecipe = r1;
		newRecipe.setAmtSugar(2);
		assertTrue(cm.editRecipe(r1, newRecipe));
	}
	
	/**
	 * Test edit recipe2. Test edit duplicate recipe
	 */
	public void testEditRecipe2() {
		assertFalse(cm.editRecipe(r1, r1));
	}
	
	/**
	 * Test get recipes.
	 */
	public void testGetRecipes(){
		/*Recipe [] emptyArray = new Recipe[4];
		for(int i = 0; i < 4; i++){
			assertSame(null, cm..);
		}
		
		assertSame(emptyArray, cm.getRecipes());
		*/
	}
	
	/**
	 * Test add inventory1. Test add inventory from zero
	 */
	public void testAddInventory1(){
		i.setCoffee(0);
		i.setMilk(0);
		i.setSugar(0);
		i.setChocolate(0);
		
		assertTrue(cm.addInventory(amtCoffee, amtMilk, amtSugar, amtChocolate));
		assertEquals(amtCoffee, i.getCoffee());
		assertEquals(amtMilk, i.getMilk());
		assertEquals(amtSugar, i.getSugar());
		assertEquals(amtChocolate, i.getChocolate());
	}
	
	/**
	 * Test add inventory2.Test add nothing to inventory.
	 */
	public void testAddInventory2(){
		assertTrue(cm.addInventory(0, 0, 0, 0));
	}
	
	/**
	 * Test add ienvtory3. Test cannot add negative amount to inventory
	 */
	public void testAddIenvtory3(){
		assertFalse(cm.addInventory(-1, -1, -1, -1));
	}
	
	/**
	 * Test purchase beverage1.
	 */
	public void testPurchaseBeverage1(){
		cm.addRecipe(r1);
		assertTrue(i.enoughIngredients(r1));
		assertEquals(0, cm.makeCoffee(r1, 50));
		
		//check inventory updated
		assertEquals(12, i.getCoffee());
		assertEquals(14, i.getMilk());
		assertEquals(14, i.getSugar());
		assertEquals(15, i.getChocolate());
	}
	
	/**
	 * Test purchase beverage2. Test not enough money.
	 */
	public void testPurchaseBeverage2(){
		cm.addRecipe(r1);
		assertEquals(30, cm.makeCoffee(r1, 30));
	}
	
	/**
	 * Test purchase beverage3. Test out of ingredient.
	 */
	public void testPurchaseBeverage3(){
		cm.addRecipe(r1);
		i.setCoffee(0);
		assertEquals(50, cm.makeCoffee(r1, 50));
	}
	
	/**
	 * Test purchase beverage5. Test buy coffee that uses all ingredients.
	 */
	public void testPurchaseBeverage4(){
		
		r1.setAmtChocolate(2); //extra chocolate
		assertEquals(2, r1.getAmtChocolate());
		cm.addRecipe(r1);
		assertEquals(0, cm.makeCoffee(r1, 50));
		
		//check inventory updated
		assertEquals(12, i.getCoffee());
		assertEquals(14, i.getMilk());
		assertEquals(14, i.getSugar());
		assertEquals(13, i.getChocolate());
	}
	
}