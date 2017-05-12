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
import kr.or.dgit.bigdata.ui.DialogEmp;

public class ButtonEventEmp extends AbstractButtonEvent{
	private DialogEmp dlgEmp;
	private JButton button;
	private DepartmentService deptService;
	private TitleService titleService;
	private EmployeeService empService;

	public ButtonEventEmp(JButton button, DialogEmp dialogEmp) {
		this.button = button;
		this.dlgEmp = dialogEmp;
		this.deptService = DepartmentService.getInstance();
		this.titleService = TitleService.getInstance();
		this.empService = EmployeeService.getInstance();
	}

	@Override
	public void updateObject() {
		if ( !emptyCheck(dlgEmp.tfEname, "사원명을 입력해주세요") ) {
			return;
		}
		empService.updateEmp(getEmployee());
		super.updateObject();
	}

	@Override
	public void initObject() {
		String lastEno = Employee.getStrEno(empService.getLastEno());
		dlgEmp.tfEno.setText(lastEno);
		dlgEmp.tfEname.setText("");
		dlgEmp.spnSalary.setValue(1500000);
		dlgEmp.cmbDept.setSelectedIndex(0);
		dlgEmp.rdoMale.setSelected(true);
		dlgEmp.rdoFemale.setSelected(false);
		dlgEmp.tfJoinDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		dlgEmp.cmbTitle.setSelectedIndex(0);
		
		dlgEmp.btnAdd.setText("추가");
		dlgEmp.tablePanel.reload();
	}

	@Override
	public void addObject() {
		if ( !emptyCheck(dlgEmp.tfEname, "사원명을 입력해주세요") ) {
			return;
		}
		empService.insertEmp(getEmployee());
		super.addObject();
	}
	
	private Employee getEmployee(){
		String strEno = dlgEmp.tfEno.getText();
		strEno = strEno.substring(1, strEno.length());
		int eno = Integer.parseInt(strEno);
		
		String ename = dlgEmp.tfEname.getText();
		int salary = Integer.parseInt(dlgEmp.spnSalary.getValue().toString());
		
		String deptName = (String)dlgEmp.cmbDept.getSelectedItem();
		deptName = deptName.substring(0, deptName.indexOf("("));
		List<Department> deptList = deptService.selectAllDept();
		Department dept = null;
		for (Department d : deptList) {
			if (deptName.equals(d.getDname())) {
				dept = d;
			}
		}
		
		int gender = 0;
		if (dlgEmp.rdoFemale.isSelected()) {
			gender = 1;
		}
		
		String[] splDate = dlgEmp.tfJoinDate.getText().split("-");
		int year = Integer.parseInt(splDate[0]);
		int month = Integer.parseInt(splDate[1]);
		int dayOfMonth = Integer.parseInt(splDate[2]);
		
		GregorianCalendar gc = new GregorianCalendar(year, month-1, dayOfMonth);
		Date joindate = gc.getTime();
		
		String titleName = (String)dlgEmp.cmbTitle.getSelectedItem();
		List<Title> titleList = titleService.selectAllTitle();
		Title title = null;
		for (Title t : titleList) {
			if (titleName.equals(t.getTname())) {
				title = t;
			}
		}
		return new Employee(eno, ename, salary, dept, gender, joindate, title);
	}//getEmployee
}
