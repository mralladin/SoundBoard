package SoundBoard;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;

public class HBoxCell extends HBox
{
	Label label = new Label();
	Button button = new Button();

	HBoxCell(File file)
	{
		super();
		Image imageDecline = new Image(getClass().getResourceAsStream("../assets/trash.png"));
		ImageView imageview = new ImageView(imageDecline);
		imageview.setFitHeight(27);
		imageview.setFitWidth(28);
		label.setFont(new Font(18));
		label.setText(file.getName().length() > 37 ? file.getName().substring(0, 37) : file.getName());
		label.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(label, Priority.ALWAYS);

		button.setGraphic(imageview);

		this.getChildren().addAll(label, button);
	}

}