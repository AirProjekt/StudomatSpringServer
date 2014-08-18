package hr.web.aplikacije.service;

import hr.web.aplikacije.dao.CourseRepositoryDAO;
import hr.web.aplikacije.domain.Kolegij;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepositoryDAO courseRepositoryDAO;

	@Override
	public List<Kolegij> fetchAllKolegijList() {
		return courseRepositoryDAO.fetchAllKolegijList();
	}

	@Override
	public void insertKolegijResults(Integer korisnikId, Integer kolegijId) {
		courseRepositoryDAO.insertKolegijResults(korisnikId, kolegijId);
	}

	@Override
	public Integer retriveKorisnikId(String userName) {
		return courseRepositoryDAO.retriveKorisnikId(userName);
	}

	@Override
	public List<Kolegij> fetchAllKolegijListUpisani(Integer id) {
		return courseRepositoryDAO.fetchAllKolegijListUpisani(id);
	}

	@Override
	public int insertKolegij(Kolegij kolegij) {
		return courseRepositoryDAO.insertKolegij(kolegij);
	}

	@Override
	public void deleteKolegij(Kolegij kolegij) {
		courseRepositoryDAO.deleteKolegij(kolegij);
		
	}

}
