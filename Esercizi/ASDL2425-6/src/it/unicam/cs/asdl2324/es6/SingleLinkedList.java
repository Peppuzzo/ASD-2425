package it.unicam.cs.asdl2324.es6;

import java.util.*;

/**
 * Lista concatenata singola che non accetta valori null, ma permette elementi
 * duplicati. Le seguenti operazioni non sono supportate:
 *
 * <ul>
 * <li>ListIterator<E> listIterator()</li>
 * <li>ListIterator<E> listIterator(int index)</li>
 * <li>List<E> subList(int fromIndex, int toIndex)</li>
 * <li>T[] toArray(T[] a)</li>
 * <li>boolean containsAll(Collection<?> c)</li>
 * <li>addAll(Collection<? extends E> c)</li>
 * <li>boolean addAll(int index, Collection<? extends E> c)</li>
 * <li>boolean removeAll(Collection<?> c)</li>
 * <li>boolean retainAll(Collection<?> c)</li>
 * </ul>
 *
 * L'iteratore restituito dal metodo {@code Iterator<E> iterator()} è fail-fast,
 * cioè se c'è una modifica strutturale alla lista durante l'uso dell'iteratore
 * allora lancia una {@code ConcurrentMopdificationException} appena possibile,
 * cioè alla prima chiamata del metodo {@code next()}.
 *
 * @author Luca Tesei
 *
 * @param <E>
 *                il tipo degli elementi della lista
 */
public class SingleLinkedList<E> implements List<E> {

    private int size;

    private Node<E> head;

    private Node<E> tail;

    private int numeroModifiche;

    /**
     * Crea una lista vuota.
     */
    public SingleLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
        this.numeroModifiche = 0;
    }

    /*
     * Classe per i nodi della lista concatenata. E' dichiarata static perché
     * gli oggetti della classe Node<E> non hanno bisogno di accedere ai campi
     * della classe principale per funzionare.
     */
    private static class Node<E> {
        private E item;

        private Node<E> next;

        /*
         * Crea un nodo "singolo" equivalente a una lista con un solo elemento.
         */
        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

    }

    /*
     * Classe che realizza un iteratore per SingleLinkedList.
     * L'iteratore deve essere fail-fast, cioè deve lanciare una eccezione
     * ConcurrentModificationException se a una chiamata di next() si "accorge"
     * che la lista è stata cambiata rispetto a quando l'iteratore è stato
     * creato.
     *
     * La classe è non-static perché l'oggetto iteratore, per funzionare
     * correttamente, ha bisogno di accedere ai campi dell'oggetto della classe
     * principale presso cui è stato creato.
     */
    private class Itr implements Iterator<E> {

        private Node<E> lastReturned;

        private int numeroModificheAtteso;

        private Itr() {
            // All'inizio non è stato fatto nessun next
            this.lastReturned = null;
            this.numeroModificheAtteso = SingleLinkedList.this.numeroModifiche;
        }

        @Override
        public boolean hasNext() {
            if (this.lastReturned == null)
                // sono all'inizio dell'iterazione
                return SingleLinkedList.this.head != null; // la lista non è vuota, c'è qualcun'altro (true)
            else
                // almeno un next è stato fatto
                return lastReturned.next != null;

        }

        @Override
        public E next() {
            // controllo concorrenza
            if (this.numeroModificheAtteso != SingleLinkedList.this.numeroModifiche) {
                throw new ConcurrentModificationException(
                        "Lista modificata durante l'iterazione");
            }
            // controllo hasNext()
            if (!hasNext())
                throw new NoSuchElementException(
                        "Richiesta di next quando hasNext è falso");
            // c'è sicuramente un elemento di cui fare next
            // aggiorno lastReturned e restituisco l'elemento next
            if (this.lastReturned == null) {
                // sono all’inizio e la lista non è vuota
                this.lastReturned = SingleLinkedList.this.head;
                return SingleLinkedList.this.head.item;
            } else {
                // non sono all’inizio, ma c’è ancora qualcuno
                lastReturned = lastReturned.next;
                return lastReturned.item;
            }

        }

    }

    /*
     * Una lista concatenata è uguale a un'altra lista se questa è una lista
     * concatenata e contiene gli stessi elementi nello stesso ordine.
     *
     * Si noti che si poteva anche ridefinire il metodo equals in modo da
     * accettare qualsiasi oggetto che implementi List<E> senza richiedere che
     * sia un oggetto di questa classe:
     *
     * obj instanceof List
     *
     * In quel caso si può fare il cast a List<?>:
     *
     * List<?> other = (List<?>) obj;
     *
     * e usando l'iteratore si possono tranquillamente controllare tutti gli
     * elementi (come è stato fatto anche qui):
     *
     * Iterator<E> thisIterator = this.iterator();
     *
     * Iterator<?> otherIterator = other.iterator();
     *
     * ...
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (!(obj instanceof SingleLinkedList))
            return false;
        SingleLinkedList<?> other = (SingleLinkedList<?>) obj;
        // Controllo se entrambe liste vuote
        if (head == null) {
            if (other.head != null)
                return false;
            else
                return true;
        }
        // Liste non vuote, scorro gli elementi di entrambe
        Iterator<E> thisIterator = this.iterator();
        Iterator<?> otherIterator = other.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            E o1 = thisIterator.next();
            // uso il polimorfismo di Object perché non conosco il tipo ?
            Object o2 = otherIterator.next();
            // il metodo equals che si usa è quello della classe E
            if (!o1.equals(o2))
                return false;
        }
        // Controllo che entrambe le liste siano terminate
        return !(thisIterator.hasNext() || otherIterator.hasNext());
    }

    /*
     * L'hashcode è calcolato usando gli hashcode di tutti gli elementi della
     * lista.
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        // implicitamente, col for-each, uso l'iterator di questa classe
        for (E e : this)
            hashCode = 31 * hashCode + e.hashCode();
        return hashCode;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
      if(o == null)
        throw new NullPointerException("La lista non può controllare valori nulli!");

      Iterator<E> iterator = this.iterator(); // Creo un oggetto Iterator sulla LinkedList corrente

      while(iterator.hasNext()){
        // Verifico se l'item passato è uguale al prossimo elemento dell'iteratore
        if(o.equals(iterator.next()))
          return true;
      }
      return false;
    }

    @Override
    public boolean add(E e) {
        if(e == null)
          throw new NullPointerException("Tentativo di inserimento di un valore NULL!");

        Node<E> node = new Node<>(e, null); // Il nuovo nodo da aggiungere

        if(this.head == null){
          this.head = node;
          this.tail = node;
          this.numeroModifiche++;
        } else {
          this.tail.next = node;
          this.tail = node;
          this.numeroModifiche++;
        }
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
      if (o == null)
        throw new NullPointerException(
          "Tentativo di rimuovere un oggetto null");
      int index = this.indexOf(o);
      if (index == -1)
        // oggetto non presente
        return false;
      // rimuovo la prima occorrenza dell'oggetto in posizione index
      this.remove(index);
      return true;
    }

    @Override
    public void clear() {
      // Scollegando la testa, non ho più nodi in cui accedere
      this.head = null;
      this.size = 0;
      this.numeroModifiche++;
    }

    @Override

    public E get(int index) {

      if(index < 0 || this.size() < 1 || this.size() <= index)
        throw new IndexOutOfBoundsException("Indice per la ricerca non valida");

      Iterator<E> iterator = this.iterator();
      // l'indice di confronto per l'elemento iterato
      int currentInt = 0;

      while(iterator().hasNext()){
        E current = iterator.next();
        if(currentInt == index){
          return current;
        }
        currentInt++;
      }
      return null;
    }

    @Override
    public E set(int index, E element) {
        if(element == null)
          throw new NullPointerException("L'elemento non può essere sostituisco con valori nulli.");
        if(index >= this.size() || index < 0)
          throw new IndexOutOfBoundsException("Limite della lista superato oppure " +
            "Indice non valido.");

        Node<E> nodoCorrente = this.head;
        int indexCorrente = 0;

        while(nodoCorrente != null){
          if(indexCorrente == index){
            //Sostituisco il valore del nodo
            E vecchioValore = nodoCorrente.item;
            nodoCorrente.item = element;
            this.numeroModifiche++;
            return vecchioValore;
          }
          nodoCorrente = nodoCorrente.next;
          indexCorrente++;
        }

      return null;
    }

    @Override
    public void add(int index, E element) {
      if (index < 0 || index > this.size)
        // è ammesso index == this.size, vedi API
        throw new IndexOutOfBoundsException(
          "Tentativo di accesso a un indice non valido della lista");
      if (element == null)
        throw new NullPointerException(
          "Tentativo di aggiungere un elemento");
      // so che l'indice è valido, quindi sotto non controllo il next == null
      Node<E> previous = null;
      Node<E> n = this.head;
      int count = 0;
      while (count < index) {
        count++;
        previous = n;
        n = n.next;
      }
      // n punta al nodo di indice index e previous punta al nodo precedente
      // nella lista
      // nel caso in cui index == this.size n è null e previous è il nodo tail
      // creo un nuovo nodo facendolo puntare all'elemento corrente in
      // posizione index
      Node<E> newNode = new Node<E>(element, n);
      if (previous == null)
        // sto inserendo in testa
        this.head = newNode;
      else {
        // sto inserendo in una posizione che non è head,
        previous.next = newNode;
        // Cambio la tail nel caso index == this.size
        if (index == this.size)
          this.tail = newNode;
      }
      // aggiorno size e modifiche
      this.size++;
      this.numeroModifiche++;
    }

    @Override
    public E remove(int index) {
      if (index < 0 || index >= this.size)
        throw new IndexOutOfBoundsException(
          "Tentativo di accesso a un indice non valido della lista");
      // so che l'indice è valido, quindi sotto non controllo il next == null
      Node<E> previous = null;
      Node<E> n = this.head;
      int count = 0;
      while (count < index) {
        count++;
        previous = n;
        n = n.next;
      }
      // n punta al nodo di indice index e previous punta al nodo precedente
      // nella lista
      if (previous == null) {
        // sto eleminando l'elemento in testa alla lista
        if (n.next == null) {
          // l'elemento in testa è anche in coda, la lista si svuota
          this.head = null;
          this.tail = null;
        } else {
          // l'elemento in testa ha almeno un elemento successivo
          this.head = n.next;
        }
      } else {
        // sto eliminando un elemento non in testa
        if (n.next == null) {
          // sto eliminando l'elemento in coda
          previous.next = null;
          this.tail = previous;
        } else {
          // sto eliminando un elemento centrale
          previous.next = n.next;
        }
      }
      // aggiorno la size e il numero modifiche
      this.size--;
      this.numeroModifiche++;
      return n.item;
    }

    @Override
    public int indexOf(Object o) {
      if(o == null)
        throw new NullPointerException("Parametro passato non preferibile per i tipi null.");

      Node<E> current = this.head;
      int index = 0;

      while(current != null){
        if(current.item.equals(o)){
          return index;
        }
        current = current.next;
        index++;
      }
      // Se non è presente l'elemento
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
      if (o == null)
        throw new NullPointerException("Richiesto index di elemento null");
      // parto dal nodo testa
      Node<E> n = this.head;
      int index = -1;
      int lastIndex = -1;
      // cerco un elemento uguale a o fino a quando non ho guardato tutti gli
      // elementi
      while (n != null) {
        index++;
        // l'indice del nodo n è index
        if (o.equals(n.item))
          // trovata una occorrenza dell'elemento
          lastIndex = index;
        // vado avanti
        n = n.next;
      }
      return lastIndex;
    }

    @Override
    public Object[] toArray() {
      Object[] result = new Object[this.size];
      int i = 0;
      // uso il foreach
      for (E el : this) {
        result[i] = el;
        i++;
      }
      return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Operazione non supportata.");
    }
}
