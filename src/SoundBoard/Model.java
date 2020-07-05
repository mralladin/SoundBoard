package SoundBoard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model
{

	private static ArrayList<File> filedata;
	private ArrayList<HBoxCell> filedata2;
	private final ObservableList<Integer> data;
	private ArrayList<Integer> datal;
	private static int[] LayoutData;
	private ArrayList<String> keyd;
	private final ObservableList<String> keydo;

	public Model()
	{
		LayoutData= new int[4];
		keyd= new ArrayList<>();
		
		keydo= FXCollections.observableArrayList(keyd);
		keydo.addAll(new String("F1"),new String("F2"),new String("F3"),new String("F4"),new String("F5"),new String("F6"),new String("F7"),new String("F8"),new String("F9"),new String("F10"),new String("F11"),new String("F12"),new String("Pos 1"),new String("Ende"));
		datal= new ArrayList<>();
		data= FXCollections.observableList(datal);
		for(int i=1;i<11;i++){
			datal.add(new Integer(i));
			
		}
		filedata = new ArrayList<>();
		filedata2 = new ArrayList<>();
		
		readData();
		
	}

	@SuppressWarnings("unchecked")
	public static void readData()
	{
		try
		{
			FileInputStream fis = new FileInputStream("soundboard_data.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			filedata = (ArrayList<File>) (ois.readObject());
			ois.close();
			FileInputStream fis1 = new FileInputStream("Layout_data.ser");
			ObjectInputStream ois1 = new ObjectInputStream(fis1);

			LayoutData = (int[]) (ois1.readObject());
			ois1.close();

		} catch (FileNotFoundException e)
		{
			File yourFile = new File("soundboard_data.ser");
			File yourFile2 = new File("Layout_data.ser");
			try
			{
				LayoutData[0]=1;
				LayoutData[1]=5;
				LayoutData[2]=48;
				LayoutData[3]=8;
				yourFile2.createNewFile();
				yourFile.createNewFile();
			} catch (IOException e1)
			{
				
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}

		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			LayoutData[0]=1;
			LayoutData[1]=5;
			LayoutData[2]=48;
			LayoutData[3]=8;
			e.printStackTrace();
		}
	}

	public static void saveData()
	{
	
		try
		{

			FileOutputStream fos = new FileOutputStream("soundboard_data.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(filedata);
			oos.close();
			
			FileOutputStream fos1 = new FileOutputStream("Layout_data.ser");
			ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
			
			oos1.writeObject(LayoutData);
			oos1.close();

		} catch (FileNotFoundException e)
		{
			File yourFile = new File("soundboard_data.ser");
			try
			{
				yourFile.createNewFile();
			} catch (IOException e1)
			{

				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public ArrayList<File> getFiles()
	{

		return Model.filedata;
	}

	public ArrayList<HBoxCell> getFiles2()
	{

		return this.filedata2;
	}

	public ObservableList<Integer> getData() {
		return data;
	}

	public int[] getLayoutData() {
		return LayoutData;
	}

	public void setLayoutData(int[] layoutData) {
		LayoutData = layoutData;
	}

	public ObservableList<String> getKeydo() {
		return keydo;
	}

}
