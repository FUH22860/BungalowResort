package test;
import model.BungalowResort;
import model.EventBungalow;
import model.ResortException;

public class TestImport
{
    public static void main(String[] args)
    {
        try
        {
            BungalowResort br = new BungalowResort("New Resort");
            EventBungalow  eb0 = new EventBungalow (500., 222,'L',10); //  2500.-
            br.addBungalow(eb0);
            System.out.println(br); // 1 EventBungalow          
//            br.importWohnBungalows(null);
//            br.importWohnBungalows("x:\\scratch\\wohnBungalows.csv");
            br.importWohnBungalows("c:\\scratch\\wohnBungalows.csv");
            System.out.println(".... import ok....\n");
            System.out.println(br); // 1 EventBungalow, 2 WohnBungalows
            
            br.importWohnBungalows("c:\\scratch\\wohnBungalows.csv"); // Fehler Bungalow steht in der Anlage
            System.out.println(".... import ok....\n");
            System.out.println(br);           
        }
        catch (ResortException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
