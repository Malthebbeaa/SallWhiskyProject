package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;

public class PaneCreator extends GridPane {

    public PaneCreator(){
        this.setBorder(Border.stroke(Paint.valueOf("Black")));
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(20, 50, 50, 10));
    }
}
