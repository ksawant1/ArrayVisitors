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
	}

	/**
	 * writetoFile() prints the array list into the output text files
	 */

	public void writeToFile(String filename,int outputtype) throws IOException {
		try {
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
	}

	// to store the set passed from MissingIntsVisitor
	public void storeMissingInteger(Collection<Integer> missingInteger) {
		this.missingInteger = missingInteger;
	}

	@Override
	public String toString() {
		return "Results{" +
				"commonInteger=" + commonInteger +
				", missingInteger=" + missingInteger +
				'}';
	}
}


