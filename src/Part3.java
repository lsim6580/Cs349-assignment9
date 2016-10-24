/**
 * Created by luke on 10/22/2016.
 */
public class Part3 {
    private static int x,y,z;

    public static synchronized void f() {
        x = x + 1;
        y = y + 1;
        z = z + x - y;
    }

    public static void printValues(){
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("z = " + z);
    }

    public static void main(String args[]) {
        Runnable run1 = new Runnable() {
            @Override
            public void run() {

                for (int x = 0; x < 200000; x++) {
                    f();
                }

            }
        };
        Runnable run2 = new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 100000; x++) {
                    f();
                }
            }
        };
        try {
        Thread t1 = new Thread(run1);
        Thread t2 = new Thread(run2);
        long startTime = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long endTime = System.nanoTime();
        System.out.println("Computation took " + ((endTime - startTime) / 1000000) + " milliseconds");
        printValues();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
