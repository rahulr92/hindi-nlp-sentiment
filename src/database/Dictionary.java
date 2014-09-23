package database;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class Dictionary {
	public static String searchEnglish(String word){
		String meaning = null;
		String engDic = "C:\\Users\\rrakes\\workspace\\Hackathone\\src\\database\\EnglishWords.txt";
		File engFile = new File(engDic);
		BufferedReader br;
		try {
		br = new BufferedReader(new FileReader(engFile));
			
		String line;
		while ((line = br.readLine()) != null) {
			
		   if(line.compareTo(word) == 0){
			   
			   meaning = word;
			   return meaning;
		   }
		  
		}
		br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return meaning;
	}
	public static String searchHindi(String word){
		String meaning = null;
		String hinDic = "C:\\Users\\rrakes\\workspace\\Hackathone\\src\\database\\HindiWords.txt";
		File hinFile = new File(hinDic);
		BufferedReader br;
		try {
		br = new BufferedReader(new FileReader(hinFile));
		String line;
		while ((line = br.readLine()) != null) {
			
			   int index = line.indexOf('=');
			   String key = line.substring(0,index);
			   if(key.compareTo(word)==0){
			      String value = line.substring(index+1, line.length());
			      meaning = value;
			      
			      return meaning;
			   }
			   
			  }
			  
		br.close();	
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return meaning;
	}
	public static String searchHinglish(String word){
		String meaning = null;
		String hinEngDic = "C:\\Users\\rrakes\\workspace\\Hackathone\\src\\database\\HindiEngWords.txt";
		File hinEngFile = new File(hinEngDic);
		BufferedReader br;
		try {
		br = new BufferedReader(new FileReader(hinEngFile));
		String line;
		while ((line = br.readLine()) != null) {
			
			   int index = line.indexOf('=');
			   String key = line.substring(0,index);
			   String value = line.substring(index+1, line.length());
			   if(value.compareTo(word)==0){
			      
			      meaning = searchHindi(key);
			      return meaning;
			   }
			   
			  }
			  
		br.close();	
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return meaning;
	}

	public static String meaningOfWord(String word) {
		String meaning = null;
		
		meaning = searchEnglish(word);
		if(meaning == null)
			meaning = searchHindi(word);
		if(meaning == null)
			meaning = searchHinglish(word);
		
		return meaning;
	}
	
}
