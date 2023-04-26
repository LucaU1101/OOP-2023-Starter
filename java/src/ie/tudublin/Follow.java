package ie.tudublin;

public class Follow {
    String word;
    int count;

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public Follow(String word, int count)
    {
        this.word = word;
        this.count = count;
    }

    @Override
    public String toString() 
    {
        return word + ": " + count;
    }

    public void incrementCount() 
    {
        count++;
    }

    
}