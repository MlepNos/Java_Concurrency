package ThreadsIntro;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        System.out.println(ThreadColor.ANSI_PURPLE+ "Hello from the ThreadsIntro.Main thread");

        Thread anotherThread = new AnotherThread();
        anotherThread.start();


        Thread threadWithName = new ThreadWithName();
        threadWithName.setName("== Rivaldo");
        threadWithName.start();
        //threadWithName.interrupt();

        new Thread(() -> {
            System.out.println(ThreadColor.ANSI_GREEN+"Hello from the Lambda function Thread");
        }).start();

        /*
        Thread myRunnableThread = new Thread(new ThreadsIntro.MyRunnable());
        myRunnableThread.start();
        */



        // Threads Can be done in Lambda Functions
        Thread myRunnableThread2 = new Thread(new MyRunnable() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_RED + "Hello from the Lambda function and implementation of run");
                try{
                    threadWithName.join();
                    System.out.println(ThreadColor.ANSI_RED + "threadwithNameTerminated, Im running again");
                }catch (InterruptedException e){
                    System.out.println(ThreadColor.ANSI_RED + "I was Interrupted");
                }
            }
        });
        myRunnableThread2.start();


        System.out.println(ThreadColor.ANSI_PURPLE + "Hello again from the ThreadsIntro.Main thread");

    }
}