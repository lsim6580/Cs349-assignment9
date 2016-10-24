import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by luke on 10/22/2016.
 */
public class Part4 {
    final int[][] matrix; // if you run out of memory during execution, reduce the second dimension
    Random rand = new Random();
    final int row;
    final int col;
    Thread[] tArray;
    volatile List<Integer> totalList = new ArrayList<>();

    // Initialize matrix with random numbers
    Part4(int row, int col) {
        this.row = row;
        this.col = col;
        tArray = new Thread[row];
        matrix = new int[row][col];
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                int randomNum = rand.nextInt(200); // 0 - 199
                matrix[x][y] = randomNum;
            }
        }
    }

    public void calculate() {
        int sum = 0;
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                sum = sum + (int) Math.log(matrix[x][y]);
            }

        }
        System.out.println(sum);
    }

    public void calculateMThread() {
        int index = 0;
        for (int rowM[] : matrix) {
            tArray[index] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int sum = 0;
                    for (int x = 0; x < rowM.length; x++) {
                        sum = sum + (int) Math.log(rowM[x]);

                    }
                    totalList.add(sum);
                }
            });
            index++;
        }

    }



    public static void main(String args[]) {
        Part4 part4 = new Part4(3, 1000000);
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                part4.calculate();
            }
        };
        Thread thread = new Thread(run1);
        long startTime = System.nanoTime();
        thread.start();

        try{
            thread.join();
            long endTime = System.nanoTime();
            System.out.println("Regular Computation took " + ((endTime - startTime) / 1000000) + " milliseconds");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        part4.calculateMThread();
        long startThreadTime = System.nanoTime();
        for(Thread t: part4.tArray) {
            t.start();
        }
            try {
                for (Thread t : part4.tArray) {
                    t.join();
                }
                int total = 0;
                for (int t : part4.totalList) {
                    total += t;
                }
                System.out.println(total);
                long endThreadTime = System.nanoTime();
                System.out.println("Multi Thread Computation took " + ((endThreadTime - startThreadTime) / 1000000) + " milliseconds");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


