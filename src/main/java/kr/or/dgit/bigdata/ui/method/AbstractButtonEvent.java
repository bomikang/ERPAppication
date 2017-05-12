package kr.or.dgit.bigdata.ui.method;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public abstract class AbstractButtonEvent implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("추가")) {
			addObject();
		}else if(e.getActionCommand().equals("취소")){
			initObject();
		}else if(e.getActionCommand().equals("수정")){
			updateObject();
		}
	}

	public void updateObject(){
		JOptionPane.showMessageDialog(null, "수정되었습니다");
		initObject();
	}
	public abstract void initObject();
	public void addObject(){
		JOptionPane.showMessageDialog(null, "등록되었습니다");
		initObject();
	}
	
	public boolean emptyCheck(JTextField tf, String msg){
		if (tf.getText().equals("")) {
			JOptionPane.showMessageDialog(null, msg);
			tf.requestFocus();
			return false;
		}else{
			return true;
		}
	}
}
