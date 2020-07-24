package arrayvisitors.util;

public class MyLogger {

	private static MyLogger uniqueInstance = new MyLogger();
	private MyLogger(){}

	public static MyLogger getInstance(){
		return uniqueInstance;

	}
	public static enum DebugLevel {
		DRIVER, CONSTRUCTOR, FILEPROCESSOR, COMMONINTSVISITOR, MISSINGINTSVISITOR, POPULATEMYARRAYVISITOR, MYARRAY,
		MYARRAYLIST, RESULTS, NONE
	};

	private static DebugLevel debugLevel;

	/**
	 * levels
	 * debugvalue=0 for Driver class
	 * debugvalue=1 for Constructor
	 * debugvalue=2 for Fileprocessor class
	 * debugvalue=3 for CommonintsVisitor class
	 * debugvalue=4 for MissingIntsVisitor class
	 * debugvalue=5 for PopulateMyArray class
	 * debugvalue=6 for Myarray class
	 * debugvalue=7 for Myarraylist class
	 * debugvalue=8 for Results class
	 */

	public void setDebugValue(int levelIn) {
		switch (levelIn) {
			case 0:
				debugLevel = DebugLevel.DRIVER;
				break;
			case 1:
				debugLevel = DebugLevel.CONSTRUCTOR;
				break;
			case 2:
				debugLevel = DebugLevel.FILEPROCESSOR;
				break;
			case 3:
				debugLevel = DebugLevel.COMMONINTSVISITOR;
				break;
			case 4:
				debugLevel = DebugLevel.MISSINGINTSVISITOR;
				break;
			case 5:
				debugLevel = DebugLevel.POPULATEMYARRAYVISITOR;
				break;
			case 6:
				debugLevel = DebugLevel.MYARRAY;
				break;
			case 7:
				debugLevel = DebugLevel.MYARRAYLIST;
				break;
			case 8:
				debugLevel = DebugLevel.RESULTS;
				break;
			default:
				debugLevel = DebugLevel.NONE;
				break;
		}
	}

	public void setDebugValue(DebugLevel indebugValue) {
		MyLogger.debugLevel = indebugValue;
	}

	public void writeMessage(String message, DebugLevel levelIn) {
		if (levelIn == debugLevel)
			System.out.println(message);
	}

	public String toString() {
		return "The debug level has been set to the following " + debugLevel;
	}
}
