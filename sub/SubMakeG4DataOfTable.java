package sub;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import application.DAO;
import javafx.collections.ObservableList;
import model.G3Model;
import model.G4Model;

public class SubMakeG4DataOfTable {
	public static void makeData(ObservableList<G4Model> data, LocalDate today2) {
		
		//Length用のformatを作成。（日付をintに変換するときに使用する）
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd");
		//月の最終日を取得しString型変数に代入する
		LocalDate tukiend = today2.withDayOfMonth(today2.lengthOfMonth());
		String TukiendStiring = tukiend.format(fmt).toString();
		//TukiEndをint型に変換
		int TukiLength = (int)Integer.parseInt(TukiendStiring);
		//月初め日を取得する
		LocalDate tukistart = today2.withDayOfMonth(1);
		//LocalDateのformatを作成
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd日");
		//曜日用のformatを作成
		DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("E", Locale.JAPANESE);
		
//		31日分のデータを作るため1次元目は31個、2次元目は左から順に「科目」、「達成度平均」、「時間」、「備考」が入る
		String G4dataRecode[][] =new String[31][4];
//		空文字で埋める
        for(int i = 0; i < TukiLength; i++) {
        	for(int k = 0; k < G4dataRecode[i].length; k++) G4dataRecode[i][k] = "";
        }
        
        //2次元配列G4dataRecodeに値を入れていく。
          DAO.getKamoku(G4dataRecode, tukistart, tukiend, TukiLength);
          DAO.getTasseidoheikin(G4dataRecode, tukistart, tukiend, TukiLength);
          DAO.getSougoujikan(G4dataRecode, tukistart, tukiend, TukiLength);
          DAO.getBikou(G4dataRecode, tukistart, tukiend, TukiLength);
        
		for(int i = 0; i < TukiLength; i++) {
			LocalDate hiduke = tukistart.plusDays(i);
			
			data.addAll( new G4Model(hiduke.format(fmt2).toString(),hiduke.format(fmt3).toString(),G4dataRecode[i][0],G4dataRecode[i][1],G4dataRecode[i][2],G4dataRecode[i][3]) );
		}
			
			
		
		
	}
}