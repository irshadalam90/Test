package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import pojos.EmailPojo;
import pojos.LoginPojo;
import dao.Dao;
import service.Service;

public class ServiceImpl implements Service{
	
	private Dao dao;
	
	@Autowired
	public void setDao(Dao dao) {
		System.out.println("dao created");
		this.dao = dao;
	}


	@Override
	public List<EmailPojo> getEmail() {
		List<EmailPojo> emailList = dao.getEmailList();
		return emailList;
	}


	@Override
	public void addEmail(String email , String dateTime , String username, Integer userId) {
		String adminId = Integer.toString(userId);
		EmailPojo epojo = new EmailPojo();
		epojo.setEmail(email);
		epojo.setDate(dateTime);
		epojo.setMailFrom(username);
		epojo.setSubAdminId(adminId);
		dao.addEmail(epojo);
		
	}



	@Override
	public ArrayList<LoginPojo> userList() {
		ArrayList<LoginPojo> record = dao.secureLoginPage();
		
		System.out.println("record");
		return record;
	}


	@Override
	public ArrayList<EmailPojo> showEmail() {
		ArrayList<EmailPojo> emailList = dao.viewEmail();
		return emailList;
	}


	@Override
	public Integer getUserId(String username) {
		Integer id = dao.getPersonalId(username);
	return id;
	
	}


	@Override
	public String getTotalId(Integer userId) {
		String id = dao.getSubAdminId(userId);
		return id;
	}


	


	

	/*@Override
	public List<UserPojo> searchUser(Integer firstAge, Integer secondAge) {
		return dao.userSearch(firstAge,secondAge);
	}*/
	
	
}
