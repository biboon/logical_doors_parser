package circuits;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Sonde extends Composant {

    protected Composant connectedComponent;
    protected String componentIn;
    
    Sonde(Composant comp, String in) {
        connectedComponent = comp;
        componentIn = in;
    }
    
    public boolean getEtat() {
        System.out.println(componentIn + " de " + connectedComponent.getId() + ", true ou false ?");
        Scanner saisie = new Scanner(System.in);
        try {
            return saisie.nextBoolean();
        } catch (InputMismatchException e) {
            return this.getEtat();
        }
    }
}
