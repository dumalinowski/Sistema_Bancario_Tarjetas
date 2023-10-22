package bo;
import entidades.Cuenta;
import entidades.Tarjeta;
import persistencia.TarjetaDAO;
import exception.BancoException;

public class TarjetaBO
{
	private CuentaBO cuentaBo;
	private TarjetaDAO dao;
	
	public TarjetaBO(TarjetaDAO dao, CuentaBO cuentaBo, MovimientoBO movBo)
	{
		this.cuentaBo = cuentaBo;
		this.dao = dao;
	}
	
	public void setDao(TarjetaDAO newDao)
	{
		dao = newDao;
	}
	
	public void altaTarjeta(String owner) throws BancoException
	{
		if(!tieneTarjeta(owner)) { //Si este usuario no tiene tarjetas...
			dao.addCard(0, owner, 0, 10000, 0);
		}
		else throw new BancoException("Este usuario ya tiene tarjeta");
	}
	
	public void bajaTarjeta(int numeroTarjeta) throws BancoException
	{
		dao.eraseCard(numeroTarjeta);
	}
	
	public Tarjeta obtenerTarjeta(int numeroTarjeta) throws BancoException //obtener tarjeta por su numero
	{
		return dao.getCard(numeroTarjeta);
	}
	
	public Tarjeta obtenerTarjeta(String owner) throws BancoException //obtener tarjeta por su due?o
	{
		return dao.getCard(owner);
	}
	
	public void modificarTarjeta(Tarjeta tarjeta) throws BancoException
	{
		dao.setCard(tarjeta);
	}
	
	public boolean tieneTarjeta(String owner)
	{
		boolean result = true;
		
		try {
			dao.getCard(owner);
		}
		catch(BancoException e) {
			result = false;
		}
		
		return result;
	}
	
	public void compraConTarjeta(String owner, int monto) throws BancoException //realizar compra con la tarjeta de este usuario
	{
		if(monto < 0) throw new BancoException("Monto no valido");
		Tarjeta tarjeta = obtenerTarjeta(owner);
		
		if(tarjeta.getCredito() + monto <= tarjeta.getLimiteCredito())
		{
			tarjeta.setCredito(tarjeta.getCredito() + monto);
			modificarTarjeta(tarjeta);
		}
		else throw new BancoException("Saldo insuficiente");
	}
	
	public void pagarTarjeta(int numeroTarjeta, int numeroCuenta) throws BancoException
	{
		Tarjeta tarjeta = obtenerTarjeta(numeroTarjeta);
		Cuenta cuenta = cuentaBo.obtenerCuenta(numeroCuenta);
		if(!tarjeta.getOwner().equals(cuenta.getOwner())) throw new BancoException("La tarjeta y la cuenta no le pertenecen al mismo usuario");
		
		if(cuenta.getSaldo() >= tarjeta.getCredito())
		{
			cuenta.setSaldo(cuenta.getSaldo() - tarjeta.getCredito());
			tarjeta.setCredito(0);
			cuentaBo.modificarCuenta(cuenta);
			modificarTarjeta(tarjeta);
		}
		else throw new BancoException("Saldo insuficiente en cuenta");
	}
}