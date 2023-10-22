package vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JTextField;
import entidades.Tarjeta;

@SuppressWarnings("serial")
public class CardPanel extends Panel
{
	CardPanel(Handler handler, Tarjeta tarjeta)
	{
		super(handler);
		Box mainBox = Box.createVerticalBox();
		Box buttonBox = Box.createHorizontalBox();
		Box dataBox = Box.createVerticalBox();
		JButton operacion = new JButton("Compra");
		JButton pagarTarjeta = new JButton("Pagar tarjeta");
		JTextField numero = new JTextField(String.valueOf(tarjeta.getNumeroTarjeta()));
		JTextField credito = new JTextField(String.valueOf(tarjeta.getCredito()));
		JTextField creditoLimite = new JTextField(String.valueOf(tarjeta.getLimiteCredito()));
		
		numero.setEditable(false);
		credito.setEditable(false);
		creditoLimite.setEditable(false);
		
		operacion.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.mostrarTransaccionTarjetaActual();
			}
		});
		pagarTarjeta.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.mostrarPagoTarjeta();
			}
		});
		
		buttonBox.add(operacion);
		buttonBox.add(pagarTarjeta);
		
		dataBox.add(UIHelper.labeledEditBox("Numero", numero));
		dataBox.add(UIHelper.labeledEditBox("Credito", credito));
		dataBox.add(UIHelper.labeledEditBox("Credito limite", creditoLimite));
		mainBox.add(dataBox);
		mainBox.add(buttonBox);
		add(mainBox);
	}
}