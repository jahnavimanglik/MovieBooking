package moviebooking;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import javax.swing.*;
    public class Booking  {
        Scanner in = new Scanner(System.in);
        String seats[][] = {{"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10"},
                {"B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10"},
                {"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10"},
                {"D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10"},
                {"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10"},
                {"F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10"},
                {"G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10"},
                {"H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10"},
                {"I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10"},
                {"J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8", "J9", "J10"}}; // template for seats
        String dynamicseats[][] = new String[10][10];
        String booked_tickets[][]= new String[100][2]; //movie name, seat ID
        String booked_indices[] = new String[100];
        String name,number, email,yourcity,yourmoviechoice,choice,snacknum,tickets="",snacklist="",seatID="",previndex,index;
        String ticket[] = new String[100];
        int prices[] = new int[60];
        int counter1=0, counter2=0,sn, tchoice,price, y_or_n,n,hallno,mc,totaltickets=0, p=0,q=0,r=0;
        double bill=0, gst,st;
        String timing[] = {"8:30 pm","9:00 pm","9:30 pm","10:00 pm","10:30 pm"};
        boolean flag= false, f = false, fl=false,check=false,c;
        String movie[] = {"1. Shershaah","2. Sardar Udham","3. Bell Bottom", "4. Venom","5. Onward"};
        String snacks[] = {"1. Popcorn (Caramel/Cheese) \u20B9 299",
                "2. Medium Burger+Medium Fries+Medium Soft Drink: \u20B9 349",
                "3. Popcorn (Salted) \u20B9 249",
                "4. Medium Fries+Soft Drink: \u20B9 199",
                "5. Soft Drink: \u20B9 099",
                "6. Nacho Meal: \u20B9 249"};
        String[] snackchoice = new String[60];
        String payment[] = {"1. Cash","2. Debit/Credit Card","3. UPI","4. Wallet","5. NetBanking"};
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        void welcome(){
            if(fl){
                System.out.println("Perfect! Now, Please enter specifications for movie.\n");
                fl=false;
                movie();
            }
            else{

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        dynamicseats[i][j]= seats[i][j]; //resets seat template for ever customer
                    }
                }
                System.out.println("Welcome to SVN Cinemas!"); //welcome message
                System.out.println("Please Enter Your Details:");
                details();
            }
        }
        void details(){
            System.out.print("Name: ");
            name = in.nextLine();
            System.out.println("");
            System.out.print("Contact Number: ");
            number = in.next();
            System.out.println("");
            System.out.print("Email Address: ");
            email = in.next();
            System.out.println("");
            fl = true;
            welcome();
        }
        void timing(){
            for (int i = 0; i < timing.length; i++) {
                System.out.println((i+1)+". "+timing[i]+" ");
            }
            System.out.println("");
            System.out.print("Enter your timing choice number (Eg. 2 for 6:30 pm): ");
            tchoice = in.nextInt();
            if(tchoice>5||tchoice<=0){ //eliminating all impossible cases
                System.out.println("You have entered an incorrect choice. Please choose again.");
                timing();
            }
            else
                seatID+=tchoice;
            System.out.println("");
            seatID();
        }
        void movie(){
            for (int i = 0; i < movie.length; i++) {
                System.out.println(movie[i]);
            }
            System.out.println("\n");
            System.out.print("Movie: ");
            mc = in.nextInt();
            if(mc>5||mc<0){
                yourmoviechoice = movie[mc-1].substring(3);
                seatID+= mc;
            }
            timing();
        }
        boolean checkseatID(){
            int a = 0; boolean h = false;
            while(booked_indices[a]!=null){
                if(booked_indices[a].equalsIgnoreCase(seatID)){
                    h = true;
                    index = booked_indices[a];
                }
                a++;
            }
            return h;//true if there has been a booking before in the same hall, false if not
        }
        void pricing(){
            System.out.println("");
            System.out.println("Regular: \u20B9 350");
            System.out.println("Gold: \u20B9 400\n");
            newSeatChart();
        }
        void seatID(){
            seatID = Integer.toString(mc)+Integer.toString(tchoice)+""; //stores the variables unique to every hall
            previndex=seatID;
            booked_indices[p]= seatID;
            p++;
            pricing();
        }
        void tickets(){
            System.out.println("Please enter \"Done\" when you are done choosing tickets");
            for(int i=0;i<100;i++){
                choice = in.next();
                if(choice.equalsIgnoreCase("Done")){
                    System.out.println("");
                    break;
                }
                else{
                    if(choice.length()>=4||choice.length()<2)
                        System.out.println("Invalid choice");
                    else{
                        choice = choice.toUpperCase();
                        for (int j = 0; j < 10; j++) {
                            for (int k = 0; k < 10; k++) {
                                if((choice.equals(dynamicseats[j][k]))){
                                    check=true;
                                }
                            }
                        }
                        if(!check){
                            System.out.println("Invalid choice.");
                            tickets();
                        }
                        else{
                            if(choice.charAt(0)=='A'||choice.charAt(0)=='B'||choice.charAt(0)=='C'||choice.charAt(0)=='D'||choice.charAt(0)=='E'){ //for regular seats
                                if(Character.isDigit(choice.charAt(1))){
                                    ticket[i] = choice;
                                    booked_tickets[r][q] = seatID;
                                    booked_tickets[r][q+1] = choice;
                                    r++;
                                    counter1++;
                                }
                                else
                                    System.out.println("Invalid choice");
                            }
                            else if(choice.charAt(0)=='F'||choice.charAt(0)=='G'||choice.charAt(0)=='H'||choice.charAt(0)=='I'||choice.charAt(0)=='J'){ //for 'gold' seats
                                if(Character.isDigit(choice.charAt(1))){
                                    ticket[i] = choice;
                                    booked_tickets[r][q] = seatID;
                                    booked_tickets[r][q+1] = choice;
                                    r++;
                                    counter2++;
                                }
                                else
                                    System.out.println("Invalid choice");
                            }
                            else
                                System.out.println("Invalid seat number.");
                        }
                    }
                }
            }

            check = false;
            snacks();
        }
        void snacks(){
            for (int i = 0; i < snacks.length; i++) {
                System.out.println(snacks[i]);
            }
            System.out.println("");
            System.out.println("Do you wish to purchase some snacks? \nThey will be delivered to your seat during the movie.");
            System.out.println();
            System.out.println("Enter 1 if yes, 2 if no");
            y_or_n = in.nextInt();
            switch(y_or_n){
                case 1:
                    System.out.println("Enter \"Done\" when you are done choosing snacks");
                    System.out.println("Please enter the snack number(s)");
                    for(int i=0;i<60;i++){
                        snacknum = in.next();
                        if(snacknum.equalsIgnoreCase("Done"))
                            break;
                        sn = Integer.parseInt(snacknum);
                        snackchoice[i] = snacks[--sn].substring(3, snacks[sn].length()-5);
                        price = Integer.parseInt(snacks[sn].substring((snacks[sn].length())-4).trim());
                        prices[i]=price;
                        bill+= price;
                        if(sn>6){
                            System.out.println("Invalid choice. Please choose again.");
                            snacks();
                        }
                    }
                    break;
                case 2:
                    snacklist = "NA";
                    System.out.println("");
                    break;
                default:
                    System.out.println("Please enter the correct choice.");
                    snacks();
                    break;
            }
            System.out.println("");
            calculate();
        }
        void calculate(){
            bill = bill+350*counter1+400*counter2;
            bill+=0.0;
            gst = 0.28*bill;
            gst = (Math.round(gst*100))/100.0; //to get the amount in hundredths
            paymentmethod();
        }
        void paymentmethod(){
            for (int i = 0; i < payment.length; i++)
                if(i==payment.length-1)
                    System.out.println(payment[i]+" ");
                else
                    System.out.print(payment[i]+" ");
            System.out.println("Please enter number of payment choice \n");
            n = in.nextInt();
            --n;
            genBill();
        }
        void genBill(){
            System.out.println("Your Bill: ");
            System.out.println("");
            System.out.println("---------------------------\n");
            System.out.println("\tSVN CINEMAS\t");
            System.out.println("---------------------------");
            System.out.println(formatter.format(date)+"\n");
            System.out.println("Name: "+name+"\n");
            System.out.println("Contact number: "+number+"\n");
            System.out.println("Email address: "+email+"\n");
            System.out.println("Movie: "+movie[mc-1].substring(3)+"\n");
            System.out.println("Timing: "+timing[tchoice-1]+"\n");
            System.out.println("Hall Number: "+(hallno+tchoice)+"\n");
            System.out.print("Seats: ");
            for (int i = 0; i < ticket.length; i++) {
                if(ticket[i]==null)
                    break;
                else{
                    tickets+= ticket[i].toUpperCase()+" ";
                    System.out.print(ticket[i].toUpperCase( )+" ");
                }
            }
            System.out.println("\n");
            if(y_or_n==1){
                int i=0;
                while(snackchoice[i]!=null){
                    System.out.print((i+1)+". "+snackchoice[i]+ " = \u20B9");
                    System.out.println(prices[i]);
                    snacklist = snacklist+((i+1)+". "+snackchoice[i]+ " = \u20B9"+prices[i]+"\n");
                    st+=prices[i];
                    i++;
                }
                System.out.println("\nSubtotal: \u20B9 "+st);
                System.out.println("");
            }

            System.out.println(counter1+"x \u20B9 350 = \u20B9 "+(counter1*350)+"\n");
            System.out.println(counter2+"x \u20B9 400 = \u20B9 "+(counter2*400)+"\n");
            totaltickets+=counter1+counter2;
            System.out.println("Total: \u20B9"+bill+"\n");
            System.out.println("GST @28%: \u20B9"+gst+"\n");
            System.out.println("---------------------------");
            System.out.println("Amount Payable: \u20B9"+Math.round((bill+gst)*100)/100.0+"\n");
            System.out.println("Payable at the theatre. Payment method: "+payment[n].substring(3)+"\n");
            System.out.println("Please ensure that you wear a mask on theatre premises.\n");
            System.out.println("Have a nice day!"+"\n");
            System.out.println("---------------------------\n");
            System.out.println("\tEND OF RECEIPT\t");
            System.out.println("---------------------------\n");
            System.out.println("Print Receipt? Enter Y if yes, N if no");
            char ch = in.next().charAt(0);
            if(ch=='Y'||ch=='y')
                genReceipt();
        }
        void genReceipt(){ //JFrame that opens as a new window
            JFrame frame = new JFrame();
            frame.setSize(300,500);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel();
            frame.add(panel);
            JTextArea receipt = new JTextArea("---------------------------\n"+"\tSVN CINEMAS\t\n"+
                    "---------------------------\n"+formatter.format(date)+"\n"+"Name: "+name+"\n"+
                    "Contact number: "+number+"\n"+"Email address: "+email+"\n"+
                    "Movie: "+movie[mc-1].substring(3)+"\n"+"Timing: "+timing[tchoice-1]+"\n"+"Hall Number: "
                    +(hallno+tchoice)+"\n"+"Seats: "+tickets+"\n"+snacklist+" \nSubtotal: \u20B9 "+st+"\n"+
                    counter1+"x \u20B9 350 = \u20B9 "+(counter1*350)+"\n"+counter2+"x \u20B9 400 = \u20B9 "+(counter2*400)+"\n"+
                    "Bill: \u20B9"+bill+"\n"+"GST @28%: \u20B9"+gst+"\n"+"---------------------------\n"+
                    "Amount Payable: \u20B9"+Math.round((bill+gst)*100)/100.0+"\n"+
                    "Payable at the theatre. Payment method: "+payment[n].substring(3)+"\n"+
                    "Please ensure that you wear a mask on theatre premises.\n"+
                    "Have a nice day!"+"\n"+"---------------------------\n"+"\tEND OF RECEIPT\t\n"+
                    "---------------------------\n");
            panel.add(receipt);
        }
        void refreshdata(){ //to refresh the existing customer data
            name="";
            number="";
            email="";
            tickets="";
            index="";
            snacklist="";
            hallno=0;
            tchoice=0;
            counter1=0;
            counter2=0;
            st=0;
            bill=0;
            gst=0;
            for(int i=0;i<100;i++)
                ticket[i] ="";
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    dynamicseats[i][j]= seats[i][j];
                }
            }
            c=checkseatID();
        }
        void newSeatChart(){
            r=0;
            System.out.println("Choose your seat(s)");
            System.out.println("↑-----Screen This Way-----↑\n");
            System.out.println("Regular seats: \n");
            if(!c){
                for (int i = 0; i < 10; i++) {
                    System.out.print((char)(65+i)+"  ");
                    for (int j = 0; j < 10; j++) {
                        System.out.print(seats[i][j]+" ");
                    }
                    System.out.println("\n");
                    if(i==4)
                        System.out.println("Gold seats:\n");
                }
            }
            else{
                for(int w=0;w<100;w++){
                    if(index.equalsIgnoreCase(booked_tickets[r][q])){
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                if(booked_tickets[r][q]==null||booked_tickets[r][q+1]==null)
                                    break;
                                if(booked_tickets[r][q+1].equals(seats[i][j]))
                                    dynamicseats[i][j] = "X";
                                else
                                    dynamicseats[i][j] = seats[i][j];
                                r++;
                            }
                        }
                    }
                }
                for(int i = 0; i < 10; i++){
                    System.out.print((char)(65+i)+"  ");
                    for (int j = 0; j < 10; j++){
                        System.out.print(dynamicseats[i][j]+" ");
                    }
                    System.out.println("\n");
                    if(i==4)
                        System.out.println("Gold seats:\n");
                }
            }
            tickets();
        }
        public static void main(String[] args) {
            Booking ob = new Booking();
            Scanner in = new Scanner(System.in);
            char ch;
            int n=1;
            do{
                System.out.println("Customer Number "+n);
                ob.welcome();
                System.out.println("New Session? Enter Y if yes, N if no");
                ch = in.next().charAt(0);
                if(ch=='Y'||ch=='y')
                    ob.refreshdata();
                n++;
            }
            while(ch=='Y'||ch=='y');
            if(!(ch=='Y'||ch=='y'))
                System.exit(0);
        }
    }
}
