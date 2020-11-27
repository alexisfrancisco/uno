

public class CardList {
	/* Attributes: first, keeps the front of the list
	 * count, keeps track of the number of Cards on the list
	 */
	private Card first;
	private int count;

	public int size() {
		return this.countCards();
	}
	
	public Card getFirst() {
		return this.first;
	}
	
	/* 
	 * Constructor (I did this one for you)
	 * if all is true, it creates a complete deck of 40 cards,
	 *                 4 colors, numbers from 0-9
	 * if all is false, it just creates an empty list
	 */
	public CardList(boolean all) {
		first=null;
		count=0;
		if (all) {
			for (Card.Colors color : Card.Colors.values()) {
				for (int number=0;number<=9;number++) {
					add(number,color);
				}	
			}
		}
	}

	/* DO THIS:
	 * traverse: is not actually used in the gui version of the program, 
	 * but it is useful while debugging the program
	 */
	public void traverse() {
		Card current = this.first;
		
		while (current != null) {
			System.out.println(current.toString());
			current  = current.getNext();
		}
	}

	/* DO THIS:
	 * add: adds a card to the front of the list
	 * given number and color
	 */
	private void add(int number,Card.Colors color) {
		this.first = new Card(number, color, this.first);
	}

	/* DO THIS:
	 * add: adds a card to the front of the list
	 * given a reference to the new card
	 */
	private void add(Card card) {
		this.first = new Card(card.getNumber(), card.getColor(), this.first);
	}
	
	/* DO THIS:
	 * countCards: Traverse the list and return the number of cards.
	 *     When complex operations are done on a list, such as
	 *     concatenation of lists, countCards is used to make sure that the
	 *     number of cards is kept updated (just there because we are lazy
	 *     and do not want to think about how to compute the new number
	 *     of cards based on the original ones).
	 */
	private int countCards() {   // WORKS
		Card current = this.first;
		count = 0;
		
		while (current != null) {
			current  = current.getNext();
			count++;
		}
		
		return count;
	}

	/* DO THIS:
	 * Append a new list of cards "list" at the end of the current list (this)
	 * Notice that it might be possible for this.first to be null
	 */
	public void concatenateWith(CardList list) { // WORKS
		if (this.first == null) {	
			this.first = list.getFirst();
		}
		else {
			Card lastCard = first;
			while (lastCard.getNext() != null) {
				lastCard = lastCard.getNext();
			}
			
			lastCard.setNext(list.first);
		}
	}

	/* DO THIS:
	 * moveTo: move the front card from this to the front of destination
	 */
	public void moveTo(CardList destination) {			// WORKS
		if (this.first == null) 
			return;
		
		destination.add(this.first);
		
		this.first = this.first.getNext();
	}
	
	/* DO THIS:
	 * moveTo: move the first num cards from this to the front of destination,
	 * it can use the moveTo(CardList destination) method repeatedly
	 */	
	public void moveTo(int num,CardList destination) {		// WORKS
		if (this.first == null)
			return;
		
		int count = num;
		while (count > 0) {
			this.moveTo(destination);
			
			count--;
		}
	}

	/* DO THIS:
	 * moveTo: Given a Card x, it finds the card on this list and
	 *         moves it to the front of the destination list.
	 * 
	 */
	public boolean moveTo(Card x,CardList destination) {		// WORKS
		if (this.first == null)			// if the list is empty, return false
			return false;
		
		Card prev = null;
		Card current = this.first;		// sets current to the first in this list
		
		if (this.first.matches(true, x)) {
			destination.add(this.first);
			this.first = current.getNext();
			return true;
		}
		
		
		while (current != null) {		// while current hasnt reached end (null) or isnt the card x
			if (current.matches(true, x)) {
				destination.add(current);
				current = current.getNext();
				prev.setNext(current);
				return true;
			}
			prev = current;
			current = current.getNext();		// goes thru next iteration
		}
		
		return false;
	}
		
	/* DO THIS:
	 * shuffle: Easy way is to create two new empty lists,
	 *          repeat split number of times: move the
	 *          first card of this to the first list, and then 
	 *          the next one to the second list,
	 *          finally, concatenate the two lists to this. 
	 */
	public void shuffle(int split) {			//WORKS
		CardList firstL = new CardList(false);
		CardList secondL = new CardList(false);
		
		boolean ifOdd = true;
		int count = split;
		
		while (count > 0) {
			if(ifOdd)
				this.moveTo(firstL);
			else 
				this.moveTo(secondL);
			
			ifOdd = !ifOdd;
			
			count--;
		}
		
		this.concatenateWith(firstL);
		this.concatenateWith(secondL);
	}
	
	/* DO THIS:
	 * search: return a card that matches either the number or color
	 *         of the given card x.
	 *         You must use the matches(false,x) method that you wrote for the
	 *         Card class.
	 */
	public Card search(Card x) {			// WORKS
		Card current = this.first;
		
		while (current != null) {
			if (current.matches(false, x))
				return current;
			current = current.getNext();
		}
		
		return null;
	}

	
	/* DO THIS:
	 * getCard: returns a Card in this list that matches exactly
	 *          (use matches(true,card) method in Card) the given card
	 */
	public Card getCard(Card card) {			// WORKS
		Card current = this.first;
		
		while (current != null && !(current.matches(true, card))){
			current = current.getNext();
		}
		
		if (current == null) {
			return null;
		}
		
		return current;
	}

}
