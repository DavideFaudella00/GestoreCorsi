package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLException;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {
	public List<Corso> getCorsiByPeriodo(int periodo) {
		String sql = "SELECT * " + "FROM corso " + "WHERE pd = ?";
		List<Corso> result = new ArrayList<Corso>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
						rs.getInt("pd"));
				result.add(c);
			}
			conn.close();
			return result;
		} catch (SQLException e) {
			System.out.println("Errore nel DAO");
			e.printStackTrace();
			return null;
		}
	}

	public Map<Corso, Integer> getIscritti(int periodo) {
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd, COUNT(*) AS n " +
				"FROM corso c, iscrizione i "+
				"WHERE c.codins = i.codins AND c.pd = ? " + 
				"GROUP BY c.codins, c.crediti, c.nome, c.pd";
		Map<Corso, Integer> result = new HashMap<Corso, Integer>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, periodo);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
						rs.getInt("pd"));
				result.put(c, rs.getInt("n"));
			}
			conn.close();
			st.close();
			return result;
		} catch (SQLException e) {
			System.out.println("Errore nel DAO 2");
			e.printStackTrace();
			return null;
		}
	}
}
