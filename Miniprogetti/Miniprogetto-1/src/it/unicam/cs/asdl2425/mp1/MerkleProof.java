package it.unicam.cs.asdl2425.mp1;

/**
 * Una classe che rappresenta una prova di Merkle per un determinato albero di
 * Merkle ed un suo elemento o branch. Oggetti di questa classe rappresentano un
 * proccesso di verifica auto-contenuto, dato da una sequenza di oggetti
 * MerkleProofHash che rappresentano i passaggi necessari per validare un dato
 * elemento o branch in un albero di Merkle decisi al momento di costruzione
 * della prova.
 *
 * @author Luca Tesei, Marco Caputo (template) GIUSEPPE, CALABRESE
 *         giusepp.calabrese@studenti.unicam.it (implementazione)
 */
public class MerkleProof {

    /**
     * La prova di Merkle, rappresentata come una lista concatenata di oggetti
     * MerkleProofHash.
     */
    private final HashLinkedList<MerkleProofHash> proof;

    /**
     * L'hash della radice dell'albero di Merkle per il quale la prova è stata
     * costruita.
     */
    private final String rootHash;

    /**
     * Lunghezza massima della prova, dato dal numero di hash che la compongono
     * quando completa. Serve ad evitare che la prova venga modificata una volta
     * che essa sia stata completamente costruita.
     */
    private final int length;

    /**
     * Costruisce una nuova prova di Merkle per un dato albero di Merkle,
     * specificando la radice dell'albero e la lunghezza massima della prova. La
     * lunghezza massima della prova è il numero di hash che la compongono
     * quando completa, oltre il quale non è possibile aggiungere altri hash.
     *
     * @param rootHash
     *                     l'hash della radice dell'albero di Merkle.
     * @param length
     *                     la lunghezza massima della prova.
     */
    public MerkleProof(String rootHash, int length) {
        if (rootHash == null)
            throw new IllegalArgumentException("The root hash is null");
        this.proof = new HashLinkedList<>();
        this.rootHash = rootHash;
        this.length = length;
    }

    /**
     * Restituisce la massima lunghezza della prova, dato dal numero di hash che
     * la compongono quando completa.
     *
     * @return la massima lunghezza della prova.
     */
    public int getLength() {
        return length;
    }

    /**
     * Aggiunge un hash alla prova di Merkle, specificando se esso dovrebbe
     * essere concatenato a sinistra o a destra durante la verifica della prova.
     * Se la prova è già completa, ovvero ha già raggiunto il massimo numero di
     * hash deciso alla sua costruzione, l'hash non viene aggiunto e la funzione
     * restituisce false.
     *
     * @param hash
     *                   l'hash da aggiungere alla prova.
     * @param isLeft
     *                   true se l'hash dovrebbe essere concatenato a sinistra,
     *                   false altrimenti.
     * @return true se l'hash è stato aggiunto con successo, false altrimenti.
     */
    public boolean addHash(String hash, boolean isLeft) {
      // Nel caso in cui sia completa o superiore
      if (this.proof.getSize() >= this.length)
        return false;
      // Costruzione dell'oggetto da aggiungere
      MerkleProofHash proofHash = new MerkleProofHash(hash, isLeft);
      if(proofHash.isLeft()){
        this.proof.addAtHead(proofHash);
      }
      else {
        this.proof.addAtTail(proofHash);
      }
      return true;
    }

    /**
     * Rappresenta un singolo step di una prova di Merkle per la validazione di
     * un dato elemento.
     */
    public static class MerkleProofHash {
        /**
         * L'hash dell'oggetto.
         */
        private final String hash;

        /**
         * Indica se l'hash dell'oggetto dovrebbe essere concatenato a sinistra
         * durante la verifica della prova.
         */
        private final boolean isLeft;

        public MerkleProofHash(String hash, boolean isLeft) {
            if (hash == null)
                throw new IllegalArgumentException("The hash cannot be null");

            this.hash = hash;
            this.isLeft = isLeft;
        }

        /**
         * Restituisce l'hash dell'oggetto MerkleProofHash.
         *
         * @return l'hash dell'oggetto MerkleProofHash.
         */
        public String getHash() {
            return hash;
        }

        /**
         * Restituisce true se, durante la verifica della prova, l'hash
         * dell'oggetto dovrebbe essere concatenato a sinistra, false
         * altrimenti.
         *
         * @return true se l'hash dell'oggetto dovrebbe essere concatenato a
         *         sinistra, false altrimenti.
         */
        public boolean isLeft() {
            return isLeft;
        }

        @Override
        public boolean equals(Object obj) {
            /*
             * Due MerkleProofHash sono uguali se hanno lo stesso hash e lo
             * stesso flag isLeft
             */
          if(obj == null)
            return false;
          if(this == obj)
            return true;

          // Casto l'oggetto
          MerkleProofHash proofHash = (MerkleProofHash) obj;
          if(this.getHash().equals(proofHash.getHash()) && this.isLeft() == proofHash.isLeft()){
            return true;
          }
          return false;
        }

        @Override
        public String toString() {
            return hash + (isLeft ? "L" : "R");
        }

        @Override
        public int hashCode() {
            /*
             * Implementare in accordo a equals
             */
          return this.hash.hashCode();
        }
    }

    /**
     * Valida un dato elemento per questa prova di Merkle. La verifica avviene
     * combinando l'hash del dato con l'hash del primo oggetto MerkleProofHash
     * in un nuovo hash, il risultato con il successivo e così via fino
     * all'ultimo oggetto, e controllando che l'hash finale coincida con quello
     * del nodo radice dell'albero di Merkle orginale.
     *
     * @param data
     *                 l'elemento da validare.
     * @return true se il dato è valido secondo la prova; false altrimenti.
     * @throws IllegalArgumentException
     *                                      se il dato è null.
     */
    public boolean proveValidityOfData(Object data) {
      if(data == null)
        throw new IllegalArgumentException("L'oggetto non può essere null!");
      // L'hash dell'oggetto da controllare
      String validate = HashUtil.dataToHash(data);

      for(MerkleProofHash proofHash : this.proof){
        if(proofHash.isLeft){
          // Concateno a sinistra
          validate = HashUtil.computeMD5((proofHash.getHash() + validate).getBytes());
        }
        // Concateno a destra
        else validate = HashUtil.computeMD5((validate + proofHash.getHash()).getBytes());
      }
      return this.rootHash.equals(validate);
    }

    /**
     * Valida un dato branch per questa prova di Merkle. La verifica avviene
     * combinando l'hash del branch con l'hash del primo oggetto MerkleProofHash
     * in un nuovo hash, il risultato con il successivo e così via fino
     * all'ultimo oggetto, e controllando che l'hash finale coincida con quello
     * del nodo radice dell'albero di Merkle orginale.
     *
     * @param branch
     *                   il branch da validare.
     * @return true se il branch è valido secondo la prova; false altrimenti.
     * @throws IllegalArgumentException
     *                                      se il branch è null.
     */
    public boolean proveValidityOfBranch(MerkleNode branch) {
      if(branch == null)
        throw new IllegalArgumentException("Il parametro passato non può essere null!");

      // L'hash del branch da verificare
      String validateBranch = branch.getHash();

      for (MerkleProofHash proofHash : this.proof){
        validateBranch = HashUtil.computeMD5((proofHash.getHash() + validateBranch).getBytes());
      }
      // Se coincide con la radice root
      return this.rootHash.equals(validateBranch);
    }
}
