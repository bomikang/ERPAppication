package kr.or.dgit.bigdata.ui.method;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import kr.or.dgit.bigdata.dto.Department;
import kr.or.dgit.bigdata.dto.Employee;
import kr.or.dgit.bigdata.dto.Title;
import kr.or.dgit.bigdata.service.DepartmentService;
import kr.or.dgit.bigdata.service.EmployeeService;
import kr.or.dgit.bigdata.service.TitleService;
import kr.or.dgit.bigdata.ui.DialogTitle;

public class ButtonEventTitle extends AbstractButtonEvent{
	private DialogTitle dlgTitle;
	private JButton button;
	private TitleService titleService;

	public ButtonEventTitle(JButton button, DialogTitle dlgTitle) {
		this.button = button;
		this.dlgTitle = dlgTitle;
		titleService = TitleService.getInstance();
	}

	@Override
	public void updateObject() {
		if( !emptyCheck(dlgTitle.tfTname, "직책명을 입력하세요")){
			return;
		}
		titleService.updateTitle(getTitle());
		super.updateObject();
	}

	@Override
	public void initObject() {
		String lastTcode = Title.getStrTcode(titleService.getLastTcode());
		dlgTitle.tfTcode.setText(lastTcode);
		dlgTitle.tfTname.setText("");
		
		dlgTitle.btnAdd.setText("추가");
		dlgTitle.tablePanel.reload();
	}

	@Override
	public void addObject() {
		if( !emptyCheck(dlgTitle.tfTname, "직책명을 입력하세요")){
			return;
		}
		titleService.insertTitle(getTitle());
		super.addObject();
	}

	private Title getTitle(){
		String strTcode = dlgTitle.tfTcode.getText();
		strTcode = strTcode.substring(1, strTcode.length());
		int tcode = Integer.parseInt(strTcode);
		
		String tname = dlgTitle.tfTname.getText();
		
		return new Title(tcode, tname);
	}//getTitle
}
