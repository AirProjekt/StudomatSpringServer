package hr.web.aplikacije.dao;

import hr.web.aplikacije.domain.Kolegij;

import java.util.List;

public interface CourseRepositoryDAO {
	
	public List<Kolegij> fetchAllKolegijList();
	
	public void insertKolegijResults(Integer korisnikId, Integer kolegijId);
	
	public Integer retriveKorisnikId(String userName);
	
	public List<Kolegij> fetchAllKolegijListUpisani(Integer id);
	
	public int insertKolegij(Kolegij kolegij);
	
	public void deleteKolegij(Kolegij kolegij);
	
}
