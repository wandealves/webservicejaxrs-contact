package javaes.wordpress.com.domain;

import java.util.List;

/**
 * @author https://javaes.wordpress.com/
 *
 */
public interface IService {
	List<Contact> getContacts();

	Contact getContact(Long id);

	boolean delete(Long id);

	boolean save(Contact contact);

	List<Contact> findByName(String name);
}
