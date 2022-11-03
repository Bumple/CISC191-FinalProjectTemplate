package edu.sdccd.cisc191.template;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class SelectionScreenController extends Application{
    private MonsterBuilder builder = new MonsterBuilder();
    @FXML Button firstButton;
    @FXML Button secondButton;
    @FXML Button thirdButton;
    @FXML Button readyButton;
    @FXML
    Label selectYourThingLabel;
    private Stage stage;

    private int[] selections = new int[]{1,1,1};
    private int count = 0;
    private String[][] names = new String[][]{
            { "Water", "Fire", "Earth"},
            {"Vampiric", "Blurry", "Lucky Shot"},
            {"Tank", "Balanced", "Assassin"}
    };
    public void changeSelections(int a){
        selections[count] = a;
        if(count<2) {
            count++;
            firstButton.setText(names[count][0]);
            secondButton.setText(names[count][1]);
            thirdButton.setText(names[count][2]);
        }
        else{
            readyButton.setVisible(true);
        }

    }
    public void numOnePicked(ActionEvent actionEvent)   {
        changeSelections(1);
    }


    public void numTwoPicked(ActionEvent actionEvent) {
        changeSelections(2);
    }

    public void numThreePicked(ActionEvent actionEvent) {
        changeSelections(3);
    }

    public void readySelected(ActionEvent actionEvent) {
        Monster bumbo = builder.customMonster(selections[0],selections[1],selections[2]);
        try {
            Socket sender = new Socket(InetAddress.getLocalHost(), 1099);
            ObjectOutputStream out = new ObjectOutputStream(sender.getOutputStream());
            out.writeObject(bumbo);
            out.flush();
            out.close();
            Parent root = FXMLLoader.load(getClass().getResource("/fightTemplateForOldVersion.fxml"));
            Scene scene = new Scene(root);
            stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }

         catch (IOException e){
            System.out.println(e);
            }

       //((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void start(Stage stage) throws Exception {
        Parent root =  FXMLLoader.load(getClass().getResource("/character_creation_screen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
