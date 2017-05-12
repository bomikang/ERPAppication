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

import kr.or.dgit.bigdata.dto.Title;
import kr.or.dgit.bigdata.service.TitleService;
import kr.or.dgit.bigdata.ui.method.ButtonEventTitle;
import kr.or.dgit.bigdata.ui.method.TableTitle;

import java.awt.BorderLayout;
import java.awt.Color;

public class DialogTitle extends JDialog {
	private JPanel contentPane;
	public JTextField tfTcode;
	public JTextField tfTname;
	public TableTitle tablePanel;
	private TitleService titleService;
	public JButton btnAdd;

	public DialogTitle() {
		titleService = TitleService.getInstance();
		
		setTitle("직책관리");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 100, 440, 294);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(1, 1, 10, 10));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setBounds(12, 5, 400, 73);
		panel.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel);
		
		JPanel panelInput = new JPanel();
		panelInput.setBackground(new Color(255, 255, 255));
		panelInput.setBorder(new EmptyBorder(0, 0, 0, 0));
		panelInput.setLayout(null);
		panel.add(panelInput);
		
		JLabel lblTcode = new JLabel("번호");
		lblTcode.setBounds(24, 0, 95, 21);
		lblTcode.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblTcode);
		
		tfTcode = new JTextField();
		tfTcode.setBounds(131, 0, 150, 21);
		tfTcode.setEditable(false);
		tfTcode.setColumns(10);
		panelInput.add(tfTcode);
		
//		set number
		String lastTcode = Title.getStrTcode(titleService.getLastTcode());
		tfTcode.setText(lastTcode);
		
		JLabel lblTname = new JLabel("직책명");
		lblTname.setBounds(21, 31, 95, 21);
		lblTname.setHorizontalAlignment(SwingConstants.RIGHT);
		panelInput.add(lblTname);
		
		tfTname = new JTextField();
		tfTname.setBounds(131, 31, 150, 21);
		tfTname.setColumns(10);
		panelInput.add(tfTname);
		
		JPanel panelButton = new JPanel();
		panelButton.setBackground(new Color(255, 255, 255));
		panelButton.setBounds(12, 88, 400, 33);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPane.add(panelButton);
		
		btnAdd = new JButton("추가");
		JButton btnCancel = new JButton("취소");
		btnAdd.setBackground(new Color(255, 204, 102));
		btnCancel.setBackground(new Color(255, 204, 102));
		panelButton.add(btnAdd);
		panelButton.add(btnCancel);
		
		tablePanel = new TableTitle(this);
		tablePanel.setBounds(12, 131, 400, 114);
		contentPane.add(tablePanel);
		
//		button event listener
		btnAdd.addActionListener(new ButtonEventTitle(btnAdd, this));
		btnCancel.addActionListener(new ButtonEventTitle(btnCancel, this));
		
		setModal(true);
		setVisible(true);
	}
}
