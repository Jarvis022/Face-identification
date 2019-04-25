package newproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.image.*;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Game_Screen {
    
    public static String x = "Faces";
    public static String y = "4";
    public Color color = Color.STEELBLUE;
    
    public static int P1_score = 0, P2_score = 0, click_count = 0;
    Text score1 = new Text();
    Text score2 = new Text();
    Text turn = new Text();
    ImageView displayPlayer = new ImageView();
    Image PlayerImage1;
    Image PlayerImage2;
    
    public Tile selected = null;
    public int clickCount = 2;
    
    public static MediaPlayer player;
    public static Media pick;
    
    public boolean music_control = true;
    
    Pane root_game = new Pane();
   
    public Game_Screen() {
        
        createLabelPlayer("Player 1",135,120);
        createLabelPlayer("Player 2",1120,120);
        createLabelScore(score1,180,175);
        createLabelScore(score2,1166,175);
       
        createLabelTurn();
        turn.setText("Player 1's Turn");
        score1.setText(Integer.toString(0));
        score2.setText(Integer.toString(0));
        PlayerImage1 = new Image("/newproject/PlayerImage/" + x + "/Player1.jpg" ,200,400,false,true);
        PlayerImage2 = new Image("/newproject/PlayerImage/" + x + "/Player2.jpg" ,200,400,false,true);
        setPlayerImage(PlayerImage1,115,120);
        root_game.getChildren().add(displayPlayer);
    }
    
    public int NOP() {
        int i = (Integer.parseInt(y) * Integer.parseInt(y)) / 2;
        return i;
    }
    
    public int NPR() {
        int i = Integer.parseInt(y);
        return i;
    }
    
    public void ColorSetter() {
       switch (x) {
          
            case "Faces":
                color = Color.YELLOWGREEN;
                break;
            case "Monuments":
                color = Color.SKYBLUE;
                break;
           
        }
    }
    
    public Parent createContent() { 
        pick = new Media(getClass().getResource("/newproject/Audio/"+x+"/audio.mp3").toExternalForm());
        player = new MediaPlayer(pick);
        player.play();
        root_game.setPrefSize(1366.0, 695.0);
        ColorSetter();
        List<Tile> tiles = new ArrayList<>();
        
        for (int i = 1; i <= NOP(); i++) {
            Image img = new Image("/newproject/Images/"+x+"/"+i+".jpg",600/NPR(),600/NPR(),false,true);
            tiles.add(new Tile(img));
            tiles.add(new Tile(img));
        }

        Collections.shuffle(tiles);
        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            tile.setTranslateX(378 + (600/NPR()) * (i % NPR()));
            tile.setTranslateY(80 + (600/NPR()) * (i / NPR()));
            root_game.getChildren().add(tile);
        }
        P1_score = 0;
        P2_score = 0;
        click_count = 0;
        createResetButton();
        createAudioButton();          
        return root_game;
    }
    public final void setPlayerImage(Image image,int x,int y) {
        displayPlayer.setImage(image);
        displayPlayer.relocate(x,y+150);
       
    }
    
    public final void createLabelPlayer(String pl,int x,int y) {
        Text t = new Text(pl);
        t.setTranslateX(x);
        t.setTranslateY(y);
        InnerShadow is = new InnerShadow();
        is.setOffsetX(1.0f);
        is.setOffsetY(2.5f);
        t.setEffect(is);
        t.setFill(Color.YELLOWGREEN);
        t.setFont(Font.font(null, FontWeight.EXTRA_BOLD, 35));
        root_game.getChildren().addAll(t);
    }
    
    public final void createLabelScore(Text t,int x,int y) {
        t.setTranslateX(x);
        t.setTranslateY(y);
        t.setCache(true);
        t.setFill(Color.SEAGREEN);
        t.setFont(Font.font(null, FontWeight.BOLD, 30));
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        t.setEffect(r);
        root_game.getChildren().add(t);
    }
    public final void createLabelTurn() {
        turn.setTranslateX(580);
        turn.setTranslateY(30);
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);
        turn.setEffect(is);
        turn.setFill(Color.YELLOW);
        turn.setFont(Font.font(null, FontWeight.BOLD, 35));
        root_game.getChildren().add(turn);
    }
    
    public void createResetButton() {
        Image reset = new Image("/newproject/ButtonImages/reset.jpg",30,30,false,true);
        Button rbt = new Button();
        ImageView img = new ImageView(reset);
        img.setImage(reset);
        rbt.setGraphic(img);
        rbt.setTranslateX(430);
        rbt.setTranslateY(710);
        
        rbt.setOnAction((ActionEvent event) -> {
            player.stop();
            score1.setText(Integer.toString(0));
            score2.setText(Integer.toString(0));
            turn.setText("Player 1's Turn");
            setPlayerImage(PlayerImage1,115,120);
            createContent();
        });
        root_game.getChildren().add(rbt);
    }
    
    public void createAudioButton() {
        Image audio = new Image("/newproject/ButtonImages/start_audio.jpg",30,30,false,true);
        Button abt = new Button();
        ImageView img = new ImageView(audio);
        img.setImage(audio);
        abt.setGraphic(img);
        abt.setTranslateX(900);
        abt.setTranslateY(710);
        abt.setOnAction((ActionEvent event) -> {
            Image audio1;
            if (music_control) {
                player.pause();
                music_control = false;
                audio1 = new Image("/newproject/ButtonImages/pause_audio.jpg",30,30,false,true);
            } else {
                player.play();
                music_control = true;
                audio1 = new Image("/newproject/ButtonImages/start_audio.jpg",30,30,false,true);
            }
            ImageView img1 = new ImageView(audio1);
            img1.setImage(audio1);
            abt.setGraphic(img1);
        });
        root_game.getChildren().add(abt);
    }

    public final class Tile extends StackPane {
        
        boolean click_flag = false;
        ImageView img;
        Image image;
        public Tile(Image image) {
            this.image = image;
            Rectangle border = new Rectangle(600/NPR(), 600/NPR());
            img = new ImageView();
            img.setImage(image);
            border.setFill(color);
            border.setStroke(Color.BLACK);
            InnerShadow is = new InnerShadow();
            is.setOffsetX(4.0);
            is.setOffsetY(4.0);
            border.setEffect(is);
            getChildren().addAll(border,img);
            setOnMouseClicked(this::handleMouseClick);
            close_start();
        }
        
        public void handleMouseClick(MouseEvent event) {
            if (click_flag == true) {
               // System.out.println("100000000000000000000000000000000000000000000000");
            } 
            else {
                click_count++;
                if (((click_count) % 4) == 0) {
                    turn.setText("Player 1's Turn");   
                    setPlayerImage(PlayerImage1,110,120);
                } else if (((click_count) % 2) == 0) {
                    turn.setText("Player 2's Turn");   
                    setPlayerImage(PlayerImage2,1070,120);
                }
                
                if (isOpen() || clickCount == 0) {   
                   // System.out.println("1");
                    return;
                }    
                clickCount--;

                if (selected == null) {
                    selected = this;
                   // System.out.println("12");
                    open(() -> {});
                }
                else {
                    open(() -> {
                        if (!hasSameValue(selected)) {
                            selected.close();
                            this.close();
                        }

                        selected = null;
                        clickCount = 2;
                    });
                }
            }               
        }         

        public boolean isOpen() {
            return img.getOpacity() == 1;
        }
        
        public void open(Runnable action) {
         //   System.out.println("1234");
            click_flag = true;
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), img);
            ft.setToValue(1);
            ft.setOnFinished(e -> action.run());
            ft.play();
        }

        public void close_start() {
            FadeTransition ft = new FadeTransition(Duration.seconds(NPR()), img);
            ft.setToValue(0);
            ft.play();
           // System.out.println("1start");
        }
        
        public void close() {
            click_flag = false;
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), img); 
            ft.setToValue(0);
            ft.play();
        }

        public boolean hasSameValue(Tile other) {
            if ((image.equals(other.image)) && ((click_count) % 4) == 0) {
                P2_score++;
                score2.setText(Integer.toString(P2_score * 10));
            } else if ((image.equals(other.image)) && ((click_count) % 2) == 0) {
                P1_score++;
                score1.setText(Integer.toString(P1_score * 10));
            }
            if ((P1_score + P2_score) == NOP()) {
                if (P1_score > P2_score) {
                    End_Screen.winner.setText("Player 1 won the game by "+(P1_score-P2_score) * 10+" points.");
                } else if (P1_score < P2_score) {
                    End_Screen.winner.setText("Player 2 won the game by "+(P2_score-P1_score) * 10+" points.");
                } else {
                    End_Screen.congo.setText("Well Played!");
                    End_Screen.winner.setText("This game is a DRAW.");
                }
                player.stop();
                Navigation.GameStage.setScene(Navigation.End);
            }
            return image.equals(other.image); 
        }
    }
}