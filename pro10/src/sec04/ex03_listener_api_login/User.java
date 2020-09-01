package sec04.ex03_listener_api_login;

public class User {
	
	private String user_id;
	private String user_pw;
	private int user_num=0;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public int getUser_num() {
		return user_num;
	}
	
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	
}
