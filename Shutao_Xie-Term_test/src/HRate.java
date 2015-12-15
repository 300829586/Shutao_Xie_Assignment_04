import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;

//JFrame WINDOW CLASS ++++++++++++++++++++++++++++++++++++++++++
public class HRate extends JFrame {

	// PRIVATE INSTANCE VARIABLES +++++++++++++++++++++++++++++++
	private JPanel _contentPane;
	private JTextField _txtAge;
	private JTextField _txtMaximum;
	private JTextField _txtTarget;

	//CONSTRUCTOR
	public HRate() {
		_initialize();	
	}  
	
	// PRIVATE METHODS +++++++++++++++++++++++++++++++++++++++++++
	private void _initialize(){	
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		_contentPane = new JPanel();
		_contentPane.setBackground(Color.BLUE);
		_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(_contentPane);
		_contentPane.setLayout(null);
		
		JLabel _lblCalculatorApp = new JLabel("Target Heart Rate");
		_lblCalculatorApp.setForeground(Color.YELLOW);
		_lblCalculatorApp.setBackground(Color.WHITE);
		_lblCalculatorApp.setHorizontalAlignment(SwingConstants.CENTER);
		_lblCalculatorApp.setFont(new Font("Tahoma", Font.BOLD, 18));
		_lblCalculatorApp.setBounds(119, 11, 207, 19);
		_contentPane.add(_lblCalculatorApp);
		
		JRadioButton _rdbtnMale = new JRadioButton("Male");
		_rdbtnMale.setFont(new Font("Tahoma", Font.BOLD, 11));
		_rdbtnMale.setForeground(Color.BLACK);
		_rdbtnMale.setBackground(Color.WHITE);
		_rdbtnMale.setSelected(true);
		_rdbtnMale.setBounds(120, 49, 76, 19);
		_contentPane.add(_rdbtnMale);
		
		JRadioButton _rdbtnFemale = new JRadioButton("Female");
		_rdbtnFemale.setFont(new Font("Tahoma", Font.BOLD, 11));
		_rdbtnFemale.setBackground(Color.WHITE);
		_rdbtnFemale.setBounds(236, 49, 76, 19);
		_contentPane.add(_rdbtnFemale);
		
		JLabel _lblAge = new JLabel("My Age: ");
		_lblAge.setForeground(Color.YELLOW);
		_lblAge.setFont(new Font("Tahoma", Font.BOLD, 11));
		_lblAge.setHorizontalAlignment(SwingConstants.RIGHT);
		_lblAge.setBounds(31, 79, 104, 22);
		_contentPane.add(_lblAge);
		
		_txtAge = new JTextField();
		_txtAge.setBounds(145, 79, 86, 20);
		_contentPane.add(_txtAge);
		_txtAge.setColumns(10);
		
		JLabel _lblMaximum = new JLabel("Maximum Heart Rate:");
		_lblMaximum.setFont(new Font("Tahoma", Font.BOLD, 11));
		_lblMaximum.setForeground(Color.YELLOW);
		_lblMaximum.setHorizontalAlignment(SwingConstants.RIGHT);
		_lblMaximum.setBounds(28, 153, 145, 21);
		_contentPane.add(_lblMaximum);
		
		JLabel _lblTarget = new JLabel("Target-heart-rate Range:");
		_lblTarget.setFont(new Font("Tahoma", Font.BOLD, 11));
		_lblTarget.setForeground(Color.YELLOW);
		_lblTarget.setHorizontalAlignment(SwingConstants.RIGHT);
		_lblTarget.setBounds(10, 185, 163, 24);
		_contentPane.add(_lblTarget);
		
		_txtMaximum = new JTextField();
		_txtMaximum.setBounds(181, 153, 177, 20);
		_contentPane.add(_txtMaximum);
		_txtMaximum.setColumns(10);
		
		_txtTarget = new JTextField();
		_txtTarget.setBounds(181, 185, 177, 22);
		_contentPane.add(_txtTarget);
		_txtTarget.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBackground(Color.WHITE);
		btnCalculate.setForeground(Color.BLACK);
		btnCalculate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCalculate.setBounds(155, 119, 118, 23);
		_contentPane.add(btnCalculate);
		
	}
	
	public class Listener implements ActionListener{
	@Override
	public void actionPerformed(ActionEvent event) {
	        
       if(event.getSource()==this._txtAge){
    	   try {
    	  	   int maxHRate=220-Integer.parseInt(this._txtAge.getText());
    	  	   double targetMax= maxHRate*0.85;
    	  	   double targetMin=maxHRate*0.5;

    	  	 this._txtMaximum=
		} catch (Exception e) {
			
			System.out.println("Hey that was just wrong");

    	  }
}}}	
}
