package Messages;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Message message = new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();


    }
}


class Message{
    private String message;
    private boolean empty=true;
    public synchronized String Read(){
        while (empty){
         try {
             wait();
         }catch (InterruptedException e ){

         }
        }
        empty = true;
        notifyAll();
        return message;
    }
    public synchronized void Write(String message){
        while (!empty){
            try {
                wait();
            }catch (InterruptedException e ){

            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}

class Writer implements Runnable{
    private Message message;
    public Writer(Message message){
        this.message = message;
    }
    @Override
    public void run() {
         String messages[] ={
                 "Do Re Mi Fa",
                 "Sol La Si Do",
                 "Mi Fa Re Mi"
         };
        Random random = new Random();
        for(int i =0;i< messages.length;i++){
            message.Write(messages[i]);
            try {
                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){

            }
        }
        message.Write("Finished");
    }
}

class Reader implements Runnable{
    private Message message;
    public Reader(Message message){
        this.message=message;
    }


    @Override
    public void run() {
        Random random = new Random();
        for(String latestMessage = message.Read(); !latestMessage.equals("Finished"); latestMessage = message.Read()) {
            System.out.println(latestMessage);
            try{
                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){

            }
        }
    }
}



