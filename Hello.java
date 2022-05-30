import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;  
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.lang.String;
import java.io.FileWriter;
import java.util.Random;



public class Hello implements ActionListener {
  
  public static void main(String[] args) {
	   
    global.total = 0.0;
    global.count = 0;
    global.count2 = 0;
    global.sumQuant = 0;
    int k = global.count + 1;
    final JFrame newApp = new JFrame("Nile Dot Com");
    
    
    newApp.setDefaultCloseOperation(3);
    newApp.setBounds(100, 100, 911, 453);
    final JPanel mainP = new JPanel();
    mainP.setBackground(Color.BLACK);
    newApp.setContentPane(mainP);
    mainP.setLayout((LayoutManager)null);
    JLabel labelNoi = new JLabel("Enter number of items in this order");
    labelNoi.setForeground(Color.yellow);
    labelNoi.setHorizontalAlignment(4);
    labelNoi.setBounds(237, 66, 266, 14);
    mainP.add(labelNoi);
    JLabel labelId = new JLabel("Enter item ID for item #" + Integer.toString(k));
    labelId.setForeground(Color.yellow);
    labelId.setHorizontalAlignment(4);
    labelId.setBounds(237, 115, 266, 14);
    mainP.add(labelId);
    JLabel labelQfi = new JLabel("Enter quantity for item #" + Integer.toString(k));
    labelQfi.setForeground(Color.yellow);
    labelQfi.setHorizontalAlignment(4);
    labelQfi.setBounds(237, 166, 266, 14);
    mainP.add(labelQfi);
    JLabel labelItinf = new JLabel("item #" + Integer.toString(k) +" " +" info");
    labelItinf.setForeground(Color.yellow);
    labelItinf.setHorizontalAlignment(4);
    labelItinf.setBounds(237, 222, 266, 14);
    mainP.add(labelItinf);
    JLabel labelSubt = new JLabel("Order subtotal for " + Integer.toString(k) +" item(s)");
    labelSubt.setForeground(Color.yellow);
    labelSubt.setHorizontalAlignment(4);
    labelSubt.setBounds(237, 274, 266, 14);
    mainP.add(labelSubt);
    final JTextField nOi = new JTextField();
    final JTextField iD = new JTextField();
    final JTextField qFi = new JTextField();
    final JTextField itInf = new JTextField();
    final JTextField subT = new JTextField();
    nOi.setBounds(513, 58, 370, 30);
    mainP.add(nOi);
    nOi.setColumns(10);
    iD.setBounds(513, 107, 370, 30);
    mainP.add(iD);
    iD.setColumns(10);
    qFi.setBounds(513, 158, 370, 30);
    mainP.add(qFi);
    qFi.setColumns(10);
    itInf.setBounds(513, 214, 370, 30);
    mainP.add(itInf);
    itInf.setColumns(10);
    subT.setBounds(513, 266, 370, 30);
    mainP.add(subT);
    subT.setColumns(10);
    subT.setEditable(false);
    itInf.setEditable(false);
    final JButton processItem = new JButton("Process Item #" + Integer.toString(global.count + 1));
    final JButton confirmItem = new JButton("Confirm Order#" + Integer.toString(global.count + 1));
    final JButton viewOrder = new JButton("View Order");
    final JButton finishOrder = new JButton("Finish Order");
    JButton newOrder = new JButton("New Order");
    JButton exit = new JButton("Exit");
    
    
    
    confirmItem.setEnabled(false);
    viewOrder.setEnabled(false);
    finishOrder.setEnabled(false);
    global.count = 1;
    
   
    processItem.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  labelItinf.setText("item #" + Integer.toString(global.count+1) +" " +" info");
        	  File fp = new File("inventory.txt");
        	    try {
        			global.source = new Scanner(fp);
        		} catch (FileNotFoundException e2) {
        			// TODO Auto-generated catch block
        			e2.printStackTrace();
        		}
        	  processItem.setEnabled(false);
        	  if(global.count == 0) {
        		  global.numberOfItem = Integer.parseInt(nOi.getText());
        		    nOi.setEditable(false);
        		    labelSubt.setText("Order subtotal for " + Integer.toString(global.numberOfItem) +" item(s)");
        	  }
        	 
        	  ArrayList<String>input = new ArrayList<String>();
        	  String reader = new String();
        	  int i = 0;
        	  while(global.source.hasNext()) {
        		  reader = global.source.nextLine();
        		  String [] listA = new String[4];
        		  listA = reader.split(",");
        		  if(listA[0].equals(iD.getText())) {
        			  input.addAll(Arrays.asList(listA));
        			  
        		  }
        		  
        	  }
        	  
        	  if(input.isEmpty()) {
        		  System.out.println("Sahara");
        		  global.check = false;
        		  confirmItem.setEnabled(true);
        	  } else {
        		  global.check = true;
        		  System.out.println(input);
        		  if(input.get(2).equals(" false")) {
        			  JOptionPane.showMessageDialog(mainP, "Sorry... that item is out of stock, please try another item","Nile Dot Com - ERROR", 0);
            		  processItem.setEnabled(true);
            		  confirmItem.setEnabled(false);
            		  iD.setText("");
            		  qFi.setText("");
        		  } else {
        			  int quantity = Integer.parseInt(qFi.getText());
        			  double fixedPrice = Double.parseDouble(input.get(3));
              		  int percentage = 0;
              		  double discount = 0.0;
              		  Double finalPrice = 0.0;
              		  System.out.println(input.get(0));
              		  global.sumQuant += quantity;
              		 if(quantity >= 1 && quantity <= 4) {
             			  discount = 0;
             			  percentage = 0;
             		  } else if(quantity >= 5 && quantity <= 9) {
             			  discount = 0.10;
             			  percentage = 10;
             		  } else if(quantity >= 10 && quantity <= 14) {
             			  discount = 0.15;
             			  percentage = 15;
             		  } else if(quantity >= 15) {
             			  discount = 0.20;
             			  percentage = 20;
             		  }
             		   finalPrice = ((quantity * fixedPrice) - discount);
             		  global.total += finalPrice ;
             		 String apex = " ";
           		  
           		  apex += input.get(0) + " " + input.get(1) + " $" + input.get(3) + " " + Integer.toString(quantity) + " " + Integer.toString(percentage) + "%" + " $" + Double.toString(finalPrice);
           		labelItinf.setText("item #" + Integer.toString(global.count) +" " +" info");
           		  itInf.setText(apex);
           		  
           		  global.cartF.add(apex);
           		  
           		  confirmItem.setEnabled(true);
              		  
        		  }
        	  }
        	  
        	  
        	  
        	 
        	  
          }
        });
    
    confirmItem.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  String idTp = iD.getText();
        	  Date date1 = new Date();
        	  confirmItem.setEnabled(false);
        	  viewOrder.setEnabled(true);
        	  finishOrder.setEnabled(true);
        	  processItem.setEnabled(true);
        	  iD.setText("");
    		  qFi.setText("");
    		  
        	  
        	  
        	  
        	  if(global.check == true) {
        		  String apex1 = " ";
         		 apex1 += "$";
           		  apex1 += Double.toString(global.total); 
           		  subT.setText(apex1);
        		 
        		 String ab = " ";
        		 ab += "Item #" + Integer.toString(global.count)+ " Accepted. Added to your cart";
        		 JOptionPane.showMessageDialog(mainP,ab ,"Nile Dot Com -Item Confirmed", -1);
        		 labelSubt.setText("Order subtotal for " + Integer.toString(global.count) +" item(s)");
        		 labelItinf.setText("item #" + Integer.toString(global.count) +" " +" info");
        		 global.count++;
        		 processItem.setText("Process Item #" + Integer.toString(global.count));
          		  confirmItem.setText("Confirm Order#" + Integer.toString(global.count));
           	  global.timeHoldHour.add(global.formatter.format(date1));
           	  global.timeHoldDate.add(global.formatter2.format(date1));
       		System.out.println(global.count);
       		 labelId.setText("Enter item ID for item #" + Integer.toString(global.count));
       		 labelQfi.setText("Enter quantity for item #" + Integer.toString(global.count));
       		System.out.println(" Count is " + (global.count-1) + " Item is " + Integer.parseInt(nOi.getText()));
       		if((global.count-1) == Integer.parseInt(nOi.getText())) {
      		  processItem.setEnabled(false);
      		  confirmItem.setEnabled(false);
      		  
      		  
      	  }
       		 
       		 
       		
        		 
        	
        	  } else {
        		  String ab = " ";
        		  ab += "item ID "+idTp  + " not in file";
        		  JOptionPane.showMessageDialog(mainP,ab,"Nile Dot Com - Error",0);
        		  itInf.setText("");
        		  processItem.setText("Process Item #" + Integer.toString(global.count));
         		  confirmItem.setText("Confirm Order #" + Integer.toString(global.count));
        	  }
        	  
        	
          }
        });
    viewOrder.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
        	  String output = "";
        	  for(int i = 0; i<global.cartF.size(); i++){
        		  output += Integer.toString(i+1);
        		  output += ".";
        	      String everything = global.cartF.get(i);
        	      

        	      output += everything;   
        	      output += "\n";
        	  }
        	  output += "\n";
            		JOptionPane.showMessageDialog(mainP, output, "Nile Dot Com - Current Shopping Cart Status",-1); 
            	
            
            	
            
              
          }
        });
    finishOrder.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            
            String notice = new String();
            Date date1 = new Date();
            notice = "";
            notice += "\n";
            notice += "\n";
            notice += "Date: " + " " + global.formatter1.format(date1);
            notice += "\n";
            notice += "\n";
            notice += "Item# / ID / Title / Price / Qty / Disc % / Subtotal:";
            notice += "\n";
            notice += "\n";
            
            for(int i=0;i<global.cartF.size();i++) {
            	notice += Integer.toString(i+1) + ".";
            	notice += global.cartF.get(i);
            	notice += "\n";
            }
            
            notice += "\n";
            notice += "\n";
            
            notice += "Order subtotal: " + " " +"$" + global.total;
            notice += "\n";
            notice += "\n";
            double tax = 0.06;
            tax *= global.total;
            global.total += tax;
            notice += "Tax rate: " +" " + " "+  "6%";
            notice += "\n";
            notice += "\n";
            notice += "Tax Amount: " + " "+ " " + "$"+ Double.toString(tax);
            notice += "\n";
            notice += "\n";
            notice += "Order total: " + " " + " " + "$"+ global.total;
            notice += "\n";
            notice += "\n";
            notice += "Thanks for shopping at Nile Dot Com!";
            notice += "\n";
            JOptionPane.showMessageDialog(mainP, notice, "Nile Dot Com -Final Notice", -1);
            
            
            String build = new String();
            Random rd = new Random();
      	  global.idTans[0] = rd.ints(200000000, (900000000 + 1)).findFirst().getAsInt();
      	  for(int i =1;i<global.cartF.size();i++) {
      		  int calc = global.idTans[i-1] + 1;
      		  global.idTans[i] = calc;
      	  }
            
            build = "";
            for(int i=0;i<global.cartF.size();i++) {
            	build += Integer.toString(global.idTans[i]);
            	build += ",";
            	build += " ";
            	build += global.cartF.get(i);
            	build += ",";
            	build += " ";
            	build += global.timeHoldDate.get(i);
            	build += ",";
            	build += " ";
            	build += global.timeHoldHour.get(i);
            	build += "\n";
            	
            }
            System.out.println(build);
            global.cartF.clear();
            global.timeHoldDate.clear();
            global.timeHoldHour.clear();
            global.input2.clear();
            
            try {
            	FileWriter myObj = new FileWriter("transaction.txt");
            	myObj.write(build);
            	myObj.close();
            	
            	
            } catch(IOException e1) {
            	e1.printStackTrace();
            }
            
            
            	
              newApp.dispose();
           
          }
        });
    newOrder.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            newApp.dispose();
            Hello.main(null);
          }
        });
    exit.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            newApp.dispose();
          }
        });
    processItem.setBounds(10, 380, 130, 23);
    confirmItem.setBounds(150, 380, 130, 23);
    viewOrder.setBounds(290, 380, 130, 23);
    finishOrder.setBounds(430, 380, 130, 23);
    newOrder.setBounds(570, 380, 130, 23);
    exit.setBounds(710, 380, 130, 23);
    mainP.add(processItem);
    mainP.add(confirmItem);
    mainP.add(viewOrder);
    mainP.add(finishOrder);
    mainP.add(newOrder);
    mainP.add(exit);
    System.out.println(global.check);
    newApp.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {}
}