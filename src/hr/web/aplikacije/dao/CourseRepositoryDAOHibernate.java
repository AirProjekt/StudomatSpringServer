package hr.web.aplikacije.dao;

import hr.web.aplikacije.domain.Kolegij;
import hr.web.aplikacije.domain.Korisnik;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class CourseRepositoryDAOHibernate implements CourseRepositoryDAO{
	
	private SessionFactory factory;
	
	public CourseRepositoryDAOHibernate(SessionFactory factory) {
		this.factory = factory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kolegij> fetchAllKolegijList() {
		return factory.getCurrentSession().createQuery("from Kolegij").list();
	}

	@Override
	public void insertKolegijResults(Integer korisnikId, Integer kolegijId) {
		String sql = "INSERT INTO STUDOMAT.UPIS (KORISNIK_ID,KOLEGIJ_ID)VALUES ("+korisnikId+
				","+kolegijId+");";
		SQLQuery query = factory.getCurrentSession().createSQLQuery(sql);
		query.executeUpdate();
		
	}

	@Override
	public Integer retriveKorisnikId(String userName) {
		Query query = factory.getCurrentSession().createQuery("from Korisnik where userName = :name");
		query.setParameter("name", userName);
		Korisnik korisnik = (Korisnik)query.uniqueResult();
		return korisnik.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Kolegij> fetchAllKolegijListUpisani(Integer id) {
		Query query = factory.getCurrentSession().createQuery("select k from Kolegij k join k.korisnici kor where kor.id = :id");
		query.setParameter("id", id);
		return query.list();
	}

	@Override
	public int insertKolegij(Kolegij kolegij) {
		factory.getCurrentSession().persist(kolegij);
		return 1;
	}

	@Override
	public void deleteKolegij(Kolegij kolegij) {
		factory.getCurrentSession().delete(kolegij);
		
	}

}
