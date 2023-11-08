package com.example.javafxcinema_project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ChooseSeats extends Application {
    private final ArrayList<String> selectedSeats;
    private Movie movie;

    public ChooseSeats(ArrayList<String> selectedSeats, Movie movie) {
        this.selectedSeats = selectedSeats;
        this.movie = movie;
    }
    @Override
    public void start(Stage stage) {
        stage.setTitle("Elije un lugar para " + movie.getTitle() + "!");
        stage.setResizable(false);

        GridPane gridPane = new GridPane();

        Rectangle screen = new Rectangle(600, 20, Color.GRAY);
        StackPane screenPane = new StackPane(screen);
        HBox seatLegend = createSeatLegend();

        int colInit = 1;
        int colEnd = 10;
        GridPane.setConstraints(screenPane, 0, 0, colEnd - colInit + 1, 1);
        char rowInit = 'A';
        char rowEnd = 'E';
        GridPane.setConstraints(seatLegend, 0, rowEnd - rowInit + 2, colEnd - colInit + 1, 1);

        gridPane.getChildren().addAll(screenPane, seatLegend);

        for (char row = rowInit; row <= rowEnd; row++) {
            for (int col = colInit; col <= colEnd; col++) {
                String seatName = String.valueOf(row) + col;
                Button seatButton = new Button(seatName);
                seatButton.setMinSize(50, 50);
                seatButton.setMaxSize(50, 50);
                seatButton.setCursor(Cursor.HAND);
                seatButton.setOnAction(event -> toggleSeatSelection(seatButton, seatName));
                gridPane.add(seatButton, col - colInit, row - rowInit + 1);
                GridPane.setMargin(seatButton, new Insets(0, 10, 0, 0));
            }
        }

        Button confirmButton = new Button("Confirmar!");
        confirmButton.setOnAction(event -> {
            for (String string :
                    selectedSeats) {
                System.out.print(string + ", ");
            }
            PaymentWindow paymentWindow = new PaymentWindow(selectedSeats, movie);
            try {
                paymentWindow.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stage.close();
        });

        Button buyAllButton = new Button("Comprar Todos!");
        buyAllButton.setOnAction(actionEvent -> {
            System.out.println("Sala normal, todos los asientos");
            selectAllSeats(gridPane);
        });

        Button rentGameRoomButton = new Button("Rentar Sala para Videojuegos");
        rentGameRoomButton.setOnAction(actionEvent -> {
            System.out.println("Sala game, todos los asientos");
            selectAllSeats(gridPane);
        });

        HBox buttonBox = new HBox(confirmButton, buyAllButton, rentGameRoomButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        gridPane.add(buttonBox, 0, rowEnd - rowInit + 3, colEnd - colInit + 1, 1);
        GridPane.setMargin(buttonBox, new Insets(10, 0, 0, 0));

        Scene scene = new Scene(gridPane, 590, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void selectAllSeats(GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button seatButton) {
                String seatName = seatButton.getText();

                if (!selectedSeats.contains(seatName)) {
                    selectedSeats.add(seatName);
                    seatButton.setStyle("-fx-background-color: green");
                }
            }
        }
    }

    private HBox createSeatLegend() {
        Rectangle available = new Rectangle(50, 20, Color.LIGHTGRAY);
        Label availableLbl = new Label("Disponible");
        Rectangle selected = new Rectangle(50, 20, Color.GREEN);
        Label selectedLbl = new Label("Seleccionado");
        Rectangle occupied = new Rectangle(50, 20, Color.RED);
        Label occupiedLbl = new Label("Ocupado");
        HBox legend = new HBox(10, available, availableLbl, selected, selectedLbl, occupied, occupiedLbl);
        legend.setAlignment(Pos.CENTER);
        return legend;
    }

    private void toggleSeatSelection(Button button, String seatName) {
        if (selectedSeats.contains(seatName)) {
            selectedSeats.remove(seatName);
            button.setStyle(null);
        } else {
            selectedSeats.add(seatName);
            button.setStyle("-fx-background-color: green;");
        }
    }
}

