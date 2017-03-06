/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//this is the main app
package GPACalc;

import GPACalc.AlertBox;
import GPACalc.GradesWindow;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static int numberOfClasses;
    public static int numberOfGradingPeriods;
    private String classInfo;
    private String periodsInfo;
    private static final GridPane rootPane = new GridPane();
    public static TextField[][] gradesFields;
    private TextField[] classNamesFields;
    private final Label totalGPAField = new Label();
    private final Button gradeInputButton = new Button("Add Grades");
    private final Button calculateButton = new Button("Calculate GPA");
    public static String[] classNames;

    static float[][] gradeGrid;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField classField = new TextField();
        TextField gradingPeriodField = new TextField();
        Label classLabel = new Label("Number of classes:");
        Label gradingPeriodLabel = new Label("Number of grading periods:");
        Dialog<String> notANumber = new Dialog<>();
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        notANumber.getDialogPane().getButtonTypes().add(okButton);
        notANumber.setTitle("Invalid Input");


        rootPane.setAlignment(Pos.CENTER);
        rootPane.setHgap(10);
        rootPane.setVgap(10);
        rootPane.setPadding(new Insets(25, 25, 25, 25));


        Button startButton = new Button("Start");
        Button continueButton = new Button("Continue");
        Button continueButton2 = new Button("Continue");

        rootPane.add(startButton, 0, 0);

        startButton.setOnAction(event -> {

            rootPane.getChildren().remove(startButton);
            rootPane.add(classLabel, 0, 0);
            rootPane.add(gradingPeriodLabel, 0, 1);
            rootPane.add(classField, 1, 0);
            rootPane.add(gradingPeriodField, 1, 1);
            rootPane.add(continueButton, 1, 2);
        });

        //First Continue Button
        continueButton.setOnAction(event -> {
            try {
                classInfo = classField.getText();
                periodsInfo = gradingPeriodField.getText();
                numberOfClasses = Integer.parseInt(classInfo);
                numberOfGradingPeriods = Integer.parseInt(periodsInfo);

                rootPane.getChildren().remove(classLabel);
                rootPane.getChildren().remove(classField);
                rootPane.getChildren().remove(gradingPeriodField);
                rootPane.getChildren().remove(gradingPeriodLabel);
                rootPane.getChildren().remove(continueButton);

                classNamesFields = new TextField[numberOfClasses];
                gradesFields = new TextField[numberOfClasses][numberOfGradingPeriods];

                for (int i = 0; i < numberOfClasses; i++) {
                    TextField classNameField = new TextField();
                    classNamesFields[i] = classNameField;
                    Label classListNumbers = new Label("Name of class");
                    rootPane.add(classNameField, 1, i);
                    rootPane.add(classListNumbers, 0, i);
                }

                gradeGrid = new float[numberOfClasses][numberOfGradingPeriods];
                rootPane.add(continueButton2, 1, numberOfClasses + 1);

            } catch (NumberFormatException e) {
                AlertBox.displayAlert1("Please enter a number");
                e.getMessage();
            }
        });

        //Second Continue Button
        continueButton2.setOnAction(event -> {
            classNames = new String[numberOfClasses];
            rootPane.getChildren().removeAll(rootPane.getChildren());
            for (int i = 0; i < classNames.length; i++) {
                classNames[i] = classNamesFields[i].getText();
                Label classNameInfo = new Label();
                classNameInfo.setText(classNames[i]);
                rootPane.add(classNameInfo, 0, i);


            }
            rootPane.add(gradeInputButton, 1, classNames.length);
            rootPane.add(calculateButton, 1, classNames.length + 1);
        });

        //Button to add grades
        gradeInputButton.setOnAction(event -> GradesWindow.showGradesWindow());

        calculateButton.setOnAction(event -> {

            rootPane.getChildren().removeAll(rootPane.getChildren());
            StringBuilder calculatedGPA = new StringBuilder();
            float GPAValue = 0;
            float totalGPA = 0;
            Label totalGPAText = new Label("Total GPA");

            for (int i = 0; i < numberOfClasses; i++) {
                GPAValue = 0;
                for (int j = 0; j < numberOfGradingPeriods; j++) {
                    GPAValue += gradeGrid[i][j];

                }
                GPAValue /= numberOfGradingPeriods;
                totalGPA += GPAValue;
                calculatedGPA.setLength(0);
                calculatedGPA.append(GPAValue);
                Label classNamesLabel = new Label();
                Label classGPA = new Label();
                classGPA.setText(calculatedGPA.toString());
                classNamesLabel.setText(classNames[i]);

                rootPane.add(classNamesLabel, 0, i);
                rootPane.add(classGPA, 1, i);
            }

            totalGPA /= numberOfClasses;

            calculatedGPA.setLength(0);
            calculatedGPA.append(totalGPA);

            totalGPAField.setText(calculatedGPA.toString());

            rootPane.add(totalGPAText, 0, numberOfClasses);
            rootPane.add(totalGPAField, 1, numberOfClasses);
        });

        primaryStage.setTitle("GPA Calculator");
        primaryStage.setScene(new Scene(rootPane, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
