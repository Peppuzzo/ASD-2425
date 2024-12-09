package it.unicam.cs.asdl2425.mp1;

import com.sun.javafx.sg.prism.NodeEffectInput;

import java.util.*;

// TODO inserire solo gli import della Java SE che si ritengono necessari

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
      this.root = null;
      this.width = -1;
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
        // TODO implementare
      
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
        // TODO implementare
        return -1;
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
        // TODO implementare
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
        // TODO implementare
      if(data == null)
        throw new NullPointerException("Il parametro passato è null!");

      MerkleProof proof = new MerkleProof(root.getHash(), this.width);


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
        // TODO implementare
        return false;
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
        // TODO implementare
        return false;
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
        // TODO implementare
        return null;
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

    // TODO inserire eventuali metodi privati per fini di implementazione
}
