package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import pojos.EmailPojo;
import pojos.LoginPojo;


public interface Service {

	public List<EmailPojo> getEmail();

	public void addEmail(String addemail, String dateTime, String username, Integer userId);

	public ArrayList<LoginPojo> userList();

	public ArrayList<EmailPojo> showEmail();

	public Integer getUserId(String username);

	public String getTotalId(Integer userId);


	

}
