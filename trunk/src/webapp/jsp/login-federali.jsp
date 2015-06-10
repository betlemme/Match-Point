<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>    
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

    <script src="http://yui.yahooapis.com/3.15.0/build/yui/yui-min.js"></script>
    
</head>
<body>

<div class="container">
	<div class="row">
	
	<div class="col-md-4">
			<div class="card">
               <h3 class="card-heading simple">Login Circolo</h3>
			   <div class="card-body">
					<form role="form" method="post" action="<c:url value="/login?tipo=circolo"/>">
						<div class="form-group">
							<label for="InputEmail1">Email address</label>
							<input type="email" name="email" class="form-control" id="InputEmail1" placeholder="Enter email">
						</div>
						<div class="form-group">
							<label for="InputPassword1">Password</label>
							<input type="password" name="password" class="form-control" id="InputPassword1" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>	
				</div>
				<button class="btn btn-block" data-toggle="modal" data-target="#modalCircolo">Registrazione</button>
            </div>
	</div>
	
	<div class="col-md-4">
				<div class="card">
               <h3 class="card-heading simple">Login Arbitro</h3>
			   <div class="card-body">
					<form role="form" method="post" action="<c:url value="/login?tipo=arbitro"/>">
						<div class="form-group">
							<label for="InputEmail1">Email address</label>
							<input type="email" name="email" class="form-control" id="InputEmail1" placeholder="Enter email">
						</div>
						<div class="form-group">
							<label for="InputPassword1">Password</label>
							<input type="password" name="password" class="form-control" id="InputPassword1" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>	
				</div>
				<button class="btn btn-block" data-toggle="modal" data-target="#modalArbitro">Registrazione</button>
            </div>
	</div>
	
	<div class="col-md-4">
				<div class="card">
               <h3 class="card-heading simple">Login Federazione</h3>
			   <div class="card-body">
					<form role="form" method="post" action="<c:url value="/login?tipo=federazione"/>">
						<div class="form-group">
							<label for="InputEmail1">Email address</label>
							<input type="email" name="email" class="form-control" id="InputEmail1" placeholder="Enter email">
						</div>
						<div class="form-group">
							<label for="InputPassword1">Password</label>
							<input type="password" name="password" class="form-control" id="InputPassword1" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-default">Submit</button>
					</form>	
				</div>
				<button class="btn btn-block" data-toggle="modal" data-target="#modalFederazione">Registrazione</button>
            </div>
	
	</div>
	
	</div>
</div>

	<!-- Modal -->
	<div class="modal fade" id="modalCircolo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">Nuovo circolo</h4>
	      </div>
	      <div class="modal-body">
	
			<form method="POST" action="<c:url value="/create-circolo"/>" class="form-horizontal" role="form">
				    	<div class="form-group">
				    		<label for="nome" class="col-sm-2 control-label">Nome</label>
			    			<div class="col-sm-10">
			                	<input id="nome" name="nome" type="text" class="form-control" placeholder="Nome">
			              	</div>
			            </div>
				    	
				    	<div class="form-group">
				    		<label for="telefono" class="col-sm-2 control-label">Telefono</label>
			    			<div class="col-sm-10">
			                	<input id="telefono" name="telefono" type="text" class="form-control" placeholder="Telefono">
			              	</div>
			            </div>
				    	
				    	
				    	
				    	<div class="form-group">
				    		<label for="cognome" class="col-sm-2 control-label">Indirizzo</label>
			    			<div class="col-sm-10">
			                	<input id="indirizzo" name="indirizzo" type="text" class="form-control" placeholder="Cognome">
			              	</div>
			            </div>
			            <div class="form-group">
				    		<label for="cef-salary" class="col-sm-2 control-label">Email</label>
			    			<div class="col-sm-10">
			                	<input id="email" name="email" type="email" class="form-control" placeholder="Email">
			              	</div>
			            </div>
			            <div class="form-group">
				    		<label for="cef-salary" class="col-sm-2 control-label">Password</label>
			    			<div class="col-sm-10">
			                	<input id="password" name="password" type="password" class="form-control" placeholder="Password">
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
	
	<!-- Modal -->
	<div class="modal fade" id="modalFederazione" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">Nuovo utente federale</h4>
	      </div>
	      <div class="modal-body">
	
			<form method="POST" action="<c:url value="/create-utente-federale"/>" class="form-horizontal" role="form">
			            <div class="form-group">
				    		<label for="cef-salary" class="col-sm-2 control-label">Email</label>
			    			<div class="col-sm-10">
			                	<input id="cef-salary" name="email" type="email" class="form-control" placeholder="Email">
			              	</div>
			            </div>
			            <div class="form-group">
				    		<label for="cef-salary" class="col-sm-2 control-label">Password</label>
			    			<div class="col-sm-10">
			                	<input id="cef-salary" name="password" type="password" class="form-control" placeholder="Password">
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
	
	<!-- Modal -->
	<div class="modal fade" id="modalArbitro" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="myModalLabel">Nuovo arbitro</h4>
	      </div>
	      <div class="modal-body">
	
			<form method="POST" action="<c:url value="/create-arbitro"/>" class="form-horizontal" role="form">
				    	<div class="form-group">
				    		<label for="nome" class="col-sm-2 control-label">Nome</label>
			    			<div class="col-sm-10">
			                	<input id="nome" name="nome" type="text" class="form-control" placeholder="Nome">
			              	</div>
			            </div>
				    	<div class="form-group">
				    		<label for="cognome" class="col-sm-2 control-label">Cognome</label>
			    			<div class="col-sm-10">
			                	<input id="cognome" name="cognome" type="text" class="form-control" placeholder="Cognome">
			              	</div>
			            </div>
			            <div class="form-group">
				    		<label for="cef-age" class="col-sm-2 control-label">Data nascita (eg 2013-12-23)</label>
			    			<div class="col-sm-10">
			                	<input id="cef-age" name="data_nascita" type="date" class="form-control" placeholder="Data di nascita">
			              	</div>
			              	<div id="calendar"></div>
			            </div>
			            <div class="form-group">
				    		<label for="cef-salary" class="col-sm-2 control-label">Email</label>
			    			<div class="col-sm-10">
			                	<input id="cef-salary" name="email" type="email" class="form-control" placeholder="Email">
			              	</div>
			            </div>
			            <div class="form-group">
				    		<label for="cef-salary" class="col-sm-2 control-label">Password</label>
			    			<div class="col-sm-10">
			                	<input id="cef-salary" name="password" type="password" class="form-control" placeholder="Password">
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

</body>
</html>