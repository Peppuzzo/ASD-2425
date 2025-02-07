/**
 *
 */
package it.unicam.cs.asdl2425.pt1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luca Tesei (template) GIUSEPPE, CALABRESE
 * giusepp.calabrese@studenti.unicam.it (implementazione)
 *
 */
class AVLTreeSortTest {

  /**
   * @auhtor Giuseppe Calabrese
   */
    @Test
    final void testSort() {
      AVLTree<Integer> treeSort = new AVLTree<Integer>(35);

      treeSort.insert(13);
      treeSort.insert(14);
      treeSort.insert(14);
      treeSort.insert(15);
      treeSort.insert(15);
      treeSort.insert(16);
      treeSort.insert(17);
      treeSort.insert(18);
      treeSort.insert(18);
      List<Integer> list = treeSort.inOrderVisit();
      SortingAlgorithmResult<Integer> sort = new SortingAlgorithmResult<>(list, treeSort.getNumberOfNodes());

      List<Integer> listComparation = new ArrayList<Integer>();
      listComparation.add(13);
      listComparation.add(14);
      listComparation.add(14);
      listComparation.add(15);
      listComparation.add(15);
      listComparation.add(16);
      listComparation.add(17);
      listComparation.add(18);
      listComparation.add(18);
      listComparation.add(35);

      SortingAlgorithmResult<Integer> sorting = new SortingAlgorithmResult<Integer>(listComparation, treeSort.getNumberOfNodes());

      // Controllo che il numero di confronti sia identico
      assertEquals(sort.getCountCompare(), sorting.getCountCompare());
      // Controllo che le liste siano identiche
      assertEquals(sort.getL(), sorting.getL());
    }

    @Test
    final void testSortNull() {
      assertThrows(NullPointerException.class,
        () -> new AVLTree<Integer>(null), "La verifica è per Null");
    }
}
