package SoundBoard;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonComponent extends Button
{

	private Image imageDecline2;
	private Image imageDecline;
	private int value = 0;

	public ButtonComponent()
	{
		imageDecline = new Image(getClass().getResourceAsStream("../assets/button1.png"));

		imageDecline2 = new Image(getClass().getResourceAsStream("../assets/button2.png"));

		this.setMinSize(imageDecline.getWidth() - 5, imageDecline.getHeight() - 5);
		// String image =
		// ButtonComponent.class.getResource("button1.png").toExternalForm();
		// this.setStyle("-fx-background-image: url('" + image + "'); " +
		// "-fx-background-position: center center; " +
		// "-fx-background-repeat: stretch;");

		this.setOnMousePressed(new EventHandler<Event>()
		{

			@Override
			public void handle(Event event)
			{
				onClick();

			}
		});
		this.setOnMouseReleased(new EventHandler<Event>()
		{

			@Override
			public void handle(Event event)
			{

				onRelease();

			}
		});

		this.setOnTouchPressed(new EventHandler<Event>()
		{

			@Override
			public void handle(Event event)
			{
				onClick();

			}
		});

		this.setOnTouchReleased(new EventHandler<Event>()
		{

			@Override
			public void handle(Event event)
			{
				onRelease();
			}
		});

		String color = String.valueOf((int) (Math.random() * 256)) + "," + String.valueOf((int) (Math.random() * 256))
				+ "," + String.valueOf((int) (Math.random() * 256));

		this.setStyle(

				"-fx-background-radius: 999px ; " + "-fx-focus-color: transparent ; "
						+ "-fx-faint-focus-color: transparent ; " + "-fx-max-width: 50px; " + "-fx-max-height: 50px; "
						+ "-fx-background-color: rgb(" + color + ") ;"
		// "-fx-background-insets: 0px; " +
		// "-fx-padding: 0px;"
		);
		this.setGraphic(new ImageView(imageDecline));

	}

	public void onClick()
	{
		this.setGraphic(new ImageView(imageDecline2));
	}

	public void onRelease()
	{
		this.setGraphic(new ImageView(imageDecline));

	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

}
