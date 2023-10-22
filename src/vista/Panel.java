package vista;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel
{
	protected Handler handler;
	
	public Panel(Handler handler)
	{
		this.handler = handler;
	}
}