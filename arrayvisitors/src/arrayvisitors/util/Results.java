package arrayvisitors.util;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
/**
 * Results is a utility that prints to corresponding output files and std console
 * @author Krupa Sawant
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	Collection<Integer> commonInteger;
	Collection<Integer> missingInteger ;
	public Results() {
		MyLogger.getInstance().writeMessage("constructor of results", MyLogger.DebugLevel.CONSTRUCTOR);

	}

	/**
	 * writetoFile() prints the array list into the output text files
	 */

	public void writeToFile(String filename,int outputtype) throws IOException {
		try {
			MyLogger.getInstance().writeMessage("writetoFile() inside Result takes an output file and writes final results to it", MyLogger.DebugLevel.RESULTS);
			FileWriter fileWriter = new FileWriter(filename);
			PrintWriter printWriter = new PrintWriter(fileWriter);
            //writes to output files
			if(outputtype==1){
			for (int x : commonInteger) {
				printWriter.println(x);
			}}
			else
			{
				for (int x : missingInteger) {
					printWriter.println(x);
				}}

			printWriter.close();
		} catch (IOException ex) {
			System.out.println("file not found");
		}
	}

	/**
	 * writeToStdout prints the word array list onto the console.
	 */
	public void writeToStdout() throws ArrayIndexOutOfBoundsException {
		try {
			MyLogger.getInstance().writeMessage("writetoStdout() inside Result writes final results to console", MyLogger.DebugLevel.RESULTS);
			System.out.println("Printing final output");
			System.out.println("Common integers between files");
			for (int x : commonInteger) {

				System.out.print(x+" ");
			}
			System.out.println();
			System.out.println("Missing integers in file");
			for (int x : missingInteger) {
				System.out.print(x+" ");
			}

		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new ArrayIndexOutOfBoundsException("empty input file");
		}
	}

	 // to store the set passed from CommonIntsVisitor

	public void storeCommonInteger(Collection<Integer> commonInteger) {

		this.commonInteger = commonInteger;
		MyLogger.getInstance().writeMessage("storeCommonInteger() inside Result stores commoninteger set to print it", MyLogger.DebugLevel.RESULTS);

	}

	// to store the set passed from MissingIntsVisitor
	public void storeMissingInteger(Collection<Integer> missingInteger) {

		this.missingInteger = missingInteger;
		MyLogger.getInstance().writeMessage("storeMissingInteger inside Result stores missing integer set to print it", MyLogger.DebugLevel.RESULTS);

	}

	@Override
	public String toString() {
		return "Results{" +
				"commonInteger=" + commonInteger +
				", missingInteger=" + missingInteger +
				'}';
	}
}


