package arrayvisitors.visitors;

/**
 * Element interface with accept method
 * both array and arraylist are of type element
 */
public interface Element {
	public void accept(Visitor visit);
}