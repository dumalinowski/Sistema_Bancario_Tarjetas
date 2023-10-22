package entidades;

public class Tarjeta
{
	private int numeroTarjeta;
	private String owner;
	private int credito;
	private int limiteCredito;
	
	public Tarjeta(int numeroTarjeta, String owner, int credito, int limiteCredito)
	{
		this.numeroTarjeta = numeroTarjeta;
		this.owner = owner;
		this.credito = credito;
		this.limiteCredito = limiteCredito;
	}
	
	public int getNumeroTarjeta()
	{
		return numeroTarjeta;
	}
	
	public String getOwner()
	{
		return owner;
	}
	
	public int getCredito()
	{
		return credito;
	}
	
	public int getLimiteCredito()
	{
		return limiteCredito;
	}
	
	public void setNumeroTarjeta(int numeroTarjeta)
	{
		this.numeroTarjeta = numeroTarjeta;
	}
	
	public void setOwner(String owner)
	{
		this.owner = owner;
	}
	
	public void setCredito(int credito)
	{
		this.credito = credito;
	}
	
	public void setLimiteCredito(int limiteCredito)
	{
		this.limiteCredito = limiteCredito;
	}
}