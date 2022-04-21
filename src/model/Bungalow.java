package model;
import java.io.Serializable;
import java.util.Comparator;

public abstract class Bungalow implements Comparable <Bungalow>, Serializable
{
	private static final long serialVersionUID = 1L;
	private int nummer;
	private boolean frei;
	private double grundpreis;
	
	public Bungalow(double grundpreis, int nummer) throws ResortException
	{
		setGrundpreis(grundpreis);
		setNummer(nummer);
		setFrei(true);
	}
    public Bungalow()
    {
        
    }
	// ------------------------------ getter ------------
	public boolean isFrei() 
	{
		return frei;
	}
	public double getGrundpreis() 
	{
		return grundpreis;
	}
	public int getNummer() 
	{
		return nummer;
	}
	// ------------------------------ setter ------------
	public void setFrei(boolean frei) 
	{
		this.frei = frei;
	}
	public void setGrundpreis(double grundpreis) throws ResortException  
	{
		if (grundpreis >= 25. && grundpreis <= 500.)
			this.grundpreis = grundpreis;
		else
			throw new ResortException("Falscher Parameterwert fuer Grundpreis  (" +
	                   grundpreis + ")  bei setGrundpreis !!"); 
	}
	public void setNummer(int nummer) throws ResortException 
	{
		if ( (nummer/100) >= 1  &&  (nummer/100) <= 7  && 
			  nummer - (nummer/100)*100 >= 1 &&  nummer - (nummer/100)*100 <= 25 )
			this.nummer = nummer;
		else 
			throw new ResortException("Falscher Parameterwert fuer Nummer  (" +
					                   nummer + ")  bei setNummer !!"); 
	}
	// ------------------------------ others ------------

    public abstract double berechneTagesertrag();

	public void erhoeheGrundpreis(int proz) throws ResortException
	{
		if (proz > 0)
		{
			grundpreis *= 1.0 + proz/100.0;
		}
		else
			throw new ResortException("Falscher Parameterwert fuer proz  (" +
	                   proz + ")  bei erhoeheGrundpreis !!"); 			
	}
    
    // ------------------------------- Comparatoren ----------------------
//    public static class NummernComparator implements Comparator<Bungalow>
//    {
//        public int compare(Bungalow b1, Bungalow b2)
//        {
//            return b1.getNummer()-b2.getNummer();
//        }
//    }
    public static class PreisComparator implements Comparator<Bungalow>
    {
        public int compare(Bungalow b1, Bungalow b2)
        {
            return Double.compare(b1.getGrundpreis(), b2.getGrundpreis());
        }
    }
    public static class ErtragsComparator implements Comparator<Bungalow>
    {
        public int compare(Bungalow b1, Bungalow b2)
        {
            return Double.compare(b1.berechneTagesertrag(), b2.berechneTagesertrag());
        }
    }
 
	public int compareTo(Bungalow b) {
		if (b != null)
			return nummer - b.nummer;
		else
			return 1;
	}    
    
//	public int compareTo(Bungalow b) {
//		if (b != null)
//		{
//			double diff = grundpreis - b.getGrundpreis();
//			if (diff > 0)
//				return 1;
//			else
//				if (diff < 0)
//					return -1;
//				else
//					return 0;
//		}
//		else
//			return 1;
//
	
	// ------------------------- equals / hasCode ----------------

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (frei ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(grundpreis);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + nummer;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bungalow other = (Bungalow) obj;
		if (frei != other.frei)
			return false;
		if (Double.doubleToLongBits(grundpreis) != Double.doubleToLongBits(other.grundpreis))
			return false;
		if (nummer != other.nummer)
			return false;
		return true;
	}
	// ------------------------------ toString -------------------
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" # ").append(nummer).append(" / Grundpreis pro Person:  EUR ").
		   append(grundpreis).append(" -> ");
		if (frei)
			sb.append("frei");
		else
			sb.append("belegt");
		return sb.toString();
	}	
	
}
