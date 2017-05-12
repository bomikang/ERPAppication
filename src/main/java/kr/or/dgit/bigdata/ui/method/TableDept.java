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
import kr.or.dgit.bigdata.ui.DialogDept;

public class TableDept extends AbstractTable{
	public TableDept(DialogDept dialogDept) {
		super(dialogDept);
	}
	
	@Override
	public void reload() {
		DefaultTableModel model = new DefaultTableModel(getRowData(), new String[] {"번호", "부서명", "위치"});
		table.setModel(model);
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);
		tableSetWidth(100, 150, 100);
	}
	
	@Override
	public String[][] getRowData() {
		List<Department> deptList = deptService.selectAllDept();
		
		String[][] arrDatas = new String[deptList.size()][];
		for (int i = 0; i < deptList.size(); i++) {
			arrDatas[i] = deptList.get(i).toArray();
		}
		
		return arrDatas;
	}
	
	@Override
	protected void updateMode(int no) {
		Department dept = deptService.selectOneDept(no);
		
		dlgDept.tfDcode.setText(Department.getStrDcode(no));
		dlgDept.tfDname.setText(dept.getDname());
		dlgDept.tfFloor.setText(dept.getFloor()+"");
		dlgDept.btnAdd.setText("수정");
	}

	@Override
	protected void deleteMode(int no) {
		int res = deptService.deleteDept(no);
		if (res == 0) {
			JOptionPane.showMessageDialog(null, "해당 부서에 아직 직원이 남아있어 삭제가 불가능합니다.");
		}else{
			JOptionPane.showMessageDialog(null, "해당 부서는 삭제되었습니다.");
		}
	}
}
