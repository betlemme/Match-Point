package it.gemmed.servlet;

import it.gemmed.database.FindPartitaDatabase;
import it.gemmed.resource.PartitaSingola;
import it.gemmed.resource.Torneo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tumblr.jumblr.JumblrClient;
import com.tumblr.jumblr.types.Blog;
import com.tumblr.jumblr.types.Post;
/**
 * Servlet che genera la pagina di benvenuto del sito
 * 
 * @author GEMMED
 * @version 1.00
 */
@SuppressWarnings("serial")
public class HomePageServlet extends AbstractDatabaseServlet {
	
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

		// Authenticate via OAuth
		JumblrClient client = new JumblrClient(
		  "Qh91mAey1fne489Y5YFwr7Xr5gbZkanvIPw1ah6WWAbLo2EB20",
		  "T6hdiqLe6w0SYINWwO3kuIscYm0lNanbxY9KR4IGrb5Tf9tt06"
		);
		client.setToken(
		  "VrNKJD0sbepWLmNGtJqwzq4s6qc5qd9InX1fv3fgljeAaDZDDz",
		  "t1RVuseWjRAuG33vstMpHTLDSoIfE9Ft0VyVJ75cLqiiD6FqT5"
		);

		
		Blog blog = client.blogInfo("gemmed-match-point");
		
		List<Post> l = blog.posts();
		
		// Prendi solo gli ultimi 9 post
		int size = l.size();
		if(size >9)
		   l = l.subList(0, 8);
		
		List<PartitaSingola> last = null;
		
		try {
			last = new FindPartitaDatabase(DS.getConnection()).findProssimePartite();
		} catch (SQLException e) {
			log(e.getMessage());
		}
		
		req.getSession().setAttribute("posts", l);
		req.getSession().setAttribute("ultime", last);

		req.getRequestDispatcher("/jsp/index.jsp").forward(req, res);

	}

}