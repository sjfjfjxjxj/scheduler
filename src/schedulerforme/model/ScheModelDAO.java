package schedulerforme.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScheModelDAO {
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "student";
	private final String PASSWORD = "student";
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";

	/**
	 * 전체스케줄 보기. 일자 오름차순
	 * @return List<Schedule> scheList
	 */
	public List<Schedule> loadAll() {
		List<Schedule> scheList = null;
		Schedule schedule = null;
		String sql = "SELECT * FROM SCHEDULER_TBL ORDER BY 2";
		try {
			// SELECT * FROM SCHEDULER_TBL ORDER BY SCHE_DEADLINE ASC;
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(sql);
			scheList = new ArrayList<Schedule>();
			while (rset.next()) {
				schedule = new Schedule();
				schedule.setScheTitle(rset.getNString(1));
				schedule.setScheDeadline(rset.getInt(2));
				schedule.setScheOfficialCheck(rset.getNString(3));
				schedule.setScheTodo(rset.getNString(4));
				schedule.setScheWithWhom(rset.getNString(5));
				schedule.setScheToWhere(rset.getNString(6));
				schedule.setScheSysdate(rset.getString(7));
				scheList.add(schedule);
			}
			conn.close();
			stmt.close();
			rset.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scheList;
	}
	
	/**
	 * 일정제목으로 찾기->수정/삭제용으로 넘길 것
	 * @param insertTitle
	 * @return Schedule schedule
	 */
	public Schedule loadOneSche(String insertTitle) {
		String sql = "SELECT * FROM SCHEDULER_TBL WHERE SCHE_TITLE =?";
		Schedule schedule = null;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, insertTitle);
			ResultSet rset = pstmt.executeQuery();
			while(rset.next()) {
				schedule = new Schedule();
				schedule.setScheTitle(rset.getString(1));
				schedule.setScheDeadline(rset.getInt(2));
				schedule.setScheOfficialCheck(rset.getString(3));
				schedule.setScheTodo(rset.getString(4));
				schedule.setScheWithWhom(rset.getString(5));
				schedule.setScheToWhere(rset.getString(6));
				schedule.setScheSysdate(rset.getString(7));
				
			}
			rset.close();
			conn.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedule;
	}
	
	
	
/**
 * 특정일자 일정(들) 출력하기
 * @param scheDate
 * @return List<Schedule> scheList
 */
	public List<Schedule> loadSome(int scheDate) {
		List<Schedule> scheList = null;
		String sql = "SELECT * FROM SCHEDULER_TBL WHERE SCHE_DEADLINE = ?";
		// SELECT *
		// FROM SCHEDULER_TBL
		// WHERE SCHE_DEADLINE = ?
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scheDate);
			scheList = new ArrayList<Schedule>();
			ResultSet rset = pstmt.executeQuery();
			while (rset.next()) {
				Schedule schedule = new Schedule();
				schedule.setScheTitle(rset.getString(1));
				schedule.setScheDeadline(rset.getInt(2));
				schedule.setScheOfficialCheck(rset.getString(3));
				schedule.setScheTodo(rset.getString(4));
				schedule.setScheWithWhom(rset.getString(5));
				schedule.setScheToWhere(rset.getString(6));
				schedule.setScheSysdate(rset.getString(7));
				scheList.add(schedule);
			}
			conn.close();
			rset.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scheList;

	}

	/**
	 * 공식/비공식 카테고리로 출력
	 * 
	 * @param privateOfficial
	 * @return List<Schedule> schedule
	 */
	public List<Schedule> loadSches(String privateOfficial) {
		List<Schedule> scheList = null;
		String sql = "SELECT * FROM SCHEDULER_TBL WHERE SCHE_OFFICIALCHECK = ?";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, privateOfficial);
			ResultSet rset = pstmt.executeQuery();
			scheList = new ArrayList<Schedule>();
			while (rset.next()) {
				Schedule schedule = new Schedule();
				schedule.setScheTitle(rset.getString(1));
				schedule.setScheDeadline(rset.getInt(2));
				schedule.setScheOfficialCheck(rset.getString(3));
				schedule.setScheTodo(rset.getString(4));
				schedule.setScheWithWhom(rset.getString(5));
				schedule.setScheToWhere(rset.getString(6));
				schedule.setScheSysdate(rset.getString(7));
				scheList.add(schedule);
			}
			conn.close();
			pstmt.close();
			rset.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // oracle.jdbc.driver.OracleDriver
		catch (SQLException e) {
			e.printStackTrace();
		}
		return scheList;
	}

	/**
	 * 새스케줄저장
	 * @param schedule
	 * @return int result
	 */
	public int upLoadOne(Schedule schedule) {
		String sql = "INSERT INTO SCHEDULER_TBL VALUES(?,?,?,?,?,?,DEFAULT)";
		// INSERT INTO SCHEDULER_TBL VALUES(?,?,?,?,?,?,DEFAULT);
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, schedule.getScheTitle());
			pstmt.setInt(2, schedule.getScheDeadline());
			pstmt.setString(3, schedule.getScheOfficialCheck());
			pstmt.setString(4, schedule.getScheTodo());
			pstmt.setString(5, schedule.getScheWithWhom());
			pstmt.setString(6, schedule.getScheToWhere());
	
			result = pstmt.executeUpdate();
	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	
	}

	/**
	 * 일정제목 받아서 일정 수정
	 * @param schedule
	 * @return int result
	 */
	public int replaceSche(Schedule schedule) {
		String sql = "UPDATE SCHEDULER_TBL SET SCHE_DEADLINE=?, SCHE_OFFICIALCHECK=?, SCHE_TODO=?, SCHE_WITHWHOM=?, SCHE_TOWHERE=? WHERE SCHE_TITLE= ?";
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, schedule.getScheDeadline());
			pstmt.setString(2, schedule.getScheOfficialCheck());
			pstmt.setString(3, schedule.getScheTodo());
			pstmt.setString(4, schedule.getScheWithWhom());
			pstmt.setString(5, schedule.getScheToWhere());
			pstmt.setString(6, schedule.getScheTitle());
			result = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	/**
	 * 일정제목 받아서 일정 삭제
	 * @param scheTitle
	 * @return int result
	 */
	public int dropSche(String scheTitle) {
		int result = 0;
		String sql = "DELETE FROM SCHEDULER_TBL WHERE SCHE_TITLE = ?";
		try {
			Class.forName(DRIVER_NAME);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, scheTitle);
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}


}
