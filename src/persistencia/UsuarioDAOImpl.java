package persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import exception.BancoException;
import entidades.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO
{
	public UsuarioDAOImpl() {}

	public void addUser(Usuario user) throws BancoException
	{
		Connection c = DBManager.getInstance().connect();

		try
		{
			PreparedStatement preparedS = c.prepareStatement("INSERT INTO usuarios (user, pass, nombre, apellido, saldo, dni, active) VALUES(?, ?, ?, ?, ?, ?, TRUE)");
			preparedS.setString(1, user.getUser());
			preparedS.setString(2, user.getPassword());
			preparedS.setString(3, user.getNombre());
			preparedS.setString(4, user.getApellido());
			preparedS.setInt(5, user.getSaldo());
			preparedS.setInt(6, user.getDNI());
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
			catch (SQLException e1)
			{
				e1.printStackTrace();
			}
			throw new BancoException("Error al agregar usuario");
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

	public Usuario getUser(String userName) throws BancoException
	{
		String sql = "SELECT * FROM usuarios WHERE user = '" + userName + "' AND active = TRUE";
		Connection c = DBManager.getInstance().connect();
		Usuario result = null;
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{
				result = new Usuario(rs.getString("user"),
									 rs.getString("pass"),
									 rs.getString("nombre"),
									 rs.getString("apellido"),
									 rs.getInt("saldo"),
									 rs.getInt("dni"));
			}
			else throw new BancoException("Usuario inexistente");
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
				e.printStackTrace();
			}
			catch (SQLException e1) {}
			throw new BancoException("Error al obtener usuario");
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

	public List<Usuario> getAllUsers() throws BancoException
	{
		String sql = "SELECT * FROM usuarios WHERE active = TRUE";
		Connection c = DBManager.getInstance().connect();
		List<Usuario> result = new ArrayList<Usuario>();
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next())
			{
				do
				{
					Usuario newUser = new Usuario(rs.getString("user"),
												  rs.getString("pass"),
												  rs.getString("nombre"),
												  rs.getString("apellido"),
												  rs.getInt("saldo"),
												  rs.getInt("dni"));
					result.add(newUser);
				} while (rs.next());
			}
			//else throw new DAOException("No hay usuarios");
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
			}
			catch (SQLException e1) {}
			throw new BancoException("Error al obtener usuarios");
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

	public void setUser(Usuario user) throws BancoException // setea el usuario user.nombre
	{
		Connection c = DBManager.getInstance().connect();
		try
		{
			PreparedStatement s = c.prepareStatement("UPDATE usuarios SET pass = ?, nombre = ?, apellido = ?, saldo = ?, dni = ? WHERE user = ? AND active = TRUE");
			s.setString(1, user.getPassword());
			s.setString(2, user.getNombre());
			s.setString(3, user.getApellido());
			s.setInt(4, user.getSaldo());
			s.setInt(5, user.getDNI());
			s.setString(6, user.getUser());
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

	public void eraseUser(String userName) throws BancoException
	{
		Connection c = DBManager.getInstance().connect();
		try
		{
			Statement s = c.createStatement();
			//s.executeUpdate("DELETE FROM usuarios WHERE user = '" + userName + "'");
			s.executeUpdate("UPDATE usuarios SET active = FALSE WHERE user = '" + userName + "'"); //baja logica
			c.commit();
		}
		catch (SQLException e)
		{
			try
			{
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {}
			throw new BancoException("Error al borrar usuario");
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

	public boolean userExists(String userName) throws BancoException
	{
		Connection c = DBManager.getInstance().connect();
		boolean result = false;
		try
		{
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT COUNT(*) AS cnt FROM usuarios WHERE user = '" + userName + "' AND active = TRUE");
			if (rs.next() && rs.getInt("cnt") != 0) {
				result = true;
			}
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