package vista;
import javax.swing.table.AbstractTableModel;
import entidades.Movimiento;
import java.util.List;

@SuppressWarnings("serial")
public class MovementTableModel extends AbstractTableModel
{
	private static final int /*NUMERO_MOVIMIENTO = 0, NUMERO_CUENTA = 1,*/ IMPORTE = 0, TIPO = 1, FECHA = 2;
	private static final String[] titles = { /*"Numero de movimiento", "Numero de cuenta",*/ "Importe", "Tipo", "Fecha" };
	private List<Movimiento> movimientos;
	
	public MovementTableModel(List<Movimiento> movimientos)
	{
		this.movimientos = movimientos;
	}
	
	public String getColumnName(int col)
	{
		return titles[col];
	}
	
	public int getColumnCount()
	{
		return titles.length;
	}
	
	public int getRowCount()
	{
		return movimientos.size();
	}
	
	public Object getValueAt(int row, int col)
	{
		Movimiento mov = movimientos.get(row);
		switch(col)
		{
		/*case NUMERO_MOVIMIENTO:
			return mov.getNumeroMovimiento();
		case NUMERO_CUENTA:
			return mov.getNumeroCuenta();*/
		case IMPORTE:
			return mov.getImporte();
		case TIPO:
			return mov.getTipo();
		case FECHA:
			return mov.getFecha();
		default:
			return null;
		}
	}
}
