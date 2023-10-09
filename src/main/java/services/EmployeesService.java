package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Employee;
import providers.MySqlProvider;

public class EmployeesService {

	private Connection conn;
	private Statement stmt;
	private ResultSet res;
	private PreparedStatement prepstmt;
	private String query;
	private int numRows;
	
	public List<Employee> getEmpsList() {
		List<Employee> empList = new ArrayList<Employee>();
		try {
			query = "SELECT * FROM employees ORDER BY id";
			conn = MySqlProvider.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(query);
			// ->
			while (res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				double salary = res.getDouble("salary");
				int age = res.getInt("age");
				int depId = res.getInt("depId");
				empList.add(new Employee(id, name, age, salary, depId));
			}
			// ->
			res.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("getEmps-ClassNotFound: " + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("getEmps-SQL: " + ex.getMessage());
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
				System.out.println("getEmps-SQL2: " + ex.getMessage());
			}
		}
		return empList;
	}

	public boolean createEmp(String name, int age, double salary, int depId) {
		boolean success = false;
		try {
			query = "INSERT INTO employees (name) VALUES (?)";
			conn = MySqlProvider.getConnection();
			prepstmt = conn.prepareStatement(query);
			prepstmt.setString(1, name);
			prepstmt.setInt(2, age);
			prepstmt.setDouble(3, salary);
			prepstmt.setInt(4, depId);
			// ->
			numRows = prepstmt.executeUpdate();
			if (numRows != 0) {
				success = true;
			}
			// ->
			prepstmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("createEmp-ClassNotFound: " + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("createEmp-SQL: " + ex.getMessage());
		} finally {
			try {
				if (prepstmt != null) {
					prepstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("createEmp-SQL2: " + ex.getMessage());
			}
		}
		return success;
	}

	public boolean deleteEmp(int id) {
		boolean success = false;
		try {
			query = "DELETE FROM employees WHERE id=?";
			conn = MySqlProvider.getConnection();
			prepstmt = conn.prepareStatement(query);
			prepstmt.setInt(1, id);
			// ->
			numRows = prepstmt.executeUpdate();
			if (numRows != 0) {
				success = true;
			}
			// ->
			prepstmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("deleteEmp-ClassNotFound: " + ex.getMessage());
			success = false;
		} catch (SQLException ex) {
			System.out.println("deleteEmp-SQL: " + ex.getMessage());
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
				System.out.println("deleteEmp-SQL2: " + ex.getMessage());
			}
		}
		return success;
	}

	public Employee getEmpById(int eId) {
		Employee emp = null;
		try {
			query = "SELECT * FROM employees WHERE id=?";
			conn = MySqlProvider.getConnection();
			prepstmt = conn.prepareStatement(query);
			prepstmt.setInt(1, eId);
			res = prepstmt.executeQuery();
			// ->
			if (res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				double salary = res.getDouble("salary");
				int age = res.getInt("age");
				int depId = res.getInt("depId");
				emp = new Employee(id, name, age, salary, depId);
			}
			// ->
			res.close();
			prepstmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("getEmpById-ClassNotFound: " + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("getEmpById-SQL: " + ex.getMessage());
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
				System.out.println("getEmpById-SQL2: " + ex.getMessage());
			}
		}
		return emp;
	}
}