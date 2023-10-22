package vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CardTransactionPanel extends Panel
{
	JTextField monto;
	JButton comprar;
	
	CardTransactionPanel(Handler handler, int numeroTarjeta)
	{
		super(handler);
		Box box = Box.createVerticalBox();
		monto = new JTextField(30);
		comprar = new JButton("Realizar compra");
		
		comprar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				/*try
				{
					Tarjeta tar = handler.obtenerTarjeta(numeroTarjeta);
					String strCantidad = monto.getText();
					
					if(!strCantidad.isEmpty())
					{
						int cantidad = Integer.parseInt(strCantidad);
						if(cantidad <= tar.getLimiteCredito() - tar.getCredito())
						{
							tar.setCredito(tar.getCredito() + cantidad);
							handler.modificarTarjeta(tar);
							handler.mostrarMensaje("Compra exitosa");
						}
						else handler.mostrarMensaje("Saldo insuficiente");
					}
				}
				catch(BancoException except) {
					handler.mostrarError(except.getMessage());
				}*/
				String strCantidad = monto.getText();
				
				if(!strCantidad.isEmpty())
				{
					try {
						handler.compraConTarjeta(Integer.parseInt(strCantidad));
					}
					catch(NumberFormatException except) {
						handler.mostrarError("Cantidad no valida");
					}
				}
				else handler.mostrarError("Llene todos los campos");
			}
		});
		
		box.add(UIHelper.labeledEditBox("Monto de compra", monto));
		box.add(comprar);
		add(box);
	}
}