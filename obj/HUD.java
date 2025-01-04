import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class HUD {
    private Text healthText;
                              
    public void setupHUD(Pane root) {
        healthText = new Text("100");
        healthText.setFont(Font.font("Arial", FontWeight.BOLD, 100));
        healthText.setFill(Color.WHITE);
        healthText.setLayoutX(370);
        healthText.setLayoutY(80);

        ImageView hudImage = new ImageView(new Image("HUD.png"));
        hudImage.setFitWidth(700);
        hudImage.setFitHeight(100);
        hudImage.setLayoutX(0);
        hudImage.setLayoutY(0);

       // root.getChildren().addAll(hudImage, healthText);
        healthText.toFront();
    }

    public void updateHealth(int health) {
        healthText.setText(String.valueOf(health));
    }
}