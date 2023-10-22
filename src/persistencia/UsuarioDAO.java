package persistencia;
import java.util.List;
import exception.BancoException;
import entidades.Usuario;

public interface UsuarioDAO
{
	public void addUser(Usuario user) throws BancoException;
	public Usuario getUser(String userName) throws BancoException;
	public List<Usuario> getAllUsers() throws BancoException;
	public void setUser(Usuario user) throws BancoException;
	public void eraseUser(String user) throws BancoException;
	public boolean userExists(String userName) throws BancoException;
}