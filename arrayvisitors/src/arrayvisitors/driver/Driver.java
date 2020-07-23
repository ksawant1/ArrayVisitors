package arrayvisitors.driver;
import java.io.File;
import java.io.IOException;
import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.exceptions.InputFileMatchException;
import arrayvisitors.exceptions.InputFileNotExistsException;
import arrayvisitors.exceptions.OutputFileMatchException;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.Results;
import arrayvisitors.visitors.MissingIntsVisitor;
import arrayvisitors.visitors.PopulateMyArrayVisitor;
import arrayvisitors.visitors.CommonIntsVisitor;

/**
 * Driver is a utility that contains main method
 * @author Krupa Sawant
 */

public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 4;

	/**
	 * Handles few exceptions and mainly creates instances for visitors, ADTs and results
	 * @exception IOException On invalid input.
	 * @exception InputFileNotExistsException On not finding input file where mentioned.
	 * @exception InputFileMatchException when both input files have same name and path
	 * @exception OutputFileMatchException when both output files have same name and path
	 */
	public static void main(String[] args) throws IOException, InputFileMatchException, OutputFileMatchException, InputFileNotExistsException {

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

		//checking for validations
		try {
			// if input file does not exist in the given path
			if (!inputFile.exists() || !inputFile2.exists()) {
				throw new InputFileNotExistsException("");
			}

			// if input1 file name and path are matching to input file2
			if (inputfile.equals(inputfile2)) {
				throw new InputFileMatchException("");
			}

			//storing output files
			String commonintsout = args[2];
			String missingintsout = args[3];

			// if output1 file name and path are matching to output file2
			if (commonintsout.equals(missingintsout))
				throw new OutputFileMatchException("");

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
			//creates myarraylist of myarray objects
			MyArrayList myArrayList = new MyArrayList(array1, array2);
			myArrayList.accept(commonIntsVisitor);
			//write to file in results
			results.writeToFile(commonintsout, 1);


			//instances of missingintsvisitor
			MissingIntsVisitor missingIntsVisitor = new MissingIntsVisitor(results);
			//to visit missingintsvisitor
			array1.accept(missingIntsVisitor);
			array2.accept(missingIntsVisitor);
			//write to file the results
			results.writeToFile(missingintsout, 2);
			//print on console final results
			results.writeToStdout();

		} catch (InputFileMatchException e) {
			System.out.println("input files have same name and path");
			System.exit(0);
		}
		catch (InputFileNotExistsException e) {
			System.out.println("input file doesn't exist");
			System.exit(0);
		}
		catch (OutputFileMatchException e){
			System.out.println("output files have same name and path");
			System.exit(0);
		}
		catch (IOException e){
			System.out.println("input/output invalid");
			System.exit(0);
		}

	}

	@Override
	public String toString() {
		return "Driver{}";
	}
}
