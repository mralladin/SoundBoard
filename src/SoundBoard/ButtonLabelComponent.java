package SoundBoard;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ButtonLabelComponent extends VBox
{

	private ButtonComponent a;

	public ButtonLabelComponent(String text)
	{

		a = new ButtonComponent();
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(a, new Label(text));

	}

	public ButtonComponent getButton()
	{
		return this.a;
	}

}
