package model;

public class WohnBungalow extends Bungalow 
{
	private static final long serialVersionUID = 1L;
	private int betten;
	private boolean meerblick;
	private int kategorie;
	
	public WohnBungalow(double grundpreis, int nummer, int betten, int kategorie,
			            boolean meerblick) throws ResortException  
	{
		super(grundpreis, nummer);
		setBetten(betten);
		setMeerblick(meerblick);
		setKategorie(kategorie);
	}
    public WohnBungalow(String zeile) throws ResortException
    {
        setAll(zeile);
    }
    public WohnBungalow(String[] zeilenTeile) throws ResortException
    {
        setAll(zeilenTeile);
    }    
	//--------------------------------- getters --------------------------------
	public int getBetten() 
	{
		return betten;
	}
	public int getKategorie() 
	{
		return kategorie;
	}
	public boolean getMeerblick() 
	{
		return meerblick;
	}
	//--------------------------------- setters --------------------------------
	public void setBetten(int betten) throws ResortException  
	{
		if (betten >= 2 && betten <= 7)
			this.betten = betten;
		else
			throw new ResortException("Falscher Parameterwert für Betten  (" +
	                                   betten + ")  bei setBetten !!"); 
	}
	public void setKategorie(int kategorie) throws ResortException  
	{
		if (kategorie >= 1 && kategorie <= 5)
			this.kategorie = kategorie;
		else
			throw new ResortException("Falscher Parameterwert für Kategorie  (" +
					                    kategorie + ")  bei setKategorie !!"); 
	}
	public void setMeerblick(boolean meerblick) 
	{
		this.meerblick = meerblick;
	}
    private void setAll(String zeile) throws ResortException
    {
        try
        {
            setNummer( Integer.parseInt(zeile.substring(0, 3).trim() )  );
            setBetten( Integer.parseInt(zeile.substring(4, 5) )  );
            setMeerblick(  zeile.charAt(6) == 't'?true:false);
            setKategorie( Integer.parseInt(zeile.substring(8, 9)));
            setGrundpreis(  Double.parseDouble(zeile.substring(10,13).trim() )  );
        }
        catch (NumberFormatException nfe)
        {
            throw new ResortException("Parse-Fehler beim Einlesen einer Bungalow-Zeile ("+zeile+") !!");
        }
        catch (IndexOutOfBoundsException iobe)
        {
            throw new ResortException("Index-Fehler beim Einlesen einer Bungalow-Zeile ("+zeile+") !!");
        }
    }
    private void setAll(String[] zeilenTeile) throws ResortException
    {
    	if (zeilenTeile != null)
    	{
	        try
	        {
	        	// WohnBungalow;125; 6;false;1; 200.0
	        	//              [1] [2] [3] [4] [5]
	            setNummer( Integer.parseInt(zeilenTeile[1].trim() )  );
	            setBetten( Integer.parseInt(zeilenTeile[2].trim() )  );
	            setMeerblick(  zeilenTeile[3].trim().equals("true")?true:false);
	            setKategorie( Integer.parseInt(zeilenTeile[4].trim() ) );
	            setGrundpreis(  Double.parseDouble(zeilenTeile[5].trim() ) );
	        }
	        catch (NumberFormatException nfe)
	        {
	            throw new ResortException("Parse-Fehler beim Einlesen einer Bungalow-Zeile !!");
	        }
	        catch (IndexOutOfBoundsException iobe)
	        {
	            throw new ResortException("Index-Fehler beim Einlesen einer Bungalow-Zeile !!");
	        }
    	}
    	else
    		throw new ResortException("Fehler bei setAll(): null-Referenz fuer zeilenTeile erhalten");
    }    
	//--------------------------------- others --------------------------------

    public double berechneTagesertrag()
    {
        double ertrag = 0.;
        if ( ! isFrei() )
        {
            ertrag = getGrundpreis()*betten;
            if (betten == 2)
                ertrag += 50d;
            else
                if (betten == 7)
                    ertrag -= getGrundpreis()/2;
            if (getMeerblick())
                ertrag *= 1.25;
            switch (kategorie)
            {
                case 1:  ertrag *=  .75; break;
                case 3:  ertrag *= 1.15; break;
                case 4:  ertrag *= 1.25; break;
                case 5:  ertrag *= 1.5;  break;
            }
        }
        return ertrag;
    }
    
	// ------------------------- equals / hasCode ----------------
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + betten;
		result = prime * result + kategorie;
		result = prime * result + (meerblick ? 1231 : 1237);
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
		WohnBungalow other = (WohnBungalow) obj;
		if (betten != other.betten)
			return false;
		if (kategorie != other.kategorie)
			return false;
		if (meerblick != other.meerblick)
			return false;
		return true;
	}
    
    // ------------------------ toString -----------------------------
    
    public String toString() 
	{
		return new StringBuffer("WohnBungalow ").append(super.toString()).
				append(" / Tagesertrag: EUR ").append(berechneTagesertrag()).toString();
	}
 

	public String toStringCsv()
    {
    	String sep = ";";
        StringBuilder sb = new StringBuilder(100);
        sb.append("WohnBungalow").append(sep).append(getNummer()).append(sep).append(betten).append(sep).
        append(getMeerblick()).append(sep).append(kategorie).append(sep).append(getGrundpreis());
        return sb.toString();
    }
   
}
