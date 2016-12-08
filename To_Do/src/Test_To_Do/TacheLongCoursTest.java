package Test_To_Do;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import exception_To_Do.ExceptionTacheAnterieur;
import model_To_Do.Categorie;
import model_To_Do.TacheLongCours;

public class TacheLongCoursTest {

	private TacheLongCours tache;
	private Categorie cat;
	private Calendar date;

	@Before
	public void setUp() throws Exception {
		cat = new Categorie("nomCategorie");
		date = new GregorianCalendar(2016,11,25); //YYYY MM-1 DD
		tache = new TacheLongCours("nomTache", "description", date, cat);
	}

	@After
	public void tearDown() {
		tache = null;
		cat = null;
		date = null;
	}

	@Test(expected=ExceptionTacheAnterieur.class)
	public void testTacheLongCoursIntStringCalendarInt() throws ExceptionTacheAnterieur {
		Calendar date = new GregorianCalendar(2000,0,1);
		TacheLongCours tp = new TacheLongCours(1, "test", date, 0);
	}

	@Test
	public void testGetJourRestant() {
		int d = (int) (tache.getEcheance().getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 86400000;
		assertEquals(tache.getJourRestant(), d);
	}

	@Test
	public void testGetCategorie() {
		assertTrue(tache.getCategorie().equals(cat));
	}

	@Test
	public void testSetCategorie() {
		Categorie newCat = new Categorie("newCat");
		tache.setCategorie(newCat);
		assertTrue(tache.getCategorie().equals(newCat));
		tache.setCategorie(cat);
	}

	@Test
	public void testGetNom() {
		assertEquals(tache.getNom(), "nomTache");
	}

	@Test
	public void testSetNom() {
		tache.setNom("newNom");
		assertEquals(tache.getNom(), "newNom");
		tache.setNom("nomTache");
	}

	@Test
	public void testGetDescription() {
		assertEquals(tache.getDescription(), "description");
	}

	@Test
	public void testSetDescription() {
		tache.setNom("newDescription");
		assertEquals(tache.getNom(), "newDescription");
		tache.setNom("description");
	}

	@Test
	public void testGetEcheance() {
		assertEquals(tache.getEcheance(), date);
	}

	@Test
	public void testSetEcheance() {
		Calendar newDate = new GregorianCalendar(2018,7,13);
		tache.setEcheance(newDate);
		assertEquals(tache.getEcheance(), newDate);
		tache.setEcheance(date);
	}

	@Test
	public void testGetId() {
		assertEquals(tache.getId(), 0);
	}

	@Test
	public void testSetId() {
		tache.setId(12);
		assertEquals(tache.getId(), 12);
		tache.setId(0);
	}
	
	@Test
	public void testIsRetarded() {
		assertEquals(tache.isRetard(), tache.getEcheance().before(Calendar.getInstance()));
	}

	@Test
	public void testIsRetard() {
		assertFalse(tache.isRetard());
	}
	
	@Test
	public void testSetRetard() {
		tache.setRetard(true);
		assertTrue(tache.isRetard());
	}

	@Test
	public void testIsTermine() {
		assertFalse(tache.isTermine());
	}
	
	@Test
	public void testSetTermine() {
		tache.setTermine(true);
		assertTrue(tache.isTermine());
		tache.setTermine(false);
	}

	@Test
	public void testEqualsTache() throws ExceptionTacheAnterieur {
		Calendar newDate = new GregorianCalendar(2018,7,13);
		Categorie newCat = new Categorie("newCat");
		TacheLongCours tp = new TacheLongCours("newTache", "newDescription", newDate, newCat);
		assertFalse(tache.equals(tp));
	}

	@Test
	public void testGetGranularite() {
		assertEquals(tache.getGranularite(), 0);
	}

	@Test
	public void testSetGranularite() {
		tache.setGranularite(75);
		assertEquals(tache.getGranularite(), 75);
		tache.setGranularite(0);
	}

}
