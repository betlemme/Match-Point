package it.gemmed.servlet;

import it.gemmed.database.FindUtenteFederaleDatabase;
import it.gemmed.database.UpdateUtenteFederaleDatabase;
import it.gemmed.resource.UtenteFederale;
import it.gemmed.resource.Message;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che consente la modifica della password dell'account di un utente federale
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class GestioneFederazioneServlet extends AbstractDatabaseServlet {

	/**
	 * @param req
	 *            Richiesta HTTP del client.
	 * @param res
	 *            Risposta HTTP del server.
	 * 
	 * @throws ServletException
	 *             Se si verificano degli errori eseguendo la servlet.
	 * @throws IOException
	 *      
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		// request parameters
					String email = null;
					String password = null;
					
					// model
					UtenteFederale f  = null;
					Message m = null;

					try
					{
						// retrieves the request parameters
						email =req.getParameter("email");
						f =	new FindUtenteFederaleDatabase(DS.getConnection()).findUtente(email);
						password = req.getParameter("password");
						f.setPassword(password);
						new UpdateUtenteFederaleDatabase(DS.getConnection(),f).updateUtenteFederale();
						
						m = new Message("Utente successfully modified.");

					} 
					catch (NumberFormatException ex) {
						m = new Message("Cannot modify the profile.", "E100", ex.getMessage());
						log(ex.getMessage());
					} 
					catch (SQLException ex) {
							m = new Message("Cannot modify the profile: unexpected error while accessing the database.", "E200", ex.getMessage());
							log(ex.getMessage());
					}
					
					req.getSession().setAttribute("federazione", f);
					req.getSession().setAttribute("message", m);
					
					req.getRequestDispatcher("/profiloFederazione").forward(req, res);
				}

			}