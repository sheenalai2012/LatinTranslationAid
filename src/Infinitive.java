import java.util.ArrayList;

public class Infinitive extends Use {
    private ArrayList<String> uses;
    private boolean hist;

    String[] compl = {"possum", "statuo", "volo", "conor", "nolo", "tempto", "malo", "audeo", "cupio", "debeo", "patior", "constituo", "dubito", "paro", "incipio", "desisto", "videor"};
    public Infinitive(){
        uses = new ArrayList<>();
        hist = true;
    }
    @Override
    public void filter1(ArrayList<Word> words) {
        for(Word wd : words)
            if(isIn(wd, compl))
                uses.add("complementary infinitive");
            else if(isIn3(wd, "Accusative"))
                uses.add("indirect statement");
            else if(isIn3(wd, "Indicative"))
                hist = false;

    }
    public void filter2(ArrayList<Word> words){
        if(hist)
            uses.add("historical infinitive");
    }

    @Override
    public ArrayList<String> getUses() {
        return uses;
    }

    @Override
    public String toString() {
        return "(" + uses.toString().substring(1, uses.toString().length() - 1) + ")";
    }
}
