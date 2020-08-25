import java.util.ArrayList;

public abstract class Use {
    public abstract void filter1(ArrayList<Word> words);
    public abstract void filter2(ArrayList<Word> words);

    public void removeDuplicates(ArrayList<String> strs){
        for (int i = 0; i < strs.size(); i++) {
            for (int j = 0; j < strs.size(); j++) {
                if(i != j && strs.get(i).equals(strs.get(j))) {
                    strs.remove(strs.get(i));
                    i--;
                    break;
                }
            }
        }
    }
    public boolean isIn(Word wd, String[] strs){
        for(String s : strs)
            for(DictionaryEntry entry : wd.getEntries())
                if(entry.getBase().equals(s))
                    return true;
        return false;
    }
    public boolean isIn2(Word wd, String[] strs){
        for(String s : strs)
            if(wd.getKeyword().equals(s))
                return true;
        return false;
    }
    public boolean isIn3(Word wd, String clss){
            for(DictionaryEntry entry : wd.getEntries())
                for(Use use : entry.getUses()) {
                    String c = use.getClass().toString().substring((use.getClass().toString().indexOf(" ") + 1));
                    if (c.equals(clss))
                        return true;
                    else if (clss.equals("Verb") && (c.equals("Indicative") || c.equals("Infinitive") || c.equals("Subjunctive") || c.equals("Imperative") || c.equals("Participle")))
                        return true;
                }
                    return false;
    }
    public boolean contains1(Word wd, String b){
        for(DictionaryEntry entry : wd.getEntries())
            if(entry.getBase().contains(b))
                return true;
            return false;
    }
    public boolean contains15(Word wd, String[] bases){
        for(DictionaryEntry entry : wd.getEntries())
            for(String b : bases)
                if(entry.getBase().contains(b))
                    return true;
                return false;
    }
    public boolean contains2(Word wd, String f){
        for(DictionaryEntry entry : wd.getEntries())
            for(String form : entry.getForms())
                if(form.contains(f))
                    return true;
                return false;
    }
    public boolean prefix(Word wd, String[] preps){
        for(String  prep : preps)
            for(DictionaryEntry entry : wd.getEntries())
                if(entry.getBase().indexOf(prep) == 0)
                    return true;
        return false;
    }
    public boolean hasUse(Word wd, String str){
        for(DictionaryEntry entry : wd.getEntries())
            for(Use use : entry.getUses())
                if(use.getUses().contains(str))
                    return true;
                return false;
    }
    public abstract ArrayList<String> getUses();
    public abstract String toString();

}
