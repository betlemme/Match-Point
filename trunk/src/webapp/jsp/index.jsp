<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head lang="en">

<meta charset="UTF-8">
<title>Match Point</title>

<c:import url="/jsp/include/head.jsp" />

<style type="text/css">
.jumbotron {
	padding-top: 50px;
	height: 400px;
	background-image: url(img/sfondo.jpg);
}
      html,
      body {
        height: 100%;
        /* The html and body elements cannot have any padding or margin. */
      }
      /* Wrapper for page content to push down footer */
      #push {
      	height: 90px;
      }
      
      #wrap {
        min-height: 100%;
        height: auto !important;
        height: 100%;
        /* Negative indent footer by it's height */
        margin: 0 auto -90px;
      }
      #footer {
        height: 90px;
        background-color: #2e303f; 
        width: 100%;
      }
</style>

	 <script>
	 $(function() {
		 $( "#datepicker" ).datepicker();
		 });
	 </script>

</head>

<body>
	<div id="wrap">
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Match Point</a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<c:if
						test="${giocatore.email == null && arbitro.email == null && circolo.email == null && federazione.email == null}">

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Login federali <span class="caret"></span></a>
							<ul class="dropdown-menu" role="menu">
								<li><a href='' data-toggle="modal" data-target="#circolo">Circolo</a></li>
								<li><a href="" data-toggle="modal" data-target="#arbitro">Arbitro</a></li>
								<li><a href="" data-toggle="modal"
									data-target="#federazione">Federazione</a></li>
							</ul></li>
					</c:if>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><c:choose>
							<c:when	test="${giocatore.email != null}">
								<ul class="nav navbar-nav navbar-right">
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown"><c:out value="${giocatore.email}" /> <b
											class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/profiloGiocatore"/>">Profilo</a></li>
											<li class="divider"></li>
											<li><a href="<c:url value="/logout"/>">Log Out</a></li>
										</ul></li>
								</ul>
							</c:when>
							<c:when test="${arbitro.email != null}">
								<ul class="nav navbar-nav navbar-right">
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown"><c:out value="${arbitro.email}" /> <b
											class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/profiloArbitro"/>">Profilo</a></li>
											<li class="divider"></li>
											<li><a href="<c:url value="/logout"/>">Log Out</a></li>
										</ul></li>
								</ul>
							</c:when>
							<c:when test="${circolo.email != null}">
								<ul class="nav navbar-nav navbar-right">
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown"><c:out value="${circolo.email}" /> <b
											class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/profiloCircolo"/>">Profilo</a></li>
											<li class="divider"></li>
											<li><a href="<c:url value="/logout"/>">Log Out</a></li>
										</ul></li>
								</ul>
							</c:when>		
							<c:when test="${federazione.email != null}">
								<ul class="nav navbar-nav navbar-right">
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown"><c:out value="${federazione.email}" /> <b
											class="caret"></b></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value="/profiloFederazione"/>">Profilo</a></li>
											<li class="divider"></li>
											<li><a href="<c:url value="/logout"/>">Log Out</a></li>
										</ul></li>
								</ul>
							</c:when>	
							<c:otherwise>
								<form class="navbar-form" role="form" method="post"
									action="<c:url value="/login?tipo=player"/>">
									<div class="form-group">
										<input type="email" name="email" class="form-control"
											id="InputEmail1" placeholder="Enter email">
									</div>
									<div class="form-group">
										<input type="password" name="password" class="form-control"
											id="InputPassword1" placeholder="Password">
									</div>
									<button type="submit" class="btn btn-default">Login</button>

								</form>
								<li><div class="navbar-form">
										<button class="btn btn-info" data-toggle="modal"
											data-target="#myModal">Registrazione</button>
									</div></li>
							</c:otherwise>
						</c:choose></li>
				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>



	<div class="jumbotron">
		<h1 style='color: #ffffff;'>Federazione Italiana Tennis</h1>
		<p style='color: #ffffff;'>Questo è il tuo nuovo sito in cui puoi
			visualizzare tutte le informazioni riguardo tornei, strutture e
			circoli che la Federazione Italiana Tennis rende disponibili per te.</p>
	</div>

	<div class="container" style='padding-top: 20px;'>

		<!-- Colonna contenente il login per i giocatori e per la federazione -->
		<div class="row">

			<!-- Div contenente le ultime da tumblr -->
			<div class="col-md-4 col-md-offset-1">

				<div class="panel panel-default">
					<div class="panel-body">
						<h3>
							<a href="https://www.tumblr.com/blog/gemmed-match-point">Latest
								from Tumblr</a>
						</h3>
						<table class="table">
							<thead></thead>
							<tbody>
								<c:forEach var="post" items="${posts}">
									<tr>
										<td><c:out value="${post.text}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

			</div>

			<!-- Div contenente le ultime sulle partite -->
			<div class="col-md-7">

				<div class="panel panel-default">
					<div class="panel-body">
						<h3>Ultime partite</h3>
						<table class="table">
							<thead>
								<th>Sfidante</th>
								<th>Sfidante</th>
								<th>Data</th>
								<th>Ora</th>
								<th>Torneo</th>
							</thead>
							<tbody>
								<c:forEach var="partita" items="${ultime}">
									<tr>
										<td><c:out value="${partita.sfidanteA}" /></td>
										<td><c:out value="${partita.sfidanteB}" /></td>
										<td><c:out value="${partita.data}" /></td>
										<td><c:out value="${partita.ora}" /></td>
										<td><c:out value="${partita.nomeTorneo}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

				</div>

			</div>
		</div>
	</div>
	<div id="push"></div>
		<!-- wrap -->
	</div>
	<!-- Footer -->
	<div id="footer">
		<div class="container">
			<div class="row">
				<div class="col-md-6">
					<aside class="widget widget_crafty-social-buttons_widget">
						<div
							class="crafty-social-buttons crafty-social-link-buttons crafty-social-buttons-size-4">
							<span style='color: white' class="crafty-social-caption">Seguici su:</span>
							<ul class="list-inline">
								<li><a class="crafty-social-button csb-facebook"
									href="https://www.facebook.com/FederazioneItalianaTennis"
									target="_blank"><img title="Facebook" alt="Facebook"
										width="48" height="48"
										src="http://www.fetb.it/wp-content/plugins/crafty-social-buttons/buttons/arbenting/facebook.png" /></a></li>
								<li><a class="crafty-social-button csb-youtube"
									href="http://youtube.com/user/" target="_blank"><img
										title="YouTube" alt="YouTube" width="48" height="48"
										src="http://www.fetb.it/wp-content/plugins/crafty-social-buttons/buttons/arbenting/youtube.png" /></a></li>
								<li><a class="crafty-social-button csb-google"
									href="http://plus.google.com/" target="_blank"><img
										title="Google" alt="Google" width="48" height="48"
										src="http://www.fetb.it/wp-content/plugins/crafty-social-buttons/buttons/arbenting/google.png" /></a></li>
								<li><a class="crafty-social-button csb-twitter"
									href="http://twitter.com/" target="_blank"><img
										title="Twitter" alt="Twitter" width="48" height="48"
										src="http://www.fetb.it/wp-content/plugins/crafty-social-buttons/buttons/arbenting/twitter.png" /></a></li>
								<li><a class="crafty-social-button csb-instagram"
									href="http://instagram.com/" target="_blank"><img
										title="Instagram" alt="Instagram" width="48" height="48"
										src="http://www.fetb.it/wp-content/plugins/crafty-social-buttons/buttons/arbenting/instagram.png" /></a></li>
								<li><a class="crafty-social-button csb-rss"
									href="<c:url value="/rss"/>"><img title="RSS"
										alt="RSS" width="48" height="48"
										src="http://www.fetb.it/wp-content/plugins/crafty-social-buttons/buttons/arbenting/rss.png" /></a></li>
							</ul>
						</div>
					</aside>
				</div>
				<div class="col-md-6">
					<p style='color: white' class="pull-right">
						&copy; 2014 <a href="http://www.fetb.it"
							title="Ferrara Tchoukball" rel="bookmark">Match Point</a>
						&middot; Designed by GEMMED
					</p>
				</div>
			</div>
			<!-- .row.widget-area -->
		</div>
		<!--.footer-widgets -->
	</div>


	<!-- Modale registrazione -->
	<div class="modal fade" id="myModal" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Nuovo utente</h4>
				</div>
				<div class="modal-body">

					<form method="POST"
						action="<c:url value="/gestione-giocatore?azione=crea"/>"
						class="form-horizontal" role="form">
						<div class="form-group">
							<label for="nome" class="col-sm-2 control-label">Nome</label>
							<div class="col-sm-10">
								<input id="nome" name="nome" type="text" class="form-control"
									placeholder="Nome">
							</div>
						</div>
						<div class="form-group">
							<label for="cognome" class="col-sm-2 control-label">Cognome</label>
							<div class="col-sm-10">
								<input id="cognome" name="cognome" type="text"
									class="form-control" placeholder="Cognome">
							</div>
						</div>
						<div class="form-group">
							<label for="cef-age" class="col-sm-2 control-label">Data
								nascita</label>
							<div class="col-sm-4">
								<input name="data_nascita" type="text" id="datepicker"
									class="form-control" placeholder="Data di nascita">
							</div>
						</div>
						<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input id="cef-salary" name="email" type="email"
									class="form-control" placeholder="Email">
							</div>
						</div>
						<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Telefono</label>
							<div class="col-sm-4">
								<input id="cef-salary" name="telefono" type="telefono"
									class="form-control" placeholder="Telefono">
							</div>
						</div>
						<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Indirizzo</label>
							<div class="col-sm-10">
								<input id="cef-salary" name="indirizzo" type="indirizzo"
									class="form-control" placeholder="Indirizzo">
							</div>
						</div>
						<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Citta</label>
							<div class="col-sm-10">
								<input id="cef-salary" name="citta" type="citta"
									class="form-control" placeholder="Citta">
							</div>
						</div>
						<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Provincia</label>
							<div class="col-sm-2">
								<input id="cef-salary" name="provincia" type="provincia"
									class="form-control" placeholder="Prov.">
							</div>
						</div>
						<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input id="cef-salary" name="password" type="password"
									class="form-control" placeholder="Password">
							</div>
						</div>
						<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Sesso</label>
							<div class="col-sm-10">
								<br> <input name="sex" type="radio" value="maschio" checked>Maschio<br>
								<input name="sex" type="radio" value="femmina">Femmina
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Registra</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modale login circolo -->
	<div class="modal fade" id="circolo" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Login Circolo</h4>
				</div>
				<div class="modal-body">
					<form role="form" method="post"
						action="<c:url value="/login?tipo=circolo"/>">
						<div class="form-group">
							<label for="InputEmail1">Email address</label> <input
								type="email" name="email" class="form-control" id="InputEmail1"
								placeholder="Enter email">
						</div>
						<div class="form-group">
							<label for="InputPassword1">Password</label> <input
								type="password" name="password" class="form-control"
								id="InputPassword1" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-default">Login</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modale login arbitro -->
	<div class="modal fade" id="arbitro" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Login Arbitro</h4>
				</div>
				<div class="modal-body">
					<form role="form" method="post"
						action="<c:url value="/login?tipo=arbitro"/>">
						<div class="form-group">
							<label for="InputEmail1">Email address</label> <input
								type="email" name="email" class="form-control" id="InputEmail1"
								placeholder="Enter email">
						</div>
						<div class="form-group">
							<label for="InputPassword1">Password</label> <input
								type="password" name="password" class="form-control"
								id="InputPassword1" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-default">Login</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modale login federazione -->
	<div class="modal fade" id="federazione" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Login Federazione</h4>
				</div>
				<div class="modal-body">
					<form role="form" method="post"
						action="<c:url value="/login?tipo=federazione"/>">
						<div class="form-group">
							<label for="InputEmail1">Email address</label> <input
								type="email" name="email" class="form-control" id="InputEmail1"
								placeholder="Enter email">
						</div>
						<div class="form-group">
							<label for="InputPassword1">Password</label> <input
								type="password" name="password" class="form-control"
								id="InputPassword1" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-default">Login</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	


</body>
</html>