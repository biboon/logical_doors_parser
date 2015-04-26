package circuits;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Circuit {

    protected String nom;
    protected List<Composant> composants = new ArrayList<Composant>();
    protected SondesTable tableSondes = new SondesTable();
    
    public Circuit(String nom) {
        this.nom = nom;
    };

    public Circuit(String nom, Composant[] cps) {
        this.nom = nom;
        composants.addAll(Arrays.asList(cps));
        Collections.sort(composants);
    }
    
    public void addComponent(Composant c) {
        composants.add(c);
        //Collections.sort(composants);
    }
    
    public List<String> nomenclature() {
        Iterator<Composant> iter = composants.iterator();
        List<String> res = new ArrayList<String>();
        while (iter.hasNext())
            res.add(iter.next().getId());
        return res;
    }
    
    public void description() {
        Iterator<Composant> iter = composants.iterator();
        System.out.println("Circuit description of: " + nom);
        while (iter.hasNext())
            System.out.println(iter.next().description());
    }
    
    public void traceEtats() {
        Iterator<Composant> iter = composants.iterator();
        System.out.println("Circuit state of: " + nom);
        while (iter.hasNext())
            System.out.println(iter.next().traceEtat());
    }
    
    public List<Interrupteur> getIns() {
        List<Interrupteur> res = new ArrayList<Interrupteur>();
        Iterator<Composant> iter = composants.iterator();
        while (iter.hasNext()) {
            Composant currentCp = iter.next();
            if (currentCp instanceof Interrupteur)
                res.add((Interrupteur) currentCp);
        }
        return res;
    }
    
    public List<Vanne> getOuts() {
        List<Vanne> res = new ArrayList<Vanne>();
        Iterator<Composant> iter = composants.iterator();
        while (iter.hasNext()) {
            Composant currentCp = iter.next();
            if (currentCp instanceof Vanne)
                res.add((Vanne) currentCp);
        }
        return res;
    }
    
    public void probe() {
        Composant current, tmp_in;
        for (int i = 0; i < composants.size(); i ++) {
            current = composants.get(i);
            if (current instanceof Porte2Entrees) {
            
                tmp_in = ((Porte2Entrees) current).getIn1();
                if (tmp_in instanceof Interrupteur) {
                    LazySonde tmp_probe = tableSondes.getSonde((Interrupteur)  tmp_in, current, "in1");
                    ((Porte2Entrees) current).setIn1(tmp_probe);
                    System.out.println("in1 of " + current.getId() + " switched to LazySonde (" + tmp_probe.getId() + ")");
                }
                
                tmp_in = ((Porte2Entrees) current).getIn2();
                if (tmp_in instanceof Interrupteur) {
                    LazySonde tmp_probe = tableSondes.getSonde((Interrupteur)  tmp_in, current, "in2");
                    ((Porte2Entrees) current).setIn2(tmp_probe);
                    System.out.println("in2 of " + current.getId() + " switched to LazySonde (" + tmp_probe.getId() + ")");
                }
                
            } else if (current instanceof Porte) {
                
                tmp_in = ((Not) current).getIn();
                if (tmp_in instanceof Interrupteur) {
                    LazySonde tmp_probe = tableSondes.getSonde((Interrupteur) tmp_in, current, "in");
                    ((Not) current).setIn(tmp_probe);
                    System.out.println("in of " + current.getId() + " switched to LazySonde (" + tmp_probe.getId() + ")");
                }
                
            }
        }
    }
    
    public void unProbe() {
        Composant current, tmp_in;
        Interrupteur tmp_inter;
        for (int i = 0; i < composants.size(); i ++) {
            current = composants.get(i);
            if (current instanceof Porte2Entrees) {
                tmp_in = ((Porte2Entrees) current).getIn1();
                if (tmp_in instanceof LazySonde) {
                    tmp_inter = tableSondes.getInterrupteur((LazySonde)  tmp_in);
                    ((Porte2Entrees) current).setIn1(tmp_inter);
                    System.out.println("in1 of " + current.getId() + " restored to Interrupteur (" + tmp_inter.getId() + ")");
                }
                tmp_in = ((Porte2Entrees) current).getIn2();
                if (tmp_in instanceof LazySonde) {
                    tmp_inter = tableSondes.getInterrupteur((LazySonde)  tmp_in);
                    ((Porte2Entrees) current).setIn2(tmp_inter);
                    System.out.println("in2 of " + current.getId() + " restored to Interrupteur (" + tmp_inter.getId() + ")");
                }
            } else if (current instanceof Porte) {
                tmp_in = ((Not) current).getIn();
                if (tmp_in instanceof LazySonde) {
                    tmp_inter = tableSondes.getInterrupteur((LazySonde)  tmp_in);
                    ((Not) current).setIn(tmp_inter);
                    System.out.println("in of " + current.getId() + " restored to Interrupteur (" + tmp_inter.getId() + ")");
                }
            }
        }
    }
    
    public void resetSondes() {
        tableSondes.resetSondes();
    }
}


