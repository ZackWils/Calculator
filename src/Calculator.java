/**
 * Class Name: Calculator
 * Purpose: To provide the functionality of a calculator
 * @author Zachari Wilson
 * Date: April 9, 2021
 */

import java.util.ArrayList;

public class Calculator {
	
	private String operand; //Current unit in expression being displayed/modified
	private String operator; //+ - / * %
	
	private boolean decimalPressed; //To prevent multiple decimal points
	private ArrayList<String> list; //Stores the list of operands and operators in the current expression
	
	Calculator()
	{
		this.operand = "";
		this.operator = "";
		
		this.decimalPressed = false;
		
		this.list = new ArrayList<String>();
	}
	
	//Getters and Setters
	public String getOperand() {
		return operand;
	}

	public void setOperand(String operand) {
		this.operand = operand;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public boolean isDecimalPressed() {
		return decimalPressed;
	}

	public void setDecimalPressed(boolean decimalPressed) {
		this.decimalPressed = decimalPressed;
	}
	//Getters and Setters
	
	//Clears all fields, called when "C" and "=" buttons are clicked
	public void clear()
	{
		this.operand = "";
		this.operator = "";
		
		this.decimalPressed = false;
		
		this.list.clear();
	}
	
	//Removes the last character of the given string value
	public String backspace(String value) throws EmptyOperandException
	{
		checkEmpty(value);
		if (value.charAt(value.length() - 1) == '.') decimalPressed = false;
		value = value.substring(0, value.length() - 1);
		return value;
	}
	
	//Returns the percentage in decimal form
	public String findPercentage(String value) throws EmptyOperandException
	{
		checkEmpty(value);
		value = Double.toString(Double.parseDouble(value) / 100);
		return value;
	}
	
	//Toggles the sign of the current operand depending on the boolean flag
	public String togglePlusMinus(boolean flag) throws EmptyOperandException
	{
		checkEmpty(operand);
		if(flag)
			operand = operand.replaceFirst("-", "");
		else
		{
			String tmp = operand;
			operand = "-" + tmp;
		}
		
		return operand;
	}
	
	//Returns the value squared
	public String findSquared(String value) throws EmptyOperandException
	{
		checkEmpty(value);
		
		double num = Double.parseDouble(value);
		
		value = Double.toString(num * num);
		
		return value;
	}
	
	//Returns the square root of the value
	public String findSquareRoot(String value) throws EmptyOperandException
	{
		checkEmpty(value);
		
		value = Double.toString( Math.sqrt( Double.parseDouble(value) ) );
		
		return value;
	}
	
	//Appends the value to the current operand
	public void buildOperand(String value) throws LongOperandException
	{
		checkLong(operand, value);
		
		if(value.equals("."))
		{
			if(!decimalPressed)
			{
				operand += value;
				decimalPressed = true;
			}
		}
		else
			operand += value;
	}
	
	//Adds the current operand and operator to the expression list
	public void buildExpression(String value) throws EmptyOperandException
	{
		checkEmpty(operand);
		operator = value;
		
		list.add(operand);
		list.add(operator);
		operand = "";
		operator = "";
		decimalPressed = false;
		
	}
	
	//Calculates and returns the result of the current expression contained in list
	public double calculate() throws EmptyOperandException, ArithmeticException
	{
		checkEmpty(operand);
		list.add(operand);
		if (list.size() == 0)
			return 0.0;
		
		String last = list.get(list.size() - 1);
		if (last.equals("/") || last.equals("x") || last.equals("+") || last.equals("-")) list.remove(list.size() - 1);

		
		double operand1, operand2;
		String operator;
		
		
		
		for(int i = 0; i < list.size() - 1; i++)
		{
			operator = list.get(i);
			if(operator.equals("/"))
			{
				operand1 = Double.parseDouble( list.get(i - 1) );
				operand2 = Double.parseDouble( list.get(i + 1) );
				
				list.set( i - 1 , Double.toString( operand1 / operand2 ) );
				
				list.remove(i);
				list.remove(i);
				i -= 1;
			}
			else if (operator.equals("x"))
			{
				operand1 = Double.parseDouble( list.get(i - 1) );
				operand2 = Double.parseDouble( list.get(i + 1) );
				
				list.set( i - 1 , Double.toString( operand1 * operand2 ) );
				
				list.remove(i);
				list.remove(i);
				i -= 1;
			}
		}
		
		for(int i = 0; i < list.size() - 1; i++)
		{
			operator = list.get(i);
			if(operator.equals("+"))
			{
				operand1 = Double.parseDouble( list.get(i - 1) );
				operand2 = Double.parseDouble( list.get(i + 1) );
				
				list.set( i - 1 , Double.toString( operand1 + operand2 ) );
				
				list.remove(i);
				list.remove(i);
				i -= 1;
			}
			else if (operator.equals("-"))
			{
				operand1 = Double.parseDouble( list.get(i - 1) );
				operand2 = Double.parseDouble( list.get(i + 1) );
				
				list.set( i - 1 , Double.toString( operand1 - operand2 ) );
				
				list.remove(i);
				list.remove(i);
				i -= 1;
			}
		}
		return Double.parseDouble( list.get(0) );
	}
	
	//Converts the decimal value to hexadecimal and returns it
	public String convertHex(String value) throws EmptyOperandException, LongOperandException
	{
		checkEmpty(value);
		int num = (int) Math.round(Double.parseDouble(value));
		int remainder;
		value = "";
		while (num != 0)
		{
			remainder = num % 16;
			if (remainder > 9)
			{
				value = (char) (remainder + 55) + value;
			}
			else
			{
			value = Integer.toString(num % 16) + value;
			}
			num = (int) Math.floor(num / 16);
		}
		return value;
	}
	
	//Converts the decimal value to octal and returns it
	public String convertOct(String value) throws EmptyOperandException, LongOperandException
	{
		checkEmpty(value);
		int num = (int) Math.round(Double.parseDouble(value));
		value = "";
		while (num != 0)
		{
			value = Integer.toString(num % 8) + value;
			num = (int) Math.floor(num / 8);
		}
		return value;
	}
	
	//Converts the decimal value to binary and returns it
	public String convertBin(String value) throws EmptyOperandException, LongOperandException
	{
		checkEmpty(value);
		int num = (int) Math.round(Double.parseDouble(value));
		value = "";
		while (num != 0)
		{
			value = Integer.toString(num % 2) + value;
			num = (int) Math.floor(num / 2);
		}
		return value;
	}
	
	//Returns a string representing the calculator object
	public String toString()
	{
		String returnString = "";
		returnString += "Current Expression: ";
		for(int i = 0; i < list.size(); i++)
		{
			returnString += list.get(i) + ' ';
		}
		returnString += "\nCurrent operand: " + operand;
		returnString += "\nCurrent operator: " + operator;
		return returnString;
	}
	
	//Check is the given value is empty, throws EmptyOperandexception if it is
	private void checkEmpty(String value) throws EmptyOperandException
	{
		if(value.isEmpty())
			throw new EmptyOperandException();
	}
	
	//Checks if num1 added to num2 is too long
	private void checkLong(String num1, String num2) throws LongOperandException
	{
		String combined = num1 + num2;
		if(combined.length() > 15)
			throw new LongOperandException();
	}
	
}
