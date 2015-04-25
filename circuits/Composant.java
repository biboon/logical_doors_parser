package circuits;


public abstract class Composant implements Comparable<Composant> {

    public String getId() {
        return super.toString(); // class@numero renvoye par Object
    }

    public String description() {
        return getId();
    }
    
    public String traceEtat() {
        String res = description();
        try {
          res += " state: " + getEtat();
        } catch (NonConnecteException ex) {
          res += " Composant non connecte";
        }
        return res;
    }

    public abstract boolean getEtat() throws NonConnecteException;
    
    public int compareTo(Composant cp) {
        return this.description().compareTo(cp.description());
    }
}
