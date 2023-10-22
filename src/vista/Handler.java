package vista;
import exception.BancoException;
import entidades.Cuenta;
import entidades.Usuario;
import entidades.Movimiento;
import persistencia.MovimientoDAOImpl;
import entidades.Tarjeta;
import persistencia.DBManager;

import javax.swing.JOptionPane;

import bo.CuentaBO;
import bo.MovimientoBO;
import bo.UsuarioBO;
import bo.TarjetaBO;

import persistencia.UsuarioDAOImpl;
import persistencia.CuentaDAOImpl;
import persistencia.TarjetaDAOImpl;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class Handler
{
	private CuentaBO cuentaNegocio;
	private MovimientoBO movNegocio;
	private TarjetaBO tarjetaNegocio;
	private UsuarioBO usuarioNegocio;
	private Frame frame;
	private Usuario user = null; //usuario logeado actualmente
	
	public Handler()
	{
		movNegocio = new MovimientoBO();
		movNegocio.setDao(new MovimientoDAOImpl());
		cuentaNegocio = new CuentaBO(new CuentaDAOImpl(), movNegocio);
		tarjetaNegocio = new TarjetaBO(new TarjetaDAOImpl(), cuentaNegocio, movNegocio);
		usuarioNegocio = new UsuarioBO(new UsuarioDAOImpl(), tarjetaNegocio, movNegocio, cuentaNegocio);
		
		frame = new Frame(this, "Sistema");
		frame.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e) {
				DBManager.getInstance().shutdown();
		    }
		});
		frame.setVisible(true);
		frame.setPanel(new LoginPanel(this));
	}
	
	public void mostrarAltaUsuario()
	{
		frame.setPanel(new NewUserPanel(this));
	}
	
	public void mostrarModificacionUsuario()
	{
		frame.setPanel(new ModifyUserPanel(this));
	}
	
	public void mostrarListaCuentas()
	{
		frame.setPanel(new AccountListPanel(this));
	}
	
	public void mostrarLogin()
	{
		frame.setPanel(new LoginPanel(this));
	}
	
	public void mostrarTransaccion()
	{
		frame.setPanel(new TransactionPanel(this));
	}
	
	public void mostrarTransaccion(int numeroCuenta)
	{
		frame.setPanel(new TransactionPanel(this, numeroCuenta));
	}
	
	public void mostrarMovimientos(int numeroCuenta)
	{
		frame.setPanel(new MovementListPanel(this, numeroCuenta));
	}
	
	public void mostrarTarjeta()
	{
		try {
			frame.setPanel(new CardPanel(this, tarjetaNegocio.obtenerTarjeta(getLoggedUser().getUser())));
		}
		catch(BancoException e) {
			mostrarError(e.getMessage());
		}
	}
	
	public void mostrarTransaccionTarjetas(int numeroTarjeta)
	{
		frame.setPanel(new CardTransactionPanel(this, numeroTarjeta));
	}
	
	public void mostrarPagoTarjeta()
	{
		frame.setPanel(new PagarTarjetaPanel(this));
	}
	
	public void login(String userName, String password)
	{
		try
		{
			user = usuarioNegocio.login(userName, password); //throws
			frame.showMenuBar(true);
			frame.setPanel(null);
		}
		catch(BancoException e)
		{
			mostrarError(e.getMessage());
		}
	}
	
	public void logout()
	{
		this.user = null;
		frame.showMenuBar(false);
		mostrarLogin();
	}
	
	public Usuario getLoggedUser()
	{
		return user;
	}
	
	public Tarjeta getLoggedUserCard() throws BancoException
	{
		return tarjetaNegocio.obtenerTarjeta(getLoggedUser().getUser());
	}
	
	public void bajaUsuarioLogeado()
	{
		bajaUsuario(user.getUser());
		logout();
	}
	
	public void altaUsuario(String user, String password, String nombre, String apellido, int dni)
	{
		try
		{
			usuarioNegocio.altaUsuario(user, password, nombre, apellido, dni);
			mostrarMensaje("Se dio de alta el usuario " + user + " correctamente");
			
			if(this.user != null) {
				frame.setPanel(null);
			}
			else mostrarLogin();
		}
		catch(BancoException de) { mostrarError(de.getMessage()); }
	}
	
	public void altaCuenta()
	{
		try
		{
			cuentaNegocio.altaCuenta(getLoggedUser().getUser());
			mostrarMensaje("Se dio de alta la cuenta correctamente");
		}
		catch(BancoException e)
		{
			mostrarError(e.getMessage());
		}
	}
	
	public void altaTarjeta()
	{
		try
		{
			tarjetaNegocio.altaTarjeta(getLoggedUser().getUser());
			mostrarMensaje("Se dio de alta la tarjeta correctamente");
		}
		catch(BancoException e)
		{
			mostrarError(e.getMessage());
		}
	}
	
	public void bajaUsuario(String userName)
	{
		try
		{
			usuarioNegocio.bajaUsuario(userName);
			mostrarMensaje("Se dio de baja el usuario " + userName + " correctamente");
			frame.setPanel(null);
		}
		catch(BancoException de) { mostrarError(de.getMessage()); }
	}
	
	public void modificarUsuario(Usuario user)
	{
		try
		{
			usuarioNegocio.modificarUsuario(user);
			mostrarMensaje("Se modifico el usuario " + user.getUser() + " correctamente");
		}
		catch (BancoException de) { mostrarError(de.getMessage()); }
	}
	
	public void modificarCuenta(Cuenta cuenta) //No llamado por nadie
	{
		try
		{
			cuentaNegocio.modificarCuenta(cuenta);
		}
		catch(BancoException e)
		{
			mostrarError(e.getMessage());
		}
	}
	
	public void modificarTarjeta(Tarjeta tarjeta)
	{
		try
		{
			tarjetaNegocio.modificarTarjeta(tarjeta);
		}
		catch(BancoException e)
		{
			mostrarError("Error al modificar tarjeta");
		}
	}
	
	public List<Usuario> obtenerListaUsuarios() throws BancoException
	{
		return usuarioNegocio.obtenerListaUsuarios();
	}
	
	public List<Cuenta> obtenerListaCuentas(String userName)
	{
		List<Cuenta> result = null;
		try
		{
			result = cuentaNegocio.obtenerListaCuentas(userName);	
		}
		catch(BancoException e)
		{
			mostrarError(e.getMessage());
		}
		return result;
	}
	
	public List<Movimiento> obtenerListaMovimientos(int numeroCuenta)
	{
		List<Movimiento> result = null;
		try
		{
			result = movNegocio.obtenerListaMovimientos(numeroCuenta);
		}
		catch(BancoException e)
		{
			mostrarError(e.getMessage());
		}
		return result;
	}

	public Usuario obtenerUsuario(String userName) throws BancoException
	{
		return usuarioNegocio.obtenerUsuario(userName);
	}
	
	public Cuenta obtenerCuenta(int nroCuenta) throws BancoException
	{
		return cuentaNegocio.obtenerCuenta(nroCuenta);
	}
	
	public Tarjeta obtenerTarjeta(int numeroTarjeta) throws BancoException
	{
		return tarjetaNegocio.obtenerTarjeta(numeroTarjeta);
	}
	
	public void depositarCuenta(int numeroCuenta, int importe, String operacion) //Deposito - Extraccion
	{
		try
		{
			cuentaNegocio.depositarCuenta(getLoggedUser(), numeroCuenta, importe, operacion);
			mostrarMensaje("Transaccion exitosa");
		}
		catch(BancoException e) {
			mostrarError(e.getMessage());
		}
	}
	
	public void pagarTarjeta(int numeroCuenta)
	{
		try
		{
			tarjetaNegocio.pagarTarjeta(tarjetaNegocio.obtenerTarjeta(getLoggedUser().getUser()).getNumeroTarjeta(), numeroCuenta);
			mostrarMensaje("Tarjeta pagada correctamente");
		}
		catch(BancoException except) {
			mostrarError(except.getMessage());
		}
	}
	
	public void compraConTarjeta(int monto)
	{
		if(monto > 0)
		{
			try {
				//movNegocio.altaMovimiento(new Movimiento(getLoggedUserCard().getNumeroTarjeta(), monto, "Tarjeta"));
				tarjetaNegocio.compraConTarjeta(getLoggedUser().getUser(), monto);
				mostrarMensaje("Compra exitosa");
			}
			catch(BancoException e) {
				mostrarError(e.getMessage());
			}
		}
		else mostrarError("Monto no valido");
	}
	
	public void mostrarTransaccionTarjetaActual() //tarjeta actual seria la del usuario logeado.
	{
		try {
			mostrarTransaccionTarjetas(tarjetaNegocio.obtenerTarjeta(getLoggedUser().getUser()).getNumeroTarjeta());
		}
		catch(BancoException e) {
			mostrarError(e.getMessage());
		}
	}
	
	public void mostrarMensaje(String msg)
	{
		JOptionPane.showMessageDialog(null, msg, "Notificacion", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void mostrarError(String msg)
	{
		JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void cerrar()
	{
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
}