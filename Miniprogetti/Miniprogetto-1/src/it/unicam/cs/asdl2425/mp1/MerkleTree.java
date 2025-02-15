package it.unicam.cs.asdl2425.mp1;

import java.util.*;

/**
 * Un Merkle Tree, noto anche come hash tree binario, è una struttura dati per
 * verificare in modo efficiente l'integrità e l'autenticità dei dati
 * all'interno di un set di dati più ampio. Viene costruito eseguendo l'hashing
 * ricorsivo di coppie di dati (valori hash crittografici) fino a ottenere un
 * singolo hash root. In questa implementazione la verifica di dati avviene
 * utilizzando hash MD5.
 *
 * @author Luca Tesei, Marco Caputo (template) GIUSEPPE, CALABRESE
 *         giusepp.calabrese@studenti.unicam.it (implementazione)
 *
 * @param <T>
 *                il tipo di dati su cui l'albero è costruito.
 */
public class MerkleTree<T> {
    /**
     * Nodo radice dell'albero.
     */
    private final MerkleNode root;

    /**
     * Larghezza dell'albero, ovvero il numero di nodi nell'ultimo livello.
     */
    private final int width;

  /**
   * @autor Giuseppe Calabrese
   * <p>
   * Una lista contenente tutte le sue foglie a
   * partire dagli hash, in un certo ordine
   */
    private ArrayList<String> hashLeaves;

    /**
     * Costruisce un albero di Merkle a partire da un oggetto HashLinkedList,
     * utilizzando direttamente gli hash presenti nella lista per costruire le
     * foglie. Si noti che gli hash dei nodi intermedi dovrebbero essere
     * ottenuti da quelli inferiori concatenando hash adiacenti due a due e
     * applicando direttmaente la funzione di hash MD5 al risultato della
     * concatenazione in bytes.
     *
     * @param hashList
     *                     un oggetto HashLinkedList contenente i dati e i
     *                     relativi hash.
     * @throws IllegalArgumentException
     *                                      se la lista è null o vuota.
     */
    public MerkleTree(HashLinkedList<T> hashList) {
      if(hashList == null || hashList.getSize() == 0)
        throw new IllegalArgumentException("Parametri passati non validi per la generazione" +
          "dell'albero di Merkle");
      this.root = rootTree(hashList); // Calcolo del nodo che rappresenta l'Hash originale
      this.width = hashList.getSize(); // La larghezza dell'albero nel momento della sua creazione
    }

    /**
     * Restituisce il nodo radice dell'albero.
     *
     * @return il nodo radice.
     */
    public MerkleNode getRoot() {
        return root;
    }

    /**
     * Restituisce la larghezza dell'albero.
     *
     * @return la larghezza dell'albero.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Restituisce l'altezza dell'albero.
     *
     * @return l'altezza dell'albero.
     */
    public int getHeight() {
      // Altezza dell'albero ovvero la sua più profondità fino
      // all'ultima foglia
      return (int) Math.ceil(Math.log(this.width) / Math.log(2));
    }

    /**
     * Restituisce l'indice di un dato elemento secondo l'albero di Merkle
     * descritto da un dato branch. Gli indici forniti partono da 0 e
     * corrispondono all'ordine degli hash corrispondenti agli elementi
     * nell'ultimo livello dell'albero da sinistra a destra. Nel caso in cui il
     * branch fornito corrisponda alla radice di un sottoalbero, l'indice
     * fornito rappresenta un indice relativo a quel sottoalbero, ovvero un
     * offset rispetto all'indice del primo elemento del blocco di dati che
     * rappresenta. Se l'hash dell'elemento non è presente come dato
     * dell'albero, viene restituito -1.
     *
     * @param branch
     *                   la radice dell'albero di Merkle.
     * @param data
     *                   l'elemento da cercare.
     * @return l'indice del dato nell'albero; -1 se l'hash del dato non è
     *         presente.
     * @throws IllegalArgumentException
     *                                      se il branch o il dato sono null o
     *                                      se il branch non è parte
     *                                      dell'albero.
     */
    public int getIndexOfData(MerkleNode branch, T data) {
      if(branch == null || data == null || !(validateBranch(branch)))
        throw new IllegalArgumentException("Parametri passati per l'indice non validi");

      // L'hash dell'elemento da dover cercare
      String checkData = HashUtil.dataToHash(data);

      // Controllo ricorsivamente se è presente in un sotto-albero (sinistro o destro)
      return checkIndexBranch(branch, checkData, 0);
    }

    /**
     * Restituisce l'indice di un elemento secondo questo albero di Merkle. Gli
     * indici forniti partono da 0 e corrispondono all'ordine degli hash
     * corrispondenti agli elementi nell'ultimo livello dell'albero da sinistra
     * a destra (e quindi l'ordine degli elementi forniti alla costruzione). Se
     * l'hash dell'elemento non è presente come dato dell'albero, viene
     * restituito -1.
     *
     * @param data
     *                 l'elemento da cercare.
     * @return l'indice del dato nell'albero; -1 se il dato non è presente.
     * @throws IllegalArgumentException
     *                                      se il dato è null.
     */
    public int getIndexOfData(T data) {
      if(data == null)
        throw new IllegalArgumentException("Non è consentito passare valori null!");

      // L'hash del singolo nodo calcolato tramite L'algoritmo MD5
      String validateHash = HashUtil.dataToHash(data);

      //Controllo se il dato è presente coincide con una delle mie foglie
      for(int i = 0; i < this.hashLeaves.size(); i++){
        if(this.hashLeaves.get(i).equals(validateHash)){
          return i;
        }
      }
      return -1;
    }

    /**
     * Sottopone a validazione un elemento fornito per verificare se appartiene
     * all'albero di Merkle, controllando se il suo hash è parte dell'albero
     * come hash di un nodo foglia.
     *
     * @param data
     *                 l'elemento da validare
     * @return true se l'hash dell'elemento è parte dell'albero; false
     *         altrimenti.
     */
    public boolean validateData(T data) {
      if(data == null)
        throw new NullPointerException("Il parametro passato è null!");

      String dataHash = HashUtil.dataToHash(data);

      // Confronto per la validazione dell'hash del dato
      for (String hash : this.hashLeaves) {
        if (hash.equals(dataHash)) {
          return true;
        }
      }
      return false;
    }

    /**
     * Sottopone a validazione un dato sottoalbero di Merkle, corrispondente
     * quindi a un blocco di dati, per verificare se è valido rispetto a questo
     * albero e ai suoi hash. Un sottoalbero è valido se l'hash della sua radice
     * è uguale all'hash di un qualsiasi nodo intermedio di questo albero. Si
     * noti che il sottoalbero fornito può corrispondere a una foglia.
     *
     * @param branch
     *                   la radice del sottoalbero di Merkle da validare.
     * @return true se il sottoalbero di Merkle è valido; false altrimenti.
     */
    public boolean validateBranch(MerkleNode branch) {

      // In questo caso non serve prendere MD5 perché essendo un branch
      // è già calcolato di suo
      return checkHashTree(this.root, branch.getHash());
    }

    /**
     * Sottopone a validazione un dato albero di Merkle per verificare se è
     * valido rispetto a questo albero e ai suoi hash. Grazie alle proprietà
     * degli alberi di Merkle, ciò può essere fatto in tempo costante.
     *
     * @param otherTree
     *                      il nodo radice dell'altro albero di Merkle da
     *                      validare.
     * @return true se l'altro albero di Merkle è valido; false altrimenti.
     * @throws IllegalArgumentException
     *                                      se l'albero fornito è null.
     */
    public boolean validateTree(MerkleTree<T> otherTree) {
      if(otherTree == null)
        throw new IllegalArgumentException("Impossibile validare un albero di Merkle.");

      // La verifica effettiva dell'albero di Merkle tramite i due root degli alberi
      return this.getRoot().getHash().equals(otherTree.getRoot().getHash());
    }

    /**
     * Trova gli indici degli elementi di dati non validi (cioè con un hash
     * diverso) in un dato Merkle Tree, secondo questo Merkle Tree. Grazie alle
     * proprietà degli alberi di Merkle, ciò può essere fatto confrontando gli
     * hash dei nodi interni corrispondenti nei due alberi. Ad esempio, nel caso
     * di un singolo dato non valido, verrebbe percorso un unico cammino di
     * lunghezza pari all'altezza dell'albero. Gli indici forniti partono da 0 e
     * corrispondono all'ordine degli elementi nell'ultimo livello dell'albero
     * da sinistra a destra (e quindi l'ordine degli elementi forniti alla
     * costruzione). Se l'albero fornito ha una struttura diversa, possibilmente
     * a causa di una quantità diversa di elementi con cui è stato costruito e,
     * quindi, non rappresenta gli stessi dati, viene lanciata un'eccezione.
     *
     * @param otherTree
     *                      l'altro Merkle Tree.
     * @throws IllegalArgumentException
     *                                      se l'altro albero è null o ha una
     *                                      struttura diversa.
     * @return l'insieme di indici degli elementi di dati non validi.
     */
    public Set<Integer> findInvalidDataIndices(MerkleTree<T> otherTree) {
      if(otherTree == null || this.hashLeaves.size() != otherTree.hashLeaves.size()){
        throw new IllegalArgumentException("Albero o struttura non coincidono per il confronto");
      }

      // Il set di indici non validi per l'albero passato
      Set<Integer> indiciInvalid = new HashSet<>();

      for(int i = 0; i < this.hashLeaves.size(); i++){
        // La stringa della foglia dell'istanza dell'albero Merkle
        String hashThisMerkle = this.hashLeaves.get(i);
        // La stringa della foglia dell'albero di merkle passato
        String hashOtherMerkle = otherTree.hashLeaves.get(i);

        // Se gli indici delle foglie non coincidono, aggiungi alla lista
        if(!(hashThisMerkle.equals(hashOtherMerkle))){
          indiciInvalid.add(i);
        }
      }
      return indiciInvalid;
    }

    /**
     * Restituisce la prova di Merkle per un dato elemento, ovvero la lista di
     * hash dei nodi fratelli di ciascun nodo nel cammino dalla radice a una
     * foglia contenente il dato. La prova di Merkle dovrebbe fornire una lista
     * di oggetti MerkleProofHash tale per cui, combinando l'hash del dato con
     * l'hash del primo oggetto MerkleProofHash in un nuovo hash, il risultato
     * con il successivo e così via fino all'ultimo oggetto, si possa ottenere
     * l'hash del nodo padre dell'albero. Nel caso in cui non ci, in determinati
     * step della prova non ci siano due hash distinti da combinare, l'hash deve
     * comunque ricalcolato sulla base dell'unico hash disponibile.
     *
     * @param data
     *                 l'elemento per cui generare la prova di Merkle.
     * @return la prova di Merkle per il dato.
     * @throws IllegalArgumentException
     *                                      se il dato è null o non è parte
     *                                      dell'albero.
     */
    public MerkleProof getMerkleProof(T data) {
        // TODO implementare
        return null;
    }

    /**
     * Restituisce la prova di Merkle per un dato branch, ovvero la lista di
     * hash dei nodi fratelli di ciascun nodo nel cammino dalla radice al dato
     * nodo branch, rappresentativo di un blocco di dati. La prova di Merkle
     * dovrebbe fornire una lista di oggetti MerkleProofHash tale per cui,
     * combinando l'hash del branch con l'hash del primo oggetto MerkleProofHash
     * in un nuovo hash, il risultato con il successivo e così via fino
     * all'ultimo oggetto, si possa ottenere l'hash del nodo padre dell'albero.
     * Nel caso in cui non ci, in determinati step della prova non ci siano due
     * hash distinti da combinare, l'hash deve comunque ricalcolato sulla base
     * dell'unico hash disponibile.
     *
     * @param branch
     *                   il branch per cui generare la prova di Merkle.
     * @return la prova di Merkle per il branch.
     * @throws IllegalArgumentException
     *                                      se il branch è null o non è parte
     *                                      dell'albero.
     */
    public MerkleProof getMerkleProof(MerkleNode branch) {
        // TODO implementare
        return null;
    }


  /**
   * @autor Giuseppe Calabrese
   *
   * @return La lista di hash di ogni nodo ordinato
   */
  private ArrayList<String> getHashLeaves(){
      return this.hashLeaves;
    }

  /**
   * Metodo per conservare gli hash dei nodi disposti in un certo ordine
   *
   * @autor Giuseppe Calabrese
   *
   * @param linkedList la struttura dati contenente l'albero di MerkleTree
   */
  private void setHashLeaves(HashLinkedList<T> linkedList){
      this.hashLeaves = linkedList.getAllHashes();
    }


  /**
   * Implementazione del metodo rootHash per trovare in corrispondenza
   * della sua più profondità, l'hash originale
   * <p>
   * Il metodo ha lo scopo principale di ricavare la radice partendo
   * dall'insieme di foglie che vengono passate nel metodo <code> MerkleTree </code>
   * nel momento della sua creazione
   *
   * @autor Giuseppe Calabrese
   *
   * @param treeList La lista corrente costruita sull'albero MerkleTree
   * @return la radice dell'albero
   */
  private MerkleNode rootTree (HashLinkedList<T> treeList){

     /*
      * Metodo per il calcolo delle foglie a partire dai
      * propri hash in un certo ordine
      */
      setHashLeaves(treeList);

      // La lista contente tutte le mie foglie a partire dai suoi hash
      ArrayList<MerkleNode> merkleNodes = new ArrayList<>();

      MerkleNode nodeL = null;
      MerkleNode nodeR =  null;

      // Inserimento di ogni singolo nodo contenente il proprio hash
      for(String hashnode : this.getHashLeaves()){
        // Creazione del nodo corrente da aggiungere alla lista
        MerkleNode node = new MerkleNode(hashnode, null, null);
        merkleNodes.add(node); // Si aggiunge ogni singola foglia alla lista
      }

    // Costruzione dell'albero
    while (merkleNodes.size() > 1) {
      // Lista contenente i nodi da concatenare
      ArrayList<MerkleNode> nextNode = new ArrayList<>();

      for (int i = 0; i < merkleNodes.size(); i += 2) {
        // Se sono presenti due figli (quindi pari)
        if (i + 1 < merkleNodes.size()) {
          nodeL = merkleNodes.get(i);
          nodeR = merkleNodes.get(i + 1);
          String combinedHash = HashUtil.computeMD5((nodeL.getHash() + nodeR.getHash()).getBytes());
          nextNode.add(new MerkleNode(combinedHash, nodeL, nodeR));
        } else { // Se è presente un solo figlio (quindi dispari), lo duplico
          nodeL = merkleNodes.get(i);
          String combinedHash = HashUtil.computeMD5((nodeL.getHash() + nodeL.getHash()).getBytes());
          nextNode.add(new MerkleNode(combinedHash, nodeL, null));
        }
      }
      // Passo al nodo successivo
      merkleNodes = nextNode;
    }
    return merkleNodes.get(0); // La radice, ovvero il primo elemento nella lista
  }

  /**
   * Il metodo controlla ricorsivamente per ogni nodo figlio sinistro/ destro,
   * se può essere presente o meno nel sotto-albero di MerkleTree
   *
   * @autor Giuseppe Calabrese
   * @param nodeBranch il nodo corrente su cui validate il branch
   * @param hash l'ash del branch
   * @return true se è presente in un sotto albero del MerkleTree,
   *         false altrimenti
   */
  private boolean checkHashTree(MerkleNode nodeBranch, String hash) {

    if(nodeBranch == null)
      return false;
    if (nodeBranch.getHash().equals(hash)) {
      return true;
    }
    // Controllo ricorsivamente anche per i nodi figli
    return checkHashTree(nodeBranch.getLeft(), hash) ||
      checkHashTree(nodeBranch.getRight(), hash);
  }


  /**
   * Effettua la ricerca pr l'indice di un elemento specifico in un
   * soto-albero di Merkle, in modo ricorsivo.
   *
   * @autor Giuseppe Calabrese
   * @param branch la radice da cercare
   * @param hash l'hash MD5 dell'elemento da cercare
   * @param startIndex L'indice di partenza per ogni branch (ovvero 0)
   * @return l'indice del branch (se presente)
   */
  private int checkIndexBranch(MerkleNode branch, String hash, int startIndex){
    // Caso base: nodo nullo
    if (branch == null) {
      return -1;
    }

    // Controlliamos e l'hash dei una foglia è uguale all'hash della nostra ricerca
    if (branch.getLeft() == null && branch.getRight() == null) {
      return branch.getHash().equals(hash) ? startIndex : -1;
    }

    // Si applica la ricorsione del sotto-albero sinistro
    int leftResult = checkIndexBranch(branch.getLeft(), hash, startIndex);
    if (leftResult != -1) {
      return leftResult;
    }

    // Nel caso in cui non sia presente nel sotto albero sinistro, si
    // cerca ricorsivamente su quello destro
    int leftLeaves = nodeLeave(branch.getLeft());
    return checkIndexBranch(branch.getRight(), hash, startIndex + leftLeaves);
  }


  /**
   * Calcolo il numero di foglie presenti nel sotto-albero specificato
   *
   * @autor Giuseppe Calabrese
   * @param node l'indice di cui contare le foglie
   * @return il numero di foglie presenti
   */
  private int nodeLeave(MerkleNode node){
    if (node == null) {
      return 0;
    }
    // Se l'hash è uguale alla foglia, ritorno il suo indice
    if (node.getLeft() == null && node.getRight() == null) {
      return 1;
    }
    return nodeLeave(node.getLeft()) + nodeLeave(node.getRight());
  }
}
