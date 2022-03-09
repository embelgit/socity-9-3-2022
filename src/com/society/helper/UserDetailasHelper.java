package com.society.helper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.society.dao.UserDetailasDao;
import com.society.hibernate.UserDetailasHibernate;

public class UserDetailasHelper 
{
	public void doUserDetails(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("in helper");
		
		String name = request.getParameter("name");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmpassword");
		
		System.out.println("IN HELPER:");
		System.out.println("name-------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+name);
		System.out.println("userName-------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+userName);
		System.out.println("password-------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+password);
		System.out.println("password-------------!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+confirmPassword);
		
		UserDetailasHibernate u = new UserDetailasHibernate();
		
		u.setName(name);
		u.setUserName(userName);
		u.setPassword(password);
		u.setConfirmPassword(confirmPassword);
		
		UserDetailasDao uDao = new UserDetailasDao();
		uDao.valUserDetails(u);
		
	}

}
