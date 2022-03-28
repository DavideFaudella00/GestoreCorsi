package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDAO;

	public Model() {
		this.corsoDao = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}

	public List<Corso> getCorsiByPeriodo(int periodo) {
		return this.corsoDao.getCorsiByPeriodo(periodo);
	}

	public Map<Corso, Integer> getIscritti(int peridodo) {
		return this.corsoDao.getIscritti(peridodo);
	}

	public List<Studente> getStudentiByCorso(String codins) {
		return this.studenteDAO.getStudentiByCorso(codins);
	}

	public List<Divisione> getDivisioneStudenti(String codins) {
		return this.studenteDAO.getDivisioneStudenti(codins);
	}
}
