package vista;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Box;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import exception.BancoException;
//import entidades.Tarjeta;

@SuppressWarnings("serial")
public class PagarTarjetaPanel extends Panel
{
	JTextField numCuenta;
	JButton pagar;
	
	public PagarTarjetaPanel(Handler handler)
	{
		super(handler);
		numCuenta = new JTextField(30);
		pagar = new JButton("Pagar tarjeta");
		Box box = Box.createVerticalBox();
		
		pagar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.pagarTarjeta(Integer.parseInt(numCuenta.getText()));
			}
		});
		
		box.add(UIHelper.labeledEditBox("Numero de cuenta ", numCuenta));
		box.add(pagar);
		add(box);
	}
}