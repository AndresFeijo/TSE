package facultad.tse.practico.cliente;

import facultad.tse.practico.service.DocumentoEJBRemoto;

import javax.swing.*;
import java.awt.*;
import java.beans.Beans;

public class NuevoDocumentoPanel extends JPanel {
    private DocumentoEJBRemoto service;
    private JTextField txtId, txtPaciente, txtDescripcion, txtObservaciones;

    public NuevoDocumentoPanel(DocumentoEJBRemoto service) {
        this.service = service;
        setLayout(new GridLayout(5, 2, 5, 5));
        setBorder(BorderFactory.createTitledBorder("Agregar Documento"));
        setBackground(new Color(245, 247, 250));

        txtId = new JTextField();
        txtPaciente = new JTextField();
        txtDescripcion = new JTextField();
        txtObservaciones = new JTextField();

        add(new JLabel("ID:"));
        add(txtId);
        add(new JLabel("Paciente:"));
        add(txtPaciente);
        add(new JLabel("Descripción:"));
        add(txtDescripcion);
        add(new JLabel("Observaciones:"));
        add(txtObservaciones);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(0, 120, 255));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        add(btnAgregar);

        btnAgregar.addActionListener(e -> agregarDocumento());

        // ⚡ Preview dummy en WindowBuilder
        if (Beans.isDesignTime()) {
            txtId.setText("1");
            txtPaciente.setText("Nombre Paciente");
            txtDescripcion.setText("Descripción ejemplo");
            txtObservaciones.setText("Observaciones ejemplo");
        }
    }

    private void agregarDocumento() {
        try {
            int id = Integer.parseInt(txtId.getText());
            String paciente = txtPaciente.getText();
            String descripcion = txtDescripcion.getText();
            String observaciones = txtObservaciones.getText();

            if (!Beans.isDesignTime() && service != null) {
                service.agregar(id, paciente, descripcion, observaciones);
            }

            JOptionPane.showMessageDialog(this, "Documento agregado!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
