package arrayvisitors.adt;

import arrayvisitors.visitors.Element;
import arrayvisitors.visitors.Visitor;

public class MyArrayList implements Element, MyArrayListI {
	MyArray[] arrays;

	public MyArrayList(MyArray... arrays) {
		this.arrays = arrays;

	}

	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public MyArray[] getArrays() {
		return arrays;
	}

}