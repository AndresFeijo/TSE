<html>
<head><title>Crear documento</title></head>
<body>
<h2>Agregar Documento</h2>
<form action="DocumentoServlet" method="post">
    Paciente: <input type="text" name="paciente"><br>
    Descripcion: <input type="text" name="descripcion"><br>
    Observaciones: <input type="text" name="observaciones"><br>
    <input type="submit" value="Agregar">
</form>

<a href="listar.jsp">Ver Listado</a>
</body>
</html>
