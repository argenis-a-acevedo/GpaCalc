/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//class for the grades window
package GPACalc;

/**
 *
 * @author argen
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by argen on 3/4/2017. lalallaallala
 */
class GradesWindow {

    private static boolean displayAlert = true;
    private static final StringBuilder gradeChecker = new StringBuilder();

    static void showGradesWindow() {

        Stage window = new Stage();
        window.setMinWidth(250);
        GridPane gradesGridPane = new GridPane();
        gradesGridPane.setAlignment(Pos.CENTER);
        gradesGridPane.setHgap(10);
        gradesGridPane.setVgap(10);
        gradesGridPane.setPadding(new Insets(25, 25, 25, 25));

        //Grade Input Grid
        for (int i = 0; i < Main.numberOfClasses; i++) {
            for (int j = 0; j < Main.numberOfGradingPeriods; j++) {
                TextField gradeInputField = new TextField();
                Label classNameLabel = new Label(Main.classNames[i]);
                Main.gradesFields[i][j] = gradeInputField;
                gradesGridPane.add(gradeInputField, j + 1, i);
                gradesGridPane.add(classNameLabel, 0, i);
            }
        }
        Button closeButton = new Button("Confirm");

        gradesGridPane.add(closeButton, 0, Main.numberOfClasses);

        closeButton.setOnAction(event -> {

            displayAlert = true;
            try {
                for (int i = 0; i < Main.numberOfClasses; i++) {
                    for (int j = 0; j < Main.numberOfGradingPeriods; j++) {
                        float temporaryGradeHolder = Float.parseFloat(Main.gradesFields[i][j].getText());
                        if (temporaryGradeHolder > 4) {
                            throw new NumberFormatException();
                        }
                        Main.gradeGrid[i][j] = temporaryGradeHolder;
                    }
                }
                window.close();
            } catch (NumberFormatException e) {

                for (int i = 0; i < Main.numberOfClasses; i++) {
                    for (int j = 0; j < Main.numberOfGradingPeriods; j++) {
                        gradeChecker.setLength(0);
                        gradeChecker.append(Main.gradesFields[i][j].getText().toUpperCase());
                        switch (gradeChecker.toString()) {
                            case "A":
                                Main.gradeGrid[i][j] = 4;
                                break;

                            case "B":
                                Main.gradeGrid[i][j] = 3;
                                break;

                            case "C":
                                Main.gradeGrid[i][j] = 2;
                                break;

                            case "D":
                                Main.gradeGrid[i][j] = 1;
                                break;
                            case "F":
                                Main.gradeGrid[i][j] = 0;
                                break;

                            case "4":
                                Main.gradeGrid[i][j] = 4;
                                break;

                            case "3":
                                Main.gradeGrid[i][j] = 3;
                                break;

                            case "2":
                                Main.gradeGrid[i][j] = 2;
                                break;

                            case "1":
                                Main.gradeGrid[i][j] = 1;
                                break;

                            case "0":
                                Main.gradeGrid[i][j] = 0;
                                break;

                            default:
                                if (displayAlert) {
                                    AlertBox.displayAlert1("Only A,B,C,D,F or 0-4 is accepted");
                                    displayAlert = false;
                                }
                                break;
                        }
                    }
                }
                if (displayAlert) {
                    window.close();
                }
            }

        });

        Scene scene = new Scene(gradesGridPane);
        window.setScene(scene);
        window.showAndWait();
    }
}
