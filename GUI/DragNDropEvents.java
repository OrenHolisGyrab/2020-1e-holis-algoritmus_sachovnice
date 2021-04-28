package Sachovnice.GUI;

import javafx.scene.Node;
import javafx.scene.input.*;

public class DragNDropEvents {
	public static Node figure1Dragged;
	public static Node figure2Dragged;
	public static int figureNumber = 1;
	public static String figure1Id;
	public static String figure2Id;
	public static Node figure1Dropped;
	public static Node figure2Dropped;

	public static void setDragNDrop(Node el, String imageSource) {
		el.setOnDragDetected((MouseEvent event) -> {
			System.out.println(el != figure2Dropped);
			if (figureNumber == 3 && (el != figure1Dragged && el != figure2Dragged) && (el != figure1Dropped && el != figure2Dropped)) {
				InformationPanel.showMessage("Nemůžete položit více než 2 figury");
				return;
			} else if (el == figure1Dragged || el == figure2Dragged) {
				InformationPanel.showMessage("Stejnou figuru nemůžete položit 2x.");
				return;
			}

			Dragboard db = el.startDragAndDrop(TransferMode.ANY);

			String[] figureName = imageSource.split("-");
			String color = figureName[1].equals("black") ? "černé" : "bílé";
			InformationPanel.showMessage("Je pohybováno figurou " + InformationPanel.translations(figureName[0]) + ", která je " + color + " barvy");
			InformationPanel.hint(figureName[0]);

			if (el != figure1Dropped && el != figure2Dropped) {
				if (figureNumber == 1) {
					figure1Dragged = el;
				} else if (figureNumber == 2) {
					figure2Dragged = el;
				}
			} else if (el == figure1Dropped){
				figure1Dragged.setDisable(false);
			} else {
				figure2Dragged.setDisable(false);
			}
			ClipboardContent content = new ClipboardContent();
			content.putString(imageSource);
			db.setContent(content);
		});

		el.setOnMouseDragged((MouseEvent event) -> event.setDragDetect(true));
	}

	public static void setDropZone(Node el, String type, String figureName) {
		el.setOnDragOver(event -> {
			if (event.getGestureSource() != el && event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				if (figureNumber == 3 && el != figure1Dragged && el != figure2Dragged) {
					figure1Dragged.setDisable(false);
					figure2Dragged.setDisable(false);
				}
			}

			event.consume();
		});

		el.setOnDragDropped((DragEvent event) -> {
			Dragboard db = event.getDragboard();
			if (db.hasString()) {
				if (type.equals("figureBoard")) {
					if (figureName.equals(figure1Id)) {
						ChessBoard.removeFigureFromButton(figure1Dropped, 1);
						reset1();
					} else if (figureName.equals(figure2Id)) {
						ChessBoard.removeFigureFromButton(figure2Dropped, 2);
						reset2();
					}

					ChessBoard.clearChessBoard();
				} else if (type.equals("chessBoard")) {
					if (figureNumber == 1) {
						InformationPanel.showMessage("");
						InformationPanel.showHint("");
						figure1Dropped = el;
						figure1Id = db.getString();
					} else if (figureNumber == 2 && !figure1Id.equals(db.getString())) {
						InformationPanel.showHint("");
						figure2Dropped = el;
						figure2Id = db.getString();
					} else if (figureNumber == 3) {
						return;
					} else if (figure1Id.equals(db.getString()) || figure2Id.equals(db.getString())) {
						return;
					}

					figureNumber++;

					ChessBoard.setFigureAtButton(el, db.getString());
				}

				event.setDropCompleted(true);
			} else {
				event.setDropCompleted(false);
			}
			event.consume();
		});
	}

	public static void reset1() {
		if (figure1Dragged != null)
			figure1Dragged.setDisable(false);
		figure1Dragged = null;
		figure1Dropped = null;
		figureNumber = 1;
	}

	public static void reset2() {
		if (figure2Dragged != null)
			figure2Dragged.setDisable(false);
		figure2Dragged = null;
		figure2Dropped = null;
		figureNumber = 1;
	}

	public static void reset() {
		reset1();
		reset2();
	}
}
