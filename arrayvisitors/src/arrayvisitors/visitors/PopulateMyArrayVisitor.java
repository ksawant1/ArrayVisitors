package arrayvisitors.visitors;
import arrayvisitors.adt.MyArrayI;
import arrayvisitors.util.FileProcessor;

/**
 * for filling instances of my array with integers from corresponding input files
 */
public class PopulateMyArrayVisitor implements Visitor {
	FileProcessor fileProcessor;

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
				System.out.println("Input File Empty");
			while (line != null) {
				//if not null the integer will be added in array
				element.add(Integer.parseInt(line));
				line = fileProcessor.poll();
			}
			element.printelements();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}