package arrayvisitors.visitors;

/**
 * Element interface with accept method
 * both array and arraylist are of type element
 * @author Krupa Sawant
 */
public interface Element {
	void accept(Visitor visit);
}