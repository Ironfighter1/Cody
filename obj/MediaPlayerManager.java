import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
public class MediaPlayerManager {
  private MediaPlayer mediaPlayer;

  public MediaPlayerManager(String soundFilePath) {
    Media sound = new Media(new File(soundFilePath).toURI().toString());
    mediaPlayer = new MediaPlayer(sound);
    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    mediaPlayer.setVolume(0.5);
    mediaPlayer.play();
  }
}