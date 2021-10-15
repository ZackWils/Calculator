/**
 * Class Name: EmptyOperandException
 * Purpose: An exception to be thrown when an operand is empty
 * @author Zachari Wilson
 * Date: April 9, 2021
 */

public class EmptyOperandException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	EmptyOperandException()
	{
		super("Empty Operand");
	}
	
}
