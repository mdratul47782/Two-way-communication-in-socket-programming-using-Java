package cn;
import java.io.*;//exception throw er jonno//input out put e joto kichu ache sob import kore nao
import java.net.*;//server socket er jonno built in class
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ServerThread {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(2222);//server socket server er built in class

        System.out.println("Waiting for Clients");// kotojon clint nibo ei while llop e change korte hobe
        while (true) {
            Socket s = ss.accept();//server clint er request accept korche

            System.out.println("A new client is connected: " + s);//notun clint amader shthe connect hoise

            DataOutputStream dos= new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());

            Thread t = new ClientHandler(s, dis, dos);//built in class Thread  ar eta ClientHandler(s, dis, dos) constructor
            t.start();
            
        }
    }
}

//clintHandler child class
class ClientHandler extends Thread {
    //final key constant er jonno value change hobe na
//mane soc,output,input ei variable gulate jate amar Socket,DataOutputStream,DataInputStream ei notun je information asbe etay jate notun variable e  store hoy
final Socket soc;
final DataOutputStream output;
final DataInputStream input;
String received;//received ke globally dicler korlam
String toreturn;
public ClientHandler (Socket s, DataInputStream dis, DataOutputStream dos) {//data type soho class name
this.soc= s;//this use korlam indicate kore deyar jonno
this.input = dis;
this.output= dos;

}
public void run(){
    while (true){
        try {
            output.writeUTF("what do you want?[Date/Time]");///////////////ekane write hoyeche to clint side e read hobe//////////
            received = input.readUTF();///////////////clint likhbe kichu ar ekhane server read korbe ekhane//////////
            
            if(received.equals("Bye")){//jodi kono clint bye bole then clint er shathe close hoye jabe
                
            
            System.out.println("Closing the Connection" +this.soc );///ei socket er under e je clint chilo take close kore dibe
            this.soc.close();//socke(clint) close hoye gelo
            break;
            
            }
            ///////////////////////////////
            Date d = new Date(); ///date,time
            DateFormat fordate = new SimpleDateFormat("dd/MM/yy");
            DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
            
            switch(received){
             case"Date":
                 toreturn = fordate.format(d);
                 output.writeUTF(toreturn);
                 break;
             case "time":
                 toreturn = fortime.format(d);
                 output.writeUTF(toreturn);
                 break;
             default:
                 output.writeUTF("Invalid input");
                 break;
            }
            
            //////////////////////////////
        } catch(Exception e){
            System.out.println(e);
        }
    }
    try{

this.output.close();
this.input.close();


}catch(Exception e){
System.out.println(e);
}
 
}


}
