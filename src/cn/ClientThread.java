package cn;
import java.io.*;//IOException er jonno
import java.net.*;//server s er jonno
import java.util.Scanner;

public class ClientThread {
    public static void main(String[] args) throws IOException { 
        Socket s = new Socket("localhost", 2222);//jar theke nibo tar ip address

        System.out.println("Connected");

        DataOutputStream dos = new DataOutputStream(s.getOutputStream());//kono kichu likhar jonno
        DataInputStream dis = new DataInputStream(s.getInputStream());//read korar jonno
         

        Scanner scn = new Scanner(System.in);//kono kichu likhar jonno keyboard theke input nite

        while (true) {//continous communication er jonno
            System.out.println( dis.readUTF());//server ask korbe tumi ki caccho {date/time}///mane clint shonar kaj korlo////////
            String str = scn.nextLine(); //date jante chai tai likhe pathalam ar string er under e rakhlam
            dos.writeUTF(str);////clint write kore server e output pathaye dilo////
            
             
            
            if(str.equals("Bye")){//jodi kono clint bye bDateole then clint er shathe close hoye jabe
            System.out.println("Closing the Connection" +s );///ei socket er under e je clint chilo take close kore dibe
            s.close();//socke(clint) close hoye gelo
            break;
            
            }
            String received = dis.readUTF();
            System.out.println(received);
            
        }
        dis.close();
        dos.close();
        s.close();
    }
}
