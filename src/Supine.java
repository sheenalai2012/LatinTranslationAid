import java.util.ArrayList;

public class Supine extends Use {
    private ArrayList<String> uses;
    private String str;

    String[] acc = {"venio", "eo"};
    String[] abl = {"nefas", "fas", "opus", "facilis", "difficilis", "iucundus", "credibilis"};
    public Supine(String str){
        uses = new ArrayList<>();
        this.str = str;
    }
    @Override
    public void filter1(ArrayList<Word> words) {
        for(Word wd : words){
            if(str.contains("nom") && isIn(wd, acc))
                uses.add("accusative supine to show purpose");
            else if(str.contains("dat") && isIn(wd, abl))
                uses.add("ablative supine of specification");
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
        return "(" + uses.toString().substring(1, uses.toString().length() - 1) + ")";
    }
}
