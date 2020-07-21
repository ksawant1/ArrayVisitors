package arrayvisitors.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	Collection<Integer> commonInteger;
	Collection<Integer> missingInteger;

	public Results() {
	}

	/**
	 * writetoFile() prints the array list into the output.txt file
	 */

	public void writeToFile(String filename) throws IOException {
		try {
			FileWriter fileWriter = new FileWriter(filename);
			PrintWriter printWriter = new PrintWriter(fileWriter);

			for (int x : commonInteger) {
				printWriter.println(x);
			}
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
			for (int x : commonInteger) {
				System.out.print(x);
			}

		} catch (ArrayIndexOutOfBoundsException ex) {
			throw new ArrayIndexOutOfBoundsException("empty input file");
		}
	}

	/**
	 * stores the tree in arraylist
	 *
	 * @param commonInteger string parameter
	 */
	public void storeCommonInteger(Collection<Integer> commonInteger) {
		this.commonInteger = commonInteger;
	}

	public void storeMissingInteger(Collection<Integer> missingInteger) {
		this.missingInteger = missingInteger;
	}
}


