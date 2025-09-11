<div class="modal fade" id="crearModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content rounded-3 shadow-lg">
      <div class="modal-header">
        <h5 class="modal-title">Nuevo Documento Clínico</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <form action="DocumentoServlet" method="post">
        <div class="modal-body row g-3">
          <div class="col-md-6">
            <label class="form-label">Paciente</label>
            <input type="text" class="form-control" name="paciente" required>
          </div>
          <div class="col-md-6">
            <label class="form-label">Descripción</label>
            <input type="text" class="form-control" name="descripcion" required>
          </div>
          <div class="col-12">
            <label class="form-label">Observaciones</label>
            <textarea class="form-control" name="observaciones" rows="3"></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-success">Guardar</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        </div>
      </form>
    </div>
  </div>
</div>
