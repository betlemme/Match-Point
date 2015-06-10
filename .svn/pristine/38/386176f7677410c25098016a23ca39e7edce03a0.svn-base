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


import it.gemmed.resource.PartitaSingola;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Crea una nuova partita di un torneo all'inerto del database. Sono richiesti solo i campi
 * not NULL, per settare gli altri campi utilizzare la classe UpdatePartitaSingolaDatabase
 * 
 * @author GEMMED
 * @version 0.1
 */
public class CreatePartitaSingolaDatabase {

	/**
	 * Statement SQL per l'inserimento
	 */
	private static final String STATEMENT = "INSERT INTO partita_singola (numero_partita, data, ora, campo, circolo, sfidantea, sfidanteb, vittoriasfidantea, torneo, set1, set2, set3, forfait) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?::risultato, ?::risultato, ?::risultato, ?)";
	
	/**
	 * Connessione al database
	 */
	private final Connection con;

	/**
	 * Elemento di tipo partitasingola che dese essere aggiunto al database
	 */
	private final PartitaSingola partita;

	/**
	 * Creazione di un nuovo oggetto per poter inserire elementi di tipo partitasingola nel database
	 * 
	 * @param con
	 *            connessione al database
	 * @param partita
	 *            partitasingola da salvare nel database
	 */
	public CreatePartitaSingolaDatabase(final Connection con, final PartitaSingola partita) {
		this.con = con;
		this.partita = partita;
	}//CreatePartitaSingolaDatabase

	/**
	 * Savataggio di elementi di tipo partitasingola nel database
	 * 
	 * @throws SQLException
	 *             se si verificano errori nello storing dell'elemento
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
			pstmt.setString(6,partita.getSfidanteA());
			pstmt.setString(7,partita.getSfidanteB());
			pstmt.setBoolean(8, partita.isvittoriaSfidanteA());
			pstmt.setInt(9, partita.getTorneo());
			pstmt.setString(10, partita.getSet1().toString());
			pstmt.setString(11, partita.getSet2().toString());
			pstmt.setString(12, partita.getSet3().toString());
			pstmt.setBoolean(13, partita.getForfait());

			
			pstmt.execute();

		} finally {
			if (pstmt != null) {
				pstmt.close();
			}//if

			con.close();
		}//try-catch
	}//createPartita
	
}//CreatePartitaSingolaDatabase
