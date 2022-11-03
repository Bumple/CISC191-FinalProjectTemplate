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

public class FightScreen extends Application{
    @FXML Button runButt;
    @FXML Button scratchButt;
    @FXML Button lightHitButt;
    @FXML Button strongHitButt;
    @FXML Button beginButton;
    @FXML Label enemyName;
    @FXML Label enemyHP;
    @FXML Label myHP;
    @FXML Label myAP;
    @FXML Label enemyStatsLabel;
    @FXML Label myTurnResults;
    @FXML Label enemyTurnResults;
    @FXML Label myNameLabel;
    private updatedInfo info;
    FightClient bill = new FightClient();
    public void checkEnding() throws Exception {

        Integer[] ended = bill.checkEnding();
        if (ended[0] == 1){
            strongHitButt.setVisible(false);
            lightHitButt.setVisible(false);
            enemyName.setVisible(false);
            myAP.setVisible(false);
            myTurnResults.setVisible(false);
            enemyTurnResults.setVisible(false);
            scratchButt.setVisible(false);
            myNameLabel.setVisible(false);
            enemyStatsLabel.setVisible(false);
            runButt.setText("You Won!");
            if(ended[1]== 1){
                runButt.setText("You Won!");
            } else {
                runButt.setText("You lost:(");
            }
        }
    }

    public void updateLabels() throws Exception {
        info = bill.updateAll();
        checkEnding();
        enemyTurnResults.setText("Enemy hit for " + info.getEnemyAttackResults().toString() + " damage");
        myTurnResults.setText("You hit for " + info.getMyAttackResults().toString() + " damage");
        enemyHP.setText("HP: " + info.getEnemyHealth().toString());
        myHP.setText("HP: " + info.getMyHPandAP().toString());
    }


    public void start(Stage stage) throws Exception {
        Parent root =  FXMLLoader.load(getClass().getResource("/fightTemplateForOldVersion.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void heavyPressed(ActionEvent actionEvent) throws Exception {
        try {
            bill.sendHit(1);
            updateLabels();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    public void lightPressed(ActionEvent actionEvent) {
        try {
            bill.sendHit(2);
            updateLabels();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    public void scratchedPressed(ActionEvent actionEvent) {
        try {
            bill.sendHit(3);
            updateLabels();
        }
          catch (Exception e){
            System.out.println(e);
        }

    }
    public void runAway(ActionEvent actionEvent) {
        try {
            bill.stopConnection();
        }
        catch (IOException e){
            System.out.println(e);
        }
        finally {
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide(); //closes scene after attempting to close socket
        }

    }
    public void changeHits(String[] elementHits){
        lightHitButt.setText(elementHits[1]);
        strongHitButt.setText(elementHits[0]);
    }

    public void begin(ActionEvent actionEvent) throws IOException {
        bill.startConnection();
        beginButton.setVisible(false);

        try {
          String[] hitNames = bill.readElements();
          changeHits(hitNames);
          enemyStatsLabel.setText("Enemy " + bill.readStats());
          myHP.setText("HP " + bill.readStats());
          myAP.setText("AP " + bill.readStats());
          enemyHP.setText("HP: " + bill.readStats());
        }
        catch (Exception e){
            System.out.println(e);
        }
        strongHitButt.setVisible(true);
        lightHitButt.setVisible(true);
        enemyName.setVisible(true);
        enemyHP.setVisible(true);
        myHP.setVisible(true);
        myAP.setVisible(true);
        enemyStatsLabel.setVisible(true);
        myTurnResults.setVisible(true);
        enemyTurnResults.setVisible(true);
        runButt.setVisible(true);
        scratchButt.setVisible(true);
        myNameLabel.setVisible(true);
    }
}

