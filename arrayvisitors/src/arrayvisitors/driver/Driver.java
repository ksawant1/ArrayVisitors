package arrayvisitors.driver;
import java.io.File;
import java.io.IOException;
import arrayvisitors.adt.MyArray;
import arrayvisitors.adt.MyArrayList;
import arrayvisitors.exceptions.InputFileMatchException;
import arrayvisitors.exceptions.InputFileNotExistsException;
import arrayvisitors.exceptions.OutputFileMatchException;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.MyLogger;
import arrayvisitors.util.Results;
import arrayvisitors.visitors.MissingIntsVisitor;
import arrayvisitors.visitors.PopulateMyArrayVisitor;
import arrayvisitors.visitors.CommonIntsVisitor;

/**
 * Driver is a utility that contains main method
 * @author Krupa Sawant
 */

public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

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
		if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${input2}")) || (args[2].equals("${commonintsout}")) || (args[3].equals("${missingintsout}")) || (args[4].equals("${debug}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		//storing input files
		String inputfile = args[0];
		String inputfile2 = args[1];
		File inputFile = new File(args[0]);
		File inputFile2 = new File(args[1]);
		MyLogger.getInstance().setDebugValue(Integer.parseInt(args[4]));
		MyLogger.getInstance().writeMessage("Inside Driver Class", MyLogger.DebugLevel.DRIVER);
		MyLogger.getInstance().writeMessage("obtaining input files from command line", MyLogger.DebugLevel.DRIVER);
		MyLogger.getInstance().writeMessage("checking for validations", MyLogger.DebugLevel.DRIVER);

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
			MyLogger.getInstance().writeMessage("creating file processor instances", MyLogger.DebugLevel.DRIVER);
			//instances of File Processor
			FileProcessor fpinput = new FileProcessor(inputfile);
			FileProcessor fpinput2 = new FileProcessor(inputfile2);

			MyLogger.getInstance().writeMessage("creating MyArray instances", MyLogger.DebugLevel.DRIVER);
			//instances of MyArray
			MyArray array1 = new MyArray();
			MyArray array2 = new MyArray();
			MyLogger.getInstance().writeMessage("creating result instances", MyLogger.DebugLevel.DRIVER);
			//result instance
			Results results = new Results();
			MyLogger.getInstance().writeMessage("creating populatemyarray visitor instances and passing it to myarrayinstances", MyLogger.DebugLevel.DRIVER);
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

			MyLogger.getInstance().writeMessage("creating commonints visitor instances and passing it to myarraylistinstances", MyLogger.DebugLevel.DRIVER);
			//instances of commonintsvisitor
			CommonIntsVisitor commonIntsVisitor = new CommonIntsVisitor(results);
			//creates myarraylist of myarray objects
			MyArrayList myArrayList = new MyArrayList(array1, array2);
			myArrayList.accept(commonIntsVisitor);
			//write to file in results
			results.writeToFile(commonintsout, 1);

			MyLogger.getInstance().writeMessage("creating missingints visitor instances and passing it to myarrayinstances", MyLogger.DebugLevel.DRIVER);
			//instances of missingintsvisitor
			MissingIntsVisitor missingIntsVisitor = new MissingIntsVisitor(results);
			//to visit missingintsvisitor
			array1.accept(missingIntsVisitor);
			array2.accept(missingIntsVisitor);
			MyLogger.getInstance().writeMessage("writing results to console and files", MyLogger.DebugLevel.DRIVER);
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
