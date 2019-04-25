package newproject;

import javafx.scene.Parent;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class End_Screen {
    
    public static Pane root_end = new Pane();
    
    public static Text congo = new Text("Congrats!");
    public static Text winner = new Text("");
    
    public Parent createContent_end() {
        root_end.setPrefSize(1366.0, 692.0);
        createLabel(congo,570,150,60);
        createLabel(winner,280,300,50);
        return root_end;
    }
  
    public void createLabel(Text t,int x,int y, int z) {
        t.setTranslateX(x);
        t.setTranslateY(y);
        t.setFont(Font.font("Cambria",FontWeight.EXTRA_BOLD, z));
        Effect glow = new Glow(0.5);
        t.setEffect(glow);
        t.setCache(true);
        t.setFill(Color.RED);
        root_end.getChildren().add(t);
    }
}
