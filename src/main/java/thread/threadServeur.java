package thread;

import coteServeur.Serveur;

import java.io.IOException;

public class threadServeur extends Thread {
    public threadServeur() {
    }

    @Override
    public void run() {
        try {
            Serveur.main(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
