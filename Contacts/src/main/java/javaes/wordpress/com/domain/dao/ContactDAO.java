package javaes.wordpress.com.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javaes.wordpress.com.domain.Contact;

/**
 * @author https://javaes.wordpress.com/
 *
 */
public class ContactDAO extends BaseDAO implements IGenericDAO {

	public Contact getContactById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from contact where idContact=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Contact c = createContact(rs);
				rs.close();
				return c;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Contact> findByName(String name) throws SQLException {
		List<Contact> contacts = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from contact where lower(name) like ?");
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Contact c = createContact(rs);
				contacts.add(c);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return contacts;
	}

	public List<Contact> getContacts() throws SQLException {
		List<Contact> contacts = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("select * from contact");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Contact c = createContact(rs);
				contacts.add(c);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return contacts;
	}

	public Contact createContact(ResultSet rs) throws SQLException {
		Contact c = new Contact();
		c.setIdContact(rs.getLong("idContact"));
		c.setName(rs.getString("name"));
		c.setTelephone(rs.getString("telephone"));
		c.setEmail(rs.getString("email"));
		return c;
	}

	public void save(Contact c) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (c.getIdContact() == null) {
				stmt = conn.prepareStatement("insert into contact (name,telephone,email) VALUES(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("update contact set name=?,telephone=?,email=? where idContact=?");
			}
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getTelephone());
			stmt.setString(3, c.getEmail());
			if (c.getIdContact() != null) {
				// Update
				stmt.setLong(4, c.getIdContact());
			}
			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o contato");
			}
			// Se inseriu, ler o id auto incremento
			if (c.getIdContact() == null) {
				Long id = getGeneratedId(stmt);
				c.setIdContact(id);
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	// id gerado com o campo auto incremento
	public static Long getGeneratedId(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;
	}

	public boolean delete(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("delete from contact where idContact=?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
