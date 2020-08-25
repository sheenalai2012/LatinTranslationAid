import java.util.ArrayList;

public class Participle extends Use {
    private ArrayList<String> uses;
    private String word;
    private String form;
    private int ind;
    public Participle(String word, String form, int ind){
        uses = new ArrayList<>();
        this.word = word;
        this.form = form;
        this.ind = ind;
    }
    public void filter1(ArrayList<Word> words) {
        for (int i = 0; i < words.size(); i++)
            if(ind != i) {
                Word wd = words.get(i);
                for (DictionaryEntry entry : wd.getEntries())
                    for (String fm : entry.getForms())
                        if ((fm.substring(0, fm.indexOf(" ")).equals("noun") || fm.substring(0, fm.indexOf(" ")).equals("pron")) && fm.substring(fm.indexOf(" ") + 1).contains(form))
                            if (!uses.contains(entry.getWord()))
                                uses.add(entry.getWord());
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
        if(uses.isEmpty())
            return "";
        else
            return "(" + uses.toString().substring(1, uses.toString().length() - 1) + ")";
    }
}
