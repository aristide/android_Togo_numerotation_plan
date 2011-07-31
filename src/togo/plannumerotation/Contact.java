package togo.plannumerotation;


/**
 * 
 * @author aristide mendoo.aristide@gmail.com j2me/android developper
 * 
 *         contact object
 */
public class Contact {

	public String name;
	public String id;
	public String PhoneNumber;
	public String PhoneNumer_id;

	public Contact(String PhoneNumer_id, String id, String PhoneNumber,
			String name) {
		this.name = name;
		this.id = id;
		this.PhoneNumber = PhoneNumber;
		this.PhoneNumer_id = PhoneNumer_id;
	}

	public void updatePhoneNumber(String PhoneNumber) {
		this.PhoneNumber = PhoneNumber;
	}
}
