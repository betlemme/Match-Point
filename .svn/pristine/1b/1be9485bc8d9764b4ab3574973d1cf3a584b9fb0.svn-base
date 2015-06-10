package it.gemmed.servlet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

/**
 * Recupera il {@code DataSource} per gestire il pool di connessioni del database.
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public abstract class AbstractDatabaseServlet extends HttpServlet {

	/**
	 * Pool di connessioni del database
	 */
	protected static final DataSource DS;

	static {

		InitialContext cxt;
		DataSource ds = null;
		try {
			cxt = new InitialContext();
			ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/studenti");
		} catch (NamingException e) {
			ds = null;

			throw new IllegalStateException(
					"Impossible to access the connection pool to the database: "
							+ e.getMessage());
		} finally {
			DS = ds;
		}

	}

}
