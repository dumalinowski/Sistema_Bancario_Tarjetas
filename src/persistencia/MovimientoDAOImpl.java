package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import exception.BancoException;
import entidades.Movimiento;;

public class MovimientoDAOImpl implements MovimientoDAO
{
	public void addMovement(Movimiento movimiento) throws BancoException
	{
		Connection c = DBManager.getInstance().connect();

		try
		{
			PreparedStatement preparedS = c.prepareStatement("INSERT INTO movimientos (numeroCuenta, importe, tipo, date, active) VALUES(?, ?, ?, current_timestamp, TRUE)");
			preparedS.setInt(1, movimiento.getNumeroCuenta());
			preparedS.setInt(2, movimiento.getImporte());
			preparedS.setString(3, movimiento.getTipo());
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
			throw new BancoException("Error al agregar movimiento");
		}
		finally
		{
			try
			{
				c.close();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public Movimiento getMovement(int numeroMovimiento) throws BancoException
	{
		String sql = "SELECT * FROM movimientos WHERE numeroMovimiento = '" + numeroMovimiento + "' AND active = TRUE";
		Connection c = DBManager.getInstance().connect();
		Movimiento result = null;
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{
				result = new Movimiento(rs.getInt("numeroMovimiento"),
										rs.getInt("numeroCuenta"),
										rs.getInt("importe"),
										rs.getString("tipo"),
										rs.getTimestamp("date"));
			}
			else throw new BancoException("Movimiento inexistente");
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
			throw new BancoException("Error al obtener movimiento");
		}
		finally
		{
			try
			{
				c.close();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}

	public List<Movimiento> getAllMovements(int numeroCuenta) throws BancoException
	{
		String sql = "SELECT * FROM movimientos WHERE numeroCuenta = '" + numeroCuenta + "' AND active = TRUE AND tipo IN ('Deposito', 'Extraccion')";
		Connection c = DBManager.getInstance().connect();
		List<Movimiento> result = new ArrayList<Movimiento>();
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{
				do
				{
					Movimiento newMovement = new Movimiento(rs.getInt("numeroMovimiento"),
															rs.getInt("numeroCuenta"),
															rs.getInt("importe"),
															rs.getString("tipo"),
															rs.getTimestamp("date"));
					result.add(newMovement);
				} while (rs.next());
			}
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new BancoException("Error al obtener movimientos");
		}
		finally
		{
			try
			{
				c.close();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}
	
	public List<Movimiento> getAllCardMovements(int numeroCuenta) throws BancoException
	{
		String sql = "SELECT * FROM movimientos WHERE numeroCuenta = '" + numeroCuenta + "' AND active = TRUE AND tipo = 'Tarjeta'";
		Connection c = DBManager.getInstance().connect();
		List<Movimiento> result = new ArrayList<Movimiento>();
		
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{
				do
				{
					Movimiento newMovement = new Movimiento(rs.getInt("numeroMovimiento"),
															rs.getInt("numeroCuenta"),
															rs.getInt("importe"),
															rs.getString("tipo"),
															rs.getTimestamp("date"));
					result.add(newMovement);
				} while (rs.next());
			}
			else throw new BancoException("No hay movimientos en la tarjeta de este usuario");
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new BancoException("Error al obtener movimientos");
		}
		finally
		{
			try
			{
				c.close();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return result;
	}

	public void eraseMovement(int numeroMovimiento) throws BancoException
	{
		Connection c = DBManager.getInstance().connect();
		try
		{
			Statement s = c.createStatement();
			//s.executeUpdate("DELETE FROM movimientos WHERE numeroMovimiento = '" + numeroMovimiento + "' AND active = TRUE");
			s.executeUpdate("UPDATE movimientos SET active = FALSE WHERE numeroMovimiento = '" + numeroMovimiento + "'");
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
			throw new BancoException("Error al borrar movimiento");
		}
		finally
		{
			try
			{
				c.close();
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
