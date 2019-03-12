package dao.impl;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import dao.Dao;
import pojos.EmailPojo;
import pojos.LoginPojo;


public class DaoEmailImpl extends DBConnectionDao implements Dao{
	@Autowired
	private JdbcTemplate jdbcTemplate;  
	  
	private DataSource dataSource;
	  @Autowired
	  public void setDataSource(DataSource dataSource)  {
		
	    this.dataSource=dataSource;
	      this.jdbcTemplate = new JdbcTemplate(this.dataSource);   
	     }

	@Override
	public List<EmailPojo> getEmailList() {
		// TODO Auto-generated method stub
		return hibernateTemp.findByCriteria(DetachedCriteria.forClass(EmailPojo.class));
	}

	@Override
	public void addEmail(EmailPojo email) {
		hibernateTemp.save(email);
	}

	@Override
	public ArrayList<LoginPojo> secureLoginPage() {
		return (ArrayList<LoginPojo>) hibernateTemp.findByCriteria(DetachedCriteria.forClass(LoginPojo.class));
	}

	@Override
	public ArrayList<EmailPojo> viewEmail() {
		
		return (ArrayList<EmailPojo>) hibernateTemp.findByCriteria(DetachedCriteria.forClass(EmailPojo.class));
	}

	@Override
	public Integer getPersonalId(String username) {
		return(Integer) jdbcTemplate.queryForObject("select id from admin where userName='"+username+"'",new RowMapper<Integer>(){  
			public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {  
				
				Integer id=0;
				
				id = rs.getInt("id");
				
		        return id; 
		    }  
		    });
		    
	}

	@Override
	public String getSubAdminId(Integer userId) {
		/*return (List<EmailPojo>) jdbcTemplate.query("SELECT COUNT(subAdminId) FROM addemail  WHERE subAdminId= "+userId+" ",new RowMapper<EmailPojo>(){  
		    @Override  
		    public EmailPojo mapRow(ResultSet rs, int rownumber) throws SQLException {  
		    	EmailPojo id= new EmailPojo();  
		    	id.setSubAdminId(rs.getString("subAdminId"));
		        return id;  
		    }  
		});*/
		return(String) jdbcTemplate.queryForObject("SELECT COUNT(subAdminId) FROM addemail  WHERE subAdminId= '"+userId+"' ",new RowMapper<String>(){  
			public String mapRow(ResultSet rs, int rownumber) throws SQLException {  
				
				String id="";
				
				id = rs.getString("COUNT(subAdminId)");
				
		        return id; 
		    }  
		    });
	}

}
