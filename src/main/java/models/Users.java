package models;

public class Users {
	private int id;
	private String login;
	private String password;
	private String email;
	private String regdate;
	private int role_id;
	private int status_id;
	private int mail_confirm;
	
	public Users(int id, String login, String password, String email, String regdate, int role_id, int status_id,
			int mail_confirm) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.email = email;
		this.regdate = regdate;
		this.role_id = role_id;
		this.status_id = status_id;
		this.mail_confirm = mail_confirm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public int getMail_confirm() {
		return mail_confirm;
	}

	public void setMail_confirm(int mail_confirm) {
		this.mail_confirm = mail_confirm;
	}
	
	
}
