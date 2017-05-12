package kr.or.dgit.bigdata.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kr.or.dgit.bigdata.dto.Department;
import kr.or.dgit.bigdata.dto.Employee;
import kr.or.dgit.bigdata.dto.Title;
import kr.or.dgit.bigdata.service.DepartmentService;
import kr.or.dgit.bigdata.service.EmployeeService;
import kr.or.dgit.bigdata.service.TitleService;
import kr.or.dgit.bigdata.ui.method.ButtonEventEmp;
import kr.or.dgit.bigdata.ui.method.TableEmp;

import java.awt.BorderLayout;
import java.awt.Color;

public class DialogEmp extends JDialog {
	private TitleService titleService;
	private EmployeeService empService;
	private DepartmentService deptService;
	private JPanel contentPane;
	public JTextField tfEno;
	public JTextField tfEname;
	public JTextField tfJoinDate;
	public JSpinner spnSalary;
	public JComboBox cmbDept;
	public JRadioButton rdoMale;
	public JRadioButton rdoFemale;
	public JComboBox cmbTitle;
	public TableEmp tablePanel;
	public JButton btnAdd;

	public DialogEmp() {
		titleService = TitleService.getInstance();
		empService = EmployeeService.getInstance();
		deptService = DepartmentService.getInstance();
		
		setTitle("사원관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 100, 681, 539);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(1, 1, 10, 10));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBounds(25, 5, 555, 225);
		panel.setLayout(new GridLayout(0, 1, 0, 10));
		contentPane.add(panel);
		
		JPanel panelInput = new JPanel();
		panelInput.setBackground(Color.WHITE);
		panelInput.setBorder(new EmptyBorder(0, 30, 0, 100));
		panelInput.setLayout(new GridLayout(0, 2, 10, 5));
		panel.add(panelInput);
		
		JLabel lblEno = new JLabel("번호");
		lblEno.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblEno);
		
		tfEno = new JTextField();
		tfEno.setEditable(false);
		tfEno.setColumns(10);
		panelInput.add(tfEno);
		
//		set number
		String lastEno = Employee.getStrEno(empService.getLastEno());
		tfEno.setText(lastEno);
		
		JLabel lblEname = new JLabel("사원명");
		lblEname.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblEname);
		
		tfEname = new JTextField();
		tfEname.setColumns(10);
		panelInput.add(tfEname);
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblTitle);
		
//		set title
		cmbTitle = new JComboBox();
		List<Title> titleList = titleService.selectAllTitle();
		for (Title title : titleList) {
			cmbTitle.addItem(title.getTname());
		}
		panelInput.add(cmbTitle);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblSalary);
		
//		set salary
		spnSalary = new JSpinner();
		spnSalary.setModel(new SpinnerNumberModel(1500000, 1000000, 5000000, 100000));
		panelInput.add(spnSalary);
		
		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblGender);
		
		JPanel panelGender = new JPanel();
		panelGender.setBackground(Color.WHITE);
		panelGender.setLayout(new GridLayout(0, 2, 0, 0));
		panelInput.add(panelGender);
		
		rdoMale = new JRadioButton("남");
		rdoFemale = new JRadioButton("여");
		rdoMale.setBackground(Color.WHITE);
		rdoMale.setHorizontalAlignment(SwingConstants.CENTER);
		rdoFemale.setBackground(Color.WHITE);
		rdoFemale.setHorizontalAlignment(SwingConstants.CENTER);
		rdoMale.setSelected(true);
		panelGender.add(rdoMale);
		panelGender.add(rdoFemale);
		
//		gender change listener
		rdoMale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdoFemale.setSelected(false);
			}
		});
		rdoFemale.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdoMale.setSelected(false);
			}
		});
		
		JLabel lblDept = new JLabel("부서");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblDept);
		
//		set department name
		cmbDept = new JComboBox();
		List<Department> deptList = deptService.selectAllDept();
		for (Department dept : deptList) {
			cmbDept.addItem(Employee.getStrDept(dept));			
		}
		panelInput.add(cmbDept);
		
		JLabel lblJoinDate = new JLabel("입사일");
		lblJoinDate.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblJoinDate);
		
//		숫자와 backspace, delete 키만 허용
//		년도 입력 후, 월 입력 후 "-"가 붙도록 함
		tfJoinDate = new JTextField();
		tfJoinDate.setColumns(10);
		tfJoinDate.setText(Employee.getStrJoindate(new Date()));
		tfJoinDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if ( !(e.getKeyChar() >= KeyEvent.VK_0 && e.getKeyChar() <= KeyEvent.VK_9) 
						&& !(e.getKeyChar() == KeyEvent.VK_DELETE)
						&& !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
				if (tfJoinDate.getText().length() == 4) {
					tfJoinDate.setText(tfJoinDate.getText() + "-");
				}
				if (tfJoinDate.getText().length() == 7) {
					tfJoinDate.setText(tfJoinDate.getText() + "-");
				}
				if (tfJoinDate.getText().length() > 9) {
					e.consume();
				}
			}			
		});
		panelInput.add(tfJoinDate);
		
		JPanel panelButton = new JPanel();
		panelButton.setBackground(Color.WHITE);
		panelButton.setBounds(12, 240, 641, 33);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(panelButton);
		
		btnAdd = new JButton("추가");
		JButton btnCancel = new JButton("취소");
		
		btnAdd.setBackground(new Color(255, 204, 102));
		btnCancel.setBackground(new Color(255, 204, 102));
		panelButton.add(btnAdd);
		panelButton.add(btnCancel);
		
		tablePanel = new TableEmp(this);
		tablePanel.setBounds(12, 283, 641, 207);
		contentPane.add(tablePanel);
		
//		button event listener
		btnAdd.addActionListener(new ButtonEventEmp(btnAdd, this));
		btnCancel.addActionListener(new ButtonEventEmp(btnAdd, this));
		
		setModal(true);
		setVisible(true);
	}
}
