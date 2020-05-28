package com.meerzulee.view;

import com.meerzulee.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Menu  implements Initializable {
    private String map;
    @FXML
    private Button startButton;
    @FXML
    private ChoiceBox choiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(String s : Main.getMaps()){
            if (s.endsWith(".txt")){
                choiceBox.getItems().add(s);
            }
         }
        setMap(Main.getMaps().get(0));
        choiceBox.getSelectionModel().select(0);

        choiceBox.getSelectionModel().selectedItemProperty().addListener(
                (ov,  value,  new_value)->{
                    setMap(new_value);
                });

//        System.out.println(finalFilePath);
        startButton.setOnAction(event -> {
            Main.startGame(getPath(),getMap());
        });
    }

    public String getPath() {
        return "resources/maps/";
    }

    public String getMap() {
        return map;
    }

    public void setMap(Object map) {
        this.map = map.toString();
    }
}
