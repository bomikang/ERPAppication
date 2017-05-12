package kr.or.dgit.bigdata.app;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.setting.service.ExportSettingService;
import kr.or.dgit.setting.service.ImportSettingService;
import kr.or.dgit.setting.service.InitSettingService;
import kr.or.dgit.setting.service.ServiceSetting;
import java.awt.Color;

public class SettingMain extends JFrame implements ActionListener{
	private JPanel contentPane;
	private JButton btnInit;
	private JButton btnBackup;
	private JButton btnRestore;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SettingMain frame = new SettingMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public SettingMain() {
		setTitle("DB관리메뉴");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 100);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new GridLayout(0, 3, 10, 0));
		setContentPane(contentPane);
		
		btnInit = new JButton("초기화");
		btnInit.setBackground(Color.WHITE);
		btnInit.addActionListener(this);
		contentPane.add(btnInit);
		
		btnBackup = new JButton("백업");
		btnBackup.setBackground(Color.WHITE);
		btnBackup.addActionListener(this);
		contentPane.add(btnBackup);
		
		btnRestore = new JButton("복원");
		btnRestore.setBackground(Color.WHITE);
		btnRestore.addActionListener(this);
		contentPane.add(btnRestore);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ServiceSetting create = null;
		
		if (e.getSource() == btnInit) {
			create = new InitSettingService();
			JOptionPane.showMessageDialog(null, "초기화 완료");
		}else if (e.getSource() == btnBackup) {
			create = new ExportSettingService();
			JOptionPane.showMessageDialog(null, "데이터 백업 완료");
		}else if (e.getSource() == btnRestore) {
			create = new ImportSettingService();
			JOptionPane.showMessageDialog(null, "데이터 복원 완료");
		}
		create.initSetting();
	}

}
