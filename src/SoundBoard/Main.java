package SoundBoard;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application 
{

	private View view;
	private Model model;
	private Presenter presenter;
	private Scene scene;
	HostServicesDelegate hostServices;

	@Override
	public void start(Stage mainStage) throws Exception
	{
		hostServices = HostServicesFactory.getInstance(this);
		model = new Model();
		view = new View(hostServices);
		presenter = new Presenter(view, model,mainStage);
		view.setPresenter(presenter);
		
		
		 scene = new Scene(view.getUI(), 623, 400);
				mainStage.setScene(scene);
		mainStage.show();
		
		mainStage.setResizable(false);
		mainStage.setTitle("Soundboard 3.0");
		mainStage.getIcons().add(new Image(getClass().getResourceAsStream("../assets/main.jpg")));
		mainStage.setOnHiding((WindowEvent w)->{ System.exit(1);});

			}

	

	
	public static void main(String[] args)
	{
		
		launch(args);
		
	}




	

}
