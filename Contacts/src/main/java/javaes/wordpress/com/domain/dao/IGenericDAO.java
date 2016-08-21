package javaes.wordpress.com.domain.dao;

import java.sql.SQLException;
import java.util.List;

import javaes.wordpress.com.domain.Contact;

/**
 * @author https://javaes.wordpress.com/
 *
 */
public interface IGenericDAO {
	Contact getContactById(Long id) throws SQLException;
	List<Contact> findByName(String name) throws SQLException;
	List<Contact> getContacts() throws SQLException;
	void save(Contact c) throws SQLException;
	boolean delete(Long id) throws SQLException;
}
