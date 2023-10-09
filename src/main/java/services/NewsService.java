package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.News;
import providers.MySqlProvider;

public class NewsService {

	private Connection conn;
	private Statement stmt;
	private ResultSet res;
	private PreparedStatement prepstmt;
	private String query;
	private int numRows;
	
	private String generateSlug(String title, String description) {
        String combinedText = title + "-" + description;

        String cleanedText = combinedText.replaceAll("[^a-zA-Z0-9\\s-]", "")
                                        .replaceAll("\\s+", "-")
                                        .toLowerCase();

        if (cleanedText.length() > 100) {
            cleanedText = cleanedText.substring(0, 100);
        }

        cleanedText = cleanedText.replaceAll("-+$", "");

        return cleanedText;
    }
	
	public List<News> getNewsList() {
		List<News> depList = new ArrayList<News>();
		try {
			query = "SELECT * FROM news ORDER BY id";
			conn = MySqlProvider.getConnection();
			stmt = conn.createStatement();
			res = stmt.executeQuery(query);
			// ->
			while (res.next()) {
				int id = res.getInt("id");
				String slug = res.getString("slug");
				String title = res.getString("title");
				String description = res.getString("description");
				depList.add(new News(id, slug, title, description));
			}
			// ->
			res.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("getNews-ClassNotFound: " + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("getNews-SQL: " + ex.getMessage());
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
				System.out.println("getNews-SQL2: " + ex.getMessage());
			}
		}
		return depList;
	}

	public boolean createNews(String newsTitle, String newsDescription) {
		boolean success = false;
		try {
			String slug = generateSlug(newsTitle, newsDescription);
			query = "INSERT INTO news (slug, title, description) VALUES (?, ?, ?)";
			conn = MySqlProvider.getConnection();
			prepstmt = conn.prepareStatement(query);
			prepstmt.setString(1, slug);
			prepstmt.setString(2, newsTitle);
			prepstmt.setString(3, newsDescription);
			// ->
			numRows = prepstmt.executeUpdate();
			if (numRows != 0) {
				success = true;
			}
			// ->
			prepstmt.close();
			conn.close();
		} catch (ClassNotFoundException ex) {
			System.out.println("createNews-ClassNotFound: " + ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("createNews-SQL: " + ex.getMessage());
		} finally {
			try {
				if (prepstmt != null) {
					prepstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println("createNews-SQL2: " + ex.getMessage());
			}
		}
		return success;
	}

}
