package test;
import model.BungalowResort;
import model.EventBungalow;
import model.ResortException;
import model.WohnBungalow;

public class TestResort
{
    public static void main(String[] args)
    {
        try
        {
            BungalowResort br; 
//            br = new BungalowResort(null);
            br = new BungalowResort("Le Resort");

            WohnBungalow  wb10 = new WohnBungalow (100.,123,4,2,false); //  400.-
            wb10.setFrei(false);
            WohnBungalow  wb11 = new WohnBungalow (50.,125,2,5,true);  //   281.25
            wb11.setFrei(false);

            EventBungalow  eb4 = new EventBungalow (200., 701,'L',10); //  2500.-
            eb4.setFrei(false);
            EventBungalow  eb5 = new EventBungalow (100., 702,'S',100);// 11000.-
            eb5.setFrei(false);
            EventBungalow  eb6 = new EventBungalow (100., 703,'M',50);//   6500.-
            eb6.setFrei(false);

//            br.addBungalow(null);
//            for (int i =  0; i < 101; i++)
//                br.addBungalow( new EventBungalow (100., 703,'M',50) );
//            br.addBungalow(eb4);
            br.addBungalow(eb4);
            br.addBungalow(wb11);
            br.addBungalow(eb6);
            br.addBungalow(wb10);
            br.addBungalow(eb5);
            System.out.println(br);
            
            System.out.println("Sortiere Nummer: ");
            br.sortBungalowsNummer();
            System.out.println(br);
            
            System.out.println("Sortiere Tagesertrag: ");
            br.sortBungalowsTagesertrag();
            System.out.println(br);
            
            System.out.println("Sortiere Preis: ");
			br.sortBungalowsPreis();
            System.out.println(br);
            
            System.out.println("Tagesertrag:" +br.berechneTagesertrag());  // 20681.25
            System.out.println("Betten:" +br.berechneAnzBettenBelegt());   // 6
            wb10.setFrei(true);
            System.out.println("Betten:" +br.berechneAnzBettenBelegt());   // 2
//            br.removeBungalow(-1);
//            br.removeBungalow(5);
            br.removeBungalow(1);
            System.out.print("Remove 1\n"+br);
            
            System.out.print("Remove 24.: -> "); 
            System.out.println(br.removeBungalows(24.));  // 0
            System.out.println(br);
            System.out.print("Remove 150.: -> "); 
            System.out.println(br.removeBungalows(150.));  // 3
            System.out.println(br);
            
            // Testfaelle fuer erhoeheGrundpreis
            //EventBungalow  eb4 = new EventBungalow (200., 701,'L',10); //  2500.-
            System.out.println(eb4); // 200.0
//            eb4.erhoeheGrundpreis(0); // Fehler 0
            eb4.erhoeheGrundpreis(100); // 100 Prozent
            System.out.println(eb4); // 400.0
            eb4.erhoeheGrundpreis(200); // 200 Prozent
            System.out.println(eb4); // 1200.0 
            eb4.erhoeheGrundpreis(10); // 10 Prozent
            System.out.println(eb4); // 1320.0             
            eb4.erhoeheGrundpreis(1); // 1 Prozent
            System.out.println(eb4); // 1333.2 
 
            System.out.println();
            System.out.println(br); // 1 EventBungalow               
//          br.saveBungalows(null);
//          br.saveBungalows("x:\\scratch\\bungalows.ser");
            br.saveBungalows("c:\\scratch\\bungalows.ser");
            System.out.println(".... save ok....\n");
          
//          System.out.println();
//          System.out.println(br);               
////        br.exportWohnBungalows(null);
////        br.exportWohnBungalows("x:\\scratch\\wohnBungalows.csv");
//        	br.exportWohnBungalows("c:\\scratch\\wohnBungalows.csv"); // leer, keine Wohnbungalows
//        	System.out.println(".... export ok....\n");   
          
          br.addBungalow(wb11);
          br.addBungalow(wb10);
      
          System.out.println();
          System.out.println(br); // 1 EventBungalow, 2 WohnBungalows              
          br.exportWohnBungalows("c:\\scratch\\wohnBungalows.csv"); // 2 WohnBungalows
          System.out.println(".... export ok....\n");         
        
        }
        catch (ResortException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
