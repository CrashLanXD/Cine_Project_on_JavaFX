package com.example.javafxcinema_project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PaymentWindow extends Application {
    private final ArrayList<String> selectedSeats;
    Movie movie;

    public PaymentWindow(ArrayList<String> selectedSeats, Movie movie) {
        this.selectedSeats = selectedSeats;
        this.movie = movie;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Ventana de pago");
        int maxTickets = selectedSeats.size();

        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(20));

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label adultsLabel = new Label("Adultos:");
        TextField adultsTextField = new TextField();

        Label minorsLabel = new Label("Menores:");
        TextField minorsTextField = new TextField();
        minorsTextField.setDisable(movie.getClassification().equals("C"));

        Label seniorsLabel = new Label("Adultos Mayores:");
        TextField seniorsTextField = new TextField();
        seniorsTextField.setDisable(movie.getClassification().equals("B-15"));

        grid.add(adultsLabel, 0, 0);
        grid.add(adultsTextField, 1, 0);
        grid.add(minorsLabel, 0, 1);
        grid.add(minorsTextField, 1, 1);
        grid.add(seniorsLabel, 0, 2);
        grid.add(seniorsTextField, 1, 2);

        Label availableSeatsLabel = new Label("Asientos disponibles: " + maxTickets);
        HBox seatsInfo = new HBox(20, grid, availableSeatsLabel);

        Label paymentInfoLabel = new Label("Datos de pago");
        Label cardNumberLabel = new Label("Número de tarjeta:");
        TextField cardNumberField = new TextField();
        Label nameLabel = new Label("Nombre:");
        TextField nameField = new TextField();
        Label emailLabel = new Label("Correo Electrónico:");
        TextField emailField = new TextField();

        VBox paymentInfo = new VBox(10, paymentInfoLabel, cardNumberLabel, cardNumberField, nameLabel, nameField, emailLabel, emailField);
        paymentInfo.setPrefWidth(300);

        Button confirmButton = new Button("Confirmar");
        confirmButton.setOnAction(actionEvent -> {
            int adults = parseTextField(adultsTextField);
            int minors = parseTextField(minorsTextField);
            int seniors = parseTextField(seniorsTextField);

            if (adults + minors + seniors != maxTickets) {
                showAlert("Advertencia", "La suma de boletos no coincide con el número total de asientos.");
                return;
            }

            String cardNumber = cardNumberField.getText();
            String name = nameField.getText();
            String email = emailField.getText();

            System.out.println("Tickets para Adultos: " + adults);
            System.out.println("Tickets para Menores: " + minors);
            System.out.println("Tickets para Adultos Mayores: " + seniors);
            System.out.println("Número de Tarjeta: " + cardNumber);
            System.out.println("Nombre: " + name);
            System.out.println("Correo Electrónico: " + email);
            System.out.println("Ir a la generación de boletos >>>");

            TicketWindow ticketWindow = new TicketWindow(0, minors, adults, seniors, selectedSeats);
            try {
                ticketWindow.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stage.close();
        });

        VBox paymentVBox = new VBox(20, seatsInfo, paymentInfo, confirmButton);
        paymentVBox.setPadding(new Insets(20));
        vBox.getChildren().addAll(paymentVBox);

        Scene scene = new Scene(vBox, 600, 420);
        stage.setScene(scene);
        stage.show();
    }

    private int parseTextField(TextField textField) {
        try {
            if (!textField.isDisabled() && !textField.getText().isEmpty()) {
                return Integer.parseInt(textField.getText());
            }
        } catch (NumberFormatException e) {
            return 0;
        }
        return 0;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(content);
        alert.showAndWait();
    }
}