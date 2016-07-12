import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import com.mysql.jdbc.Connection;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Account {

	JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account window = new Account();
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
	public Account(final DBConnect dbconn, int UserNo) {
		initialize(dbconn, UserNo);
	}
	public Account() {
		initialize(null, -1);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final DBConnect dbconn, final int UserNo) {
		//Check if dbconn is null and UserNo is -1; if so, not connect to the database or not login yet.
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Account");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTitle.setBounds(480, 21, 73, 15);
		frame.getContentPane().add(lblTitle);
		
		JButton btnAcctVou = new JButton("Account Voucher");
		btnAcctVou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcctVoucher acctVoucher=new AcctVoucher(dbconn, UserNo);
				acctVoucher.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnAcctVou.setBounds(328, 255, 170, 23);
		frame.getContentPane().add(btnAcctVou);
		
		JButton btnOriginalVou = new JButton("Original Voucher");
		btnOriginalVou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Voucher voucher=new Voucher(dbconn, UserNo);
				voucher.frame.setLocation(0,350);
				voucher.frame.setVisible(true);
			}
		});
		btnOriginalVou.setBounds(549, 255, 170, 23);
		frame.getContentPane().add(btnOriginalVou);
		
		JButton btnRecord = new JButton("Record");
		btnRecord.setBounds(328, 289, 80, 23);
		frame.getContentPane().add(btnRecord);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*textVoucher.setText("");
				textAccount.setText("");
				textAmount.setText("");
				textSupplier.setText("");
				textCustomer.setText("");
				textItem.setText("");
				textDescription.setText("");
				textAcctDate.setText("");*/
			}
		});
		btnClear.setBounds(432, 289, 80, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				System.exit(0);
			}
		});
		btnExit.setBounds(639, 289, 80, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer transfer=new Transfer(dbconn, UserNo);
				transfer.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnBack.setBounds(536, 289, 80, 23);
		frame.getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 964, 186);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"VoucherNo.", "Account", "SupplierNo.", "CustomerNo.", "ProductNo.", "Amount", "D/C", "Description", "Date", "Source"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Float.class, Integer.class, String.class, Date.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		scrollPane.setViewportView(table);
		
		JLabel lblUserNo = new JLabel("");
		lblUserNo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblUserNo.setBounds(10, 22, 125, 15);
		if(UserNo==-1)
			lblUserNo.setText("UserNo.: not log in");
		else lblUserNo.setText("UserNo.: "+UserNo);
		frame.getContentPane().add(lblUserNo);
	}
}
