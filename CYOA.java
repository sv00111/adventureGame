//Shrey Valia & Aman Mangalore
//svalia & aamangal
//12/8/14
//cmps012b
//CYOA.java
//the main java file, has the code needed to run the adventure

import java.io.*;
import java.util.*;

public class CYOA {

    
    public static void main(String[] args) throws IOException {

        char restartName = 0;

        int room = 0;

        char userchoice = 0;


        Calc myStack = new Calc();

        while (restartName != 'q')
        {
            if( userchoice != 'z' )
            {
                room = 0;
            }

            restartName = 0;

            boolean oIsTrue = false, tIsTrue = false;
            int h = -1;
            int i = 0;
            int j = 0;
            int k = 0;
            int l = 1;
            int m = 0;
            int n = 0;
            int o = 0;
            int p = 0;
            String checker;
            String line;
            String line2;
            String navigator;
            String nullchecker;
            String nullchecker2;
            String nullchecker3;
            String roomdisplay;
            String optiondisplay;
            String[][] messages = new String[20][15];
            String[][] options = new String[20][12];
            String[][] director = new String[20][12];

            if (args[0].isEmpty())
                System.exit(0);
            File file = new File(args[0]);

            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                line = scan.next();
                line2 = scan.nextLine();

                if (line.equals("r")) {
                    h++;
                    i = 0;
                    j = 0;
                    k = 0;
                    messages[h][0] = line2;
                    options[h][0] = line2;
                    director[h][0] = line2;
                }
                if (line.equals("d")) {
                    i++;
                    messages[h][i] = line2;
                }
                if (line.equals("o")) {
                    j++;
                    options[h][j] = line2;
                }
                if (line.equals("t")) {
                    k++;
                    director[h][k] = line2;
                }
            }

            int counter = 0, counterCheck = 0;
            for (int x = h; x > 0; x--) {
                counter = 0;
                counterCheck = 0;
                while (true) {
                    nullchecker = options[x][counter];
                    if (nullchecker == null)
                        break;
                    counter++;
                }
                while (true) {
                    nullchecker = director[x][counterCheck];
                    if (nullchecker == null)
                        break;
                    counterCheck++;
                }
                if(counter!= counterCheck)
                {
                    System.out.println("There is an error in the file and the amount of t options don't equal the amount of " +
                            "o options");
                    return;
                }
            }
            while (true) {
                myStack.push(room);
                l = 1;
                while (true) {
                    nullchecker = messages[room][l];
                    if (nullchecker == null)
                        break;
                    System.out.println(messages[room][l]);
                    l++;
                }
                l = 1;
                System.out.println();//extra line space
                while (true) {
                    nullchecker = options[room][l];
                    if (nullchecker == null)
                        break;
                    System.out.println((char) (l + 96) + " -" + options[room][l]);
                    l++;
                }
                System.out.println();

                Scanner scan2 = new Scanner(System.in);
                userchoice = scan2.next().charAt(0);

                if(userchoice == 'z')
                {
                    System.out.println("[undo]");
                    room = myStack.pop();
                    room = myStack.pop();
                    break;
                }

                String temp[] = new String[20];
                if (userchoice == 'y')
                {
                    System.out.println("[information]");
                    System.out.println();

                    int a; // s is used to check if the string is null
                    String nullcheck2;
                    for(a = 0; a < 10; a++ )
                    {
                        nullchecker = director[a][0];
                        if (nullchecker == null)
                            break;
                        temp[a] = director[a][0];

                    }
                    l = a;
                    mergeSort(temp, 0, a);

                    for(a = 0; a < l; a++ )
                    {
                        for (int b = 0; b < l; b++) {
                            if (0 == temp[a].compareTo(director[b][0])) {
                                System.out.print(director[b][0] + " :" );
                                for (int c = 1; c < 12; c++)
                                {
                                    nullcheck2 = director[b][c];
                                    if (nullcheck2 == null)
                                        break;
                                    System.out.print(nullcheck2+ ", ");
                                }
                                System.out.println();
                            }
                        }
                    }
                    //this break breaks after the rest of the code is executed in the y ifstatment
                    break;

                }
                if (userchoice == 'q') {
                    System.out.println("[quit]");
                    myStack.deleteStack();
                    return;
                }
                if (userchoice == 'r') {
                    System.out.println("Restarting the game in one second");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }


                m = (int) userchoice;
                m = m - 96;

                if((int) userchoice< 96 || (int)userchoice > 109 )
                {
                    System.out.println("Option not possible, going to the first room");
                    room = 0;
                    m = 0;
                    userchoice = 96;

                }

                System.out.println( "[" + options[room][(int)userchoice-96] + "]");
                System.out.println();
                navigator = director[room][m];

                if (navigator == null)
                {
                    System.out.println("Option not possible, try again");

                    navigator = director[room][0];
                    n = room;
                    //break;
                }

                n = 0;
                while (true) {
                    checker = messages[n][0];
                    if (navigator.equals(checker))
                        break;
                    n++;
                }
                room = n;

            }
        }
    }

    public static void mergeSort(String[] firstName, int firstHalf, int secondHalf) {

        int total = secondHalf- firstHalf;
        if (total <= 1)
            return;
        int mid = firstHalf + total/2;

        // recursively sort until Size of array = 1
        mergeSort(firstName, firstHalf, mid);
        mergeSort(firstName, mid, secondHalf);

        // merge two sorted arrays
        String[] temp = new String[total];

        int i = firstHalf, j = mid;

        for (int k = 0; k < total; k++)
        {
            if (i == mid)
                temp[k] = firstName[j++];
            else if (j == secondHalf)
                temp[k] = firstName[i++];
            else if (firstName[j].compareToIgnoreCase(firstName[i]) < 0)
                temp[k] = firstName[j++];
            else
                temp[k] = firstName[i++];
        }
        for (int k = 0; k < total; k++)
            firstName[firstHalf + k] = temp[k];
    }

}



class Calc {
    private int count;
    private int[] stacker = new int[101];
    // Constructor
    public Calc() {
        count = 0;
    }

    // Push a number
    public void push(int x) {
        if (count > 101)
        {
            throw new RuntimeException();
        }
        count++;
        stacker[count] = x;
    }

    public void deleteStack()
    {
        int temp = count;
        for (int i = 0; i < temp; i++)
        {
            --count;
        }

    }
    // Pop top number (removes)
    public int pop() {
        if (count == 0)
        {
           System.out.println("You cant undo anymore, going to the first room");
	  return 0;
           // throw new RuntimeException();
        }
        int temppop = stacker[count];
        stacker[count] = 0;
        count--;
        return temppop;
    }

    public int peek() {
        if (count == 0)
        {
            throw new RuntimeException();
        }
        return stacker[count];
    }

    // Add top two numbers
    public void add() {
        if (count < 2)
        {
            throw new RuntimeException();
        }
        int tempresult1;
        int tempadd1 = stacker[count];
        int tempadd2 = stacker[count-1];
        tempresult1 = tempadd1 + tempadd2;
        stacker[count] = 0;
        count--;
        stacker[count] = tempresult1;
    }

    // Subtract top two numbers (top on right side)
    public void subtract() {
        int tempsub1;
        int tempsub2;
        int tempresult2;
        if (count < 2)
        {
            throw new RuntimeException();
        }
        tempsub1 = stacker[count];
        tempsub2 = stacker[count-1];
        tempresult2 = tempsub2 - tempsub1;
        stacker[count] = 0;
        count--;
        stacker[count] = tempresult2;
    }

    // Multiply top two numbers
    public void multiply() {
        int tempmult1;
        int tempmult2;
        int tempresult3;
        if (count < 2)
        {
            throw new RuntimeException();
        }
        tempmult1 = stacker[count];
        tempmult2 = stacker[count-1];
        tempresult3 = tempmult1 * tempmult2;
        stacker[count] = 0;
        count--;
        stacker[count] = tempresult3;
    }

    // Divide top two numbers (top on bottom)
    public void divide() {
        int tempdiv1;
        int tempdiv2;
        int tempresult4;
        if (count < 2)
        {
            throw new RuntimeException();
        }
        tempdiv1 = stacker[count];
        tempdiv2 = stacker[count-1];
        tempresult4 = tempdiv2/tempdiv1;
        stacker[count] = 0;
        count--;
        stacker[count] = tempresult4;
    }

    // Return how many numbers are in the stack
    public int depth() {
        return count;
    }

    //Return the reciprocal of the top element in the stack
    public void reciprocal() {
        int temptop1 = stacker[count];
        int temptop2 = 1/temptop1;
        stacker[count] = temptop2;
    }
}
