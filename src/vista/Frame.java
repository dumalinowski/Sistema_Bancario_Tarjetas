package vista;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JPanel;

public class Frame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Handler handler;
	
	public Frame(Handler handler, String titulo)
	{	
		this.handler = handler;
		setTitle(titulo);
		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setPanel(JPanel panel)
	{
		Container pane = getContentPane();
		pane.removeAll();
		if(panel != null) { //si paso null, el frame quedara en blanco
			pane.add(panel);
		}
		pane.validate();
		pane.repaint();
	}
	
	public void showMenuBar(boolean show)
	{
		setJMenuBar(show ? new MenuBar(handler) : null);
		revalidate();
	}
}