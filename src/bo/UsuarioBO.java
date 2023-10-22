package bo;
import java.util.List;

import exception.BancoException;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Usuario;
import persistencia.UsuarioDAO;

public class UsuarioBO
{
	private TarjetaBO tarjetaBo;
	private MovimientoBO movBo;
	private CuentaBO cuentaBo;
	private UsuarioDAO dao;
	
	public UsuarioBO() {}
	
	public UsuarioBO(UsuarioDAO dao, TarjetaBO tarjetaBo, MovimientoBO movimientoBo, CuentaBO cuentaBo)
	{
		this.dao = dao;
		this.tarjetaBo = tarjetaBo;
		this.movBo = movimientoBo;
		this.cuentaBo = cuentaBo;
	}
	
	public void setDao(UsuarioDAO newDao)
	{
		dao = newDao;
	}
	
	public Usuario login(String userName, String password) throws BancoException
	{
		Usuario result = null;
		
		try
		{
			Usuario user = obtenerUsuario(userName); //throws
			if(user.getPassword().equals(password))
			{
				result = user;
			}
			else throw new BancoException("Usuario o contrase?a incorrecto");
		}
		catch (BancoException e)
		{
			throw new BancoException("Usuario o contrase?a incorrecto");
		}
		
		return result;
	}
	
	public void altaUsuario(String user, String password, String nombre, String apellido, int dni) throws BancoException
	{
		if(!dao.userExists(user)) {
			dao.addUser(new Usuario(user, password, nombre, apellido, 0, dni));
		}
		else throw new BancoException("Usuario ya existe");
	}
	
	public void bajaUsuario(String userName) throws BancoException
	{
		if(dao.userExists(userName))
		{
			for(Cuenta cuenta : cuentaBo.obtenerListaCuentas(userName)) //Borrar cuentas asociadas a este usuario
			{
				for(Movimiento mov : movBo.obtenerListaMovimientos(cuenta.getNumeroCuenta())) {
					movBo.bajaMovimiento(mov.getNumeroMovimiento());
				}//hacer baja logica de las entidades, no baja fisica. Modificar las consultas para que no carguen las filas dadas de baja
				
				cuentaBo.bajaCuenta(cuenta.getNumeroCuenta());
			}
			if(tarjetaBo.tieneTarjeta(userName)) {
				tarjetaBo.bajaTarjeta(tarjetaBo.obtenerTarjeta(userName).getNumeroTarjeta());
			}
			dao.eraseUser(userName);
		}
		else throw new BancoException("Usuario inexistente");
	}
	
	public void modificarUsuario(Usuario user) throws BancoException
	{
		if(dao.userExists(user.getUser())) {
			dao.setUser(user);
		}
		else throw new BancoException("Usuario inexistente");
	}
	
	public Usuario obtenerUsuario(String userName) throws BancoException
	{
		return dao.getUser(userName);
	}
	
	public List<Usuario> obtenerListaUsuarios() throws BancoException
	{
		return dao.getAllUsers();
	}
	
	public boolean usuarioExiste(String userName) throws BancoException
	{
		return dao.userExists(userName);
	}
}