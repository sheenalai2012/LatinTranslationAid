import java.util.ArrayList;

public class Nominative extends Use {
    private ArrayList<String> uses;
    private String word;
    public Nominative(String word){
        this.word = word;
        uses = new ArrayList<>();
        uses.add("subject");
    }
    public void filter1(ArrayList<Word> words){
        boolean hasVerb = false;
        for(Word word : words)
            for(DictionaryEntry entry : word.getEntries()){
                if(entry.getBase().equals("sum") || entry.getBase().equals("fio"))
                    uses.add("predicate nominative");
                for(String form : entry.getForms())
                    if(form.substring(0, form.indexOf(" ")).equals("verb"))
                        hasVerb = true;
            }
        if(!hasVerb && !uses.contains("predicate nominative"))
            uses.add("predicate nominative");
    }
    public void filter2(ArrayList<Word> words){

    }
    public ArrayList<String> getUses(){
        return uses;
    }

    public String toString(){
        return "(" + uses.toString().substring(1, uses.toString().length() - 1) + ")";
    }
}
