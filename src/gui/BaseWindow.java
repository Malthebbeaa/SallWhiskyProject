package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public abstract class BaseWindow {
    private GridPane pane;
    public BaseWindow(){
        this.pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(20));
    }

    public GridPane getPane(){
        return pane;
    }

    public abstract void initContent();
}
