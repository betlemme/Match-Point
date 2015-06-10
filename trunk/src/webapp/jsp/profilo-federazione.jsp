<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profilo federazione</title>
<c:import url="/jsp/include/head.jsp" />
</head>

<body>
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
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="container" style="padding-top: 60px">
		<div class="row">

			<!-- First Coloumn -->
			<div class="col-md-1"></div>
			<!-- Central Coloumn -->
			<div class="col-md-10">
				<!-- TITOLO -->
				<div class="well">
					<h1 class="text-center">Federazione Italiana Tennis</h1>
				</div>
				<!-- MENU' -->
				<div class="row">
					<ul class="nav nav-tabs" role="tablist">
						<li class="active"><a href="#Gen" data-toggle="tab"
							role="tab">Generale</a></li>
						<li><a href="#Tess" data-toggle="tab" role="tab">
								Gestione Tesserati</a></li>
						<li><a href="#NoTess" data-toggle="tab" role="tab">
								Gestione Scadenza Tesserati</a></li>
						<li><a href="#Circ" data-toggle="tab" role="tab"> Circoli</a>
						</li>
						<li><a href="#Torn" data-toggle="tab" role="tab"> Tornei</a>
						</li>
						<li><a href="#Arbi" data-toggle="tab" role="tab"> Arbitri</a>
						</li>
					</ul>
				</div>
				<!-- CONTAINER -->
				<div class="tab-content">
					<!-- GENERALE -->

					<c:if test='${search == null}'>
						<div class="tab-pane active" id="Gen">
					</c:if>
					<c:if test='${search != null}'>
						<div class="tab-pane" id="Gen">
					</c:if>
					<div class='row'>
						<div class='col-md-3'>
							<img src="img/iconPerson.png" width="100">
						</div>
						<div class='col-md-9'>
							<h2 align="center"
								style="background-color: #ffffff; width: 300px">
								<c:out value="${federazione.email}" />
							</h2>
							<br> <img src="img/glyphicons_044_keys.png"> 
							<a data-toggle="modal" data-target="#editProfilo" href="#">Cambia la tua password</a>
							
						</div>
					</div>

					<div class='row' style='padding-top: 50px;'>
						<div class='col-md-2'>
							<button class="btn btn-info" data-toggle="modal"
								data-target="#modalCircolo">Aggiungi circolo</button>
						</div>
						<div class='col-md-2'>
							<button class="btn btn-info" data-toggle="modal"
								data-target="#modalArbitro">Aggiungi arbitro</button>
						</div>
						<div class='col-md-2'>
							<button class="btn btn-info" data-toggle="modal"
								data-target="#modalFederazione">Aggiungi utente fed.</button>
						</div>
					</div>


				</div>

				<!-- TESSERATI -->
				<c:if test='${search == null}'>
					<div class="tab-pane" id="Tess">
				</c:if>
				<c:if test='${search != null}'>
					<div class="tab-pane active" id="Tess">
				</c:if>
				<br>
				<table>
					<tbody>
						<tr>
							<td style="vertical-align: center; background-color: #ffffff">
								<img src="img/find1.png" width="100">
							</td>
							<td style="vertical-align: center; background-color: #ffffff">
								<span><span style="font-size: x-large"><strong>&nbsp;
											Ricerca Tesserato</strong></span></span>
							</td>
						</tr>
					</tbody>
				</table>
				<br>
				<div>
					<form class="navbar-form" method="POST"
						action="<c:url value="/profiloFederazione?search_player=true"/>">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Search"
								size="40" name="value">
						</div>
						<div class="form-group">

							<div class="radio">
								<label> <input name="radio_choice" type="radio"
									value="nome" checked>Nome
								</label>
							</div>
							<div class="radio">
								<label> <input name="radio_choice" type="radio"
									value="cognome">Cognome
								</label>
							</div>
							<div class="radio">
								<label> <input name="radio_choice" type="radio"
									value="email">Email
								</label>
							</div>
							<div class="radio">
								<label> <input name="radio_choice" type="radio"
									value="tessera">N&deg; Tessera
								</label>
							</div>
						</div>
						<button type="submit" class="btn btn-success">Submit</button>
					</form>
					<br>

					<table class="table">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Indirizzo</th>
								<th>Telefono</th>
								<th>Email</th>
								<th>Tessera</th>
								<th>Classifica</th>
								<th>Circolo Associato</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="giocatore" items="${giocatori}">
								<tr>
									<td><c:out value="${giocatore.nome}" /></td>
									<td><c:out value="${giocatore.cognome}" /></td>
									<td><c:out value="${giocatore.indirizzo}" /></td>
									<td><c:out value="${giocatore.telefono}" /></td>
									<td><c:out value="${giocatore.email}" /></td>
									<td><c:if test="${empty giocatore.tessera}">
											<a class="btn btn-warning" data-toggle="modal" data-target="#tesseraGioc"
												onclick="setEmailValueGiocatore('<c:out value='${giocatore.email}'/>')">Assegna
												numero</a>
										</c:if> <c:if test="${!empty giocatore.tessera}">
											<c:out value="${giocatore.tessera}" />
											<a href data-toggle="modal" data-target="#tesseraGioc"
												onclick="setEmailValueGiocatore('<c:out value='${giocatore.email}'/>')">(Modifica)</a>
										</c:if></td>
									
									<td><c:if test="${empty giocatore.tessera}">
											Assegnare prima Tessera
										</c:if> <c:if test="${!empty giocatore.tessera}">
											<c:out value="${giocatore.classifica}" />
											<a href data-toggle="modal" data-target="#classificaGioc"
												onclick="setClassificaValueGiocatore('<c:out value='${giocatore.email}'/>')">(Modifica)</a>
										</c:if></td>
									<td><c:if test="${empty giocatore.tessera}">
											Assegnare prima Tessera
										</c:if> <c:if test="${!empty giocatore.tessera}">
											<c:out value="${giocatore.circolo}" />
											<a href data-toggle="modal" data-target="#circoloGioc"
												onclick="setCircoloValueGiocatore('<c:out value='${giocatore.email}'/>')">(Modifica)</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
			
			<!--NO TESSERATI -->
				<c:if test='${scadenza == null}'>
					<div class="tab-pane" id="NoTess">
				</c:if>
				<c:if test='${scadenza != null}'>
					<div class="tab-pane active" id="NoTess">
				</c:if>
				<br>
				<table>
					<tbody>
						<tr>
							<td style="vertical-align: center; background-color: #ffffff">
								<img src="img/find1.png" width="100">
							</td>
							<td style="vertical-align: center; background-color: #ffffff">
								<span><span style="font-size: x-large"><strong>&nbsp;
											Gestione Scadenza/No Tesserati</strong></span></span>
							</td>
						</tr>
					</tbody>
				</table>
				<br>
				<div>
					<br>
					<h1><img src="img/Icona_giocatore.png" width="5%"> Giocatori</h1>
					<table class="table">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Indirizzo</th>
								<th>Telefono</th>
								<th>Email</th>
								<th>Tessera</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="giocatore" items="${giocatoriScad}">
								<tr>
									<td><c:out value="${giocatore.nome}" /></td>
									<td><c:out value="${giocatore.cognome}" /></td>
									<td><c:out value="${giocatore.indirizzo}" /></td>
									<td><c:out value="${giocatore.telefono}" /></td>
									<td><c:out value="${giocatore.email}" /></td>
									<td><c:if test="${empty giocatore.tessera}">
											<a class="btn btn-warning" data-toggle="modal" data-target="#tesseraGioc"
												onclick="setEmailValueGiocatore('<c:out value='${giocatore.email}'/>')">Assegna
												numero</a>
										</c:if> <c:if test="${!empty giocatore.tessera}">
											<c:out value="${giocatore.tessera}" />
											<a href data-toggle="modal" data-target="#tesseraGioc"
												onclick="setEmailValueGiocatore('<c:out value='${giocatore.email}'/>')">(Modifica)</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<h1><img src="img/icona_arbitro.png" width="5%"> Arbitri</h1>
					<table class="table">
						<thead>
							<tr>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Indirizzo</th>
								<th>Telefono</th>
								<th>Email</th>
								<th>Tessera</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="arbitro" items="${arbitriScad}">
								<tr>
									<td><c:out value="${arbitro.nome}" /></td>
									<td><c:out value="${arbitro.cognome}" /></td>
									<td><c:out value="${arbitro.indirizzo}" /></td>
									<td><c:out value="${arbitro.telefono}" /></td>
									<td><c:out value="${arbitro.email}" /></td>
									<td><c:out value="${arbitro.tessera}" />
									<a href data-toggle="modal" data-target="#tesseraArb"
										onclick="setEmailValueArbitro('<c:out value='${arbitro.email}'/>')">(Modifica)</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					
				</div>
			</div>
			
			
			
			<!-- CIRCOLI -->
			<div class="tab-pane" id="Circ">
				<br>
				<table>
					<tbody>
						<tr>
							<td style="vertical-align: center; background-color: #ffffff">
								<img src="img/find1.png" width="100">
							</td>
							<td style="vertical-align: center; background-color: #ffffff">
								<span><span style="font-size: x-large"><strong>&nbsp;
											Circoli</strong></span></span>
							</td>
						</tr>
					</tbody>
				</table>
				<br>
				<table class="table">
					<thead>
						<tr>
							<th>Nome</th>
							<th>Telefono</th>
							<th>Indirizzo</th>
							<th>Città</th>
							<th>Provincia</th>
							<th>Email</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="circolo" items="${circoli}">
							<tr>
								<td><c:out value="${circolo.nome}" /></td>
								<td><c:out value="${circolo.telefono}" /></td>
								<td><c:out value="${circolo.indirizzo}" /></td>
								<td><c:out value="${circolo.citta}" /></td>
								<td><c:out value="${circolo.provincia}" /></td>
								<td><c:out value="${circolo.email}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>

			<!-- TORNEI -->
			<div class="tab-pane" id="Torn">
				<h1><img src="img/icon_256.png" width="8%"> Tornei che richiedono approvazione</h1>
				<h5>Assegnare un arbitro per approvare automaticamente.</h5>

				<table class="table">
					<thead>
						<tr>
							<th>Nome Torneo</th>
							<th>Circolo</th>
							<th>Iscrizione inizio</th>
							<th>Iscrizione fine</th>
							<th>Data inizio</th>
							<th>Data fine</th>
							<th>Categoria</th>
						    <th>Tipologia</th>
							<th>Arbito assegnato</th>
							<th>Approvazione</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="torneo" items="${tornei}">
							<tr>
								<td><c:out value="${torneo.nome}" /></td>
								<td><c:out value="${torneo.circolo}" /></td>
								<td><c:out value="${torneo.iscrizione_inizio}" /></td>
								<td><c:out value="${torneo.iscrizione_fine}" /></td>
								<td><c:out value="${torneo.data_inizio}" /></td>
								<td><c:out value="${torneo.data_fine}" /></td>
								<td><c:out value="${torneo.categoria}" /></td>
								<td><c:out value="${torneo.tipologia}" /></td>
								<!-- un po' di javascript.... -->
								<c:if test="${torneo.convalidato == 'approvare'}">
										<td><a class="btn btn-warning" data-toggle="modal"
											data-target="#myModal"
											onclick="setActionValue(<c:out value='${torneo.id}'/>)">Assegna
											Arbitro</a></td>
										<td><a class="btn btn-danger" data-toggle="modal"
											data-target="#nApprova"
											onclick="setDeleteValue(<c:out value='${torneo.id}'/>)">Non
											Approvare</a></td>
								</c:if> 
								<c:if test="${torneo.convalidato == 'si'}">
						           <td><c:out value="${torneo.arbitro}" /></td>
						           <td>Approvato</td>
								</c:if> 
								<c:if test="${torneo.convalidato == 'no'}">
						   			<td></td>
						   			<td>Non approvato</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- ARBITRI -->
			<div class="tab-pane" id="Arbi">
				<h1><img src="img/arbitro.png" width="8%"> Arbitri </h1>

				<table class="table">
					<thead>
						<tr>
							<th>Nome</th>
							<th>Cognome</th>
							<th>Telefono</th>
							<th>Città</th>
							<th>Provincia</th>
							<th>Email</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="arbitro" items="${arbitri}">
							<tr>
								<td><c:out value="${arbitro.nome}" /></td>
								<td><c:out value="${arbitro.cognome}" /></td>
							    <td><c:out value="${arbitro.telefono}" /></td>
								<td><c:out value="${arbitro.citta}" /></td>
								<td><c:out value="${arbitro.provincia}" /></td>
								<td><c:out value="${arbitro.email}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>
	<!-- Second Coloumn -->
	<div class="col-md-1"></div>
	</div>
	</div>


	<!-- Modale assegnazione arbitro -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Assegna arbitro</h4>
				</div>
				<div class="modal-body">

					<form method="POST"
						action="<c:url value="/gestione-torneo?azione=associa&federazione=${federazione.email}"/>"
						class="form-horizontal" role="form" id="action">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<select class="form-control" name="arbitro">
									<c:forEach var="arbitro" items="${arbitri}">
										<option><c:out value="${arbitro.email}" /></option>
									</c:forEach>
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

	<!-- Modale disapprovare torneo -->
	<div class="modal fade" id="nApprova" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Eliminazione Torneo</h4>
				</div>
				<div class="modal-body">

					<form method="POST"
						action="<c:url value="/gestione-torneo?azione=noapprova&federazione=${federazione.email}"/>"
						class="form-horizontal" role="form" id="deletetorneo">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								Sei Sicuro di non Voler Approvare il torneo?
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Ok</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>



	<!-- Modale assegnazione tessera al giocatore -->
	<div class="modal fade" id="tesseraGioc" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Assegna tessera</h4>
				</div>
				<div class="modal-body">

					<form method="POST"
						action="<c:url value="/gestione-giocatore?azione=tessera"/>"
						class="form-horizontal" role="form" id="tesseraFormG">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="form-group">
									<label for="tesseraText" class="col-sm-2 control-label">Tessera</label>
									<div class="col-sm-8">
										<input id="tesseraText" name="tessera" type="text"
											class="form-control" placeholder="Numero Tessera">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="cef-age" class="col-sm-2 control-label">Data scadenza </label>
								<div class="col-sm-4">
									<input id="cef-age" name="data_scadenza" type="date" class="form-control" placeholder="Data scadenza">
								</div>
								<div id="calendar"></div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Cambia</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modale assegnazione classifica al giocatore -->
	<div class="modal fade" id="classificaGioc" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Assegna classifica</h4>
				</div>
				<div class="modal-body">

					<form method="POST"
						action="<c:url value="/gestione-giocatore?azione=classifica"/>"
						class="form-horizontal" role="form" id="classificaFormG">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="form-group">
									<label for="classificaText" class="col-sm-2 control-label">Classifica</label>
									<div class="col-sm-3">
										<input id="classificaText" name="classifica" type="text"
											class="form-control" placeholder="Class.">
									</div>
								</div>
							</div>
							
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Cambia</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	<!-- Modale assegnazione circolo al giocatore -->
	<div class="modal fade" id="circoloGioc" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Assegna circolo</h4>
				</div>
				<div class="modal-body">

					<form method="POST"
						action="<c:url value="/gestione-giocatore?azione=circolo"/>"
						class="form-horizontal" role="form" id="circoloFormG">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="form-group">
									<label for="circoloText" class="col-sm-2 control-label">Circolo</label>
									<div class="col-sm-offset-2 col-sm-10">
										<select class="form-control" name="circolo">
											<c:forEach var="cir" items="${circoli}">
												<option value="${cir.email}"><c:out value="${cir.email}" /></option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Cambia</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<!-- Modale assegnazione tessera al arbitro -->
	<div class="modal fade" id="tesseraArb" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Assegna tessera</h4>
				</div>
				<div class="modal-body">

					<form method="POST"
						action="<c:url value="/gestioneArbitro?azione=tessera"/>"
						class="form-horizontal" role="form" id="tesseraFormA">
						<div class="form-group">
							<div class="form-group">
								<label for="cef-age" class="col-sm-2 control-label">Data scadenza </label>
								<div class="col-sm-4">
									<input id="cef-age" name="data_scadenza" type="date" class="form-control" placeholder="Data scadenza">
								</div>
								<div id="calendar"></div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Cambia</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	

	<script>
	    function setActionValue(id) {
	    	document.getElementById('action').action = "<c:url value='/gestione-torneo?azione=associa&federazione=${federazione.email}'/>"+"&id="+id;
	    };
	    
	    function setDeleteValue(id) {
	    	document.getElementById('deletetorneo').action = "<c:url value='/gestione-torneo?azione=noapprova&federazione=${federazione.email}'/>"+"&id="+id;
	    };
	    
	    function setEmailValueGiocatore(email) {
	    	document.getElementById('tesseraFormG').action = "<c:url value='/gestione-giocatore?azione=tessera'/>"+"&email="+email;
	    };
	    
	    function setClassificaValueGiocatore(email) {
	    	document.getElementById('classificaFormG').action = "<c:url value='/gestione-giocatore?azione=classifica'/>"+"&email="+email;
	    };
    
	    function setCircoloValueGiocatore(email) {
	    	document.getElementById('circoloFormG').action = "<c:url value='/gestione-giocatore?azione=circolo'/>"+"&email="+email;
	    };
	    
	    function setEmailValueArbitro(email) {
	    	document.getElementById('tesseraFormA').action = "<c:url value='/gestioneArbitro?azione=tessera'/>"+"&email="+email;
	    };
	    
    </script>

	<!-- Modale creazione nuovo circolo -->
	<div class="modal fade" id="modalCircolo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Nuovo circolo</h4>
				</div>
				<div class="modal-body">

					<form method="POST" action="<c:url value="/create-circolo"/>"
						class="form-horizontal" role="form">
						<div class="form-group">
							<label for="nome" class="col-sm-2 control-label">Nome</label>
							<div class="col-sm-10">
								<input id="nome" name="nome" type="text" class="form-control"
									placeholder="Nome">
							</div>
						</div>

						<div class="form-group">
							<label for="telefono" class="col-sm-2 control-label">Telefono</label>
							<div class="col-sm-4">
								<input id="telefono" name="telefono" type="text"
									class="form-control" placeholder="Telefono">
							</div>
						</div>
						<div class="form-group">
							<label for="cognome" class="col-sm-2 control-label">Indirizzo</label>
							<div class="col-sm-10">
								<input id="indirizzo" name="indirizzo" type="text"
									class="form-control" placeholder="Indirizzo">
							</div>
						</div>
						<div class="form-group">
							<label for="citta" class="col-sm-2 control-label">Citta</label>
							<div class="col-sm-10">
								<input id="citta" name="citta" type="text"
									class="form-control" placeholder="Citta">
							</div>
						</div>
						<div class="form-group">
							<label for="provincia" class="col-sm-2 control-label">Provincia</label>
							<div class="col-sm-2">
								<input id="provincia" name="provincia" type="text"
									class="form-control" placeholder="Prov.">
							</div>
						</div>
						<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input id="email" name="email" type="email" class="form-control"
									placeholder="Email">
							</div>
						</div>
						<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input id="password" name="password" type="password"
									class="form-control" placeholder="Password">
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

	<!-- Modal  per un nuovo utente della federazione-->
	<div class="modal fade" id="modalFederazione" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Nuovo utente federale</h4>
				</div>
				<div class="modal-body">

					<form method="POST"
						action="<c:url value="/create-utente-federale"/>"
						class="form-horizontal" role="form">
						<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input id="cef-salary" name="email" type="email"
									class="form-control" placeholder="Email">
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
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-default">Registra</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modale creazione Arbitro -->
	<div class="modal fade" id="modalArbitro" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Nuovo arbitro</h4>
				</div>
				<div class="modal-body">

					<form method="POST" action="<c:url value="/create-arbitro"/>"
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
							<label for="cef-age" class="col-sm-2 control-label">Data nascita</label>
							<div class="col-sm-4">
								<input id="cef-age" name="data_nascita" type="date"
									class="form-control" placeholder="Data di nascita">
							</div>
							<div id="calendar"></div>
						</div>
						<div class="form-group">
							<label for="indirizzo" class="col-sm-2 control-label">Indirizzo</label>
							<div class="col-sm-10">
								<input id="indirizzo" name="indirizzo" type="text"
									class="form-control" placeholder="Indirizzo">
							</div>
						</div>
						<div class="form-group">
							<label for="citta" class="col-sm-2 control-label">Citta</label>
							<div class="col-sm-10">
								<input id="citta" name="citta" type="text"
									class="form-control" placeholder="Citta">
							</div>
						</div>
						<div class="form-group">
							<label for="provincia" class="col-sm-2 control-label">Provincia</label>
							<div class="col-sm-2">
								<input id="provincia" name="provincia" type="text"
									class="form-control" placeholder="Prov.">
							</div>
						</div>
						<div class="form-group">
							<label for="telefono" class="col-sm-2 control-label">Telefono</label>
							<div class="col-sm-10">
								<input id="telefono" name="telefono" type="text"
									class="form-control" placeholder="Telefono">
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
							<label for="cef-salary" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input id="cef-salary" name="password" type="password"
									class="form-control" placeholder="Password">
							</div>
						</div>
						<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Tessera</label>
							<div class="col-sm-8">
								<input id="cef-salary" name="tessera" type="text"
									class="form-control" placeholder="Numero Tessera">
							</div>
						</div>
							<div class="form-group">
							<label for="cef-salary" class="col-sm-2 control-label">Scadenza Tessera</label>
							<div class="col-sm-4">
								<input id="cef-salary" name="scadenza_tessere" type="date"
									class="form-control" placeholder="Scadenza Tessera">
									<div id="calendar"></div>
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
	
	<!-- Modale modifica password -->
	<div class="modal fade" id="editProfilo">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Modifica prassword</h4>
				</div>
				
				<form method="POST"
						action="<c:url value="/gestioneFederazione"/>"
						class="form-horizontal" role="form">
						
						<input type="hidden" name="email" class="form-control" id="inputName" value="${federazione.email}">
															
						<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">Password</label>
							<div class="col-md-6">
								<input type="text" name="password" class="form-control" id="inputPassword"
									placeholder="Password">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Save changes</button>
						</div>
					</form>
				</div>
				
			</div>
			<!-- /.modal-content -->
		</div>
		
		<script>
		$(function() { 
			  //for bootstrap 3 use 'shown.bs.tab' instead of 'shown' in the next line
			  $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
			    //save the latest tab; use cookies if you like 'em better:
			    localStorage.setItem('lastTab', $(e.target).attr('id'));
			    console.log(localStorage.getItem('lastTab')+"dasf");
			  });

			  //go to the latest tab, if it exists:
			  var lastTab = localStorage.getItem('lastTab');
			  if (lastTab) {
			      $('#'+lastTab).tab('show');
			  }
			});
		</script>
</body>
</html>