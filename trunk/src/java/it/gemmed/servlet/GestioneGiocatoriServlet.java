package it.gemmed.servlet;

import it.gemmed.database.CreatePlayerDatabase;
import it.gemmed.database.FindPlayerDatabase;
import it.gemmed.database.UpdateGiocatoreDatabase;
import it.gemmed.resource.Giocatore;
import it.gemmed.resource.Message;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Servlet che aggiunge al database il profilo di un giocatore che 
 * si è registrato o modifica i dati di un giocatore (telefono, città, 
 * password) a seconda del valore del parametro 'azione'.
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class GestioneGiocatoriServlet extends AbstractDatabaseServlet {

	/**
	 * @param req
	 *            Richiesta HTTP del client.
	 * @param res
	 *            Risposta HTTP del server.
	 * @throws ServletException
	 *             Se si verificano degli errori eseguendo la servlet.
	 * @throws IOException
	 *      
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String azione = req.getParameter("azione");
		
		if(azione.equals("tessera")) {
			String email = req.getParameter("email");
			String tessera = req.getParameter("tessera");
			Date scadenza = Date.valueOf(req.getParameter("data_scadenza"));
			try {
				Giocatore p = new FindPlayerDatabase(DS.getConnection()).findPlayerByEmail(email);
				p.setTessera(tessera);
				p.setScadenza_tessera(scadenza);
				new UpdateGiocatoreDatabase(DS.getConnection(), p).updateGiocatore();
				
				HttpSession session = req.getSession();
				session.setAttribute("scadenza", true);
				req.getRequestDispatcher("/profiloFederazione").forward(req, res);
			} catch (SQLException e) {
				log(e.getMessage());
			}
		}
		
		if(azione.equals("classifica")) {
			String email = req.getParameter("email");
			String classifica = req.getParameter("classifica");
			try {
				log("classifica"+classifica);
				log("email"+ email);

				Giocatore p = new FindPlayerDatabase(DS.getConnection()).findPlayerByEmail(email);
				p.setClassifica(classifica);
				new UpdateGiocatoreDatabase(DS.getConnection(), p).updateGiocatore();
				
				HttpSession session = req.getSession();
				req.getRequestDispatcher("/profiloFederazione").forward(req, res);
			} catch (SQLException e) {
				log(e.getMessage());
			}
		}
		
		if(azione.equals("circolo")) {
			String email = req.getParameter("email");
			String circolo = req.getParameter("circolo");
			try {
				

				Giocatore p = new FindPlayerDatabase(DS.getConnection()).findPlayerByEmail(email);
				p.setCircolo(circolo);
				new UpdateGiocatoreDatabase(DS.getConnection(), p).updateGiocatore();
				
				HttpSession session = req.getSession();
				req.getRequestDispatcher("/profiloFederazione").forward(req, res);
			} catch (SQLException e) {
				log(e.getMessage());
			}
		}
		
		
		if(azione.equals("crea")) {
			// request parameters
			String nome = null;
			String cognome = null;
			Date data_nascita = null;
			String email = null;
			String indirizzo = null;
			String citta = null;
			String provincia = null;
			String telefono = null;
			String password = null;
			boolean ismaschio;
			
			SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
			
			// model
			Giocatore p  = null;
			Message m = null;

			try
			{
				// retrieves the request parameters
				nome = req.getParameter("nome");
				cognome = req.getParameter("cognome");
				data_nascita = new Date(dt.parse(req.getParameter("data_nascita")).getTime());
				email = req.getParameter("email");
				telefono = req.getParameter("telefono");
				indirizzo = req.getParameter("indirizzo");
				citta = req.getParameter("citta");
				provincia = req.getParameter("provincia");
				password = req.getParameter("password");
				String sex = req.getParameter("sex");
				if(sex.equals("maschio"))
					ismaschio = true;
				else
					ismaschio = false;
					

				// creates a new player from the request parameters
				p = new Giocatore(nome, cognome, data_nascita, email, password, ismaschio, telefono, indirizzo, citta, provincia);

				// creates a new object for accessing the database and stores the player
				new CreatePlayerDatabase(DS.getConnection(), p).createEmployee();
				
				m = new Message("Player successfully created.");

			} 
			catch (NumberFormatException ex) {
				m = new Message("Cannot create the player. Invalid input parameters.", 
						"E100", ex.getMessage());
				log(ex.getMessage());
			} 
			catch (SQLException ex) {
					m = new Message("Cannot create the player: unexpected error while accessing the database.", 
							"E200", ex.getMessage());
					log(ex.getMessage());
			} catch (ParseException e) {
				log(e.getMessage());
			}
			
			Email emailToSend = new SimpleEmail();
			emailToSend.setHostName("smtp.googlemail.com");
			emailToSend.setSmtpPort(465);
			emailToSend.setAuthenticator(new DefaultAuthenticator("gemmed2@gmail.com", "iekohchu"));
			emailToSend.setSSLOnConnect(true);
			try {
				emailToSend.setFrom("gemmed2@gmail.com");
				emailToSend.setSubject("Iscrizione al sito della federazione tennis");
				emailToSend.setMsg("Grazie per essersi iscritto al sito della Federazione Italiana Tennis. I tuoi dati di accesso sono:"
						+ "Username: "+ email + 
						"Password:" + password +
						"Ricordati, se non l'hai ancora fatto, di richiedere il tesseramento al tuo circolo di appartenenza.");
				emailToSend.addTo(email);
				emailToSend.send();
			} catch (EmailException e) {
				log(e.getMessage());
			}
			
			// stores the player and the message as a request attribute
			req.setAttribute("giocatore", p);
			req.setAttribute("message", m);
			
			// forwards the control to the createEmployeeResult JSP
			req.getRequestDispatcher("/login?tipo=player").forward(req, res);
		}
		
		if(azione.equals("modifica")) {
			// request parameters
			String email = null;
			String indirizzo = null;
			String telefono = null;
			String password = null;
			String provincia = null;
			String citta = null;
			
			// model
			Giocatore p  = null;
			Message m = null;

			try
			{
				// retrieves the request parameters
				email =req.getParameter("email");
				p =	new FindPlayerDatabase(DS.getConnection()).findPlayerByEmail(email);
				telefono = req.getParameter("telefono");
				citta = req.getParameter("citta");
				provincia = req.getParameter("provincia");
				indirizzo = req.getParameter("indirizzo");
				password = req.getParameter("password");
				if(!telefono.equals("")){
					Long.parseLong(telefono);
					p.setTelefono(telefono);
				}
				if(!indirizzo.equals(""))
					p.setIndirizzo(indirizzo);
				if(!password.equals(""))
					p.setPassword(password);
				if(!citta.equals(""))
					p.setCitta(citta);
				if(!provincia.equals(""))
					p.setProvincia(provincia);
				new UpdateGiocatoreDatabase(DS.getConnection(),p).updateGiocatore();
				
				m = new Message("Giocatore successfully modified.");

			} 
			catch (NumberFormatException ex) {
				m = new Message("Cannot modify the player's profile.", "E100", ex.getMessage());
				log(ex.getMessage());
			} 
			catch (SQLException ex) {
					m = new Message("Cannot modify the profile: unexpected error while accessing the database.", "E200", ex.getMessage());
					log(ex.getMessage());
			}
			
			req.getSession().setAttribute("giocatore", p);
			req.getSession().setAttribute("message", m);
			
			req.getRequestDispatcher("/profiloGiocatore").forward(req, res);
		}

	}

}