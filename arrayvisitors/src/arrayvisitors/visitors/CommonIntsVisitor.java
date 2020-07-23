package arrayvisitors.visitors;
import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.util.Results;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * a visitor class that finds common integers stored in myarraylist and sends to result for printing
 */
public class CommonIntsVisitor implements Visitor{
	Results results;

	//sets result instance
	public CommonIntsVisitor(Results results) {
		this.results = results;
	}

	//mainly for calling findCommonIntsVisitor()
	public void visit(Element myElement){
		findCommonIntsVisitor((MyArrayList) myElement);
	}

	private void findCommonIntsVisitor(MyArrayList myArrayList) {
		Set<Integer> integers = new LinkedHashSet<>();
		Set<Integer> commonIntegers = new LinkedHashSet<>();

		// for finding common integers and passing to results
		for (MyArray array : myArrayList.getArrays()) {
			for (int x : array.getArray()) {
				if (integers.contains(x))
					commonIntegers.add(x);

				integers.add(x);
			}
		}
		results.storeCommonInteger(commonIntegers);
	}

}