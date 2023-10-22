package exception;

public class BancoException extends Exception
{
	private static final long serialVersionUID = 1L;
	public BancoException(String message) { super(message); }
	public BancoException(String message, Throwable cause) { super(message, cause); }
	public BancoException(Throwable cause) { super(cause); }
}