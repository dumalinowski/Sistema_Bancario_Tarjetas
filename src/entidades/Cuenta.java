package entidades;

public class Cuenta
{
	private String owner; //foreign key
	private int numeroCuenta; //primary key
	private int saldo;
	
	public Cuenta(String owner, int numeroCuenta, int saldo)
	{
		this.owner = owner;
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
	}
	
	public String getOwner()
	{
		return owner;
	}
	
	public void setOwner(String owner)
	{
		this.owner = owner;
	}
	
	public int getNumeroCuenta()
	{
		return numeroCuenta;
	}
	
	public void setNumeroCuenta(int num)
	{
		numeroCuenta = num;
	}
	
	public int getSaldo()
	{
		return saldo;
	}
	
	public void setSaldo(int num)
	{
		saldo = num;
	}
}