package javaes.wordpress.com.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import javaes.wordpress.com.domain.Contact;
import javaes.wordpress.com.domain.ContactService;
import javaes.wordpress.com.domain.IService;
import javaes.wordpress.com.domain.Response;

/**
 * @author https://javaes.wordpress.com/
 *
 */
@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ContactResource {
	private IService contactService = new ContactService();
	@GET
	public List<Contact> get() {
		List<Contact> contact = contactService.getContacts();
		return contact;
	}

	@GET
	@Path("{id}")
	public Contact get(@PathParam("id") long id) {
		Contact c = contactService.getContact(id);
		return c;
	}

	@GET
	@Path("/name/{name}")
	public List<Contact> getByNome(@PathParam("name") String name) {
		List<Contact> contacts = contactService.findByName(name);
		return contacts;
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		contactService.delete(id);
		return Response.Ok("Contato deletado com sucesso");
	}

	@POST
	public Response post(Contact c) {
		contactService.save(c);
		return Response.Ok("Contato salvo com sucesso");
	}

	@PUT
	public Response put(Contact c) {
		contactService.save(c);
		return Response.Ok("Contato atualizado com sucesso");
	}
}
