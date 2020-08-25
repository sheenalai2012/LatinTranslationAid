import java.io.IOException;
import java.util.ArrayList;

public class Chunk {
    private ArrayList<Word> words;
    public Chunk(String chunk) throws IOException {
        words = new ArrayList<>();
        divide(chunk);
    }

    private void divide(String chunk) throws IOException {
        int j = 0;
        int ind = 0;
        if(chunk.charAt(0) <= '?' && chunk.charAt(0) >= '!') {
            words.add(new Word(chunk, words, ind));
        }
        else {
            chunk += " ";
            for (int i = 0; i < chunk.length(); i++)
                if (chunk.charAt(i) == ' ') {
                    words.add(new Word (chunk.substring(j, i), words, ind));
                    j = i + 1;
                    ind++;
                }
        }
    }
    public void filter1(){
        for(Word word : words){
            word.filter1();
        }
    }
    public void filter2() throws IOException {
        for(Word word : words)
            word.filter2();
    }
    public ArrayList<Word> getWords() {
        return words;
    }
    public String toString(){
        String rtn = "";
        for(Word word : words)
            if(!word.getEntries().isEmpty())
             rtn += (word.toString() + "\n");
        return rtn;
    }

}
