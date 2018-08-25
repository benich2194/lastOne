package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import utils.NameToWindow;

public abstract class WindowManager {
	private static FlowPane contentPane;
	private static Parent currentParent, previousParent;

	public static void openWindow(String ui) {
		try {
			if (currentParent != null) {
				previousParent = currentParent;
			}

			currentParent = FXMLLoader.load(WindowManager.class.getResource(ui + ".fxml"));
			contentPane.getChildren().removeAll();
			contentPane.getChildren().setAll(currentParent);
		} catch (Exception ex) {
			System.out.println("Catch caught");
			ex.printStackTrace();
		}
	}

	public static void openWindow(NameToWindow n2w) {
		System.out.println("Attempting to open " + n2w + ".fxml");
		try {
			if (currentParent != null) {
				previousParent = currentParent;
			}

			currentParent = FXMLLoader.load(WindowManager.class.getResource(n2w + ".fxml"));
			contentPane.getChildren().removeAll();
			contentPane.getChildren().setAll(currentParent);
		} catch (Exception ex) {
			System.out.println("Catch caught");
			ex.printStackTrace();
		}
	}

	public static void setContentPane(FlowPane fp) {
		contentPane = fp;
	}

	public static void goBack() {
		if (previousParent != null && previousParent != currentParent) {
			contentPane.getChildren().removeAll();
			contentPane.getChildren().setAll(previousParent);
			Parent tmp = previousParent;
			previousParent = currentParent;
			currentParent = tmp;
		}
	}
}
