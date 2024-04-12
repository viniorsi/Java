<%@page import="br.fiap.classedao.LicencaVooDAO"%>
<%@page import="br.fiap.modelos.LicencaVooModel"%>
<%@page import="br.fiap.classedao.DroneDAO"%>
<%@page import="br.fiap.modelos.DroneModelo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="licencaDrone.css">
</head>
<body>
<%Integer droneID = Integer.parseInt(request.getParameter("drone_id"));
			DroneModelo drone = new DroneDAO().pesquisar(droneID);
			LicencaVooModel licenca = new LicencaVooDAO().pesquisar(droneID);
			%>
			<div class="main">
				<div class="infos">
						<h1>Licença do drone</h1>
					<div class="quadrado">
			
				
						<p>
							<label for="idDrone">ID do drone:&ensp;</label>
							<span><%=drone.getId()%></span>
							<input name="idDrone" id="idDrone" type="hidden" value="<%=drone.getId()%>" value="readonly"/>
						</p>
						
						<p>
							<label for="nSerie">Número de série:&ensp;</label>
							<span><%=drone.getNumeroSerie()%></span>
							<input name="nSerie" type="hidden" value="<%=drone.getNumeroSerie()%>" value="readonly"/>
						</p>
						
						<p>
							<label for="licenca">Licença de voo:&ensp;</label>
							<span><%=licenca.getLicencaVoo()%></span>
							<input name="licenca" type="hidden" value="<%=licenca.getLicencaVoo()%>" value="readonly"/>
						</p>
						
						<p>
							<label for="emissao">Data de Emissão:&ensp;</label>
							<span><%=licenca.getDataEmissao()%></span>
							<input name="emissao" type="hidden" value="<%=licenca.getDataEmissao()%>" value="readonly"/>
						</p>
						
						<p>
							<label for="validade">Data de validade:&ensp;</label>
							<span><%=licenca.getDataValidade()%></span>
							<input name="validade" type="hidden" value="<%=licenca.getDataValidade()%>" value="readonly"/>
						</p>
						
						
						</div>
						 <a href="listagemDrones.jsp" ><button class="button back" type="button">Voltar</button></a>
				</div>
			</div>
			




</body>
</html>