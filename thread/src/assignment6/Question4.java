package assignment6;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Question4 {
	public static void main(String[] args) {
	//Create 2 threads for
	//(1)Adding numbers to the set
	//(2)Iterate over the set
        Thread t1 = new Thread(new addToSetSync());
        Thread t2 = new Thread(new iterateOverSetSync());

        //ConcurrentModificationException will NOT be received because 
        //both addToSetSync() and iterateOverSetSync() are synchronized
        t1.start();
        t2.start();
    }

}

class DataSync {
	public static Set<Integer> numberSet = new HashSet<>();
    public static int counter = 1;
    public static Object lock = new Object();
}

class addToSetSync implements Runnable {
	

	@Override
	 public void run() {
		// TODO Auto-generated method stub
		
			while (true) {
				//synchronized to avoid collision
				synchronized (DataSync.lock) {
					DataSync.numberSet.add(DataSync.counter);
					System.out.println("In total " + DataSync.counter + " numbers are added ");
					DataSync.counter++;
				}
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            //temporarily release time for other threads
	            //to make output more readable
	            Thread.yield();
	        }
		
	}
	
}

class iterateOverSetSync implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			while (true) {
				//synchronized to avoid collision
				synchronized (DataSync.lock) {
					Iterator<Integer> it = DataSync.numberSet.iterator();
				
		            while (it.hasNext()) {
		                System.out.print(it.next() + " ");

		            }
				}
	            System.out.println(" ");
	
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException ex) {
	                ex.printStackTrace();
	            }
	            //temporarily release time for other threads
	            //to make output more readable
	            Thread.yield();
	        }
		
	}
	
}


