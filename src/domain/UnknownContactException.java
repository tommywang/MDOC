package domain;

public class UnknownContactException extends Throwable{

	private static final long serialVersionUID = 1L;

	public String toString(Contact contact){
		return "Unknown Contact : " + contact.getFirstName() + contact.getLastName();
	}
}
