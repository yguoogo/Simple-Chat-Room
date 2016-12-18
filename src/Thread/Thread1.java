package Thread;

/**
 * Created by yuguanxu on 12/17/16.
 */



class Runner extends Thread{
    public void run() {
        for(int i = 0 ; i < 10; i++){
            System.out.println("Hello " + i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Thread1 {
    public static void main(String[] args) {
        Runner runner1 = new Runner();
        runner1.start();

        Runner runner2 = new Runner();
        runner2.start();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        });

        t1.start();
    }
}
