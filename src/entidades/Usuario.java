package entidades;

public class Usuario
{
	private String user; //primary key
	private String password;
	private String nombre;
	private String apellido;
	private int saldo;
	private int dni;
	
	public Usuario(String user, String password, String nombre, String apellido, int saldo, int dni)
	{
		this.apellido = apellido;
		this.user =user;
		this.nombre = nombre;
		this.password = password;
		this.saldo = saldo;
		this.dni = dni;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public int getSaldo()
	{
		return saldo;
	}
	
	public void setSaldo(int saldo)
	{
		this.saldo = saldo;
	}
	
	public int getDNI()
	{
		return dni;
	}
	
	public void setDNI(int dni)
	{
		this.dni = dni;
	}
	
	public String getApellido()
	{
		return apellido;
	}
	
	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}
	
	public String getUser()
	{
		return user;
	}
	
	public void setUser(String user)
	{
		this.user = user;
	}
}