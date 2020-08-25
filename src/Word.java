import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Word {
    private ArrayList<DictionaryEntry> entries;
    private String keyword;
    private ArrayList<Word> words;
    private int ind;

    public Word(String keyword, ArrayList<Word> words, int ind) throws IOException {
        Document d = Jsoup.connect("http://www.perseus.tufts.edu/hopper/morph?l=" + keyword + "&la=la").timeout(6000).get();
        entries = new ArrayList<>();
        this.keyword = keyword;
        this.words = words;
        this.ind = ind;
        search(d, true);
    }
    private void search(Document d, boolean good) {
        ArrayList<String> bases = new ArrayList<>();
        ArrayList<String> definitions = new ArrayList<>();
        ArrayList<String> forms = new ArrayList<>();
        Elements ele = d.select("h4.la");
        for (Element element : ele)
            bases.add(element.text());
        ele = d.select("span.lemma_definition");
        for(Element element : ele)
            definitions.add(element.text());
        ele = d.select("table");
        for(Element element : ele) {
            forms.add(element.text());
        }
        forms.remove(0);
        if(bases.size() == 0 && keyword.charAt(0) >= 'A' && keyword.charAt(0) <= 'z'){
            entries.add(new DictionaryEntry(keyword));
        }
        else {
            for (int i = 0; i < bases.size(); i++)
                if (!definitions.get(i).contains("definition unavailable") && bases.get(i).charAt(bases.get(i).length() - 1) >= 'A')
                    entries.add(new DictionaryEntry(keyword, bases.get(i), definitions.get(i), forms.get(i), words, ind, good));
        }
    }

    public ArrayList<DictionaryEntry> getEntries() {
        return entries;
    }
    public void filter1(){
        for(DictionaryEntry entry : entries)
            entry.filter1();
        for (int i = 0; i < entries.size() && entries.size() != 1; i++) {
            DictionaryEntry entry = entries.get(i);
            if (entry.getUses().isEmpty())
                entries.remove(entry);
        }
    }
    public void filter2() throws IOException {
        for(DictionaryEntry entry : entries)
            entry.filter2();
        for (int i = 0; i < entries.size() && entries.size() != 0; i++) {
            DictionaryEntry entry = entries.get(i);
            if (entry.getUses().isEmpty())
                entries.remove(entry);
        }
        if(entries.isEmpty()) {
            search(Jsoup.connect("http://www.perseus.tufts.edu/hopper/morph?l=" + keyword + "&la=la").timeout(6000).get(), false);
        }
    }

    public String toString(){
        String rtn = "";
        for(DictionaryEntry entry : entries)
            rtn += entry.toString();
        return rtn;
    }
    public String getKeyword(){
        return keyword;
    }

}

