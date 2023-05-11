package Main;

/**
 *  Interfície que serveix per a la comunicació de l'estructura
 */
public interface PerEsdeveniments {
    public void notificar(String s) throws InterruptedException;
}