package vista;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UIHelper
{
	static public JPanel labeledEditBox(String label, JTextField field)
	{
		JPanel aux = new JPanel();
		aux.add(new JLabel(label));
		aux.add(field);
		return aux;
	}
}
