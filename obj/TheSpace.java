import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class TheSpace extends Application {
  private Stage primaryStage;
  //private Camera camera;
  private HUD hud;
  private Player player;                                 
  private Maze maze;
  private BackgroundManager backgroundManager;
  private MediaPlayerManager mediaPlayerManager;
  // TheSpace.java
  int[][] mazeArray = {
  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
  {1, 0, 0, 1, 0, 0, 1, 0, 1, 1},
  {1, 0, 0, 1, 0, 0, 1, 0, 1, 1},
  {1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
  {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
  };
  
  
  public static void main(String[] args) {
    launch(args);
  }
  
  
  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    primaryStage.setTitle("The_Space");
    
    // Initialize components
    //camera = new Camera(0, 0, 20550, 20000);
    hud = new HUD();
    player = new Player(maze);
    Maze maze = new Maze(mazeArray);  // Konstruktor wird mit einem 2D-Array aufgerufen
    backgroundManager = new BackgroundManager();
    //mediaPlayerManager = new MediaPlayerManager("path/to/sound.mp3");
    
    Pane root = new Pane();
    backgroundManager.generateStars(root);
    maze.drawMaze(root);
    hud.setupHUD(root);
    player.initializePlayer(root);
    Scene scene = new Scene(root, Constants.WIDTH, Constants.HEIGHT);
    setupEventHandlers(scene);
    
    primaryStage.setScene(scene);
    primaryStage.show();
    
    startGameLoop(root);
  }
  
  private void setupEventHandlers(Scene scene) {
    scene.setOnMouseMoved(event -> player.handleMouseMove(event));
    scene.setOnKeyPressed(event -> player.handleKeyPress(event.getCode()));  // event.getCode() gibt den KeyCode zurück
    scene.setOnKeyReleased(event -> player.handleKeyRelease(event.getCode())); // event.getCode() gibt den KeyCode zurück
  }

  private void startGameLoop(Pane root) {
    AnimationTimer timer = new AnimationTimer() {
      
      public void handle(long now) {
        backgroundManager.updateStars();
        player.update();
        hud.updateHealth(player.getHealth());
      }
    };
    timer.start();
  }
}