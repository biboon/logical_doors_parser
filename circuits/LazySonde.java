package circuits;

import java.util.Scanner;
import java.util.InputMismatchException;

public class LazySonde extends Sonde {

    protected Composant connectedComponent;
    protected String componentIn;
    protected boolean value;
    protected boolean isSet = false;
    
    LazySonde(Composant comp, String in) {
        super(comp, in);
    }
    
    public boolean getEtat() {
        if (!isSet) {
            value = super.getEtat();
            isSet = true;
        }
        return value;
    }
    
    public void reset(){
        isSet = false;
    }
}
