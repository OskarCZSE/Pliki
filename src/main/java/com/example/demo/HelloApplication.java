package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javafx.scene.control.TextArea;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane gridPane = new GridPane();
        GridPane ReadPanel = new GridPane();
        GridPane ReadPanel1 = new GridPane();
        GridPane ReadPanel2 = new GridPane();
        GridPane ReadPanel3 = new GridPane();
        GridPane WritePanel = new GridPane();
        GridPane WritePanel1 = new GridPane();
        GridPane WritePanel2 = new GridPane();
        GridPane[] ReadPanels = {ReadPanel, ReadPanel1, ReadPanel2, ReadPanel3};
        GridPane[] WritePanels = {WritePanel1, WritePanel2};
        Button ReadBtn = new Button("Odczytaj");
        Button OverwriteBtn = new Button("Nadpisz");
        Button EndBtn = new Button("KONIEC");
        TextField ReadField = new TextField();
        TextArea FileReadField = new TextArea();
        TextArea OverwriteField = new TextArea();
        Text ReadText = new Text("Plik do odczytu:");
        final String[] TextReaded = new String[1];

        EventHandler<ActionEvent> eventRead = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                TextReaded[0] = "";
                try {
                    File file = new File(System.getProperty("user.dir") + "/src/main/java/com/example/demo/" + ReadField.getText());
                    Scanner reader = new Scanner(file);
                    while (reader.hasNextLine()) {
                        String line = reader.nextLine();
                        TextReaded[0] = TextReaded[0] + line + "\n";
                    }
                    FileReadField.setText(TextReaded[0]);
                    reader.close();
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        };

        EventHandler<ActionEvent> eventOverwrite = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String TextToOverwrite = OverwriteField.getText();
                try {
                    File file = new File(System.getProperty("user.dir") + "/src/main/java/com/example/demo/" + ReadField.getText());
                    FileWriter Writer = new FileWriter(file);
                    Writer.write(TextToOverwrite);
                    Writer.close();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        };

        EventHandler<ActionEvent> eventEnd = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                stage.close();
            }
        };

        ReadBtn.setOnAction(eventRead);
        OverwriteBtn.setOnAction(eventOverwrite);
        EndBtn.setOnAction(eventEnd);

        gridPane.setHgap(10);
        gridPane.add(ReadPanel,1,1);
        gridPane.add(WritePanel,1,2);

        CreateBorder(ReadPanel);
        CreateBorder(WritePanel);

        SetGapAll(ReadPanels, 5);
        SetGapAll(WritePanels, 5);

        ReadPanel.add(ReadPanel1, 0, 0);
        ReadPanel.add(ReadPanel2, 0, 1);
        ReadPanel.add(ReadPanel3, 0, 2);

        WritePanel.add(WritePanel1, 0, 0);
        WritePanel.add(WritePanel2, 0, 4);

        ReadPanel1.add(ReadText, 4, 0);
        ReadPanel1.add(ReadField, 8, 0);

        ReadPanel2.add(FileReadField, 4, 2);
        FileReadField.setMaxSize(253, 100);

        ReadPanel3.add(ReadBtn, 42, 0);

        WritePanel1.add(OverwriteField, 4, 0);
        OverwriteField.setMaxSize(253, 150);

        WritePanel2.add(OverwriteBtn, 43, 2);
        WritePanel2.add(EndBtn, 43, 4);

        Scene scene = new Scene(gridPane, 310, 480);
        stage.setTitle("Odczyt pliku");
        stage.setScene(scene);
        stage.show();
    }

    public void CreateBorder(GridPane Panel){
        Panel.setStyle("-fx-border-style: solid inside;");
        Panel.setStyle("-fx-border-color: black;");
        Panel.setPadding(new Insets(10, 15, 15, 0));
    }
    public void SetGap(GridPane Panel, int Size){
        Panel.setVgap(Size);
        Panel.setHgap(Size);
    }
    public void SetGapAll(GridPane[] Panels, int Size){
        for (GridPane panel : Panels) {
            SetGap(panel, 5);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}