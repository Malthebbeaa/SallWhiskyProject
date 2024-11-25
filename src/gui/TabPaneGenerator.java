package gui;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class TabPaneGenerator {
    private TabPane tabPane;
    private Tab selectedTab;
    public TabPaneGenerator() {
    }

    public TabPane generateTabPane(List<String> tabNames, List<GridPane> gridPanes){
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setTabMinWidth(60);
        tabPane.setTabMinHeight(30);
        for (int i = 0; i < tabNames.size(); i++) {
            Tab tab = new Tab();
            tab.setText(tabNames.get(i));
            tab.setContent(gridPanes.get(i));
            tabPane.getTabs().add(tab);
        }

        return tabPane;
    }

    public TabPane getTabPane() {
        return tabPane;
    }
}
