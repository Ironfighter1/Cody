import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
public class Player {         
  private int health = 100;
  private double x;
  private double y;
  private ImageView playerImage;
  private boolean moveLeft, moveRight, moveUp, moveDown;
  private static final int TILE_SIZE = 80;  // Die Größe der einzelnen Tiles   
  public Maze maze;
  
    public Player() {
        this(null);  // Rufe den Konstruktor mit Maze als null auf
    }
  
   
  public Player(Maze maze) {
    this.x = 1;  // Standardwert für x
    this.y = 1;  // Standardwert für y
    this.playerImage = new ImageView(new Image("Astronaut.png"));
    playerImage.setFitHeight(TILE_SIZE);
    playerImage.setFitWidth(TILE_SIZE);
    playerImage.isVisible();
   
  }

    // Konstruktor mit Argumenten (optional)
  public Player(double startX, double startY) {
    this.x = startX;
    this.y = startY;
    this.playerImage = new ImageView(new Image("Astronaut.png"));  // Neues ImageView erstellen
    playerImage.setFitHeight(TILE_SIZE);
    playerImage.setFitWidth(TILE_SIZE);
    playerImage.setVisible(true);  // Stelle sicher, dass das Bild sichtbar ist
  }
  
  public void initializePlayer(Pane root) {
    // Setzt die Position des Spielers
    playerImage.setX(this.x);
    playerImage.setY(this.y);
    
    // Fügt den ImageView zur Szene hinzu
    root.getChildren().add(playerImage);
    
    // Stellt sicher, dass das Bild sichtbar ist
    playerImage.setVisible(true);
    
    // Falls notwendig, setze die Größe des Bildes
    playerImage.setFitWidth(TILE_SIZE*2);
    playerImage.setFitHeight(TILE_SIZE*2);
  }
 
  public void handleMouseMove(MouseEvent event) {
    // Update player orientation based on mouse movement
  }

  public void handleKeyPress(KeyCode keyCode) {
    switch (keyCode) {
      case A -> moveLeft = true;
      case D -> moveRight = true;
      case W -> moveUp = true;
      case S -> moveDown = true;
    }
  }

  public void handleKeyRelease(KeyCode keyCode) {
    switch (keyCode) {
      case A -> moveLeft = false;
      case D -> moveRight = false;
      case W -> moveUp = false;
      case S -> moveDown = false;
    }
  }
  
   // Bewege den Spieler
  
    public void update() {
    // Hier können Sie die Spiellogik aktualisieren, z.B. Benutzereingaben verarbeiten
    int Speed = 2;
    
    // Bewegung des Spielers und der Waffe basierend auf Benutzereingaben und Kollisionsstatus
    if (moveLeft) {   
      if (playerImage.getX() > 0 && maze.isNextMoveValid(playerImage.getX() - Speed, playerImage.getY(), playerImage.getFitWidth(), playerImage.getFitHeight(),x,y)) {
        playerImage.setX(playerImage.getX() - Speed);
        //weapon.setX(weapon.getX() - Speed);
      }
    }
    
    if (moveRight) {          
      if (playerImage.getX() < playerImage.getFitWidth() && maze.isNextMoveValid(playerImage.getX() + Speed, playerImage.getY(), playerImage.getFitWidth(), playerImage.getFitHeight(), x, y)) {
        playerImage.setX(playerImage.getX() + Speed);
        //weapon.setX(weapon.getX() + Speed);
      }
    }
    
    if (moveUp) {           
      if ( playerImage.getY() > 0 && maze.isNextMoveValid(playerImage.getX(), playerImage.getY() - Speed, playerImage.getFitWidth(), playerImage.getFitHeight(), x, y)) {
        playerImage.setY(playerImage.getY() - Speed);
        //weapon.setY(weapon.getY() - Speed);
      }
    }
    
    if (moveDown) {             
      if (playerImage.getY() <  playerImage.getFitHeight() && maze.isNextMoveValid(playerImage.getX(), playerImage.getY() + Speed, playerImage.getFitWidth(), playerImage.getFitHeight(), x, y)) {
        playerImage.setY(playerImage.getY() + Speed);
        //weapon.setY(weapon.getY() + Speed);
      }
    }         
    // Aktualisiere die Position des Spielfigur-Images
    playerImage.setX(x);
    playerImage.setY(y);
    if (moveLeft|| moveRight|| moveUp||moveDown) {
      System.out.println(x);
      System.out.println(y);
    } // end of if
    }


  

  public int getHealth() {
    return health;
  }
  
   // Setze die Maze-Instanz (wenn nicht bereits gesetzt)
    public void setMaze(Maze maze) {
        this.maze = maze;
    }
}