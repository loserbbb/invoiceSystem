package cn.gavid.login;

import org.junit.Test;

import cn.gavid.invoiceSystem.login.dao.UserDao;
import cn.gavid.invoiceSystem.login.domain.User;

public class LoginDaoTest {

	@Test
	public void fun1(){
		UserDao userdao = new UserDao();
		User user = userdao.findByName("admin");
		System.out.println(user);
	}
}
