package facultad.tse.practico.cliente;

import com.formdev.flatlaf.FlatLightLaf;
import facultad.tse.practico.service.DocumentoEJBRemoto;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class EstiloWeb extends JFrame {
    private DocumentoEJBRemoto service;
    private JPanel content; // Panel principal dinámico

    public EstiloWeb() {
        conectarEJB();

        setTitle("Consultorio TSE - Cliente Swing");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Consultorio TSE");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));

        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        headerRight.setBackground(Color.WHITE);
        JLabel user = new JLabel("Profesional de la salud");
        JButton nuevoDoc = new JButton("Nuevo Documento");
        nuevoDoc.setBackground(new Color(0, 120, 255));
        nuevoDoc.setForeground(Color.WHITE);
        nuevoDoc.setFocusPainted(false);
        nuevoDoc.setFont(new Font("SansSerif", Font.PLAIN, 14));

        headerRight.add(user);
        headerRight.add(nuevoDoc);

        header.add(titulo, BorderLayout.WEST);
        header.add(headerRight, BorderLayout.EAST);

        // SIDEBAR
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(33, 37, 41));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        JLabel menuTitle = new JLabel("Menú");
        menuTitle.setForeground(Color.WHITE);
        menuTitle.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));

        JButton btnDocs = crearBotonSidebar("Documentos");

        sidebar.add(menuTitle);
        sidebar.add(btnDocs);

        // CONTENIDO PRINCIPAL
        content = new JPanel(new BorderLayout());
        content.setBackground(new Color(245, 247, 250));

        // Contenido inicial (bienvenida)
        mostrarBienvenida();

        // LAYOUT GENERAL
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);

        // EVENTOS DE BOTONES
        btnDocs.addActionListener(e -> {
            DocumentoPanel dp = new DocumentoPanel(service);
            mostrarPanelCentral(dp);
        });

        nuevoDoc.addActionListener(e -> {
            NuevoDocumentoPanel ndp = new NuevoDocumentoPanel(service);
            mostrarPanelCentral(ndp);
        });

    }

    private JButton crearBotonSidebar(String texto) {
        JButton btn = new JButton(texto);
        btn.setMaximumSize(new Dimension(200, 40));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(33, 37, 41));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        return btn;
    }

    private void mostrarPanelCentral(JPanel panel) {
        content.removeAll();
        content.add(panel, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    
    private void conectarEJB() {
        try {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            props.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
            

            Context ctx = new InitialContext(props);
            
            String jdniname =  
            		"ejb:practico/practico-ejb/DocumentoEJB!facultad.tse.practico.service.DocumentoEJBRemoto";
            
            service = (DocumentoEJBRemoto) ctx.lookup(jdniname);
        } catch (NamingException e) {
            JOptionPane.showMessageDialog(this, "Error conectando con EJB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ✅ Contenido inicial
    private void mostrarBienvenida() {
        content.removeAll();

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel bienvenida = new JLabel("Bienvenido al Consultorio TSE");
        bienvenida.setFont(new Font("SansSerif", Font.BOLD, 24));
        bienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Desde aquí podés gestionar tus documentos clínicos y acceder a la información de tus pacientes.");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitulo.setForeground(Color.GRAY);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton verDocs = new JButton("Ver Listado de Documentos");
        verDocs.setBackground(new Color(0, 120, 255));
        verDocs.setForeground(Color.WHITE);
        verDocs.setFocusPainted(false);
        verDocs.setAlignmentX(Component.CENTER_ALIGNMENT);
        verDocs.addActionListener(e -> mostrarDocumentoApp());

        card.add(bienvenida);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(subtitulo);
        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(verDocs);

        content.add(card, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    // ✅ Mostrar Documentos en el panel central
    private void mostrarDocumentoApp() {
        content.removeAll();
        DocumentoPanel dp = new DocumentoPanel(service);
        content.add(dp, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    private void mostrarFormularioNuevoDocumento() {
        content.removeAll();
        NuevoDocumentoPanel ndp = new NuevoDocumentoPanel(service);
        content.add(ndp, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new EstiloWeb().setVisible(true));
    }
}
