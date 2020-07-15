package content;

/**
 * 
 * @author Lê Huỳnh Minh
 * 
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Window.Type;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;



public class Menu extends JFrame {

	static Menu frame;
	private JPanel contentPane;
	private JTextField mssvTextField;
	private JTextField NameTextField;
	//private StudentManager s = new StudentManager();
	private JTextField GPAtextField;
	private JTextArea AddressTextArea;
	private JTextField birthYearTextField;
	private JTable stTable;
	
	private Connection con;
	private PreparedStatement pst;
	private Statement stm;
	private ResultSet rs;
	
	/*private String [] columnNames = new String [] {
            "ID", "Name", "Age", "Address", "GPA"};*/
   // private Object data = new Object [][] {};
    
    private String[] nganh = {"Công Nghệ Thông Tin","Kỹ thuật viễn thông","Kinh Tế","Kế Toán","Điện - Điện Tử", "Công Trình Xây Dựng", "Kết Cấu Xây Dựng", "Cơ Khí Ôtô", "Giao Thông Công Trình", "Nhiệt Điện", "Kỹ Thuật Điện", "Quản trị kinh doanh", "Kế toán TH và GT", "Kết cấu xây dựng", "Tự động hoá", "Kinh tế XD công trình GT", "Kinh tế vận tải du lịch", "Địa kỹ thuật công trình GT", "Kỹ thuật xây dựng Cầu hầm", "Cơ khí giao thông công chính", "Quản trị Logistics", "Kinh tế bưu chính viễn thông", "Quản trị doanh nghiệp XD"};
	private final JComboBox comboBox = new JComboBox(nganh);
	
	private StudentDAO std;
	
	DefaultTableModel tbl;
	
	SQLconnector sqlc = new SQLconnector();
	private final JLabel genderLabel = new JLabel("Giới tính:  ");
	private final JRadioButton rdbtnFemaleButton = new JRadioButton("Nữ");
	private final JRadioButton rdbtnMaleButton = new JRadioButton("Nam");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private static DecimalFormat df = new DecimalFormat("0.0");
	private final JTextField idSearchField = new JTextField();
	
	private final JButton Addbtn = new JButton("Thêm");
	private final JButton Updatebtn = new JButton("Sửa");
	private final JButton Deletebtn = new JButton("Xóa");
	
	private final JButton ClearField = new JButton("Refresh");
	private final JButton searchBtn = new JButton("Tìm kiếm");
	private final JLabel SearchLabel = new JLabel("Tìm kiếm theo: ");
	private final JLabel clickRefreshbtn = new JLabel("Bấm Refresh để quay lại chế độ xem bình thường");
	private final JTextField nameSearchField = new JTextField();
	private final JLabel FilteredTitle = new JLabel("FilteredTitle");
	private final JComboBox GenderBox = new JComboBox();
	private final JComboBox SearchByCriteriaJCB = new JComboBox();
	private final JButton hideSearchBtn = new JButton("Hủy");
	private final JMenu FeatureMenu = new JMenu("Chức năng");
	private final JMenuItem xeploai = new JMenuItem("Xếp loại");
	
	private final JToolBar toolBar = new JToolBar();
	private final JLabel RankLabel = new JLabel("Xếp loại: ");
	private final JComboBox RankTitleJCB = new JComboBox();
	private final JMenuItem boloc = new JMenuItem("Bộ lọc");
	private final JMenuItem thongke = new JMenuItem("Thống kê");
	private final JToolBar toolBar2 = new JToolBar();
	private final JComboBox listingJCB = new JComboBox();
	private final JButton btnX2 = new JButton("X");
	private final JLabel ListingLabel = new JLabel("Thống kê: ");
	//private int searchbtnFlag=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Menu();
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
	
	
	public Menu() {
		
		//tableUpdate();
		initGUI();
		tableUpdate();
		
	}
	/*public Student getStudentInfo() {
        // validate student
        if (!validateName() || !validateAge() || !validateAddress() || !validateGPA()) {
            return null;
        }
		try {
            Student student = new Student();
            if (getIDTextField() != null && !"".equals(getIDTextField())) {
                student.setId(Integer.parseInt(getIDTextField()));
            }
            student.setName(getNameTextField().trim());
            student.setAddress(getAddressTextArea().trim());
            student.setClassStudent(getClassTextField().trim());
            student.setAge(Byte.parseByte(getBirthYearTextField().trim()));
            student.setGpa(Float.parseFloat(getGPAtextField().trim()));
            return student;
        } catch (Exception e) {
        	showMessage(e.getMessage());
        }
        return null;
    }
	public void showListStudents(List<Student> list) 
	{
        int size = list.size();
        Object [][] students = new Object[size][5];
        for (int i = 0; i < size; i++) {
            students[i][0] = list.get(i).getId();
            students[i][1] = list.get(i).getName();
            students[i][2] = list.get(i).getAddress();
            students[i][3] = list.get(i).getClass();
            students[i][4] = list.get(i).getAge();
            students[i][5] = list.get(i).getGpa();
        }
        stTable.setModel(new DefaultTableModel(students, columnNames));
    }
	
	private boolean validateName() {
        String name = NameTextField.getText();
        if (name == null || "".equals(name.trim())) {
            NameTextField.requestFocus();
            showMessage("Name không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateAddress() {
        String address = AddressTextArea.getText();
        if (address == null || "".equals(address.trim())) {
            AddressTextArea.requestFocus();
            showMessage("Address không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateAge() {
        try {
            Byte age = Byte.parseByte(birthYearTextField.getText().trim());
            if (age < 18 || age > 100) {
                birthYearTextField.requestFocus();
                showMessage("Năm sinh không hợp lệ, năm sinh nên trong khoảng 1980 đến 2005.");
                return false;
            }
        }catch (Exception e) {
            birthYearTextField.requestFocus();
            showMessage("Năm sinh không hợp lệ!");
            return false;
        }
        return true;
    }
    
    private boolean validateGPA() {
        try {
            Float gpa = Float.parseFloat(GPAtextField.getText().trim());
            if (gpa < 0 || gpa > 10) {
                GPAtextField.requestFocus();
                showMessage("GPA không hợp lệ, gpa nên trong khoảng 0 đến 10.");
                return false;
            }
        } catch (Exception e) {
            GPAtextField.requestFocus();
            showMessage("GPA không hợp lệ!");
            return false;
        }
        return true;
    }
	
	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}*/
	
	private void initGUI() {
		setTitle("TRÌNH QUẢN LÝ SINH VIÊN MENU - 1.0 Early build");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Set OptionPane.messageFont's specified size as DEFAULT
		UIManager.put("OptionPane.messageFont", new Font("System", Font.PLAIN, 24));
		UIManager.put("OptionPane.buttonFont", new Font("System", Font.PLAIN, 20));
		
		
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1, 1);
		contentPane.add(layeredPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		file.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		menuBar.add(file);
		
		JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		file.add(openMenuItem);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportExcel(stTable);
			}
		});
		saveMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		file.add(saveMenuItem);
		
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
		
		JMenuItem btl = new JMenuItem("BTL JAVA QLSV - Lê Huỳnh Minh (✔), Đào Khải Minh, Khuất Lê Thành Luân");
		btl.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		aboutMenu.add(btl);
		FeatureMenu.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		menuBar.add(FeatureMenu);
		xeploai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toolBar.setVisible(true);
				toolBar.setEnabled(true);
				RankTitleJCB.setSelectedIndex(0);
				toolBar2.setVisible(false);
			}
		});
		xeploai.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		FeatureMenu.add(xeploai);
		boloc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toolBar.setVisible(false);
				toolBar2.setVisible(false);
			}
		});
		boloc.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		FeatureMenu.add(boloc);
		thongke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolBar2.setVisible(true);
				listingJCB.setSelectedIndex(0);
				toolBar.setVisible(false);
			}
		});
		thongke.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		
		FeatureMenu.add(thongke);
		menuBar.add(clickRefreshbtn);
		
		clickRefreshbtn.setVisible(false);
		clickRefreshbtn.setBackground(Color.BLACK);
		clickRefreshbtn.setForeground(Color.RED);
		clickRefreshbtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		getContentPane().setLayout(null);
		comboBox.setModel(new DefaultComboBoxModel(nganh));
		
		
		
		//String[] nganh = {"Công Nghệ Thông Tin","Kỹ thuật viễn thông","Kinh Tế","Kế Toán","Điện - Điện Tử", "Công Trình Xây Dựng", "Kết Cấu Xây Dựng", "Cơ Khí Ôtô", "Giao Thông", "Nhiệt Điện", "Kỹ Thuật Điện"};
		//JComboBox comboBox = new JComboBox(nganh);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		comboBox.setBounds(174, 257, 318, 29);
		contentPane.add(comboBox);
		
		
		//Nhập sinh viên/ Thêm sinh viên
		Addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Student st= new Student(Integer.parseInt(mssvTextField.getText()),NameTextField.getText(),AddressTextArea.getText(),(String)comboBox.getSelectedItem(),Integer.parseInt(birthYearTextField.getText()),Double.parseDouble(GPAtextField.getText()));
				//DefaultTableModel model = (DefaultTableModel)table.getModel();
				try {
						StudentDAO std = new StudentDAO();
						Student st= new Student(mssvTextField.getText(),NameTextField.getText(),buttonGroup.getSelection().getActionCommand(),AddressTextArea.getText(),(String)comboBox.getSelectedItem(),Integer.parseInt(birthYearTextField.getText()),Double.parseDouble(GPAtextField.getText()));
						//System.out.println(Integer.parseInt(mssvTextField.getText())+NameTextField.getText()+AddressTextArea.getText()+(String)comboBox.getSelectedItem()+Integer.parseInt(birthYearTextField.getText())+Double.parseDouble(GPAtextField.getText()));
						
						std.save(st);
						tbl.setRowCount(0);
						tableUpdate();
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						if(e1.getMessage().equals("For input string: \"\""))
						{
							JLabel mess = new JLabel("Thông tin chưa được nhập đầy đủ");
							mess.setFont(new Font("Arial", Font.BOLD, 18));
							JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
						}
						else
						{
							JLabel mess = new JLabel("Thông tin nhập không đúng cách. Cụ thể lỗi: "+e1.getMessage());
							mess.setFont(new Font("Arial", Font.BOLD, 18));
							JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
						}
					}
			}
		});
		Addbtn.setBounds(39, 540, 114, 45);
		Addbtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		contentPane.add(Addbtn);
		
		JLabel mssvLabel = new JLabel("MSSV: ");
		mssvLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		mssvLabel.setBounds(63, 90, 90, 45);
		contentPane.add(mssvLabel);
		
		JLabel nameLabel = new JLabel("H\u1ECD & T\u00EAn:  ");
		nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		nameLabel.setBounds(29, 145, 123, 45);
		contentPane.add(nameLabel);
		
		JLabel classLabel = new JLabel("L\u1EDBp:  ");
		classLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		classLabel.setBounds(88, 245, 64, 45);
		contentPane.add(classLabel);
		
		mssvTextField = new JTextField();
		mssvTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		mssvTextField.setBounds(173, 100, 157, 26);
		contentPane.add(mssvTextField);
		mssvTextField.setColumns(10);
		
		NameTextField = new JTextField();
		NameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if(!(Character.isLetter(k) || k==KeyEvent.VK_BACK_SPACE || k==KeyEvent.VK_DELETE || k==KeyEvent.VK_PERIOD || k==KeyEvent.VK_SPACE))
				{
					eve.consume(); //Tiêu thụ sự kiện này => Không xử lý nó
				}
			}
		});
		NameTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		NameTextField.setColumns(10);
		NameTextField.setBounds(174, 148, 260, 36);
		contentPane.add(NameTextField);
		
		JLabel gpaLabel = new JLabel("GPA:  ");
		gpaLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		gpaLabel.setBounds(82, 300, 71, 45);
		contentPane.add(gpaLabel);
		
		GPAtextField = new JTextField();
		GPAtextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if(!(Character.isDigit(k) || k==KeyEvent.VK_BACK_SPACE || k==KeyEvent.VK_DELETE || k==KeyEvent.VK_PERIOD))
				{
					eve.consume(); //Tiêu thụ sự kiện này => Không xử lý nó
				}
			}
		});
		GPAtextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GPAtextField.setColumns(10);
		GPAtextField.setBounds(174, 307, 64, 26);
		contentPane.add(GPAtextField);
		
		JLabel addressLabel = new JLabel("\u0110\u1ECBa ch\u1EC9: ");
		addressLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		addressLabel.setBounds(63, 355, 90, 45);
		contentPane.add(addressLabel);
		
		
		
		
		AddressTextArea = new JTextArea();
		//AddressTextArea.setWrapStyleWord(true);
		AddressTextArea.setLineWrap(true);
		AddressTextArea.setRows(5);
		AddressTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		AddressTextArea.setBounds(173, 369, 288, 100);
		contentPane.add(AddressTextArea);
		
		
		
		JLabel birthYearLabel = new JLabel("Năm Sinh: ");
		birthYearLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		birthYearLabel.setBounds(39, 468, 114, 45);
		contentPane.add(birthYearLabel);
		
		birthYearTextField = new JTextField();
		birthYearTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent eve) {
				char k = eve.getKeyChar();
				if(!(Character.isDigit(k) || (k==KeyEvent.VK_BACK_SPACE) || k==KeyEvent.VK_DELETE))
				{
					eve.consume();
				}
			}
		});
		birthYearTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		birthYearTextField.setColumns(10);
		birthYearTextField.setBounds(172, 479, 124, 26);
		contentPane.add(birthYearTextField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(507, 101, 1402, 811);
		contentPane.add(scrollPane);
		
		stTable = new JTable();
		stTable.setBorder(new SoftBevelBorder(BevelBorder.RAISED, SystemColor.info, SystemColor.activeCaption, null, null));
		stTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = stTable.getSelectedRow();
				TableModel model = stTable.getModel();
				String mssv=(model.getValueAt(row, 0).toString());
				String ten=(model.getValueAt(row, 1).toString());
				String gt=(model.getValueAt(row, 2).toString());
				String dc=(model.getValueAt(row, 3).toString());
				String lop=(model.getValueAt(row, 4).toString());
				String ns=(model.getValueAt(row, 5).toString());
				String gpa=(df.format(model.getValueAt(row, 6)).toString());
				
				mssvTextField.setText(mssv);
				mssvTextField.setEditable(false);
				NameTextField.setText(ten);
				if(gt.equals("Nữ"))
				{
					rdbtnFemaleButton.setSelected(true);
				}
				if(gt.equals("Nam"))
				{
					rdbtnMaleButton.setSelected(true);
				}
				AddressTextArea.setText(dc);
				comboBox.setSelectedItem(lop);
				birthYearTextField.setText(ns);
				GPAtextField.setText(gpa);
				
				Updatebtn.setEnabled(true);
				Deletebtn.setEnabled(true);
				Addbtn.setEnabled(false);
			}
		});
		//stTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		stTable.setRowSelectionAllowed(true);
		JTableHeader tableHeader = stTable.getTableHeader();
		tableHeader.setFont(new Font("Times New Roman", Font.BOLD, 24));
		stTable.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		stTable.setRowHeight(30);
		stTable.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"MSSV", "Họ & Tên", "Giới tính", "Địa chỉ", "Lớp", "Năm sinh", "GPA"}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Byte.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		stTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		stTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		stTable.getColumnModel().getColumn(2).setPreferredWidth(50);
		stTable.getColumnModel().getColumn(3).setPreferredWidth(300);
		stTable.getColumnModel().getColumn(4).setPreferredWidth(150);
		stTable.getColumnModel().getColumn(5).setPreferredWidth(50);
		stTable.getColumnModel().getColumn(6).setPreferredWidth(50);
		//stTable.getColumnModel().getColumn(5).setPreferredWidth(15);
		scrollPane.setViewportView(stTable);
		
		
		
		//cập nhật/ sửa sinh viên
		Updatebtn.setEnabled(false);
		Updatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = sqlc.getConnection();
					StudentDAO std = new StudentDAO();
					//int row = stTable.getSelectedRow();
					//String value=(stTable.getModel().getValueAt(row, 0).toString());
					
					
					Student st= new Student(mssvTextField.getText(),NameTextField.getText(),buttonGroup.getSelection().getActionCommand(),AddressTextArea.getText(),(String)comboBox.getSelectedItem(),Integer.parseInt(birthYearTextField.getText()),Double.parseDouble(GPAtextField.getText()));
					std.update(st);
					tbl.setRowCount(0);
					tableUpdate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					if(e.toString().contains("java.lang.ArrayIndexOutOfBoundsException"))
					{
						JLabel mess = new JLabel("Chưa chọn sinh viên để sửa");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					if(e.toString().contains("For input string"))
					{
						JLabel mess = new JLabel("Chưa chọn sinh viên để sửa");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					else
					{
						JLabel mess = new JLabel("Thông tin nhập không đúng cách. Cụ thể lỗi: "+e.getMessage());
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
		});
		Updatebtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Updatebtn.setBounds(177, 540, 114, 45);
		contentPane.add(Updatebtn);
		
		
		//xóa sinh viên
		Deletebtn.setEnabled(false);
		Deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = sqlc.getConnection();
					StudentDAO std = new StudentDAO();
					int row = stTable.getSelectedRow();
					String value=(stTable.getModel().getValueAt(row, 0).toString());
					
					int answer = JOptionPane.showConfirmDialog(frame, "Bạn có chắc là muốn xóa sinh viên này không?", "CẢNH BÁO", JOptionPane.YES_NO_OPTION);
					
					if (answer == JOptionPane.YES_OPTION) 
					{
						
						//String query="DELETE FROM STUDENTS WHERE ID="+value;
						//String value=mssvTextField.getText();
						std.delete(value);
						JLabel mess = new JLabel("Đã xóa 1 sinh viên");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess);
						functionReset();
						
					} else if(answer == JOptionPane.NO_OPTION) {
					  	// do something else
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if(e1.toString().contains("java.lang.ArrayIndexOutOfBoundsException"))
					{
						JLabel mess = new JLabel("Chưa chọn sinh viên để xóa");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					if(e1.toString().contains("Null"))
					{
						JLabel mess = new JLabel("Chưa chọn sinh viên để xóa");
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					else
					{
						JLabel mess = new JLabel("Xóa không đúng cách. Cụ thể lỗi: "+e1);
						mess.setFont(new Font("Arial", Font.BOLD, 18));
						JOptionPane.showMessageDialog(frame, mess, "LỖI", JOptionPane.PLAIN_MESSAGE);
					}
					//JOptionPane.showMessageDialog(frame, e1, "LỖI", JOptionPane.PLAIN_MESSAGE);

				}
				
			}
		});
		Deletebtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		Deletebtn.setBounds(319, 540, 114, 45);
		contentPane.add(Deletebtn);
		genderLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		genderLabel.setBounds(39, 200, 113, 45);
		
		contentPane.add(genderLabel);
		buttonGroup.add(rdbtnFemaleButton);
		rdbtnFemaleButton.setSelected(true);
		rdbtnFemaleButton.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		rdbtnFemaleButton.setBounds(252, 212, 64, 21);
		
		contentPane.add(rdbtnFemaleButton);
		buttonGroup.add(rdbtnMaleButton);
		rdbtnMaleButton.setSelected(true);
		rdbtnMaleButton.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		rdbtnMaleButton.setBounds(179, 212, 71, 21);
		
		contentPane.add(rdbtnMaleButton);
		
		rdbtnMaleButton.setActionCommand("Nam");
		rdbtnFemaleButton.setActionCommand("Nữ");
		
		idSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				/*String query=idSearchField.getText().toLowerCase();
				
				TableRowSorter<DefaultTableModel> t = new TableRowSorter<DefaultTableModel>(tbl);
				stTable.setRowSorter(t);
				t.setRowFilter(RowFilter.regexFilter(query));*/
				//tbl.setRowCount(0); //reset table
				
				//reset textFields
				mssvTextField.setText("");
				NameTextField.setText("");
				rdbtnFemaleButton.setSelected(false);
				rdbtnMaleButton.setSelected(false);
				AddressTextArea.setText("");
				comboBox.setSelectedIndex(0);
				birthYearTextField.setText("");
				GPAtextField.setText("");
				
				try {
					Connection con = sqlc.getConnection();
					PreparedStatement pst = con.prepareStatement("SELECT * FROM Students WHERE ID=?");
					pst.setString(1, idSearchField.getText());
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						String ID = rs.getString("ID");
						
						String name = rs.getString("Name");
						
						String gender = rs.getString("Gender");
						
						String address = rs.getString("Address");
						
						String Class = rs.getString("Class");
						
						String birthYear = rs.getString("birthYear");
						
						String gpa = rs.getString("GPA");
						
						mssvTextField.setText(ID);
						mssvTextField.setEditable(false);
						NameTextField.setText(name);
						if(gender.equals("Nữ"))
						{
							rdbtnFemaleButton.setSelected(true);
						}
						if(gender.equals("Nam"))
						{
							rdbtnMaleButton.setSelected(true);
						}
						AddressTextArea.setText(address);
						comboBox.setSelectedItem(Class);
						birthYearTextField.setText(birthYear);
						GPAtextField.setText(gpa);
						
						
						//result.add(new Student(ID, Name, Gender, Address, Class, BirthYear, GPA));
					}
					
					
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
				sqlc.closeConnection();
				
				tbl.setRowCount(0); //reset table
				tableUpdateFiltered("SELECT * FROM Students WHERE Id LIKE N'%"+idSearchField.getText()+"%'"); //update table based on filter
				Updatebtn.setEnabled(true);
				Deletebtn.setEnabled(true);
			}
		});
		idSearchField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		idSearchField.setColumns(10);
		idSearchField.setBounds(648, 66, 235, 36);
		
	
		contentPane.add(idSearchField);
		ClearField.setBackground(new Color(204, 0, 0));
		ClearField.setForeground(Color.WHITE);
		FilteredTitle.setVisible(false);
		ClearField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				functionReset();
			}
		});
		ClearField.setFont(new Font("Times New Roman", Font.BOLD, 24));
		ClearField.setBounds(319, 479, 114, 45);
		SearchLabel.setToolTipText("Refresh chương trình về trạng thái ban đầu");
		
		SearchLabel.setVisible(false);
		idSearchField.setVisible(false);
		nameSearchField.setVisible(false);
		GenderBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = GenderBox.getSelectedItem().toString();
				tbl.setRowCount(0); //reset table
				tableUpdateFiltered("SELECT * FROM Students WHERE Gender=N'"+selected+"'"); //update table based on filter
			}
		});
		GenderBox.setVisible(false);
		
		contentPane.add(ClearField);
		SearchLabel.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		SearchLabel.setBounds(893, 65, 157, 36);
		
		contentPane.add(SearchLabel);
		nameSearchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//tbl.setRowCount(0); //reset table
				
				//reset textFields
				mssvTextField.setText("");
				NameTextField.setText("");
				rdbtnFemaleButton.setSelected(false);
				rdbtnMaleButton.setSelected(false);
				AddressTextArea.setText("");
				comboBox.setSelectedIndex(0);
				birthYearTextField.setText("");
				GPAtextField.setText("");
				
				try {
					Connection con = sqlc.getConnection();
					PreparedStatement pst = con.prepareStatement("SELECT * FROM Students WHERE Name=?");
					pst.setString(1, nameSearchField.getText());
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						String ID = rs.getString("ID");
						
						String name = rs.getString("Name");
						
						String gender = rs.getString("Gender");
						
						String address = rs.getString("Address");
						
						String Class = rs.getString("Class");
						
						String birthYear = rs.getString("birthYear");
						
						String gpa = rs.getString("GPA");
						
						mssvTextField.setText(ID);
						mssvTextField.setEditable(false);
						NameTextField.setText(name);
						if(gender.equals("Nữ"))
						{
							rdbtnFemaleButton.setSelected(true);
						}
						if(gender.equals("Nam"))
						{
							rdbtnMaleButton.setSelected(true);
						}
						AddressTextArea.setText(address);
						comboBox.setSelectedItem(Class);
						birthYearTextField.setText(birthYear);
						GPAtextField.setText(gpa);
						
						Updatebtn.setEnabled(true);
						Deletebtn.setEnabled(true);
						//result.add(new Student(ID, Name, Gender, Address, Class, BirthYear, GPA));
					}
					
					
						
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
				sqlc.closeConnection();
				
				tbl.setRowCount(0); //reset table
				tableUpdateFiltered("SELECT * FROM Students WHERE Name LIKE N'%"+nameSearchField.getText()+"%'"); //update table based on filter
			}
		});
		nameSearchField.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		nameSearchField.setColumns(10);
		nameSearchField.setBounds(648, 66, 235, 36);
		
		contentPane.add(nameSearchField);
		FilteredTitle.setBackground(Color.WHITE);
		FilteredTitle.setForeground(Color.BLUE);
		FilteredTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));
		FilteredTitle.setBounds(1014, 10, 457, 45);
		
		contentPane.add(FilteredTitle);
		
	
		GenderBox.setModel(new DefaultComboBoxModel(new String[] {"Chưa chọn", "Nam", "Nữ"}));
		GenderBox.setToolTipText("Chọn giới tính để lọc sinh viên theo giới tính đó");
		GenderBox.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GenderBox.setBounds(648, 65, 135, 37);
		
		contentPane.add(GenderBox);
		
		//ấn tìm kiếm
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchLabel.setVisible(true);
				idSearchField.setVisible(true);
				tbl.setRowCount(0);
				tableUpdate();
				
				FilteredTitle.setVisible(false);
				SearchByCriteriaJCB.setVisible(true);
				SearchByCriteriaJCB.setSelectedIndex(0);
				
				mssvTextField.setText("");
				mssvTextField.setEditable(false);
				NameTextField.setText("");
				rdbtnFemaleButton.setSelected(false);
				rdbtnMaleButton.setSelected(false);
				AddressTextArea.setText("");
				comboBox.setSelectedIndex(0);
				birthYearTextField.setText("");
				GPAtextField.setText("");
				searchBtn.setVisible(false);
				clickRefreshbtn.setVisible(false);
				Updatebtn.setEnabled(false);
				Deletebtn.setEnabled(false);
				Addbtn.setEnabled(false);
				
				hideSearchBtn.setVisible(true);
			}
		});
		searchBtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		searchBtn.setBounds(507, 66, 135, 36);
		contentPane.add(searchBtn);
		SearchByCriteriaJCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String selected = SearchByCriteriaJCB.getSelectedItem().toString();
				if(selected.equals("MSSV"))
				{
					idSearchField.setVisible(true);
					nameSearchField.setVisible(false);
					GenderBox.setVisible(false);
				}
				if(selected.equals("Họ & Tên"))
				{
					idSearchField.setVisible(false);
					nameSearchField.setVisible(true);
					GenderBox.setVisible(false);
				}
				if(selected.equals("Giới Tính"))
				{
					idSearchField.setVisible(false);
					nameSearchField.setVisible(false);
					GenderBox.setVisible(true);
				}
				mssvTextField.setText("");
				mssvTextField.setEditable(true);
				NameTextField.setText("");
				rdbtnFemaleButton.setSelected(false);
				rdbtnMaleButton.setSelected(false);
				AddressTextArea.setText("");
				comboBox.setSelectedIndex(0);
				birthYearTextField.setText("");
				GPAtextField.setText("");
				idSearchField.setText("");
				nameSearchField.setText("");
				GenderBox.setSelectedIndex(0);
				tbl.setRowCount(0);
				tableUpdate();
			}
		});
		SearchByCriteriaJCB.setFont(new Font("Times New Roman", Font.ITALIC, 24));
		SearchByCriteriaJCB.setModel(new DefaultComboBoxModel(new String[] {"MSSV", "Họ & Tên", "Giới Tính"}));
		SearchByCriteriaJCB.setBounds(1042, 66, 135, 36);
		SearchByCriteriaJCB.setVisible(false);
		
		contentPane.add(SearchByCriteriaJCB);
		
		//ấn hủy tìm kiếm
		hideSearchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mssvTextField.setText("");
				mssvTextField.setEditable(true);
				NameTextField.setText("");
				rdbtnFemaleButton.setSelected(false);
				rdbtnMaleButton.setSelected(false);
				AddressTextArea.setText("");
				comboBox.setSelectedIndex(0);
				birthYearTextField.setText("");
				GPAtextField.setText("");
				idSearchField.setText("");
				tbl.setRowCount(0);
				tableUpdate();
				clickRefreshbtn.setVisible(false);
				FilteredTitle.setVisible(false);
				SearchLabel.setVisible(false);
				idSearchField.setVisible(false);
				nameSearchField.setVisible(false);
				GenderBox.setVisible(false);
				SearchByCriteriaJCB.setVisible(false);
				
				Updatebtn.setEnabled(false);
				Deletebtn.setEnabled(false);
				Addbtn.setEnabled(true);
				
				hideSearchBtn.setVisible(false);
				searchBtn.setVisible(true);
			}
		});
		hideSearchBtn.setFont(new Font("Times New Roman", Font.BOLD, 24));
		hideSearchBtn.setBounds(507, 66, 135, 36);
		contentPane.add(hideSearchBtn);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(39, 532, 419, 8);
		contentPane.add(separator);
		toolBar.setBackground(SystemColor.activeCaption);
		toolBar.setBounds(0, 0, 296, 45);
		toolBar.setVisible(false);
		contentPane.add(toolBar);
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolBar.setVisible(false);
				toolBar.setEnabled(false);
			}
		});
		
		
		RankTitleJCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				functionReset();
				tbl.setRowCount(0);
				mssvTextField.setEditable(false);
				String rank = RankTitleJCB.getSelectedItem().toString();
				if(rank.equals("Xuất sắc"))
				{
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=9"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
					FilteredTitle.setVisible(true);
					FilteredTitle.setForeground(Color.BLUE);
					FilteredTitle.setText("Danh sách Sinh viên được Học bổng");
				}
				if(rank.equals("Giỏi"))
				{
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=8 AND GPA<9"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
					FilteredTitle.setVisible(true);
					FilteredTitle.setForeground(Color.CYAN);
					FilteredTitle.setText("Danh sách Sinh viên Giỏi");
				}
				if(rank.equals("Khá"))
				{
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=7 AND GPA<8"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
					FilteredTitle.setVisible(true);
					FilteredTitle.setForeground(Color.GREEN);
					FilteredTitle.setText("Danh sách Sinh viên Khá");
				}
				if(rank.equals("Trung Bình"))
				{
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=5 AND GPA<7"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
					FilteredTitle.setVisible(true);
					FilteredTitle.setForeground(Color.ORANGE);
					FilteredTitle.setText("Danh sách Sinh viên Trung Bình");
				}
				if(rank.equals("Yếu"))
				{
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA<5"); //update table based on filter
					clickRefreshbtn.setVisible(true); //show the tip
					FilteredTitle.setVisible(true);
					FilteredTitle.setForeground(Color.BLACK);
					FilteredTitle.setText("Danh sách Sinh viên Yếu");
				}
				if(rank.equals("Tất cả"))
				{
					tableUpdateFiltered("SELECT * FROM Students"); //update table based on filter
					clickRefreshbtn.setVisible(false); //show the tip
					FilteredTitle.setVisible(false);
				}
			}
		});
		RankLabel.setForeground(SystemColor.desktop);
		toolBar.add(RankLabel);
		RankLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		RankTitleJCB.setFont(new Font("Tahoma", Font.PLAIN, 24));
		RankTitleJCB.setModel(new DefaultComboBoxModel(new String[] {"Tất cả", "Xuất sắc", "Giỏi", "Khá", "Trung Bình", "Yếu"}));
		toolBar.add(RankTitleJCB);
		toolBar.add(btnX);
		btnX.setForeground(Color.WHITE);
		btnX.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnX.setBackground(Color.RED);
		
		toolBar2.setBackground(SystemColor.info);
		toolBar2.setBounds(319, 0, 440, 45);
		toolBar2.setVisible(false);
		
		contentPane.add(toolBar2);
		toolBar2.add(ListingLabel);
		ListingLabel.setBackground(SystemColor.menuText);
		ListingLabel.setForeground(SystemColor.menuText);
		ListingLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		listingJCB.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
				String select = listingJCB.getSelectedItem().toString();
				if(select.equals("Tỉ lệ nam : nữ"))
				{
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE Gender=N'Nam'");
					int malecount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE Gender=N'Nữ'");
					int femalecount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdate();
					DefaultPieDataset pieDataset = new DefaultPieDataset();
					pieDataset.setValue("Nam", malecount);
					pieDataset.setValue("Nữ", femalecount);
					JFreeChart chart = ChartFactory.createPieChart("Tỉ lệ Nam : Nữ", pieDataset, true, true, true);
					ChartFrame frame = new ChartFrame("Sơ đồ tròn Tỉ lệ Nam : Nữ", chart);
					frame.setVisible(true);
					frame.setSize(600, 800);
				}
				if(select.equals("Ngành theo số sinh viên"))
				{
					int i;
					int[] count = new int[100];
					for(i=0;i<nganh.length;i++)
					{
						tbl.setRowCount(0);
						tableUpdateFiltered("SELECT * FROM Students WHERE CLASS=N'"+nganh[i]+"'");
						if(stTable.getRowCount()==0)
						{
							count[i]=0;
						}
						if(stTable.getRowCount()!=0)
						{
							count[i]=stTable.getRowCount();
						}
						
					}
					
					tbl.setRowCount(0);
					tableUpdate();
					
					DefaultPieDataset pieDataset = new DefaultPieDataset();
					for(i=0;i<nganh.length;i++)
					{
						pieDataset.setValue(nganh[i], count[i]);
					}
					for(i=0;i<nganh.length;i++)
					{
						System.out.print(count[i]+" ngành "+i+" ");
					}
					
					JFreeChart chart = ChartFactory.createPieChart("Tỉ lệ Ngành theo số sinh viên", pieDataset, true, true, true);					
					ChartFrame frame = new ChartFrame("Sơ đồ tròn Ngành theo số sinh viên", chart);
					frame.setVisible(true);
					frame.setSize(600, 800);
					
				}
				if(select.equals("Xếp loại"))
				{
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA<5");
					int weakcount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=5 AND GPA<7");
					int avgcount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=7 AND GPA<8");
					int closecount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=8 AND GPA<9");
					int goodcount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdateFiltered("SELECT * FROM Students WHERE GPA>=9");
					int outstandingcount=stTable.getRowCount();
					tbl.setRowCount(0);
					tableUpdate();
					DefaultPieDataset pieDataset = new DefaultPieDataset();
					pieDataset.setValue("Yếu (<5)", weakcount);
					pieDataset.setValue("Trung Bình (>=5)", avgcount);
					pieDataset.setValue("Khá (>=7)", closecount);
					pieDataset.setValue("Giỏi (>=8)", goodcount);
					pieDataset.setValue("Xuất sắc (>=9)", outstandingcount);
					JFreeChart chart = ChartFactory.createPieChart("Tỉ lệ Xếp loại", pieDataset, true, true, true);
					ChartFrame frame = new ChartFrame("Sơ đồ tròn Tỉ Xếp loại", chart);
					frame.setVisible(true);
					frame.setSize(600, 800);
					
				}
				if(select.equals("Điểm"))
				{ 
					int i;
					int[] count = new int[100];
					for(i=0;i<11;i++)
					{
						int j=i+1;
						tbl.setRowCount(0);
						tableUpdateFiltered("SELECT * FROM Students WHERE GPA>="+i+" AND GPA <"+j);
						if(stTable.getRowCount()==0)
						{
							count[i]=0;
						}
						if(stTable.getRowCount()!=0)
						{
							count[i]=stTable.getRowCount();
						}
					}
					
					tbl.setRowCount(0);
					tableUpdate();
					
					DefaultCategoryDataset Dataset = new DefaultCategoryDataset();
					for(i=0;i<11;i++)
					{
						Dataset.setValue(count[i], Integer.toString(i), Integer.toString(i));
					}
					for(i=0;i<11;i++)
					{
						System.out.print(count[i]+" điểm "+i+" ");
					}
					
					JFreeChart chart = ChartFactory.createBarChart3D("Thống kê điểm" , "Loại điểm", "Số lượng", Dataset, PlotOrientation.VERTICAL, true, true, true);
					ChartFrame frame = new ChartFrame("Sơ đồ cột Tỉ lệ Điểm", chart);
					frame.setVisible(true);
					frame.setSize(600, 800);
					
				}
				
			}
		});
		listingJCB.setModel(new DefaultComboBoxModel(new String[] {"None", "Ngành theo số sinh viên", "Tỉ lệ nam : nữ", "Điểm", "Xếp loại"}));
		listingJCB.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		toolBar2.add(listingJCB);
		btnX2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toolBar2.setVisible(false);
			}
		});
		btnX2.setForeground(Color.WHITE);
		btnX2.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnX2.setBackground(Color.RED);
		
		toolBar2.add(btnX2);
		hideSearchBtn.setVisible(false);
		
		//System.out.println(buttonGroup.getSelection().getActionCommand());
	}
	public String getIDTextField() {
		return mssvTextField.getText();
	}
	public String getNameTextField() {
		return NameTextField.getText();
	}
	public String getGPAtextField() {
		return GPAtextField.getText();
	}
	public String getClassTextField() {
		return ((String) comboBox.getSelectedItem());
	}
	public String getAddressTextArea() {
		return AddressTextArea.getText();
	}
	public String getBirthYearTextField() {
		return birthYearTextField.getText();
	}
	
	public List<Student> getAll() {
		List<Student> result = new ArrayList<Student>();
		
		try {
			Connection con = sqlc.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM Students");
			//
			while (rs.next()) {
				String ID = rs.getString("ID");
				String Name = rs.getString("Name");
				String Gender = rs.getString("Gender");
				String Address = rs.getString("Address");
				String Class = rs.getString("Class");
				int BirthYear = rs.getInt("BirthYear");
				float GPA = rs.getFloat("GPA");

				
				result.add(new Student(ID, Name, Gender, Address, Class, BirthYear, GPA));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sqlc.closeConnection();
		return result;
	}
	
	public void tableUpdate() //Update table's datas
	{
		List<Student> stl = getAll();
		tbl = (DefaultTableModel)stTable.getModel();
		Object[] row = new Object[7];
		for(int i=0;i<stl.size();i++)
		{
			row[0]=stl.get(i).getId();
			row[1]=stl.get(i).getName();
			row[2]=stl.get(i).getGender();
			row[3]=stl.get(i).getAddress();
			row[4]=stl.get(i).getClassStudent();
			row[5]=stl.get(i).getAge();
			row[6]=stl.get(i).getGpa();
			tbl.addRow(row);
		}
	}
	
	
	public List<Student> getFilteredInfo(String t) {
		List<Student> result = new ArrayList<Student>();
		
		try {
			Connection con = sqlc.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(t);
			while (rs.next()) {
				String ID = rs.getString("ID");
				String Name = rs.getString("Name");
				String Gender = rs.getString("Gender");
				String Address = rs.getString("Address");
				String Class = rs.getString("Class");
				int BirthYear = rs.getInt("BirthYear");
				float GPA = rs.getFloat("GPA");
				
				result.add(new Student(ID, Name, Gender, Address, Class, BirthYear, GPA));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sqlc.closeConnection();
		return result;
	}
	public void tableUpdateFiltered(String t) //Update table's datas
	{
		List<Student> stl = getFilteredInfo(t);
		tbl = (DefaultTableModel)stTable.getModel();
		Object[] row = new Object[7];
		for(int i=0;i<stl.size();i++)
		{
			row[0]=stl.get(i).getId();
			row[1]=stl.get(i).getName();
			row[2]=stl.get(i).getGender();
			row[3]=stl.get(i).getAddress();
			row[4]=stl.get(i).getClassStudent();
			row[5]=stl.get(i).getAge();
			row[6]=stl.get(i).getGpa();
			tbl.addRow(row);
		}
	}
	
	public void functionReset()
	{
		mssvTextField.setText("");
		mssvTextField.setEditable(true);
		NameTextField.setText("");
		rdbtnFemaleButton.setSelected(false);
		rdbtnMaleButton.setSelected(false);
		AddressTextArea.setText("");
		comboBox.setSelectedIndex(0);
		birthYearTextField.setText("");
		GPAtextField.setText("");
		idSearchField.setText("");
		tbl.setRowCount(0);
		tableUpdate();
		clickRefreshbtn.setVisible(false);
		FilteredTitle.setVisible(false);
		SearchLabel.setVisible(false);
		idSearchField.setVisible(false);
		nameSearchField.setVisible(false);
		GenderBox.setVisible(false);
		SearchByCriteriaJCB.setVisible(false);
		hideSearchBtn.setVisible(false);
		searchBtn.setVisible(true);
		
		Updatebtn.setEnabled(false);
		Deletebtn.setEnabled(false);
		Addbtn.setEnabled(true);
	}
	
	public void exportExcel(JTable table) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		int i = chooser.showSaveDialog(chooser);
		if (i == JFileChooser.APPROVE_OPTION) 
		{
			File file = chooser.getSelectedFile();
			try {
					//FileWriter out = new FileWriter(file + ".xls");
					//BufferedWriter bwrite = new BufferedWriter(out);
					
					FileOutputStream fileOutputStream = new FileOutputStream(file + ".txt");
		            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
		            BufferedWriter bw = new BufferedWriter(writer);
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					// ten Cot
					for (int j = 0; j < table.getColumnCount(); j++) {
						bw.write(model.getColumnName(j) + "\t");
					}
					bw.write("\n");
					// Lay du lieu dong
					for (int j = 0; j < table.getRowCount(); j++) 
					{
						for (int k = 0; k < table.getColumnCount(); k++) 
						{
							bw.write(model.getValueAt(j, k) + "\t");
						}
						bw.write("\n");
					}
					bw.close();
					JOptionPane.showMessageDialog(null, "Lưu file thành công!");
				} catch (Exception e2) 
				{
					JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
				}
		}
	}
}
