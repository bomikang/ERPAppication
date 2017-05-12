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
import kr.or.dgit.bigdata.ui.DialogDept;

public class ButtonEventDept extends AbstractButtonEvent {
	private DialogDept dlgDept;
	private JButton button;
	private DepartmentService deptService;
	
	public ButtonEventDept(JButton button, DialogDept dlgDept) {
		this.button = button;
		this.dlgDept = dlgDept;
		deptService = DepartmentService.getInstance();
	}

	@Override
	public void updateObject() {
		if ( !emptyCheck(dlgDept.tfDname, "부서명을 입력해주세요") || !emptyCheck(dlgDept.tfFloor, "위치를 입력해주세요")){
			return;
		}
		deptService.updateDept(getDepartment());
		super.updateObject();
	}

	@Override
	public void initObject() {
		String lastDcode = Department.getStrDcode(deptService.getLastDcode());
		dlgDept.tfDcode.setText(lastDcode);
		dlgDept.tfDname.setText("");
		dlgDept.tfFloor.setText("");
		
		dlgDept.btnAdd.setText("추가");
		dlgDept.tablePanel.reload();
	}

	@Override
	public void addObject() {
		if ( !emptyCheck(dlgDept.tfDname, "부서명을 입력해주세요") || !emptyCheck(dlgDept.tfFloor, "위치를 입력해주세요")){
			return;
		}
		
		deptService.insertDept(getDepartment());
		super.addObject();
	}
	
	private Department getDepartment(){
		String strDcode = dlgDept.tfDcode.getText();
		strDcode = strDcode.substring(1, strDcode.length());
		int dcode = Integer.parseInt(strDcode);
		
		String dname = dlgDept.tfDname.getText();
		int floor = Integer.parseInt(dlgDept.tfFloor.getText());
		
		return new Department(dcode, dname, floor);
	}
}
