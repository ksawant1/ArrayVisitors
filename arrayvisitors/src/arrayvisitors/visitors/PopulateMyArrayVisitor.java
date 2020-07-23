package arrayvisitors.visitors;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.exceptions.InputFileEmptyException;
import arrayvisitors.util.FileProcessor;
import java.io.IOException;
/**
 * for filling instances of my array with integers from corresponding input files
 * @author Krupa Sawant
 */
public class PopulateMyArrayVisitor implements Visitor {
	FileProcessor fileProcessor;

	public void setFileProcessor(FileProcessor fileProcessor) {
		this.fileProcessor = fileProcessor;
	}

	//mainly calls addarr()
	public void visit(Element element) {
		populatearray((MyArrayI) element);
	}

	/**
	 * parses a line from input and adds the integer string to myarray
	 * @exception InputFileEmptyException On empty file.
	 * @exception IOException On invalid input
	 * @exception NumberFormatException when input containes other than integer
	 */
	private void populatearray(MyArrayI element) {
		try {
			// calls poll() for parsing input line by line
			String line = fileProcessor.poll();
			if (line == null)
				throw new InputFileEmptyException("");
			while (line != null) {
				int number=Integer.parseInt(line);
				if(number<0 || number>99)
					throw new NumberFormatException("");
				element.add(number);
				line = fileProcessor.poll();

			}
		} catch (InputFileEmptyException e) {
			System.out.println("input file is empty");
			System.exit(0);
		}catch (IOException e){
			System.out.println("input invalid");
			System.exit(0);
		}catch(NumberFormatException e){
			System.out.println("input file contains string other than integer or it is negative or it is greater than 99");
			System.exit(0);

		}
	}


	@Override
	public String toString() {
		return "PopulateMyArrayVisitor{" +
				"fileProcessor=" + fileProcessor +
				'}';
	}
}