package ThreadsIntro;

public class AnotherThread extends Thread {

    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_BLUE + "Hello from Another thread: " + currentThread().getName());


    }
}
