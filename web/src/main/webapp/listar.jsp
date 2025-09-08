<%@ page import="facultad.tse.practico.model.Documento, java.util.List" %>
<%@ page import="javax.naming.InitialContext" %>

<html>
<body>
<h2>Listado de Documentos</h2>
<ul>
<% 
List<Documento> documentos = (List<Documento>) request.getAttribute("documentos");
for(Documento doc : documentos) { 
	%>
   <li><%= doc.getId() %> - <%= doc.getFecha() %> - <%= doc.getDescripcion() %> - <%= doc.getPaciente() %> - <%= doc.getObservaciones() %></li>
<% } %>
</ul>
</body>
</html>
