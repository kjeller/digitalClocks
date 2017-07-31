package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDateTime;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // gets the text objects
        Text binary = (Text)root.getChildrenUnmodifiable().get(0);
        Text hex = (Text)root.getChildrenUnmodifiable().get(1);
        Text digital = (Text)root.getChildrenUnmodifiable().get(2);

        primaryStage.setTitle("DigitalClocks");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        // the binary clock updates once a second.
        final Timeline binaryClock = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        e -> setCurrentTime(binary, 2)),
                new KeyFrame(Duration.seconds(1))
        );
        // indefinite cycles will make sure clock updates
        binaryClock.setCycleCount(Timeline.INDEFINITE);
        binaryClock.play();

        // the hex clock updates once a second.
        final Timeline hexClock = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        e -> setCurrentTime(hex, 16)),
                new KeyFrame(Duration.seconds(1))
        );
        // indefinite cycles will make sure clock updates
        hexClock.setCycleCount(Timeline.INDEFINITE);
        hexClock.play();

        // the clock updates once a second.
        final Timeline digitalClock = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        e -> setCurrentTime(digital, 10)),
                new KeyFrame(Duration.seconds(1))
        );
        // indefinite cycles will make sure clock updates
        digitalClock.setCycleCount(Timeline.INDEFINITE);
        digitalClock.play();
    }

    // this method will be called every second and it fetches the current time
    // and sets text object with some formatting.
    private void setCurrentTime(Text currentTime, int base) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String hour = convertToStringFromBase(localDateTime.getHour(), base);
        String minute = convertToStringFromBase(localDateTime.getMinute(), base);
        String second = convertToStringFromBase(localDateTime.getSecond(), base);
        currentTime.setText(hour + ":" + minute + ":" + second);
    }

    // converts int to string with known base
    private String convertToStringFromBase(int value, int base) {
        return Integer.toString(value, base);
    }
}