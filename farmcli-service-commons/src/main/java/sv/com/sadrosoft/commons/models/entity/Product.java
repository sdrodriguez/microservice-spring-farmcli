/**
 * 
 */
package sv.com.sadrosoft.commons.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author DevLider
 *
 */
@Entity
@Table(name = "pruducts")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3252781817821418633L;
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "prd_name")
	private String prdName;
	@Column(name = "prd_price")
	private Double prdPrice;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	
	@Transient
	private Integer port;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public Double getPrdPrice() {
		return prdPrice;
	}
	public void setPrdPrice(Double prdPrice) {
		this.prdPrice = prdPrice;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
}
