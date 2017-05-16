package cn.gavid.invoiceSystem.login.domain;

/*
 * 领域对象User
 * anthor :gavid
 */
public class User {

	private String uid;
	private String uName;
	private String uPassword;
	private boolean uRemmberpwd;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	public boolean isuRemmberpwd() {
		return uRemmberpwd;
	}
	public void setuRemmberpwd(boolean uRemmberpwd) {
		this.uRemmberpwd = uRemmberpwd;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uName=" + uName + ", uPassword="
				+ uPassword + ", uRemmberpwd=" + uRemmberpwd + "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String uid, String uName, String uPassword, boolean uRemmberpwd) {
		super();
		this.uid = uid;
		this.uName = uName;
		this.uPassword = uPassword;
		this.uRemmberpwd = uRemmberpwd;
	}

	
}
