package vista;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AccountListPanel extends ListPanel
{
	AccountListPanel(Handler hndlr)
	{
		super(hndlr);
		JButton seleccionarCuenta = new JButton("Operacion");
		JButton altaCuenta = new JButton("Alta cuenta");
		JButton historial = new JButton("Historial de movimientos");
		
		seleccionarCuenta.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int selRow = table.getSelectedRow();
				if(selRow != -1) {
					handler.mostrarTransaccion((int)((AbstractTableModel)table.getModel()).getValueAt(selRow, 0));
				}
				else handler.mostrarMensaje("Seleccione una fila");
			}
		});
		altaCuenta.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.altaCuenta();
				table.setModel(new AccountTableModel(handler.obtenerListaCuentas(handler.getLoggedUser().getUser())));
			}
		});
		historial.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int selRow = table.getSelectedRow();
				if(selRow != -1) {
					handler.mostrarMovimientos((int)((AbstractTableModel)table.getModel()).getValueAt(selRow, 0));
				}
				else handler.mostrarMensaje("Seleccione una fila");
			}
		});

		addButton(seleccionarCuenta);
		addButton(altaCuenta);
		addButton(historial);
		table.setModel(new AccountTableModel(handler.obtenerListaCuentas(handler.getLoggedUser().getUser())));
	}
}