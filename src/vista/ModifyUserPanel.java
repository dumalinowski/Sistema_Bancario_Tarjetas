package vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import exception.BancoException;
import entidades.Usuario;

@SuppressWarnings("serial")
public class ModifyUserPanel extends UserDataPanel
{
	public ModifyUserPanel(Handler hndlr)
	{
		super(hndlr);
		Usuario user = handler.getLoggedUser();
		getUserField().setText(user.getUser());
		getUserField().setEditable(false);
		getPasswordField().setText(user.getPassword());
		getNombreField().setText(user.getNombre());
		getApellidoField().setText(user.getApellido());
		
		String strDni = Integer.toString(user.getDNI());
		getDniField().setText(strDni);
		getBoton().setText("Modificar Usuario");
		
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
				
				try
				{
					if(!dniText.isEmpty()) {
						intDni = Integer.parseInt(dniText);
					}
				}
				catch(java.lang.NumberFormatException nfe)
				{
					handler.mostrarMensaje("Invalid input.");
					return;
				}
				
				if(!userText.isEmpty())
				{
					try
					{
						Usuario usuario = handler.obtenerUsuario(userText);
						if(!passwordText.isEmpty()) usuario.setPassword(passwordText);
						if(!nombreText.isEmpty()) usuario.setNombre(nombreText);
						if(!apellidoText.isEmpty()) usuario.setApellido(apellidoText);
						if(!dniText.isEmpty()) usuario.setDNI(intDni);	
						handler.modificarUsuario(usuario);
					}
					catch(BancoException de) { handler.mostrarMensaje(de.getMessage()); }
				}
				else handler.mostrarMensaje("Complete el campo de usuario");
			}
		});
	}
}