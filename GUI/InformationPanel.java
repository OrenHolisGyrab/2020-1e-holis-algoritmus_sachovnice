package Sachovnice.GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class InformationPanel {

	public static Text msgWindow = new Text();
	public static Text hintWindow = new Text();

	public static GridPane createPanel() {
		GridPane pane = new GridPane();

		Button clearBoardBtn = new Button("Vyčistit šachovnici");

		clearBoardBtn.setOnAction(InformationPanel::clearBoard);

		pane.add(clearBoardBtn, 0, 0);
		pane.add(msgWindow, 0, 1);
		pane.add(hintWindow, 0, 2);
		msgWindow.setStyle("-fx-font-size: 14px;");
		hintWindow.setStyle("-fx-font-size: 14px;");

		return pane;
	}

	private static void clearBoard(ActionEvent actionEvent) {
		DragNDropEvents.reset();
		ChessBoard.reset(false);
		ImageHintPanel.clearImage();
		hintWindow.setText("");
		msgWindow.setText("");
	}

	public static void showMessage(String text) {
		msgWindow.setText(text);
	}

	public static void showHint(String hint) {
		hintWindow.setText(hint);
	}

	public static void hint(String figureName) {
		ImageHintPanel.showImage(figureName);
		switch(figureName) {
			case "horse":
				showHint(
					"Nápověda: Kůň je figurka, která jde o dvě pole a poté o jedno vlevo."
				);
				break;
			case "pawn":
				showHint(
					"Nápověda: Pěšec je figura, která jde v prvním tahu \n o dvě pole vpřed a poté o jedno." +
					" Pokud bude stát \n šikmo k nepřátelské figurce tak se může při vyhození \n pohnout šikmo o jedno pole."
				);
				break;
			case "shooter":
				showHint(
					"Nápověda: Střelec je figura, která chodí šikmo o libovolný\n počet polí." +
					" Pokud dáte dva střelce na bílou a černou tak se nikdy nevyhodí."
				);
				break;
			case "queen":
				showHint(
					"Nápověda: Dáma je figura, která chodí o libovolný počet polí\n šíkmo, po sloupcích nebo i po řádcích."
				);
				break;
			case "king":
				showHint(
					"Nápověda: Král je figura, která chodí o jedno pole v sloupci\n nebo řádce."
				);
				break;
			case "tower":
				showHint(
					"Nápověda: Věž je figura, která chodí o libovolný počet polí\n v řádku nebo sloupci."
				);
				break;
		}
	}

	public static String translations(String text) {
		String figure;
		switch(text) {
			case "horse":
				figure = "kůň";
				break;
			case "pawn":
				figure = "pěšec";
				break;
			case "shooter":
				figure = "střelec";
				break;
			case "queen":
				figure = "dáma";
				break;
			case "king":
				figure = "král";
				break;
			case "tower":
				figure = "věž";
				break;
			default:
				figure = "Chyba Omegalul";
		}
		return figure;
	}
}
