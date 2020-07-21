package arrayvisitors.driver;

import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.Results;
import arrayvisitors.visitors.MissingIntsVisitor;
import arrayvisitors.visitors.PopulateMyArrayVisitor;
import arrayvisitors.visitors.CommonIntsVisitor;

public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;
	public static void main(String[] args) throws Exception {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${input}")) || (args[1].equals("${input2}")) || (args[2].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		//storing input files
		String filename = args[0];
		String filename2 = args[1];

		//instances of File Processor
		FileProcessor fpinput = new FileProcessor(filename);
		FileProcessor fpinput2 = new FileProcessor(filename2);


		//instances of MyArray
		MyArray array1 = new MyArray();
		MyArray array2 = new MyArray();

		//result instance
		Results results = new Results();

		//instances of populatemyarrayvisitor
		PopulateMyArrayVisitor populateMyArrayVisitor = new PopulateMyArrayVisitor();

		//sets input for first myarrayinstance
		populateMyArrayVisitor.setFileProcessor(fpinput);
		//to visit populatemyarrayvisitor
		array1.accept(populateMyArrayVisitor);
		//sets input for second myarrayinstance
		populateMyArrayVisitor.setFileProcessor(fpinput2);
		////to visit populatemyarrayvisitor
		array2.accept(populateMyArrayVisitor);

		//instances of commonintsvisitor
		CommonIntsVisitor commonIntsVisitor = new CommonIntsVisitor(results);
		MyArrayList myArrayList = new MyArrayList(array1, array2);
		myArrayList.accept(commonIntsVisitor);

		//instances of missingintsvisitor
		MissingIntsVisitor missingIntsVisitor = new MissingIntsVisitor(results);
		array1.accept(missingIntsVisitor);
		array2.accept(missingIntsVisitor);
	}
}
