<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="/includes/header.jsp" %>
<div class="d-flex">
  <%@ include file="/includes/sidebar.jsp" %>

  <div class="container-fluid p-5">
    <div class="card shadow-lg border-0 rounded-3 p-5 text-center">
      <h1 class="fw-bold mb-4"> Bienvenido al Consultorio TSE</h1>
      <p class="lead text-muted mb-4">
        Desde aquí podés gestionar tus documentos clínicos y acceder a la información de tus pacientes.
      </p>
      <a href="ListarDocumentosServlet" class="btn btn-primary btn-lg px-4">
         Ver Listado de Documentos
      </a>
    </div>
  </div>
</div>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
