package it.gemmed.servlet;

import it.gemmed.database.CreateCircoloDatabase;
import it.gemmed.resource.Circolo;
import it.gemmed.resource.Message;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che permette la creazione di un nuovo utente circolo 
 * nel database, con una propria email e password;
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class CreateCircoloServlet extends AbstractDatabaseServlet {

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
		String telefono = null;
		String indirizzo = null;
		String citta = null;
		String provincia = null;
		String email = null;
		String password = null;

		// model
		Circolo c  = null;
		Message m = null;

		try{
			nome = req.getParameter("nome");
			telefono = req.getParameter("telefono");
			indirizzo = req.getParameter("indirizzo");
			citta = req.getParameter("citta");
			provincia = req.getParameter("provincia");
			email = req.getParameter("email");
			password = req.getParameter("password");

			c = new Circolo(nome, indirizzo, citta, provincia, telefono, email, password);

			new CreateCircoloDatabase(DS.getConnection(), c).createCircolo();
			
			m = new Message("Circolo successfully created.");

		} catch (NumberFormatException ex) {
			m = new Message("Cannot create the employee. Invalid input parameters: identifier, age, and salary must be integer.", 
					"E100", ex.getMessage());
		} catch (SQLException ex) {
				m = new Message("Cannot create the employee: unexpected error while accessing the database.", 
						"E200", ex.getMessage());
		}
		
		// stores the employee and the message as a request attribute
		req.setAttribute("circolo", c);
		req.setAttribute("message", m);
		
		// forwards the control to the createEmployeeResult JSP
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, res);


	}

}
