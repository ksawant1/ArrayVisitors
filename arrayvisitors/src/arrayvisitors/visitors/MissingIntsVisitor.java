package arrayvisitors.visitors;
import arrayvisitors.adt.MyArray;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.Results;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *  a visitor class that finds missing integers stored in myarray and sends to result for printing
 * @author Krupa Sawant
 */
public class MissingIntsVisitor implements Visitor {
	Results results;
	List<Integer> missing = new ArrayList<>();

	//sets the result instance
	public MissingIntsVisitor(Results results) {
		this.results = results;
		MyLogger.getInstance().writeMessage("constructor for missingints visitor", MyLogger.DebugLevel.CONSTRUCTOR);

	}

	//mainly for calling findMissingInt()
	public void visit(Element myElement) {
		findMissingInt((MyArray) myElement);
		MyLogger.getInstance().writeMessage("visit() inside missingintsvisitor calls private methods inside the visitor", MyLogger.DebugLevel.MISSINGINTSVISITOR);

	}

	private void findMissingInt(MyArray myArray) {
		Set<Integer> integers = new TreeSet<>();
		MyLogger.getInstance().writeMessage("findingMisssingInt() inside missingintsvisitor counts all numbers between 0 and 99 that are missing int two arrays and prints in results", MyLogger.DebugLevel.MISSINGINTSVISITOR);
		//for counting missing integers in arrays and send to results
		for (int x : myArray.getArray())
			integers.add(x);
		List<Integer> missingInteger = IntStream.range(00, 99)
				.filter(num -> !integers.contains(num))
				.boxed()
				.collect(Collectors.toList());
		missing.addAll(missingInteger);
		//passes set to results
		results.storeMissingInteger(missing);

	}

	@Override
	public String toString() {
		return "MissingIntsVisitor{" +
				"results=" + results +
				", missing=" + missing +
				'}';
	}
}
