package vista;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class ListPanel extends Panel
{
	protected JTable table = new JTable();
	private Box buttonBox = Box.createHorizontalBox();
	
	public ListPanel(Handler handler)
	{
		super(handler);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(new JScrollPane(table));
		add(buttonBox);
	}
	
	public void addButton(JButton button)
	{
		buttonBox.add(button);
	}
}