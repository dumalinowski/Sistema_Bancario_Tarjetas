package vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class NewUserPanel extends UserDataPanel
{	
	NewUserPanel(Handler hndlr) //ModifyUserPanel y NewUserPanel son casi iguales, generalizar con herencia y metodos abstractos - DONE
	{
		super(hndlr);
		JButton volver = new JButton("Volver");
		getBoton().setText("Registrar Usuario");
		add(volver);
		
		volver.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.mostrarLogin();
			}
		});
		
		getBoton().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String userText = getUserField().getText();
				String passwordText = getPasswordField().getText();
				String nombreText = getNombreField().getText();
				String apellidoText = getApellidoField().getText();
				String dniText = getDniField().getText();
				int intDni = 0;
				if(userText.isEmpty() || nombreText.isEmpty() || apellidoText.isEmpty() || dniText.isEmpty()) {
					handler.mostrarMensaje("Complete todos los campos");
					return;
				}

				try {
					intDni = Integer.parseInt(dniText);
				}
				catch(java.lang.NumberFormatException nfe) {
					handler.mostrarMensaje("Invalid input.");
					return;
				}
				
				handler.altaUsuario(userText, passwordText, nombreText, apellidoText, intDni);
			}
		});
	}
}