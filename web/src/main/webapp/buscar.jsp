<%@ page import="facultad.tse.practico.model.Documento" %>
<%@ page import="javax.naming.InitialContext" %>

<%
    String idParam = request.getParameter("id");
	Documento resultado = null;

    if (idParam != null && !idParam.isEmpty()) {
        try {
            int id = Integer.parseInt(idParam);
            InitialContext ctx = new InitialContext();
            facultad.tse.practico.service.DocumentoEJBLocal service =
            (facultad.tse.practico.service.DocumentoEJBLocal) ctx.lookup("java:module/DocumentoEJB");
            resultado = service.buscarPorId(id);
        } catch (Exception e) {
            out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
%>

<html>
<head><title>Buscar Documento</title></head>
<body>
<h2>Buscar Entidad por ID</h2>
<form method="get" action="buscar.jsp">
    ID: <input type="number" name="id">
    <input type="submit" value="Buscar">
</form>

<% if (resultado != null) { %>
    <h3>Resultado:</h3>
    <p>ID: <%= resultado.getId() %></p>
    <p>Descripcion: <%= resultado.getDescripcion() %></p>
    <p>Paciente: <%= resultado.getPaciente() %></p>
    <p>Fecha: <%= resultado.getFecha() %></p>
    <p>Observaciones: <%= resultado.getObservaciones() %></p>
<% } else if (idParam != null) { %>
    <p>No se encontró ninguna entidad con ese ID.</p>
<% } %>

<br><a href="index.jsp">Volver</a>
</body>
</html>
