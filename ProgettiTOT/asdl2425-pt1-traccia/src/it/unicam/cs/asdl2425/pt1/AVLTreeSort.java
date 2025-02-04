/**
 *
 */
package it.unicam.cs.asdl2425.pt1;

import java.util.ArrayList;
import java.util.List;

/**
 * Algoritmo di ordinamento che usa un albero AVL con molteplicità per ordinare
 * una lista di elementi. La strategia di realizzazione è semplice: si
 * inseriscono i valori da ordinare in un AVLTree e poi si fa una visita inOrder
 * per ottenere la lista ordinata di elementi.
 *
 * @author Luca Tesei (template) GIUSEPPE, CALABRESE
 *         giusepp.calabrese@studenti.unicam.it (implementazione)
 *
 */
public class AVLTreeSort<E extends Comparable<E>>
        implements SortingAlgorithm<E> {

    public SortingAlgorithmResult<E> sort(List<E> l) {
      if (l == null) {
        throw new NullPointerException("Impossibile eseguire il Sorting per liste null.");
      }
      if(l.isEmpty()){
        return new SortingAlgorithmResult<>(new ArrayList<>(), 0);
      }

      // Si crea un albero AVLTree per molteplicità
      AVLTree<E> treeSort = new AVLTree<>();
      int comparisons = 0;

      // Inseriamo gli elementi nell'AVLTree e contiamo i confronti
      for (E element : l) {
        comparisons += treeSort.insert(element);
      }

      // Recuperiamo gli elementi in ordine tramite una visita in-order
      List<E> list = treeSort.inOrderVisit();

      // Creiamo il risultato dell'ordinamento
      return new SortingAlgorithmResult<>(list, comparisons);

    }
    public String getName() {
        return "AVLTreeSort";
    }
}
