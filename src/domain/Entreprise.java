package domain;

public class Entreprise extends Contact{
	
	private int numSiret;
	
	public Entreprise(){
		super();
		numSiret = 0;
	}

	public int getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(int numSiret) {
		this.numSiret = numSiret;
	}

	
}
