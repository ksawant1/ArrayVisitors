package arrayvisitors.adt;

import java.util.Arrays;
import arrayvisitors.visitors.Element;
import arrayvisitors.visitors.Visitor;

/**
 * defines an ADT for MyArray
 * has methods which adds new integers to array, size increase and print
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

	// function to get complete array

	public int[] getArray() {
		int[] array1 = new int[numofelems];
		for (int i = 0; i < numofelems; i++) {

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
		if (numofelems == sizeofarray)
			growSize();
		array[numofelems] = data;
		numofelems++;

	}

    //mainly creates a new array with increased size, copies elements of old array into it and makes the new array as current array
	public void growSize() {
		int newarr[] = null;
		if (numofelems == sizeofarray) {
			newarr = Arrays.copyOf(array, sizeofarray + sizeofarray / 2);
		}
		array = newarr;
		sizeofarray = sizeofarray + sizeofarray / 2;

	}
   // prints the array list
	public void printelements() {
		for (int i = 0; i < numofelems; i++) {
			System.out.print(array[i] + " ");
		}
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "MyArray{" +
				"arr=" + java.util.Arrays.toString(array) +
				", numofelems=" + numofelems +
				", sizeofarray=" + sizeofarray +
				'}';
	}
}