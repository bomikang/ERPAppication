package kr.or.dgit.bigdata.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.dto.Department;
import kr.or.dgit.bigdata.service.DepartmentService;
import kr.or.dgit.bigdata.ui.method.ButtonEventDept;
import kr.or.dgit.bigdata.ui.method.TableDept;
import java.awt.Color;

public class DialogDept extends JDialog {
	private JPanel contentPane;
	public JTextField tfDcode;
	public JTextField tfDname;
	public JTextField tfFloor;
	public TableDept tablePanel;
	public JButton btnAdd;
	private DepartmentService deptService;
	
	public DialogDept() {
		deptService = new DepartmentService();
		
		setTitle("부서관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 100, 543, 311);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(1, 1, 10, 10));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBounds(25, 5, 419, 99);
		panel.setLayout(new GridLayout(0, 1, 0, 10));
		contentPane.add(panel);
		
		JPanel panelInput = new JPanel();
		panelInput.setBackground(new Color(255, 255, 255));
		panelInput.setBorder(new EmptyBorder(0, 30, 0, 100));
		panelInput.setLayout(null);
		panel.add(panelInput);
		
		JLabel lblDcode = new JLabel("번호");
		lblDcode.setBounds(30, 0, 129, 23);
		lblDcode.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblDcode);
		
		tfDcode = new JTextField();
		tfDcode.setBounds(169, 0, 162, 23);
		tfDcode.setEditable(false);
		tfDcode.setColumns(10);
		panelInput.add(tfDcode);
		
//		set number
		String lastDcode = Department.getStrDcode(deptService.getLastDcode());
		tfDcode.setText(lastDcode);
		
		JLabel lblDname = new JLabel("부서명");
		lblDname.setBounds(30, 28, 129, 23);
		lblDname.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblDname);
		
		tfDname = new JTextField();
		tfDname.setBounds(169, 28, 162, 23);
		tfDname.setColumns(10);
		panelInput.add(tfDname);
		
		JLabel lblFloor = new JLabel("위치");
		lblFloor.setBounds(30, 56, 129, 23);
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblFloor);
		
		tfFloor = new JTextField();
		tfFloor.setBounds(169, 56, 162, 23);
		tfFloor.setColumns(10);
		panelInput.add(tfFloor);
		
		JPanel panelButton = new JPanel();
		panelButton.setBackground(new Color(255, 255, 255));
		panelButton.setBounds(12, 114, 503, 33);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(panelButton);
		
		btnAdd = new JButton("추가");
		btnAdd.setBackground(new Color(255, 204, 102));
		btnAdd.setHorizontalAlignment(SwingConstants.RIGHT);
		panelButton.add(btnAdd);
		
		JButton btnCancel = new JButton("취소");
		btnCancel.setBackground(new Color(255, 204, 102));
		panelButton.add(btnCancel);
		
		tablePanel = new TableDept(this);
		tablePanel.setBounds(12, 157, 503, 105);
		contentPane.add(tablePanel);
		
//		button event listener
		btnAdd.addActionListener(new ButtonEventDept(btnAdd, this));
		btnCancel.addActionListener(new ButtonEventDept(btnCancel, this));
		
		setModal(true);
		setVisible(true);
	}
}
