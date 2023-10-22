package persistencia;
import exception.BancoException;
import java.util.List;
import entidades.Movimiento;

public interface MovimientoDAO
{
	public void addMovement(Movimiento movimiento) throws BancoException;
	public Movimiento getMovement(int numeroMovimiento) throws BancoException;
	public List<Movimiento> getAllMovements(int numeroCuenta) throws BancoException;
	public List<Movimiento> getAllCardMovements(int numeroCuenta) throws BancoException;
	public void eraseMovement(int numeroMovimiento) throws BancoException;
}