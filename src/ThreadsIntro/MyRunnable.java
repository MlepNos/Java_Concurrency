package ThreadsIntro;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_RED + "Hello from ThreadsIntro.MyRunnable's Implementation of run()");
    }
}
