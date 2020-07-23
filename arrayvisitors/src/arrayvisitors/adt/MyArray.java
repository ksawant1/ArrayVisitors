package arrayvisitors.adt;
import java.util.Arrays;
import arrayvisitors.visitors.Element;
import arrayvisitors.visitors.Visitor;
/**
 * defines an ADT for MyArray
 * has methods which adds new integers to array, size increase and print
 * @author Krupa Sawant
 */

public class MyArray implements MyArrayI, Element {
	int array[];
	int numofelems, sizeofarray;

	//defines a default size of 10 for a new array, number of elements as zero and size of array as 10
	public MyArray() {
		array = new int[10];
		numofelems = 0;
		sizeofarray = 10;
	}

    //clone method
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	//empty finalize method
	public void finalize(){
	}

	// function to get complete array
	public int[] getArray() {
		int[] array1 = new int[numofelems];
		for (int i = 0; i < getNumofelems(); i++) {
			array1[i]=(array[i]);
		}
		return array1;
	}
	//accepts the visitor
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	//add element if space is available in array else creates space that is 50 percent more of previous size
	public void add(int data) {
		if (getNumofelems() == getSizeofarray())
			growSize();
		array[getNumofelems()] = data;
		numofelems++;
	}

    //mainly creates a new array with increased size, copies elements of old array into it and makes the new array as current array
	public void growSize() {
		int newarr[] = null;
		if (getNumofelems() == getSizeofarray()) {
			newarr = Arrays.copyOf(array, sizeofarray + sizeofarray / 2);
		}
		array = newarr;
		sizeofarray = sizeofarray + sizeofarray / 2;

	}

	//getters and setters

	public void setArray(int[] array) {
		this.array = array;
	}

	public int getNumofelems() {
		return numofelems;
	}

	public void setNumofelems(int numofelems) {
		this.numofelems = numofelems;
	}

	public int getSizeofarray() {
		return sizeofarray;
	}

	public void setSizeofarray(int sizeofarray) {
		this.sizeofarray = sizeofarray;
	}



	//to string method
	@Override
	public String toString() {
		return "MyArray{" +
				"array=" + Arrays.toString(array) +
				", numofelems=" + getNumofelems() +
				", sizeofarray=" + getSizeofarray() +
				'}';
	}
}