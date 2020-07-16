package content;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClassForm extends JFrame {
	SQLconnector sqlc = new SQLconnector();
	private JPanel contentPane;
	private final JLabel ClassIdLabel = new JLabel("Mã lớp: ");
	private final JLabel ClassNameLabel = new JLabel("Tên lớp:  ");
	private final JTextField ClassIdTextField = new JTextField(10);
	private final JTextField ClassNameTextField = new JTextField(10);
	private final JButton EnterClassBtn = new JButton("Nhập");
	private final JButton DeleteClassBtn = new JButton("Xóa");
	public List<Classes> listClasses;
	private final JButton UpdateClassBtn = new JButton("Sửa");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassForm frame = new ClassForm();
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
	public ClassForm() {
		initGUI();
	}
	private void initGUI() {
		setTitle("Quản lý lớp");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ClassIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		ClassIdLabel.setBounds(44, 67, 90, 45);
		
		contentPane.add(ClassIdLabel);
		ClassNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		ClassNameLabel.setBounds(43, 122, 106, 45);
		
		contentPane.add(ClassNameLabel);
		ClassIdTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if((Character.isDigit(k)) || (Character.isLetter(k)))
				{
					
					if(ClassIdTextField.getText().length()<20)
					{
						ClassIdTextField.setEditable(true);
					}
					else
					{
						ClassIdTextField.setEditable(false);
					}
				}
				else
				{
					if((k==KeyEvent.VK_BACK_SPACE) || k==KeyEvent.VK_DELETE)
					{
							
						ClassIdTextField.setEditable(true);
					}
					else
					{
						eve.consume();
					}
				}
			}
		});
		ClassIdTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		ClassIdTextField.setBounds(154, 77, 157, 26);
		
		contentPane.add(ClassIdTextField);
		ClassNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if(Character.isLetter(k))
				{
					
					if(ClassNameTextField.getText().length()<20)
					{
						ClassNameTextField.setEditable(true);
					}
					else
					{
						ClassNameTextField.setEditable(false);
					}
				}
				else
				{
					if((k==KeyEvent.VK_BACK_SPACE) || k==KeyEvent.VK_DELETE || k==KeyEvent.VK_SPACE)
					{
							
						ClassNameTextField.setEditable(true);
					}
					else
					{
						eve.consume(); //Tiêu thụ sự kiện này => Không xử lý nó
					}
				}
			}
		});
		ClassNameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		ClassNameTextField.setBounds(155, 125, 260, 36);
		
		contentPane.add(ClassNameTextField);
		EnterClassBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!ClassIdTextField.getText().equals("") && !ClassNameTextField.getText().equals(""))
					{
						Classes cl = new Classes(ClassIdTextField.getText(),ClassNameTextField.getText());
						save(cl);
						setVisible(false);
					}
					else
					{
						throw new Exception("For input string: \"\"");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if(e1.getMessage().equals("For input string: \"\""))
					{
						JLabel mess = new JLabel("Thông tin chưa được nhập đầy đủ");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					else
					{
						JLabel mess = new JLabel(e1.getMessage());
						mess.setFont(new Font("Arial", Font.BOLD, 24));
						JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					
				}
			}
		});
		EnterClassBtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		EnterClassBtn.setBounds(27, 191, 106, 46);
		
		contentPane.add(EnterClassBtn);
		DeleteClassBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!ClassIdTextField.getText().equals(""))
					{
						delete(ClassIdTextField.getText());
						setVisible(false);
					}
					else
					{
						throw new Exception("For input string: \"\"");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if(e1.getMessage().equals("For input string: \"\""))
					{
						JLabel mess = new JLabel("Không lấy được mã lớp");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					else
					{
						JLabel mess = new JLabel(e1.getMessage());
						mess.setFont(new Font("Arial", Font.BOLD, 24));
						JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					
				}
			}
		});
		DeleteClassBtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		DeleteClassBtn.setBounds(296, 191, 106, 46);
		
		contentPane.add(DeleteClassBtn);
		
		UpdateClassBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!ClassIdTextField.getText().equals("") && !ClassNameTextField.getText().equals(""))
					{
						Classes cl = new Classes(ClassIdTextField.getText(),ClassNameTextField.getText());
						update(cl);
						setVisible(false);
					}
					
					else
					{
						throw new Exception("For input string: \"\"");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if(e1.getMessage().equals("For input string: \"\""))
					{
						JLabel mess = new JLabel("Thông tin chưa được nhập đầy đủ");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					else
					{
						JLabel mess = new JLabel(e1.getMessage());
						mess.setFont(new Font("Arial", Font.BOLD, 24));
						JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		UpdateClassBtn.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		UpdateClassBtn.setBounds(164, 191, 106, 46);
		
		contentPane.add(UpdateClassBtn);
	}
	
	
	public boolean save(Classes cl) 
	{
		int result = -1;
		Connection con = sqlc.getConnection();
		try {
				Statement stm = con.createStatement();
				String sql = "INSERT INTO Classes VALUES (N'"+cl.getClassId()+"', N'" + cl.getClassName() + "')";
				result = stm.executeUpdate(sql);
				JLabel mess2 = new JLabel("Đã nhập 1 lớp");
				mess2.setFont(new Font("Arial", Font.BOLD, 18));
				JOptionPane.showMessageDialog(null, mess2);
				Menu.ClassUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
			JLabel mess = new JLabel(e.getMessage());
			mess.setFont(new Font("Arial", Font.BOLD, 24));
			JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
		}
		return result > 0;
	}
	
	public boolean delete(String ml) {
		int result = -1;
		int i;
		Connection con = sqlc.getConnection();
		try {
				Statement stm = con.createStatement();
				String sql = "DELETE Classes WHERE ClassId=N'" + ml + "'";
				result = stm.executeUpdate(sql);
				JLabel mess2 = new JLabel("Đã xóa 1 lớp");
				mess2.setFont(new Font("Arial", Font.BOLD, 18));
				JOptionPane.showMessageDialog(null, mess2);
				Menu.ClassUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			/*if(e.getMessage().equals("404"));
			{
				JLabel mess = new JLabel("Không tìm thấy lớp để xóa");
				mess.setFont(new Font("Arial", Font.BOLD, 24));
				JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
			}*/
			JLabel mess = new JLabel("Xóa không đúng cách. Cụ thể lỗi: "+e.getMessage());
			mess.setFont(new Font("Arial", Font.BOLD, 24));
			JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
			
		}
		return result > 0;
	}
	
	public boolean update(Classes cl) {
		int result = -1;
		Connection con = sqlc.getConnection();
		try {
				Statement stm = con.createStatement();
				String sql = "UPDATE Classes SET ClassName=N'"+cl.getClassName()+"' WHERE ClassId=N'" + cl.getClassId() + "'";
				result = stm.executeUpdate(sql);
				JLabel mess2 = new JLabel("Đã sửa 1 lớp");
				mess2.setFont(new Font("Arial", Font.BOLD, 18));
				JOptionPane.showMessageDialog(null, mess2);
				Menu.ClassUpdate();
	            
		} catch (SQLException e) {
			e.printStackTrace();
			/*if(e.getMessage().equals("404"));
			{
				JLabel mess = new JLabel("Không tìm thấy lớp để sửa");
				mess.setFont(new Font("Arial", Font.BOLD, 24));
				JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
			}*/
			JLabel mess = new JLabel(e.getMessage());
			mess.setFont(new Font("Arial", Font.BOLD, 24));
			JOptionPane.showMessageDialog(null, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
		}
		return result > 0;
	}
	
}
