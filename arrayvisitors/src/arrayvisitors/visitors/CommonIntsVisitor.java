package arrayvisitors.visitors;
import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.Results;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * a visitor class that finds common integers stored in myarraylist and sends to result for printing
 * @author Krupa Sawant
 */
public class CommonIntsVisitor implements Visitor{
	Results results;

	//sets result instance
	public CommonIntsVisitor(Results results)
	{
		this.results = results;
		MyLogger.getInstance().writeMessage("constructor for common integer visitor", MyLogger.DebugLevel.CONSTRUCTOR);
	}


	//mainly for calling findCommonIntsVisitor()
	public void visit(Element myElement){
		MyLogger.getInstance().writeMessage("visit method in commonintsvisitor is used for calling private methods inside of the visitor", MyLogger.DebugLevel.COMMONINTSVISITOR);
		findCommonIntsVisitor((MyArrayList) myElement);
	}

	private void findCommonIntsVisitor(MyArrayList myArrayList) {
		Set<Integer> integers = new LinkedHashSet<>();
		Set<Integer> commonIntegers = new LinkedHashSet<>();
		// for finding common integers and passing to results
		MyLogger.getInstance().writeMessage("findCommonIntsVisitor in commonintsvisitor  checks whether the two files have common values and stores them into a set which is passed to results", MyLogger.DebugLevel.COMMONINTSVISITOR);
		for (MyArray array : myArrayList.getArrays()) {
			for (int x : array.getArray()) {
				if (integers.contains(x))
					commonIntegers.add(x);

				integers.add(x);
			}
		}
		results.storeCommonInteger(commonIntegers);
	}

	@Override
	public String toString() {
		return "CommonIntsVisitor{" +
				"results=" + results +
				'}';
	}
}