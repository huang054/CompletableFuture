package merge;

import merge.mergeTest.MergeTest;

import java.util.concurrent.ExecutionException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        MergeTest mergeTest = new MergeTest();
        mergeTest.init();
        for(int i=0;i<100;i++){
            final int j=i;

              //  System.out.println(mergeTest.getOrders(i));
            new Thread(() -> {
                try {

                    System.out.println(mergeTest.getOrders(j));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

             //  mergeTest.getOrders(i);

        }
    }
}
