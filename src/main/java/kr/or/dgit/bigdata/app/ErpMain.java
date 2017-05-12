package kr.or.dgit.bigdata.app;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.ui.DialogDept;
import kr.or.dgit.bigdata.ui.DialogEmp;
import kr.or.dgit.bigdata.ui.DialogTitle;

import java.awt.Color;

public class ErpMain extends JFrame implements ActionListener{
	private JPanel contentPane;
	private JButton btnEmp;
	private JButton btnDept;
	private JButton btnTitle;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErpMain frame = new ErpMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ErpMain() {
		setTitle("대구아이티ERP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 130);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.setLayout(new GridLayout(0, 3, 20, 0));
		setContentPane(contentPane);
		
		btnEmp = new JButton("사원관리");
		btnEmp.setBackground(Color.WHITE);
		btnEmp.addActionListener(this);
		contentPane.add(btnEmp);
		
		btnDept = new JButton("부서관리");
		btnDept.setBackground(Color.WHITE);
		btnDept.addActionListener(this);
		contentPane.add(btnDept);
		
		btnTitle = new JButton("직책관리");
		btnTitle.setBackground(Color.WHITE);
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEmp) {
			new DialogEmp();
		}else if(e.getSource() == btnDept){
			new DialogDept();
		}else if(e.getSource() == btnTitle){
			new DialogTitle();
		}
	}
}
