import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//import java.sql.Date;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
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
import java.text.SimpleDateFormat;
import java.util.*;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView.TableCell;

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
				"Account", "SupplierNo.", "CustomerNo.", "ProductNo.", "Amount", "D/C", "Description", "Date", "Source"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, /*Float.class, Integer.class,*/String.class, String.class, Date.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		//tableSale.setModel(model);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		
		JComboBox box = new JComboBox();  
        box.addItem("Debit");  
        box.addItem("Credit");  
        TableColumn d = table.getColumn("D/C");  
        DefaultCellEditor dce = new DefaultCellEditor(box);   
        d.setCellEditor(dce);
        
        ////////////////////////////////////////////////
        

        //淇敼琛ㄦ牸鐨勯粯璁ょ紪杈戝櫒锛�
        table.getColumnModel().getColumn(7).setCellEditor(new MyButtonEditor());

         

//        /*
//        杩欐牱鍚庡氨鑳藉熀鏈揪鍒版晥鏋滀簡銆備絾鏄繕瑕佹敞鎰忥紝瀵瑰垪2鏉ヨ锛岃繕闇�瑕佸惎鐢ㄥ彲缂栬緫鍔熻兘锛屾墠琛岋紝涓嶇劧浠嶇劧瑙﹀彂涓嶄簡浜嬩欢鐨勩��
//
//        浠ｇ爜鐗囨锛�
//        */
//
//        public boolean isCellEditable(int row, int column)  
//        {  
//            // 甯︽湁鎸夐挳鍒楃殑鍔熻兘杩欓噷蹇呴』瑕佽繑鍥瀟rue涓嶇劧鎸夐挳鐐瑰嚮鏃朵笉浼氳Е鍙戠紪杈戞晥鏋滐紝涔熷氨涓嶄細瑙﹀彂浜嬩欢銆�   
//            if (column == 2)  
//            {  
//                return true;  
//            }  
//            else  
//            {  
//                return false;  
//            }  
//        } 
        
        ////////////////////////////////////////////////
                 
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
					String samount = (String) table.getValueAt(i, 4);
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
					int dc = (table.getValueAt(i, 5).equals("Debit"))?0:1;
					sumc += dc==0?0:amount;
					sumd += dc==0?amount:0;
					
					i++;
				} while ((i<=rowcount)&&(!voucherNo.equals("")));
				if (sumd - sumc!=0) 
					JOptionPane.showMessageDialog(null, "Unbalanced amount!","", JOptionPane.ERROR_MESSAGE);
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
					String supplierno = (String) table.getValueAt(i, 1);  //now i=0
					String customerno = (String) table.getValueAt(i, 2);
					String productno = (String) table.getValueAt(i, 3);
					String source = (String) table.getValueAt(i, 8);
					do
					{
						if (sumd - sumc==0) no++;
						voucherno = no+"";
						while (voucherno.length()<10)
						{
							voucherno = "0" + voucherno;
						}
						voucherNo = (String) table.getValueAt(i, 0);     //whether we have got to the end
						if (voucherNo.equals("")) break;
						String account = (String) table.getValueAt(i, 0);
//						String supplierno = (String) table.getValueAt(i, 1);
//						String customerno = (String) table.getValueAt(i, 2);
//						String productno = (String) table.getValueAt(i, 3);
						DecimalFormat decimalFormat=new DecimalFormat(".00");
						double amount = 0;
						String samount = (String) table.getValueAt(i, 4);
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
						int dc = (table.getValueAt(i, 5).equals("Debit"))?0:1;
						
						sumc += dc==0?0:amount;
						sumd += dc==0?amount:0;
						String description = (String) table.getValueAt(i, 6);
						Date utildate=(Date) table.getValueAt(i, 7);
						java.sql.Date date=new java.sql.Date(utildate.getTime());
						System.out.println(date);
//						SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
//						String dateString=f.format(date);
//						System.out.println(dateString);
//						java.util.Date utildate = (Date) table.getValueAt(i, 7);
//						java.sql.Date date=new java.sql.Date(utildate.getTime());
//						
//						
//						System.out.println(date);
//						String source = (String) table.getValueAt(i, 8);			
						String sql_account = "INSERT INTO account values(\""+voucherno+"\",\""+account+"\",\""+amount+"\","+dc+",\""+date+"\")";
						String sql_voucher = "INSERT INTO voucher values(\""+voucherno+"\",\""+supplierno+"\",\""+customerno+"\",\""+productno +"\",\""+description+"\",\""+date+"\",\""+source+"\",\""+UserNo+"\")";
						
						double signedamount = (dc==0)?amount:-amount;
						String sql_balance = "INSERT INTO balance(account,balance) VALUES(\""+account+"\","+signedamount+") ON DUPLICATE KEY UPDATE balance=balance+"+signedamount;
					    if(date.getTime()<=new Date().getTime()) dbconn.Update(dbconn.conn, sql_balance);
						dbconn.Update(dbconn.conn, sql_account);
						System.out.print("INSERT INTO account values("+voucherno+","+account+","+amount+","+dc+","+date+");" + "\n");
						if (sumd - sumc==0) 
						{
							dbconn.Update(dbconn.conn, sql_voucher);
							System.out.print("INSERT INTO voucher values("+voucherno+","+supplierno+","+customerno+","+productno +","+description+","+date+","+source+","+UserNo+");" + "\n");
							sumd = sumc = 0;
						}
						i++;
					} while ((i<=rowcount)&&(!voucherNo.equals("")));
					JOptionPane.showMessageDialog(null, "Record successfully!","", JOptionPane.INFORMATION_MESSAGE);
					//System.out.print("Fuck U MOTHERFXCKER!");//FUUUUUUUUUUUUUUUUUUUUUCK UUUUUUUUU RECORD SUCCESS!
					//drawNewTable();
					
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
								"Account", "SupplierNo.", "CustomerNo.", "ProductNo.", "Amount", "D/C", "Description", "Date", "Source"
							}
						) {
							Class[] columnTypes = new Class[] {
								String.class, String.class, String.class, String.class, String.class, /*Float.class, Integer.class,*/String.class, String.class, Date.class, String.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
						});

						//tableSale.setModel(model);
						table.getColumnModel().getColumn(6).setPreferredWidth(50);
						JComboBox box = new JComboBox();  
				        box.addItem("Debit");  
				        box.addItem("Credit");  
				        TableColumn d = table.getColumn("D/C");  
				        DefaultCellEditor dce = new DefaultCellEditor(box);   
				        d.setCellEditor(dce);
				        
				        table.getColumnModel().getColumn(7).setCellEditor(new MyButtonEditor());
					
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
//				((DefaultTableModel) table.getModel()).setRowCount(0);
				
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
						"Account", "SupplierNo.", "CustomerNo.", "ProductNo.", "Amount", "D/C", "Description", "Date", "Source"
					}
				) {
					Class[] columnTypes = new Class[] {
						String.class, String.class, String.class, String.class, String.class, /*Float.class, Integer.class,*/String.class, String.class, Date.class, String.class
					};
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

				//tableSale.setModel(model);
				table.getColumnModel().getColumn(6).setPreferredWidth(50);
				JComboBox box = new JComboBox();  
		        box.addItem("Debit");  
		        box.addItem("Credit");  
		        TableColumn d = table.getColumn("D/C");  
		        DefaultCellEditor dce = new DefaultCellEditor(box);   
		        d.setCellEditor(dce);
		        
		        table.getColumnModel().getColumn(7).setCellEditor(new MyButtonEditor());
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
	
	////////////////////////////////////////////////////////////
	public class MyButtonEditor extends DefaultCellEditor  
    {  
      
        /** 
         * serialVersionUID 
         */  
        private static final long serialVersionUID = -6546334664166791132L;  
      
        private JPanel panel;  
      
        private JButton button;  
      
        public MyButtonEditor()  
        {  
            // DefautlCellEditor鏈夋鏋勯�犲櫒锛岄渶瑕佷紶鍏ヤ竴涓紝浣嗚繖涓笉浼氫娇鐢ㄥ埌锛岀洿鎺ew涓�涓嵆鍙��   
            super(new JTextField());  
      
            // 璁剧疆鐐瑰嚮鍑犳婵�娲荤紪杈戙��   
            this.setClickCountToStart(1);  
      
            this.initButton();  
      
            this.initPanel();  
      
            // 娣诲姞鎸夐挳銆�   
            this.panel.add(this.button);  
        }  
      
        private void initButton()  
        {  
            this.button = new JButton();  
      
            // 璁剧疆鎸夐挳鐨勫ぇ灏忓強浣嶇疆銆�   
            this.button.setBounds(0, 0, 50, 15);  
      
            // 涓烘寜閽坊鍔犱簨浠躲�傝繖閲屽彧鑳芥坊鍔燗ctionListner浜嬩欢锛孧ouse浜嬩欢鏃犳晥銆�   
            this.button.addActionListener(new ActionListener()  
            {  
                public void actionPerformed(ActionEvent e)  
                {  
                    // 瑙﹀彂鍙栨秷缂栬緫鐨勪簨浠讹紝涓嶄細璋冪敤tableModel鐨剆etValue鏂规硶銆�   
                    MyButtonEditor.this.fireEditingCanceled();
                    int row = table.getSelectedRow();
                    int col = table.getSelectedColumn();
                    System.out.print("Performed Success! Col: " + col + " Row: " + row + "\n");
                    DateUI dateTable = new DateUI(table, row, col);
                    // 杩欓噷鍙互鍋氬叾瀹冩搷浣溿��   
                    // 鍙互灏唗able浼犲叆锛岄�氳繃getSelectedRow,getSelectColumn鏂规硶鑾峰彇鍒板綋鍓嶉�夋嫨鐨勮鍜屽垪鍙婂叾瀹冩搷浣滅瓑銆�   
                }  
            });  
      
        }  
      
        private void initPanel()  
        {  
            this.panel = new JPanel();  
      
            // panel浣跨敤缁濆瀹氫綅锛岃繖鏍穊utton灏变笉浼氬厖婊℃暣涓崟鍏冩牸銆�   
            this.panel.setLayout(null);  
        }  
      
      
        /** 
         * 杩欓噷閲嶅啓鐖剁被鐨勭紪杈戞柟娉曪紝杩斿洖涓�涓狫Panel瀵硅薄鍗冲彲锛堜篃鍙互鐩存帴杩斿洖涓�涓狟utton瀵硅薄锛屼絾鏄偅鏍蜂細濉厖婊℃暣涓崟鍏冩牸锛� 
         */  
        @Override  
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)  
        {  
            // 鍙负鎸夐挳璧嬪�煎嵆鍙�備篃鍙互浣滃叾瀹冩搷浣溿��   
            this.button.setText(value == null ? "" : String.valueOf(value));  
      
            return this.panel;  
        }  
      
        /** 
         * 閲嶅啓缂栬緫鍗曞厓鏍兼椂鑾峰彇鐨勫�笺�傚鏋滀笉閲嶅啓锛岃繖閲屽彲鑳戒細涓烘寜閽缃敊璇殑鍊笺�� 
         */  
        @Override  
        public Object getCellEditorValue()  
        {  
            return this.button.getText();  
        }  
      
    } 
	

	
	
	////////////////////////////////////////////////////////////
}
