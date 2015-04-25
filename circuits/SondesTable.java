package circuits;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;

public class SondesTable {

    private Map<LazySonde, Interrupteur> sondesToInterrupteurs = new TreeMap<LazySonde, Interrupteur>();
    private Map<Interrupteur, LazySonde> interrupteursToSondes = new TreeMap<Interrupteur, LazySonde>();
    
    public Interrupteur getInterrupteur(LazySonde sonde) {
        return sondesToInterrupteurs.get(sonde);
    }
    
    public LazySonde getSonde(Interrupteur inter, Composant cible, String entree) {
        if (interrupteursToSondes.containsKey((Interrupteur) inter)) {
            return interrupteursToSondes.get((Interrupteur) inter);
        } else {
            LazySonde newSonde = new LazySonde(cible, entree);
            sondesToInterrupteurs.put(newSonde, inter);
            interrupteursToSondes.put(inter, newSonde);
            return newSonde;
        }
    }
    
    public void resetSondes() {
        Iterator<LazySonde> iter = interrupteursToSondes.values().iterator();
        while (iter.hasNext())
            iter.next().reset();
    }
    
    public void clear() {
        interrupteursToSondes.clear();
        sondesToInterrupteurs.clear();
    }
}
