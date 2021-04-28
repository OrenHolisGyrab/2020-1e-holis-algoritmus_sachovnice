package Sachovnice.GUI;

import Sachovnice.Sachovnice;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.IOException;

public class FiguresRender {
    private static final GridPane pane = new GridPane();

    public static GridPane createFiguresBar() {
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < Sachovnice.namesOfFigures.length; x++) {
                try {
                    String figureName = Sachovnice.namesOfFigures[x] + ((x + y) % 2 == 0 ? "-black" : "-white");
                    ImageView figure = createFigure(figureName, 45, 45);
                    pane.add(figure, x, y);

                    DragNDropEvents.setDragNDrop(figure, figureName);
                    DragNDropEvents.setDropZone(figure, "figureBoard", figureName);
                } catch (IOException ignored) {}
            }
        }
        return pane;
    }

    public static ImageView createFigure(String figureName, int height, int width) throws IOException {
        FileInputStream inputStream = new FileInputStream("D://javaXD/src/Sachovnice/images/figures/" + figureName + ".png");;
        ImageView imageView = new ImageView(new Image(inputStream));
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setPreserveRatio(true);
        return imageView;
    }
}