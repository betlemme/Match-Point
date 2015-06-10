package it.gemmed.servlet;

import it.gemmed.database.CreateArbitroDatabase;
import it.gemmed.resource.Arbitro;
import it.gemmed.resource.Message;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che aggiunge al database il profilo di un utente arbitro 
 * con propria email e password;
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class CreateArbitroServlet extends AbstractDatabaseServlet {

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

		// request parameters
		String nome = null;
		String cognome = null;
		Date data_nascita = null;
		String indirizzo = null;
		String citta = null;
		String provincia = null;
		String telefono = null;
		String email = null;
		String password = null;
		String tessera = null;
		Date scadenza_tessere= null;

		// model
		Arbitro p  = null;
		Message m = null;

		try{
			// retrieves the request parameters
			nome = req.getParameter("nome");
			cognome = req.getParameter("cognome");
			data_nascita = Date.valueOf(req.getParameter("data_nascita"));
			indirizzo = req.getParameter("indirizzo");
			citta = req.getParameter("citta");
			provincia = req.getParameter("provincia");
			telefono = req.getParameter("telefono");
			email = req.getParameter("email");
			password = req.getParameter("password");
			tessera = req.getParameter("tessera");
			scadenza_tessere = Date.valueOf(req.getParameter("scadenza_tessere"));

			p = new Arbitro(nome, cognome, data_nascita, indirizzo, telefono, email, password, tessera, scadenza_tessere, citta, provincia);

			new CreateArbitroDatabase(DS.getConnection(), p).createArbitro();
			
			m = new Message("Arbitro successfully created.");

		} catch (NumberFormatException ex) {
			m = new Message("Cannot create the arbitro. Invalid input parameters.", 
					"E100", ex.getMessage());
		} catch (SQLException ex) {
				m = new Message("Cannot create the arbitro: unexpected error while accessing the database.", 
						"E200", ex.getMessage());
		}
		
		req.setAttribute("arbitro", p);
		req.setAttribute("message", m);
		
		// Si redirige verso la homepage
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, res);


	}

}
