package ie.tudublin;

import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;

public class DANI extends PApplet {

    private ArrayList<Word> model;
    private Random random;

    public void settings() {
        size(1000, 1000);
        //fullScreen(SPAN);
    }

    public void setup() {
        colorMode(HSB);
        loadFile("shakespere.txt");
        printModel();
    }

    String[] sonnet;

    public DANI() 
    {
        model = new ArrayList<>();
        random = new Random();
    }

	public void loadFile(String file)
    {
        String[] lines = loadStrings(file);

        for(String line:lines) 
        {
            String[] words = split(line, ' ');
    
            for(int i = 0; i < words.length - 1; i++) 
            {
                String currentWord = words[i].replaceAll("[^\\w\\s]", "").toLowerCase();
                String nextWord = words[i + 1].replaceAll("[^\\w\\s]", "").toLowerCase();

                if (currentWord.isEmpty() || nextWord.isEmpty()) 
                {
                    continue;
                }
    
                Word word = findWord(currentWord);
                if(word == null) 
                {
                    word = new Word(currentWord);
                    model.add(word);
                }
                word.addFollow(nextWord);
            }
        }
    }

    public String[] writeSonnet()
    {
        String[] sonnet = new String[14];


        for (int i = 0; i < 14; i++) {
            sonnet[i] = writeLine();
        }
        return sonnet;
    }

    public String writeLine() {
		StringBuilder sb = new StringBuilder();
		Word currentWord = model.get(random.nextInt(model.size()));
		int wordCount = 0;
	
		while (wordCount < 7) {
			sb.append(currentWord.getWord()).append(" ");
			ArrayList<Follow> follows = currentWord.getFollows();
	
			if (follows.isEmpty()) {
				break;
			}
	
			Follow nextFollow = follows.get(random.nextInt(follows.size()));
			currentWord = findWord(nextFollow.getWord());
	
			if (currentWord == null) {
				break;
			}
	
			wordCount++;
		}
	
		return sb.toString().trim();
	}

    

    public Word findWord(String word) 
    {
        for(Word w:model) 
        {
            if(w.getWord().equals(word)) 
            {
                return w;
            }
        }
        return null;
    }

    public void printModel() 
    {
        for (Word word : model) 
        {
            System.out.print(word.getWord() + ": ");
            ArrayList<Follow> follows = word.getFollows();
            for (int i = 0; i < follows.size(); i++) 
            {
                Follow follow = follows.get(i);
                System.out.print(follow.getWord() + "(" + follow.getCount() + ")");
                if (i < follows.size() - 1) 
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }


    public void keyPressed() {
        if(key == ' ') 
        {
            sonnet = writeSonnet();
            for (String line : sonnet) 
            {
                System.out.println(line);
            }
        }
    }

    float off = 0;

    public void draw() 
    {
        background(0);
        fill(255);
        noStroke();
        textSize(20);
        textAlign(CENTER, CENTER);
        
        if(sonnet != null) 
        {
			int y = 50;
			for (String line : sonnet) {
				text(line, width / 2, y);
				y += 50;
            }
        }
    }
}






