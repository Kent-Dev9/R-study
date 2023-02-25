package sub;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ComboBoxTableCell;

public class SubMakeG3KScolumn {
	


	public static void make(TableColumn g3kamokusyubetuColumn, String[] strHIDARI, String[] strMIGI) {
		 int count = 0;
		 List G3ColumnList = new ArrayList();
		
		for (int i = 0; i < strHIDARI.length; i++) {
			if (!(strHIDARI[i].equals(""))) G3ColumnList.add(strHIDARI[i]);
			if (!(strMIGI[i].equals(""))) G3ColumnList.add(strMIGI[i]);
			count = G3ColumnList.size();
			System.out.println(count);
		}
		
		switch(count) {
		case 0:
    		String str01 = null;
    		g3kamokusyubetuColumn.setCellFactory(ComboBoxTableCell.forTableColumn(str01));
    		break;
    		
		case 1:
    		String str11 = (String) G3ColumnList.get(0);
    		g3kamokusyubetuColumn.setCellFactory(ComboBoxTableCell.forTableColumn(str11));
    		break;
    	
    	case 2:
    		String str21 = (String) G3ColumnList.get(0);
    		String str22 = (String) G3ColumnList.get(1);
    		g3kamokusyubetuColumn.setCellFactory(ComboBoxTableCell.forTableColumn(str21, str22));
    		break;
    		
    	case 3:
    		String str31 = (String) G3ColumnList.get(0);
    		String str32 = (String) G3ColumnList.get(1);
    		String str33 = (String) G3ColumnList.get(2);
    		g3kamokusyubetuColumn.setCellFactory(ComboBoxTableCell.forTableColumn(str31, str32, str33));
    		break;
    		
    	case 4:
    		String str41 = (String) G3ColumnList.get(0);
    		String str42 = (String) G3ColumnList.get(1);
    		String str43 = (String) G3ColumnList.get(2);
    		String str44 = (String) G3ColumnList.get(3);
    		g3kamokusyubetuColumn.setCellFactory(ComboBoxTableCell.forTableColumn(str41, str42, str43, str44));
    		break;
    		
    	case 5:
    		String str51 = (String) G3ColumnList.get(0);
    		String str52 = (String) G3ColumnList.get(1);
    		String str53 = (String) G3ColumnList.get(2);
    		String str54 = (String) G3ColumnList.get(3);
    		String str55 = (String) G3ColumnList.get(4);
    		g3kamokusyubetuColumn.setCellFactory(ComboBoxTableCell.forTableColumn(str51,str52, str53, str54, str55));
    		break;
    		
    	case 6:
    		String str61 = (String) G3ColumnList.get(0);
    		String str62 = (String) G3ColumnList.get(1);
    		String str63 = (String) G3ColumnList.get(2);
    		String str64 = (String) G3ColumnList.get(3);
    		String str65 = (String) G3ColumnList.get(4);
    		String str66 = (String) G3ColumnList.get(5);
   			g3kamokusyubetuColumn.setCellFactory(ComboBoxTableCell.forTableColumn(str61, str62, str63, str64, str65, str66));
    		break;
    	case 7:
    		String str71 = (String) G3ColumnList.get(0);
    		String str72 = (String) G3ColumnList.get(1);
    		String str73 = (String) G3ColumnList.get(2);
    		String str74 = (String) G3ColumnList.get(3);
    		String str75 = (String) G3ColumnList.get(4);
    		String str76 = (String) G3ColumnList.get(5);
    		String str77 = (String) G3ColumnList.get(6);
   			g3kamokusyubetuColumn.setCellFactory(ComboBoxTableCell.forTableColumn(str71, str72, str73, str74, str75, str76, str77));
    		break;
    	default :
    		System.out.println("デフォルト");
    		break;
		
		
		
		
		}
		
		
		
	}

}
