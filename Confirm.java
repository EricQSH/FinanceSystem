import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;

public class Confirm {

	JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirm window = new Confirm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Confirm(final DBConnect dbconn, int UserNo) {
		initialize(dbconn, UserNo);
	}
	public Confirm() {
		initialize(null, -1);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final DBConnect dbconn, final int UserNo) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 47, 375, 150);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		/*
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"OrderNo.", "State"
			}
		) {
		*/
		final DefaultTableModel model = new DefaultTableModel(null, new String[] {"OrderNo.", "State"})
		{
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JLabel lblPaymentConfirmation = new JLabel("Payment Confirmation");
		lblPaymentConfirmation.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPaymentConfirmation.setBounds(138, 17, 171, 15);
		frame.getContentPane().add(lblPaymentConfirmation);
		
		//Get all information from Table ???
		try 
		{	
			//Perhaps need to connect to another database, use SQL
			ResultSet rs = dbconn.Query(dbconn.conn, "test_check_payment"); //using test data
			while(rs.next())
			{
				String orno = rs.getString(1);
				String state = rs.getString(2);
				Vector a =  new Vector();//Use Class Vector to contain information
				a.add(orno);
				a.add(state);
				//if (balance>0) a = new String[]{account, Math.abs(balance) + "", ""};
				//if (balance<0) a = new String[]{account, "", Math.abs(balance) + ""};
				if (state.equals("Î´¸¶¿î")) model.addRow(a);
			}
			rs.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedColumn();
				String orNo = (String) table.getValueAt(row, 0);
				String sql = "UPDATE test_check_payment SET state=\"Î´·¢»õ\" WHERE orno=" + orNo;
				dbconn.Update(dbconn.conn, sql);
				model.removeRow(row);
			}
		});
		btnConfirm.setBounds(158, 216, 93, 23);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Transfer transfer = new Transfer(dbconn, UserNo);
				frame.setVisible(false);
				transfer.frame.setVisible(true);
			}
		});
		btnBack.setBounds(307, 216, 93, 23);
		frame.getContentPane().add(btnBack);
	}
}
