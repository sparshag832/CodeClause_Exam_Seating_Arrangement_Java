import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.print.attribute.standard.SheetCollate;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Seat_Allocation extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private JTextField txtNM;
	private JTextField txtRN;
	ArrayList<String> seat = new ArrayList<>();
	private JPanel pnlTable;

	public void declareSeats()
	{
		for(int i=1;i<=3;i++){
		for(int j=1;j<=10;j++){
		String s = "R-"+i+" S-"+j;
		seat.add(s);
		}
		}
		try {
		String a= "Select Seat from seatallocation";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/seating?user=root&password=root");
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(a);
		while (rs.next()){
		seat.remove(rs.getString(1));
		}
		}catch (Exception e){
		System.out.println(e);
		}
		}
	
	public void settableData() {
		
		try
		{
		String a= "SELECT * FROM seatallocation ORDER BY ROLL_NO ASC;";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/seating?user=root&password=root");
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(a);
		
		java.sql.ResultSetMetaData rsmd=rs.getMetaData();
		DefaultTableModel model=new DefaultTableModel();
		
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{
			
			model.addColumn(rsmd.getColumnName(i));
		}  
		while(rs.next())
		{
			String row[]=new String[rsmd.getColumnCount()];
			for(int i=1;i<=rsmd.getColumnCount();i++)
			{
				row[i-1]=rs.getString(i);
			}
			model.addRow(row);
		}
		table.setModel(model);
		connection.close();

				}

				catch(Exception e)
				{
					// JOptionPane.showMessageDialog(null, e.getMessage());
				}
	
}
	
	public void export(JTable table, File file){
	    try
	    {
	      TableModel m = table.getModel();
	      FileWriter fw = new FileWriter(file);
	      for(int i = 0; i < m.getColumnCount(); i++){
	        fw.write(m.getColumnName(i) + "\t");
	      }
	      fw.write("\n");
	      for(int i=0; i < m.getRowCount(); i++) {
	        for(int j=0; j < m.getColumnCount(); j++) {
	          fw.write(m.getValueAt(i,j).toString()+"\t");
	        }
	        fw.write("\n");
	      }
	      fw.close();
	    }
	    catch(Exception e2){ System.out.println(e2); }
	  }

	
	public Seat_Allocation() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 433);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 20, 60));
		panel.setBounds(10, 21, 528, 38);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHEAD = new JLabel("Exam Seat Arrangement System");
		lblHEAD.setBounds(123, 11, 300, 20);
		panel.add(lblHEAD);
		lblHEAD.setForeground(new Color(255, 228, 196));
		lblHEAD.setFont(new Font("Segoe UI Light", Font.BOLD, 14));
		lblHEAD.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(244, 164, 96));
		panel_1.setBounds(367, 107, 171, 276);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEN = new JLabel("Enter Name");
		lblEN.setBounds(10, 11, 107, 14);
		panel_1.add(lblEN);
		
		txtNM = new JTextField();
		txtNM.setBounds(10, 36, 151, 20);
		panel_1.add(txtNM);
		txtNM.setColumns(10);
		
		JLabel lblERN = new JLabel("Enter Roll Number");
		lblERN.setBounds(10, 80, 107, 14);
		panel_1.add(lblERN);
		
		txtRN = new JTextField();
		txtRN.setBounds(10, 113, 151, 20);
		panel_1.add(txtRN);
		txtRN.setColumns(10);
		
		JLabel lblSLC = new JLabel("Select Class");
		lblSLC.setBounds(10, 167, 78, 14);
		panel_1.add(lblSLC);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"CS1", "CS2","IT"}));
		comboBox.setBounds(98, 163, 63, 22);
		panel_1.add(comboBox);
		
		
		JButton btnAllS = new JButton("Allocate Seat");
		btnAllS.setBackground(new Color(0, 255, 0));
		btnAllS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( txtNM.getText().equals("")|| txtRN.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Please fill all record to get seat.");
					}else{
					try {
					Random rn = new Random();
					String seatNo="";
					if(seat.size()==0){
					JOptionPane.showMessageDialog(null,"THERE ARE NO SEATS AVAILABLE");
					}else{
					seatNo = seat.get(rn.nextInt(seat.size()));
					}
					String sql = "insert into seatallocation values (?,?,?,?)";
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/seating?user=root&password=root");
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setString(1,txtNM.getText());
					statement.setString(3,txtRN.getText());
					statement.setString(2,""+comboBox.getSelectedItem());
					statement.setString(4,seatNo);
					seat.remove(seatNo);
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null,"RECORD ADDED SUCCESSFULLY");
					txtNM.setText("");
					txtRN.setText("");
					for(String i:seat){
					System.out.println(i);
					}
					}catch (Exception ex){
					JOptionPane.showMessageDialog(null,ex.getMessage());
					}
					settableData();
					}
				
			}
		});
		btnAllS.setBounds(20, 223, 130, 23);
		panel_1.add(btnAllS);
		
	
		pnlTable = new JPanel();
		pnlTable.setBounds(21, 107, 321, 246);
		contentPane.add(pnlTable);
		pnlTable.setLayout(new BorderLayout(0, 0));
		
        table = new JTable();
        pnlTable.add(table, BorderLayout.CENTER);
        
		JScrollPane scrollPane = new JScrollPane(table);
		pnlTable.add(scrollPane);
		
		JLabel lblESD = new JLabel("Enter Student Details");
		lblESD.setHorizontalAlignment(SwingConstants.CENTER);
		lblESD.setBounds(390, 83, 122, 14);
		contentPane.add(lblESD);
		
		JLabel lblSALC = new JLabel("Seat Allocation Chart");
		lblSALC.setHorizontalAlignment(SwingConstants.CENTER);
		lblSALC.setBounds(103, 83, 133, 14);
		contentPane.add(lblSALC);
		
		JButton btnExcel = new JButton("Generate Excel");
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 if(e.getSource() == btnExcel){
			           JFileChooser fchoose = new JFileChooser();
			           int option = fchoose.showSaveDialog(Seat_Allocation.this);
			           if(option == JFileChooser.APPROVE_OPTION){
			             String name = fchoose.getSelectedFile().getName(); 
			             String path = fchoose.getSelectedFile().getParentFile().getPath();
			             String file = path + "\\" + name + ".xls"; 
			             export(table, new File(file));
			           }
			         }
			      }
			
		});
		
		btnExcel.setBackground(new Color(0, 255, 0));
		btnExcel.setBounds(114, 364, 122, 23);
		contentPane.add(btnExcel);
		
		settableData();
		declareSeats();
	}
}
