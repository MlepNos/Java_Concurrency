package MultipleThreads;



public class Main {
    public static void main(String[] args) {
        //if we used variables in countdown the other thread would change the i value of the other thread because they use the same instance "countdown" and they are not synced
        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");
        /*
        t1.start();
        t2.start();
        */

        //Synced method // do not do this
        CountdownSynced countdownSynced = new CountdownSynced();
        CountdownThread t3 = new CountdownThread(countdownSynced);
        t3.setName("Thread 3");
        CountdownThread t4 = new CountdownThread(countdownSynced);
        t4.setName("Thread 4");

        /*
        t3.start();
        t4.start();
        */

        //Synced statement
        CountdownSyncedLoop countdownSyncedloop = new CountdownSyncedLoop();
        CountdownThread t5 = new CountdownThread(countdownSyncedloop);
        t5.setName("Thread 5");
        CountdownThread t6 = new CountdownThread(countdownSyncedloop);
        t6.setName("Thread 6");

        t5.start();
        t6.start();

    }
}

class Countdown{
    public void doCountdown(){
        String color;

        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            case "Thread 3":
                color = ThreadColor.ANSI_GREEN;
                break;
            default:
                color = ThreadColor.ANSI_RED;
        }
        for( int i =10; i>0 ;i--){
            System.out.println(color + Thread.currentThread().getName()+ " : i ="+ i);
        }
    }

}

class CountdownSynced extends Countdown{
    private int i;
    public synchronized void doCountdown(){
        String color;

        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            case "Thread 3":
                color = ThreadColor.ANSI_GREEN;
                break;
            default:
                color = ThreadColor.ANSI_RED;
        }
        for(i =10; i>0 ;i--){
            System.out.println(color + Thread.currentThread().getName()+ " : i ="+ i);
        }
    }

}

class CountdownSyncedLoop extends Countdown{
    private int i;
    public  void doCountdown(){
        String color;

        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            case "Thread 3":
                color = ThreadColor.ANSI_GREEN;
                break;
            case "Thread 5":
                color = ThreadColor.ANSI_BLUE;
                break;
            default:
                color = ThreadColor.ANSI_RED;
        }
        synchronized(this){
            for(i =10; i>0 ;i--){
                System.out.println(color + Thread.currentThread().getName()+ " : i ="+ i);
            }
        }

    }

}

class CountdownThread extends Thread{
    private Countdown threadCountdown;
    public CountdownThread(Countdown countdown){
        threadCountdown = countdown;
    }


    public void run(){
        threadCountdown.doCountdown();
    }

}

