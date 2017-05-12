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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.or.dgit.bigdata.dto.Department;
import kr.or.dgit.bigdata.dto.Title;
import kr.or.dgit.bigdata.service.TitleService;
import kr.or.dgit.bigdata.ui.DialogTitle;

public class TableTitle extends AbstractTable {

	public TableTitle(DialogTitle dialog) {
		super(dialog);
	}
	
	@Override
	protected void updateMode(int tcode) {
		Title title = titleService.selectOneTitle(tcode);
		
		dlgTitle.tfTcode.setText(Title.getStrTcode(tcode));
		dlgTitle.tfTname.setText(title.getTname());
		dlgTitle.btnAdd.setText("수정");
	}

	@Override
	protected void deleteMode(int tcode) {
		int res = titleService.deleteTitle(tcode);
		if (res == 0) {
			JOptionPane.showMessageDialog(null, "해당 직책을 가진 직원이 아직 남아있어 삭제가 불가능합니다.");
		}else{
			JOptionPane.showMessageDialog(null, "해당 직책은 삭제되었습니다.");
		}
	}

	@Override
	protected void reload() {
		DefaultTableModel model = new DefaultTableModel(getRowData(), new String[] {"번호", "직책"});
		table.setModel(model);
		tableCellAlignment(SwingConstants.CENTER, 0, 1);
		tableSetWidth(100, 170);
	}

	@Override
	protected String[][] getRowData() {
		List<Title> titleList = titleService.selectAllTitle();
		String[][] arrDatas = new String[titleList.size()][];
		
		for (int i = 0; i < titleList.size(); i++) {
			arrDatas[i] = titleList.get(i).toArray();
		}
		return arrDatas;
	}
}
