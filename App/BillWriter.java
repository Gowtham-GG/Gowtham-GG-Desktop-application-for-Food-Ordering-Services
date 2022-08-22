package App;

import java.util.*;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import java.awt.*;

public class BillWriter
{
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime timeStamp = LocalDateTime.now();
    int x;
    BillWriter(JLabel[] dishArray, JLabel[] quantityArray, JLabel[] eachUnitSumArray)
    {
        x = 0;
        System.out.println(dishArray);
        System.out.println(quantityArray);
        System.out.println(eachUnitSumArray);
    }

    void writer()
    {
        int flag = 0;
        try
        {
            File billLog = new File("./BillLog.txt");
            if(billLog.exists())
            {
                flag = 1;
            }
            else
            {
                billLog.createNewFile();
            }

            if(billLog.exists())
            {
                FileWriter pen = new FileWriter("./BillLog.txt", true);
                pen.write(dtf.format(timeStamp));
                pen.write("\n-----------------------Order Summary----------------------\n");
                pen.write("\nFood Item\t|\tQuantity\t|\tPrice\n");

                
            }


        }   
        catch(Exception e)
        {

        }
    }

}