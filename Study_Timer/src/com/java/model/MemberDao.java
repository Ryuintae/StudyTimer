package com.java.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MemberDao {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306";
	static String uid = "root";
	static String pwd = "0000";

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	private String query = null;

	public MemberDao() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원삭제
	public void delete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(url, uid, pwd);
			pstmt = conn.prepareStatement("delete from member.member user where id=?");
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("오류 발생 : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	// 회원가입 값 넣기
	public void insertData(String id, String pw, String name, String email) {

		int result = 0;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = null;

		try {

			sql = "INSERT INTO member.member (id,pw,name,email) VALUES(?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			result = pstmt.executeUpdate();
			// 사용자가 로그인했떤 dto.getID를 그대로 받아와서 바꿔주기
			// dao 업데이트를 실행하고 dto는 id값
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	// id 중복체크
	public boolean getIdByCheck(String id) {

		boolean result = true;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = null;

		try {

			sql = "SELECT * FROM member.member where id=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next())
				result = false;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return result;

	}

	// 로그인
	public boolean login(String id, String pass) {

		PreparedStatement pstmt = null;
		Connection con = null;
		boolean result = false;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = null;

		try {
			sql = "SELECT pw FROM member.member where id='" + id + "'";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (pass.equals(rs.getString("pw"))) {
					return true;
				} else {
					System.out.println("로그인 실패");
				}
			}
			pstmt.close();

		} catch (Exception e) {
			System.out.println("중복 : " + e.toString());
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		return result;

	}

	// 시간입력
	public void insertTime(String id, int hour, int min, int ms) {

		int result = 0;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = null;

		try {

			sql = "INSERT INTO member.myinfo (id,Hour,Min,Ms) VALUES(?,?,?,?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setInt(2, hour);
			pstmt.setInt(3, min);
			pstmt.setInt(4, ms);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	// 랭킹 시스템
	public String rankTime() {

		String result = "";
		Statement pstmt = null;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql = null;

		try {
			sql = "SELECT * FROM member.myinfo ORDER BY Hour,Min DESC Limit 1";

			pstmt = con.createStatement();

			rs = pstmt.executeQuery(sql);

			int total_hour = 0;
			int total_min = 0;
			int total_ms = 0;

			while (rs.next()) {

				total_hour += Integer.parseInt(rs.getString("Hour"));
				total_min += Integer.parseInt(rs.getString("Min"));
				total_ms += Integer.parseInt(rs.getString("Ms"));
			}

			result = "총 시간 : " + total_hour + " 시간 " + total_min + " 분 " + total_ms + " 초";

			pstmt.close();

		} catch (Exception e) {

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return result;
	}

	// 총 공부시간 갖고오기
	public String selectTime(String id) {

		String result = "";
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = null;

		try {

			sql = "select Hour,Min,Ms from member.myinfo where id=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			int total_hour = 0;
			int total_min = 0;
			int total_ms = 0;

			while (rs.next()) {

				total_hour += Integer.parseInt(rs.getString(1));
				total_min += Integer.parseInt(rs.getString(2));
				total_ms += Integer.parseInt(rs.getString(3));
			}

			result = "총 시간 : " + total_hour + " 시간 " + total_min + " 분 " + total_ms + " 초";

			pstmt.close();

		} catch (Exception e) {

		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return result;

	}

	// 비밀번호 update문
	public int mUpdate(String id, String swap) {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, uid, pwd);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		String sql = null;
		try {
			sql = "update member.member set pw=? where id =?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, swap);
			pstmt.setString(2, id);

			result = pstmt.executeUpdate();

			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<MemberDto> mSelect() {
		query = "select * from member.member";
		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		try {
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");

				MemberDto dto = new MemberDto(id, pw, name, email);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return dtos;
	}

}
