package it.gemmed.database;

/*
 * Copyright 2014 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import it.gemmed.resource.PartitaDoppia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creates a new double match into the database.
 * 
 * @author GEMMED
 * @version 1.00
 */
public class CreatePartitaDoppiaDatabase {

	/**
	 * The SQL statement to be executed
	 */
	private static final String STATEMENT = "INSERT INTO partita_doppia (numero_partita, data, ora, campo, circolo, teama, teamb, vittoriateama, torneo, set1, set2, set3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?::risultato, ?::risultato, ?::risultato)";
	
	/**
	 * The connection to the database
	 */
	private final Connection con;

	/**
	 * The single match to be stored into the database
	 */
	private final PartitaDoppia partita;

	/**
	 * Creates a new object for storing a double match into the database.
	 * 
	 * @param con
	 *            the connection to the database.
	 * @param partita
	 *            the double match to be stored into the database.
	 */
	public CreatePartitaDoppiaDatabase(final Connection con, final PartitaDoppia partita) {
		this.con = con;
		this.partita = partita;
	}//CreatePartitaDoppiaDatabase

	/**
	 * Stores a new double match in the database
	 * 
	 * @throws SQLException
	 *             if any error occurs while storing the match.
	 */

	public void createPartita() throws SQLException {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(STATEMENT);
			pstmt.setInt(1, partita.getNumeroPartita());
			pstmt.setDate(2,partita.getData());
			pstmt.setTime(3, partita.getOra());
			pstmt.setInt(4, partita.getCampo());
			pstmt.setString(5, partita.getCircolo());
			pstmt.setString(6,partita.getTeamA());
			pstmt.setString(7,partita.getTeamB());
			pstmt.setBoolean(8, partita.isVittoriaTeamA());
			pstmt.setInt(9, partita.getTorneo()); 
			pstmt.setString(10, partita.getSet1().toString());
			pstmt.setString(11, partita.getSet2().toString());
			pstmt.setString(12, partita.getSet3().toString());
			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if

			con.close();
		}//try-cath

	}//createPartita
	
}//CreatePartitaDoppiaDatabase