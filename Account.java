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
import java.text.DecimalFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 964, 186);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"", null, null, null, null, null, null, null, null, null},
				{"", null, null, null, null, null, null, null, null, null},
				{"", null, null, null, null, null, null, null, null, null},
				{"", null, null, null, null, null, null, null, null, null},
				{"", null, null, null, null, null, null, null, null, null},
				{"", null, null, null, null, null, null, null, null, null},
				{"", null, null, null, null, null, null, null, null, null},
				{"", null, null, null, null, null, null, null, null, null},
				{"", null, null, null, null, null, null, null, null, null},
				{"", null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"VoucherNo.", "Account", "SupplierNo.", "CustomerNo.", "ProductNo.", "Amount", "D/C", "Description", "Date", "Source"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, /*Float.class, Integer.class,*/String.class, String.class, Date.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		//tableSale.setModel(model);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		scrollPane.setViewportView(table);
		
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
				Voucher voucher=new Voucher(dbconn, UserNo, table);
				voucher.frame.setLocation(0,350);
				voucher.frame.setVisible(true);
			}
		});
		btnOriginalVou.setBounds(549, 255, 170, 23);
		frame.getContentPane().add(btnOriginalVou);
		
		JButton btnRecord = new JButton("Record");
		btnRecord.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int rowcount = table.getRowCount();
				int i=0;
				double sumd = 0, sumc = 0;
				String voucherNo = (String) table.getValueAt(i, 0);
				do
				{
					voucherNo = (String) table.getValueAt(i, 0);
					if (voucherNo.equals("")) break;
					DecimalFormat decimalFormat=new DecimalFormat(".00");
					double amount = 0;
					String samount = (String) table.getValueAt(i, 5);
					boolean decimal = true;
					for (int i1=0;i1<samount.length();i1++)
					{
						if ((samount.charAt(i1)!='.')&&(decimal))
						{
							amount *= 10;
							amount += samount.charAt(i1) - '0';
						}
						if (samount.charAt(i1)=='.') 
						{
							decimal = !decimal;
						}
						int t = 100;
						if ((samount.charAt(i1)!='.')&&(!decimal))
						{
							amount += (double)(samount.charAt(i1) - '0')/t;
							t *= 10;
						}
					}
					int dc = (table.getValueAt(i, 6).equals("Debit"))?0:1;
					sumc += dc==0?0:amount;
					sumd += dc==0?amount:0;
					
					i++;
				} while ((i<=rowcount)&&(!voucherNo.equals("")));
				if (sumd - sumc!=0) 
					System.out.print("Fuck U!");//FUUUUUUUUUUUUUUUUUUUUUCK UUUUUUUUU NOT BALANCE!
				else
				{
				
					String voucherno = "0000000000";
					try 
					{	
						String sql = "SELECT voucherNo from voucher WHERE voucherNo in (Select max(voucherNo) from voucher)";
						ResultSet rs = dbconn.Run(dbconn.conn, sql);
						//if (rs.next()) voucherno = "0";
						while(rs.next())
						{
							voucherno = rs.getString(1);
							//System.out.print(voucherno + " ");
						}
						rs.close();
					} 
					catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int no = 0;
					int ii;
					for (ii=0;ii<voucherno.length();ii++)
					{
						no *= 10;
						no += voucherno.charAt(ii) - '0';
					}
					//System.out.print(no);
					
					rowcount = table.getRowCount();
					i=0;
					sumd = sumc = 0;
					voucherNo = (String) table.getValueAt(i, 0);
					do
					{
						if (sumd - sumc==0) no++;
						voucherno = no+"";
						while (voucherno.length()<10)
						{
							voucherno = "0" + voucherno;
						}
						voucherNo = (String) table.getValueAt(i, 0);
						if (voucherNo.equals("")) break;
						String account = (String) table.getValueAt(i, 1);
						String supplierno = (String) table.getValueAt(i, 2);
						String customerno = (String) table.getValueAt(i, 3);
						String productno = (String) table.getValueAt(i, 4);
						DecimalFormat decimalFormat=new DecimalFormat(".00");
						double amount = 0;
						String samount = (String) table.getValueAt(i, 5);
						boolean decimal = true;
						for (int i1=0;i1<samount.length();i1++)
						{
							if ((samount.charAt(i1)!='.')&&(decimal))
							{
								amount *= 10;
								amount += samount.charAt(i1) - '0';
							}
							if (samount.charAt(i1)=='.') 
							{
								decimal = !decimal;
							}
							int t = 100;
							if ((samount.charAt(i1)!='.')&&(!decimal))
							{
								amount += (double)(samount.charAt(i1) - '0')/t;
								t *= 10;
							}
						}
						int dc = (table.getValueAt(i, 6).equals("Debit"))?0:1;
						sumc += dc==0?0:amount;
						sumd += dc==0?amount:0;
						String description = (String) table.getValueAt(i, 7);
						Date date = (Date) table.getValueAt(i, 8);
						String source = (String) table.getValueAt(i, 9);			
						
						System.out.print("INSERT INTO account values("+voucherno+","+account+","+amount+","+dc+","+date+");" + "\n");
						if (sumd - sumc==0) 
						{
							System.out.print("INSERT INTO voucher values("+voucherno+","+supplierno+","+customerno+","+productno +","+description+","+date+","+source+","+UserNo+");" + "\n");
							sumd = sumc = 0;
						}
						i++;
					} while ((i<=rowcount)&&(!voucherNo.equals("")));
					System.out.print("Fuck U MOTHERFXCKER!");//FUUUUUUUUUUUUUUUUUUUUUCK UUUUUUUUU RECORD SUCCESS!
					//"INSERT INTO account values("+voucherNo+","+account+","+amount+","+debitCredit+","+date+");"
					//"INSERT INTO voucher values("+voucherNo+","+supplierNo+","+customerNo+","+productNo +","+description+","+date+","+source+","+userNo+");"
				}
			}
		});
		btnRecord.setBounds(328, 289, 80, 23);
		frame.getContentPane().add(btnRecord);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*	
			    textVoucher.setText("");
				textAccount.setText("");
				textAmount.setText("");
				textSupplier.setText("");
				textCustomer.setText("");
				textItem.setText("");
				textDescription.setText("");
				textAcctDate.setText("");
			*/
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
	
		JLabel lblUserNo = new JLabel("");
		lblUserNo.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblUserNo.setBounds(10, 22, 125, 15);
		if(UserNo==-1)
			lblUserNo.setText("UserNo.: not log in");
		else lblUserNo.setText("UserNo.: "+UserNo);
		frame.getContentPane().add(lblUserNo);
	}
}
