/**
 * Class Name: LongOperandException
 * Purpose: Exception to be thrown when an operand is too long
 * @author Zachari Wilson
 * Date: April 9, 2021
 */

public class LongOperandException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	LongOperandException()
	{
		super("Long Operand");
	}
	
}
