package it.gemmed.servlet;

import it.gemmed.database.CreatePartitaDatabase;
import it.gemmed.database.CreateTorneoDatabase;
import it.gemmed.database.FindPlayerDatabase;
import it.gemmed.database.FindPartitaDatabase;
import it.gemmed.database.UpdatePartitaSingolaDatabase;


import it.gemmed.resource.Giocatore;
import it.gemmed.resource.PartitaSingola;
import it.gemmed.resource.Torneo;
import it.gemmed.resource.Message;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che genera le partite di un torneo, in base al 
 * numero di giocatori iscritti a tale torneo, secondo la struttura del tabellone tennistico;
 * 
 * @author GEMMED
 * @version 1.00
 */

@SuppressWarnings("serial")
public class CreateTabelloneServlet extends AbstractDatabaseServlet {

	/**
	 * @param req
	 *            Richiesta HTTP del client.
	 * @param res
	 *            Risposta HTTP del server.
	 * 
	 * @throws ServletException
	 *             Se si verificano degli errori eseguendo la servlet.
	 * @throws IOException
	 *             Se si verificano degli errori nel comunicazione tra client/server.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		List<PartitaSingola> p = new ArrayList<PartitaSingola>();
		List<Giocatore> l = null;
		int id = -1;
		String circolo = null;
		PartitaSingola partita =null;
		Message m = null;
		
		try {
			id = Integer.parseInt(req.getParameter("id"));
			circolo = req.getParameter("circolo");
			l = new FindPlayerDatabase(DS.getConnection()).findPlayerByTorneo(id);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int n = l.size();//numero giocatori iscritti => n-1 partite
		log("Numero giocatori iscritti:"+n);

		//la lista di partite p è fatta così: 
		//le partite sono numerate da 1 a n-1 (pa finale è 1, la semifinale 1 è 2 ecc...)
		//le partite "figlie" di una partita con numeroPartita i  sono quelle con numero partita 2i e 2i+1.
		
		//ora creo n/2-1 partite senza partecipanti
		for (int i = 1; i <= (n-1)/2; i++) {
			//queste 2 stringhe in realtà non servono.
			String giocatoreA = "vinc part 2*i";
			String giocatoreB = "vinc part 2*i+1";
			log("Valore del primo ciclo:"+i);
			partita = new PartitaSingola(i, id, giocatoreA, giocatoreB);
			partita.setCircolo(circolo);
			
			try {
				new CreatePartitaDatabase(DS.getConnection(), partita).createPartita();
				
				m = new Message("Employee successfully created.");

			} catch (NumberFormatException ex) {
				m = new Message("Cannot create the employee. Invalid input parameters: identifier, age, and salary must be integer.", 
						"E100", ex.getMessage());
			} catch (SQLException ex) {
				if (ex.getSQLState().equals("23505")) {
					m = new Message("Cannot create the employee: employee " + id + " already exists.", 
							"E300", ex.getMessage());
				} else {
					m = new Message("Cannot create the employee: unexpected error while accessing the database.", 
							"E200", ex.getMessage());
				}
			}
			
			p.add(partita);
			
		}		
		
		//ora creo n/2 partite inizializzate coi giocatori iscritti
		int j = 0;
		for (int i = ((n-1)/2)+1; i <= n-1; i++) {
			log("Valore del secondo ciclo:"+i);

			String giocatoreA = l.get(j).getEmail();
			j++;
			log("valore di j(primo ++):"+j);
			String giocatoreB = l.get(j).getEmail();
			j++;
			log("valore di j(secondo ++):"+j);
			partita = new PartitaSingola(i,id, giocatoreA, giocatoreB);
			partita.setCircolo(circolo);
			
			try {
				new CreatePartitaDatabase(DS.getConnection(), partita).createPartita(giocatoreA, giocatoreB);
				
				m = new Message("Employee successfully created.");

			} catch (NumberFormatException ex) {
				m = new Message("Cannot create the employee. Invalid input parameters: identifier, age, and salary must be integer.", 
						"E100", ex.getMessage());
			} catch (SQLException ex) {
				if (ex.getSQLState().equals("23505")) {
					m = new Message("Cannot create the employee: employee " + id + " already exists.", 
							"E300", ex.getMessage());
				} else {
					m = new Message("Cannot create the employee: unexpected error while accessing the database.", 
							"E200", ex.getMessage());
				}
			}
			
			p.add(partita);
				
		}
	
		
		// if eseguito solo se il numero di giocatori (n) è dispari: imposta l'ultimo giocatore che rimane nella lista
				// l (lista di Giocatori) come sfidanteB alla partita numero (n-1)/2, cioè quella partita nell'albero che ha
				//si ritrova ad avere un solo figlio. il -1 successivo viene dato per il motivo che in una lista il primo 
				//elemento è in posizione '0'.
				
		if( (n % 2) != 0 ){
			log("Tabellone:Sono dispari");
			p.get( (n-1)/2 -1).setSfidanteB( l.get(j).getEmail());
			log("numero dell partita sfindante B:"+p.get( (n-1)/2 -1).getNumeroPartita());
			PartitaSingola pfantasma;
			try {
				pfantasma = new FindPartitaDatabase(DS.getConnection()).findPartita( id, ((n-1)/2 ) );
				new UpdatePartitaSingolaDatabase(DS.getConnection(), pfantasma).updateSfidanteB(l.get(j).getEmail());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//if
		
		

		
		// stores the employee and the message as a request attribute
		req.setAttribute("tabellone", p);
		req.setAttribute("message", m);
		
		// forwards the control to the createEmployeeResult JSP
		req.getRequestDispatcher("/profiloTorneoArbitro").forward(req, res);


	}

}
