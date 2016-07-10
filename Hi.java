import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.mysql.jdbc.Connection;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Hi {

	JFrame frame;
	private JTextField txtUser;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hi window = new Hi();
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
	public Hi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBodongAccount = new JLabel("Bodong Account");
		lblBodongAccount.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblBodongAccount.setBounds(171, 22, 145, 21);
		frame.getContentPane().add(lblBodongAccount);
		
		JLabel lblUser = new JLabel("UserNo.");
		lblUser.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblUser.setBounds(117, 80, 44, 15);
		frame.getContentPane().add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setBounds(171, 77, 139, 21);
		txtUser.setText("111111"); //ƒ¨»œ’À∫≈
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblPass.setBounds(107, 126, 54, 15);
		frame.getContentPane().add(lblPass);
		
		txtPass = new JPasswordField();
		txtPass.setColumns(10);
		txtPass.setBounds(171, 122, 139, 21);
		txtPass.setEchoChar('*');
		txtPass.setText("r00t_User"); //ƒ¨»œ√‹¬Î
		frame.getContentPane().add(txtPass);
		
		JButton btnLogin = new JButton("Log in");
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				//ªÒ»°’À∫≈”Î√‹¬Î
				DBConnect dbconn = new DBConnect();
				Connection conn = (Connection) dbconn.conn;
				try 
				{	
					ResultSet rs = dbconn.Query(conn, "Login");
					while(rs.next())
					{
						int userno = rs.getInt(1);
						String password = rs.getString(2);
						String username = rs.getString(3);
						//System.out.print(userno + " " + password + " " + username + " indb\n");
						//System.out.print(txtUser.getText() + " " + txtPass.getText() + " " + username + " input\n" );
						//if ("Qi".equals(txtUser.getText()) && "123456".equals(txtPass.getText()))
						if (txtUser.getText().equals(userno+"") && password.equals(txtPass.getText()))
						{
							//System.out.print("Add a \"Login Success!\" dialog!\n");
							Transfer transfer=new Transfer(conn, userno);
							transfer.frame.setVisible(true);
							frame.setVisible(false);
							break;
						}
						else //System.out.print("Add a \"Wrong Password!\" dialog!\n");
						{
							JOptionPane.showMessageDialog(null, "Please enter the correct information","Wrong Infomation", JOptionPane.ERROR_MESSAGE);
							txtUser.setText("");
							txtPass.setText("");
						}
					}
					rs.close();
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//else frame.setTitle(txtPass.getText());
				
			}
		});
		btnLogin.setBounds(113, 184, 93, 23);
		frame.getContentPane().add(btnLogin);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				System.exit(0);
			}
		});
		
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnExit.setBounds(243, 183, 93, 23);
		frame.getContentPane().add(btnExit);
	}
}
