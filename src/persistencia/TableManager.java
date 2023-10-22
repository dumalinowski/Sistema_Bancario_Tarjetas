package persistencia;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager
{	
	public static void createUserTable()
	{
		createTable("CREATE TABLE usuarios (user VARCHAR(30) PRIMARY KEY, pass VARCHAR(10), nombre VARCHAR(50), apellido VARCHAR(50), saldo INTEGER, dni INTEGER, active BOOLEAN)");
	}
	
	public static void createCuentasTable()
	{
		createTable("CREATE TABLE cuentas (numeroCuenta INTEGER IDENTITY, owner VARCHAR(30), saldo INTEGER, active BOOLEAN, FOREIGN KEY (owner) REFERENCES usuarios(user))");
	}
	
	public static void createMovimientosTable()
	{
		createTable("CREATE TABLE movimientos (numeroMovimiento INTEGER IDENTITY, numeroCuenta INTEGER, importe INTEGER, tipo VARCHAR(15), date TIMESTAMP, active BOOLEAN, FOREIGN KEY (numeroCuenta) REFERENCES cuentas(numeroCuenta))");
	}
	
	public static void createTarjetasTable()
	{
		createTable("CREATE TABLE tarjetas (numeroTarjeta INTEGER IDENTITY, owner VARCHAR(30), credito INTEGER, limiteCredito INTEGER, fechaCierre VARCHAR(20), fechaVencimiento VARCHAR(20), active BOOLEAN, FOREIGN KEY (owner) REFERENCES usuarios(user))");
	}
	
	private static void createTable(String query)
	{
		Connection c = DBManager.getInstance().connect();
		if(!DBManager.getInstance().tableExists(query.split(" ")[2]))
		{
			try
			{
				Statement s = c.createStatement();
				s.executeQuery(query);
			}
			catch (SQLException e)
			{
				try
				{
					c.rollback(); //Si hubo error, revertir cambios, puede tirar SQLException
					e.printStackTrace();
				} catch (SQLException e1) { e1.printStackTrace(); }
			}
			finally
			{
				try { c.close(); }
				catch (SQLException e) { e.printStackTrace(); }
			}
		}
	}
		
	public static void dropTable(String tableName)
	{
		Connection c = DBManager.getInstance().connect();
		String sql = "DROP TABLE " + tableName;
		
		try
		{
			Statement s = c.createStatement();
			s.execute(sql);
			c.commit();
		}
		catch (SQLException e)
		{
			try { c.rollback(); }
			catch (SQLException e1) { e1.printStackTrace(); }
		}
		finally 
		{
			try { c.close(); }
			catch (SQLException e) { e.printStackTrace(); }
		}
	}
}