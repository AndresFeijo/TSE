<%@ page import="facultad.tse.practico.jpa.entities.*, java.util.List" %>
<%@ page import="javax.naming.InitialContext" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<%@ include file="/includes/header.jsp" %>
<%@ include file="crearDocumento.jsp" %>
<div class="d-flex">
  <%@ include file="/includes/sidebar.jsp" %>

  <div class="container-fluid p-4">
	<div class="d-flex align-items-center mb-4">
	  <h3 class="mb-0">Documentos Clínicos</h3>
	  <a href="#" class="btn btn-primary ms-3" data-bs-toggle="modal" data-bs-target="#crearModal">
	    Nuevo Documento
	  </a>
	</div>
    <!-- Campo de búsqueda por ID -->
		<div class="mb-3">
		  <form action="ListarDocumentosServlet" method="get" class="input-group">
		    <input type="text" name="id" pattern="\d+" title="Solo números enteros" id="buscarId" class="form-control" placeholder="Escribe un ID...">
		    <button type="submit" class="btn btn-primary">Buscar</button>
		  </form>
		</div>

    <%
      List<Documento> documentos = (List<Documento>) request.getAttribute("documentos");
      String res = (String) request.getAttribute("busqueda");
    if (documentos != null && res == null) {
    	
      if (!documentos.isEmpty()) {
      
    %>
        <table id="tablaDocumentos" class="table table-striped align-middle">
          <thead class="table-light">
            <tr>
              <th>ID</th>
              <th>Paciente</th>
              <th>Descripción</th>
              <th>Observaciones</th>
            </tr>
          </thead>
          <tbody>
          <%
            for (Documento doc : documentos) {
          %>
              <tr>
                <td><%= doc.getId() %></td>
                <td><%= doc.getPaciente() %></td>
                <td><%= doc.getDescripcion() %></td>
                <td><%= doc.getObservaciones() %></td>
              </tr>
          <%
            }
          %>
          </tbody>
        </table>
    <%}
    else {
        %>
            <div class="alert alert-info shadow-sm rounded-3">
              No hay documentos cargados aún.
            </div>
        <%
      }
    } else { 
      		if (!res.isEmpty() && res == "no") { %>
      		
      	        <div class="alert alert-info shadow-sm rounded-3">
                No se encontró ningún documento con ese id.
              	</div>
              	
      		<% }
      		
      }%>
    
  </div>
</div>

