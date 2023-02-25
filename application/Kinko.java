package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Kinko {
	private static String Fid ;
	Alert alert = new Alert(AlertType.NONE);
	
	private static void setFid(String id) {
		Fid = id;
	}
	
	
	//loginボタン押下時に呼び出す
	public String loginCheck(String id , String passwd) {
	
		if (id == "") {
			return "IDを入力してください";
		}
		else if (passwd == "") {
			return "パスワードを入力してください";
		} 
		else {
			String returnPasswd = DAO.getPasswd(id);
			if (passwd.equals(returnPasswd)) {
				setFid(id);
				return "OK";
			} 
			else {
		        alert.setAlertType(AlertType.ERROR);
		        alert.setTitle("エラー");
		        alert.setContentText("IDまたはパスワードが違います");
		        alert.show();
				return "";
			}	
		}
	}
	

	public String passwdHenkouCheck(String oldPasswd, String newPasswd, String TWnewPasswd) {
		if (oldPasswd.equals("")) return "現在のパスワードを入力してください";
		if (newPasswd.equals("")) return "新しいパスワードを入力してください";
		if (TWnewPasswd.equals("")) return "新しいパスワードを再度入力してください";
		if (!(newPasswd.equals(TWnewPasswd))) return "確認用パスワードが新しいパスワードと一致しません";
		if (oldPasswd.equals(newPasswd)) return "新しいパスワードが現在のパスワードと同じです";
		
		String returnNowPasswd = DAO.getPasswd(Fid);
		if (returnNowPasswd.equals(oldPasswd)) {
				return "OK";
		}
		return "不具合が発生しています";
		
	}
	
	public String passwdHenkou(String newPasswd) {
		
		boolean PasswdUpdateResult = DAO.updatePasswd(Fid, newPasswd);
		if (PasswdUpdateResult == true) {
	        alert.setAlertType(AlertType.INFORMATION);
	        alert.setTitle("成功");
	        alert.setContentText("パスワード変更は正常に終了しました");
	        alert.show();
			return "success";
		}
		return "fail";
	}
}
