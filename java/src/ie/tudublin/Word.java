package ie.tudublin;

import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Follow> follows;

    public Word(String word) {
        this.word = word;
        this.follows = new ArrayList<Follow>();
    }

    public String getWord() {
        return this.word;
    }

    public ArrayList<Follow> getFollows() {
        return this.follows;
    }

    public void addFollow(String follow) {
        // Check if the follow already exists in the list
        for (Follow f : this.follows) {
            if (f.getWord().equals(follow)) {
                f.incrementCount();
                return;
            }
        }
        // If the follow doesn't exist, add it to the list
        this.follows.add(new Follow(follow));
    }

    public String toString() {
        String output = this.word + ": ";
        for (Follow f : this.follows) {
            output += f.toString() + " ";
        }
        return output;
    }
}








