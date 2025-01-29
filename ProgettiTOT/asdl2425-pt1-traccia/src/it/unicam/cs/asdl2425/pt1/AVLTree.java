package it.unicam.cs.asdl2425.pt1;

import java.util.List;

/**
 * Un AVLTree è un albero binario di ricerca che si mantiene sempre bilanciato.
 * In questa particolare classe si possono inserire elementi ripetuti di tipo
 * {@code E} e non si possono inserire elementi {@code null}.
 *
 * @param E
 *              il tipo degli elementi che possono essere inseriti in questo
 *              AVLTree. La classe {@code E} deve avere un ordinamento naturale
 *              definito tra gli elementi.
 *
 * @author Luca Tesei (template) GIUSEPPE, CALABRESE
 *         giusepp.calabrese@studenti.unicam.it (implementazione)
 *
 */
public class AVLTree<E extends Comparable<E>> {

    // puntatore al nodo radice, se questo puntatore è null allora questo
    // AVLTree è vuoto
    private AVLTreeNode root;

    // Numero di elementi inseriti in questo AVLTree, comprese le ripetizioni
    private int size;

    // Numero di nodi in questo AVLTree
    private int numberOfNodes;

    /**
     * Costruisce un AVLTree vuoto.
     */
    public AVLTree() {
      this.root = null;
      this.size = 0;
      this.numberOfNodes = 0;
    }

    /**
     * Costruisce un AVLTree che consiste solo di un nodo radice.
     *
     * @param rootElement
     *                        l'informazione associata al nodo radice
     * @throws NullPointerException
     *                                  se l'elemento passato è null
     */
    public AVLTree(E rootElement) {
        // TODO implementare
      if(rootElement == null)
        throw new NullPointerException("La radice dell'albero non può essere NULL!");
      // creazione della radice con l'informazione passata
      this.setRoot(new AVLTreeNode(rootElement));
      this.size++;
      this.numberOfNodes++;
    }

    /**
     * Determina se questo AVLTree è vuoto.
     *
     * @return true, se questo AVLTree è vuoto.
     */
    public boolean isEmpty() {
        return (this.root == null);
    }

    /**
     * Restituisce il numero di elementi contenuti in questo AVLTree. In caso di
     * elementi ripetuti essi vengono contati più volte.
     *
     * @return il numero di elementi di tipo {@code E} presenti in questo
     *         AVLTree.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Restituisce il numero di nodi in questo AVLTree.
     *
     * @return il numero di nodi in questo AVLTree.
     */
    public int getNumberOfNodes() {
        return this.numberOfNodes;
    }

    /**
     * Restituisce l'altezza di questo AVLTree. Se questo AVLTree è vuoto viene
     * restituito il valore -1.
     *
     * @return l'altezza di questo AVLTree, -1 se questo AVLTree è vuoto.
     */
    public int getHeight() {
      if(this.isEmpty()){
        return -1;
      }
      // + 1 per la radice
      return 1 + getMaxChildHeight(this.root);
    }

    /**
     * @return the root
     */
    public AVLTreeNode getRoot() {
        return root;
    }

    /**
     * @param root
     *                 the root to set
     */
    public void setRoot(AVLTreeNode root) {
        this.root = root;
    }

    /**
     * Determina se questo AVLTree è bilanciato. L'albero è bilanciato se tutti
     * i nodi hanno un fattore di bilanciamento compreso tra -1 e +1. Il fattore
     * di bilanciamento di un nodo si definisce come l'altezza del sottoalbero
     * sinistro meno l'altezza del sottoalbero destro.
     *
     * @return true, se il fattore di bilanciamento di tutti i nodi dell'albero
     *         è compreso tra -1 e +1.
     */
    public boolean isBalanced() {
      return isBalancedTree(getRoot()) != -1;
    }

    /**
     * Inserisce un nuovo elemento in questo AVLTree. Se l'elemento è già
     * presente viene incrementato il suo numero di occorrenze.
     *
     * @param el
     *               l'elemento da inserire.
     * @return il numero di confronti tra elementi della classe {@code E}
     *         effettuati durante l'inserimento
     * @throws NullPointerException
     *                                  se l'elemento {@code el} è null
     */
    public int insert(E el) {
        // TODO implementare e usare il metodo corrispondente in AVLTreeNode
      if(el == null)
        throw new NullPointerException("Impossibile inserire elementi nulli!");


      return 0;
    }

    /**
     * Determina se questo AVLTree contiene un certo elemento.
     *
     * @param el
     *               l'elemento da cercare
     * @return true se l'elemento è presente in questo AVLTree, false
     *         altrimenti.
     * @throws NullPointerException
     *                                  se l'elemento {@code el} è null
     */
    public boolean contains(E el) {
        // TODO implementare e usare il metodo corrispondente (search) in
        // AVLTreeNode
      if(el == null)
        throw new NullPointerException("WARNING: Impossibile effettuare la ricerca per elementi NULL!");
      // ricerca dell'elemento
      return (this.getRoot().search(el) != null);
    }

    /**
     * Determina se un elemento è presente in questo AVLTree e ne restituisce il
     * relativo nodo.
     *
     * @param el
     *               l'elemento da cercare
     * @return il nodo di questo AVLTree che contiene l'elemento {@code el} e la
     *         sua molteplicità, oppure {@code null} se l'elemento {@code el}
     *         non è presente in questo AVLTree.
     * @throws NullPointerException
     *                                  se l'elemento {@code el} è null
     *
     */
    public AVLTreeNode getNodeOf(E el) {
        // TODO implementare e usare il metodo corrispondente (search) in
        // AVLTreeNode
      if(el == null){
        throw new NullPointerException("warning: impossibile effettuare la ricerca" +
          "per valori di tipo NULL!");
      }
        return null;
    }

    /**
     * Determina il numero di occorrenze di un certo elemento in questo AVLTree.
     *
     * @param el
     *               l'elemento di cui determinare il numero di occorrenze
     * @return il numero di occorrenze dell'elemento {@code el} in questo
     *         AVLTree, zero se non è presente.
     * @throws NullPointerException
     *                                  se l'elemento {@code el} è null
     */
    public int getCount(E el) {
        // TODO implementare e usare il metodo corrispondente in AVLTreeNode
      if(el == null)
        throw new NullPointerException("Impossibile cercare il numero di occorrenze per valori NULL!");

      return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String descr = "AVLTree [root=" + root.el.toString() + ", size=" + size
                + ", numberOfNodes=" + numberOfNodes + "]\n";
        return descr + this.root.toString();
    }

    /**
     * Restituisce la lista degli elementi contenuti in questo AVLTree secondo
     * l'ordine determinato dalla visita in-order. Per le proprietà dell'albero
     * AVL la lista ottenuta conterrà gli elementi in ordine crescente rispetto
     * all'ordinamento naturale della classe {@code E}. Nel caso di elementi
     * ripetuti, essi appaiono più volte nella lista consecutivamente.
     *
     * @return la lista ordinata degli elementi contenuti in questo AVLTree,
     *         tenendo conto della loro molteplicità.
     */
    public List<E> inOrderVisit() {
        // TODO implementare
        return null;
    }

    /**
     * Restituisce l'elemento minimo presente in questo AVLTree.
     *
     * @return l'elemento minimo in questo AVLTree, {@code null} se questo
     *         AVLTree è vuoto.
     */
    public E getMinimum() {
        // TODO implementare e usare il metodo corrispondente in AVLTreeNode
        return null;
    }

    /**
     * Restituisce l'elemento massimo presente in questo AVLTree.
     *
     * @return l'elemento massimo in questo AVLTree, {@code null} se questo
     *         AVLTree è vuoto.
     */
    public E getMaximum() {
        // TODO implementare e usare il metodo corrispondente in AVLTreeNode
        return null;
    }

    /**
     * Restituisce l'elemento <b>strettamente</b> successore, in questo AVLTree,
     * di un dato elemento. Si richiede che l'elemento passato sia presente
     * nell'albero.
     *
     * @param el
     *               l'elemento di cui si chiede il successore
     * @return l'elemento <b>strettamente</b> successore, rispetto
     *         all'ordinamento naturale della classe {@code E}, di {@code el} in
     *         questo AVLTree, oppure {@code null} se {@code el} è l'elemento
     *         massimo.
     * @throws NullPointerException
     *                                      se l'elemento {@code el} è null
     * @throws IllegalArgumentException
     *                                      se l'elemento {@code el} non è
     *                                      presente in questo AVLTree.
     */
    public E getSuccessor(E el) {
        // TODO implementare e usare il metodo corrispondente in AVLTreeNode
        return null;
    }

    /**
     * Restituisce l'elemento <b>strettamente</b> predecessore, in questo
     * AVLTree, di un dato elemento. Si richiede che l'elemento passato sia
     * presente nell'albero.
     *
     * @param el
     *               l'elemento di cui si chiede il predecessore
     * @return l'elemento <b>strettamente</b> predecessore rispetto
     *         all'ordinamento naturale della classe {@code E}, di {@code el} in
     *         questo AVLTree, oppure {@code null} se {@code el} è l'elemento
     *         minimo.
     * @throws NullPointerException
     *                                      se l'elemento {@code el} è null
     * @throws IllegalArgumentException
     *                                      se l'elemento {@code el} non è
     *                                      presente in questo AVLTree.
     */
    public E getPredecessor(E el) {
        // TODO implementare e usare il metodo corrispondente in AVLTreeNode
        return null;
    }

    /**
     * Gli elementi di questa classe sono i nodi di un AVLTree, che è la classe
     * "involucro" della struttura dati.
     *
     * @author Luca Tesei (Template)
     *
     */
    public class AVLTreeNode {
        // etichetta del nodo
        private E el;

        // molteplicità dell'elemento el
        private int count;

        // sottoalbero sinistro
        private AVLTreeNode left;

        // sottoalbero destro
        private AVLTreeNode right;

        // genitore del nodo, null se questo nodo è la radice dell'AVLTree
        private AVLTreeNode parent;

        // altezza del sottoalbero avente questo nodo come radice
        private int height;

        /**
         * Create an AVLTreeNode as a root leaf
         *
         * @param el
         *               the element
         */
        public AVLTreeNode(E el) {
            this.el = el;
            this.count = 1;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.height = 0;
        }

        /**
         * Create an AVLTreeNode node containing one element to be considered
         * child of the given parent.
         *
         * @param el
         *                   the element
         * @param parent
         *                   the parent of the node
         */
        public AVLTreeNode(E el, AVLTreeNode parent) {
            this.el = el;
            this.count = 1;
            this.left = null;
            this.right = null;
            this.parent = parent;
            this.height = 0;
        }

        /**
         * Restituisce il nodo predecessore di questo nodo. Si suppone che
         * esista un nodo predecessore, cioè che questo nodo non contenga
         * l'elemento minimo del sottoalbero di cui è radice.
         *
         * @return il nodo predecessore
         */
        public AVLTreeNode getPredecessor() {
            // TODO implementare
            return null;
        }

        /**
         * Restituisce il nodo successore di questo nodo. Si suppone che esista
         * un nodo successore, cioè che questo nodo non contenga l'elemento
         * massimo del sottoalbero di cui è radice.
         *
         * @return il nodo successore
         */
        public AVLTreeNode getSuccessor() {
            // TODO implementare
            return null;
        }

        /**
         * Restituisce il nodo contenente l'elemento massimo del sottoalbero di
         * cui questo nodo è radice.
         *
         * @return il nodo contenente l'elemento massimo del sottoalbero di cui
         *         questo nodo è radice.
         */
        public AVLTreeNode getMaximum() {
            // TODO implementare
          // memorizzo il nodo corrente
            AVLTreeNode nodeCurrent = this;
            while(nodeCurrent.getRight() != null){
              nodeCurrent = nodeCurrent.getRight();
            }
            // il nodo più a destra dell'albero, ovvero il suo MAX
            return nodeCurrent;
        }

        /**
         * Restituisce il nodo contenente l'elemento minimo del sottoalbero di
         * cui questo nodo è radice.
         *
         * @return il nodo contenente l'elemento minimo del sottoalbero di cui
         *         questo nodo è radice.
         */
        public AVLTreeNode getMinimum() {
          AVLTreeNode current = this;
          while(current.getLeft() != null){
            current = current.getLeft();
          }
            return current;
        }

        /**
         * Determina se questo è un nodo foglia.
         *
         * @return true se questo nodo non ha figli, false altrimenti.
         */
        public boolean isLeaf() {
          return this.getLeft() == null && this.getRight() == null;
        }

        /**
         * Restituisce l'altezza del sottoalbero la cui radice è questo nodo.
         *
         * @return l'altezza del sotto albero la cui radice è questo nodo.
         */
        public int getHeight() {
            return this.height;
        }

        /**
         * Aggiorna l'altezza del sottoalbero la cui radice è questo nodo
         * supponendo che l'altezza dei nodi figli sia già stata aggiornata.
         */
        public void updateHeight() {
          // Caso base: non avendo sotto-alberi, l'altezza è 0.
          if(this.isLeaf()){
            this.setHeight(0);
          }
          // controllo se la radice gha entrambi i figli,
          // in quel caso si prenda il max tra di loro + il nodo radice
          else if(this.getLeft() != null && this.getRight() != null){
            this.setHeight(Math.max(this.getLeft().getHeight(), this.getRight().getHeight()) + 1);
          }
          // caso in cui ho solo il sotto-albero sinistro
          else if(this.getRight() == null){
            this.setHeight(this.getLeft().getHeight() + 1);
          }
          // caso in cui ho solo il sotto-albero dentro
          else {
            this.setHeight(this.getRight().getHeight() + 1);
          }
        }

        /**
         * Determina il fattore di bilanciamento di questo nodo. Se il nodo è
         * una foglia il fattore di bilanciamento è 0. Se il nodo ha solo il
         * figlio sinistro allora il fattore di bilanciamento è l'altezza del
         * figlio sinistro + 1. Se il nodo ha solo il figlio destro allora il
         * fattore di bilanciamento è l'altezza del figlio destro + 1,
         * moltiplicata per -1. Se il nodo ha entrambi i figli il fattore di
         * bilanciamento è l'altezza del figlio sinistro meno l'altezza del
         * figlio destro.
         *
         * @return il fattore di bilanciamento di questo nodo.
         */
        public int getBalanceFactor() {
            // TODO implementare

          int leftHeight = (this.left != null) ? this.left.getHeight() : -1;
          int rightHeight = (this.right != null) ? this.right.getHeight() : -1;

          return leftHeight - rightHeight;
        }

        /**
         * Determina se questo nodo e tutti i suoi discendenti hanno un fattore
         * di bilanciamento compreso tra -1 e 1.
         *
         * @return true se questo nodo e tutti i suoi discendenti sono
         *         bilanciati, false altrimenti.
         */
        public boolean isBalanced() {
            // TODO implementare
          int factor = getBalanceFactor();
          if(factor < -1 || factor > 1){
            return false;
          }

          // verifica del bilanciamento del sotto-albero sinistro
          if(this.getLeft() != null && !this.getLeft().isBalanced()){
            return false;
          }
          // verifica del bilanciamento del sotto-albero destro
          if(this.getRight() != null && !this.getRight().isBalanced()){
            return false;
          }
          // tutti i sotto-alberi (e i suoi discendenti) sono bilanciati
          return true;
        }

        /**
         * @return the el
         */
        public E getEl() {
            return el;
        }

        /**
         * @param el
         *               the el to set
         */
        public void setEl(E el) {
            this.el = el;
        }

        /**
         * @return the count
         */
        public int getCount() {
            return count;
        }

        /**
         * @param count
         *                  the count to set
         */
        public void setCount(int count) {
            this.count = count;
        }

        /**
         * @return the left
         */
        public AVLTreeNode getLeft() {
            return left;
        }

        /**
         * @param left
         *                 the left to set
         */
        public void setLeft(AVLTreeNode left) {
            this.left = left;
        }

        /**
         * @return the right
         */
        public AVLTreeNode getRight() {
            return right;
        }

        /**
         * @param right
         *                  the right to set
         */
        public void setRight(AVLTreeNode right) {
            this.right = right;
        }

        /**
         * @return the parent
         */
        public AVLTreeNode getParent() {
            return parent;
        }

        /**
         * @param parent
         *                   the parent to set
         */
        public void setParent(AVLTreeNode parent) {
            this.parent = parent;
        }

        /**
         * @param height
         *                   the height to set
         */
        public void setHeight(int height) {
            this.height = height;
        }

        /*
         * (non-Javadoc)
         *
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            StringBuffer s = new StringBuffer();
            s.append("(");
            s.append(this.el);
            s.append(", ");
            if (this.left == null)
                s.append("()");
            else
                s.append(this.left.toString());
            s.append(", ");
            if (this.right == null)
                s.append("()");
            else
                s.append(this.right.toString());
            s.append(")");
            return s.toString();
        }

        /**
         * Ricerca un elemento a partire da questo nodo.
         *
         * @param el
         *               the element to search for
         *
         * @return the node containing the element or null if the element is not
         *         found
         */
        public AVLTreeNode search(E el) {
          // effettua una visita inorder nell'albero per cercare un nodo dal valore pari a el,
          // se non lo trova restituisce null
          if(this.getEl().compareTo(el) > 0 && getLeft() != null) return getLeft().search(el);
          if(this.getEl().equals(el)) return this;
          if(this.getEl().compareTo(el) < 0 && getRight() != null) return getRight().search(el);

          return null;
        }

        /**
         * Inserisce un elemento nell'albero AVL a partire da questo nodo. Se
         * l'elemento è già presente ne aumenta semplicemente la molteplicità di
         * uno. Se l'elemento non è presente aggiunge un nodo nella opportuna
         * posizione e poi procede al ribilanciamento dell'albero se
         * l'inserimento del nuovo nodo provoca uno sbilanciamento in almeno un
         * nodo.
         *
         * @param el
         *               l'elemento da inserire
         *
         * @return il numero di confronti tra elementi della classe {@code E}
         *         effettuati durante l'inserimento.
         */
        public int insert(E el) {
            // TODO implementare
            return 0;
        }

        // TODO inserire i metodi per i quattro tipi di rotazioni
        // sinistra-sinistra, sinistra-destra, destra-destra e destra-sinistra
        // come metodi private con gli opportuni parametri.


      /**
       * La rotazione left_left-Rotation consiste nella rotazione del sotto
       * albero destro quando la radice (ovvero l'albero sinistro)
       * è minore rispetto al figlio destro
       *
       * @author Giuseppe Calabrese
       * @throws IllegalArgumentException
       *            se il nodo passato e il suo figlio destro sono null
       */
      private void left_left_Rotation() {
        if (this.getRight() == null) {
          throw new IllegalArgumentException("Impossibile effettuare la rotazione: nodo o figlio destro null.");
        }

        // Si prende il figlio destro del nodo
        AVLTreeNode rightChild = this.getRight();

        // Si sposta il sotto-albero sinistro di rightChild come figlio destro di node
        this.setRight(rightChild.getLeft());
        if (rightChild.getLeft() != null) {
          rightChild.getLeft().setParent(this);
        }

        // Si aggiorna il parent di rightChild
        rightChild.setParent(this.getParent());
        if (this.getParent() == null) {
          // Se il nodo è la radice, aggiorniamo la radice
          setRoot(rightChild);
        } else if (this == this.getParent().getLeft()) {
          // Se il nodo è il figlio sinistro del suo genitore
          this.getParent().setLeft(rightChild);
        } else {
          // Se il nodo è il figlio destro del suo genitore
          this.getParent().setRight(rightChild);
        }

        // Si collega il node come figlio sinistro di rightChild
        rightChild.setLeft(this);
        this.setParent(rightChild);
        updateHeight();
        rightChild.updateHeight();
      }

      /**
       * La rotazione right-right_Rotation (rotazione a destra) consiste nello spostare il sotto-albero
       * sinistro del nodo corrente per bilanciare un albero sbilanciato a sinistra.
       * Questo accade quando il sotto-albero sinistro del nodo corrente è più alto
       * rispetto al sotto-albero destro, con un fattore di bilanciamento fuori dai limiti.

       * @author Giuseppe Calabrese
       * @throws IllegalArgumentException
       *            se i suoi figli sono null
       */
      private void right_right_Rotation() {
        if (this.getLeft() == null) {
          throw new IllegalArgumentException("Impossibile effettuare la rotazione con parametri null.");
        }

        // Recupera il figlio sinistro del nodo
        AVLTreeNode leftChild = this.getLeft();

        // Sposta il sotto-albero destro di leftChild come sotto-albero sinistro di node
        this.setLeft(leftChild.getRight());
        if (leftChild.getRight() != null) {
          leftChild.getRight().setParent(this);
        }
        // Aggiorna il genitore di leftChild
        leftChild.setParent(this.getParent());
        if (this.getParent() == null) {
          // Se il nodo è la radice, aggiorna la radice con leftChild
          setRoot(leftChild);
        } else if (this == this.getParent().getRight()) {
          // Se il nodo era il figlio destro del suo genitore
          this.getParent().setRight(leftChild);
        } else {
          // Se il nodo era il figlio sinistro del suo genitore
          this.getParent().setLeft(leftChild);
        }

        // Collega node come figlio destro di leftChild
        leftChild.setRight(this);
        this.setParent(leftChild);

        // Aggiorna le altezze dei nodi coinvolti
        updateHeight();
        leftChild.updateHeight();
      }

      /**
       * La rotazione left_right_Rotation bilancia un nodo sbilanciato
       * quando il fattore di bilanciamento è maggiore di 1 e il figlio sinistro ha un fattore
       * di bilanciamento minore di 0.
       *
       * @author Giuseppe Calabrese
       * @throws IllegalArgumentException
       *              se i suoi figli sono null
       */
      private void left_right_Rotation(){
        if(this.getLeft() == null || this.getLeft().getRight() == null){
          throw new IllegalArgumentException("Impossibile roteare nodi per valori nulli.");
        }
        this.getLeft().left_left_Rotation();
        right_right_Rotation();
      }

      /**
       * La rotazione right_left_Rotation bilancia un nodo sbilanciato
       * quando il fattore di bilanciamento è maggiore di 1 e il figlio destro ha un fattore
       * di bilanciamento minore di 0.
       *
       * @author Giuseppe Calabrese
       * @throws IllegalArgumentException
       *              se i suoi figli sono null
       */
      private void right_left_Rotation(){
        if(this.getRight() == null || this.getRight().getLeft() == null){
          throw new IllegalArgumentException("Impossibile roteare nodi per valori nulli.");
        }
        this.getRight().right_right_Rotation();
        left_left_Rotation();
      }
    }

  /**
   * Metodo per il calcolo ricorsivo dell'altezza per il MAX tra i figli sinistro e destro
   *
   * @author Giuseppe Calabrese
   * @param node il nodo a cui riferirsi
   * @return il max tra i due sotto alberi sinistro e destro
   */
  private int getMaxChildHeight(AVLTreeNode node){
      int leftMax = (node.getLeft() != null) ? node.getLeft().getHeight() : -1;
      int rightMax = (node.getRight() != null) ? node.getRight().getHeight() : -1;

      return Math.max(leftMax, rightMax);
  }

  /**
   * Verifica se il sotto-alberi sottostante nel nodo specificato è bilanciato.
   *
   * @author Giuseppe Calabrese
   * @param node il nodo che rappresenta la radice del sotto-albero da verificare.
   * @return un intero che rappresenta l'altezza del sotto-albero se bilanciato, oppure no
   */
  private int isBalancedTree(AVLTreeNode node) {
    if (node == null) {
      return 0;  // Un nodo null è bilanciato e ha altezza 0
    }

    // altezza del sotto-albero sinistro per quel nodo
    int leftHeight = isBalancedTree(node.getLeft());
    //altezza del sotto-albero destro per il nodo passato
    int rightHeight = isBalancedTree(node.getRight());

    if (leftHeight == -1 || rightHeight == -1) {
      return -1;  // Se un sotto-albero non è bilanciato, ritorna -1
    }

    if (Math.abs(leftHeight - rightHeight) > 1) {
      return -1;  // Se la differenza di altezza è maggiore di 1, non è bilanciato
    }

    return Math.max(leftHeight, rightHeight) + 1;  // Restituisce l'altezza del nodo
  }

}
