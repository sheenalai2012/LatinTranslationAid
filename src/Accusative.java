import java.util.ArrayList;

public class Accusative extends Use{
    private ArrayList<String> uses;

    String[] duration  = {"dies", "hora", "mensis", "annus", "nox", "vespernus"};
    String[] preposition = {"ante", "per", "ad", "propter", "circum", "super", "contra", "versus", "inter", "extra", "intra", "trans", "post", "sub", "in", "ob", "praeter"};
    public Accusative(String base){
        uses = new ArrayList<>();
        for(String str : duration)
            if(str.equals(base))
                uses.add("duration of time");
    }

    @Override
    public void filter1(ArrayList<Word> words) {
        for(Word wd : words)
            if(isIn(wd, preposition))
                uses.add("object of preposition");
            else if(isIn3(wd, "Verb") && (contains1(wd, "or") || contains2(wd, "act")))
                uses.add("direct object");
            else if(isIn3(wd, "Infinitive"))
                uses.add("subject of infinitive indirect statement");
    }
    public void filter2(ArrayList<Word> words){
        uses.add("direct object");
        uses.add("extent of space");
        uses.add("2nd object in a verb that takes double accusatives");
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
