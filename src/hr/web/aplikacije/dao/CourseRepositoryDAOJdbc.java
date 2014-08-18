package hr.web.aplikacije.dao;

import hr.web.aplikacije.domain.Kolegij;
import hr.web.aplikacije.domain.VrstaKolegija;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepositoryDAOJdbc implements CourseRepositoryDAO{
	
	private JdbcTemplate m_jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return m_jdbcTemplate;
	}

	public void setDataSource(DataSource dataSource) {
		m_jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Kolegij> fetchAllKolegijList() {
		List<Kolegij> kolegijList = getJdbcTemplate().query("SELECT * FROM STUDOMAT.KOLEGIJ", new KolegijRowMapper());
		
		return kolegijList;
	}

	@Override
	public void insertKolegijResults(Integer korisnikId, Integer kolegijId) {
		getJdbcTemplate().update("INSERT INTO STUDOMAT.UPIS (KORISNIK_ID,KOLEGIJ_ID)VALUES (?,?)", korisnikId, kolegijId);
		
	}

	@Override
	public Integer retriveKorisnikId(String userName) {
		return getJdbcTemplate().queryForObject("SELECT KORISNIK_ID FROM STUDOMAT.KORISNIK WHERE KORISNICKO_IME = ?", Integer.class, userName);
	}

	@Override
	public List<Kolegij> fetchAllKolegijListUpisani(Integer id) {
		List<Kolegij> listKolegijUpisani = getJdbcTemplate().query("SELECT k.KOLEGIJ_ID, NAZIV,ECTS,VRSTA_KOLEGIJA FROM STUDOMAT.KOLEGIJ k "
				+ "LEFT JOIN STUDOMAT.UPIS u ON k.KOLEGIJ_ID = u.KOLEGIJ_ID LEFT JOIN STUDOMAT.KORISNIK kr ON u.KORISNIK_ID = kr.KORISNIK_ID "
				+ "WHERE kr.KORISNIK_ID = ?", new KolegijRowMapper(), id);
		
		return listKolegijUpisani;
	}
	
	private class KolegijRowMapper implements RowMapper<Kolegij> {
		public Kolegij mapRow(ResultSet rs, int rowNum) throws SQLException {
			return mapKolegij(rs);
		}

		private Kolegij mapKolegij(ResultSet rs) throws SQLException {
			int kolegijId = rs.getInt("KOLEGIJ_ID");
			String naziv = rs.getString("NAZIV");
			int ects = rs.getInt("ECTS");
			int vrstaKolegijaId = rs.getInt("VRSTA_KOLEGIJA");
			Kolegij kolegij = null;
			if (Kolegij.VRSTA_KOLEGIJA_OBVEZNI_ID == vrstaKolegijaId) {
				kolegij = new Kolegij(kolegijId, naziv, VrstaKolegija.OBVEZNI, 
						ects);
			} else {
				kolegij = new Kolegij(kolegijId, naziv, VrstaKolegija.IZBORNI, 
						ects);
			}
			return kolegij;
		}
	}

	@Override
	public int insertKolegij(Kolegij kolegij) {
		int vrsta = (kolegij.getVrstaKolegija() == VrstaKolegija.OBVEZNI) ? 1 : 2;
		return getJdbcTemplate().update("INSERT INTO STUDOMAT.KOLEGIJ (NAZIV,ECTS,VRSTA_KOLEGIJA)VALUES (?,?,?)", 
				kolegij.getNazivKolegija(), kolegij.getEcts(),vrsta);
	}

	@Override
	public void deleteKolegij(Kolegij kolegij) {
		getJdbcTemplate().update("DELETE FROM STUDOMAT.KOLEGIJ WHERE KOLEGIJ_ID = ?",kolegij.getId());
		
	}

}
