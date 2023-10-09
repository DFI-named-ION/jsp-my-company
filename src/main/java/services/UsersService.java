package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import providers.MySqlProvider;

public class UsersService {

	private Connection conn;
	private PreparedStatement stmt;
	private String query;
	private int status;
	private ResultSet res;
	
	public UsersService() {
		conn = null;
		stmt = null;
		query = "";
		status = 0;
		res = null;
	}
	
	public boolean addUser(String login, String password, String email, int role_id, int status_id) {
		boolean success = false;
		try {
			query = "insert into users (login, password, email, role_id, status_id) ";
			query += "values (?, ?, ?, ?, ?)";
			conn = MySqlProvider.getConnection();
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, login);
			stmt.setString(2, password);
			stmt.setString(3, email);
			stmt.setInt(4, role_id);
			stmt.setInt(5, status_id);
			
			status = stmt.executeUpdate();
			
			if (status != 0) {
				success = true;
			}
			
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("addUser error - " + ex.getMessage());
			success = false;
		} catch (SQLException ex) {
			System.out.println("addUser error - " + ex.getMessage());
			success = false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("addUser error - " + ex.getMessage());
				success = false;
			}
		}
		return success;
	}
	
	public boolean confirmUser(String email) {
		boolean success = false;
		try {
			query = "update users set mail_confirm=? where email=?";
			
			conn = MySqlProvider.getConnection();
			stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, 1);
			stmt.setString(2, email);
			
			status = stmt.executeUpdate();
			
			if (status != 0) {
				success = true;
			}
			
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException - " + ex.getMessage());
			success = false;
		} catch (SQLException ex) {
			System.out.println("SqlException - " + ex.getMessage());
			success = false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("SqlException - " + ex.getMessage());
			}
		}
		return success;
	}
	
	public boolean loginUser(String login, String password) {
		boolean success = false;
		try {
			query = "select login, password from users where login=? and password=?";
			
			conn = MySqlProvider.getConnection();
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, login);
			stmt.setString(2, password);
			
			res = stmt.executeQuery();
			
			if (res.next()) {
				success = true;
			}
			
			res.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException - " + ex.getMessage());
			success = false;
		} catch (SQLException ex) {
			System.out.println("SqlException - " + ex.getMessage());
			success = false;
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
				System.out.println("SqlException - " + ex.getMessage());
			}
		}
		return success;
	}

	public boolean checkConfirm(String login) {
		boolean success = false;
		try {
			query = "select mail_confirm from users where login=?";
			
			conn = MySqlProvider.getConnection();
			stmt = conn.prepareStatement(query);
			
			stmt.setString(1, login);
			
			res = stmt.executeQuery();
			
			if (res.next()) {
				int stat = res.getInt("mail_confirm");
				if (stat == 1) {
					success = true;
				}
			}
			
			res.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException - " + ex.getMessage());
			success = false;
		} catch (SQLException ex) {
			System.out.println("SqlException - " + ex.getMessage());
			success = false;
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
				System.out.println("SqlException - " + ex.getMessage());
			}
		}
		return success;
	}

	public boolean checkLoginFree(String login) {
		boolean success = false;
		try {
			query = "select login from users where login=?";
			conn = MySqlProvider.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, login);
			// ->
			res = stmt.executeQuery();
			if (!res.next()) {
				success = true;
			}
			// ->
			res.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("checkLoginFree-ClassNotFound " + ex.getMessage());
			success = false;
		} catch (SQLException ex) {
			System.out.println("checkLoginFree-SQL " + ex.getMessage());
			success = false;
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
				
			}
 		}
		return success;
	}

	public boolean checkEmailFree(String email) {
		boolean success = false;
		try {
			query = "select email from users where email=?";
			conn = MySqlProvider.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, email);
			// ->
			res = stmt.executeQuery();
			if (!res.next()) {
				success = true;
			}
			// ->
			res.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("checkEmailFree-ClassNotFound " + ex.getMessage());
			success = false;
		} catch (SQLException ex) {
			System.out.println("checkEmailFree-SQL " + ex.getMessage());
			success = false;
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
				
			}
 		}
		return success;
	}

}