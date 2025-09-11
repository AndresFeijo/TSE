package facultad.tse.practico.cliente;

import facultad.tse.practico.clases.Documento;
import facultad.tse.practico.service.DocumentoEJBRemoto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.beans.Beans;

public class DocumentoPanel extends JPanel {
    private DocumentoEJBRemoto service;
    private DefaultTableModel tableModel;
    private JTextField txtBuscar;

    public DocumentoPanel(DocumentoEJBRemoto service) {
        this.service = service;
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        // Búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(new Color(245, 247, 250));
        searchPanel.add(new JLabel("Buscar por ID:"));
        txtBuscar = new JTextField(10);
        searchPanel.add(txtBuscar);

        // Tabla
        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Paciente", "Descripción", "Observaciones"}, 0
        );
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Eventos de búsqueda
        txtBuscar.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (!Beans.isDesignTime()) {
                    buscarDocumento();
                }
            }
        });

        // ⚡ Carga de datos
        if (Beans.isDesignTime()) {
            // Preview WindowBuilder → datos dummy
            tableModel.addRow(new Object[]{"-", "-", "-", "-"});
            tableModel.addRow(new Object[]{"-", "-", "-", "-"});
        } else {
            // Runtime → cargar desde EJB
            refrescarTabla();
        }
    }

    private void buscarDocumento() {
        try {
            String filtro = txtBuscar.getText().trim();
            tableModel.setRowCount(0);
            if (filtro.isEmpty()) {
                refrescarTabla();
                return;
            }
            Documento doc = service.buscarPorId(Integer.parseInt(filtro));
            if (doc != null) {
                tableModel.addRow(new Object[]{
                        doc.getId(), doc.getPaciente(), doc.getDescripcion(), doc.getObservaciones()
                });
            }
        } catch (Exception ignored) {}
    }

    private void refrescarTabla() {
        try {
            List<Documento> documentos = service.listar();
            tableModel.setRowCount(0);
            for (Documento doc : documentos) {
                tableModel.addRow(new Object[]{
                        doc.getId(), doc.getPaciente(), doc.getDescripcion(), doc.getObservaciones()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error cargando documentos: " + ex.getMessage());
        }
    }
}
