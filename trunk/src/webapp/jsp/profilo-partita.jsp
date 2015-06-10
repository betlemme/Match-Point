<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Partita - presentazione</title>

<c:import url="/jsp/include/head.jsp" />


</head>

<body>
	<!-- NAVBAR
================================================== -->



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
				<a class="navbar-brand" href="<c:url value="/home" />">Match
					Point</a>
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
		</div>
		<!-- /.container-fluid -->
	</nav>

	<hr>

	<div class="jumbotron">

		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1>
						Torneo
						<c:out value="${torneo.nome}" />
						
						
					</h1>
					<h1>
						Partita
						<c:out value="${partita.numeroPartita}" />
					</h1>
				</div>

			</div>
		</div>
	</div>


	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Numero</th>
					<th>Data</th>
					<th>Ora</th>
					<th>Campo</th>
					<th>Sfidante A</th>
					<th>Sfidante B</th>
					<th>set 1</th>
					<th>set 2</th>
					<th>set 3</th>
					<!-- l'ho tolto... ma può servire per debug -->
					<!--<th>boolean</th>-->
					<th>Vincitore</th>
				</tr>
			</thead>

			<tbody>

				<tr>
					<form method="GET" action="<c:url value="/profiloPartita"/>">
						<input type="hidden" name="id" value="${torneo.id}"> <input
							type="hidden" name="numeroPartita"
							value="${partita.numeroPartita}">
						<td><button type="submit" class="btn btn-default">
								<c:out value="${partita.numeroPartita}" />
							</button></td>
					</form>

					<td><c:out value="${partita.data}" /></td>
					<td><c:out value="${partita.ora}" /></td>
					<td><c:out value="${partita.campo}" /></td>



					<c:choose>
						<c:when test="${partita.sfidanteA != null}">
							<td><c:out value="${partita.sfidanteA}" /></td>
						</c:when>

						<c:otherwise>
							<td>vincitore partita <c:out
									value="${(partita.numeroPartita*2)}" /></td>
						</c:otherwise>
					</c:choose>



					<c:choose>
						<c:when test="${partita.sfidanteB != null}">
							<td><c:out value="${partita.sfidanteB}" /></td>
						</c:when>

						<c:otherwise>
							<td>vincitore partita <c:out
									value="${(partita.numeroPartita*2+1)}" /></td>
						</c:otherwise>
					</c:choose>





					<td><c:out value="${partita.set1.getRisultato()}" /></td>
					<td><c:out value="${partita.set2.getRisultato()}" /></td>
					<td><c:out value="${partita.set3.getRisultato()}" /></td>
					<!--<td><c:out value="${partita.vittoriaSfidanteA}"/></td>-->



					<c:if test="${partita.set1.getRisultato() != null}">
						<td><c:out
								value="${partita.vittoriaSfidanteA ? partita.sfidanteA : partita.sfidanteB}" /></td>
					</c:if>

					<c:if test="${partita.set1.getRisultato() == null && partita.forfait}">
						<td><c:out value="${partita.vittoriaSfidanteA ? partita.sfidanteA : partita.sfidanteB}" /></td>
					</c:if>


				</tr>

			</tbody>

		</table>

		<hr>
		<c:if
			test="${partita.set1.getRisultato() == null && partita.sfidanteA != null && partita.sfidanteB != null && !partita.forfait }">
			<!-- Button trigger modal -->
			<button class="btn btn-primary btn-lg" data-toggle="modal"
				data-target="#myModal">Assegna vincitore</button>
				<hr>
		</c:if>
		
		<form method="GET" action="<c:url value="/profiloTorneoArbitro"/>">
						<input type="hidden" name="id" value="${torneo.id}">
						<button type="submit" class="btn btn-default">
								torna al torneo
						</button>
		</form>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">Esito</h4>
					</div>
					<div class="modal-body">

						<form method="GET" action="<c:url value="/updatePartitaSingola"/>"
							class="form-horizontal" role="form">

							<input type="hidden" name="id" value="${torneo.id}"> <input
								type="hidden" name="numeroPartita"
								value="${partita.numeroPartita}"> <input type="hidden"
								name="sfidanteA" value="${partita.sfidanteA}"> <input
								type="hidden" name="sfidanteB" value="${partita.sfidanteB}">


							<div class="form-group">
								<label for="nome" class="col-sm-2 control-label">Vincitore: </label>
								<div class="col-sm-10">
									<input type="radio" name="vincitore" value="A">
									<c:out value="${partita.sfidanteA}" />
									<br> <input type="radio" name="vincitore" value="B">
									<c:out value="${partita.sfidanteB}" />
								</div>
							</div>
							
							<div class="form-group">
								<label for="nome" class="col-sm-2 control-label"></label>
								<div class="col-sm-10">
									  <input type="checkbox" name="forfait" value="true">Vittoria per forfait<br>
								</div>
							</div>
							
							<div class="form-group">
								<label for="nome" class="col-sm-2 control-label"></label>
								<div class="col-sm-10">
									  <input type="checkbox" name="twoset" value="true">Vittoria in 2 set<br>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<select class="form-control" name="set1">
										<option value="6-0" selected="selected">6-0</option>
										<option value="6-1">6-1</option>
										<option value="6-2">6-2</option>
										<option value="6-3">6-3</option>
										<option value="6-4">6-4</option>
										<option value="7-5">7-5</option>
										<option value="7-6">7-6</option>
										<option value="0-6">0-6</option>
										<option value="1-6">1-6</option>
										<option value="2-6">2-6</option>
										<option value="3-6">3-6</option>
										<option value="4-6">4-6</option>
										<option value="5-7">5-7</option>
										<option value="6-7">6-7</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<select class="form-control" name="set2">
										<option value="6-0" selected="selected">6-0</option>
										<option value="6-1">6-1</option>
										<option value="6-2">6-2</option>
										<option value="6-3">6-3</option>
										<option value="6-4">6-4</option>
										<option value="7-5">7-5</option>
										<option value="7-6">7-6</option>
										<option value="0-6">0-6</option>
										<option value="1-6">1-6</option>
										<option value="2-6">2-6</option>
										<option value="3-6">3-6</option>
										<option value="4-6">4-6</option>
										<option value="5-7">5-7</option>
										<option value="6-7">6-7</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<select class="form-control" name="set3">
										<option value="6-0" selected="selected">6-0</option>
										<option value="6-1">6-1</option>
										<option value="6-2">6-2</option>
										<option value="6-3">6-3</option>
										<option value="6-4">6-4</option>
										<option value="7-5">7-5</option>
										<option value="7-6">7-6</option>
										<option value="0-6">0-6</option>
										<option value="1-6">1-6</option>
										<option value="2-6">2-6</option>
										<option value="3-6">3-6</option>
										<option value="4-6">4-6</option>
										<option value="5-7">5-7</option>
										<option value="6-7">6-7</option>
									</select>
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

		<!-- fino a qui -->


	</div>
	<!-- /container -->

	<hr>

	<footer>
		<p>&copy; Company 2014</p>
	</footer>
	</div>
	<!-- /container -->

</body>

</html>