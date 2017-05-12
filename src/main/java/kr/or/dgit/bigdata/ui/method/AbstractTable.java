package kr.or.dgit.bigdata.ui.method;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.or.dgit.bigdata.dto.Department;
import kr.or.dgit.bigdata.service.DepartmentService;
import kr.or.dgit.bigdata.service.EmployeeService;
import kr.or.dgit.bigdata.service.TitleService;
import kr.or.dgit.bigdata.ui.DialogDept;
import kr.or.dgit.bigdata.ui.DialogEmp;
import kr.or.dgit.bigdata.ui.DialogTitle;

public abstract class AbstractTable extends JPanel {
	protected DepartmentService deptService;
	protected TitleService titleService;
	protected EmployeeService empService;
	protected DialogDept dlgDept;
	protected DialogTitle dlgTitle;
	protected DialogEmp dlgEmp;
	protected JTable table;
	
	public AbstractTable(Object dialog) {
		if (dialog.getClass().getSimpleName().equals("DialogDept")) {
			this.dlgDept = (DialogDept)dialog;
		}else if(dialog.getClass().getSimpleName().equals("DialogTitle")){
			this.dlgTitle = (DialogTitle)dialog;
		}else{
			this.dlgEmp = (DialogEmp)dialog;
		}
		
		this.deptService = DepartmentService.getInstance();
		this.titleService =  TitleService.getInstance();
		this.empService = EmployeeService.getInstance();
		
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//팝업메뉴
		JPopupMenu popup = new JPopupMenu();
		JMenuItem miUpdate = new JMenuItem("수정");
		JMenuItem miDelete = new JMenuItem("삭제");
        popup.add(miUpdate);
        popup.add(miDelete);
        menuClick(miUpdate);
        menuClick(miDelete);
        
        table.setComponentPopupMenu(popup);
    	table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int r = table.rowAtPoint(e.getPoint());
			        if (r >= 0 && r < table.getRowCount()) {
			            table.setRowSelectionInterval(r, r);
			        } else {
			            table.clearSelection();
			        }
			        
				}
			}
		});
		
		reload();
		setVisible(true);
	}
	
	public void menuClick(JMenuItem item) {
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				int no = 0;
				if (table.getSelectedRow() >= 0) {
					String strNo = (String) model.getValueAt(table.getSelectedRow(), 0);
					strNo = strNo.substring(1, strNo.length());
					no = Integer.parseInt(strNo);
				}
				
				if (e.getActionCommand().equals("수정")) {
					updateMode(no);
				}else if(e.getActionCommand().equals("삭제")){
					deleteMode(no);
				}
				
				reload();
			}
		});
	}//팝업메뉴 클릭
	
	protected abstract void updateMode(int no);
	protected abstract void deleteMode(int no);
	protected abstract void reload();
	protected abstract String[][] getRowData();

	public void tableCellAlignment(int align, int ...idx) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		
		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	
	public void tableSetWidth(int ...width){
		TableColumnModel cModel = table.getColumnModel();
		
		for (int i = 0; i < width.length; i++) {
			cModel.getColumn(i).setPreferredWidth(width[i]);
		}
	}
}
