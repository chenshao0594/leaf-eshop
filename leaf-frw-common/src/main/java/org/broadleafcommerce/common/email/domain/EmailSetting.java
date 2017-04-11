package org.broadleafcommerce.common.email.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EMAIL_SETTING")
public class EmailSetting implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2450613998815456849L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "host", nullable = false)
	private String host;

	@NotNull
	@Column(name = "port", nullable = false)
	private Integer port;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "smtp_security", nullable = false)
	private SMTPSecurityEnum smtpSecurity;

	@NotNull
	@Column(name = "from_address", nullable = false)
	private String fromAddress;

	@NotNull
	@Column(name = "user_name", nullable = false)
	private String userName;

	@NotNull
	@Column(name = "password", nullable = false)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public SMTPSecurityEnum getSmtpSecurity() {
		return smtpSecurity;
	}

	public void setSmtpSecurity(SMTPSecurityEnum smtpSecurity) {
		this.smtpSecurity = smtpSecurity;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
