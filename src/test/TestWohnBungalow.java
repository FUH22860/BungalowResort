package test;
import model.ResortException;
import model.WohnBungalow;

public class TestWohnBungalow 
{
	public static void main(String[] args) 
	{
        // ------------------------------  falsch -------------------------------
			try
            {
//                WohnBungalow  wb1 = new WohnBungalow (0., 123,2,5,true); // Fehler grundpreis 0.0
//                WohnBungalow  wb2 = new WohnBungalow (1000., 123,2,5,true); // Fehler grundpreis 1000.0
//                WohnBungalow  wb3 = new WohnBungalow (50., 23,2,5,true); // Fehler nummer 23
//                WohnBungalow  wb4 = new WohnBungalow (50., 923,2,5,true); // Fehler nummer 923
//                WohnBungalow  wb5 = new WohnBungalow (50., 132,2,5,true); // Fehler nummer 132
//                WohnBungalow  wb6 = new WohnBungalow (50., 123,0,5,true); // Fehler betten 0
//                WohnBungalow  wb7 = new WohnBungalow (50., 123,9,5,true); // Fehler betten 9
//                WohnBungalow  wb8 = new WohnBungalow (50., 123,3,0,true); // Fehler kategorie 0
//                WohnBungalow  wb9 = new WohnBungalow (50., 123,3,10,true); // Fehler kategorie 10

                // ------------------------------  korrekt -------------------------------
                System.out.println( "\n");
                WohnBungalow  wb10 = new WohnBungalow (100.,123,4,2,false); // 400.-
                System.out.println(wb10);
                wb10.setFrei(false);
                System.out.println(wb10.berechneTagesertrag());

                WohnBungalow  wb11 = new WohnBungalow (50.,124,2,5,true);   // 281.25
                System.out.println(wb11);
                wb11.setFrei(false);
                System.out.println(wb11.berechneTagesertrag());

                WohnBungalow  wb12 = new WohnBungalow (200.,125,6,1,false); // 900.-
                System.out.println(wb12);
                wb12.setFrei(false);
                System.out.println(wb12.berechneTagesertrag());
                
                WohnBungalow  wb13 = new WohnBungalow (200.,125,6,1,false); // 0.-
                System.out.println(wb13);
                wb12.setFrei(true);
                System.out.println(wb13.berechneTagesertrag());               

            }
            catch (ResortException e)
            {
                System.out.println(e.getMessage());
            }
	}

}
