package persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import exception.BancoException;
import entidades.Tarjeta;

public class TarjetaDAOImpl implements TarjetaDAO
{
	public void addCard(int numeroTarjeta, String owner, int credito, int limiteCredito, int aPagar) throws BancoException
	{
		Connection c = DBManager.getInstance().connect();

		try
		{
			Tarjeta tarjeta = new Tarjeta(numeroTarjeta, owner, credito, limiteCredito); //setear las fechas luego
			
			PreparedStatement preparedS = c.prepareStatement("INSERT INTO tarjetas (owner, credito, limiteCredito, active) VALUES(?, ?, ?, TRUE)");
			preparedS.setString(1, tarjeta.getOwner());
			preparedS.setInt(2, tarjeta.getCredito());
			preparedS.setInt(3, tarjeta.getLimiteCredito());
			preparedS.executeUpdate();
			c.commit();
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
				e.printStackTrace();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new BancoException("Error al agregar tarjeta");
		}
		finally
		{
			try {
				c.close();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public Tarjeta getCard(int numeroTarjeta) throws BancoException
	{
		String sql = "SELECT * FROM tarjetas WHERE numeroTarjeta = '" + numeroTarjeta + "' AND active = TRUE";
		Connection c = DBManager.getInstance().connect();
		Tarjeta result = null;
		
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{
				result = new Tarjeta(rs.getInt("numeroTarjeta"),
									 rs.getString("owner"),
									 rs.getInt("credito"),
									 rs.getInt("limiteCredito"));
			}
			else throw new BancoException("Tarjeta inexistente");
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
				e.printStackTrace();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new BancoException("Error al obtener tarjeta");
		}
		finally
		{
			try {
				c.close();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public Tarjeta getCard(String owner) throws BancoException
	{
		String sql = "SELECT * FROM tarjetas WHERE owner = '" + owner + "' AND active = TRUE";
		Connection c = DBManager.getInstance().connect();
		Tarjeta result = null;
		
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{
				result = new Tarjeta(rs.getInt("numeroTarjeta"),
									 rs.getString("owner"),
									 rs.getInt("credito"),
									 rs.getInt("limiteCredito"));
			}
			else throw new BancoException("Usted no posee una tarjeta de credito");
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
				e.printStackTrace();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new BancoException("Error al obtener tarjeta");
		}
		finally
		{
			try {
				c.close();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public void setCard(Tarjeta tarjeta)
	{
		String query = "UPDATE tarjetas SET owner = ?, credito = ?, limiteCredito = ? WHERE numeroTarjeta = '" + tarjeta.getNumeroTarjeta() + "'";
		Connection c = DBManager.getInstance().connect();
		
		try
		{
			PreparedStatement s = c.prepareStatement(query);
			s.setString(1, tarjeta.getOwner());
			s.setInt(2, tarjeta.getCredito());
			s.setInt(3, tarjeta.getLimiteCredito());
			s.executeUpdate();
			c.commit();
		}
		catch (SQLException e)
		{
			try {
				c.rollback();
				e.printStackTrace();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally
		{
			try {
				c.close();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void eraseCard(int numeroTarjeta) throws BancoException
	{
		Connection c = DBManager.getInstance().connect();
		try
		{
			Statement s = c.createStatement();
			s.executeUpdate("UPDATE tarjetas SET active = FALSE WHERE numeroTarjeta = '" + numeroTarjeta + "'");
			c.commit();
		}
		catch (SQLException e)
		{
			try {
				c.rollback();
				e.printStackTrace();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new BancoException("Error al borrar tarjeta");
		}
		finally
		{
			try {
				c.close();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}