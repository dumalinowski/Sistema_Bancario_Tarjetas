package vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MovementListPanel extends ListPanel
{
	public MovementListPanel(Handler handler, int numeroCuenta)
	{
		super(handler);
		JButton volver = new JButton("Volver");
		
		volver.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.mostrarListaCuentas();
			}
		});
		
		addButton(volver);
		table.setModel(new MovementTableModel(handler.obtenerListaMovimientos(numeroCuenta)));
	}
}