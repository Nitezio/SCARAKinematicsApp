package org.example.scarakinematicsapp;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SCARAKinematicsApp extends Application {

    // Inputs
    private TextField tfA1, tfA2, tfQ1, tfQ2;
    private Canvas canvas;
    private GraphicsContext gc;

    // Constants for rendering
    private static final double CANVAS_WIDTH = 600;
    private static final double CANVAS_HEIGHT = 400;
    private static final double CENTER_X = CANVAS_WIDTH / 2;
    private static final double CENTER_Y = CANVAS_HEIGHT / 2;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CSC4702 - Assignment 1: Forward Kinematics");

        // 1. Input Section
        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(10));
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setAlignment(Pos.CENTER);

        tfA1 = new TextField("100"); // Default length a1
        tfA2 = new TextField("100"); // Default length a2
        tfQ1 = new TextField("45");  // Default angle q1
        tfQ2 = new TextField("45");  // Default angle q2

        inputGrid.add(new Label("Link 1 Length (a1):"), 0, 0);
        inputGrid.add(tfA1, 1, 0);
        inputGrid.add(new Label("Link 2 Length (a2):"), 0, 1);
        inputGrid.add(tfA2, 1, 1);
        inputGrid.add(new Label("Angle 1 (q1 deg):"), 2, 0);
        inputGrid.add(tfQ1, 3, 0);
        inputGrid.add(new Label("Angle 2 (q2 deg):"), 2, 1);
        inputGrid.add(tfQ2, 3, 1);

        // 2. Buttons
        Button btnDraw = new Button("Draw Pose");
        Button btnReverse = new Button("Reverse Motion");

        btnDraw.setOnAction(e -> drawRobot());
        btnReverse.setOnAction(e -> reverseMotion());

        // 3. Canvas for Drawing
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        drawGrid(); // Draw initial background

        // Layout
        VBox root = new VBox(15);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(inputGrid, btnDraw, btnReverse, canvas);

        Scene scene = new Scene(root, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initial draw
        drawRobot();
    }

    private void drawGrid() {
        gc.setFill(Color.WHITESMOKE);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(1);

        // Draw Axis
        gc.setStroke(Color.BLACK);
        gc.strokeLine(CENTER_X, 0, CENTER_X, CANVAS_HEIGHT); // Y Axis
        gc.strokeLine(0, CENTER_Y, CANVAS_WIDTH, CENTER_Y);  // X Axis
        gc.fillText("(0,0) Base", CENTER_X + 5, CENTER_Y + 15);
    }

    private void drawRobot() {
        try {
            // Parse inputs
            double a1 = Double.parseDouble(tfA1.getText());
            double a2 = Double.parseDouble(tfA2.getText());
            double q1Deg = Double.parseDouble(tfQ1.getText());
            double q2Deg = Double.parseDouble(tfQ2.getText());

            // Clear canvas
            drawGrid();

            // Calculate Forward Kinematics
            double q1Rad = Math.toRadians(q1Deg);
            double q2Rad = Math.toRadians(q2Deg);

            // Joint 1 is at (CENTER_X, CENTER_Y)
            double x1 = CENTER_X + a1 * Math.cos(q1Rad);
            double y1 = CENTER_Y - a1 * Math.sin(q1Rad); // Inverted Y

            // End Effector position (End of Link 2)
            double xE = x1 + a2 * Math.cos(q1Rad + q2Rad);
            double yE = y1 - a2 * Math.sin(q1Rad + q2Rad); // Inverted Y

            // DRAWING
            gc.setLineWidth(5);

            // Link 1 (Red)
            gc.setStroke(Color.RED);
            gc.strokeLine(CENTER_X, CENTER_Y, x1, y1);

            // Link 2 (Blue)
            gc.setStroke(Color.BLUE);
            gc.strokeLine(x1, y1, xE, yE);

            // Joints (Circles)
            gc.setFill(Color.BLACK);
            gc.fillOval(CENTER_X - 5, CENTER_Y - 5, 10, 10); // Base
            gc.fillOval(x1 - 5, y1 - 5, 10, 10);             // Joint 2

            // End Effector Label
            gc.setFill(Color.DARKGREEN);
            gc.fillText(String.format("End (%.1f, %.1f)", (xE - CENTER_X), -(yE - CENTER_Y)), xE + 10, yE);

        } catch (NumberFormatException ex) {
            System.out.println("Please enter valid numbers");
        }
    }

    private void reverseMotion() {
        Timeline timeline = new Timeline();
        tfQ1.setText("0");
        tfQ2.setText("0");
        drawRobot();
    }

    public static void main(String[] args) {
        launch(args);
    }
}