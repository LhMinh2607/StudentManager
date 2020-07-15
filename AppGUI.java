package content;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.ImageIcon;

public class AppGUI {

	JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private final JLabel logoLabel = new JLabel("New label");

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGUI window = new AppGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public AppGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("TRÌNH QUẢN LÝ SINH VIÊN - 0.1 Early build");
		frame.setBounds(100, 100, 835, 537);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JLabel title = new JLabel("TRÌNH QUẢN LÝ SINH VIÊN");
		title.setBackground(SystemColor.info);
		title.setForeground(new Color(0, 120, 215));
		title.setFont(new Font("Times New Roman", Font.BOLD, 40));
		title.setBounds(147, 28, 560, 59);
		frame.getContentPane().add(title);
		logoLabel.setIcon(new ImageIcon(AppGUI.class.getResource("/img/utc2logo.png")));
		logoLabel.setBounds(58, 118, 265, 269);
		frame.getContentPane().add(logoLabel);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		file.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(file);
		
		JMenuItem showlinkMenuItem = new JMenuItem("Contact");
		showlinkMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JLabel mess = new JLabel("<html>Lê Huỳnh Minh 5951071059@st.utc2.edu.vn✔ <br/> Đào Khải Minh 5951071058@st.utc2.edu.vn <br/> Khuất Lê Thành Luân 5951071054@st.utc2.edu.vn</html");
				mess.setFont(new Font("Arial", Font.BOLD, 18));
				JOptionPane.showMessageDialog(frame, mess, "Contact", JOptionPane.PLAIN_MESSAGE);
			}
		});
		showlinkMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		file.add(showlinkMenuItem);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		file.add(exitMenuItem);
		
		JMenu aboutMenu = new JMenu("About");
		aboutMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(aboutMenu);
		
		JMenuItem btl = new JMenuItem("BTL JAVA QLSV - Lê Huỳnh Minh✔, Đào Khải Minh, Khuất Lê Thành Luân");
		btl.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		aboutMenu.add(btl);
		frame.getContentPane().setLayout(null);
		
		JLabel name = new JLabel("Tài khoản: ");
		name.setFont(new Font("Tahoma", Font.PLAIN, 25));
		name.setBounds(333, 186, 152, 37);
		frame.getContentPane().add(name);
		
		JLabel pass = new JLabel("Mật khẩu: ");
		pass.setFont(new Font("Tahoma", Font.PLAIN, 25));
		pass.setBounds(340, 246, 145, 37);
		frame.getContentPane().add(pass);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(533, 189, 185, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(533, 252, 185, 37);
		frame.getContentPane().add(passwordField);
		
		JButton loginbtn = new JButton("Đăng nhập");
		loginbtn.setBackground(SystemColor.inactiveCaptionBorder);
		loginbtn.addActionListener(new ActionListener() {
			//String n=textField.getText();
			//String p=passwordField.getText();
			//String p=String.valueOf(passwordField.getPassword());
			//@Override
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("LhMinh2607") && String.valueOf(passwordField.getPassword()).equals("123456"))
				{
					JOptionPane.showMessageDialog(frame, "Đăng nhập thành công");
					JOptionPane.showMessageDialog(frame, "Chào "+textField.getText());
					Menu menu = new Menu();
					menu.setVisible(true);
					frame.setVisible(false);
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Đăng nhập thất bại. Tài khoản hoặc mật khẩu không đúng.");
				}
				
			}
		});
		loginbtn.setFont(new Font("Times New Roman", Font.BOLD, 30));
		loginbtn.setBounds(413, 323, 230, 37);
		frame.getContentPane().add(loginbtn);
		
		/*public void switchPanel(JPanel panel)
		{
			getContentPane()
		}*/
		
	}
}
