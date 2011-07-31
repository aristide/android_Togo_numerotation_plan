package togo.plannumerotation;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.Contacts.People.Phones;

/**
 * 
 * @author aristide mendoo.aristide@gmail.com
 * 
 *         interact whith phone contacts to retrieve and update contact
 *         informations
 */
public class ContactsInfos {

	Context context;
	ArrayList<Contact> newContacts;
	ArrayList<Contact> oldContacts;
	ArrayList<Modifications> modifications;
	ArrayList<String> noms;
	ArrayList<String> ancienNum;
	ArrayList<String> nouveaNum;
	private PhoneNumberUpdater updater;
	boolean updatecontent;

	public ContactsInfos(Context context) {
		this.context = context;

		newContacts = new ArrayList<Contact>();
		oldContacts = new ArrayList<Contact>();
		modifications = new ArrayList<Modifications>();
		noms = new ArrayList<String>();
		ancienNum = new ArrayList<String>();
		nouveaNum = new ArrayList<String>();

		updater = new PhoneNumberUpdater();
		updatecontent = false;
	}

	// execute upgrade/update instructions
	public boolean RunUpdate() {

		newContacts = new ArrayList<Contact>();
		oldContacts = new ArrayList<Contact>();
		modifications = new ArrayList<Modifications>();
		updatecontent = true;
		updateContact();
		if (newContacts.size() > 0) {
			insertContacts(newContacts);
			return true;
		} else {
			return false;
		}
	}

	// execute the Downgrade instructions
	public boolean RunDowngrade() {
		newContacts = new ArrayList<Contact>();
		oldContacts = new ArrayList<Contact>();
		modifications = new ArrayList<Modifications>();
		updatecontent = false;
		noms = new ArrayList<String>();
		ancienNum = new ArrayList<String>();
		nouveaNum = new ArrayList<String>();
		DownGrade();
		if (oldContacts.size() > 0) {
			insertContacts(oldContacts);
			return true;
		} else {
			return false;
		}
	}

	// do contact treatments
	public void updateContact() {

		Cursor c = context.getContentResolver().query(
				Contacts.Phones.CONTENT_URI, null, null, null, null);
		String currentNumber = "";
		String updatenumber = "";
		try {

			while (c.moveToNext()) {

				Contact contact = new Contact(c.getString(c
						.getColumnIndex(Phones._ID)), c.getString(c
						.getColumnIndex("person")), c.getString(c
						.getColumnIndex(Phones.NUMBER)), c.getString(c
						.getColumnIndex(Phones.NAME)));
				currentNumber = updater.removeSpace(contact.PhoneNumber);
				updatenumber = updater.removeSpace(updater
						.Update(contact.PhoneNumber));

				if (!currentNumber.equalsIgnoreCase(updatenumber)) {
					oldContacts.add(contact);

					contact.updatePhoneNumber(updatenumber);
					newContacts.add(contact);
					modifications.add(new Modifications(contact.name,
							currentNumber, contact.PhoneNumber));
					noms.add(contact.name);
					ancienNum.add(currentNumber);
					nouveaNum.add(contact.PhoneNumber);

				}
				// Toast.makeText(context,message, Toast.LENGTH_LONG).show();
			}
		} finally {
			c.close();
		}
	}

	// insert or update list of modification in the mobile
	public void insertContacts(ArrayList<Contact> contacts) {
		ContentValues values = new ContentValues();
		for (int i = 0; i < contacts.size(); i++) {
			values.clear();
			values.put(Phones.NUMBER, contacts.get(i).PhoneNumber);
			Uri updateContactUri = Uri.withAppendedPath(
					Contacts.Phones.CONTENT_URI, contacts.get(i).PhoneNumer_id);
			context.getContentResolver().update(updateContactUri, values,
					Phones._ID + "=" + contacts.get(i).PhoneNumer_id, null);
		}
	}

	// not used
	public void DownGrade() {

	}

	// not used
	public ArrayList<Contact> getChanges() {
		if (updatecontent)
			return newContacts;
		else
			return oldContacts;
	}

	// return Modification arraylist of all changes
	public ArrayList<Modifications> getModifications() {
		return modifications;
	}

	// string arraylist of all contacts name from which changes
	// have been done
	public ArrayList<String> getNom() {
		return noms;
	}

	// string arraylist of old contact number
	public ArrayList<String> getanciemNum() {
		return ancienNum;
	}

	// string arraylist of new contact number
	public ArrayList<String> getnouveauNum() {
		return nouveaNum;
	}

}
