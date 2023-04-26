package ie.tudublin;
import java.util.ArrayList;

public class Word 
{
    private String word;
    ArrayList<Follow> follows;

    public Word(String word)
    {
        this.word = word;
        this.follows = new ArrayList<>();
    }

    public String getWord() 
    {
        return word;
    }

    public ArrayList<Follow> getFollows() 
    {
        return follows;
    }

    public void addFollow(String followWord) 
    {
        Follow follow = findFollow(followWord);
        if(follow == null) 
        {
            follows.add(new Follow(followWord, 1));
        } 
        else 
        {
            follow.incrementCount();
        }
    }

    public Follow findFollow(String followWord) 
    {
        for(Follow follow : follows) 
        {
            if (follow.getWord().equals(followWord)) {
                return follow;
            }
        }
        return null;
    }

    @Override
    public String toString() 
    {
        String result = word + " -> ";

    for (int i = 0; i < follows.size(); i++) {
        Follow follow = follows.get(i);
        result += follow.toString();
        if (i < follows.size() - 1) {
            result += ", ";
        }
    }
    return result;
    }

}