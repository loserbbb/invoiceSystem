package cn.gavid.invoiceSystem.login.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.gavid.invoiceSystem.login.domain.User;
import cn.itcast.jdbc.TxQueryRunner;


public class UserDao {

	private QueryRunner qr = new TxQueryRunner();
	public User findByName(String username) {
		try {
			String sql = "select * from tb_userInfo where uName = ?";
			Object[] params = {username};
			return qr.query(sql, new BeanHandler<User>(User.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
