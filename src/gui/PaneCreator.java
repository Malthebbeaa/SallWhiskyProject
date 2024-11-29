package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public void setGraphics(Button button, String path){
        Image image = new Image(getClass().getResource("/ressources/"+path).toExternalForm());
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        button.setGraphic(imageView);
    }
}
