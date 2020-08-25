import java.util.ArrayList;

public class Gerundive extends Use{
    private ArrayList<String> uses;
    String word;
    public Gerundive(String word){
        uses = new ArrayList<>();
        this.word = word;
    }
    @Override
    public void filter1(ArrayList<Word> words) {
        for(Word wd : words)
            if(wd.getKeyword().equals("ad") && word.substring(word.length() - 4).equals("ndum")){
                uses.add("accusative to show purpose");
            }
    }
    public void filter2(ArrayList<Word> words){

    }

    @Override
    public ArrayList<String> getUses() {
        return uses;
    }

    @Override
    public String toString() {
        return  "(" + uses.toString().substring(1, uses.toString().length() - 1) + ")";
    }
}
