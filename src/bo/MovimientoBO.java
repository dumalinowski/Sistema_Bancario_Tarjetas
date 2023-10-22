package bo;
import java.util.List;
import exception.BancoException;
import entidades.Movimiento;
import persistencia.MovimientoDAO;

public class MovimientoBO
{
	private MovimientoDAO dao;
	
	public MovimientoBO() {}
	
	public MovimientoBO(MovimientoDAO dao)
	{
		this.dao = dao;
	}
	
	public void setDao(MovimientoDAO dao)
	{
		this.dao = dao;
	}
	
	public void altaMovimiento(Movimiento movement) throws BancoException
	{
		if(movement.getNumeroMovimiento() < 0) throw new BancoException("Numero de movimiento no valido");
		if(movement.getNumeroCuenta() < 0) throw new BancoException("Numero de cuenta no valido");
		if(movement.getImporte() < 0) throw new BancoException("Importe no valido");
		dao.addMovement(movement);
	}
	
	public void bajaMovimiento(int numeroMovimiento) throws BancoException
	{
		dao.eraseMovement(numeroMovimiento);
	}
	
	public Movimiento obtenerMovimiento(int numeroMovimiento) throws BancoException
	{
		return dao.getMovement(numeroMovimiento);
	}
	
	public List<Movimiento> obtenerListaMovimientos(int numeroCuenta) throws BancoException
	{
		return dao.getAllMovements(numeroCuenta);
	}
	
	public List<Movimiento> obtenerListaMovimientosTarjeta(int numeroCuenta) throws BancoException
	{
		return dao.getAllCardMovements(numeroCuenta);
	}
}