package it.gemmed.servlet;

import it.gemmed.database.FindArbitroDatabase;
import it.gemmed.database.FindCircoloDatabase;
import it.gemmed.database.FindPlayerDatabase;
import it.gemmed.database.FindUtenteFederaleDatabase;
import it.gemmed.resource.Arbitro;
import it.gemmed.resource.Circolo;
import it.gemmed.resource.Giocatore;
import it.gemmed.resource.Message;
import it.gemmed.resource.UtenteFederale;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet che permette di effettuare il log-in al sito da parte di un
 * giocatore, un circolo, un arbitro o un addetto della federazione. Il log-in
 * risulta essere diverso a seconda del parametro 'tipo' specificato e avviene 
 * inserendo email e password;
 * 
 * @author GEMMED
 * @version 1.00
 */

@SuppressWarnings("serial")
public class LoginServlet extends AbstractDatabaseServlet {
	
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
		String tipo = req.getParameter("tipo");
		
		if(tipo.equals("player"))
		{
			String email = req.getParameter("email");
			Message m = null;
			Giocatore p;
			try {
				p = new FindPlayerDatabase(DS.getConnection()).findPlayerByEmail(email);
				if (p == null) {
					m = new Message("Email not found (not best practice).", 
							"E100", null);
					req.setAttribute("message", m);
					req.getRequestDispatcher("/jsp/FailedLogin.jsp").forward(req,
							res);
					return;
				}
	
				String password = req.getParameter("password");
				if (password == null || !p.getPassword().equals(password)) {
					m = new Message("Password wrong(not best practice).", 
							"E100", null);
					req.setAttribute("message", m);
					req.getRequestDispatcher("/jsp/FailedLogin.jsp").forward(req,
							res);
					return;
				}
	
				HttpSession session = req.getSession();
				//String userId = p.get(0).getEmail();
				session.setAttribute("giocatore", p);
				req.getRequestDispatcher("/profiloGiocatore").forward(req, res);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(tipo.equals("circolo"))
		{
			String email = req.getParameter("email");
			Message m = null;
			List<Circolo> p;
			try {
				p = new FindCircoloDatabase(DS.getConnection()).findCircolo(email);
				if (p.size() != 1) {
					m = new Message("Email not found (not best practice).", 
							"E100", null);
					req.setAttribute("message", m);
					req.getRequestDispatcher("/jsp/FailedLogin.jsp").forward(req,
							res);
					return;
				}

				String password = req.getParameter("password");
				if (password == null || !p.get(0).getPassword().equals(password)) {
					m = new Message("Password wrong(not best practice).", 
							"E100", null);
					req.setAttribute("message", m);
					req.getRequestDispatcher("/jsp/FailedLogin.jsp").forward(req,
							res);
					return;
				}

				HttpSession session = req.getSession();
				session.setAttribute("circolo", p.get(0));
				req.getRequestDispatcher("/profiloCircolo").forward(req, res);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (tipo.equals("arbitro"))
		{
			String email = req.getParameter("email");
			Message m = null;
			Arbitro p;
			try {
				p = new FindArbitroDatabase(DS.getConnection()).findArbitro(email);
				if (p == null) {
					m = new Message("Email not found (not best practice).", 
							"E100", null);
					req.setAttribute("message", m);
					req.getRequestDispatcher("/jsp/FailedLogin.jsp").forward(req,
							res);
					return;
				}
	
				String password = req.getParameter("password");
				if (password == null || !p.getPassword().equals(password)) {
					m = new Message("Password wrong(not best practice).", 
							"E100", null);
					req.setAttribute("message", m);
					req.getRequestDispatcher("/jsp/FailedLogin.jsp").forward(req,
							res);
					return;
				}
	
				HttpSession session = req.getSession();
				session.setAttribute("arbitro", p);
				req.getRequestDispatcher("/profiloArbitro").forward(req, res);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (tipo.equals("federazione"))
		{
			String email = req.getParameter("email");
			Message m = null;
			List<UtenteFederale> p;
			try {
				p = new FindUtenteFederaleDatabase(DS.getConnection(), email).findUtente();
				if (p.size() != 1) {
					m = new Message("Email not found (not best practice).", 
							"E100", null);
					req.setAttribute("message", m);
					req.getRequestDispatcher("/jsp/FailedLogin.jsp").forward(req,
							res);
					return;
				}

				String password = req.getParameter("password");
				if (password == null || !p.get(0).getPassword().equals(password)) {
					m = new Message("Password wrong(not best practice).", 
							"E100", null);
					req.setAttribute("message", m);
					req.getRequestDispatcher("/jsp/FailedLogin.jsp").forward(req,
							res);
					return;
				}

				HttpSession session = req.getSession();
				session.setAttribute("federazione", p.get(0));
				getServletContext().log("Test 1234567");
				req.getRequestDispatcher("/profiloFederazione").forward(req, res);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
