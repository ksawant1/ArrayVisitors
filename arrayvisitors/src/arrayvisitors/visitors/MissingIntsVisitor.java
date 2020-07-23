package arrayvisitors.visitors;
import arrayvisitors.adt.MyArray;
import arrayvisitors.util.Results;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *  a visitor class that finds missing integers stored in myarray and sends to result for printing
 */
public class MissingIntsVisitor implements Visitor {
	Results results;
	List<Integer> missing = new ArrayList<>();

	//sets the result instance
	public MissingIntsVisitor(Results results) {
		this.results = results;
	}

	//mainly for calling findMissingInt()
	public void visit(Element myElement) {
		findMissingInt((MyArray) myElement);
	}

	public void findMissingInt(MyArray myArray) {
		Set<Integer> integers = new TreeSet<>();



		//for counting missing integers in arrays and send to results
		for (int x : myArray.getArray())
			integers.add(x);
		List<Integer> missingInteger = IntStream.range(00, 99)
				.filter(num -> !integers.contains(num))
				.boxed()
				.collect(Collectors.toList());
		missing.addAll(missingInteger);
		results.storeMissingInteger(missing);

//		List<Integer> num = new ArrayList<>();
//		for(int i = 10; i <= 99; i++) {
//			if (!integers.contains(i))
//				num.add(i);
//		}


	}
}
