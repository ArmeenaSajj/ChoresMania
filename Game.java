import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Game extends JPanel {
   Maze maze = new Maze(); // Create an instance of the Maze class
   Avatar avatar = new Avatar(); // Create an instance of the Avatar class
   public static int level = 1; // Variable to keep track of the current level


   public Game() {
       // Add a key listener to the panel to handle key events
       addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {
               // This method is not used in this implementation
           }


           @Override
           public void keyPressed(KeyEvent e) {
               avatar.key_presses(e); // Pass the key event to the avatar to handle key presses
           }


           @Override
           public void keyReleased(KeyEvent e) {
               // This method is not used in this implementation
           }
       });
       setFocusable(true); // Set the panel to be focusable to receive key events
   }


   public void paint(Graphics maze_graphics) {
       maze.paint(maze_graphics); // Draw the maze on the screen
       avatar.paint(maze_graphics); // Draw the avatar on the screen
   }


   public static int changeLevel() {
       return level++; // Increment the level and return the new value
   }


   public static int getLevel() {
       return level; // Return the current level
   }


   public static void main(String[] args) {
       // Display introductory messages
       JOptionPane.showMessageDialog(null, "Years ago, a girl named Lucy went to the maze of mirrors with her friends.\n However, she accidently wanders off by herself.\n As she walks through the maze, she can't find any way out.\n Lucy accidently teleports herself into a new dimension.");
       JOptionPane.showMessageDialog(null, "Can you help Lucy flee the alternate dimension by getting\n her get through a series of mazes?");
       JOptionPane.showMessageDialog(null, "Use the arrow keys on your keyboard and follow the white path to get her to the end of the maze!");


       JFrame myWindow = new JFrame("ILLUSION LANE"); // Create a new JFrame window
       Game game = new Game(); // Create an instance of the Game class
       myWindow.add(game); // Add the game panel to the window
       myWindow.setSize(920, 540); // Set the size of the window
       myWindow.setLocation(300, 200); // Set the location of the window on the screen
       myWindow.setVisible(true); // Make the window visible
       myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation


       while (true) {
           try {
               Thread.sleep(10); // Pause the program for a short period
           } catch (InterruptedException ex) {
               Logger.getLogger(Game.class.getName()).log(Level.SEVERE, "you lose");
           }
           myWindow.repaint(); // Repaint the window to update the game graphics


           if (getLevel() > 3) {
               // Display a congratulations message when all levels are completed
               JOptionPane.showMessageDialog(null, "Congratulations! You helped Lucy get out of the mazes.\n Now she can go back to hanging out with her friends.");
               System.exit(0); // Exit the program
           }
       }
   }
}
