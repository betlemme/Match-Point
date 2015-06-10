package it.gemmed.servlet;

import it.gemmed.database.CreateUtenteFederaleDatabase;
import it.gemmed.resource.UtenteFederale;
import it.gemmed.resource.Message;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che aggiunge al database il profilo  * di un utente federale 
 * con propria email e password
 *
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class CreateUtenteFederaleServlet extends AbstractDatabaseServlet {

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
		String email = null;
		String password = null;

		// model
		UtenteFederale u  = null;
		Message m = null;

		try{
			// retrieves the request parameters
			email = req.getParameter("email");
			password = req.getParameter("password");

			// creates a new employee from the request parameters
			u = new UtenteFederale(email, password);

			// creates a new object for accessing the database and stores the employee
			new CreateUtenteFederaleDatabase(DS.getConnection(), u).createUtenteFederale();
			
			m = new Message("Utente federale successfully created.");

		} catch (NumberFormatException ex) {
			m = new Message("Cannot create the employee. Invalid input parameters: identifier, age, and salary must be integer.", 
					"E100", ex.getMessage());
		} catch (SQLException ex) {
//			if (e
//					.getSQLState()
//					.equals("23505")) {
//				m = new Message("Cannot create the employee: employee  already exists.", 
//						"E300", ex.getMessage());
//			} else {
				m = new Message("Cannot create the employee: unexpected error while accessing the database.", 
						"E200", ex.getMessage());
//			}
		}
		
		// stores the employee and the message as a request attribute
		req.setAttribute("federale", u);
		req.setAttribute("message", m);
		
		// forwards the control to the createEmployeeResult JSP
		req.getRequestDispatcher("/jsp/profilo-federazione.jsp").forward(req, res);


	}

}
