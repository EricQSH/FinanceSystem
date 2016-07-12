import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.mysql.jdbc.Connection;

public class Voucher {

	JFrame frame;
	private JTable tableOrder;
	private JTable tableInventory;
	private JTable tableTrans;
	private JTable tableDeposit;
	private JTable tableSale;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Voucher window = new Voucher();
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
	public Voucher(final DBConnect dbconn, int UserNo) {
		initialize(dbconn, UserNo);
	}
	public Voucher() {
		initialize(null, -1);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final DBConnect dbconn, final int UserNo) {
		//Check if dbconn is null and UserNo is -1; if so, not connect to the database or not login yet
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane Voucher = new JTabbedPane(JTabbedPane.TOP);
		Voucher.setBounds(10, 71, 964, 339);
		frame.getContentPane().add(Voucher);
		
		JScrollPane scrollOrder = new JScrollPane();
		Voucher.addTab("Order", null, scrollOrder, null);
		
		tableOrder = new JTable();
		/*tableOrder.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, "", null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"No.", "OrderNo.", "ItemNo.", "SupplierNo.", "Types of Payment", "Quantity", "Price", "Amount", "OrderDate", "ReceiptDate", "Recorded"
			}
		) 
		*/
		DefaultTableModel model_Order = new DefaultTableModel(null, new String[] {"No.", "OrderNo.", "ItemNo.", "SupplierNo.", "Types of Payment", "Quantity", "Price", "Amount", "OrderDate", "ReceiptDate", "Recorded"})
		{
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Integer.class, Float.class, Long.class, String.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableOrder.setModel(model_Order);
		tableOrder.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableOrder.getColumnModel().getColumn(4).setPreferredWidth(100);
		scrollOrder.setViewportView(tableOrder);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		scrollOrder.setColumnHeaderView(chckbxNewCheckBox);
		
		//Get all information from Table ???
		try 
		{	
			//Perhaps need to connect to another database, use SQL
			ResultSet rs = dbconn.Query(dbconn.conn, "test_client_order"); //using test data
			while(rs.next())
			{
//				String account = rs.getString(1);
//				double balance = rs.getDouble(2);
//				Vector a =  new Vector();//Use Class Vector to contain information
//				a.add("");
//				a.add(1);
//				//if (balance>0) a = new String[]{account, Math.abs(balance) + "", ""};
//				//if (balance<0) a = new String[]{account, "", Math.abs(balance) + ""};
//				model_Order.addRow(a);
			}
			rs.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane scrollInventory = new JScrollPane();
		Voucher.addTab("Inventory", null, scrollInventory, null);
		
		tableInventory = new JTable();
		/*tableInventory.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"No.", "InventoryNo.", "Inventory Expense", "Date", "Recorded"
			}
		) 
		*/
		DefaultTableModel model_Inventory = new DefaultTableModel(null, new String[] {"No.", "InventoryNo.", "Inventory Expense", "Date", "Recorded"})
		{
			Class[] columnTypes = new Class[] {
				String.class, String.class, Float.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableInventory.setModel(model_Inventory);
		scrollInventory.setViewportView(tableInventory);
		
		//Get all information from Table ???
		try 
		{	
			//Perhaps need to connect to another database, use SQL
			ResultSet rs = dbconn.Query(dbconn.conn, "test_client_order"); //using test data
			while(rs.next())
			{
//				String account = rs.getString(1);
//				double balance = rs.getDouble(2);
//				Vector a =  new Vector();//Use Class Vector to contain information
//				a.add("");
//				a.add(1);
//				//if (balance>0) a = new String[]{account, Math.abs(balance) + "", ""};
//				//if (balance<0) a = new String[]{account, "", Math.abs(balance) + ""};
//				model_Inventory.addRow(a);
			}
			rs.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane scrollTrans = new JScrollPane();
		Voucher.addTab("Transportation", null, scrollTrans, null);
		
		tableTrans = new JTable();
		/*
		tableTrans.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"No.", "TransNo.", "Transportation Expense", "Date", "Recorded"
			}
		)
		*/
		DefaultTableModel model_Trans = new DefaultTableModel(null, new String[] {"No.", "TransNo.", "Transportation Expense", "Date", "Recorded"})
		{
			Class[] columnTypes = new Class[] {
				String.class, String.class, Float.class, String.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableTrans.setModel(model_Trans);
		scrollTrans.setViewportView(tableTrans);
		
		//Get all information from Table ???
		try 
		{	
			//Perhaps need to connect to another database, use SQL
			ResultSet rs = dbconn.Query(dbconn.conn, "test_trans_cost"); //using test data
			while(rs.next())
			{
//				String account = rs.getString(1);
//				double balance = rs.getDouble(2);
//				Vector a =  new Vector();//Use Class Vector to contain information
//				a.add("");
//				a.add(1);
//				//if (balance>0) a = new String[]{account, Math.abs(balance) + "", ""};
//				//if (balance<0) a = new String[]{account, "", Math.abs(balance) + ""};
//				model_Trans.addRow(a);
			}
			rs.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane scrollDeposit = new JScrollPane();
		Voucher.addTab("Deposit", null, scrollDeposit, null);
		
		tableDeposit = new JTable();
		/*
		tableDeposit.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"No.", "DepositNo.", "CustomerNo.", "Amount", "Date", "Recorded"
			}
		)
		*/ 
		DefaultTableModel model_Deposit = new DefaultTableModel(null, new String[] {"No.", "DepositNo.", "CustomerNo.", "Amount", "Date", "Recorded"})
		{
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Date.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableDeposit.setModel(model_Deposit);
		scrollDeposit.setViewportView(tableDeposit);
		
		//Get all information from Table ???
		try 
		{	
			//Perhaps need to connect to another database, use SQL
			ResultSet rs = dbconn.Query(dbconn.conn, "test_deposit"); //using test data
			int count = 0;
			while(rs.next())
			{
				count++;
				String strcount = count + "";
				while (strcount.length()<8)
				{
					strcount = "0" + strcount;
				}
				String dpno = rs.getString(1);
				String cno = rs.getString(2);
				String amount = rs.getString(3);
				Date date = rs.getDate(4);
				Vector a =  new Vector();//Use Class Vector to contain information
				a.add(strcount);
				a.add(dpno);
				a.add(cno);
				a.add(amount);
				a.add(date);
				a.add(true);
				//if (balance>0) a = new String[]{account, Math.abs(balance) + "", ""};
				//if (balance<0) a = new String[]{account, "", Math.abs(balance) + ""};
				model_Deposit.addRow(a);
			}
			rs.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane scrollSale = new JScrollPane();
		Voucher.addTab("Sale", null, scrollSale, null);
		
		tableSale = new JTable();
		/*
		tableSale.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"No.", "SaleNo.", "CustomerNo.", "Sales", "SaleDate", "Types of Payment", "ItemNo.", "Item Cost", "Quantity", "SupplierInventory", "Recorded"
			}
		) 
		*/
		DefaultTableModel model_Sale = new DefaultTableModel(null, new String[] {"No.", "SaleNo.", "CustomerNo.", "Sales", "SaleDate", "Types of Payment", "ItemNo.", "Item Cost", "Quantity", "SupplierInventory", "Recorded"})
		{
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, Float.class, String.class, String.class, String.class, Float.class, Integer.class, Boolean.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableSale.setModel(model_Sale);
		tableSale.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableSale.getColumnModel().getColumn(5).setPreferredWidth(110);
		tableSale.getColumnModel().getColumn(9).setPreferredWidth(100);
		scrollSale.setViewportView(tableSale);
		
		//Get all information from Table ???
		try 
		{	
			//Perhaps need to connect to another database, use SQL
			ResultSet rs = dbconn.Query(dbconn.conn, "test_client_order"); //using test data
			while(rs.next())
			{
//				String account = rs.getString(1);
//				double balance = rs.getDouble(2);
//				Vector a =  new Vector();//Use Class Vector to contain information
//				a.add("");
//				a.add(1);
//				//if (balance>0) a = new String[]{account, Math.abs(balance) + "", ""};
//				//if (balance<0) a = new String[]{account, "", Math.abs(balance) + ""};
//				model_Sale.addRow(a);
			}
			rs.close();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JLabel lblVoucher = new JLabel("Voucher");
		lblVoucher.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblVoucher.setBounds(474, 21, 97, 15);
		frame.getContentPane().add(lblVoucher);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				System.exit(0);
			}
		});
		btnExit.setBounds(823, 428, 93, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account account=new Account(dbconn, UserNo);
				account.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(702, 428, 93, 23);
		frame.getContentPane().add(btnBack);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"All", "Recorded", "Unrecorded"}));
		comboBox.setBounds(870, 50, 80, 21);
		frame.getContentPane().add(comboBox);
	}
}
