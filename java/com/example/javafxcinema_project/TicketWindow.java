package com.example.javafxcinema_project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TicketWindow extends Application {
    private double moneyPaid;
    private final int quantityA;
    private final int quantityB;
    private final int quantityC;
    private final ArrayList<String> selectedSeats;

    public TicketWindow(double moneyPaid, int quantityA, int quantityB, int quantityC, ArrayList<String> selectedSeats) {
        this.moneyPaid = moneyPaid;
        this.quantityA = quantityA;
        this.quantityB = quantityB;
        this.quantityC = quantityC;
        this.selectedSeats = selectedSeats;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Ticket de Compra");

        // Título
        Label title = new Label("Ticket de Compra");
        title.setFont(new Font(24));

        // Detalles de los asientos seleccionados
        Label seatsLabel = new Label("Asientos seleccionados:");
        Label seatList = new Label(String.join(", ", selectedSeats));

        // Detalles de cantidad de asientos por categoría
        Label itemALabel = new Label("Cantidad de asientos de menores: " + quantityA);
        Label itemBLabel = new Label("Cantidad de asientos de adultos: " + quantityB);
        Label itemCLabel = new Label("Cantidad de asientos de adultos mayores: " + quantityC);

        // Cálculo y visualización del precio total
        double totalPrice = calculateTotalPrice();
        Label totalPriceLabel = new Label("Total: $" + String.format("%.2f", totalPrice));
        moneyPaid = totalPrice;

        // Visualización del pago del usuario
        Label paidLabel = new Label("Pago del usuario: $" + String.format("%.2f", moneyPaid));

        // Imagen del ticket
        ImageView imageView = new ImageView(new Image("C:\\Users\\Eduardo\\Desktop\\JavaFX-cinema_project\\src\\main\\resources\\img\\img_QR.png"));
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: #EEEEEE; -fx-padding: 20;");
        root.getChildren().addAll(title, seatsLabel, seatList, itemALabel, itemBLabel, itemCLabel, totalPriceLabel, paidLabel, imageView);

        Scene scene = new Scene(root, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    private double calculateTotalPrice() {
        double priceA = 35.0;
        double priceB = 50.0;
        double priceC = 40.0;
        return (quantityA * priceA) + (quantityB * priceB) + (quantityC * priceC);
    }
}
