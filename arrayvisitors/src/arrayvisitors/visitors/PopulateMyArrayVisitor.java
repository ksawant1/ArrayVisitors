package arrayvisitors.visitors;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.exceptions.InputFileEmptyException;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.MyLogger;

import java.io.IOException;
/**
 * for filling instances of my array with integers from corresponding input files
 * @author Krupa Sawant
 */
public class PopulateMyArrayVisitor implements Visitor {
	FileProcessor fileProcessor;

	public PopulateMyArrayVisitor() {
		MyLogger.getInstance().writeMessage("constructor for populatemyarray visitor", MyLogger.DebugLevel.CONSTRUCTOR);
	}

	public void setFileProcessor(FileProcessor fileProcessor) {
		this.fileProcessor = fileProcessor;
		MyLogger.getInstance().writeMessage("set method in populatemyarray takes name of input files for every instance", MyLogger.DebugLevel.POPULATEMYARRAYVISITOR);
	}

	//mainly calls addarr()
	public void visit(Element element) {
		populatearray((MyArrayI) element);
		MyLogger.getInstance().writeMessage("visit() in populatemyarray mainly calls private methods inside visitor", MyLogger.DebugLevel.POPULATEMYARRAYVISITOR);
	}

	/**
	 * parses a line from input and adds the integer string to myarray
	 * @exception InputFileEmptyException On empty file.
	 * @exception IOException On invalid input
	 * @exception NumberFormatException when input containes other than integer
	 */
	private void populatearray(MyArrayI element) {
		try {
			MyLogger.getInstance().writeMessage("populatearray() calls poll() for parsing input line by line and adds to myarray instances ", MyLogger.DebugLevel.POPULATEMYARRAYVISITOR);
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
			MyLogger.getInstance().writeMessage("if input not right it throws exceptions", MyLogger.DebugLevel.POPULATEMYARRAYVISITOR);

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