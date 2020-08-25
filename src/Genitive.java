import java.util.ArrayList;

public class Genitive extends Use{
    private ArrayList<String> uses;
    private String word;
    private boolean indef;
    // String[] partitives = {}; fjglskdg
    String[] remembering = {"memini", "obliviscor", "reminīscor",  "admoneo", "commone", "commonefacio", "commonefio"};
    String[] accusing = {"accuso", "arguo", "condemno", "absolvo", "damno"}; // reum facere?????
    String[] feeling = {"misereor", "miseresco"};
    String[] impersonal = {"misereo", "paeniteo", "pigeo", "pudeo", "taedet"}; // pertaesum est ?????
    String[] indefinite = {"aestimo", "duco", "habeo", "facio"};
    String[] indefinite2 = {"parvus", "tantus", "quantus", "magnus"};

    //partitive lists
    String[] part = {"pars", "quis", "nihil", "nemo", "alter", "nullus", "alius", "parum", "satis", "aliquis", "is", "tantus", "plus", "uterque", "quisque"};
    String[] ordin = {"primus", "secundus", "tertius", "quartus", "quintus", "sextus", "septimus", "octavus", "nonus", "decimus", "vicesimus", "tricesimus", "gesimus", "centesimus", "gentensimus", "centensimus", "millensimus"};

    public Genitive(String word){
        this.word = word;
        uses = new ArrayList<>();
    }
    public void filter1(ArrayList<Word> words){
       for(Word wd : words) {
           if (wd.getKeyword().equals("causa"))
               uses.add("with causa");
           else if (wd.getKeyword().equals("gratia"))
               uses.add("with gratia");
           else if (wd.getKeyword().equals("interest") || wd.getKeyword().equals("refert"))
               uses.add("with verb to show interest");
            else if(isIn(wd, remembering))
                uses.add("with verb of remembering, forgetting, or warning someone/something");
            else if(isIn(wd, accusing))
                uses.add("with verb of accusing or condemning");
            else if (isIn(wd, impersonal) && contains2(wd, "3rd sg"))
                uses.add("with impersonal verb to show cause of feeling");
            else if(isIn(wd, feeling))
                uses.add("with verb of pity");
            else if(isIn(wd, indefinite))
                indef = true;
            else if(contains15(wd, ordin) || isIn(wd, part) || isIn3(wd, "Numeral") || contains2(wd, "super") || contains2(wd, "comp"))
                uses.add("partitive");


       }
    }
    public void filter2(ArrayList<Word> words){
        for(Word wd : words){
            if(isIn3(wd, "Adjective") && hasUse(wd, word))
                uses.add("description/quality");
            else if (indef && isIn(wd, indefinite2))
                uses.add("indefinite value");
        }
        uses.add("possession");
        uses.add("objective");
    }
    public ArrayList<String> getUses(){
        return uses;
    }
    public String toString(){
        return "(" + uses.toString().substring(1, uses.toString().length() - 1) + ")";
    }
}
