package vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar
{
	private Handler handler;
	
	MenuBar(Handler hndlr)
	{
		this.handler = hndlr;
		JMenu menuArchivo = new JMenu("Archivo");
		JMenuItem salir = new JMenuItem("Salir");
		JMenuItem logOut = new JMenuItem("Log Out");
		JMenu menuClientes = new JMenu("Clientes");
		JMenuItem listaCuentas = new JMenuItem("Lista de cuentas");
		JMenuItem bajaUsuario = new JMenuItem("Baja usuario");
		JMenuItem modificarUsuario = new JMenuItem("Modificar usuario");
		JMenuItem transaccion = new JMenuItem("Transaccion");
		JMenuItem tarjetas = new JMenuItem("Tarjeta");
		JMenuItem altaTarjeta = new JMenuItem("Alta tarjeta");
		
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (JOptionPane.showConfirmDialog(null, "Esta seguro?") == JOptionPane.OK_OPTION)
					handler.cerrar();
			}
		});
		listaCuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { handler.mostrarListaCuentas(); }
		});
		bajaUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (JOptionPane.showConfirmDialog(null, "Esta seguro?") == JOptionPane.OK_OPTION) {
					handler.bajaUsuarioLogeado();
				}
			}
		});
		modificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { handler.mostrarModificacionUsuario(); }
		});
		transaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { handler.mostrarTransaccion(); }
		});
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (JOptionPane.showConfirmDialog(null, "Esta seguro?") == JOptionPane.OK_OPTION) {
					handler.logout();
				}
			}
		});
		tarjetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				handler.mostrarTarjeta();
			}
		});
		altaTarjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				handler.altaTarjeta();
			}
		});
		
		menuArchivo.add(salir);
		menuArchivo.add(logOut);
		menuClientes.add(bajaUsuario);
		menuClientes.add(listaCuentas);
		menuClientes.add(modificarUsuario);
		menuClientes.add(transaccion);
		menuClientes.add(tarjetas);
		menuClientes.add(altaTarjeta);
		
		add(menuArchivo);
		add(menuClientes);
	}
}