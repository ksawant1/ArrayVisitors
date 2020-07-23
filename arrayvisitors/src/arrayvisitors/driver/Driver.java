package arrayvisitors.driver;
import java.io.File;
import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.exceptions.InputFileEmptyException;
import arrayvisitors.exceptions.InputFileMatchException;
import arrayvisitors.exceptions.InputFileNotExistsException;
import arrayvisitors.exceptions.OutputFileMatchException;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.Results;
import arrayvisitors.visitors.MissingIntsVisitor;
import arrayvisitors.visitors.PopulateMyArrayVisitor;
import arrayvisitors.visitors.CommonIntsVisitor;

public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;
	public static void main(String[] args) throws Exception, InputFileMatchException, OutputFileMatchException, InputFileEmptyException, InputFileNotExistsException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 4) || (args[0].equals("${input}")) || (args[1].equals("${input2}")) || (args[2].equals("${commonintsout}")) || (args[3].equals("${missingintsout}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		//storing input files
		String inputfile = args[0];
		String inputfile2 = args[1];
		File inputFile = new File(args[0]);
		File inputFile2 = new File(args[1]);
		try {
			if (!inputFile.exists() || !inputFile2.exists()) {
				throw new InputFileNotExistsException("input file doesn't exist");
			}

			if (inputfile.equals(inputfile2))
				throw new InputFileMatchException("input files have same name and path");

			String commonintsout = args[2];
			String missingintsout = args[3];

			if (commonintsout.equals(missingintsout))
				throw new OutputFileMatchException("output files have same name and path");

			//instances of File Processor
			FileProcessor fpinput = new FileProcessor(inputfile);
			FileProcessor fpinput2 = new FileProcessor(inputfile2);


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
			results.writeToFile(commonintsout, 1);

			//instances of missingintsvisitor
			MissingIntsVisitor missingIntsVisitor = new MissingIntsVisitor(results);
			array1.accept(missingIntsVisitor);
			array2.accept(missingIntsVisitor);
			results.writeToFile(missingintsout, 2);

			results.writeToStdout();
		} catch (InputFileMatchException | InputFileNotExistsException | OutputFileMatchException e) {
			System.exit(0);
		}
	}
}
