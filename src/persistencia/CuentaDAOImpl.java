package persistencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import exception.BancoException;
import entidades.Cuenta;

public class CuentaDAOImpl implements CuentaDAO
{
	public void addAccount(Cuenta cuenta) throws BancoException
	{
		Connection c = DBManager.getInstance().connect();

		try
		{
			PreparedStatement preparedS = c.prepareStatement("INSERT INTO cuentas (owner, saldo, active) VALUES(?, ?, TRUE)");
			preparedS.setString(1, cuenta.getOwner());
			preparedS.setInt(2, cuenta.getSaldo());
			preparedS.executeUpdate();
			c.commit();
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
			}
			catch (SQLException e1) {}
			throw new BancoException("Error al agregar cuenta");
		}
		finally
		{
			try
			{
				c.close();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
	}

	public Cuenta getAccount(int numeroCuenta) throws BancoException
	{
		String sql = "SELECT * FROM cuentas WHERE numeroCuenta = '" + numeroCuenta + "' AND active = TRUE";
		Connection c = DBManager.getInstance().connect();
		Cuenta result = null;
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{
				result = new Cuenta(rs.getString("owner"),
									rs.getInt("numeroCuenta"),
									rs.getInt("saldo"));
			}
			else throw new BancoException("Cuenta inexistente");
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
				e.printStackTrace();
			}
			catch (SQLException e1) {}
			throw new BancoException("Error al obtener cuenta");
		}
		finally
		{
			try
			{
				c.close();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
		return result;
	}

	public List<Cuenta> getAllAccounts(String owner) throws BancoException
	{
		String sql = "SELECT * FROM cuentas WHERE owner = '" + owner + "' AND active = TRUE";
		Connection c = DBManager.getInstance().connect();
		List<Cuenta> result = new ArrayList<Cuenta>();
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{
				do
				{
					Cuenta newAccount = new Cuenta(rs.getString("owner"),
												rs.getInt("numeroCuenta"),
												rs.getInt("saldo"));
					result.add(newAccount);
				} while (rs.next());
			}
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
			}
			catch (SQLException e1) {}
			throw new BancoException("Error al obtener cuentas");
		}
		finally
		{
			try
			{
				c.close();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}//poner print stack trace en todos estos
		}
		return result;
	}

	public void setAccount(Cuenta cuenta) throws BancoException
	{
		Connection c = DBManager.getInstance().connect();
		try
		{
			PreparedStatement s = c.prepareStatement("UPDATE cuentas SET saldo = ? WHERE numeroCuenta = ? AND active = TRUE");
			s.setInt(1, cuenta.getSaldo());
			s.setInt(2, cuenta.getNumeroCuenta());
			s.executeUpdate();
			c.commit();
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
				e.printStackTrace();
			}
			catch (SQLException e1) {}
		}
		finally
		{
			try
			{
				c.close();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
	}

	public void eraseAccount(int numeroCuenta) throws BancoException
	{
		Connection c = DBManager.getInstance().connect();
		try
		{
			Statement s = c.createStatement();
			//s.executeUpdate("DELETE FROM cuentas WHERE numeroCuenta = '" + numeroCuenta + "'");
			s.executeUpdate("UPDATE cuentas SET active = FALSE WHERE numeroCuenta = '" + numeroCuenta + "'");
			c.commit();
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
				e.printStackTrace();
			}
			catch (SQLException e1) {}
			throw new BancoException("Error al borrar cuenta");
		}
		finally
		{
			try
			{
				c.close();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
	}

	public boolean accountExists(int numeroCuenta) throws BancoException
	{
		Connection c = DBManager.getInstance().connect();
		boolean result = false;
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT COUNT(*) AS cnt FROM cuentas WHERE numeroCuenta = '" + numeroCuenta + "' AND active = TRUE");
			result = rs.next() && rs.getInt("cnt") != 0;
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
				e.printStackTrace();
			}
			catch (SQLException e1) {}
			throw new BancoException("Error en base de datos");
		}
		finally
		{
			try
			{
				c.close();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
		}
		return result;
	}
}