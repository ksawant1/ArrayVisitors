package arrayvisitors.visitors;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.exceptions.InputFileEmptyException;
import arrayvisitors.util.FileProcessor;
import arrayvisitors.util.Validator;

import java.io.IOException;

/**
 * for filling instances of my array with integers from corresponding input files
 */
public class PopulateMyArrayVisitor implements Visitor {
	FileProcessor fileProcessor;
	Validator validator;

	public void setFileProcessor(FileProcessor fileProcessor) {
		this.fileProcessor = fileProcessor;
	}

	//mainly calls addarr()
	public void visit(Element element) {
		addarr((MyArrayI) element);
	}

	public void addarr(MyArrayI element) {
		try {
			// calls poll() for parsing input line by line
			String line = fileProcessor.poll();
			if (line == null)
				throw new InputFileEmptyException("input file is empty");
			while (line != null) {
				element.add(Integer.parseInt(line));
				line = fileProcessor.poll();
				throw new NumberFormatException("Line in input file does not contain integer");
			}
		} catch (NumberFormatException | IOException | InputFileEmptyException e) {
			System.exit(0);
		}
	}


}