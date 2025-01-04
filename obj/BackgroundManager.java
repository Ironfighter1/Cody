import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class BackgroundManager {
    private List<Circle> stars = new ArrayList<>();

    public void generateStars(Pane root) {
        Random random = new Random();
        for (int i = 0; i < 600; i++) {
            double x = random.nextDouble() * Constants.WIDTH;
            double y = random.nextDouble() * Constants.HEIGHT;
            Circle star = new Circle(x, y, 3, Color.WHITE);
            stars.add(star);
            root.getChildren().add(star);
        }
    }

    public void updateStars() {
        for (Circle star : stars) {
            star.setCenterX(star.getCenterX() - 1);
            if (star.getCenterX() < 0) {
                star.setCenterX(Constants.WIDTH);
            }
        }
    }
}