/**
 *
 */
package it.unicam.cs.asdl2425.pt1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Luca Tesei (template) GIUSEPPE, CALABRESE
 * giusepp.calabrese@studenti.unicam.it (implementazione)
 *
 */
class AVLTreeSortTest {

    @Test
    final void testSort() {
      
    }

    @Test
    final void testSortNull() {
      assertThrows(NullPointerException.class,
        () -> new AVLTree<Integer>(null), "La verifica è per Null");
    }

}
