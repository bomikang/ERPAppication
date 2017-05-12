package kr.or.dgit.bigdata.ui.method;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.or.dgit.bigdata.dto.Department;
import kr.or.dgit.bigdata.dto.Employee;
import kr.or.dgit.bigdata.dto.Title;
import kr.or.dgit.bigdata.service.DepartmentService;
import kr.or.dgit.bigdata.service.EmployeeService;
import kr.or.dgit.bigdata.service.TitleService;
import kr.or.dgit.bigdata.ui.DialogEmp;

public class TableEmp extends AbstractTable {

	public TableEmp(DialogEmp dialog) {
		super(dialog);
	}
	
	@Override
	protected void updateMode(int eno) {
		Employee emp = empService.selectOneEmp(eno);
		
		dlgEmp.tfEno.setText("E"+eno);
		dlgEmp.tfEname.setText(emp.getEname());
		
		Title t = titleService.selectOneTitle(emp.getTitle().getTcode());
		dlgEmp.cmbTitle.setSelectedItem(t.getTname());
		
		dlgEmp.spnSalary.setValue(emp.getSalary());
		if (emp.getGender() == 1) {
			dlgEmp.rdoFemale.setSelected(true);
			dlgEmp.rdoMale.setSelected(false);
		}else{
			dlgEmp.rdoFemale.setSelected(false);
			dlgEmp.rdoMale.setSelected(true);
		}
		
		Department d = deptService.selectOneDept(emp.getDept().getDcode());
		dlgEmp.cmbDept.setSelectedItem(Employee.getStrDept(d));
		
		dlgEmp.tfJoinDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(emp.getJoindate()));
		
		dlgEmp.btnAdd.setText("수정");
	}

	@Override
	protected void deleteMode(int eno) {
		empService.deleteEmp(eno);
		JOptionPane.showMessageDialog(null, "해당 직원은 삭제되었습니다.");
	}
	
	@Override
	public void reload() {
		DefaultTableModel model = new DefaultTableModel(getRowData(), new String[] {"번호", "사원명", "직책", "급여", "성별", "부서", "입사일"});
		table.setModel(model);
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 4, 5, 6);
		tableCellAlignment(SwingConstants.RIGHT, 3);
		tableSetWidth(90, 120, 90, 120, 60, 140, 130);
	}

	@Override
	public String[][] getRowData() {
		List<Employee> empList = empService.selectAllEmp();
		
		String[][] arrDatas = new String[empList.size()][];
		for (int i = 0; i < empList.size(); i++) {
			Employee emp = empList.get(i);
			emp.setDept(deptService.selectOneDept(emp.getDept().getDcode()));
			emp.setTitle(titleService.selectOneTitle(emp.getTitle().getTcode()));
			arrDatas[i] = emp.toArray();
		}
		
		return arrDatas;
	}
	
	/*
	
	private void menuClick(JMenuItem item) {
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				int eno = 0;
				if (table.getSelectedRow() >= 0) {
					String strEno = (String) model.getValueAt(table.getSelectedRow(), 0);
					strEno = strEno.substring(1, strEno.length());
					eno = Integer.parseInt(strEno);
				}
				
				if (e.getSource() == miUpdate) {
					Employee emp = empService.selectOneEmp(eno);
					
					dlgEmp.tfEno.setText("E"+eno);
					dlgEmp.tfEname.setText(emp.getEname());
					
					Title t = titleService.selectOneTitle(emp.getTitle().getTcode());
					dlgEmp.cmbTitle.setSelectedItem(t.getTname());
					
					dlgEmp.spnSalary.setValue(emp.getSalary());
					if (emp.getGender() == 1) {
						dlgEmp.rdoFemale.setSelected(true);
						dlgEmp.rdoMale.setSelected(false);
					}else{
						dlgEmp.rdoFemale.setSelected(false);
						dlgEmp.rdoMale.setSelected(true);
					}
					
					Department d = deptService.selectOneDept(emp.getDept().getDcode());
					dlgEmp.cmbDept.setSelectedItem(Employee.getStrDept(d));
					
					dlgEmp.tfJoinDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(emp.getJoindate()));
					
					dlgEmp.btnAdd.setText("수정");
				}else if(e.getSource() == miDelete){
					empService.deleteEmp(eno);
					JOptionPane.showMessageDialog(null, "해당 직원은 삭제되었습니다.");
				}
				
				reload();
			}
		});
	}//팝업메뉴 클릭
	*/
}
