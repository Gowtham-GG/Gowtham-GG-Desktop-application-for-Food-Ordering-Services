package App;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Menu 
{  
    String[] dishStripped; String priceStripped; int i = 1; String foodName;
    ArrayList<String> sapadu = new ArrayList<String>();
    ArrayList<Double> panam = new ArrayList<Double>();
    
    public void menuCatalogue(){ 
    
        
    try 
    {
        
        File f = new File("./App/Menu.txt");
        File x = new File("./App/ReferenceMenuPrice.txt");
        Scanner dataRead = new Scanner(f);
        Scanner referenceRead = new Scanner(x);
        if(f.exists() && f.canRead())
        {
            
            while(dataRead.hasNextLine())
            {
                String bufferMenuData = dataRead.nextLine();
                System.out.println(bufferMenuData);
                
            }
            dataRead.close();
            
        }
        
            
        
    } 
    catch (IOException e) 
    {
        e.printStackTrace();
    }
}
}
