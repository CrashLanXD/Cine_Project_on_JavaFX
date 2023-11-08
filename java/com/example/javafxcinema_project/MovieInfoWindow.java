package com.example.javafxcinema_project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class MovieInfoWindow extends Application {
    private final Movie movie;
    private final ArrayList<String> selectedSeats;
    public MovieInfoWindow(Movie movie, ArrayList<String> selectedSeats) {
        this.movie = movie;
        this.selectedSeats = selectedSeats;
    }
    @Override
    public void start(Stage stage) throws MalformedURLException {
        stage.setTitle(movie.getTitle());

        BorderPane mainLayout = new BorderPane();
        mainLayout.setStyle("-fx-background-color: linear-gradient(to bottom, #999999, #D1D1D1);");

        VBox leftPane = new VBox(10);
        leftPane.setPadding(new Insets(10));
        leftPane.setAlignment(Pos.TOP_CENTER);

        ImageView movieImageView = new ImageView(new Image(movie.getImage()));
        movieImageView.setFitWidth(200);
        movieImageView.setFitHeight(300);

        Label classificationLabel = new Label("Clasificación: " + movie.getClassification());
        Label durationLabel = new Label("Duración: " + movie.getDuration() + " min");
        Label genreLabel = new Label("Género: " + movie.getGenre());

        TextArea aboutTextArea = new TextArea(movie.getAbout());
        aboutTextArea.setWrapText(true);
        aboutTextArea.setPrefWidth(296);
        aboutTextArea.setPrefHeight(151);
        aboutTextArea.setEditable(false);

        leftPane.getChildren().addAll(movieImageView, classificationLabel, durationLabel, genreLabel, aboutTextArea);

        VBox rightPane = new VBox(10);
        rightPane.setPadding(new Insets(10));
        rightPane.setAlignment(Pos.TOP_LEFT);

        Label scheduleLabel = new Label("Horarios disponibles");
        ComboBox<String> scheduleComboBox = new ComboBox<>();
        scheduleComboBox.getItems().addAll(
                "6/11/23 1:00 PM",
                "6/11/23 3:00 PM",
                "6/11/23 5:00 PM",
                "6/11/23 7:00 PM",
                "6/11/23 9:00 PM",
                "7/11/23 2:00 PM",
                "7/11/23 4:00 PM"
        );

        HBox videoPane = new HBox(10);
        videoPane.setAlignment(Pos.CENTER);

        String videoPath = movie.getTrailer();
        File videoFile = new File(videoPath);
        if (videoFile.exists()) {
            System.out.println("\u001B[33mVideo Path [✓]: " + movie.getTitle() + "\u001B[0m");
            System.out.println("\u001b[31mERROR: not glib-lite found\u001B[0m");
        }
        //String videoURL = movie.getTrailer();

        // Error not found glib-lite library path
        // Don't delete this commented lines!

        String videoURL = videoFile.toURI().toURL().toString();
        Media media = new Media(videoURL);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(400);
        mediaView.setFitHeight(270);
        videoPane.getChildren().add(mediaView);
        mediaPlayer.play();



        CheckBox subscriptionCheckBox = new CheckBox("Membresia");

        Button goToChooseSeatButton = new Button("Selecciona tus asientos");
        goToChooseSeatButton.setOnAction(actionEvent -> {
            selectedSeats.clear();
            ChooseSeats chooseSeatsWindow = new ChooseSeats(selectedSeats, movie);
            try {
                chooseSeatsWindow.start(new Stage());
                stage.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        rightPane.getChildren().addAll(scheduleLabel, scheduleComboBox, videoPane, subscriptionCheckBox, goToChooseSeatButton);

        mainLayout.setLeft(leftPane);
        mainLayout.setRight(rightPane);

        Scene infoScene = new Scene(mainLayout, 740, 465);
        stage.setScene(infoScene);
        stage.show();
    }
}
