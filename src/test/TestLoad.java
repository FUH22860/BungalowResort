package test;
import model.BungalowResort;
import model.EventBungalow;
import model.ResortException;

public class TestLoad
{
    public static void main(String[] args)
    {
        try
        {
            BungalowResort br = new BungalowResort("New Resort");
            EventBungalow  eb0 = new EventBungalow (500., 222,'L',10); //  2500.-
            br.addBungalow(eb0);
            System.out.println(br); // 1 Bungalow
            System.out.println();
            
//            br.loadBungalows(null);
//            br.loadBungalows("x:\\scratch\\bungalows.ser");
            br.loadBungalows("c:\\scratch\\bungalows.ser");
            System.out.println(".... load ok....\n");
            System.out.println(br); // 2 Bungalows
            
            br.loadBungalows("c:\\scratch\\bungalows.ser"); // Fehler Bungalow steht in der Anlage
            System.out.println(".... load ok....\n");
            System.out.println(br);            
        }
        catch (ResortException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
