package javaes.wordpress.com.domain;

import java.io.Serializable;

/**
 * @author https://javaes.wordpress.com/
 *
 */
public class Contact implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long idContact;
	private String name;
	private String telephone;
	private String email;

	public Contact() {

	}

	public Contact(long idContact, String name, String telephone, String email) {
		super();
		this.idContact = idContact;
		this.name = name;
		this.telephone = telephone;
		this.email = email;
	}

	public Long getIdContact() {
		return idContact;
	}

	public void setIdContact(Long idContact) {
		this.idContact = idContact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Contact [idContact=" + idContact + ", name=" + name + ", telephone=" + telephone + ", email=" + email
				+ "]";
	}
}
