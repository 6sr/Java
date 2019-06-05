
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    
    public PhraseFilter(String pos, String toFind) {
        where = pos;
        phrase = toFind;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        if(where.equals("start") && title.startsWith(phrase)) {     //Checking if phrase in start
            return true;
        }
        else if(where.equals("end") && title.endsWith(phrase)) {     //Checking if phrase in end
            return true;
        }
        else if(where.equals("any") && title.contains(phrase)) {     //Checking if phrase present anywhere
            return true;
        }
        return false;
    }
    
    public String getName() {
        return "Phrase";
    }
}