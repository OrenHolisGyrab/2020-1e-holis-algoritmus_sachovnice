package Sachovnice.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;

public class ImageHintPanel {
	public static ImageView image = new ImageView();

	/**
	 * Creates panel for showing image hint
	 *
	 * @return Image panel
	 */
	public static GridPane createPanel() {
		GridPane pane = new GridPane();

		image.setFitHeight(300);
		image.setFitWidth(300);
		image.setPreserveRatio(true);
		pane.add(image, 0, 0);

		return pane;
	}

	/**
	 * Shows image how figure moves on chessboard
	 *
	 * @param figureName - Name of figure
	 */
	public static void showImage(String figureName) {
		try {
			FileInputStream inputStream = new FileInputStream("Sachovnice/images/hint-pictures/" + figureName + "-plays.png");
			ImageHintPanel.image.setImage(new Image(inputStream));
		} catch (Exception ignored) {}
	}

	public static void clearImage() {
		ImageHintPanel.image.setImage(null);
	}

}
