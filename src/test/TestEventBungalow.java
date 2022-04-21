package test;
import model.EventBungalow;
import model.ResortException;

public class TestEventBungalow 
{
	public static void main(String[] args) 
	{
	        // ------------------------------  falsch -------------------------------
			try
            {
//                EventBungalow  eb1 = new EventBungalow (50., 701,'X',50); // Fehler ausstattung X
//                EventBungalow  eb2 = new EventBungalow (50., 701,'L',5); // Fehler personen 5
//                EventBungalow  eb3 = new EventBungalow (50., 701,'M',500); // Fehler personen 500

                // ------------------------------  korrekt -------------------------------
                EventBungalow  eb4 = new EventBungalow (200., 701,'L',10); //  2500.-
                eb4.setFrei(false);
                System.out.println(eb4);
                System.out.println(eb4.berechneTagesertrag());
                
                EventBungalow  eb5 = new EventBungalow (100., 702,'S',100);// 11000.-
                eb5.setFrei(false);
                System.out.println("\n"+eb5);
                System.out.println(eb5.berechneTagesertrag());

                EventBungalow  eb6 = new EventBungalow (100., 703,'M',50);//  6500.-
                eb6.setFrei(false);
                System.out.println("\n"+eb6);
                System.out.println(eb6.berechneTagesertrag());
                
                EventBungalow  eb7 = new EventBungalow (100., 703,'M',50);//  0.-
                eb6.setFrei(true);
                System.out.println("\n"+eb7);
                System.out.println(eb7.berechneTagesertrag());                
            }
            catch (ResortException e)
            {
                System.out.println(e.getMessage());
            }

	}

}
