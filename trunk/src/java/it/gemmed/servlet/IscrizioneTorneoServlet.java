package it.gemmed.servlet;

import it.gemmed.database.CreateIscrizioneDatabase;
import it.gemmed.database.UpdateIscrizioneDatabase;
import it.gemmed.database.FindPlayerDatabase;
import it.gemmed.database.FindTorneoDatabase;
import it.gemmed.resource.Giocatore;
import it.gemmed.resource.Iscrizione;
import it.gemmed.resource.Message;
import it.gemmed.resource.Torneo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.*;

/**
 * Servlet che a seconda del parametro 'azione', rispettivamente 
 * iscrive, modifica la disponibilità, disiscrive un giocatore dal torneo;
 * 
 * @author GEMMED
 * @version 1.00
 */

@SuppressWarnings("serial")
public class IscrizioneTorneoServlet extends AbstractDatabaseServlet {
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
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String azione = req.getParameter("azione");
		Iscrizione i = null;
		// request p1arameters
		int torneo = 0;
		Torneo t = null;
		Giocatore g = null;
		Boolean[] disponibilita = new Boolean[24];
		String emailGiocatore;
		log("azione="+azione);
		
		Message m;
		if("crea".equals(azione)){
			try{
				// retrieves the request parameters
				//this.log(req.getParameter("id")+" @@@@@@@@@@@@@@@@@@@@@@");
				emailGiocatore = req.getParameter("email");
				torneo = Integer.parseInt(req.getParameter("id"));
				t = new FindTorneoDatabase(DS.getConnection()).findTorneo(torneo);

				g = new FindPlayerDatabase(DS.getConnection()).findPlayerByEmail(emailGiocatore);

				for(int j = 1; j < 25 ; j++){
					if("check".equals(req.getParameter ("disponibilita"+ Integer.toString(j))))
						disponibilita[j-1] = true;
					else
						disponibilita[j-1] = false;
				}//for


				// creates a new registration from the request parameters
				i = new Iscrizione(emailGiocatore, disponibilita, torneo);

				// creates a new object for accessing the database and stores the resistration
				new CreateIscrizioneDatabase(DS.getConnection(), i).createIscrizione();

				m = new Message("Iscrizione avvenuta");

			} catch (NumberFormatException ex) {
				m = new Message("Valore di id torneo malformato.", 
						"E100", ex.getMessage());
			} catch (SQLException ex) {
				log(ex.getMessage());
			}

			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("gemmed2@gmail.com", "iekohchu"));
			email.setSSLOnConnect(true);
			try {
				email.setFrom("gemmed2@gmail.com");
				email.setSubject("Iscrizione a torneo "+t.getNome());
				email.setMsg("Ciao "+ g.getEmail() + "! Ti sei correttamente iscritto al torneo "+ t.getNome() + "." +
						"Ti ricordiamo che è possibile ritirare l'iscrizione entro il "+ t.getIscrizioneFine() + "." +
						"Il torneo avrà inizio in data" + t.getData_inizio() + ".");
				email.addTo(g.getEmail());
				email.send();
			} catch (EmailException e) {
				log(e.getMessage());
			}
			 // stores the employee and the message as a request attribute
			req.setAttribute("iscrizione", i);
			//req.setAttribute("message", m);

			// Send redirect perchè sennò si lamenta che non c'è il metodo get/post
			res.sendRedirect("profiloTorneo?id="+torneo);
		
		}//if	
			
		
		if("modifica".equals(azione)){	
			try{
				// retrieves the request parameters
				//this.log(req.getParameter("id")+" @@@@@@@@@@@@@@@@@@@@@@");
				emailGiocatore = req.getParameter("email");
				torneo = Integer.parseInt(req.getParameter("id"));
				t = new FindTorneoDatabase(DS.getConnection()).findTorneo(torneo);

				g = new FindPlayerDatabase(DS.getConnection()).findPlayerByEmail(emailGiocatore);

				for(int j = 1; j < 25 ; j++){
					if("check".equals(req.getParameter ("disponibilita"+ Integer.toString(j))))
						disponibilita[j-1] = true;
					else
						disponibilita[j-1] = false;
				}//for


				// creates a new registration from the request parameters
				i = new Iscrizione(emailGiocatore, disponibilita, torneo);

				// creates a new object for accessing the database and stores the resistration
				new UpdateIscrizioneDatabase(DS.getConnection(), i).updateIscrizione();

				m = new Message("Iscrizione modificata");

			} catch (NumberFormatException ex) {
				m = new Message("Valore di id torneo malformato.", 
						"E100", ex.getMessage());
			} catch (SQLException ex) {
				log(ex.getMessage());
			}

			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("gemmed.tennis@gmail.com", "iekohchu"));
			email.setSSLOnConnect(true);
			try {
				email.setFrom("gemmed.tennis@gmail.com");
				email.setSubject("Iscrizione a torneo "+t.getNome());
				email.setMsg("Ciao "+ g.getEmail() + "! Hai modificato l'iscrizione al torneo "+ t.getNome() + "." +
						"Ti ricordiamo che è possibile ritirare l'iscrizione entro il "+ t.getIscrizioneFine() + "." +
						"Il torneo avrà inizio in data" + t.getData_inizio() + ".");
				email.addTo(g.getEmail());
				email.send();
			} catch (EmailException e) {
				log(e.getMessage());
			}
	
			 // stores the employee and the message as a request attribute
			req.setAttribute("iscrizione", i);
			//req.setAttribute("message", m);

			// Send redirect perchè sennò si lamenta che non c'è il metodo get/post
			res.sendRedirect("profiloTorneo?id="+torneo);
		
			
		}//if
		
		if("elimina".equals(azione)){	
			try{
				// retrieves the request parameters
				//this.log(req.getParameter("id")+" @@@@@@@@@@@@@@@@@@@@@@");
				emailGiocatore = req.getParameter("email");
				torneo = Integer.parseInt(req.getParameter("id"));
				t = new FindTorneoDatabase(DS.getConnection()).findTorneo(torneo);

				g = new FindPlayerDatabase(DS.getConnection()).findPlayerByEmail(emailGiocatore);



				// creates a new registration from the request parameters
				i = new Iscrizione(emailGiocatore, disponibilita, torneo);

				// creates a new object for accessing the database and stores the resistration
				new UpdateIscrizioneDatabase(DS.getConnection(), i).deleteIscrizione();

				m = new Message("Iscrizione eliminata");

			} catch (NumberFormatException ex) {
				m = new Message("Valore di id torneo malformato.", 
						"E100", ex.getMessage());
			} catch (SQLException ex) {
				log(ex.getMessage());
			}

			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("gemmed.tennis@gmail.com", "iekohchu"));
			email.setSSLOnConnect(true);
			try {
				email.setFrom("gemmed.tennis@gmail.com");
				email.setSubject("Iscrizione a torneo "+t.getNome());
				email.setMsg("Ciao "+ g.getEmail() + "! Ti sei disicritto dal torneo "+ t.getNome() + "." +
						"Ti ricordiamo che è possibile rinnovare l'iscrizione entro il "+ t.getIscrizioneFine() + "." +
						"Il torneo avrà inizio in data" + t.getData_inizio() + ".");
				email.addTo(g.getEmail());
				email.send();
			} catch (EmailException e) {
				log(e.getMessage());
			}
	
			req.setAttribute("iscrizione", i);

			req.getRequestDispatcher("/profiloGiocatore").forward(req, res);
		
			
		}//if
		
		
	}//doPost
		

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

		Iscrizione i = null;

		// request parameters
		int torneo;
		Boolean[] disponibilita = new Boolean[24];
		String emailGiocatore;

		Message m;

		try{
			// retrieves the request parameters
			torneo = Integer.parseInt(req.getParameter("id"));


			Giocatore giocatore = (Giocatore) req.getSession().getAttribute("giocatore");
			emailGiocatore = giocatore.getEmail();


			// creates a new registration from the request parameters
			i = new Iscrizione(emailGiocatore, disponibilita, torneo);

			// creates a new object for accessing the database and stores the registration
			new CreateIscrizioneDatabase(DS.getConnection(), i).createIscrizione();

			m = new Message("Iscrizione avvenuta");

		} catch (NumberFormatException ex) {
			m = new Message("Cannot submit the registraion.", 
					"E100", ex.getMessage());
		} catch (SQLException ex) {
			//			if (e
			//					.getSQLState()
			//					.equals("23505")) {
			//				m = new Message("Cannot create the employee: employee  already exists.", 
			//						"E300", ex.getMessage());
			//			} else {
			m = new Message("Cannot create the registration: unexpected error while accessing the database.", 
					"E200", ex.getMessage());
			//			}
		}

		// stores the employee and the message as a request attribute
		req.setAttribute("iscrizione", i);
		req.setAttribute("message", m);

		// forwards the control to the createEmployeeResult JSP
		req.getRequestDispatcher("/profiloTorneo").forward(req, res);


	}

}


