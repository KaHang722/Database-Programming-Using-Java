package assignment6;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Question3 {
	public static void main(String[] args) {
	//Create 2 threads for
	//(1)Adding numbers to the set
	//(2)Iterate over the set
        Thread t1 = new Thread(new addToSet());
        Thread t2 = new Thread(new iterateOverSet());

        //ConcurrentModificationException will be received because 
        //numbers are added to the set (thread 1) while
        //the set is being iterated (thread 2)
        t1.start();
        t2.start();
    }

}

//Create Data class and static variables
class Data {
	public static Set<Integer> numberSet = new HashSet<>();
    public static int counter = 1;
}

//Adding numbers to the set
class addToSet implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
            Data.numberSet.add(Data.counter);
            System.out.println("In total " + Data.counter + " numbers are added ");
            Data.counter++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		
	}
	
}

//Iterate over the set
class iterateOverSet implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
            Iterator<Integer> it = Data.numberSet.iterator();
            while (it.hasNext()) {
                System.out.print(it.next() + " ");
              
            }
            System.out.println(" ");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
	}
	
}


