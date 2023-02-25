package sub;

import java.util.ArrayList;
import java.util.List;

import controller.G4Controller;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

public class SubMakeG4kamokuList {
	
	static int sentakuBangou;

	public static ArrayList make(List menuItemList, String[] strHIDARI, String[] strMIGI) {
		for (int i = 0; i < strHIDARI.length; i++) {
			sentakuBangou = i;
			MenuItem miHidariyou = new MenuItem(strHIDARI[i]);
			miHidariyou.setOnAction(e-> G4Controller.setkamokuLabel(miHidariyou.getText()));
			MenuItem miMigiyou   = new MenuItem(strMIGI[i]);
			miMigiyou.setOnAction(e-> G4Controller.setkamokuLabel(miMigiyou.getText()));
			
			if (!(strHIDARI[i].equals(""))) menuItemList.add(miHidariyou);
			if (!(strMIGI[i].equals(""))) menuItemList.add(miMigiyou);
			
		}
		System.out.println(menuItemList.size());
		return (ArrayList) menuItemList;
		
	}

}
