package cn.gavid.invoiceSystem.login.service;

import cn.gavid.invoiceSystem.login.dao.UserDao;
import cn.gavid.invoiceSystem.login.domain.User;

public class UserService {

	private UserDao userDao = new UserDao();
	public User login(User user) throws UserException {

		User user1 = userDao.findByName(user.getuName());
		if(user1==null)
			throw new UserException("该用户不存在！！");
		if(!user1.getuPassword().equals(user.getuPassword()))
			throw new UserException("密码错误！！");
		return user1;
	}

}
