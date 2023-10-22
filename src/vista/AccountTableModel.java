package vista;
import javax.swing.table.AbstractTableModel;
import entidades.Cuenta;
import java.util.List;

@SuppressWarnings("serial")
public class AccountTableModel extends AbstractTableModel
{
	private static final int NUMERO = 0, SALDO = 1;
	private static final String[] titles = { "Numero de cuenta", "Saldo" };
	private List<Cuenta> cuentas;

	AccountTableModel(List<Cuenta> cuentas)
	{
		this.cuentas = cuentas;
	}

	public String getColumnName(int col)
	{
		return titles[col];
	}

	public int getColumnCount()
	{
		return titles.length;
	}

	public Object getValueAt(int row, int col)
	{
		Cuenta cuenta = cuentas.get(row);
		switch (col)
		{
		case NUMERO:
			return cuenta.getNumeroCuenta();
		case SALDO:
			return cuenta.getSaldo();
		default:
			return null;
		}
	}

	public int getRowCount()
	{
		return cuentas.size();
	}

	public void removeRow(int row)
	{
		cuentas.remove(row);
		fireTableDataChanged();
	}

	public void addRow(Cuenta cuenta)
	{
		cuentas.add(cuenta);
		fireTableDataChanged();
	}
}
