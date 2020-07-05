package SoundBoard;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.sun.javafx.application.HostServicesDelegate;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class View
{

	private VBox vbox;
	private Menu menuFile;

	private MenuItem addmusic;
	private MenuItem getData;
	private Presenter presenter;
	private Stage dataedit;
	private ListView<HBoxCell> dataList;
	private GridPane buttonPane;
	private Button left;
	private MenuItem about;
	private MenuItem einstellung;
	private Button right;
	private Slider volume;
	private ImageView rand;
	private Slider nameLength;
	private Slider balance;
	private Label dataname;
	private ComboBox<Integer> AnzahlZeilen;
	private ComboBox<String> hotkey;
	private Button random ;
	private ComboBox<Integer> AnzahlButtons ;
	public ComboBox<String> getHotKey(){
		return this.hotkey;
	}
	public ImageView getImage(){
		return this.rand;
	}
	public ComboBox<Integer> getButtonZeile() {
		return AnzahlZeilen;
	}

	public ComboBox<Integer> getAnzahlButtons() {
		return AnzahlButtons;
	}

	private CheckBox autoplay;
	private CheckBox shuffle;
	public Slider getNamelength(){
		return this.nameLength;
	}
	public CheckBox getAutoPlay() {
		return autoplay;
	}
	public CheckBox getShuffle() {
		return shuffle;
	}

	private Label playval;
	private HostServicesDelegate hostServices;

	public Label getPlayVal()
	{
		return this.playval;
	}

	public Slider getVolume()
	{
		return volume;
	}

	public Slider getBalance()
	{
		return balance;
	}

	public GridPane getButtonPane()
	{
		return buttonPane;
	}

	public ListView<HBoxCell> getDataList()
	{
		return dataList;
	}

	public View(HostServicesDelegate hostServices)
	{
		this.hostServices = hostServices;
		int size = 65;
		// RESSOURCES
		Image aboutu = new Image(getClass().getResourceAsStream("../assets/business.jpg"));
		ImageView ueber = new ImageView(aboutu);
		ueber.setFitHeight(27);
		ueber.setFitWidth(28);

		Image infof = new Image(getClass().getResourceAsStream("../assets/info.png"));
		ImageView help = new ImageView(infof);
		help.setFitHeight(27);
		help.setFitWidth(28);
		
		
		ImageView configur = new ImageView(new Image(getClass().getResourceAsStream("../assets/config.png")));
		configur.setFitHeight(27);
		configur.setFitWidth(28);
		

		Image imageDecline = new Image(getClass().getResourceAsStream("../assets/music.jpg"));
		ImageView imageview = new ImageView(imageDecline);
		imageview.setFitHeight(27);
		imageview.setFitWidth(28);
		Image imageDecline3 = new Image(getClass().getResourceAsStream("../assets/delete_all.jpg"));
		ImageView imageview3 = new ImageView(imageDecline3);
		imageview3.setFitHeight(53);
		imageview3.setFitWidth(53);
		Image exit1 = new Image(getClass().getResourceAsStream("../assets/exit.png"));
		ImageView exitbu = new ImageView(exit1);
		exitbu.setFitHeight(53);
		exitbu.setFitWidth(53);
		Image imageDecline2 = new Image(getClass().getResourceAsStream("../assets/data2.png"));
		ImageView imageview2 = new ImageView(imageDecline2);
		imageview2.setFitHeight(27);
		imageview2.setFitWidth(28);
		Image abortu = new Image(getClass().getResourceAsStream("../assets/abort.png"));
		ImageView abortbu = new ImageView(abortu);
		abortbu.setFitWidth(80);
		Image confirmb = new Image(getClass().getResourceAsStream("../assets/confirm.png"));
		ImageView confirmbu = new ImageView(confirmb);
		confirmbu.setFitWidth(80);

		Image mutebut = new Image(getClass().getResourceAsStream("../assets/mute.jpg"));
		ImageView muteico = new ImageView(mutebut);
		muteico.setFitHeight(size);
		muteico.setFitWidth(size);

		abortbu.setPreserveRatio(true);
		abortbu.setSmooth(true);
		abortbu.setCache(true);
		confirmbu.setPreserveRatio(true);
		confirmbu.setSmooth(true);
		confirmbu.setCache(true);
		
		
		 rand = new ImageView(new Image(getClass().getResourceAsStream("../assets/random.png")));
		rand.setFitWidth(size);
		rand.setFitHeight(size);
		
		
		// ARROWS
		Image awl = new Image(getClass().getResourceAsStream("../assets/leftw.png"));
		ImageView leftar = new ImageView(awl);
		leftar.setFitWidth(size);
		leftar.setFitHeight(size);
		Image awr = new Image(getClass().getResourceAsStream("../assets/rightw.png"));
		ImageView rightar = new ImageView(awr);
		rightar.setFitWidth(size);
		rightar.setFitHeight(size);
		Image abl = new Image(getClass().getResourceAsStream("../assets/leftb.png"));
		ImageView leftar2 = new ImageView(abl);
		leftar2.setFitWidth(size);
		leftar2.setFitHeight(size);
		Image abr = new Image(getClass().getResourceAsStream("../assets/rightb.png"));
		ImageView rightar2 = new ImageView(abr);
		rightar2.setFitWidth(size);
		rightar2.setFitHeight(size);
		// ARROWS
		// RESSOURCES

		vbox = new VBox();
		buttonPane = new GridPane();
		buttonPane.setAlignment(Pos.TOP_LEFT);
		

		buttonPane.setHgap(25);
		buttonPane.setVgap(12);
		buttonPane.setMinHeight(0);
		buttonPane.setPrefHeight(65);
		buttonPane.setMaxHeight(900);
		buttonPane.setMinWidth(0);
		buttonPane.setPrefWidth(65);
		buttonPane.setMaxWidth(1800);
		//160= 1 Reihe
		//255=
		//95
		// MENU TOP
		MenuBar menuBar = new MenuBar();
		HBox optmenu = new HBox();
		menuFile = new Menu("Optionen");
		Menu menuFile2 = new Menu("Hilfe");
		addmusic = new MenuItem("Music hinzuf.", imageview);
		getData = new MenuItem("Edit Data", imageview2);
		 einstellung = new MenuItem("Einstellungen", configur);
		menuFile.getItems().addAll(addmusic, getData,einstellung);
		menuBar.getMenus().addAll(menuFile, menuFile2);

		 about = new MenuItem("About", ueber);
		menuFile2.getItems().addAll(about);
		//--------------------------->>>>>>>>>>>
		Stage configpane= new Stage();
		GridPane configgrid= new GridPane();
		configpane.initModality(Modality.APPLICATION_MODAL);
		Scene configscene= new Scene(configgrid,370,320);
		configpane.setResizable(false);
		configpane.setScene(configscene);
		configpane.getIcons().add(new Image(getClass().getResourceAsStream("../assets/config.png")));
		configpane.setTitle("Einstellungen");
		autoplay= new CheckBox("Autoplay next Song");
		shuffle= new CheckBox("Zufalls wiedergabe?");
		 AnzahlButtons = new ComboBox<>();
		 AnzahlZeilen = new ComboBox<>();
		configgrid.add(autoplay, 1, 1);
		configgrid.add(shuffle, 1, 2);
		
		nameLength = new Slider(10,100,48);
		nameLength.setMajorTickUnit(10);
		nameLength.setShowTickLabels(true);
		nameLength.setSnapToTicks(true);
		nameLength.setShowTickMarks(true);
		
		nameLength.setMinorTickCount(0);
		
		
		nameLength.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				System.out.println("ASD");
				presenter.setData();
				nameLength.setTooltip(new Tooltip("L�nge der Titelnamen: "+Presenter.getNameLength()));
				
				
			}
		});
		hotkey= new ComboBox<String>();
		
		configgrid.add(nameLength, 0, 5,5,1);
		configgrid.add(new Label("Wie viele Buttons pro Zeile"), 1, 3);
		configgrid.add(new Label("Wie viele Zeilen"), 1, 4);
		configgrid.setPadding(new Insets(12));
		configgrid.setVgap(6);
		configgrid.setHgap(6);
		configgrid.setAlignment(Pos.CENTER);
		
		shuffle.setDisable(true);
		configgrid.add(AnzahlButtons, 2, 3);
		configgrid.add(AnzahlZeilen, 2, 4);
		configgrid.add(new Label("HotKey f�r AutoRandom"), 1, 6);
		configgrid.add(hotkey, 2, 6);
		configgrid.add(new Text("Hinweis!!!\nF�r Lautst�rke HotKey Taste \"Num\" zum aktivieren\nund mit Taste \"Bild auf\" und \"Bild ab\" Lautst�rke \nver�ndern."), 1, 7,2,1);
		
		autoplay.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				shuffle.setDisable(false);
				if(!(autoplay.isSelected()))
					{
					shuffle.setDisable(true);
					shuffle.setSelected(false);
					}
	
			}
		});
		
		
		configpane.setOnHiding(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {

				presenter.setData();
				presenter.saveData();
				presenter.updatePane();
		
				
			}
		});
		
		
		
		//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		
		

		// TUTORIAL--------->>>>>>>>>>>>>>>>>>>>>>
		VBox tutpane = new VBox();
		tutpane.setPadding(new Insets(10));
		tutpane.setSpacing(10);
		tutpane.setAlignment(Pos.CENTER);
		Stage tut = new Stage();
		Scene sctut = new Scene(tutpane, 500, 350);
		tut.setResizable(false);
		tut.initModality(Modality.APPLICATION_MODAL);
		int width = 80;
		int height = 80;
		tut.setScene(sctut);
		tut.getIcons().add(new Image(getClass().getResourceAsStream("../assets/info.png")));

		about.setOnAction((event) -> {
			tut.showAndWait();
		});
		ImageView info1 = new ImageView(new Image(getClass().getResourceAsStream("../assets/mail.png")));
		info1.setFitHeight(height);
		info1.setFitWidth(width);
		Hyperlink link = new Hyperlink("https://www.philipp-lippold.de");

		link.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{

				hostServices.showDocument("https://www.philipp-lippold.de");

			}
		});
		HBox infobox = new HBox();
		Label a = new Label("Wer ist ");
		Label b = new Label(" ?");
		a.setFont(Font.font(null, FontWeight.BOLD, 24));
		b.setFont(Font.font(null, FontWeight.BOLD, 24));
		tut.setTitle("About");
		infobox.getChildren().addAll(a, link, b);
		link.setText("Philipp Lippold");
		link.setFont(Font.font(null, FontWeight.BOLD, 24));
		Label info = new Label("Diese Programm wurde von Philipp Lippold erstellt.");
		info.setFont(Font.font(null, FontWeight.BOLD, 18));
		Label info2 = new Label(
				"Bei fragen einfach eine Email an info@philipp-lippold.de oder \nauf den Button klicken.");
		Button email = new Button();
		email.setGraphic(info1);
		tutpane.getChildren().addAll(info, infobox, info2, email);
		email.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				if (Desktop.isDesktopSupported())
				{
					Desktop desktop = Desktop.getDesktop();
					if (desktop.isSupported(Desktop.Action.MAIL))
					{
						try
						{
							desktop.mail();
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try
						{
							desktop.mail(new URI("mailto:info@philipp-lippold.de"));
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (URISyntaxException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} // alternately,

					}
				}

			}
		});

		// --------------------<<<<<<<<<<<<<<<<<<<<<<

		//

		//
		volume = new Slider(0, 100, 25);
		volume.setMinorTickCount(5);
		volume.setMajorTickUnit(25);
		volume.setShowTickLabels(true);
		volume.setShowTickMarks(true);
		volume.setPadding(new Insets(10));
		volume.setTooltip(new Tooltip("Lautst�rkeregler"));

		balance = new Slider(-1, 1, 0);

		balance.setTooltip(new Tooltip("Balanceregler"));
		balance.setMinorTickCount(1);
		balance.setMajorTickUnit(1);
		balance.setShowTickLabels(true);
		balance.setShowTickMarks(true);
		balance.setPadding(new Insets(10));
		HBox labelbox = new HBox(5);
		dataname = new Label();
		playval = new Label("");
		playval.setFont(Font.font(null, FontWeight.BOLD, 16));
		dataname.setFont(Font.font(null, FontWeight.BOLD, 16));

		labelbox.getChildren().addAll(playval, dataname);
		labelbox.setPadding(new Insets(15, 0, 0, 18));

		vbox.getChildren().addAll(menuBar, buttonPane, labelbox, balance, volume, optmenu);
		vbox.setSpacing(12);
		vbox.setAlignment(Pos.CENTER);

		optmenu.setSpacing(20);
		optmenu.setAlignment(Pos.CENTER);
		left = new Button();
		right = new Button();
		left.setGraphic(leftar);
		right.setGraphic(rightar);
		Button stop = new Button();
		left.setDisable(true);
		stop.setGraphic(muteico);
		optmenu.setPadding(new Insets(0, 0, 20, 0));
		 random = new Button();
		random.setGraphic(rand);
		System.out.println(rand.getFitHeight());
		random.setOnMouseEntered((MouseEvent event)-> {
			random.setStyle("-fx-background-color: red;"+"-fx-background-radius: 900em ; " +
					"-fx-accent: #0093ff;"+ 
					
					"-fx-focus-color: red ; "+
					"-fx-faint-focus-color: red ; " + 
					"-fx-min-width: 65px; " + 
					"-fx-max-width: 65px; " + 
					"-fx-min-height: 65px; "+
					"-fx-max-height: 65px; ");
			
					
		}); 
		random.setOnMouseExited((MouseEvent event)-> {
			random.setStyle("-fx-background-radius: 900em ; " +
					"-fx-accent: #0093ff;"+ 
					
					"-fx-focus-color: blue ; "+
					"-fx-faint-focus-color: blue ; " + 
					"-fx-min-width: 65px; " + 
					"-fx-max-width: 65px; " + 
					"-fx-min-height: 65px; "+
					"-fx-max-height: 65px; ");
		}); 

		random.setStyle(
		"-fx-background-radius: 900em ; " +
		"-fx-accent: #0093ff;"+ 
		
		"-fx-focus-color: blue ; "+
		"-fx-faint-focus-color: blue ; " + 
		"-fx-min-width: 65px; " + 
		"-fx-max-width: 65px; " + 
		"-fx-min-height: 65px; "+
		"-fx-max-height: 65px; "
				
				
				);
		
		
		optmenu.getChildren().addAll(left, stop, random,right);
		// MENU TOP

		dataList = new ListView<>();

		VBox grp = new VBox();
		HBox options = new HBox();
		Scene scene = new Scene(grp, 470, 800);

		dataedit = new Stage();
		dataedit.getIcons().add(new Image(getClass().getResourceAsStream("../assets/table.png")));
		dataedit.setTitle("Musik Daten");
		dataedit.setWidth(470);
		dataedit.setResizable(false);
		dataedit.setScene(scene);
		dataedit.initModality(Modality.APPLICATION_MODAL);
		dataList.setMinSize(0, 700);
		dataList.setMaxSize(470, 720);
		dataList.setPrefSize(480, 750);
		// CONFIRM
		VBox confbox = new VBox();
		HBox confhbox = new HBox();
		Label conftext = new Label("Sind sie wirklich sicher, dass sie alle Daten l�schen wollen?");
		confbox.setAlignment(Pos.CENTER);
		confbox.setSpacing(45);
		conftext.setFont(Font.font(null, FontWeight.BOLD, 18));
		conftext.setTextFill(Color.web("#ff0000"));
		confhbox.setAlignment(Pos.CENTER);
		confhbox.setSpacing(60);

		Button confimdel = new Button();
		Button abort = new Button();
		confimdel.setGraphic(confirmbu);
		abort.setGraphic(abortbu);
		abort.setMaxWidth(150);
		confimdel.setMaxWidth(150);
		Scene sceneconf = new Scene(confbox, 550, 200);
		confbox.setStyle("-fx-background-color:#000000 ; ");
		confbox.getChildren().addAll(conftext, confhbox);
		confhbox.getChildren().addAll(confimdel, abort);
		Stage confirm = new Stage();
		confirm.getIcons().add(new Image(getClass().getResourceAsStream("../assets/delete.png")));
		confirm.setTitle("Daten l�schen?");
		confirm.initModality(Modality.APPLICATION_MODAL);
		confirm.initStyle(StageStyle.TRANSPARENT);

		confirm.setScene(sceneconf);

		// CONFIRM

		Button clearall = new Button();
		Button exit = new Button();
		options.setSpacing(250);
		options.setAlignment(Pos.CENTER);
		exit.setGraphic(exitbu);
		clearall.setGraphic(imageview3);
		options.setPadding(new Insets(15));
		options.getChildren().addAll(clearall, exit);

		grp.getChildren().addAll(dataList, options);
		
		
		einstellung.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				configpane.showAndWait();

			}
		});
		

		dataedit.setOnHiding(new EventHandler<WindowEvent>()
		{

			@Override
			public void handle(WindowEvent event)
			{
				presenter.updatePane();
			}
		});

		exit.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				dataedit.hide();

			}
		});

		abort.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				// TODO Auto-generated method stub
				confirm.close();

			}
		});
		confimdel.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				presenter.delAll();
				dataedit.close();
				confirm.close();

			}
		});

		clearall.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{

				Platform.runLater(new Runnable()
				{

					@Override
					public void run()
					{
						abort.requestFocus();

					}
				});
				confirm.showAndWait();

			}
		});

		getData.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				presenter.updateData();

				dataedit.showAndWait();

				presenter.saveData();
			}
		});

		left.setOnMouseExited(new EventHandler<Event>()
		{

			@Override
			public void handle(Event event)
			{
				left.setGraphic(leftar);
			}
		});
		left.setOnMouseEntered(new EventHandler<Event>()
		{

			@Override
			public void handle(Event event)
			{
				left.setGraphic(leftar2);
			}
		});

		right.setOnMouseExited(new EventHandler<Event>()
		{

			@Override
			public void handle(Event event)
			{
				right.setGraphic(rightar);
			}
		});
		right.setOnMouseEntered(new EventHandler<Event>()
		{

			@Override
			public void handle(Event event)
			{
				right.setGraphic(rightar2);
			}
		});

		stop.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				presenter.stopall();

			}
		});

		addmusic.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				presenter.choose();
			}
		});
		

	}

	public Button getRandom(){
		return this.random;
	}

	public Button getLeft()
	{
		return left;
	}

	public Button getRight()
	{
		return right;
	}

	public Label getDataname()
	{
		return dataname;
	}

	public Parent getUI()
	{

		return this.vbox;
	}

	public MenuItem getMenuItem()
	{
		return addmusic;
	}

	public void setPresenter(Presenter presenter2)
	{
		this.presenter = presenter2;
	}

	public void setDisableItems() {
		einstellung.setDisable(true);
		addmusic.setDisable(true);
		getData.setDisable(true);
		about.setDisable(true);
		
	}

	public void setActiveItems() {
		einstellung.setDisable(false);
		addmusic.setDisable(false);
		getData.setDisable(false);
		about.setDisable(false);
		
	}

}
