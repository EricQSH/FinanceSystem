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
import java.awt.event.ActionEvent;

public class Account {

	JFrame frame;
	private JTextField textVoucher;
	private JTextField textAccount;
	private JTextField textAmount;
	private JTextField textSupplier;
	private JTextField textCustomer;
	private JTextField textItem;
	private JTextField textDescription;
	private JTextField textAcctDate;

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
	public Account(Connection conn, int UserNo) {
		initialize(conn, UserNo);
	}
	public Account() {
		initialize(null, -1);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Connection conn, final int UserNo) {
		// 加个判断 是否登陆 是否连接数据库
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Account");
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTitle.setBounds(214, 21, 73, 15);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblVoucherno = new JLabel("VoucherNo.");
		lblVoucherno.setBounds(44, 71, 65, 15);
		frame.getContentPane().add(lblVoucherno);
		
		textVoucher = new JTextField();
		textVoucher.setBounds(110, 71, 120, 21);
		frame.getContentPane().add(textVoucher);
		textVoucher.setColumns(10);
		
		JLabel lblAccount = new JLabel("Account");
		lblAccount.setBounds(44, 108, 54, 15);
		frame.getContentPane().add(lblAccount);
		
		textAccount = new JTextField();
		textAccount.setColumns(10);
		textAccount.setBounds(110, 108, 120, 21);
		frame.getContentPane().add(textAccount);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(44, 147, 54, 15);
		frame.getContentPane().add(lblAmount);
		
		textAmount = new JTextField();
		textAmount.setBounds(110, 144, 50, 21);
		frame.getContentPane().add(textAmount);
		textAmount.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Debit", "Credit"}));
		comboBox.setBounds(165, 144, 67, 21);
		frame.getContentPane().add(comboBox);
		
		JLabel lblSupplier = new JLabel("SupplierNo.");
		lblSupplier.setBounds(272, 71, 73, 15);
		frame.getContentPane().add(lblSupplier);
		
		textSupplier = new JTextField();
		textSupplier.setBounds(355, 71, 80, 21);
		frame.getContentPane().add(textSupplier);
		textSupplier.setColumns(10);
		
		JLabel lblCustomer = new JLabel("CustomerNo.");
		lblCustomer.setBounds(272, 108, 73, 15);
		frame.getContentPane().add(lblCustomer);
		
		textCustomer = new JTextField();
		textCustomer.setColumns(10);
		textCustomer.setBounds(355, 108, 80, 21);
		frame.getContentPane().add(textCustomer);
		
		JLabel lblItem = new JLabel("ItemNo.");
		lblItem.setBounds(272, 147, 54, 15);
		frame.getContentPane().add(lblItem);
		
		textItem = new JTextField();
		textItem.setColumns(10);
		textItem.setBounds(355, 144, 80, 21);
		frame.getContentPane().add(textItem);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(44, 189, 80, 15);
		frame.getContentPane().add(lblDescription);
		
		textDescription = new JTextField();
		textDescription.setBounds(134, 189, 301, 21);
		frame.getContentPane().add(textDescription);
		textDescription.setColumns(10);
		
		JLabel lblAcctDate = new JLabel("AccountDate:");
		lblAcctDate.setBounds(44, 231, 80, 15);
		frame.getContentPane().add(lblAcctDate);
		
		textAcctDate = new JTextField();
		textAcctDate.setBounds(134, 228, 96, 21);
		frame.getContentPane().add(textAcctDate);
		textAcctDate.setColumns(10);
		
		JButton btnAcctVou = new JButton("Account Voucher");
		btnAcctVou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcctVoucher acctVoucher=new AcctVoucher(conn, UserNo);
				acctVoucher.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnAcctVou.setBounds(44, 286, 170, 23);
		frame.getContentPane().add(btnAcctVou);
		
		JButton btnOriginalVou = new JButton("Original Voucher");
		btnOriginalVou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Voucher voucher=new Voucher(conn, UserNo);
				voucher.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnOriginalVou.setBounds(265, 286, 170, 23);
		frame.getContentPane().add(btnOriginalVou);
		
		JButton btnRecord = new JButton("Record");
		btnRecord.setBounds(44, 333, 80, 23);
		frame.getContentPane().add(btnRecord);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textVoucher.setText("");
				textAccount.setText("");
				textAmount.setText("");
				textSupplier.setText("");
				textCustomer.setText("");
				textItem.setText("");
				textDescription.setText("");
				textAcctDate.setText("");
			}
		});
		btnClear.setBounds(148, 333, 80, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				System.exit(0);
			}
		});
		btnExit.setBounds(355, 333, 80, 23);
		frame.getContentPane().add(btnExit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transfer transfer=new Transfer();
				transfer.frame.setVisible(true);
				frame.setVisible(false);
				
			}
		});
		btnBack.setBounds(252, 333, 80, 23);
		frame.getContentPane().add(btnBack);
	}
}
