package ThreadsIntro;

public class ThreadWithName extends Thread{

    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_CYAN + "Hello from Thread With Name: " + currentThread().getName());

        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){

            System.out.println(ThreadColor.ANSI_CYAN +"Another Thread Wake me up");
            return;
        }
        System.out.println(ThreadColor.ANSI_CYAN +"Three Seconds have passed and Im awake ");

    }
}
