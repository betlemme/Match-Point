\<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>Match Point</title>

	<c:import url="/jsp/include/head.jsp"/>

</head>
<body>
<!-- Navbar per la pagina dell'arbitro -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
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
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<!-- Titolo Profilo arbitro -->
<div class="container" style="padding-top:60px">

<div class="row">
    <div class="well">
        <center>
            <h1>Gestione profilo Arbitro</h1>
        </center>
    </div>

	<div class="col-md-3">
		<div class="panel panel-default">
		  <div class="panel-heading">
		    <h3 class="panel-title"><c:out value="${arbitro.email}" /></h3>
		  </div>
		  <div class="panel-body">
            <h3><img src="iconPerson.png" width="30" style="vertical-align:text-bottom;">  I tuoi dati</h3>
		    <ul>
		    	<li><c:out value="${arbitro.nome}" /> <c:out value="${arbitro.cognome}" /></li>
		    	<li>ID federazione: <c:out value="${arbitro.tessera}" /></li>
		    	<li>Scadenza tessera: <c:out value="${arbitro.scadenza_tessere}"/></li>
		    	<li>Data di nascita: <c:out value="${arbitro.data_nascita}"/></li>
		    	<li>Indirizzo: <c:out value="${arbitro.indirizzo} "/> </li>
		    	<li>Città: <c:out value="${arbitro.citta} "/> </li>
		    	<li>Provincia: <c:out value="${arbitro.provincia} "/> </li>
		    	<li>Telefono: <c:out value="${arbitro.telefono}"/></li>

		   </ul>
          <a data-toggle="modal" data-target="#editProfilo" href="#">Modifica dati</a>
		  </div>
		</div>

	</div>

	<div class="col-md-9">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs">
            <li class="active"><a href="#nextgames" data-toggle="tab">Tornei</a></li>
        </ul>

        <!-- Tab panes  per vedere i tornei-->
        <div class="tab-content">
            <div class="tab-pane fade in active" id="tornei">
	
			<table class="table">
                <thead>
                  <tr>
                    <th>Nome Torneo</th>
                    <th>Circolo</th>
                    <th>Data inizio</th>
                    <th>Data fine</th>
					<th>Gestione</th>
					<th>Convalidato</th>
                  </tr>
                </thead>
                <tbody>
                   	<c:forEach var="torneo" items="${tornei}">
   						<tr>
   							<td><c:out value="${torneo.nome}"/></td>
   							<td><c:out value="${torneo.circolo}"/></td>
   							<td><c:out value="${torneo.data_inizio}"/></td>
   							<td><c:out value="${torneo.data_fine}"/></td>
							<td>
		                      	<form method="GET" action="<c:url value="/profiloTorneoArbitro"/>">
		    						<input type="hidden" name="id" value="${torneo.id}">
		    						<button type="submit" class="btn btn-default"><c:out value="Gestisci"/>
		    						</button>
		    					</form>
                   			</td>
   							<td><c:out value="${torneo.convalidato}"/></td>
   						</tr>
   					</c:forEach>
                </tbody>
              </table>
            </div>
        </div>

	</div>
</div>
</div> <!-- container -->

<!-- Modale modifica profilo -->
	<div class="modal fade" id="editProfilo">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title">Modifica profilo</h4>
				</div>
				
				<form method="POST"
						action="<c:url value="/gestioneArbitro?azione=modifica"/>"
						class="form-horizontal" role="form">
						
						<input type="hidden" name="email" class="form-control" id="inputName" value="${arbitro.email}">
						
					    <div class="form-group">
							<label for="inputtelefono" class="col-sm-2 control-label">Telefono</label>
							<div class="col-md-6">
								<input type="text" name="telefono" class="form-control" id="inputtelefono"
									placeholder="Telefono">
							</div>
						</div>
						<div class="form-group">
							<label for="inputIndirizzo" class="col-sm-2 control-label">Indirizzo</label>
							<div class="col-md-6">
								<input type="text" name="indirizzo" class="form-control" id="inputIndirizzo"
									placeholder="Indirizzo">
							</div>
						</div>
						<div class="form-group">
							<label for="inputCitta" class="col-sm-2 control-label">Citta</label>
							<div class="col-md-6">
								<input type="text" name="citta" class="form-control" id="inputCitta"
									placeholder="Citta">
							</div>
						</div>
						<div class="form-group">
							<label for="inputProvincia" class="col-sm-2 control-label">Provincia</label>
							<div class="col-md-6">
								<input type="text" name="provincia" class="form-control" id="inputProvincia"
									placeholder="provincia">
							</div>
						</div>
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
		<!-- /.modal-dialog -->
	<!-- /.modal -->

</body>
</html>