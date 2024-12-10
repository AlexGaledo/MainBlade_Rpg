import java.awt.*;
import javax.swing.*;

public class tempCodeRunner{
    public static void main(String[] args) {
        // Create the JFrame
        JFrame frame = new JFrame("BorderLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);

        // Set BorderLayout
        frame.setLayout(new BorderLayout());

        // Create components
        JButton button1 = new JButton("North Button");
        JButton button2 = new JButton("South Button");
        JButton button3 = new JButton("East Button");
        JButton button4 = new JButton("West Button");
        JButton button5 = new JButton("Center Button");

        // Set preferred sizes to make them larger
        button1.setPreferredSize(new Dimension(50, 30)); // Larger North button
        button2.setPreferredSize(new Dimension(40, 300)); // Larger South button
        button3.setPreferredSize(new Dimension(60, 60)); // Larger East button
        button4.setPreferredSize(new Dimension(800, 160)); // Larger West button

        // Add components to frame
        frame.add(button1, BorderLayout.NORTH);
        frame.add(button2, BorderLayout.SOUTH);
        frame.add(button3, BorderLayout.EAST);
        frame.add(button4, BorderLayout.WEST);
        frame.add(button5, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}
