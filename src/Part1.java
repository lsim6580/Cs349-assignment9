/**
 * Created by luke on 10/22/2016.
 */
public class Part1 extends Thread{
    private static int x,y,z;

    public static void f() {
        x = x + 1;
        y = y + 1;
        z = z + x - y;
    }

    public static void printValues() {
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("z = " + z);
    }

    public static void main(String args[]){
        Thread thread = new Part1();
        try {
            long startTime = System.nanoTime();
            thread.start();
            thread.join();
            long endTime = System.nanoTime();
            printValues();
            System.out.println("Computation took " + ((endTime - startTime) / 1000000) + " milliseconds");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void run(){

        for(int x = 0; x < 200000; x ++){
            f();
        }
        }
}
