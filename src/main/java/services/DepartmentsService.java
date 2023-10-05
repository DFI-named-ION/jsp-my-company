package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Department;
import providers.MySqlProvider;

public class DepartmentsService {
	
	private Connection conn;
	private Statement stmt;
	private ResultSet res;
	private PreparedStatement prepstmt;
	private String query;
	private int numRows;
	
	public List<Department> getDepsList() {
		List<Department> depList = new ArrayList<Department>();
		try {
			query = "SELECT * FROM departments ORDER BY id";
			conn = MySqlProvider.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(query);
			// ->
			while (res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				depList.add(new Department(id, name));
			}
			// ->
			res.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("getDeps-ClassNotFound: " + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("getDeps-SQL: " + ex.getMessage());
		} finally {
			try {
				if (res != null) {
					res.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("getDeps-SQL2: " + ex.getMessage());
			}
		}
		return depList;
	}

	public boolean createDep(String depName) {
		boolean success = false;
		try {
			query = "INSERT INTO departments (name) VALUES (?)";
			conn = MySqlProvider.getConnection();
			prepstmt = conn.prepareStatement(query);
			prepstmt.setString(1, depName);
			// ->
			numRows = prepstmt.executeUpdate();
			if (numRows != 0) {
				success = true;
			}
			// ->
			prepstmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("createDep-ClassNotFound: " + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("createDep-SQL: " + ex.getMessage());
		} finally {
			try {
				if (prepstmt != null) {
					prepstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("createDep-SQL2: " + ex.getMessage());
			}
		}
		return success;
	}

	public boolean deleteDep(int depId) {
		boolean success = false;
		try {
			query = "DELETE FROM departments WHERE id=?";
			conn = MySqlProvider.getConnection();
			prepstmt = conn.prepareStatement(query);
			prepstmt.setInt(1, depId);
			// ->
			numRows = prepstmt.executeUpdate();
			if (numRows != 0) {
				success = true;
			}
			// ->
			prepstmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("deleteDep-ClassNotFound: " + ex.getMessage());
			success = false;
		} catch (SQLException ex) {
			System.out.println("deleteDep-SQL: " + ex.getMessage());
			success = false;
		} finally {
			try {
				if (prepstmt != null) {
					prepstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("deleteDep-SQL2: " + ex.getMessage());
			}
		}
		return success;
	}

	public Department getDepById(int depId) {
		Department dep = null;
		try {
			query = "SELECT * FROM departments WHERE id=?";
			conn = MySqlProvider.getConnection();
			prepstmt = conn.prepareStatement(query);
			prepstmt.setInt(1, depId);
			res = prepstmt.executeQuery();
			// ->
			if (res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				dep = new Department(id, name);
			}
			// ->
			res.close();
			prepstmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("getDepById-ClassNotFound: " + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("getDepById-SQL: " + ex.getMessage());
		} finally {
			try {
				if (res != null) {
					res.close();
				}
				if (prepstmt != null) {
					prepstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("getDepById-SQL2: " + ex.getMessage());
			}
		}
		return dep;
	}
}