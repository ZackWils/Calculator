/**
 * Class Name: GUICalculator
 * Purpose: To provide an interface to make the calculator class user friendly. To calculate stuff.
 * @author Zachari Wilson
 * Date: April 9, 2021
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUICalculator extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem;
	JPanel btnPanel;
	JTextField textField;
	JButton button;
	Font font;
	Calculator c;
	GUICalculator() throws EmptyOperandException, LongOperandException, ArithmeticException
	{
		super("Calculator");
		setSize(300, 365);  //suggested size
		setResizable(false);  
		setLocationRelativeTo(null);  
		setLayout( new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);  
		
		c = new Calculator();
		menuBar = new JMenuBar();
		font = new Font("SansSerif", Font.PLAIN, 22);
		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(6, 4));
		
		textField = new JTextField("0.0");
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setEditable(false);
		textField.setFont(font);
		textField.setBackground(Color.WHITE);
		
		makeButton("C");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
					c.clear();
					textField.setText("0.0");
			}
		}
		);
		btnPanel.add(button);
		
		makeImgButton("icons/back.png", 25, 25);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					c.setOperand(c.backspace(c.getOperand()));
					if (c.getOperand().isEmpty())
						textField.setText("0.0");
					else
					{
					textField.setText(c.getOperand());
					}
				}
				catch(EmptyOperandException exception)
				{
					textField.setText("empty operand");
				}
			}
		}
		);
		btnPanel.add(button);
		
		makeButton("%");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					c.setOperand(c.findPercentage(c.getOperand()));
					textField.setText(c.getOperand());
				}
				catch(EmptyOperandException exception)
				{
					textField.setText("empty operand");
				}
			}
		}
		);
		btnPanel.add(button);
		
		makeButton("+/-");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					if (c.getOperand().length() != 0 && c.getOperand().charAt(0) == '-')
					{
						c.togglePlusMinus(true);
					}
					else
					{
						c.togglePlusMinus(false);
					}
					textField.setText(c.getOperand());
				}
				catch(EmptyOperandException exception)
				{
					textField.setText("empty operand");
				}
			}
		}
		);
		btnPanel.add(button);
		
		makeImgButton("icons/sqr.png", 25, 20);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					c.setOperand(c.findSquared(c.getOperand()));
					textField.setText(c.getOperand());
				}
				catch(EmptyOperandException exception)
				{
					textField.setText("empty operand");
				}
			}
		}
		);
		btnPanel.add(button);
		
		makeImgButton("icons/sqrt.png", 25, 25);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					c.setOperand(c.findSquareRoot(c.getOperand()));
					textField.setText(c.getOperand());
				}
				catch(EmptyOperandException exception)
				{
					textField.setText("empty operand");
				}
			}
		}
		);
		btnPanel.add(button);
		
		makeButton("");
		btnPanel.add(button);
		
		makeButton("/");
		button.addActionListener(new operatorListener());
		btnPanel.add(button);
		
		makeButton("7");
		button.addActionListener(new numberListener());
		btnPanel.add(button);
		
		makeButton("8");
		button.addActionListener(new numberListener());
		btnPanel.add(button);
		
		makeButton("9");
		button.addActionListener(new numberListener());
		btnPanel.add(button);
		
		makeButton("x");
		button.addActionListener(new operatorListener());
		btnPanel.add(button);
		
		makeButton("4");
		button.addActionListener(new numberListener());
		btnPanel.add(button);

		makeButton("5");
		button.addActionListener(new numberListener());
		btnPanel.add(button);
		
		makeButton("6");
		button.addActionListener(new numberListener());
		btnPanel.add(button);
		
		makeButton("-");
		button.addActionListener(new operatorListener());
		btnPanel.add(button);
		
		makeButton("1");
		button.addActionListener(new numberListener());
		btnPanel.add(button);
		
		makeButton("2");
		button.addActionListener(new numberListener());
		btnPanel.add(button);
		
		makeButton("3");
		button.addActionListener(new numberListener());
		btnPanel.add(button);
		
		makeButton("+");
		button.addActionListener(new operatorListener());
		btnPanel.add(button);
		
		makeButton("");
		btnPanel.add(button);
		
		makeButton("0");
		button.addActionListener(new numberListener());
		btnPanel.add(button);
		
		makeButton(".");
		button.addActionListener(new numberListener());
		btnPanel.add(button);
		
		makeButton("=");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				try
				{
					String result = Double.toString(c.calculate());
					textField.setText(result);
					c.clear();
					c.setOperand(result);
				}
				catch(EmptyOperandException exception)
				{
					textField.setText("empty operand");
				}
				catch(ArithmeticException exception)
				{
					textField.setText("0.0");
					c.clear();
				}
			}
		}
		);
		btnPanel.add(button);
		
		makeFileMenu();
		makeConvertMenu();
		makeHelpMenu();
		
		
		

		this.setJMenuBar(menuBar);
		
		this.add(textField, BorderLayout.NORTH);
		this.add(btnPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	private void makeOptionPane(String title, String text)
	{
		JOptionPane.showInternalMessageDialog(null, text,
				title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	private class operatorListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				c.buildExpression(e.getActionCommand());
			}
			catch(EmptyOperandException exception)
			{
				textField.setText("empty operand");
			}
		}
		
	}
	
	private class numberListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			try
			{
			c.buildOperand(e.getActionCommand());
			}
			catch (LongOperandException exception)
			{
				textField.setText("long operand");
			}
			textField.setText(c.getOperand());
		}
		
	}
	
	private void makeImgButton(String img, int width, int height)
	{
		ImageIcon icon = new ImageIcon(img);
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;  
		button = new JButton(new ImageIcon( newimg ));
		button.setBackground(Color.WHITE);
	}
	
	private void makeButton(String label)
	{
		button = new JButton(label);
		button.setBackground(Color.WHITE);
		button.setFont(font);
	}
	
	private void makeFileMenu()
	{
		menu = new JMenu("File");
		
		menuItem = new JMenuItem("Exit");
		
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		}
		);
		
		menu.add( menuItem );
		
		menuBar.add(menu);
	}
	
	private void makeConvertMenu()
	{
		menu = new JMenu("Convert");
		
		menuItem = new JMenuItem("Hex");
		
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
				textField.setText(c.convertHex(c.getOperand()));
				}
				catch (EmptyOperandException exception)
				{
					textField.setText("empty operand");
				}
				catch (LongOperandException exception)
				{
					textField.setText("long operand");
				}
			}
		}
		);
		
		menu.add( menuItem );
		
		menuItem = new JMenuItem("Dec");

		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				textField.setText(c.getOperand());
			}
		}
		);
		
		menu.add( menuItem );
		
		menuItem = new JMenuItem("Oct");
		

		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
				textField.setText(c.convertOct(c.getOperand()));
				}
				catch (EmptyOperandException exception)
				{
					textField.setText("empty operand");
				}
				catch (LongOperandException exception)
				{
					textField.setText("long operand");
				}
			}
		}
		);
		
		menu.add( menuItem );
		
		menuItem = new JMenuItem("Bin");

		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
				textField.setText(c.convertBin(c.getOperand()));
				}
				catch (EmptyOperandException exception)
				{
					textField.setText("empty operand");
				}
				catch (LongOperandException exception)
				{
					textField.setText("long operand");
				}
			}
		}
		);
		
		menu.add( menuItem );
		
		menuBar.add(menu);
	}
	
	
	private void makeHelpMenu()
	{
		menu = new JMenu("Help");
		
		menuItem = new JMenuItem("How To Use");
		
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				makeOptionPane("How To Use", "It's pretty self explanatory, it's a calculator. Click the buttons.");
			}
		}
		);
		
		menu.add( menuItem );
		
		menuItem = new JMenuItem("About");
		
		menuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				makeOptionPane("About", "Made by Zachari Wilson\nApril 2021.");
			}
		}
		);
		
		menu.add( menuItem );
		
		menuBar.add(menu);
	}
	public static void main(String [] args) throws EmptyOperandException, LongOperandException, ArithmeticException
	{
		new GUICalculator();
	}
}
