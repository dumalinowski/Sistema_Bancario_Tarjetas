package vista;
import persistencia.TableManager;

public class Main
{
	public static void main(String[] args)
	{	
		TableManager.createUserTable();
		TableManager.createCuentasTable();
		TableManager.createMovimientosTable();
		TableManager.createTarjetasTable();
		new Handler();
	}
}