package it.gemmed.servlet;

import it.gemmed.database.FindArbitroDatabase;
import it.gemmed.database.UpdateArbitroDatabase;
import it.gemmed.resource.Arbitro;
import it.gemmed.resource.Message;


import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet con funzione dipendente da un parametro:
 * se il parametro passato alla chiamata corrisponde a 'azione=modifica', 
 * aggiorna i dati dell'arbitro (nuova email, numero di telefono, città) 
 * nel database. Se 'azione=tessera' permette l'assegnazione di una tessera 
 * ad un arbitro da parte di un addetto federale;
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class GestioneArbitroServlet extends AbstractDatabaseServlet {

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

		// request parameters
		String email = null;
		String indirizzo = null;
		String citta = null;
		String provincia = null;
		String telefono = null;
		String password = null;
		// request parameters				
		String tessera = null;
		Date scadenza_tessere = null;

		// model
		Arbitro a  = null;
		Message m = null;

		if("modifica".equals(req.getParameter("azione"))){
			try
			{
				// retrieves the request parameters
				email =req.getParameter("email");
				a =	new FindArbitroDatabase(DS.getConnection()).findArbitro(email);
				telefono = req.getParameter("telefono");
				indirizzo = req.getParameter("indirizzo");
				citta = req.getParameter("citta");
				provincia = req.getParameter("provincia");
				password = req.getParameter("password");

				if(!telefono.equals(""))
					a.setTelefono(telefono);
				if(!indirizzo.equals(""))
					a.setIndirizzo(indirizzo);
				if(!password.equals(""))
					a.setPassword(password);
				if(!citta.equals(""))
					a.setCitta(citta);
				if(!provincia.equals(""))
					a.setProvincia(provincia);
				new UpdateArbitroDatabase(DS.getConnection(),a).updateArbitro();

				m = new Message("Arbitro successfully modified.");

			} 
			catch (NumberFormatException ex) {
				m = new Message("Cannot modify the profile.", "E100", ex.getMessage());
				log(ex.getMessage());
			} 
			catch (SQLException ex) {
				m = new Message("Cannot modify the profile: unexpected error while accessing the database.", "E200", ex.getMessage());
				log(ex.getMessage());
			}

			req.getSession().setAttribute("arbitro", a);
			req.getSession().setAttribute("message", m);

			req.getRequestDispatcher("/profiloArbitro").forward(req, res);
		}//if
		
		if("tessera".equals(req.getParameter("azione"))){
			try
			{
				// retrieves the request parameters
				email =req.getParameter("email");	
				a =	new FindArbitroDatabase(DS.getConnection()).findArbitro(email);
				scadenza_tessere = Date.valueOf(req.getParameter("data_scadenza"));
				a.setScadenza_tessere(scadenza_tessere);

				new UpdateArbitroDatabase(DS.getConnection(),a).updateArbitro();

				m = new Message("Arbitro successfully modified.");

			} 
			catch (NumberFormatException ex) {
				m = new Message("Cannot modify the judge.", "E100", ex.getMessage());
				log(ex.getMessage());
			} 
			catch (SQLException ex) {
				m = new Message("Cannot modify the profile: unexpected error while accessing the database.", "E200", ex.getMessage());
				log(ex.getMessage());
			}

			req.getSession().setAttribute("message", m);

			req.getRequestDispatcher("/profiloFederazione").forward(req, res);

		}//if
	}//doPost		
}//GestioneArbitroServlet