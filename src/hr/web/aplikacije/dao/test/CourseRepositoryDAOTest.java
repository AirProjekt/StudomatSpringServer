package hr.web.aplikacije.dao.test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import hr.web.aplikacije.dao.CourseRepositoryDAO;
import hr.web.aplikacije.domain.Kolegij;
import hr.web.aplikacije.domain.VrstaKolegija;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("daos-test.xml")
public class CourseRepositoryDAOTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private CourseRepositoryDAO courseRepositoryDAO;
	
	@Test
	public void fetchAllKolegijListTest(){
		List<Kolegij> listaKolegija = courseRepositoryDAO.fetchAllKolegijList();
		assertTrue(listaKolegija.size() == 4);
	}
	
	@Test
	public void insertKolegijResultsTest(){
		int brojUpisanihPrije = courseRepositoryDAO.fetchAllKolegijListUpisani(2).size();
		courseRepositoryDAO.insertKolegijResults(2, 2);
		int brojUpisanihPoslije = courseRepositoryDAO.fetchAllKolegijListUpisani(2).size();
		assertTrue((brojUpisanihPrije + 1) == brojUpisanihPoslije);
	}
	
	@Test
	public void retriveKorisnikIdTest(){
		int id = courseRepositoryDAO.retriveKorisnikId("ivo.ivic");
		assertTrue(id == 1);
	}
	
	@Test
	public void fetchAllKolegijListUpisaniTest(){
		assertTrue(courseRepositoryDAO.fetchAllKolegijListUpisani(2).size() == 2 );
	}
	
	@Test
	public void insertKolegijTest(){
		int brojKolegijaPrije = courseRepositoryDAO.fetchAllKolegijList().size();
		Kolegij kolegij = new Kolegij();
		kolegij.setNazivKolegija("Priroda i društvo");
		kolegij.setVrstaKolegija(VrstaKolegija.OBVEZNI);
		kolegij.setEcts(4);
		courseRepositoryDAO.insertKolegij(kolegij);
		int brojKolegijaPoslije = courseRepositoryDAO.fetchAllKolegijList().size();
		assertTrue((brojKolegijaPrije + 1) == brojKolegijaPoslije);
		List<Kolegij> lista = courseRepositoryDAO.fetchAllKolegijList();
		Kolegij kol = lista.get(lista.size()-1);
		assertTrue(kol.getNazivKolegija() == "Priroda i društvo");
	}
	
	@Test
	public void deleteKolegijTest(){
		List<Kolegij> lista = courseRepositoryDAO.fetchAllKolegijList();
		int brojKolegijaPrije = lista.size();
		Kolegij kolegij = lista.get(0);
		courseRepositoryDAO.deleteKolegij(kolegij);
		int brojKolegijaPoslije = courseRepositoryDAO.fetchAllKolegijList().size();
		assertTrue((brojKolegijaPrije - 1) == brojKolegijaPoslije);
	}
	
}
