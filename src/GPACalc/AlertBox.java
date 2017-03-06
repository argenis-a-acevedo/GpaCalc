/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//alert box class
package GPACalc;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

class AlertBox {

    public static void displayAlert1(String text) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("INVALID INPUT");
        window.setMinWidth(250);

        Label alertText = new Label();
        alertText.setText(text);
        Button closeButton = new Button("Close");

        VBox verticalSort = new VBox(10);
        verticalSort.getChildren().addAll(alertText, closeButton);
        verticalSort.setAlignment(Pos.CENTER);

        closeButton.setOnAction(e -> window.close());

        Scene scene = new Scene(verticalSort);
        window.setScene(scene);
        window.showAndWait();

    }

}
