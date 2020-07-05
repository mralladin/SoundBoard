package SoundBoard;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import SoundBoard.View;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tooltip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Presenter  implements NativeKeyListener
{

	private View view;
	private FileChooser fileChooser;
	private Model model;
	private MediaPlayer mediaPlayer;
	private String temp;
	private static int page = 0;
	private static int Anzahlpage = 0;
	private int pageCount = 1;
	private Stage stage;
	private static int click = 0;
	private static int Value1;
	private static int Value2;
	private static int tempValue=Value2+1;
	private static int nameLength;
	private AtomicInteger playState = new AtomicInteger(1);
	private static int val=1;
	private boolean volumehotkey=false;

	public Presenter(){
		
	}
	
	public Presenter(View view, Model model,Stage stage)
	{

		this.stage=stage;
		this.model = model;
		this.view = view;
		stage.setOnShown(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				init();
				
			}
		});
		
	}

	public void init()
	{
	
		if(val==1){
			  try {
		            GlobalScreen.registerNativeHook();
		            
		        }
		        catch (NativeHookException ex) {
		         
		        }
			   GlobalScreen.addNativeKeyListener(this);
			 val++;
			}
			
		
		//INIT WERTE LAYOUT
		
		view.getAnzahlButtons().setItems(model.getData());
		view.getButtonZeile().setItems(model.getData());

		
		//
		
		
		view.getRight().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{

				System.out.println(view.getAnzahlButtons().getSelectionModel().getSelectedItem().intValue()+"    "+view.getAnzahlButtons().getSelectionModel().getSelectedItem().intValue());
				
				
				pageCount++;
				click++;
				view.getLeft().setDisable(false);
				updatePane();
				if (Anzahlpage == pageCount)
				{
					view.getRight().setDisable(true);
				}

			}
		});

		view.getLeft().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{

				pageCount--;
				click--;
				view.getRight().setDisable(false);
				updatePane();

				if (pageCount == 1)
				{
					view.getLeft().setDisable(true);
				}

			}
		});
		

		updatePane();
	}

	public static int getNameLength() {
		return nameLength;
	}

	public void updatePane()
	{
		
		view.getHotKey().setItems(model.getKeydo());
		
		view.getHotKey().getSelectionModel().select(model.getLayoutData()[3]);
		nameLength=(int)view.getNamelength().getValue();
		Value1=model.getLayoutData()[0];//ZEILENWERT
		Value2=model.getLayoutData()[1];
		view.getButtonZeile().getSelectionModel().select(new Integer(model.getLayoutData()[0]));
		view.getAnzahlButtons().getSelectionModel().select(new Integer(model.getLayoutData()[1]));
		view.getNamelength().setValue(model.getLayoutData()[2]);
		view.getNamelength().setTooltip(new Tooltip("Länge der Titelnamen: "+model.getLayoutData()[2]));
		stage.setHeight(355+(Value1*124));
		view.getButtonPane().setPrefHeight(65+(110*Value1));
		stage.setWidth(450+(Value2*30));
		System.out.println(stage.getWidth());
		view.getButtonPane().setPrefWidth(stage.getWidth());
		tempValue=Value2+1;
		Anzahlpage = 0;
		int AnzahlButtons=Value1*Value2;
		view.getButtonPane().getChildren().clear();
		int z = 1;
		int t = 1;
		for (int j = 0; j < model.getFiles().size(); j++)
		{
			if (j % AnzahlButtons == 0)
			{
				Anzahlpage++;
			}

		}
		if (Anzahlpage <= 1)
		{
			view.getRight().setDisable(true);
			view.getLeft().setDisable(true);
		} else
		{
			view.getRight().setDisable(false);
		}

		if (pageCount == 1)
		{
			view.getLeft().setDisable(true);
		}
		if (Anzahlpage == pageCount)
		{
			view.getRight().setDisable(true);
		}

		int val = AnzahlButtons * pageCount;
		if (model.getFiles().size() < val)
		{
			val = model.getFiles().size();
		}

		for (page = (AnzahlButtons * click); page < val; page++)
		{

			String label2 = model.getFiles().get(page).getName();
			String a = label2;
			if (a.length() > 10)
				a = a.substring(0, 10);

			ButtonLabelComponent b = new ButtonLabelComponent(a);
			b.getButton().setValue(page);

			b.getButton().setOnAction(new EventHandler<ActionEvent>()
			{

				@Override
				public void handle(ActionEvent event)
				{
					view.setDisableItems();
					playState.set(1);
					String a = label2;
					if (a.length() > 10)
						a = a.substring(0, a.length());
					temp = label2.length() > nameLength ? label2.substring(0, nameLength) : label2;
					view.getPlayVal().setText("Sie h�ren: ");
					view.getDataname().setText(temp);
					if (mediaPlayer != null)
						mediaPlayer.stop();
					Media sound = new Media(
							new File(model.getFiles().get(b.getButton().getValue()).toString()).toURI().toString());
					mediaPlayer = new MediaPlayer(sound);
					mediaPlayer.volumeProperty().bind(view.getVolume().valueProperty().divide(100));
					mediaPlayer.balanceProperty().bind(view.getBalance().valueProperty());
					mediaPlayer.play();
					if(view.getShuffle().isSelected()){
						shuffleplay();
					}
					else if(view.getAutoPlay().isSelected()){
						autoplay(playState);
					}
				}

				

				private void autoplay(AtomicInteger playStat) {
					
					mediaPlayer.setOnEndOfMedia(new Runnable() {
						
						@Override
						public void run() {
							if(b.getButton().getValue()+playState.get()<model.getFiles().size()){
							Media sound = new Media(
									new File(model.getFiles().get(b.getButton().getValue()+1).toString()).toURI().toString());
							String randlabel=model.getFiles().get(b.getButton().getValue()+1).getName();
							String temp2 = randlabel.length() > nameLength ? randlabel.substring(0, nameLength) : randlabel;
							view.getPlayVal().setText("Sie h�ren: ");
							view.getDataname().setText(temp2);
							mediaPlayer = new MediaPlayer(sound);
							mediaPlayer.volumeProperty().bind(view.getVolume().valueProperty().divide(100));
							mediaPlayer.balanceProperty().bind(view.getBalance().valueProperty());
							mediaPlayer.play();
							playState.set(playState.get()+1);
							autoplay(playState);
							}
							
						}
					});
					
				}

				private void shuffleplay() {
					view.setDisableItems();
					mediaPlayer.setOnEndOfMedia(new Runnable() {
						
						@Override
						public void run() {
							int random=(int)((Math.random()*model.getFiles().size()));
							playState.set(random);
							Media sound = new Media(
									new File(model.getFiles().get(playState.get()).toString()).toURI().toString());
							String randlabel2=model.getFiles().get(random).getName();
							String temp3 = randlabel2.length() > nameLength ? randlabel2.substring(0, nameLength) : randlabel2;
							view.getPlayVal().setText("Sie h�ren: ");
							view.getDataname().setText(temp3);
							
							mediaPlayer = new MediaPlayer(sound);
							mediaPlayer.volumeProperty().bind(view.getVolume().valueProperty().divide(100));
							mediaPlayer.balanceProperty().bind(view.getBalance().valueProperty());
							
							mediaPlayer.play();
							shuffleplay();

							
						}
					});
								
				}
			});

			
			view.getRandom().setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					System.out.println("a");
					randomPlay();
					
				}
			});
			
			
				
			
			b.getButton().setOnMouseEntered(new EventHandler<Event>()
			{

				@Override
				public void handle(Event event)
				{
					
					String a = label2;
					if (label2.length() > nameLength)
						a = label2.substring(0, nameLength);

					view.getPlayVal().setText("Wollen Sie: ");
					view.getDataname().setText(a + " anh�ren?");

				}
			});

			b.getButton().setOnMouseExited(new EventHandler<Event>()
			{

				@Override
				public void handle(Event event)
				{
					view.getDataname().setText(temp);
					view.getPlayVal().setText("Sie h�ren: ");
				}
			});

			view.getButtonPane().add(b, t, z);
			t++;
			if (t % tempValue == 0)
			{
				z++;
				t = 1;
			}

		}

	}

	public void randomPlay(){
		 RotateTransition rt = new RotateTransition(Duration.millis(600), view.getImage());
	     rt.setByAngle(720);
	     rt.setCycleCount(2);
	     rt.setAutoReverse(true);
	 
	     rt.play();
		
			if(model.getFiles().size()<=0){
				return;
			}
			view.setDisableItems();
			if(mediaPlayer!=null){
				mediaPlayer.stop();
			}
			
			int random=(int)((Math.random()*model.getFiles().size()));
			playState.set(random);
			Media sound = new Media(
					new File(model.getFiles().get(playState.get()).toString()).toURI().toString());
			String randlabel2=model.getFiles().get(random).getName();
			temp=randlabel2;
			String temp3 = randlabel2.length() > nameLength ? randlabel2.substring(0, nameLength) : randlabel2;
			view.getPlayVal().setText("Sie h�ren: ");
			view.getDataname().setText(temp3);
			
			mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.volumeProperty().bind(view.getVolume().valueProperty().divide(100));
			mediaPlayer.balanceProperty().bind(view.getBalance().valueProperty());
			mediaPlayer.play();
			
			
			
			mediaPlayer.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					randomPlay();
					
				}
				
				
			

					
				
			});
	}
	
	public void choose()
	{

		fileChooser = new FileChooser();
		fileChooser.setTitle("Neue Sounddatei ausw�hlen");

		String currentDir = System.getProperty("user.home");
		File file = new File(currentDir);

		if (model.getFiles().size() > 0)

		{
			fileChooser.setInitialDirectory(model.getFiles().get(0).getParentFile());
		} else
		{
			fileChooser.setInitialDirectory(file);
		}
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Audio Files", "*.mp3"));
		try
		{

			model.getFiles().addAll(fileChooser.showOpenMultipleDialog(new Popup()));

		} catch (Exception e)
		{
			e.printStackTrace();
		}
		Model.saveData();
		updatePane();
	}

	public ArrayList<File> getFile()
	{
		return model.getFiles();
	}

	public void updateData()
	{
		
		view.getDataList().getItems().clear();

		for (File item : model.getFiles())
		{
			HBoxCell a = new HBoxCell(item);
			view.getDataList().getItems().add(a);
			a.button.setOnAction(new EventHandler<ActionEvent>()
			{

				@Override
				public void handle(ActionEvent event)
				{
					view.getDataList().getItems().remove(a);
					model.getFiles().remove(item);

				}
			});

		}

	}

	public void setModel(Model model)
	{
		this.model = model;
	}

	public void saveData()
	{

		Model.saveData();

	}

	public void delAll()
	{
		click = 0;
		page = 0;
		pageCount = 1;
		model.getFiles().clear();
		updatePane();

	}

	public void stopall()
	{
		if(mediaPlayer!=null){
		mediaPlayer.stop();
		view.setActiveItems();
		}
	}

	public void setData() {
		int c=(int)view.getNamelength().getValue();
		int b=view.getButtonZeile().getSelectionModel().getSelectedItem().intValue();
		int a=view.getAnzahlButtons().getSelectionModel().getSelectedItem().intValue();
		int d=view.getHotKey().getSelectionModel().getSelectedIndex();
		model.getLayoutData()[0]=b;
		model.getLayoutData()[1]=a;
		model.getLayoutData()[2]=c;
		model.getLayoutData()[3]=d;
		nameLength=c;
	
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		view.getPlayVal().setText(NativeKeyEvent.getKeyText(e.getKeyCode()).toString());
		view.getDataname().setText("asd");
		System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
		System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
		System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
	
		System.out.println(NativeKeyEvent.getKeyText(e.getRawCode()));
		System.out.println(NativeKeyEvent.getKeyText(e.getKeyLocation()));
	
Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				if(NativeKeyEvent.getKeyText(e.getKeyCode()).equals(view.getHotKey().getSelectionModel().getSelectedItem())){
					randomPlay();
				}
				
				if(NativeKeyEvent.getKeyText(e.getKeyCode()).equals("Num"))
				{
					if(volumehotkey){
						volumehotkey=false;
					}
					else{
						volumehotkey=true;
					}
					
				}
				
				if(volumehotkey){
				
				
				if(NativeKeyEvent.getKeyText(e.getKeyCode()).equals("Bild auf")){
					if(mediaPlayer!=null){
					
						view.getVolume().setValue(view.getVolume().getValue()+5);
					}
					
				}
				if(NativeKeyEvent.getKeyText(e.getKeyCode()).equals("Bild ab")){
					if(mediaPlayer!=null){
						view.getVolume().setValue(view.getVolume().getValue()-5);
					}
					
				}
				}
				
			}
		});
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		
	
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
