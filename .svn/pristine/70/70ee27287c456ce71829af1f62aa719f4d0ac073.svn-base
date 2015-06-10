package it.gemmed.servlet;

import it.gemmed.database.CreateCircoloDatabase;
import it.gemmed.database.FindCampoDatabase;
import it.gemmed.resource.Campo;
import it.gemmed.resource.Circolo;
import it.gemmed.resource.Message;
import it.gemmed.resource.Tiposuperficie;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che permette a un circolo di aggiungere un campo da
 * gioco, specificando numero identificativo del campo e tipo di terreno.
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class CreateCampoServlet extends AbstractDatabaseServlet {

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
		int numero = 0;
		String superficie = null;
		String email_circolo = null;

		// model
		Campo c  = null;
		Circolo circolo =null;
		Message m = null;

		try{
			numero = Integer.parseInt(req.getParameter("numero"));
			superficie = req.getParameter("superficie");
			email_circolo = req.getParameter("circolo");

			c = new Campo(numero, Tiposuperficie.valueOf(superficie), email_circolo);
			circolo= new Circolo(email_circolo);
			new FindCampoDatabase(DS.getConnection()).createCampo(c);
			
			m = new Message("Campo added succesfull.");

		} catch (NumberFormatException ex) {
			m = new Message("Cannot create the employee. Invalid input parameters: identifier, age, and salary must be integer.", 
					"E100", ex.getMessage());
		} catch (SQLException ex) {
				m = new Message("Cannot create the employee: unexpected error while accessing the database.", 
						"E200", ex.getMessage());
		}
		
		// stores the employee and the message as a request attribute
		req.setAttribute("circolo", circolo);
		req.setAttribute("message", m);
		
		// forwards the control to the createEmployeeResult JSP
		req.getRequestDispatcher("/profiloCircolo").forward(req, res);


	}

}
