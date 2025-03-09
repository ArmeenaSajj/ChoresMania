import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;


public class Avatar {
   Maze lab = new Maze();
   int x = 40;
   int y = 40;
   int width = 40;
   int height = 40;
   int movement = 40;
  
   // Method to draw the avatar on the screen
   public void paint(Graphics main_graphics){
       main_graphics.setColor(Color.BLACK);
       main_graphics.setColor(Color.black);
       main_graphics.fillOval(x + 16, y + 3, 20, 20);
       main_graphics.setColor(Color.black);
   }
  
   // Method to handle key presses
   public void key_presses(KeyEvent event){
       int[][] maze = lab.getMaze();
      
       // Move left if the left arrow key is pressed and there is no wall
       if(event.getKeyCode() == 37){
           if(maze[y/40][(x/40) - 1] != 1){
               x = x - movement;
           }
       }
       // Move right if the right arrow key is pressed and there is no wall
       if(event.getKeyCode() == 39){
           if(maze[y/40][(x/40) + 1] != 1){
               x = x + movement;
           }
       }
       // Move down if the down arrow key is pressed and there is no wall
       if(event.getKeyCode() == 40){
           if(maze[(y/40) + 1][x/40] != 1){
               y = y + movement;
           }
       }
       // Move up if the up arrow key is pressed and there is no wall
       if(event.getKeyCode() == 38){
           if(maze[(y/40) - 1][x/40] != 1){
               y = y - movement;
           }
       }
      
       // Check if the avatar has reached the end of level 1
       if(Game.getLevel() == 1){
           if(x == 840 && y == 440){
               Game.changeLevel();
               JOptionPane.showMessageDialog(null, "END OF LEVEL 1");
               x = 40;
               y = 40;
           }
       }
      
       // Check if the avatar has reached the end of level 2
       if(Game.getLevel() == 2){
           if(x == 840 && y == 440){
               Game.changeLevel();
               JOptionPane.showMessageDialog(null, "END OF LEVEL 2");
               x = 40;
               y = 40;
           }
       }
      
       // Check if the avatar has reached the end of level 3
       if(Game.getLevel() == 3){
           if(x == 840 && y == 440){
               Game.changeLevel();
               x = 40;
               y = 40;
           }
       }
   }
}


