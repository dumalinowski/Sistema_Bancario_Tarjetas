package bo;
import java.util.List;

import exception.BancoException;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Usuario;
import persistencia.CuentaDAO;

public class CuentaBO
{
	private MovimientoBO movBo;
	private CuentaDAO dao;
	
	public CuentaBO() {}
	
	public CuentaBO(CuentaDAO dao, MovimientoBO movBo)
	{
		this.movBo = movBo;
		this.dao = dao;
	}
	
	public void setDao(CuentaDAO newDao)
	{
		dao = newDao;
	}
	
	public void altaCuenta(String owner) throws BancoException
	{ //Habia sacado esta validacion porque el campo numero de cuenta es ignorado al escribir a la base de datos, ya que en la base el numero de cuenta es una PK que se auto incrementa.
	  //Solo se usa ese campo cuando leo una cuenta desde la DB para despues mostrar ese dato en la lista de cuentas.
		dao.addAccount(new Cuenta(owner, 0, 0)); //Nada que validar aca, el numero de cuenta no importa porque es un valor que lo auto incrementa la base, y el saldo siempre arranca en 0.
	}
	
	public void bajaCuenta(int numeroCuenta) throws BancoException
	{
		if(dao.accountExists(numeroCuenta)) {
			dao.eraseAccount(numeroCuenta);
		}
		else throw new BancoException("Cuenta inexistente");
	}
	
	public void modificarCuenta(Cuenta cuenta) throws BancoException
	{
		if(dao.accountExists(cuenta.getNumeroCuenta())) {
			dao.setAccount(cuenta);
		}
		else throw new BancoException("Cuenta inexistente");
	}
	
	public Cuenta obtenerCuenta(int numeroCuenta) throws BancoException
	{
		return dao.getAccount(numeroCuenta);
	}
	
	public List<Cuenta> obtenerListaCuentas(String userName) throws BancoException
	{
		return dao.getAllAccounts(userName);
	}
	
	public boolean cuentaExiste(int numeroCuenta) throws BancoException
	{
		return dao.accountExists(numeroCuenta);
	}
	
	public boolean depositarCuenta(Usuario currentUser, int numeroCuenta, int importe, String operacion) throws BancoException //Deposito - Extraccion
	{
		boolean result = false;
		Cuenta cuenta = obtenerCuenta(numeroCuenta);
			
		if(operacion.equals("Deposito")) {
			cuenta.setSaldo(cuenta.getSaldo() + importe);
			movBo.altaMovimiento(new Movimiento(cuenta.getNumeroCuenta(), importe, operacion));
		}
		else if(operacion.equals("Extraccion"))
		{
			if(importe <= cuenta.getSaldo())
			{
				if (cuenta.getOwner().equals(currentUser.getUser()))
				{
					cuenta.setSaldo(cuenta.getSaldo() - importe);
					movBo.altaMovimiento(new Movimiento(cuenta.getNumeroCuenta(), importe, operacion));
					result = true;
				}
				else throw new BancoException("Cuenta ajena");
			}
			else throw new BancoException("Fondos insuficientes");
		}
		modificarCuenta(cuenta);
		return result;
	}
}