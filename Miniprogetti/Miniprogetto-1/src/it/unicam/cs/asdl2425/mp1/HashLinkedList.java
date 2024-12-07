package it.unicam.cs.asdl2425.mp1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

/**
 * Una classe che rappresenta una lista concatenata con il calcolo degli hash
 * MD5 per ciascun elemento. Ogni nodo della lista contiene il dato originale di
 * tipo generico T e il relativo hash calcolato utilizzando l'algoritmo MD5.
 *
 * <p>
 * La classe supporta le seguenti operazioni principali:
 * <ul>
 * <li>Aggiungere un elemento in testa alla lista</li>
 * <li>Aggiungere un elemento in coda alla lista</li>
 * <li>Rimuovere un elemento dalla lista in base al dato</li>
 * <li>Recuperare una lista ordinata di tutti gli hash contenuti nella
 * lista</li>
 * <li>Costruire una rappresentazione testuale della lista</li>
 * </ul>
 *
 * <p>
 * Questa implementazione include ottimizzazioni come il mantenimento di un
 * riferimento all'ultimo nodo della lista (tail), che rende l'inserimento in
 * coda un'operazione O(1).
 *
 * <p>
 * La classe utilizza la classe HashUtil per calcolare l'hash MD5 dei dati.
 *
 * @param <T>
 *                il tipo generico dei dati contenuti nei nodi della lista.
 *
 * @author Luca Tesei, Marco Caputo (template) GIUSEPPE, CALABRESE
 *         giusepp.calabrese@studenti.unicam.it (implementazione)
 *
 */
public class HashLinkedList<T> implements Iterable<T> {
    private Node head; // Primo nodo della lista

    private Node tail; // Ultimo nodo della lista

    private int size; // Numero di nodi della lista

    private int numeroModifiche; // Numero di modifiche effettuate sulla lista
                                 // per l'implementazione dell'iteratore
                                 // fail-fast

    public HashLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.numeroModifiche = 0;
    }

    /**
     * Restituisce il numero attuale di nodi nella lista.
     *
     * @return il numero di nodi nella lista.
     */
    public int getSize() {
        return size;
    }

    /**
     * Rappresenta un nodo nella lista concatenata.
     */
    private class Node {
        String hash; // Hash del dato

        T data; // Dato originale

        Node next;

        Node(T data) {
            this.data = data;
            this.hash = HashUtil.dataToHash(data);
            this.next = null;
        }
    }

    /**
     * Aggiunge un nuovo elemento in testa alla lista.
     *
     * @param data
     *                 il dato da aggiungere.
     */
    public void addAtHead(T data) {
      if(data == null){ return; }
      // Creo un nuovo nodo da aggiungere alla testa dell'albero
      Node nodeHead = new Node(data);
      nodeHead.next = this.head;
      this.head = nodeHead;
      if(this.tail == null)
        this.tail = nodeHead;
      this.size++;
      this.numeroModifiche++;
    }

    /**
     * Aggiunge un nuovo elemento in coda alla lista.
     *
     * @param data
     *                 il dato da aggiungere.
     */
    public void addAtTail(T data) {
      if(data == null){
        return;
      }

      // Il nuovo nodo da aggiungere
      Node nodeTail = new Node(data);

      if(this.tail == null){
        this.head = nodeTail;
      }
      else {
        this.tail.next = nodeTail;
      }
      this.tail = nodeTail;
      this.size++;
      this.numeroModifiche++;
    }

    /**
     * Restituisce un'ArrayList contenente tutti gli hash nella lista in ordine.
     *
     * @return una lista con tutti gli hash della lista.
     */
    public ArrayList<String> getAllHashes() {
      // L'ArrayList contenente tutti gli hash
      ArrayList<String> hashList = new ArrayList<>();
      Node node = this.head;

      while(node != null){
        hashList.add(node.hash);
        // Il prossimo nodo da visitare
        node = node.next;
      }
        return hashList;
    }

    /**
     * Costruisce una stringa contenente tutti i nodi della lista, includendo
     * dati e hash. La stringa dovrebbe essere formattata come nel seguente
     * esempio:
     *
     * <pre>
     *     Dato: StringaDato1, Hash: 5d41402abc4b2a76b9719d911017c592
     *     Dato: SteringaDato2, Hash: 7b8b965ad4bca0e41ab51de7b31363a1
     *     ...
     *     Dato: StringaDatoN, Hash: 2c6ee3d301aaf375b8f026980e7c7e1c
     * </pre>
     *
     * @return una rappresentazione testuale di tutti i nodi nella lista.
     */
    public String buildNodesString() {
      String nodesString = "";
      Node eNode = this.head;

      while(eNode != null){
        nodesString += "Dato: " + eNode.data + ", Hash: " + eNode.hash + "\n";
        eNode = eNode.next;
      }
        return nodesString;
    }

    /**
     * Rimuove il primo elemento nella lista che contiene il dato specificato.
     *
     * @param data
     *                 il dato da rimuovere.
     * @return true se l'elemento è stato trovato e rimosso, false altrimenti.
     */
    public boolean remove(T data) {
      // se la lista è vuota
      if(this.size == 0)
        return false;

      // Nodo corrente per l'iterazione
      Node current = this.head;

      // Nel caso che in cui devo rimuovere la testa
      if(current.data.equals(data)){
        this.head = current.next;
        current.next = null;
        this.size--;
        this.numeroModifiche++;
        return true;
      }

      //Nel caso in sia in fondo alla lista
      while(current.next != null){
        //Itera fino tanto che non trovo il data identico
        if(!current.next.data.equals(data)){
          current = current.next;
          continue;
        }
        // Il nodo da rimuovere
        Node nodeRemove = current.next;
        current.next = nodeRemove.next;
        nodeRemove.next = null;
        // Collego il penultimo nodo letto come coda
        if(nodeRemove == this.tail){
          this.tail = current;
        }
        // Aggiorno la lista
        this.size--;
        this.numeroModifiche++;
        return true;
      }
      return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    /**
     * Classe che realizza un iteratore fail-fast per HashLinkedList.
     */
    private class Itr implements Iterator<T> {

        private Node lastNode;
        private final int expectedChanges;

        private Itr() {
          this.lastNode = null;
          // Numero di modifiche attese per l'iteratore
          this.expectedChanges = numeroModifiche;
        }

        @Override
        public boolean hasNext() {
          if(this.expectedChanges != numeroModifiche)
            // Non è fail-fast
            throw new ConcurrentModificationException("L'iteratore è stato modificato durante l'esecuzione!");

          return this.lastNode == null ? head != null : this.lastNode.next != null;
        }

        @Override
        public T next() {
          if(this.expectedChanges != numeroModifiche)
            throw new ConcurrentModificationException("L'iteratore non è riuscito a scorrere sul prossimo nodo!");

          if(this.lastNode == null){
            // Inizio dal primo elemento della lista
            this.lastNode = HashLinkedList.this.head;
          }
          else {
            this.lastNode = this.lastNode.next;
          }
          // Il dato contenuto
          return this.lastNode.data;
        }
    }
}
