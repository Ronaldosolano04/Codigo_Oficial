package proyectofinalrsg;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaGestionAlmacen extends JFrame {
    // Base de datos en memoria
    private Map<String, Usuario> usuarios = new HashMap<>();
    private List<Producto> productos = new ArrayList<>();
    private Usuario usuarioActual;
    
    // Componentes principales
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private JPanel loginPanel;
    private JPanel registroPanel;
    private JPanel mainMenuPanel;
    private JPanel gestionUsuariosPanel;
    private JPanel gestionProductosPanel;
    private JPanel detalleProductoPanel;
    
    // Colores modernos
    private Color colorPrimario = new Color(41, 128, 185);
    private Color colorSecundario = new Color(52, 152, 219);
    private Color colorFondo = new Color(245, 245, 245);
    private Color colorTexto = new Color(44, 62, 80);
    private Color colorBoton = new Color(46, 204, 113);
    private Color colorBotonSecundario = new Color(231, 76, 60);
    
    // Fuentes
    private Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 28);
    private Font fuenteSubtitulo = new Font("Segoe UI", Font.BOLD, 18);
    private Font fuenteNormal = new Font("Segoe UI", Font.PLAIN, 14);
    
    public SistemaGestionAlmacen() {
        super("Sistema de Gestión de Almacén");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        
        // Crear usuario admin por defecto
        Usuario admin = new Usuario("admin", "Admin", "Sistema", "123456789", "admin@almacen.com", "admin123");
        usuarios.put("admin", admin);
        
        // Crear algunos productos de ejemplo
        productos.add(new Producto("Laptop HP", "HP", "Electrónicos", 850.99, 15));
        productos.add(new Producto("Monitor 24\"", "Samsung", "Electrónicos", 199.50, 30));
        productos.add(new Producto("Silla de Oficina", "Ergo", "Muebles", 129.99, 25));
        
        // Inicializar paneles
        crearLoginPanel();
        crearRegistroPanel();
        crearMainMenuPanel();
        crearGestionUsuariosPanel();
        crearGestionProductosPanel();
        crearDetalleProductoPanel();
        
        // Agregar paneles al card layout
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registroPanel, "registro");
        mainPanel.add(mainMenuPanel, "menu");
        mainPanel.add(gestionUsuariosPanel, "usuarios");
        mainPanel.add(gestionProductosPanel, "productos");
        mainPanel.add(detalleProductoPanel, "detalleProducto");
        
        add(mainPanel);
        cardLayout.show(mainPanel, "login");
        setVisible(true);
    }
    
    private void crearLoginPanel() {
        loginPanel = new JPanel(new BorderLayout(10, 20));
        loginPanel.setBackground(colorFondo);
        loginPanel.setBorder(new EmptyBorder(20, 40, 40, 40));
        
        // Panel superior con título
        JPanel topPanel = new JPanel();
        topPanel.setBackground(colorFondo);
        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DE ALMACÉN");
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(colorPrimario);
        topPanel.add(titulo);
        loginPanel.add(topPanel, BorderLayout.NORTH);
        
        // Panel central con formulario
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(colorFondo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        JLabel subtitulo = new JLabel("Inicio de Sesión");
        subtitulo.setFont(fuenteSubtitulo);
        subtitulo.setForeground(colorTexto);
        centerPanel.add(subtitulo, gbc);
        
        gbc.gridy++;
        centerPanel.add(crearCampo("Usuario", new JTextField(20)), gbc);
        
        gbc.gridy++;
        JPasswordField passwordField = new JPasswordField(20);
        centerPanel.add(crearCampo("Contraseña", passwordField), gbc);
        
        gbc.gridy++;
        JButton loginBtn = new JButton("Iniciar Sesión");
        estiloBotonPrimario(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = ((JTextField)((JPanel)centerPanel.getComponent(2)).getComponent(1)).getText();
                char[] password = passwordField.getPassword();
                if (usuario.isEmpty() || password.length == 0) {
                    JOptionPane.showMessageDialog(SistemaGestionAlmacen.this, "Debe ingresar su usuario y contraseña", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (usuarios.containsKey(usuario)) {
                    Usuario u = usuarios.get(usuario);
                    if (new String(password).equals(u.getPassword())) {
                        usuarioActual = u;
                        cardLayout.show(mainPanel, "menu");
                    } else {
                        JOptionPane.showMessageDialog(SistemaGestionAlmacen.this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(SistemaGestionAlmacen.this, "Usuario no registrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        centerPanel.add(loginBtn, gbc);
        
        gbc.gridy++;
        JButton registroBtn = new JButton("Registrarse");
        estiloBotonSecundario(registroBtn);
        registroBtn.addActionListener(e -> cardLayout.show(mainPanel, "registro"));
        centerPanel.add(registroBtn, gbc);
        
        loginPanel.add(centerPanel, BorderLayout.CENTER);
    }
    
    private void crearRegistroPanel() {
        registroPanel = new JPanel(new BorderLayout(10, 20));
        registroPanel.setBackground(colorFondo);
        registroPanel.setBorder(new EmptyBorder(20, 40, 40, 40));
        
        // Panel superior con título
        JPanel topPanel = new JPanel();
        topPanel.setBackground(colorFondo);
        JLabel titulo = new JLabel("Registro de Usuario");
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(colorPrimario);
        topPanel.add(titulo);
        registroPanel.add(topPanel, BorderLayout.NORTH);
        
        // Panel central con formulario
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(colorFondo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        JLabel subtitulo = new JLabel("Complete todos los campos");
        subtitulo.setFont(fuenteSubtitulo);
        subtitulo.setForeground(colorTexto);
        centerPanel.add(subtitulo, gbc);
        
        gbc.gridy++;
        JTextField usuarioField = new JTextField(20);
        centerPanel.add(crearCampo("Usuario*", usuarioField), gbc);
        
        gbc.gridy++;
        JTextField nombreField = new JTextField(20);
        centerPanel.add(crearCampo("Nombre*", nombreField), gbc);
        
        gbc.gridy++;
        JTextField apellidoField = new JTextField(20);
        centerPanel.add(crearCampo("Apellido*", apellidoField), gbc);
        
        gbc.gridy++;
        JTextField telefonoField = new JTextField(20);
        centerPanel.add(crearCampo("Teléfono*", telefonoField), gbc);
        
        gbc.gridy++;
        JTextField emailField = new JTextField(20);
        centerPanel.add(crearCampo("Email*", emailField), gbc);
        
        gbc.gridy++;
        JPasswordField passwordField = new JPasswordField(20);
        centerPanel.add(crearCampo("Contraseña*", passwordField), gbc);
        
        gbc.gridy++;
        JPasswordField confirmPasswordField = new JPasswordField(20);
        centerPanel.add(crearCampo("Confirmar Contraseña*", confirmPasswordField), gbc);
        
        gbc.gridy++;
        JButton registroBtn = new JButton("Registrarse");
        estiloBotonPrimario(registroBtn);
        registroBtn.addActionListener(e -> {
            // Validar campos obligatorios
            if(usuarioField.getText().isEmpty() || nombreField.getText().isEmpty() ||
               apellidoField.getText().isEmpty() || telefonoField.getText().isEmpty() ||
               emailField.getText().isEmpty() || passwordField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validar contraseñas
            if(!new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Crear nuevo usuario
            Usuario nuevoUsuario = new Usuario(
                usuarioField.getText(),
                nombreField.getText(),
                apellidoField.getText(),
                telefonoField.getText(),
                emailField.getText(),
                new String(passwordField.getPassword())
            );
            
            usuarios.put(usuarioField.getText(), nuevoUsuario);
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(mainPanel, "login");
        });
        centerPanel.add(registroBtn, gbc);
        
        gbc.gridy++;
        JButton volverBtn = new JButton("Volver al Login");
        estiloBotonSecundario(volverBtn);
        volverBtn.addActionListener(e -> cardLayout.show(mainPanel, "login"));
        centerPanel.add(volverBtn, gbc);
        
        registroPanel.add(centerPanel, BorderLayout.CENTER);
    }
    
    private void crearMainMenuPanel() {
        mainMenuPanel = new JPanel(new BorderLayout(10, 20));
        mainMenuPanel.setBackground(colorFondo);
        mainMenuPanel.setBorder(new EmptyBorder(20, 40, 40, 40));
        
        // Panel superior con título
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(colorFondo);
        
        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DE ALMACÉN");
        titulo.setFont(fuenteTitulo);
        titulo.setForeground(colorPrimario);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titulo, BorderLayout.CENTER);
        
        JButton logoutBtn = new JButton("Cerrar Sesión");
        estiloBotonSecundario(logoutBtn);
        logoutBtn.addActionListener(e -> {
            usuarioActual = null;
            cardLayout.show(mainPanel, "login");
        });
        topPanel.add(logoutBtn, BorderLayout.EAST);
        
        mainMenuPanel.add(topPanel, BorderLayout.NORTH);
        
        // Panel central con botones
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 30, 30));
        centerPanel.setBackground(colorFondo);
        centerPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        
        JButton usuariosBtn = crearBotonMenu("USUARIOS", new ImageIcon("usuarios.png"));
        usuariosBtn.addActionListener(e -> {
            actualizarTablaUsuarios();
            cardLayout.show(mainPanel, "usuarios");
        });
        
        JButton productosBtn = crearBotonMenu("PRODUCTOS", new ImageIcon("productos.png"));
        productosBtn.addActionListener(e -> {
            actualizarTablaProductos();
            cardLayout.show(mainPanel, "productos");
        });
        
        centerPanel.add(usuariosBtn);
        centerPanel.add(productosBtn);
        
        mainMenuPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Panel inferior con información de usuario
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBackground(colorFondo);
        JLabel infoUsuario = new JLabel("Usuario: ");
        infoUsuario.setFont(fuenteNormal);
        infoUsuario.setForeground(colorTexto);
        bottomPanel.add(infoUsuario);
        mainMenuPanel.add(bottomPanel, BorderLayout.SOUTH);
    }
    
    private void crearGestionUsuariosPanel() {
        gestionUsuariosPanel = new JPanel(new BorderLayout(10, 10));
        gestionUsuariosPanel.setBackground(colorFondo);
        gestionUsuariosPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // Panel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(colorFondo);
        
        JLabel titulo = new JLabel("Gestión de Usuarios");
        titulo.setFont(fuenteSubtitulo);
        titulo.setForeground(colorPrimario);
        topPanel.add(titulo, BorderLayout.WEST);
        
        JButton volverBtn = new JButton("Volver al Menú");
        estiloBotonSecundario(volverBtn);
        volverBtn.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
        topPanel.add(volverBtn, BorderLayout.EAST);
        
        gestionUsuariosPanel.add(topPanel, BorderLayout.NORTH);
        
        // Tabla de usuarios
        String[] columnNames = {"Usuario", "Nombre", "Apellido", "Teléfono", "Email", "Acciones"};
        Object[][] data = {};
        
        JTable tablaUsuarios = new JTable(data, columnNames);
        tablaUsuarios.setFillsViewportHeight(true);
        tablaUsuarios.setRowHeight(30);
        tablaUsuarios.getTableHeader().setFont(fuenteSubtitulo.deriveFont(Font.BOLD, 14));
        tablaUsuarios.setFont(fuenteNormal);
        
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
        gestionUsuariosPanel.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void crearGestionProductosPanel() {
        gestionProductosPanel = new JPanel(new BorderLayout(10, 10));
        gestionProductosPanel.setBackground(colorFondo);
        gestionProductosPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        // Panel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(colorFondo);
        
        JLabel titulo = new JLabel("Gestión de Productos");
        titulo.setFont(fuenteSubtitulo);
        titulo.setForeground(colorPrimario);
        topPanel.add(titulo, BorderLayout.WEST);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(colorFondo);
        
        JButton nuevoBtn = new JButton("Nuevo Producto");
        estiloBotonPrimario(nuevoBtn);
        nuevoBtn.addActionListener(e -> {
            // Mostrar formulario para nuevo producto
            mostrarFormularioProducto(null);
        });
        buttonPanel.add(nuevoBtn);
        
        JButton volverBtn = new JButton("Volver al Menú");
        estiloBotonSecundario(volverBtn);
        volverBtn.addActionListener(e -> cardLayout.show(mainPanel, "menu"));
        buttonPanel.add(volverBtn);
        
        topPanel.add(buttonPanel, BorderLayout.EAST);
        gestionProductosPanel.add(topPanel, BorderLayout.NORTH);
        
        // Tabla de productos
        String[] columnNames = {"ID", "Nombre", "Marca", "Categoría", "Precio", "Cantidad", "Acciones"};
        Object[][] data = {};
        
        JTable tablaProductos = new JTable(data, columnNames);
        tablaProductos.setFillsViewportHeight(true);
        tablaProductos.setRowHeight(30);
        tablaProductos.getTableHeader().setFont(fuenteSubtitulo.deriveFont(Font.BOLD, 14));
        tablaProductos.setFont(fuenteNormal);
        
        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBorder(new LineBorder(Color.LIGHT_GRAY));
        gestionProductosPanel.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void crearDetalleProductoPanel() {
        detalleProductoPanel = new JPanel(new BorderLayout(10, 10));
        detalleProductoPanel.setBackground(colorFondo);
        detalleProductoPanel.setBorder(new EmptyBorder(20, 40, 40, 40));
        
        // Panel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(colorFondo);
        
        JLabel titulo = new JLabel("Detalle de Producto");
        titulo.setFont(fuenteSubtitulo);
        titulo.setForeground(colorPrimario);
        topPanel.add(titulo, BorderLayout.WEST);
        
        JButton volverBtn = new JButton("Volver a Productos");
        estiloBotonSecundario(volverBtn);
        volverBtn.addActionListener(e -> cardLayout.show(mainPanel, "productos"));
        topPanel.add(volverBtn, BorderLayout.EAST);
        
        detalleProductoPanel.add(topPanel, BorderLayout.NORTH);
        
        // Formulario de detalle
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(colorFondo);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        JTextField nombreField = new JTextField(25);
        formPanel.add(crearCampo("Nombre*", nombreField), gbc);
        
        gbc.gridy++;
        JTextField marcaField = new JTextField(25);
        formPanel.add(crearCampo("Marca*", marcaField), gbc);
        
        gbc.gridy++;
        JTextField categoriaField = new JTextField(25);
        formPanel.add(crearCampo("Categoría*", categoriaField), gbc);
        
        gbc.gridy++;
        JTextField precioField = new JTextField(25);
        formPanel.add(crearCampo("Precio*", precioField), gbc);
        
        gbc.gridy++;
        JTextField cantidadField = new JTextField(25);
        formPanel.add(crearCampo("Cantidad Disponible*", cantidadField), gbc);
        
        gbc.gridy++;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(colorFondo);
        
        JButton guardarBtn = new JButton("Guardar");
        estiloBotonPrimario(guardarBtn);
        buttonPanel.add(guardarBtn);
        
        JButton eliminarBtn = new JButton("Eliminar");
        estiloBotonSecundario(eliminarBtn);
        buttonPanel.add(eliminarBtn);
        
        formPanel.add(buttonPanel, gbc);
        
        detalleProductoPanel.add(formPanel, BorderLayout.CENTER);
    }
    
    // Métodos auxiliares
    private JPanel crearCampo(String etiqueta, JComponent componente) {
        JPanel panel = new JPanel(new BorderLayout(10, 5));
        panel.setBackground(colorFondo);
        
        JLabel label = new JLabel(etiqueta);
        label.setFont(fuenteNormal.deriveFont(Font.BOLD));
        label.setForeground(colorTexto);
        panel.add(label, BorderLayout.NORTH);
        
        componente.setFont(fuenteNormal);
        if(componente instanceof JTextField) {
            ((JTextField)componente).setBorder(new LineBorder(Color.GRAY, 1));
        } else if(componente instanceof JPasswordField) {
            ((JPasswordField)componente).setBorder(new LineBorder(Color.GRAY, 1));
        }
        panel.add(componente, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JButton crearBotonMenu(String texto, Icon icono) {
        JButton boton = new JButton(texto, icono);
        boton.setFont(fuenteSubtitulo.deriveFont(Font.BOLD));
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorSecundario);
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(20, 20, 20, 20));
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                boton.setBackground(colorPrimario);
            }
            public void mouseExited(MouseEvent evt) {
                boton.setBackground(colorSecundario);
            }
        });
        
        return boton;
    }
    
    private void estiloBotonPrimario(JButton boton) {
        boton.setFont(fuenteNormal.deriveFont(Font.BOLD));
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorBoton);
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(8, 20, 8, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void estiloBotonSecundario(JButton boton) {
        boton.setFont(fuenteNormal.deriveFont(Font.BOLD));
        boton.setForeground(Color.WHITE);
        boton.setBackground(colorBotonSecundario);
        boton.setFocusPainted(false);
        boton.setBorder(new EmptyBorder(8, 20, 8, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void actualizarTablaUsuarios() {
        // Implementación para actualizar tabla de usuarios
    }
    
    private void actualizarTablaProductos() {
        // Implementación para actualizar tabla de productos
    }
    
    private void mostrarFormularioProducto(Producto producto) {
        // Implementación para mostrar formulario de producto
        cardLayout.show(mainPanel, "detalleProducto");
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new SistemaGestionAlmacen());
    }
    
    // Clases de modelo
    class Usuario {
        private String username;
        private String nombre;
        private String apellido;
        private String telefono;
        private String email;
        private String password;
        
        public Usuario(String username, String nombre, String apellido, String telefono, String email, String password) {
            this.username = username;
            this.nombre = nombre;
            this.apellido = apellido;
            this.telefono = telefono;
            this.email = email;
            this.password = password;
        }
        
        // Getters y setters
        public String getUsername() { return username; }
        public String getNombre() { return nombre; }
        public String getApellido() { return apellido; }
        public String getTelefono() { return telefono; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
    }
    
    class Producto {
        private static int nextId = 1;
        private int id;
        private String nombre;
        private String marca;
        private String categoria;
        private double precio;
        private int cantidadDisponible;
        
        public Producto(String nombre, String marca, String categoria, double precio, int cantidadDisponible) {
            this.id = nextId++;
            this.nombre = nombre;
            this.marca = marca;
            this.categoria = categoria;
            this.precio = precio;
            this.cantidadDisponible = cantidadDisponible;
        }
        
        // Getters y setters
        public int getId() { return id; }
        public String getNombre() { return nombre; }
        public String getMarca() { return marca; }
        public String getCategoria() { return categoria; }
        public double getPrecio() { return precio; }
        public int getCantidadDisponible() { return cantidadDisponible; }
    }
}