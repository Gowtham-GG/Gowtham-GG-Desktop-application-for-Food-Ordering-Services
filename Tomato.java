import App.Menu;
import App.BillWriter;
import App.Coupon;

import java.awt.*;
import java.awt.event.*;



import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;


import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Baseui
{
    
    JFrame mainFrame = new JFrame("Tomato");
    
    JLabel label, menu; JLabel paymemtTitle;
    JButton buttonNext;
    JButton nextButton;
    

    
    ArrayList<String> dishDisplayed = new ArrayList<String>();
    ArrayList<Double> dishPrice = new ArrayList<Double>();
    ArrayList<Integer> dishIndex = new ArrayList<Integer>();
    ArrayList<Double> priceAccumulated = new ArrayList<Double>();
    

    ArrayList<String> cartItem = new ArrayList<String>();
    ArrayList<Integer> cartQuantity = new ArrayList<Integer>();

    

    JButton[] buttonArrayDecrement;
    JButton[] buttonArrayIncrement;
    JTextField[] quantityText;
    JLabel[] DishLabel;
    int[] quantityRecord;
    int flag[];

    String dishStripped, priceStripped, choiceStripped, foodName, paymentMethod, currentDish, dish_name, coupon, username, userAddress, mobileNo;
    int z, i = 1, numberInField, currentQuantity;
    double basePrice, price, deductPrice;
    
    
    
    
    
    
    Baseui()
    {
        
        try
        {
            File x = new File("./App/ReferenceMenuPrice.txt");
            Scanner referenceRead = new Scanner(x);
            if(x.exists() && x.canRead())
            {
                while(referenceRead.hasNextLine())
                {
                    String bMenuData = referenceRead.nextLine();
                    
                    
                    int index = bMenuData.indexOf("-")+1;
                    
                    int choiceIndex = bMenuData.indexOf(".");
                    dishStripped = bMenuData.substring(choiceIndex+1, index-1);
                    choiceStripped = bMenuData.substring(0, choiceIndex);
                    

                    priceStripped = bMenuData.substring(index);
                    
                    foodName = dishStripped;
                    
                    dishDisplayed.add(foodName);
                    dishPrice.add(Double.parseDouble(priceStripped));
                    dishIndex.add(Integer.parseInt(choiceStripped));
                    
                }
                
                
                buttonArrayDecrement = new JButton[dishDisplayed.size()];
                buttonArrayIncrement = new JButton[dishDisplayed.size()];
                quantityText = new JTextField[dishDisplayed.size()];
                DishLabel = new JLabel[dishDisplayed.size()];
                quantityRecord = new int[dishDisplayed.size()];
                flag = new int[dishDisplayed.size()];


                i = i + 1;

                dishStripped = ""; priceStripped= ""; choiceStripped = ""; foodName = ""; paymentMethod = ""; 
                currentDish = ""; dish_name = ""; coupon = "N/A"; username = "";
                userAddress = ""; mobileNo = "";
                z = 0; i = 1; numberInField = 0; currentQuantity = 0;
                basePrice = 0; price = 0; deductPrice = 0;
        
                
            }
        }
        catch(Exception e)
        {

        }
    }
    
    void userInfo()
    {
        
        mainFrame.setContentPane(new JLabel(new ImageIcon("./App/tomatobg.jpg")));
        
        
        
        
        
        JLabel titleLabel = new JLabel("Customer Details");
        titleLabel.setBounds(235, 70, 120, 20);
        titleLabel.setForeground(Color.white);
        mainFrame.add(titleLabel);
        
        JLabel usernameLabel = new JLabel("Customer Name : ");
        usernameLabel.setBounds(175, 120, 120, 20);
        usernameLabel.setForeground(Color.white);
        mainFrame.add(usernameLabel);

        JTextField usernameGet = new JTextField();
        usernameGet.setBounds(295, 122, 150, 20);
        mainFrame.add(usernameGet);

        

        JLabel userMobileLabel = new JLabel("Mobile Number  : ");
        userMobileLabel.setBounds(175, 160, 120, 20);
        userMobileLabel.setForeground(Color.white);
        mainFrame.add(userMobileLabel);

        JTextField userMobileGet = new JTextField();
        userMobileGet.setBounds(295, 162, 150, 20);
        mainFrame.add(userMobileGet);

        
        JLabel useraddressLabel = new JLabel("Address              : ");
        useraddressLabel.setBounds(175, 200, 120, 20);
        useraddressLabel.setForeground(Color.white);
        mainFrame.add(useraddressLabel);

        JTextField userAddressGet = new JTextField();
        userAddressGet.setBounds(295, 202, 150, 20);
        mainFrame.add(userAddressGet);


        nextButton = new JButton("Next");
        nextButton.setBounds(365, 240, 80, 20);
        
        JLabel warning = new JLabel();
        warning.setBounds(175, 240, 100, 20);
        warning.setForeground(Color.ORANGE);
        mainFrame.add(warning);
        
        nextButton.addActionListener
        (
            next ->
            {
                username = usernameGet.getText();
                mobileNo = userMobileGet.getText();
                userAddress = userAddressGet.getText();

                
                

                if(username.length() >= 3 & mobileNo.length() == 10 & userAddress.length() >= 5)
                {
                    
                    mainFrame.getContentPane().removeAll();
                    mainFrame.repaint();
                    mockupui(quantityRecord);

                    int flag = 0;
        try
        {
            
            File billLog = new File("./App/CustomerDetails.txt");
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
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime timeStamp = LocalDateTime.now();
            
                FileWriter pen = new FileWriter("./App/CustomerDetails.txt", true);
                pen.write(dtf.format(timeStamp));
                pen.write("\nCustomer Name : " + username + "\n");
                pen.write("Mobile No.: " + mobileNo + "\n");
                pen.write("Address :" + userAddress + "\n");
                pen.write("----------------------------------------------------------" + "\n" + "\n");
                pen.write("\n");
                pen.write("\n");
  pen.close();   
            }
}   
        catch(Exception e)
        {

        }
                }
               else
                {
                    
                    warning.setText("Enter valid text");
                }
            }
        );
        
        mainFrame.getRootPane().setDefaultButton(nextButton);

        mainFrame.add(nextButton);
    
        mainFrame.setSize(600, 900);
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        
    }   

    

    void mockupui(int[] oldQuantity)
    {
        
        cartItem.clear();
        cartQuantity.clear();;

        label = new JLabel("Menu");
        label.setForeground(Color.white);
        label.setBounds(200, 10, 100, 20);
    
        for(z = 0; z < dishDisplayed.size(); z++)
        {   
            DishLabel[z] = new JLabel(dishDisplayed.get(z));
            DishLabel[z].setBounds(40, 40*(z+1), 130, 15);
            DishLabel[z].setForeground(Color.white);
            mainFrame.add(DishLabel[z]);

            buttonArrayDecrement[z] = new JButton("-");
            buttonArrayDecrement[z].setBounds(260, 40 * (z + 1), 50, 20);
            
            buttonArrayDecrement[z].addActionListener
            (
                decrement -> 
                {
                    for(int gg = 0; gg < buttonArrayDecrement.length; gg++)
                    {   
            
                        if(decrement.getSource() == buttonArrayDecrement[gg])
                        {
                            numberInField = Integer.parseInt(quantityText[gg].getText());
                            if(numberInField <= 0)
                            {
                                quantityText[gg].setText("0");
                            }
                            else
                            {
                                quantityText[gg].setText((numberInField - 1) + "");
                            }
                            
                        }
            
                    }
                }
            );
            mainFrame.add(buttonArrayDecrement[z]);
            

            quantityText[z] = new JTextField();
            quantityText[z].setBounds(320, 40 * (z + 1), 50, 25);
            quantityText[z].setText(oldQuantity[z] + "");
            mainFrame.add(quantityText[z]);


            buttonArrayIncrement[z] = new JButton("+");
            buttonArrayIncrement[z].setBounds(380, 40 * (z + 1), 50, 20 );
            
            buttonArrayIncrement[z].addActionListener
            (
                increment -> 
                {
                    for(int gg = 0; gg < buttonArrayIncrement.length; gg++)
                    {   
            
                        if(increment.getSource() == buttonArrayIncrement[gg])
                        {
                            numberInField = Integer.parseInt(quantityText[gg].getText());
                            quantityText[gg].setText((numberInField + 1) + "");
                            
                        }
            
                    }
                }
            );
            mainFrame.add(buttonArrayIncrement[z]);
        

        }

        JLabel warning = new JLabel();
        warning.setBounds(40, 40 * (z + 2), 200, 20);
        warning.setForeground(Color.orange);
        mainFrame.add(warning);
        
        

        nextButton = new JButton("Next");
        nextButton.setBounds(310, 40 * (z+1), 70, 20);

        mainFrame.getRootPane().setDefaultButton(nextButton);

        nextButton.addActionListener
        (
            next ->
            {
                for(int mu = 0; mu < dishDisplayed.size(); mu++)
                {
                    if(Integer.parseInt(quantityText[mu].getText()) != 0)
                    {
                        cartQuantity.add(Integer.parseInt(quantityText[mu].getText()));
                        cartItem.add(dishDisplayed.get(mu));
                        quantityRecord[mu] = Integer.parseInt(quantityText[mu].getText());

                    }
                    else
                    {
                        quantityRecord[mu] = 0;
                    }  
}
                
for(int mu = 0; mu < dishDisplayed.size(); mu++)
                {
                    if(quantityRecord[mu] != 0)
                    {
                        flag[mu] = 0;
                        
                    }
                    else
                    {
                        flag[mu] = 1;
                        
                    }

                    
                }
                
                for(int mu = 0; mu < dishDisplayed.size(); mu++)
                {
                    if(flag[mu] != 1)
                    {
                        mainFrame.getContentPane().removeAll();
                        mainFrame.repaint();
                        paymentUi();
                        break;
                    }   
                    else
                    {
                        warning.setText("Cannot proceed without any order");
                    }  
                }
            }

        );
        
        mainFrame.add(nextButton);

        JButton back = new JButton("Back");
        back.setBounds(40, 40 * (z + 1), 70, 20);
        back.addActionListener
        (
            backEvent ->
            {
                mainFrame.getContentPane().removeAll();
                mainFrame.repaint();
                userInfo();
            }
        );
        
        mainFrame.add(back);

        mainFrame.add(label);
        
    }
    
    void paymentUi()
    {
        paymemtTitle = new JLabel("Payment Method");
        paymemtTitle.setBounds(240, 50, 100, 20);
        paymemtTitle.setForeground(Color.white);
        mainFrame.add(paymemtTitle);

        JButton[] paymentOptions = {new JButton("Cash on Delivery"), new JButton("Debit Card"), new JButton("Credit Card"), new JButton("UPI/BHIM")};

        int r;
        for(r = 0; r < paymentOptions.length; r++)
        {
            paymentOptions[r].setBounds(135, 90 * (r + 1), 300, 80);
             
            paymentOptions[r].addActionListener
            (
                payment ->
                    {
                        for(int e = 0; e < paymentOptions.length; e++)
                        {
                            if(payment.getSource() == paymentOptions[e])
                            {
                                paymentMethod = paymentOptions[e].getText();
                              }
                        }
                        mainFrame.getContentPane().removeAll();
                        mainFrame.repaint();
                        
                        computeBill();
                    }
            );

            mainFrame.add(paymentOptions[r]);
        }

        JButton backButton = new JButton("Back");
        backButton.setBounds(135, 30 * (r + 11), 70, 20);
        backButton.addActionListener
        (
            back ->
            {
                mainFrame.getContentPane().removeAll();
                mainFrame.repaint();
                mockupui(quantityRecord);
            }
        );
        mainFrame.add(backButton);
    }
    
    void computeBill()
    {
        for(int a = 0; a < cartItem.size(); a++)
        {
            
            currentDish = cartItem.get(a);
            
            
            currentQuantity = cartQuantity.get(a);
            
            if(dishDisplayed.contains(currentDish))
            {
                dish_name = dishDisplayed.get(dishDisplayed.indexOf(currentDish));
                basePrice = (dishPrice.get(dishDisplayed.indexOf(dish_name)));
                price = price + (basePrice * currentQuantity);    
            }


        }
        
        SummaryUi();

    }

    void SummaryUi()
    {
        JLabel summaryTitle = new JLabel("Summary");
        summaryTitle.setBounds(260, 10, 100, 20);
        summaryTitle.setForeground(Color.white);
        mainFrame.add(summaryTitle);

        JLabel Item = new JLabel("Item");
        Item.setBounds(160, 30, 40, 20);
        Item.setForeground(Color.white);
        mainFrame.add(Item);

        JLabel Quantity = new JLabel("Quantity");
        Quantity.setBounds(320, 30, 80, 20);
        Quantity.setForeground(Color.white);
        mainFrame.add(Quantity);

        JLabel PriceLabel = new JLabel("Price");
        PriceLabel.setBounds(400, 30, 80, 20);
        PriceLabel.setForeground(Color.white);
        mainFrame.add(PriceLabel);

        JLabel[] quantityLabelSummary = new JLabel[cartQuantity.size()];
        JLabel[] dishLabelSummary = new JLabel[cartQuantity.size()];
        JLabel[] respectivePrice = new JLabel[cartQuantity.size()];
        
        int r;
        for(r = 0; r < cartQuantity.size(); r++)
        {   
            quantityLabelSummary[r] = new JLabel();
            quantityLabelSummary[r].setText(cartQuantity.get(r) + "");
            quantityLabelSummary[r].setBounds(340, 30 * (r + 2), 100, 20);
            quantityLabelSummary[r].setForeground(Color.white);
            mainFrame.add(quantityLabelSummary[r]);

            dishLabelSummary[r] = new JLabel();
            dishLabelSummary[r].setText(cartItem.get(r) + "");
            dishLabelSummary[r].setBounds(130, 30 * (r + 2), 150, 20);
            dishLabelSummary[r].setForeground(Color.white);
            mainFrame.add(dishLabelSummary[r]);

            respectivePrice[r] = new JLabel();
            double respPrice;
            if((Integer.parseInt(quantityLabelSummary[r].getText())) != 0)
            {
                respPrice = dishPrice.get(dishDisplayed.indexOf(cartItem.get(r))) * cartQuantity.get(r);
                respectivePrice[r].setText(respPrice + "");
            }

            
            respectivePrice[r].setBounds(400, 30 * (r + 2), 100, 20);
            respectivePrice[r].setForeground(Color.white);
            mainFrame.add(respectivePrice[r]);


        }

JLabel seperator = new JLabel("-----------------------------------------------------------------------------------");
        seperator.setBounds(120, 30 * (r + 2), 350, 20);
        seperator.setForeground(Color.white);
        mainFrame.add(seperator);

        JLabel paymentLabel = new JLabel("Payment Method                            :");
        paymentLabel.setBounds(130, 30 * (r + 3), 250, 20);
        paymentLabel.setForeground(Color.white);
        mainFrame.add(paymentLabel);


        JLabel paymentChosen = new JLabel(paymentMethod);
        paymentChosen.setBounds(350, 30 * (r + 3), 150, 20);
        paymentChosen.setForeground(Color.white);
        mainFrame.add(paymentChosen);

        JLabel totalPriceLabel = new JLabel("Total Price                                      : ");
        totalPriceLabel.setBounds(130, 30 * (r + 4), 250, 20);
        totalPriceLabel.setForeground(Color.white);
        mainFrame.add(totalPriceLabel);

        JLabel billAmountLabel = new JLabel();
        billAmountLabel.setText(price + "");
        billAmountLabel.setBounds(350 , 30 * (r + 4), 150, 20);
        billAmountLabel.setForeground(Color.white);
        mainFrame.add(billAmountLabel);

        JLabel couponLabel = new JLabel("Coupon Code                                 :");
        couponLabel.setBounds(130, 30 * (r + 5), 250, 20);
        couponLabel.setForeground(Color.white);
        mainFrame.add(couponLabel);

        JTextField couponEntry = new JTextField("N/A");
        couponEntry.setBounds(350, 30 * (r + 5), 100, 20);
        
        mainFrame.add(couponEntry);

        JButton couponApplyButton = new JButton("Apply");
        couponApplyButton.setBounds(350, 30 * (r + 6), 70, 20);

        JLabel validity = new JLabel();
        validity.setBounds(480, 30 * (r + 6), 100, 20);
        validity.setForeground(Color.white);
        mainFrame.add(validity);

        JLabel priceCouponAppliedLabel = new JLabel("Total Price, coupon applied       :");
        priceCouponAppliedLabel.setBounds(130, 30 * (r + 7), 250, 20);
        priceCouponAppliedLabel.setForeground(Color.white);
        mainFrame.add(priceCouponAppliedLabel);

        JLabel billAmountCouponApplied = new JLabel();
        billAmountCouponApplied.setBounds(350, 30 * (r + 7), 60, 20);
        billAmountCouponApplied.setForeground(Color.white);
        mainFrame.add(billAmountCouponApplied);
        billAmountCouponApplied.setText(price + "");

        couponApplyButton.addActionListener
        (
            couponValidate ->
            {
                Coupon computeCoupon = new Coupon();
                computeCoupon.couponValidity(couponEntry.getText());
                
                
                deductPrice = computeCoupon.couponDeduction(couponEntry.getText(), price);
                coupon = couponEntry.getText();
                billAmountCouponApplied.setText(deductPrice + "");
                
                if(computeCoupon.checkValidity(couponEntry.getText(), price))
                {
                    validity.setText("Valid");
                    validity.setEnabled(false);
                }
                else
                {   
                    validity.setText("Invalid");
                }
            }
        );
        mainFrame.add(couponApplyButton);

        JButton nextButton = new JButton("Place Order");
        nextButton.setBounds(350, 30 * (r + 8), 200, 50);
        nextButton.addActionListener
        (
            placeOrder ->
            {
                mainFrame.getContentPane().removeAll();
                mainFrame.repaint();
                billWriter();
                billSummary();

            }
        );
        mainFrame.getRootPane().setDefaultButton(nextButton);
        mainFrame.add(nextButton);

        JButton editOrderButton = new JButton("Edit Order");
        editOrderButton.setBounds(40, 30 * (r + 8), 200, 50);
        editOrderButton.addActionListener
        (
            edit ->
            {
                mainFrame.getContentPane().removeAll();
                mainFrame.repaint();
                price = 0;
                
                mockupui(quantityRecord);
}
        );
        mainFrame.add(editOrderButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(40, 28 * (r + 12), 70, 20);
        backButton.addActionListener
        (
            back ->
            {
                mainFrame.getContentPane().removeAll();
                mainFrame.repaint();
                paymentUi();
            }
        );
        mainFrame.add(backButton);
}

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            mockupui(quantityRecord);
        }
    }

    void billSummary()
    {
        JLabel summaryTitle = new JLabel("ORDER PLACED SUCCESSFULLY");
        summaryTitle.setBounds(200, 10, 200, 20);
        summaryTitle.setForeground(Color.white);
        mainFrame.add(summaryTitle);

        JLabel usernameLabel = new JLabel("Customer : " + username);
        usernameLabel.setBounds(80, 30, 300, 20);
        usernameLabel.setForeground(Color.white);
        mainFrame.add(usernameLabel);

        JLabel userMobileLabel = new JLabel("Mobile : " + mobileNo);
        userMobileLabel.setBounds(360, 30, 120, 20);
        userMobileLabel.setForeground(Color.white);
        mainFrame.add(userMobileLabel);

        JLabel useraddressLabel = new JLabel("Address  : " + userAddress);
        useraddressLabel.setBounds(200, 55, 200, 20);
        useraddressLabel.setForeground(Color.white);
        mainFrame.add(useraddressLabel);

        JLabel Item = new JLabel("Item");
        Item.setBounds(160, 80, 40, 20);
        Item.setForeground(Color.white);
        mainFrame.add(Item);

        JLabel Quantity = new JLabel("Quantity");
        Quantity.setBounds(320, 80, 80, 20);
        Quantity.setForeground(Color.white);
        mainFrame.add(Quantity);

        JLabel PriceLabel = new JLabel("Price");
        PriceLabel.setBounds(400, 80, 80, 20);
        PriceLabel.setForeground(Color.white);
        mainFrame.add(PriceLabel);

        JLabel[] quantityLabelSummary = new JLabel[cartQuantity.size()];
        JLabel[] dishLabelSummary = new JLabel[cartQuantity.size()];
        JLabel[] respectivePrice = new JLabel[cartQuantity.size()];
        
        int r;
        for(r = 0; r < cartQuantity.size(); r++)
        {   
            quantityLabelSummary[r] = new JLabel();
            quantityLabelSummary[r].setText(cartQuantity.get(r) + "");
            quantityLabelSummary[r].setBounds(340,  60 + (30 * (r + 2)), 100, 20);
            quantityLabelSummary[r].setForeground(Color.white);
            mainFrame.add(quantityLabelSummary[r]);

            dishLabelSummary[r] = new JLabel();
            dishLabelSummary[r].setText(cartItem.get(r) + "");
            dishLabelSummary[r].setBounds(130,  60 + (30 * (r + 2)), 150, 20);
            dishLabelSummary[r].setForeground(Color.white);
            mainFrame.add(dishLabelSummary[r]);

            respectivePrice[r] = new JLabel();
            double respPrice;
            if((Integer.parseInt(quantityLabelSummary[r].getText())) != 0)
            {
                respPrice = dishPrice.get(dishDisplayed.indexOf(cartItem.get(r))) * cartQuantity.get(r);
                respectivePrice[r].setText(respPrice + "");
            }

            
            respectivePrice[r].setBounds(400, 60 + (30 * (r + 2)), 100, 20);
            respectivePrice[r].setForeground(Color.white);
            mainFrame.add(respectivePrice[r]);


        }

JLabel seperator = new JLabel("-----------------------------------------------------------------------------------");
        seperator.setBounds(120,  60 + (30 * (r + 2)), 350, 20);
        seperator.setForeground(Color.white);
        mainFrame.add(seperator);

        JLabel paymentLabel = new JLabel("Payment Method                            :");
        paymentLabel.setBounds(130,  60 + (30 * (r + 3)), 250, 20);
        paymentLabel.setForeground(Color.white);
        mainFrame.add(paymentLabel);


        JLabel paymentChosen = new JLabel(paymentMethod);
        paymentChosen.setBounds(350,  60 + (30 * (r + 3)), 150, 20);
        paymentChosen.setForeground(Color.white);
        mainFrame.add(paymentChosen);

        JLabel totalPriceLabel = new JLabel("Total Price                                      : ");
        totalPriceLabel.setBounds(130,  60 + (30 * (r + 4)), 250, 20);
        totalPriceLabel.setForeground(Color.white);
        mainFrame.add(totalPriceLabel);

        JLabel billAmountLabel = new JLabel();
        billAmountLabel.setText(price + "");
        billAmountLabel.setBounds(350 ,  60 + (30 * (r + 4)), 150, 20);
        billAmountLabel.setForeground(Color.white);
        mainFrame.add(billAmountLabel);

        JLabel couponLabel = new JLabel("Coupon Code                                 :");
        couponLabel.setBounds(130,  60 + (30 * (r + 5)), 250, 20);
        couponLabel.setForeground(Color.white);
        mainFrame.add(couponLabel);

      

        

        JLabel validity = new JLabel();
        validity.setBounds(350,  60 + (30 * (r + 5)), 100, 20);
        validity.setForeground(Color.white);
        mainFrame.add(validity);
        validity.setText(coupon);

        JLabel priceCouponAppliedLabel = new JLabel("Total Price, coupon applied       :");
        priceCouponAppliedLabel.setBounds(130,  60 + (30 * (r + 6)), 250, 20);
        priceCouponAppliedLabel.setForeground(Color.white);
        mainFrame.add(priceCouponAppliedLabel);

        JLabel billAmountCouponApplied = new JLabel();
        billAmountCouponApplied.setBounds(350,  60 + (30 * (r + 6)), 60, 20);
        billAmountCouponApplied.setForeground(Color.white);
        mainFrame.add(billAmountCouponApplied);
        billAmountCouponApplied.setText(price + "");

        
        

        JButton reorderButton = new JButton("Order Again");
        reorderButton.setBounds(320, 60 + (30 * (r * 8)), 150, 20);
        
        mainFrame.add(reorderButton);
        reorderButton.addActionListener
        (
                reorder ->
                {
                    
                mainFrame.getContentPane().removeAll();
                mainFrame.repaint();
                userInfo();
                }
        );

    }


    void billWriter()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime timeStamp = LocalDateTime.now();
    
        

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
                FileWriter pen = new FileWriter("./App/BillLog.txt", true);
                pen.write(dtf.format(timeStamp));
                pen.write("\n-----------------------Order Summary----------------------\n");
                pen.write("Customer Name : " + username + "\n");
                pen.write("Mobile No.: " + mobileNo + "\n");
                pen.write("Address :" + userAddress + "\n");
                pen.write("\n\t\tFood Item\t|\tQuantity\t|\tPrice\n");

                for(int r = 0; r < cartQuantity.size(); r++)
                {   
                    
                    pen.write(cartItem.get(r) + "\t\t\t" + cartQuantity.get(r) + "\t\t\t" + dishPrice.get(dishDisplayed.indexOf(cartItem.get(r))) * cartQuantity.get(r) + "\n");
                }
                Coupon computeCoupon = new Coupon();
                
                pen.write("\n----------------------------------------------------------");
                pen.write("\nPayment Method\t\t\t\t:\t" + paymentMethod + "\n");
                pen.write("Total\t\t\t\t\t\t:\t" + price + "\n");
                pen.write("Coupon Applied\t\t\t\t:\t" + coupon + "\n");
                pen.write("Total, coupon applied\t\t:\t" + price + "\n");
                pen.write("----------------------------------------------------------" + "\n" + "\n");
                pen.write("\n");
                pen.write("\n");
                


                pen.close();
}
        }   
        catch(Exception e)
        {
        }
    }        
}

      
class TomatoBeta
{
    public static void main(String a[])
    {
        Baseui base = new Baseui();
        
        int[] baseArray = new int[base.dishDisplayed.size()];
        for(int i = 0; i < base.dishDisplayed.size(); i++)
        {
            baseArray[i] = 0;
        }
        base.userInfo();
        
    }
}
