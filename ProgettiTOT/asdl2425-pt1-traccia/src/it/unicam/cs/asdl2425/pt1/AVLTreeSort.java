/**
 * 
 */
package it.unicam.cs.asdl2425.pt1;

import java.util.List;

/**
 * Algoritmo di ordinamento che usa un albero AVL con molteplicità per ordinare
 * una lista di elementi. La strategia di realizzazione è semplice: si
 * inseriscono i valori da ordinare in un AVLTree e poi si fa una visita inOrder
 * per ottenere la lista ordinata di elementi.
 * 
 * @author Luca Tesei (template) **INSERIRE NOME, COGNOME ED EMAIL
 *         xxxx@studenti.unicam.it DELLO STUDENTE** (implementazione)
 *
 */
public class AVLTreeSort<E extends Comparable<E>>
        implements SortingAlgorithm<E> {

    public SortingAlgorithmResult<E> sort(List<E> l) {
        // TODO implementare
        return null;
    }

    public String getName() {
        return "AVLTreeSort";
    }

}
