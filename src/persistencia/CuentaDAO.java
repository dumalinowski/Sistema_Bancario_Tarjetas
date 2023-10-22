package persistencia;
import java.util.List;
import exception.BancoException;
import entidades.Cuenta;

public interface CuentaDAO
{
	public void addAccount(Cuenta cuenta) throws BancoException;
	public Cuenta getAccount(int numeroCuenta) throws BancoException;
	public List<Cuenta> getAllAccounts(String owner) throws BancoException;
	public void setAccount(Cuenta cuenta) throws BancoException;
	public void eraseAccount(int numeroCuenta) throws BancoException;
	public boolean accountExists(int numeroCuenta) throws BancoException;
}