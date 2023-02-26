package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAO {
    // 接続用の情報をフィールドに定数として定義
    private static final String RDB_DRIVE = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/rstudy";
    private static final String USER = "postgres";
    private static final String PASSWD = "kent";
    
    // データベース接続を行うメソッド
    // データベース接続用定義を基にデータベースへ接続し、戻り値としてコネクション情報を返す
    private static Connection getConnection() {
        try {
            Class.forName(RDB_DRIVE);
            Connection con = DriverManager.getConnection(URL, USER, PASSWD);
            return con;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    public static String getPasswd(String id) { 
    	// 変数宣言
        Connection con = null;
        PreparedStatement pstmt = null; 
        String returnPasswd = null;

        try {
            // DBに接続
            con = DAO.getConnection();
            
            pstmt = con.prepareStatement("SELECT user_passwd FROM userinfo WHERE user_id = ?");
            pstmt.setString(1, id);
            // SQL文発行
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            returnPasswd = (rs.getString("user_passwd"));

        }catch (org.postgresql.util.PSQLException e ) {
        	
        }catch (SQLException e) {
            
        } finally {
            // リソースの開放
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return returnPasswd;
    }

	public static boolean updatePasswd(String id, String newPasswd) {
        Connection con = null;
        PreparedStatement pstmt = null; 
        String returnPasswd = null;
        
		try {
			// DBに接続
            con = DAO.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE userinfo SET user_passwd = ? WHERE user_id = ?");
            pstmt.setString(1, newPasswd);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
            con.commit();
            return true;
		}
	    catch (SQLException e) {
	    	try {
	    		e.printStackTrace();
	    		
				con.rollback();
			} catch (SQLException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			} 
	    } finally {
	        // リソースの開放
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException ignore) {
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException ignore) {
	            }
	        }
	    }
		return false;
	}

	public static String[] getG5tableKShidari() {
        Connection con = null;
        PreparedStatement pstmt = null; 
        String[] strHIDARI = new String[7];
        
        for (int i = 0; i < strHIDARI.length ; i++) {
            try {
                // DBに接続
                con = DAO.getConnection();
                pstmt = con.prepareStatement("SELECT ks_hidari FROM g5table WHERE recode_id = ?");
                pstmt.setInt(1, i+1);
                // SQL文発行
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                strHIDARI[i] = (rs.getString("ks_hidari"));
            }catch (org.postgresql.util.PSQLException e ) {
            	
            }catch (SQLException e) {
                
            } finally {
                // リソースの開放
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (SQLException ignore) {
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ignore) {
                    }
                }
            }
        }
        

        return strHIDARI;
	}

	public static String[] getG5tableKSmigi() {
        Connection con = null;
        PreparedStatement pstmt = null; 
        String[] strMIGI = new String[7];
        
        for (int i = 0; i < strMIGI.length ; i++) {
            try {
                // DBに接続
                con = DAO.getConnection();
                pstmt = con.prepareStatement("SELECT ks_migi FROM g5table WHERE recode_id = ?");
                pstmt.setInt(1, i+1);
                // SQL文発行
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                strMIGI[i] = (rs.getString("ks_migi"));
            }catch (org.postgresql.util.PSQLException e ) {
            	
            }catch (SQLException e) {
                
            } finally {
                // リソースの開放
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (SQLException ignore) {
                    }
                }
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException ignore) {
                    }
                }
            }
        }
	        return strMIGI;
	}

	public static void setg5tableKShidari(String ks_hidari, int recode_id) {
        Connection con = null;
        PreparedStatement pstmt = null; 
        
		try {
			// DBに接続
            con = DAO.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE g5table SET ks_hidari = ? WHERE recode_id  = ?");
            pstmt.setString(1, ks_hidari);
            pstmt.setInt(2, recode_id);
            pstmt.executeUpdate();
            con.commit();
		}
	    catch (SQLException e) {
	    	try {
	    		e.printStackTrace();
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
	    } finally {
	        // リソースの開放
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException ignore) {
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException ignore) {
	            }
	        }
	    }

	}

	public static void setg5tableKSmigi(String ks_migi, int recode_id) {
        Connection con = null;
        PreparedStatement pstmt = null; 
        
		try {
			// DBに接続
            con = DAO.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement("UPDATE g5table SET ks_migi = ? WHERE recode_id  = ?");
            pstmt.setString(1, ks_migi);
            pstmt.setInt(2, recode_id);
            pstmt.executeUpdate();
            con.commit();
		}
	    catch (SQLException e) {
	    	try {
	    		e.printStackTrace();
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
	    } finally {
	        // リソースの開放
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException ignore) {
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException ignore) {
	            }
	        }
	    }

	}

	public static void upsertBikouTable(String bikouText, LocalDate today) {
        Connection con = null;
        PreparedStatement pstmt = null; 
		
        try {
			// DBに接続
            con = DAO.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(
					            		"insert into bikou(hiduke, bikoutext) values(?, ?) \n"
					            		+ "ON CONFLICT ON CONSTRAINT bikou_hiduke_key\n"
					            		+ "DO\n"
					            		+ "UPDATE SET bikoutext=?"
					            		);
            java.sql.Date henkangoToday = java.sql.Date.valueOf(today);
            pstmt.setDate(1, henkangoToday);
            pstmt.setString(2, bikouText);
            pstmt.setString(3, bikouText);
            pstmt.executeUpdate();
            con.commit();
		}
	    catch (SQLException e) {
	    	try {
	    		e.printStackTrace();
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
	    } finally {
	        // リソースの開放
	        if (pstmt != null) {
	            try {
	                pstmt.close();
	            } catch (SQLException ignore) {
	            }
	        }
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException ignore) {
	            }
	        }
	    }
	}

	public static String getBikou(LocalDate hiduke) {
        Connection con = null;
        PreparedStatement pstmt = null; 
        String returnBikou = null;

        try {
            con = DAO.getConnection();
            pstmt = con.prepareStatement("SELECT bikoutext FROM bikou WHERE hiduke = ?");
            java.sql.Date kataHenkangoHiduke = java.sql.Date.valueOf(hiduke);
            pstmt.setDate(1, kataHenkangoHiduke);
            ResultSet rs = pstmt.executeQuery();
            	rs.next();
            	returnBikou = (rs.getString("bikoutext"));
            

        }catch (org.postgresql.util.PSQLException e ) {
        	
        }catch (SQLException e) {
        	
        } finally {
            // リソースの開放
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        return returnBikou;

	}

	public static String[][] getg3table(LocalDate hiduke) {
        Connection con = null;
        PreparedStatement pstmt = null; 
        String[][] strg3data = new String[7][3];
        
        //空文字を代入
        for(int i = 0; i < strg3data.length; i++) {
        	for(int k = 0; k < strg3data[i].length; k++) strg3data[i][k] = "";
        }
        
        
        try {
            // DBに接続
            con = DAO.getConnection();
            pstmt = con.prepareStatement("SELECT * FROM g3table WHERE hiduke = ?");
            java.sql.Date kataHenkangoHiduke = java.sql.Date.valueOf(hiduke);
            pstmt.setDate(1, kataHenkangoHiduke);
            
            // SQL文発行
            ResultSet rs = pstmt.executeQuery();
            int i = 0;
            while(rs.next()) {
            		strg3data[i][0] = rs.getString("ks_syubetu");
            		strg3data[i][1] = rs.getString("jikan");
            		strg3data[i][2] = rs.getString("tasseido");
              i++;			
            }

//            strHIDARI[i] = (rs.getString("ks_hidari"));
        }catch (org.postgresql.util.PSQLException e ) {
        	
        }catch (SQLException e) {
            
        } finally {
            // リソースの開放
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
        
        
		return strg3data;
	}

	public static void upsertG3table(String KS, String jikan, String tasseido, String upsertkey,int i_bikou, LocalDate today) {
		
		
	        Connection con = null;
	        PreparedStatement pstmt = null; 
			
	        try {
				// DBに接続
	            con = DAO.getConnection();
	            con.setAutoCommit(false);
	            pstmt = con.prepareStatement(
						            		"insert into g3table(hiduke, ks_syubetu, jikan, tasseido, fusiyoubikou, upsert_you_column) values(?, ?, ?, ?, ?, ?) \n"
						            		+ "ON CONFLICT ON CONSTRAINT g3table_upsert_you_column_key\n"
						            		+ "DO\n"
						            		+ "UPDATE SET hiduke = ?, ks_syubetu = ?, jikan = ?, tasseido= ?, fusiyoubikou= ? "
						            		);
	            java.sql.Date henkangoToday = java.sql.Date.valueOf(today);
	            pstmt.setDate(1, henkangoToday);
	            pstmt.setString(2, KS);
	            pstmt.setDouble(3, Double.parseDouble(jikan));
	            pstmt.setInt(4, Integer.parseInt(tasseido));
	            pstmt.setString(5, String.valueOf(i_bikou));
	            pstmt.setString(6, upsertkey);
	            
	            pstmt.setDate(7, henkangoToday);
	            pstmt.setString(8, KS);
	            pstmt.setDouble(9, Double.parseDouble(jikan));
	            pstmt.setInt(10, Integer.parseInt(tasseido));
	            pstmt.setString(11, String.valueOf(i_bikou));
	            pstmt.executeUpdate();
	            con.commit();
			}
		    catch (SQLException e) {
		    	e.printStackTrace();
				//con.rollback(); 
		    } finally {
		        // リソースの開放
		        if (pstmt != null) {
		            try {
		                pstmt.close();
		            } catch (SQLException ignore) {
		            }
		        }
		        if (con != null) {
		            try {
		                con.close();
		            } catch (SQLException ignore) {
		            }
		        }
		    }
	}


	public static Double getsougoujikan(LocalDate today, String ks_syubetu) {
		
	  	// 変数宣言
        Connection con = null;
        PreparedStatement pstmt = null; 
        Double sougoujikan = null;
        
        //月初めと月終わり日を取得
        LocalDate tukistart = today.withDayOfMonth(1);
        java.sql.Date tukiStart = java.sql.Date.valueOf(tukistart);
        LocalDate tukiend = today.withDayOfMonth(today.lengthOfMonth());
        java.sql.Date tukiEnd = java.sql.Date.valueOf(tukiend);

        try {
            // DBに接続
            con = DAO.getConnection();
            
            pstmt = con.prepareStatement("SELECT SUM (jikan) AS sougoujikan "
            		+ "FROM g3table  "
            		+ "WHERE hiduke  BETWEEN ? AND ? AND  ks_syubetu = ?;");
            // SQL文発行
            pstmt.setDate(1, tukiStart);
            pstmt.setDate(2, tukiEnd);
            pstmt.setString(3, ks_syubetu);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            sougoujikan = (rs.getDouble("sougoujikan"));
            Double result = sougoujikan;
            return result;
        }catch (org.postgresql.util.PSQLException e ) {
        	
        }catch (SQLException e) {
            
        } finally {
            // リソースの開放
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
		return null;
	}

	public static Double gettasseidoheikin(LocalDate today, String ks_syubetu) {
		// 変数宣言
        Connection con = null;
        PreparedStatement pstmt = null; 
        Double tasseidoheikin = null;
        
        //月初めと月終わり日を取得
        LocalDate tukistart = today.withDayOfMonth(1);
        java.sql.Date tukiStart = java.sql.Date.valueOf(tukistart);
        LocalDate tukiend = today.withDayOfMonth(today.lengthOfMonth());
        java.sql.Date tukiEnd = java.sql.Date.valueOf(tukiend);

        try {
            // DBに接続
            con = DAO.getConnection();
            
            pstmt = con.prepareStatement("SELECT AVG (tasseido) AS tasseidoheikin "
            		+ "FROM g3table  "
            		+ "WHERE hiduke  BETWEEN ? AND ? AND  ks_syubetu = ?;");
            // SQL文発行
            pstmt.setDate(1, tukiStart);
            pstmt.setDate(2, tukiEnd);
            pstmt.setString(3, ks_syubetu);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            tasseidoheikin = (rs.getDouble("tasseidoheikin"));
            Double result = tasseidoheikin;
            return result;
        }catch (org.postgresql.util.PSQLException e ) {
        	
        }catch (SQLException e) {
            
        } finally {
            // リソースの開放
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
		return null;
	}

	public static HashMap getBikouForG4(LocalDate today2) {
		// 変数宣言
        Connection con = null;
        PreparedStatement pstmt = null; 
        HashMap<Date, String> bikoumap = new HashMap<Date, String>();
        
        //月初めと月終わり日を取得
        LocalDate tukistart = today2.withDayOfMonth(1);
        java.sql.Date tukiStart = java.sql.Date.valueOf(tukistart);//SQL用
        LocalDate tukiend = today2.withDayOfMonth(today2.lengthOfMonth());
        java.sql.Date tukiEnd = java.sql.Date.valueOf(tukiend);//SQL用

        try {
            // DBに接続
            con = DAO.getConnection();
            
            pstmt = con.prepareStatement("SELECT *"
            		+ "FROM bikou "
            		+ "WHERE hiduke BETWEEN ? AND ? ;");
            // SQL文発行
            pstmt.setDate(1, tukiStart);
            pstmt.setDate(2, tukiEnd);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
            	bikoumap.put(rs.getDate("hiduke"),rs.getString("bikoutext"));  
            }
            return bikoumap;
        }catch (org.postgresql.util.PSQLException e ) {
        	
        }catch (SQLException e) {
            
        } finally {
            // リソースの開放
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }
		return null;
		
	}


	public static void getBikou(String[][] g4dataRecode, LocalDate tukistart, LocalDate tukiend ,int TukiLength) {
		// 変数宣言
        Connection con = null;
        PreparedStatement pstmt = null; 
        
        //tukistartとtukiendをSQLで使える型に変換
        java.sql.Date tukiStart = java.sql.Date.valueOf(tukistart);
        java.sql.Date tukiEnd = java.sql.Date.valueOf(tukiend);

        try {
            // DBに接続
            con = DAO.getConnection();
            
            pstmt = con.prepareStatement("SELECT *"
            		+ "FROM bikou "
            		+ "WHERE hiduke BETWEEN ? AND ? order by hiduke;");
            // SQL文発行
            pstmt.setDate(1, tukiStart);
            pstmt.setDate(2, tukiEnd);
            ResultSet rs = pstmt.executeQuery();
//            
                while(rs.next()) {
                    for (int i = 0; i < TukiLength; i++) {
                    	LocalDate hidukeFromDB = rs.getDate("hiduke").toLocalDate(); 
	                	if(hidukeFromDB.isEqual(tukistart.plusDays(i))) {
	                		g4dataRecode[i][3] = rs.getString("bikoutext");
	                		break;
                	    }
                    }
                }
        }catch (org.postgresql.util.PSQLException e ) {
        	
        }catch (SQLException e) {
            
        } finally {
            // リソースの開放
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }		
		
	}

	public static void getSougoujikan(String[][] g4dataRecode, LocalDate tukistart, LocalDate tukiend ,int TukiLength) {
		// 変数宣言
        Connection con = null;
        PreparedStatement pstmt = null; 
        
        //tukistartとtukiendをSQLで使える型に変換
        java.sql.Date tukiStart = java.sql.Date.valueOf(tukistart);
        java.sql.Date tukiEnd = java.sql.Date.valueOf(tukiend);

        try {
            // DBに接続
            con = DAO.getConnection();
            
            pstmt = con.prepareStatement("select sum(jikan) as sougoujikan, hiduke from g3table where hiduke between ? and ? group by hiduke order by hiduke;");
            // SQL文発行
            pstmt.setDate(1, tukiStart);
            pstmt.setDate(2, tukiEnd);
            ResultSet rs = pstmt.executeQuery();
//            
                while(rs.next()) {
                    for (int i = 0; i < TukiLength; i++) {
                    	LocalDate hidukeFromDB = rs.getDate("hiduke").toLocalDate(); 
	                	if(hidukeFromDB.isEqual(tukistart.plusDays(i))) {
	                		g4dataRecode[i][2] = rs.getString("sougoujikan");
	                		break;
                	    }
                    }
                }
        }catch (org.postgresql.util.PSQLException e ) {
        	
        }catch (SQLException e) {
            
        } finally {
            // リソースの開放
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }		
		
	}

	public static void getKamoku(String[][] g4dataRecode, LocalDate tukistart, LocalDate tukiend, int TukiLength) {
		// 変数宣言
		Connection con = null;
        PreparedStatement pstmt = null; 
        
        //tukistartとtukiendをSQLで使える型に変換
        java.sql.Date tukiStart = java.sql.Date.valueOf(tukistart);
        java.sql.Date tukiEnd = java.sql.Date.valueOf(tukiend);

        try {
            // DBに接続
            con = DAO.getConnection();
            
            pstmt = con.prepareStatement("select ks_syubetu, hiduke from g3table where hiduke between ? and ? order by hiduke;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            // SQL文発行
            pstmt.setDate(1, tukiStart);
            pstmt.setDate(2, tukiEnd);
            ResultSet rs = pstmt.executeQuery();
            String hibetuKamoku = "";
            LocalDate hidukeFromDB = null;
            int WhileFLG = 0;
                while(rs.next()) {
                    for (int i = 0; i < TukiLength; i++) {
                    	WhileFLG = 0 ;
                    	hibetuKamoku = "";
                    	hidukeFromDB = rs.getDate("hiduke").toLocalDate(); 
                    	while (hidukeFromDB.isEqual(tukistart.plusDays(i))) {
                    		if(!(hibetuKamoku.equals(""))) hibetuKamoku = hibetuKamoku + "、" ;
                           	hibetuKamoku = hibetuKamoku + rs.getString("ks_syubetu"); 
                    		g4dataRecode[i][0] = hibetuKamoku;
                    		rs.next();
                    		WhileFLG = 1;
                    		hidukeFromDB = rs.getDate("hiduke").toLocalDate(); 
                	    }
                    	if(WhileFLG == 1) {
                    		rs.previous();
                        	break;
                    	}
                    }
                }
        }catch (org.postgresql.util.PSQLException e ) {
        	
        }catch (SQLException e) {
            
        } finally {
            // リソースの開放
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }	
		
	}
	
	public static void getTasseidoheikin(String[][] g4dataRecode, LocalDate tukistart, LocalDate tukiend ,int TukiLength) {
		// 変数宣言
		Connection con = null;
        PreparedStatement pstmt = null; 
        
        //tukistartとtukiendをSQLで使える型に変換
        java.sql.Date tukiStart = java.sql.Date.valueOf(tukistart);
        java.sql.Date tukiEnd = java.sql.Date.valueOf(tukiend);

        try {
            // DBに接続
            con = DAO.getConnection();
            
            pstmt = con.prepareStatement("select tasseido, hiduke from g3table where hiduke between ? and ? order by hiduke;", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            // SQL文発行
            pstmt.setDate(1, tukiStart);
            pstmt.setDate(2, tukiEnd);
            ResultSet rs = pstmt.executeQuery();
            double tasseidoGoukei = 0; 
            double tasseidoHeikinPri = 0.0;
            Double tasseidoHeikin = 0.0;
            LocalDate hidukeFromDB = null;
            int DouituhidukeSU = 1;
            int WhileFLG = 0;
            Double Roundtasseido = null;
                while(rs.next()) {
                    for (int i = 0; i < TukiLength; i++) {
                    	WhileFLG = 0 ;
                    	tasseidoGoukei = 0;
                    	DouituhidukeSU = 1;
                    	hidukeFromDB = rs.getDate("hiduke").toLocalDate(); 
                    	while (hidukeFromDB.isEqual(tukistart.plusDays(i))) {
//                    		if(!(hibetuKamoku.equals(""))) hibetuKamoku = hibetuKamoku + "、" ;
                    		tasseidoGoukei = tasseidoGoukei + rs.getInt("tasseido");
                    		tasseidoHeikinPri = tasseidoGoukei / DouituhidukeSU;
                    		tasseidoHeikin = tasseidoHeikinPri;
                    		Roundtasseido = (double)Math.round(tasseidoHeikin * 10)/10;
                    		g4dataRecode[i][1] = Roundtasseido.toString();
                    		rs.next();
                    		WhileFLG = 1;
                    		DouituhidukeSU ++;
                    		hidukeFromDB = rs.getDate("hiduke").toLocalDate(); 
                	    }
                    	if(WhileFLG == 1) {
                    		rs.previous();
                        	break;
                    	}
                    }
                }
        }catch (org.postgresql.util.PSQLException e ) {
        	
        }catch (SQLException e) {
            
        } finally {
            // リソースの開放
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ignore) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ignore) {
                }
            }
        }	
		
	}

}
