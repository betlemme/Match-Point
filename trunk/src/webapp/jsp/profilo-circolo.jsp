<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">

<title>Circolo - presentazione</title>

<c:import url="/jsp/include/head.jsp" />



</head>

<body>
	<!-- Navbar profilo circolo -->
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
				<a class="navbar-brand" href="<c:url value="/home" />">Match Point</a>
			</div>

				<c:choose>
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
					</c:otherwise>
				</c:choose>
		</div>
		<!-- /.navbar-collapse -->
	</nav>

	<hr>

	<div class="jumbotron">

		<div class="container">
			<div class="row">
				<div class="col-md-5">
					<h1>
						Circolo Tennis
						<c:out value="${circolo.nome}" />
					</h1>
				</div>

				<div class="col-md-4">
					<h2>Posta un avviso su Tumblr</h2>
					<form action="<c:url value="/gestione-torneo?azione=posta"/>"
						method="POST">
						<textarea class="form-control" rows="3" name="post"></textarea>
						</br>
						<button type="submit" class="btn btn-info">Posta</button>
					</form>
				</div>

				<div class="col-md-3"></div>

			</div>
		</div>
	</div>


	<div class="container">

		<!-- display the list of found tornei, if any -->
		<c:if test='${not empty tornei}'>
			<div class="jumbotron">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Nome</th>
							<th>Data inizio</th>
							<th>Data fine</th>
							<th>Categoria</th>
							<th>Circolo</th>
							<th>Tipologia</th>
							<th>Arbitro</th>
							<th>Fed</th>
							<th>Convalidato</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="torneo" items="${tornei}">
							<form method="GET" action="<c:url value="/profiloTorneo"/>">
								<input type="hidden" name="id" value="${torneo.id}">
								<tr>
									<td><button type="submit" class="btn btn-default">
											<c:out value="${torneo.nome}" />
										</button></td>
									<td><c:out value="${torneo.data_inizio}" /></td>
									<td><c:out value="${torneo.data_fine}" /></td>
									<td><c:out value="${torneo.categoria}" /></td>
									<td><c:out value="${torneo.circolo}" /></td>
									<td><c:out value="${torneo.tipologia}" /></td>
									<td><c:out value="${torneo.arbitro}" /></td>
									<td><c:out value="${torneo.federazione}" /></td>
									<td><c:out value="${torneo.convalidato}" /></td>
								</tr>
							</form>
						</c:forEach>
					</tbody>

				</table>

			</div>
		</c:if>


		<!-- Button trigger modal -->
		<button class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal">Proponi nuovo torneo</button>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Nuovo torneo</h4>
					</div>
					<div class="modal-body">

						<form method="POST"
							action="<c:url value="/gestione-torneo?azione=crea"/>"
							class="form-horizontal" role="form">
							<div class="form-group">
								<label for="nome" class="col-sm-2 control-label">Nome</label>
								<div class="col-sm-10">
									<input id="nome" name="nome" type="text" class="form-control"
										placeholder="Nome">
								</div>
							</div>

							<div class="form-group">
								<label for="cef-age" class="col-sm-2 control-label">Data
									inizio</label>
								<div class="col-sm-10">
									<input id="cef-age" name="data_inizio" type="date"
										class="form-control" placeholder="Data inizio">
								</div>
								<div id="calendar"></div>
							</div>

							<div class="form-group">
								<label for="cef-age" class="col-sm-2 control-label">Data
									fine</label>
								<div class="col-sm-10">
									<input id="cef-age" name="data_fine" type="date"
										class="form-control" placeholder="Data fine">
								</div>
								<div id="calendar"></div>
							</div>

							<div class="form-group">
								<label for="cef-age" class="col-sm-2 control-label">Iscriz.
									Inizio</label>
								<div class="col-sm-10">
									<input id="cef-age" name="iscrizione_inizio" type="date"
										class="form-control" placeholder="iscriz. inizio">
								</div>
								<div id="calendar"></div>
							</div>

							<div class="form-group">
								<label for="cef-age" class="col-sm-2 control-label">Iscriz.
									Fine</label>
								<div class="col-sm-10">
									<input id="cef-age" name="iscrizione_fine" type="date"
										class="form-control" placeholder="iscriz. inizio">
								</div>
								<div id="calendar"></div>
							</div>

							<div class="form-group">
								<label for="categoria" class="col-sm-2 control-label">Categoria</label>
								<div class="col-sm-10">
									<input id="categoria" name="categoria" type="text" class="form-control"
										placeholder="Categoria">
								</div>
							</div>
							
							<input type="hidden" name="circolo" value="${circolo.email}">
							
							<div class="form-group">
								<label for="cef-salary" class="col-sm-2 control-label">Tipologia</label>
								<div class="col-sm-10">
						        	<br>
									<input name="tipologia" type="radio" value="singolo" checked>Singolo<br>
									<input  name="tipologia" type="radio" value="doppio" disabled >Doppio(In Progress)
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

		<hr>





		<!-- display the list of found tornei, if any -->
		<c:if test='${not empty campi}'>
			<div class="jumbotron">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Numero</th>
							<th>Superficie</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="campo" items="${campi}">

							<tr>
								<td><c:out value="${campo.numero}" /></td>
								<td><c:out value="${campo.superficie}" /></td>
							</tr>

						</c:forEach>
					</tbody>

				</table>

			</div>
		</c:if>


		<!-- Button trigger modal -->
		<button class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal2">Aggiungi Campo da gioco</button>

		<!-- Modal -->
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Nuovo Campo</h4>
					</div>

					<div class="modal-body">

						<form method="POST" action="<c:url value="/create-campo"/>"
							class="form-horizontal" role="form">

							<input type="hidden" name="circolo" value="${circolo.email}">
							<div class="form-group">
								<label for="cef-age" class="col-sm-2 control-label">Numero
									campo</label>
								<div class="col-sm-10">
									<input id="cef-age" name="numero" type="number"
										class="form-control" placeholder="numero">
								</div>
							</div>

							<div class="form-group">
								<label for="cef-age" class="col-sm-2 control-label">Superficie</label>
								<div class="col-sm-10">
									<select class="form-control" name="superficie">
										<option value="terra_battuta" selected="selected">Terra
											rossa</option>
										<option value="sintetico">Sintetico</option>
										<option value="erba">Erba</option>
										<option value="cemento">Cemento</option>
									</select>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>



		<hr>

		<footer>
			<p>&copy; GEMMED 2014</p>
		</footer>
	</div>
	<!-- /container -->


</body>

</html>