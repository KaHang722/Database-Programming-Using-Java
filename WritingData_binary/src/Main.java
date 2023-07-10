import javax.swing.JFrame;

public class Main {
    public static void main(String[] args)
    {
        CustomerManager customerManager = new CustomerManager();
        customerManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerManager.setSize(800, 600);
        customerManager.setVisible(true);
    }
}
