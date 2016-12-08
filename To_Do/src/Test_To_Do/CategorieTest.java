package Test_To_Do;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import model_To_Do.Categorie;

public class CategorieTest {
	
	private Categorie cat;

	@Before
	public void setUp() {
		this.cat = new Categorie("nomCategorie");
	}

	@After
	public void tearDown() {
		this.cat = null;
	}

	@Test
	public void testGetNom() {
		assertEquals(this.cat.getNom(), "nomCategorie");
	}

	@Test
	public void testSetNom() {
		this.cat.setNom("newNom");
		assertEquals(this.cat.getNom(), "newNom");
		this.cat.setNom("nomCategorie");
	}

	@Test 
	public void testGetIdentifiant() {
		System.out.println(this.cat.getIdentifiant());
		assertEquals(this.cat.getIdentifiant(), 0);
	}

	@Test
	public void testEqualsCategorie() {
		Categorie test = new Categorie("testCategorie");
		assertFalse(this.cat.equals(test));
	}

}
