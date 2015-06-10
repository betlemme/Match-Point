package it.gemmed.servlet;

import it.gemmed.database.FindCampoDatabase;
import it.gemmed.database.FindTorneoDatabase;
import it.gemmed.resource.Campo;
import it.gemmed.resource.Message;
import it.gemmed.resource.Torneo;
import it.gemmed.resource.Circolo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet che genera la pagina profilo in html di un circolo.
 * 
 * @author GEMMED
 * @version 1.00
 */

@SuppressWarnings("serial")
public class ProfiloCircoloServlet extends AbstractDatabaseServlet {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Message m = null;
		List<Torneo> p;
		List<Campo> c;
		Circolo circolo = (Circolo) req.getSession().getAttribute("circolo");
		String emailCircolo = circolo.getEmail();
		try {
			p = new FindTorneoDatabase(DS.getConnection()).findTorneo(emailCircolo);
			c= new FindCampoDatabase(DS.getConnection()).findCampi(emailCircolo);

			req.setAttribute("campi", c);
			req.setAttribute("tornei", p);
			req.getRequestDispatcher("/jsp/profilo-circolo.jsp").forward(req, res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // doPost
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
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		Message m = null;
		List<Torneo> p;
		List<Campo> c;
		Circolo circolo = (Circolo) req.getSession().getAttribute("circolo");
		String emailCircolo = circolo.getEmail();
		try {
			p = new FindTorneoDatabase(DS.getConnection()).findTorneo(emailCircolo);
			c= new FindCampoDatabase(DS.getConnection()).findCampi(emailCircolo);

			req.setAttribute("campi", c);
			req.setAttribute("tornei", p);
			req.getRequestDispatcher("/jsp/profilo-circolo.jsp").forward(req, res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // doPost
}