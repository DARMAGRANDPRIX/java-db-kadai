package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		Connection con = null;
		Statement str = null;
		try {
			con = DriverManager.getConnection(
					 "jdbc:mysql://localhost/challenge_java",
				     "root",
				     "Iwasekami18-"
				     );
			System.out.println("データベース接続成功:" + con);
			System.out.println("レコードの更新を実行します");
			str = con.createStatement();
			String sql1 = """
					UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;
					""";
			int cnt = str.executeUpdate(sql1);
			System.out.println(cnt + "件のレコードが追加されました");
			System.out.println("数学・英語の点数が高い順に並べ替えました");
			String sql2 ="""
					SELECT * FROM scores ORDER BY
					score_math DESC,
					score_english DESC;
					""";
			ResultSet result = str.executeQuery(sql2);
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int scoreMath = result.getInt("score_math");
				int scoreEnglish = result.getInt("score_english");
				System.out.println(result.getRow() + "件目：生徒ID=" + id + "／氏名=" + name + "／数学=" + scoreMath + "／英語=" + scoreEnglish);
			}
		} catch (SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			if( str != null ) {
                try { str.close(); } catch(SQLException ignore) {}
            }
            if( con != null ) {
                try { con.close(); } catch(SQLException ignore) {}
		    }
		}
	}
}

            
