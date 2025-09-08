<%@ page import="facultad.tse.practico.model.Documento, java.util.List" %>
<%@ page import="javax.naming.InitialContext" %>

<%@ include file="/includes/header.jsp" %>
<div class="d-flex">
  <%@ include file="/includes/sidebar.jsp" %>

  <div class="container-fluid p-4">
    <h3 class="mb-4">Documentos Clínicos</h3>

    <%
      List<Documento> documentos = (List<Documento>) request.getAttribute("documentos");
      if (documentos != null && !documentos.isEmpty()) {
    %>
        <table class="table table-striped align-middle">
          <thead class="table-light">
            <tr>
              <th>ID</th>
              <th>Paciente</th>
              <th>Descripción</th>
              <th>Observaciones</th>
              <th>Acciones</th>
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
                <td>
                  <button class="btn btn-sm btn-outline-info">Desinstalar minecraft</button>
                </td>
              </tr>
          <%
            }
          %>
          </tbody>
        </table>
    <%
      } else {
    %>
        <div class="alert alert-info shadow-sm rounded-3">
          No hay documentos cargados aún.
          <button class="btn btn-sm btn-primary ms-3" data-bs-toggle="modal" data-bs-target="#crearModal">
             Crear Documento
          </button>
        </div>
    <%
      }
    %>
  </div>
</div>

<!-- Modal para crear documento -->
<%@ include file="crearDocumento.jsp" %>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
