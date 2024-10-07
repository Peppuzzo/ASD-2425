/**
 *
 */
package it.unicam.cs.asdl2425.es1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author Template: Luca Tesei, Implementation: Collettiva da Esercitazione a
 *         Casa
 *
 */
class EquazioneSecondoGradoModificabileConRisolutoreTest {
    /*
     * Costante piccola per il confronto di due numeri double
     */
    static final double EPSILON = 1.0E-15;

    @Test
    final void testEquazioneSecondoGradoModificabileConRisolutore() {
        // controllo che il valore 0 su a lanci l'eccezione
        assertThrows(IllegalArgumentException.class,
                () -> new EquazioneSecondoGradoModificabileConRisolutore(0, 1,
                        1));
        // devo controllare che comunque nel caso normale il costruttore
        // funziona
        EquazioneSecondoGradoModificabileConRisolutore eq = new EquazioneSecondoGradoModificabileConRisolutore(
                1, 1, 1);
        // Controllo che all'inizio l'equazione non sia risolta
        assertFalse(eq.isSolved());
    }

    @Test
    final void testGetA() {
        double x = 10;
        EquazioneSecondoGradoModificabileConRisolutore e1 = new EquazioneSecondoGradoModificabileConRisolutore(
                x, 1, 1);
        // controllo che il valore restituito sia quello che ho messo
        // all'interno
        // dell'oggetto
        assertTrue(x == e1.getA());
        // in generale si dovrebbe usare assertTrue(Math.abs(x -
        // e1.getA())<EPSILON) ma in
        // questo caso il valore che testiamo non ha subito manipolazioni quindi
        // la sua rappresentazione sarà la stessa di quella inserita nel
        // costruttore senza errori di approssimazione

    }

    @Test
    final void testSetA() {
      // credo un oggetto e2 per testare se il valore del coefficiente a e' stato settato
      EquazioneSecondoGradoModificabileConRisolutore e2 = new EquazioneSecondoGradoModificabileConRisolutore(
        10, 20, 30);
      e2.setA(15);
      assertEquals(e2.getA() , 15);
    }

    @Test
    final void testGetB() {
        double y = 10;
        // creo un oggetto per poter testare la uguaglianza con il valore del parametro b
      EquazioneSecondoGradoModificabileConRisolutore e3 = new EquazioneSecondoGradoModificabileConRisolutore(
        10, y, 30);
      assertTrue(y == e3.getB());
    }

    @Test
    final void testSetB() {
        // verifico se il test per il parametro b verra' settato
      EquazioneSecondoGradoModificabileConRisolutore e4 = new EquazioneSecondoGradoModificabileConRisolutore(
        10, 20, 30);
      e4.setB(30);
      assertEquals(e4.getB(), 30);
    }

    @Test
    final void testGetC() {
        double z = 45;
        EquazioneSecondoGradoModificabileConRisolutore e5 = new EquazioneSecondoGradoModificabileConRisolutore(
          10, 25, z);
        //assertEquals(z == e5.getC());
        assertTrue(z == e5.getC());
    }

    @Test
    final void testSetC() {
      // creo un oggetto per verifica se il valore di c verra' settato
      EquazioneSecondoGradoModificabileConRisolutore e6 = new EquazioneSecondoGradoModificabileConRisolutore(
        10, 11, 22);
      e6.setC(55);
      assertEquals(e6.getC(), 55);
    }

    @Test
    final void testIsSolved() {
      EquazioneSecondoGradoModificabileConRisolutore e7 = new EquazioneSecondoGradoModificabileConRisolutore(
        1, 2, 3);
      e7.solve();
      // verifico se l'equivalenza per il test con una soluzione di equazione e' valida
      assertEquals(e7.isSolved(), true);

    }

    @Test
    final void testSolve() {
        EquazioneSecondoGradoModificabileConRisolutore e8 = new EquazioneSecondoGradoModificabileConRisolutore(
                10, -5, 3);
        // controllo semplicemente che la chiamata a solve() non generi errori
        e8.solve();
        // i test con i valori delle soluzioni vanno fatti nel test del metodo
        // getSolution()
    }

    @Test
    final void testGetSolution() {
      // verifico se date due soluzioni identiche (con stssi parametri) il metoo GetSolution funzioni correttamente.
        EquazioneSecondoGradoModificabileConRisolutore e9 = new EquazioneSecondoGradoModificabileConRisolutore(
          10, 20, 30);
        EquazioneSecondoGradoModificabileConRisolutore e10 = new EquazioneSecondoGradoModificabileConRisolutore(
          10, 20, 30);
        
          e9.solve();
          e10.solve();
        assertEquals(e9.getSolution(), e10.getSolution());
    }

}
