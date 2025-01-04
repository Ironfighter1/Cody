import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Maze {
  private int[][] maze;
   // Definiere die Konstante TILE_SIZE (z.B. 32 Pixel)
  private static final int TILE_SIZE = 40;  // Die Größe der einzelnen Tiles                                  
    // Konstruktor erwartet ein 2D-Array
  public Maze(int[][] mazeArray) {
    this.maze = mazeArray;
  }
  
  public void drawMaze(Pane root) {
    Pane floorPane = new Pane();
    Pane bulletPane = new Pane(); // Definieren Sie eine Pane für die Kugeln
    Pane doorPane = new Pane();
    Pane wallPane = new Pane();
    Pane laserPane = new Pane();
    boolean laserVisible = false;
    
    root.getChildren().addAll(bulletPane,floorPane,laserPane, wallPane, doorPane  ); 
    
    
    // Gehe durch jedes Element im Maze
    for (int row = 0; row < maze.length; row++) {
      for (int col = 0; col < maze[row].length; col++) {
        if (maze[row][col] == 1) {
          // Wand
          ImageView wallImage = new ImageView(new Image("wall.png"));
          wallImage.setFitWidth(TILE_SIZE);
          wallImage.setFitHeight(TILE_SIZE);
          wallImage.setX(col * TILE_SIZE);
          wallImage.setY(row * TILE_SIZE);
          wallPane.getChildren().add(wallImage);
        }else {
          // Boden
          ImageView floorImage = new ImageView(new Image("floor.png"));
          floorImage.setFitWidth(TILE_SIZE);
          floorImage.setFitHeight(TILE_SIZE);
          floorImage.setX(col * TILE_SIZE);
          floorImage.setY(row * TILE_SIZE);
          floorPane.getChildren().add(floorImage);
        }
      }
    }
  }
  
  public boolean isNextMoveValid(double nextPlayerX, double nextPlayerY, double playerWidth, double playerHeight, double startX, double startY) {
    // Überprüfe, ob der nächste Zug innerhalb der Spielfeldgrenzen liegt und ob er auf eine Wand trifft
    double halfPlayerWidth = playerWidth / 1.4; // Halbe Breite des Spielers
    double halfPlayerWidth2 = playerWidth / 5; // Halbe Breite des Spielers
    double halfPlayerHeight = playerHeight / 1.4; // Halbe Breite des Spielers
    // Flag, um zu überprüfen, ob der Laser bereits sichtbar ist
    boolean laserVisible = false;
    for (int row = 0; row < maze.length; row++) {
      for (int col = 0; col < maze[row].length; col++) {
        if (maze[row][col] == 1 || maze[row][col] == 12) {
          double wallX = col * TILE_SIZE + startX;
          double wallY = row * TILE_SIZE + startY;
          double wallWidth = TILE_SIZE;
          double wallHeight = TILE_SIZE;
          
          // Überprüfe, ob die linke Seite des Spielers kollidiert
          if (nextPlayerX + halfPlayerWidth > wallX &&
          nextPlayerX + halfPlayerWidth2 < wallX + wallWidth &&
          nextPlayerY + halfPlayerHeight < wallY + wallHeight &&
          nextPlayerY + playerHeight > wallY) {
            
            return false; // Kollision erkannt, Zug ist ungültig
          }
        }  
      }   
    }      
    return true;
  }
}