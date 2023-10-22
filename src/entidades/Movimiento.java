package entidades;
import java.sql.Timestamp;

public class Movimiento
{
	private int numeroMovimiento; //primary key
	private int numeroCuenta; //foreign key
	private int importe;
	private String tipo;
	private Timestamp fecha;
	
	public Movimiento(int numeroMovimiento, int numeroCuenta, int importe, String tipo, Timestamp fecha)
	{
		this.numeroMovimiento = numeroMovimiento;
		this.numeroCuenta = numeroCuenta;
		this.importe = importe;
		this.tipo = tipo;
		this.fecha = fecha;
	}
	
	public Movimiento(int numeroCuenta, int importe, String tipo)
	{
		this.numeroCuenta = numeroCuenta;
		this.importe = importe;
		this.tipo = tipo;
	}
	
	public int getNumeroMovimiento()
	{
		return numeroMovimiento;
	}
	
	public int getNumeroCuenta()
	{
		return numeroCuenta;
	}
	
	public int getImporte()
	{
		return importe;
	}
	
	public String getTipo()
	{
		return tipo;
	}
	
	public Timestamp getFecha()
	{
		return fecha;
	}
	
	public void setNumeroMovimiento(int num)
	{
		numeroMovimiento = num;
	}
	
	public void setNumeroCuenta(int num)
	{
		numeroCuenta = num;
	}
	
	public void setImporte(int num)
	{
		importe = num;
	}
	
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}
}