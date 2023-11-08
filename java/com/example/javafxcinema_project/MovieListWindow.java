package com.example.javafxcinema_project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MovieListWindow extends Application {
    private final List<Movie> movieList; // Lista de movies // List / ArrayList
    private final ArrayList<String> selectedSeats;

    public MovieListWindow(List<Movie> movieList, ArrayList<String> selectedSeats) {
        this.movieList = movieList;
        this.selectedSeats = selectedSeats;
    }
    @Override
    public void start(Stage stage) {
        stage.setTitle("Cine POP");

        int col = 0;
        int row = 0;

        // VBox para el título del cine
        VBox titleBox = new VBox();
        Label titleLbl = new Label("Cine POP");
        titleLbl.setStyle("-fx-text-fill: #FFA500; -fx-font-size: 36px;");
        titleBox.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #333333;");
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        for (Movie movie : movieList) {
            VBox movieBox = new VBox();

            File imageFile = new File(movie.getImage());
            Button movieButton;
            if (imageFile.exists()) {
                movieButton = createMovieButton(movie.getImage(), 226, 340);
                System.out.println("\u001B[32mImage path [✓]: " + movie.getTitle() + "\u001B[0m");
            } else {
                movieButton = createMovieButton("C:\\Users\\Eduardo\\Desktop\\JavaFX-cinema_project\\src\\main\\java\\img\\imgExample.jpg", 226, 340);
                System.out.println("\u001B[31mImage path [X]: " + movie.getTitle() + "\u001B[0m");
            }

            movieButton.setStyle("-fx-background-color: #333333;");
            movieButton.setOnAction(actionEvent -> {
                MovieInfoWindow movieInfoWindow = new MovieInfoWindow(movie, selectedSeats);
                try {
                    movieInfoWindow.start(new Stage());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            });

            Label titleLabel = new Label(movie.getTitle());
            titleLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
            titleLabel.setAlignment(Pos.CENTER);

            movieBox.setAlignment(Pos.CENTER);
            movieBox.getChildren().addAll(movieButton, titleLabel);

            gridPane.add(movieBox, col, row);
            col++;
            if (col >= 2) {
                col = 0;
                row++;
            }
        }
        gridPane.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(gridPane);
        scrollPane.setFitToWidth(true); // ajusta el scroll al ancho de la ventana

        VBox mainBox = new VBox();
        mainBox.setStyle("-fx-background-color: #444444;");
        mainBox.getChildren().addAll(titleBox, scrollPane);

        Scene scene = new Scene(mainBox, 800, 680);
        stage.setScene(scene);
        stage.show();
    }

    public Button createMovieButton(String imgPath, int fitWidth, int fitHeight) {
        ImageView imageView = new ImageView(new Image(imgPath));
        imageView.setFitWidth(fitWidth);
        imageView.setFitHeight(fitHeight);
        return new Button("", imageView);
    }
}