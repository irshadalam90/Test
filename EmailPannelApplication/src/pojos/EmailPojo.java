package pojos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="addemail")
public class EmailPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id")
	Integer id;
	
	@Column(name="email")
	String email;
	
	@Column(name="date")
	String date;
	
	@Column(name="mailFrom")
	String mailFrom;
	
	@Column(name="subAdminId")
	String subAdminId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMailFrom() {
		return mailFrom;
	}
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}
	public String getSubAdminId() {
		return subAdminId;
	}
	public void setSubAdminId(String subAdminId) {
		this.subAdminId = subAdminId;
	}
	
	
	
	
}
