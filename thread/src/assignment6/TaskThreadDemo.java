package assignment6;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TaskThreadDemo {
    public void perform() {

        Question1JFrame jFrame = new Question1JFrame();

        // Create tasks
        Runnable printA = new PrintChar('a', 3000, jFrame);
        Runnable printB = new PrintChar('b', 3000, jFrame);
        Runnable print100 = new PrintNum(5000, jFrame);

        // Create threads
        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);

        thread1.setPriority(Thread.MAX_PRIORITY);

        // Start threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

// The task for printing a specified character in specified times
class PrintChar implements Runnable {
    private char charToPrint; // The character to print
    private int times; // The times to repeat
    private Question1JFrame uiFrame;

    /**
     * Construct a task with specified character and number of
     * times to print the character
     */
    public PrintChar(char c, int t, Question1JFrame uiFrame) {
        charToPrint = c;
        times = t;
        this.uiFrame = uiFrame;
    }

    @Override
    /** Override the run() method to tell the system
     *  what the task to perform
     */
    public void run() {
        for (int i = 0; i < times; i++) {
            SwingUtilities.invokeLater(() -> uiFrame.appendText(" " + charToPrint));
        }
    }

}

// The task class for printing number from 1 to n for a given n
class PrintNum implements Runnable {
    private int lastNum;
    private Question1JFrame uiFrame;

    /**
     * Construct a task for printing 1, 2, ... i
     */
    public PrintNum(int n, Question1JFrame uiFrame) {
        lastNum = n;
        this.uiFrame = uiFrame;
    }

    @Override
    /** Tell the thread how to run */
    public void run() {
        for (int i = 1; i <= lastNum; i++) {
            SwingUtilities.invokeLater(new ParameterisedRunnable(" " + i) {
                @Override
                void run(String param) {
                    uiFrame.appendText(param);
                }
            });
            Thread.yield();
        }
    }
}

abstract class ParameterisedRunnable implements Runnable {
    private String param;

    public ParameterisedRunnable(String param) {
        this.param = param;
    }

    @Override
    public void run() {
        run(param);
    }

    abstract void run(String param);
}

class Question1JFrame extends JFrame {
    private JTextArea mainArea;

    public Question1JFrame() throws HeadlessException {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Concurrent Output");
        setSize( 320, 320);
        setResizable(false);
        mainArea = new JTextArea();
        mainArea.setLineWrap(true);

        JScrollPane scroll = new JScrollPane (mainArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        add(scroll);

        setVisible(true);
        //pack();
    }

    public void appendText(String text) {
        mainArea.append(text);
    }
}
