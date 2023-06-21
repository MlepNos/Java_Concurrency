package ProducerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ProducerConsumer.Main.EOF;

public class Main {
    public static final String EOF ="EOF";
    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        myProducer producer = new myProducer(buffer,ThreadColor.ANSI_CYAN);
        myConsumer consumer = new myConsumer(buffer,ThreadColor.ANSI_BLUE);
        myConsumer consumer2 = new myConsumer(buffer,ThreadColor.ANSI_RED);

        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(consumer2).start();
    }
}

class myProducer implements Runnable{
    private List<String> buffer;
    private String color;

    public myProducer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1","2","3","4","5"};
        for (String num : nums){
            try {
                System.out.println(color+"Adding..."+ num);
                synchronized (buffer){
                    buffer.add(num);
                }
                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                System.out.println("Producer was Interrupted");
            }
        }
        System.out.println(color+ "Adding EOF and exiting...");
        synchronized (buffer){
            buffer.add("EOF");
        }

    }
}

class myConsumer implements Runnable{
    private List<String> buffer;
    private String color;

    public myConsumer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true){
            synchronized (buffer){
                if(buffer.isEmpty()){
                    continue;
                }
                if(buffer.get(0).equals(EOF)){
                    System.out.println(color+"Exiting");
                    break;
                }else {
                    System.out.println(color+"Removed " + buffer.remove(0));
                }
            }

        }
    }
}
