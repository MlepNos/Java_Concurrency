package Messages;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

    }
}


class Message{
    private String message;
    private boolean empty=true;
    public synchronized String Read(){
        while (empty){

        }
        empty = true;
        return message;
    }
    public synchronized void Write(String message){
        while (!empty){

        }
        empty = false;
        this.message = message;
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