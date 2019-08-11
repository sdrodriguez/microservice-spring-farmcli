package sv.com.sadrosoft.users.commons.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "sg_users")
public class SgUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 20)
	private String sgUserName;
	@Column(length = 60)
	private String password;
	private Boolean enabled;
	private String sgName;
	private String sglastName;
	@Column(unique = true)
	private String sgEmail;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_rols", joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "rol_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","rol_id"})})
	private List<SgRole> rols;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSgUserName() {
		return sgUserName;
	}
	public void setSgUserName(String sgUserName) {
		this.sgUserName = sgUserName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getSgName() {
		return sgName;
	}
	public void setSgName(String sgName) {
		this.sgName = sgName;
	}
	public String getSglastName() {
		return sglastName;
	}
	public void setSglastName(String sglastName) {
		this.sglastName = sglastName;
	}
	public String getSgEmail() {
		return sgEmail;
	}
	public void setSgEmail(String sgEmail) {
		this.sgEmail = sgEmail;
	}
	public List<SgRole> getRols() {
		return rols;
	}
	public void setRols(List<SgRole> rols) {
		this.rols = rols;
	}
	
	
	
}
