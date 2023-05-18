package com.hotelijerstvo.hoteli.gui.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProgressForm {
    private final Stage dialogStage = new Stage();
    final ProgressBar progressBar = new ProgressBar(0);
    final ProgressIndicator progressIndicator = new ProgressIndicator(0);
    public ProgressForm() {
        Group root = new Group();
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.setTitle("Progress Controls");

        final Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(50);



        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                progressBar.setProgress(new_val.doubleValue()/50);
                progressIndicator.setProgress(new_val.doubleValue()/50);
            }
        });

        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(slider, progressBar, progressIndicator);
        scene.setRoot(hb);
    }

    public void activateProgressBar(final Task<?> task)  {
        progressBar.progressProperty().bind(task.progressProperty());
        progressIndicator.progressProperty().bind(task.progressProperty());
        dialogStage.show();
    }

    public Stage getDialogStage() {
        return dialogStage;
    }
}
