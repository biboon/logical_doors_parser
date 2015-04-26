package circuits;

public class Interrupteur extends Composant {

    protected boolean etat;
    protected String name;
    
    public Interrupteur(String s) {
        name = s;
    }
    
    public description() {
        super.description() + " name: " + name;
    }

    public void on() {
        etat = true;
    }

    public void off() {
        etat = false;
    }

    public boolean getEtat() throws NonConnecteException {
        return etat;
    }
}
