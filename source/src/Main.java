import org.jpl7.*;

import java.net.URISyntaxException;

/**
 * Driver class that simply launches the GUI
 */
public class Main {
    public static void main(String[] args) throws URISyntaxException {
        launchGUI();
    }
    public static void launchGUI() throws URISyntaxException {
        new MedicalGUI();
    }
}