package persistencia;
import exception.BancoException;
import entidades.Tarjeta;

public interface TarjetaDAO
{
	public void addCard(int numeroTarjeta, String owner, int credito, int limiteCredito, int aPagar) throws BancoException;
	public Tarjeta getCard(int numeroTarjeta) throws BancoException;
	public Tarjeta getCard(String owner) throws BancoException;
	public void setCard(Tarjeta tarjeta) throws BancoException;
	public void eraseCard(int numeroTarjeta) throws BancoException;
}