package model;

public class EventBungalow extends Bungalow 
{

	private static final long serialVersionUID = 1L;
	private int personen;
	private char ausstattung;
	
	public EventBungalow(double grundpreis, int nummer, char ausstattung, int personen) throws ResortException
			                        
	{
		super(grundpreis, nummer);
		setAusstattung(ausstattung);
		setPersonen(personen);
	}
	//----------------------------------------- getters ------------------------
	public char getAusstattung() 
	{
		return ausstattung;
	}
	public int getPersonen() 
	{
		return personen;
	}
	//----------------------------------------- setters ------------------------
	public void setAusstattung(char ausstattung) throws ResortException  
	{
		if (ausstattung == 'L'  || ausstattung == 'S'  || ausstattung == 'M'   )
			this.ausstattung = ausstattung;
		else
			throw new ResortException("Falscher Parameterwert für Ausstattung  (" +
	                                   ausstattung + ")  bei setAusstattung !!"); 
	}
	public void setPersonen(int personen) throws ResortException  
	{
		if (personen >= 10 && personen <= 100)
			this.personen = personen;
		else
			throw new ResortException("Falscher Parameterwert für Personen  (" +
	                                   personen + ")  bei setPersonen !!"); 
	}
	//----------------------------------------- others ------------------------
	
    public double berechneTagesertrag()
    {
    	double ertrag = 0.;
    	if ( ! isFrei() )
    	{
	        ertrag = 500. + getGrundpreis()*personen;
	        if (ausstattung == 'S')
	            ertrag += personen*5.;
	        else
	            if (ausstattung == 'M')
	                ertrag += 1000.;
	        return ertrag;
    	}
    	
    		return 0d;
    }
    
	// ------------------------- equals / hasCode ----------------
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ausstattung;
		result = prime * result + personen;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventBungalow other = (EventBungalow) obj;
		if (ausstattung != other.ausstattung)
			return false;
		if (personen != other.personen)
			return false;
		return true;
	}
	//---------------------------toString ------------------------
	public String toString()
	{
		return new StringBuffer("EventBungalow ").append(super.toString()).
				   append(" / Tagesertrag: EUR ").append(berechneTagesertrag()).toString();
	}    

}
