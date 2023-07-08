import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JPasswordField;

public class Login extends JFrame {
	private JTextField txtUSN;
	private JPasswordField txtPass;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		getContentPane().setBackground(new Color(255, 228, 225));
		setBackground(new Color(255, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblUSN = new JLabel("Username:");
		lblUSN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUSN.setBounds(81, 144, 72, 14);
		getContentPane().add(lblUSN);
		
		JLabel lblPASS = new JLabel("Password:");
		lblPASS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPASS.setBounds(249, 144, 72, 14);
		getContentPane().add(lblPASS);
		
		txtUSN = new JTextField();
		txtUSN.setBounds(61, 169, 129, 20);
		getContentPane().add(txtUSN);
		txtUSN.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(224, 169, 129, 20);
		getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		JLabel lblRule = new JLabel("Please Enter Login Credentials -");
		lblRule.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRule.setBounds(95, 89, 239, 38);
		getContentPane().add(lblRule);
		
		JButton btnLgn = new JButton("LOGIN");
		btnLgn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					 String userValue = txtUSN.getText();        //get user entered username from the textField1  
				     String passValue =(String) txtPass.getText();        //get user entered pasword from the textField2  
				      
				        //check whether the credentials are authentic or not  
				        if (userValue.equals("CommanMan") && passValue.equals("cm@12345")) {  //if authentic, navigate user to a new page  
				              
				            //create instance of the NewPage  
				        	
				        	Seat_Allocation frame = new Seat_Allocation();
				        	
							frame.setVisible(true);
				        }
				        else {
				        	JOptionPane.showMessageDialog(null, "Enter Valid Credentials");
				        		
						}
				        txtUSN.setText(null);
			        	txtPass.setText(null);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				
				  
			}
		});
		btnLgn.setBounds(170, 227, 89, 23);
		getContentPane().add(btnLgn);
		
		JCheckBox chckbxPass = new JCheckBox("Show Password");
		chckbxPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(chckbxPass.isSelected())
				{
					 txtPass.setEchoChar((char)0);
				}
				else {
					 txtPass.setEchoChar('*');
				}
				
			}
		});
		chckbxPass.setBounds(224, 196, 129, 23);
		getContentPane().add(chckbxPass);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 20, 60));
		panel.setBounds(10, 21, 414, 44);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblHead = new JLabel("Welcome To Exam Seating Arrangement System");
		lblHead.setBounds(10, 11, 394, 25);
		panel.add(lblHead);
		lblHead.setForeground(new Color(255, 240, 245));
		lblHead.setFont(new Font("Rockwell", Font.PLAIN, 15));
		lblHead.setHorizontalAlignment(SwingConstants.CENTER);
		
		
	}
}
