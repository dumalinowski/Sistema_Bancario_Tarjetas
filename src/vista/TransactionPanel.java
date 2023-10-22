package vista;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.Box;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.NumberFormatException;

@SuppressWarnings("serial")
public class TransactionPanel extends Panel
{
	private JTextField nroCuenta = new JTextField(30);
	private JTextField saldo = new JTextField(30);
	private JComboBox<String> tipo;
	
	public TransactionPanel(Handler hndlr)
	{
		super(hndlr);
		String[] opciones = { "Deposito", "Extraccion"};
		tipo = new JComboBox<String>(opciones);
		JButton listo = new JButton("Listo");
		Box box = Box.createVerticalBox();
		
		listo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					handler.depositarCuenta(Integer.parseInt(nroCuenta.getText()), Integer.parseInt(saldo.getText()), (String)tipo.getSelectedItem());
				}
				catch(NumberFormatException except)
				{
					handler.mostrarError(except.getMessage());
				}
			}
		});
		
		box.add(tipo);
		box.add(UIHelper.labeledEditBox("Numero de cuenta: ", nroCuenta));
		box.add(UIHelper.labeledEditBox("Cantidad: ", saldo));
		box.add(listo);
		
		add(box);
	}
	
	public TransactionPanel(Handler hndlr, int numeroCuenta)
	{
		this(hndlr); //llamar al otro constructor
		nroCuenta.setText(String.valueOf(numeroCuenta));
		nroCuenta.setEditable(false);
	}
}