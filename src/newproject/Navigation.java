package newproject;

import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Navigation extends Application {
    public static Stage GameStage;
    Scene Main;
    Scene Game;
    public static Scene End;
    Button start;
    Button back;
    Button stopGame;
    Button jumpToMain;
    Button exit;

    Parent root_main;
    Parent root_game;
    Parent root_end;

    public void createStartButton() {
        start = new Button("START");
        start.setStyle("-fx-font: 28 arial; -fx-base: #b6e7c9;");
        start.setTranslateX(610);
        start.setTranslateY(610);
        start.setOnAction(e -> handleButtonAction(e));
    }
    
    public void createBackButton() {
        back = new Button("BACK");
        back.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        back.setTranslateX(550);
        back.setTranslateY(710);
        back.setOnAction(e -> handleButtonAction(e));
    }
    
    public void createStopButton() {
        stopGame = new Button("STOP");
        stopGame.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        stopGame.setTranslateX(715);
        stopGame.setTranslateY(710);
        stopGame.setOnAction(e -> handleButtonAction(e));
    }
    
    public void createJumpButton() {
        jumpToMain = new Button("MAIN MENU");
        jumpToMain.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        jumpToMain.setTranslateX(460);
        jumpToMain.setTranslateY(520);
        jumpToMain.setOnAction(e -> handleButtonAction(e));
    }
    
    public void createExitButton() {
        exit = new Button("EXIT");
        exit.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        exit.setTranslateX(820);
        exit.setTranslateY(520);
        exit.setOnAction(e -> handleButtonAction(e));
    }
    
    public void handleButtonAction(ActionEvent event) {
        if (event.getTarget() == start) {
           
            Game_Screen.player.stop();
            Game_Screen game_ob = new Game_Screen();
            root_game = game_ob.createContent();
            createBackButton();
            createStopButton();
            ((Pane)root_game).getChildren().addAll(back, stopGame);
            Game = new Scene(root_game, 1366.0, 695.0);
            End.getStylesheets().addAll(this.getClass().getResource("/newproject/background/" + Game_Screen.x + "/endstyle.css").toExternalForm());
            Game.getStylesheets().addAll(this.getClass().getResource("/newproject/background/"+ Game_Screen.x +"/gamestyle.css").toExternalForm());
            GameStage.setScene(Game);
            GameStage.setFullScreen(true);
            
        } else if (event.getTarget() == stopGame) {
            Game_Screen.player.stop();
            if (((Game_Screen.P1_score) + (Game_Screen.P2_score)) == 0) {
                End_Screen.congo.setText("Well...");
                End_Screen.winner.setText("You Should have at least played the Game!");
            } else {
                End_Screen.congo.setText("Game Stopped!");
                End_Screen.winner.setText("Player 1 score: "+(Game_Screen.P1_score)*10+" points.\n"+"Player 2 score: "+(Game_Screen.P2_score)*10+" points.");
            }
            GameStage.setScene(End);
            GameStage.setFullScreen(false);
            
        } else if ((event.getTarget() == jumpToMain) || (event.getTarget() == back)) {
            Game_Screen.player.stop();
            Game_Screen.pick = new Media(getClass().getResource("/newproject/Audio/MainScreen/audio.mp3").toExternalForm());
            Game_Screen.player = new MediaPlayer(Game_Screen.pick);
            Game_Screen.player.play();
            GameStage.setScene(Main);
            GameStage.setFullScreen(false);
            
        } else if (event.getTarget() == exit) {
            exit();
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        GameStage = primaryStage;
        //Main Scene starts here
        Main_Screen main_ob = new Main_Screen();
        root_main = main_ob.createContent_new();
        createStartButton();
        ((Pane)root_main).getChildren().add(start);
        Main = new Scene(root_main, 1366.0, 695.0);  
        Main.getStylesheets().addAll(this.getClass().getResource("/newproject/background/mainstyle.css").toExternalForm());
        
        //End Scene starts here
        End_Screen end_ob = new End_Screen();
        root_end = end_ob.createContent_end();
        createJumpButton();
        createExitButton();
        ((Pane)root_end).getChildren().addAll(jumpToMain, exit);
        End = new Scene(root_end, 1366.0, 695.0);
        
        GameStage.setScene(Main);
        GameStage.show();
        GameStage.setTitle("PAIR DUELS!");
        GameStage.setFullScreen(false);
    }
   
    public static void main (String[] args) {
        launch(args);
    }
}
