package dao;

import java.util.ArrayList;
import java.util.List;

import pojos.EmailPojo;
import pojos.LoginPojo;


public interface Dao {


	public List<EmailPojo> getEmailList();

	public void addEmail(EmailPojo email);

	public ArrayList<LoginPojo> secureLoginPage();

	public ArrayList<EmailPojo> viewEmail();

	public Integer getPersonalId(String username);

	public String getSubAdminId(Integer userId);

	
}
