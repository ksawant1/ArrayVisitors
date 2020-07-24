package arrayvisitors.adt;
import arrayvisitors.util.MyLogger;
import arrayvisitors.visitors.Element;
import arrayvisitors.visitors.Visitor;
import java.util.Arrays;

/**
 * MyArrayList implements Element and MyArrayListI interface
 * has methods like accept and get a list of current arrays
 * @author Krupa Sawant
 */

public class MyArrayList implements Element, MyArrayListI {
	MyArray[] arrays;

	//empty constructor
	public MyArrayList(){
		MyLogger.getInstance().writeMessage("constructor of MyArrayList", MyLogger.DebugLevel.CONSTRUCTOR);

	}

	//explicit value constructor
	public MyArrayList(MyArray... arrays) {
		this.arrays = arrays;
		MyLogger.getInstance().writeMessage("constructor of MyArrayList", MyLogger.DebugLevel.CONSTRUCTOR);

	}
	//to accept the current visitor
	public void accept(Visitor visitor) {
		MyLogger.getInstance().writeMessage("accept the visitor", MyLogger.DebugLevel.MYARRAYLIST);
		visitor.visit(this);
	}
	// returns list of all current arrays
	public MyArray[] getArrays() {
		MyLogger.getInstance().writeMessage("returns all array objects in arraylist", MyLogger.DebugLevel.MYARRAYLIST);
		return arrays;
	}

	//setter method
	public void setArrays(MyArray[] arrays) {
		this.arrays = arrays;
	}

	//empty finalize method
	public void finalize(){

	}
	//clone method
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "MyArrayList{" +
				"arrays=" + Arrays.toString(arrays) +
				'}';
	}


}