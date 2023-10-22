package vista;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class UserDataPanel extends Panel
{
	private JTextField user = new JTextField(30);
	private JTextField password = new JTextField(30);
	private JTextField nombre = new JTextField(30);
	private JTextField apellido = new JTextField(30);
	private JTextField dni = new JTextField(30);
	private JButton modificarBoton;
	
	public UserDataPanel(Handler hndlr)
	{
		super(hndlr);
		Box nombreBox = Box.createVerticalBox();
		nombreBox.add(Box.createHorizontalStrut(15));
		nombreBox.add(UIHelper.labeledEditBox("Nombre de usuario:	", user));
		nombreBox.add(UIHelper.labeledEditBox("Contraseña:			", password));
		nombreBox.add(UIHelper.labeledEditBox("Nombre: 				", nombre));
		nombreBox.add(UIHelper.labeledEditBox("Apellido: 			", apellido));
		nombreBox.add(UIHelper.labeledEditBox("DNI: 				", dni));
		nombreBox.add(modificarBoton = new JButton());
		
		add(nombreBox);
		add(Box.createVerticalGlue()); //para que Box crezca automaticamente?
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public JTextField getUserField()
	{
		return user;
	}
	
	public JTextField getPasswordField()
	{
		return password;
	}
	
	public JTextField getNombreField()
	{
		return nombre;
	}
	
	public JTextField getApellidoField()
	{
		return apellido;
	}
	
	public JTextField getDniField()
	{
		return dni;
	}
	
	public JButton getBoton()
	{
		return modificarBoton;
	}
}