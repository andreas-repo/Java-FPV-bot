package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.routines.InetAddressValidator;

import java.io.*;
import java.net.Socket;

public class UserInterface extends Application {

    //private String ip;
    private String webcamIp;
    private SocketConnection socket;
    //private int port = 80;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml_userinterface.fxml"));
        primaryStage.setTitle("Control Software FPV-bot");

        this.socket = new SocketConnection();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text movementControlTitle = new Text("Movement Control");
        movementControlTitle.setId("movementControlTitle");
        HBox hbMovementControlTitle = new HBox( 10);
        hbMovementControlTitle.setAlignment(Pos.CENTER);
        hbMovementControlTitle.getChildren().add(movementControlTitle);
        grid.add(hbMovementControlTitle, 2, 0);

        Text movementControlExplanation = new Text("Please enter the network IP of the FPV-bot.\n" +
                "For more information please read thr readme file.");
        movementControlExplanation.setId("movementControlExplanation");
        HBox hbMovementControlExplanation = new HBox(10);
        hbMovementControlExplanation.setAlignment(Pos.CENTER);
        hbMovementControlExplanation.getChildren().addAll(movementControlExplanation);
        grid.add(hbMovementControlExplanation, 0, 2, 5, 2);

        Label setIpLabel = new Label("Set IP:");
        grid.add(setIpLabel, 0, 4, 1, 1);

        TextField setIpTextField = new TextField();
        grid.add(setIpTextField, 1, 4, 2, 1);

        Button setIpButton = new Button("Submit");
        HBox hbSetIpButton = new HBox(10);
        hbSetIpButton.setAlignment(Pos.CENTER_RIGHT);
        hbSetIpButton.getChildren().add(setIpButton);
        grid.add(hbSetIpButton, 3, 4, 1, 1);

        setIpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                InetAddressValidator validator = new InetAddressValidator();
                if(validator.isValid(setIpTextField.getText())) {
                    UserInterface.this.socket.setIp(setIpTextField.getText());

                } else {
                    System.out.println("Invalid IP-Address");
                    setIpTextField.setText("Invalid IP-Address");
                }

            }
        });

        Button turboButton = new Button("Turbo");
        HBox hbTurboButton = new HBox(10);
        hbTurboButton.setAlignment(Pos.CENTER);
        hbTurboButton.getChildren().add(turboButton);
        grid.add(hbTurboButton, 1, 6, 2, 1);

        turboButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TURBO
                try {
                    UserInterface.this.socket.turbo_on();
                } catch (IOException e) {
                    e.printStackTrace();
                    e.getCause();
                }
            }
        });

        Button forwardButton = new Button("Forward");
        HBox hbForwardButton = new HBox(10);
        hbForwardButton.setAlignment(Pos.CENTER);
        hbForwardButton.getChildren().add(forwardButton);
        grid.add(hbForwardButton, 1, 7, 2, 1);

        forwardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //FORWARD
                System.out.println("FORWARD");
                try {
                    UserInterface.this.socket.forward_on();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button turnLeftButton = new Button("Turn Left");
        HBox hbTurnLeftButton = new HBox(10);
        hbTurnLeftButton.setAlignment(Pos.CENTER_LEFT);
        hbTurnLeftButton.getChildren().add(turnLeftButton);
        grid.add(hbTurnLeftButton, 0, 8, 1, 1);

        turnLeftButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TURN LEFT
                try {
                    UserInterface.this.socket.turnLeft();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button turnRightButton = new Button("Turn Right");
        HBox hbTurnRightButton = new HBox(10);
        hbTurnRightButton.setAlignment(Pos.CENTER_RIGHT);
        hbTurnRightButton.getChildren().add(turnRightButton);
        grid.add(hbTurnRightButton, 3, 8, 1, 1);

        turnRightButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TURN RIGHT
                try {
                    UserInterface.this.socket.turnRight();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button stopButton = new Button("Stop");
        HBox hbStopButton = new HBox(10);
        hbStopButton.setAlignment(Pos.CENTER);
        hbStopButton.getChildren().add(stopButton);
        grid.add(hbStopButton,1, 9, 2, 1);

        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //STOP
                System.out.println("STOP");
                try {
                    UserInterface.this.socket.turnOff();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button reverseButton = new Button("Reverse");
        HBox hbReverseButton = new HBox(10);
        hbReverseButton.setAlignment(Pos.CENTER);
        hbReverseButton.getChildren().add(reverseButton);
        grid.add(hbReverseButton, 1, 10, 2, 1);

        reverseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //REVERSE
                System.out.println("REVERSE");
                try {
                    UserInterface.this.socket.reverseOn();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Text webcamControlTitle = new Text("Webcam Control");
        webcamControlTitle.setId("webcamControlTitle");
        HBox hbWebcamControlTitle = new HBox(10);
        hbWebcamControlTitle.setAlignment(Pos.TOP_CENTER);
        hbWebcamControlTitle.getChildren().add(webcamControlTitle);
        grid.add(hbWebcamControlTitle, 7, 0, 1, 1);


        Text webcamControlExplanation = new Text("Please enter the IP of the FPV-bot Camera. \n" +
                "For help about how to connect, please read the readme file.");
        webcamControlExplanation.setId("webcamControlExplanation");
        HBox hbWebcamControlExplanation = new HBox(10);
        hbWebcamControlExplanation.setAlignment(Pos.CENTER);
        hbWebcamControlExplanation.getChildren().add(webcamControlExplanation);
        grid.add(hbWebcamControlExplanation,5, 2, 5, 2);

        Label webcamControlLabel = new Label("Set Webcam IP:");
        webcamControlLabel.setId("webcamControlLabel");
        grid.add(webcamControlLabel, 5, 4, 1, 1);

        TextField webcamControlTextField = new TextField();
        grid.add(webcamControlTextField, 6, 4, 2, 1);

        Button webcamSetIpButton = new Button("Open Webcam");
        HBox hbWebcamSetIpButton = new HBox(10);
        hbWebcamSetIpButton.setAlignment(Pos.CENTER_RIGHT);
        hbWebcamSetIpButton.getChildren().addAll(webcamSetIpButton);
        grid.add(hbWebcamSetIpButton, 8, 4, 1,1);

        webcamSetIpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                InetAddressValidator validator = new InetAddressValidator();
                if(validator.isValid(webcamControlTextField.getText())) {
                    webcamIp = webcamControlTextField.getText();
                    //System.out.println(webcamIp);
                    OpenWebcam openWebcam = new OpenWebcam(webcamIp);
                }else {
                    System.out.println("Invalid IP-Address");
                    webcamControlTextField.setText("Invalid IP-Address");
                }
            }
        });

        Button webcamPivotLeftButton = new Button("Pivot Left");
        HBox hbWebcamPivotLeftButton = new HBox(10);
        hbWebcamPivotLeftButton.setAlignment(Pos.CENTER);
        hbWebcamPivotLeftButton.getChildren().addAll(webcamPivotLeftButton);
        grid.add(hbWebcamPivotLeftButton, 5, 8, 1, 1);

        webcamPivotLeftButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //PIVOT CAM LEFT
                try {
                    UserInterface.this.socket.servo_left();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button webcamPivotMittleButton = new Button("Pivot Middle");
        HBox hbWebcamPivotMittleButton = new HBox(10);
        hbWebcamPivotMittleButton.setAlignment(Pos.CENTER);
        hbWebcamPivotMittleButton.getChildren().addAll(webcamPivotMittleButton);
        grid.add(hbWebcamPivotMittleButton, 7, 8, 1, 1);

        webcamPivotMittleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //PIVOT CAM MIDDLE
                try {
                    UserInterface.this.socket.servo_middle();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button webcamPivotRightButton = new Button("Pivot Right");
        HBox hbWebcamPivotRightButton = new HBox(10);
        hbWebcamPivotRightButton.setAlignment(Pos.CENTER);
        hbWebcamPivotRightButton.getChildren().addAll(webcamPivotRightButton);
        grid.add(hbWebcamPivotRightButton, 8, 8, 1, 1);

        webcamPivotRightButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //PIVOT CAM RIGHT
                try {
                    UserInterface.this.socket.servo_right();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 820, 400);
        scene.getStylesheets().add("userinterface.css");
        primaryStage.getIcons().add(new Image("robot-30.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
