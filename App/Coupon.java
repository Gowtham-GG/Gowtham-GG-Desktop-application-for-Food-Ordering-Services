package App;
import java.util.ArrayList;
import java.util.Scanner;



import java.io.File;
import java.io.IOException;

public class Coupon
{
    ArrayList<String> dataExtracted = new ArrayList<String>();
    public double totalPrice;
    public void couponValidity(String s)
    {
        
        try
        {
            File f = new File("./App/Coupon.txt");
            Scanner dataRead = new Scanner(f);
            if(f.exists() && f.canRead())
            {
                while(dataRead.hasNextLine())
                {
                    String bufferCouponData = dataRead.nextLine();
                    dataExtracted.add(bufferCouponData);
                    //System.out.println("COUPON DATA " + bufferCouponData);
                }
                dataRead.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
 
    }

    public boolean checkValidity(String s, double baseBillPrice)
    {
        boolean flag = false;
        for(String x : dataExtracted)
        {
            if(s.equals(x))
            {
                switch(s)
                {
                    case("CRAVINGS"):
                    {
                        if(baseBillPrice > 250)
                        {
                        
                        flag = true; 
                        }
                        else
                        {
                            System.out.println("Invalid for the amount");
                        }
                        break;
                    }
                    case("YUMYUM"):
                    {
                        if(baseBillPrice > 300)
                        {
                            
                            flag = true;
                        }
                        else
                        {
                            System.out.println("Invalid for the amount");
                        }
                        break;
                    }
                    case("SUPER75"):
                    {
                        if(baseBillPrice > 200)
                        {
                            
                            flag = true;
                        }
                        else
                        {
                            System.out.println("Invalid for the amount");
                        }
                        break;
                    }
                    case("FINGERLICKINGOOD"):
                    {
                        if(baseBillPrice > 750)
                        {
                            
                            flag = true;
                        }
                        else
                        {
                            System.out.println("Invalid for the amount");
                        }
                        break;
                    }
                }
            }
        }
        return flag;

    }

    public double couponDeduction(String s, double baseBillPrice)
    {
        //System.out.println("In deduction");
	    totalPrice = baseBillPrice;
        for(String x : dataExtracted)
        {
            if(s.equals(x))
            {
                switch(s)
                {
                    case("CRAVINGS"):
                    {
                        if(baseBillPrice > 250)
                        {
                        totalPrice = baseBillPrice-50;   
                        }
                        else
                        {
                            System.out.println("Invalid for the amount");
                        }
                        break;
                    }
                    case("YUMYUM"):
                    {
                        if(baseBillPrice > 300)
                        {
                            totalPrice = baseBillPrice-100;
                        }
                        else
                        {
                            System.out.println("Invalid for the amount");
                        }
                        break;
                    }
                    case("SUPER75"):
                    {
                        if(baseBillPrice > 200)
                        {
                            totalPrice = baseBillPrice-75;
                        }
                        else
                        {
                            System.out.println("Invalid for the amount");
                        }
                        break;
                    }
                    case("FINGERLICKINGOOD"):
                    {
                        if(baseBillPrice > 750)
                        {
                            totalPrice = baseBillPrice-200;
                        }
                        else
                        {
                            System.out.println("Invalid for the amount");
                        }
                        break;
                    }
                }
            }
        }
        return totalPrice;
    }
}
