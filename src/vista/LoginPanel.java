package vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Box;

@SuppressWarnings("serial")
public class LoginPanel extends Panel
{
	private JTextField user = new JTextField(30);
	private JPasswordField pass = new JPasswordField(30);
	
	public LoginPanel(Handler hndlr)
	{
		super(hndlr);
		JButton login = new JButton("Login"), register = new JButton("Registrarse");
		JPanel botones = new JPanel();
		
		botones.add(login);
		botones.add(register);
		Box box = Box.createVerticalBox();
		box.add(Box.createHorizontalStrut(15));
		box.add(UIHelper.labeledEditBox("User: ", user));
		box.add(UIHelper.labeledEditBox("Password: ", pass));
		box.add(botones);
		
		login.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String userName = user.getText(), password = new String(pass.getPassword());
				if(!userName.isEmpty() && !password.isEmpty()) {
					handler.login(userName, password);
				}
				else handler.mostrarError("Complete todos los campos");
			}
		});
		
		register.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				handler.mostrarAltaUsuario();
			}
		});
		
		add(box);
		add(Box.createVerticalGlue());
	}
}