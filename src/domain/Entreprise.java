package domain;

public class Entreprise extends Contact{
	
	private int numSiret;
	
	public Entreprise(){
		super();
		numSiret = 0;
	}
	
	public Entreprise(String firstName, String lastName, String email, int numSiret){
		super(firstName,lastName,email);
		this.numSiret=numSiret;
	}
	
	public int getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(int numSiret) {
		this.numSiret = numSiret;
	}

	
}
