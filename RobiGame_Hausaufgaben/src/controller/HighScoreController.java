package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

import model.HighScore;

public class HighScoreController {
	
	ArrayList<HighScore> scores;
	
	public static void main(String[] args) {
		HighScoreController testController = new HighScoreController();
		
		testController.readHighscores();
		testController.sortHighscores();
		testController.printHighscores();
		testController.writeHighscores();
	}
	
	
	
	public void printHighscores() {
		for (HighScore hs : scores) {
			System.out.println(hs.getName() + ": " + hs.getScore());
		}
	}
	
	
	
	public void sortHighscores() {
		//scores.sort((a,b) -> b.getScore()-a.getScore());
		scores.sort(new Comparator<HighScore>() {
			
		public int compare(HighScore a, HighScore b) {
			return b.getScore()-a.getScore();		
	}
		});
	
		}
	
	
	
	public void addHighscores(HighScore a) {
		scores.add(a);
	}
	
	
	
	/**public void writeHighscores() {
		PrintWriter prnt = null;
		
		try {
			prnt = new PrintWriter(System.getProperty("user.home") + "/highscore.lst");
			//int i = 0;
			for (HighScore score : scores) {
				prnt.write(score.getName() + ";" + score.getScore() + System.lineSeparator());
				//i++;
			}} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {

				prnt.flush();
				prnt.close();
			}
	}*/
	
	
	public void writeHighscores() {
		PrintWriter prnt = null;
		try {
		prnt = new PrintWriter(System.getProperty("user.home") + "\\highscore.lst");
		// For-each loop zum abklappern der einzelnen HighScore objekte
		for (HighScore score : scores) {
		prnt.write(score.getName() + ";" + score.getScore() + System.lineSeparator());
		}
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		finally {
		prnt.flush(); // puffer leeren und ins file schreiben
		prnt.close(); // Systemresourcen freigeben
		}
		}


	
	
	public void readHighscores() {
		scores = new ArrayList<>();
		
		
		File file = new File(System.getProperty("user.home") + "/highscore.lst");
		BufferedReader bfr = null;
		
		try {
			file.createNewFile();
			FileReader reader = new FileReader(file);
			bfr = new BufferedReader(reader);
			
			while(bfr.ready()) {
				String line = bfr.readLine();
				//System.out.println(line);
				
				scores.add(parseHighscore(line));  //HighScore-Objekt parsen und in Liste einfuegen
			}
			
		} catch (IOException ioex) {
			// TODO Auto-generated catch block
			System.out.println(file.getAbsolutePath());
			ioex.printStackTrace();
		} finally {
			try { bfr.close();
			} catch(IOException e) {e.printStackTrace();}
		}
	}
	
	
	/**
	 * liest alle HighScore-Objekte aus der Datei
	 */

	
	private HighScore parseHighscore(String line) {
		HighScore result = null;
		String[] parts = line.split(";");
		String name = parts[0];
		int punkte = Integer.parseInt(parts[1]);
		result = new HighScore(name, punkte);
		return result;
	}
	
	
	public ArrayList<HighScore> getHighscoreList(){
		return scores;
	}

}
