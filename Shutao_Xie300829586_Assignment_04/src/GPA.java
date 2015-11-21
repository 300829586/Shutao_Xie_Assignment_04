import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class GPA extends JFrame {

	//PRIVATE VARIABLES
	private Integer[] _creditHoursArray = new Integer[]{1,2,3,4};
	private String[] _gradeArray = new String[]{"A+ 90-100%", "A 80-89%", "B+ 75-79%",	"B 70-74%",	"C+ 65-69%", "C 60-64%", "D+ 55-59%", "D 50-54%", "F 0-49%"};	
	private double [] _gradePointArray = {4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 0.5, 0.0};
	private ArrayList<JTextField>	_courseCode = new ArrayList<JTextField>();
	private ArrayList<JComboBox> 	_courseHours = new ArrayList<JComboBox>(), 
									_courseGrade = new ArrayList<JComboBox>();
	private ArrayList<JComponent> _componentError = new ArrayList<JComponent>();
	
	//UI COMPONENTS
	private JPanel 	_contentPane, 
					_courseScrollPane;
	private JLabel 	_creditHoursLabel, _currentGPALabel, _numOfCoursesLabel,_numLabel, 
	                _courseCodeLabel, _courseHoursLabel, _gradeLabel, 
				   	_GPALabel;
	private JTextField 	_creditHrstxtField, 
						_currentGPAtxtField;
	private JComboBox _numOfCourseComboBox;		
	private JButton _calculateBtn;	
	private JScrollPane _scrollPane;
	private GridBagLayout _gLayout;	
	private Listener listener = new Listener();
	
	//CONSTRUCTOR
	public GPA() {
		_initialize();
		_addCourses((int)(_numOfCourseComboBox.getSelectedItem()));		
	}  
	
	private void _initialize(){		
		
		this._contentPane = new JPanel();
		this._contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this._contentPane);
		this._contentPane.setLayout(null);	
		
		this.setTitle("GPA Calculator");		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setSize(528, 450);
		this.setResizable(false);		
		this.setLocationRelativeTo(null);

				
		this._creditHoursLabel = new JLabel("Credit hours earned:");
		this._creditHoursLabel.setBounds(28, 17, 147, 20);
		this._contentPane.add(this._creditHoursLabel);
		
		this._creditHrstxtField = new JTextField();
		this._creditHrstxtField.setBounds(154, 17, 83, 20);
		this._contentPane.add(this._creditHrstxtField);
		this._creditHrstxtField.setColumns(6);

		this._currentGPALabel = new JLabel("Current GPA:");
		this._currentGPALabel.setBounds(320, 17, 104, 20);

		this._contentPane.add(this._currentGPALabel);
		
		this._currentGPAtxtField = new JTextField();
		this._currentGPAtxtField.setBounds(408, 16, 90, 20);
		this._contentPane.add(this._currentGPAtxtField);
		this._currentGPAtxtField.setColumns(10);
		
		this._numOfCoursesLabel = new JLabel("Number of Courses:");
		this._numOfCoursesLabel.setBounds(320, 47, 141, 20);
		this._contentPane.add(this._numOfCoursesLabel);
		
		this._numOfCourseComboBox = new JComboBox(new Integer[]{0,1,2,3,4,5,6,7,8});
		this._numOfCourseComboBox.setEditable(true);
		this._numOfCourseComboBox.setBounds(442, 48, 56, 20);
		this._numOfCourseComboBox.addActionListener(this.listener);
		this._contentPane.add(this._numOfCourseComboBox);

		this._numLabel = new JLabel("Nr.");
		this._numLabel.setBounds(39, 86, 37, 36);
		this._contentPane.add(this._numLabel);
		
		this._courseCodeLabel = new JLabel("Course Code:");
		this._courseCodeLabel.setBounds(113, 86, 83, 36);
		this._contentPane.add(this._courseCodeLabel);
		
		this._courseHoursLabel = new JLabel("Course Hours:");
		this._courseHoursLabel.setBounds(249, 85, 90, 37);
		this._contentPane.add(this._courseHoursLabel);
		
		this._calculateBtn = new JButton("Calculate");
		this._calculateBtn.setBounds(408, 366, 90, 20);
		this._calculateBtn.addActionListener(this.listener);
		this._contentPane.add(this._calculateBtn);
			
		this._GPALabel = new JLabel("Your GPA is:");
		this._GPALabel.setBounds(199, 394, 147, 20);
		this._contentPane.add(this._GPALabel);
		
		this._scrollPane = new JScrollPane();
		this._scrollPane.setBounds(25, 115, 475, 240);
		this._scrollPane.setAutoscrolls(true);
		this._contentPane.add(this._scrollPane);
		
		this._courseScrollPane = new JPanel();
		this._scrollPane.setColumnHeaderView(this._courseScrollPane);
		
		this._gLayout = new GridBagLayout();
		this._gLayout.columnWidths = new int[] {25, 25, 25, 25};
		this._gLayout.rowHeights = new int[]{25, 25, 25, 25, 25, 25,25,25};
		this._gLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		this._gLayout.rowWeights = new double[]{0, 0, 0, 0, 0, 0,0,0};
		this._courseScrollPane.setLayout(this._gLayout);	
		
		this._gradeLabel = new JLabel("Grade:");
		_gradeLabel.setBounds(383, 86, 54, 36);
		_contentPane.add(_gradeLabel);
		
	}
	
	private void _addCourses(int numOfCourses){
	
		_resetScrollPane();
		
		for(int i = 0; i < numOfCourses; i++){
			
			JTextField codeTxt = new JTextField("");
			JComboBox hoursCom = new JComboBox(new Integer[]{1,2,3,4,5,6});
			JComboBox gradeCom = new JComboBox(this._gradeArray);
			
			this._addUIComponent(this._courseScrollPane, new JLabel(" "+ (1+i)),0,i,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE);
			this._addUIComponent(this._courseScrollPane, codeTxt,1,i,1,1,GridBagConstraints.EAST,GridBagConstraints.NONE);			
			this._addUIComponent(this._courseScrollPane, hoursCom,2,i,1,1,GridBagConstraints.EAST,GridBagConstraints.NONE);
			this._addUIComponent(this._courseScrollPane, gradeCom,3,i,1,1,GridBagConstraints.EAST,GridBagConstraints.HORIZONTAL);
		
			this._courseCode.add(codeTxt);
			this._courseHours.add(hoursCom);
			this._courseGrade.add(gradeCom);			
		}

		this._contentPane.revalidate();				
	}
	
	private void _addUIComponent(JPanel thePanel, JComponent component, int gridx, int gridy, int gridwidth, int gridheight, int anchor, int stretch ){
		
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.insets = new Insets(1,1,1,1);
		constraints.anchor = anchor;
		constraints.fill = stretch;
			
		if(component.getClass().getName() != "javax.swing.JLabel"){
			Dimension dimension = component.getPreferredSize();
			dimension.width = 130;
			component.setPreferredSize(dimension);
		}
		
		thePanel.add(component, constraints);		
	}
	
	private void _resetScrollPane(){

		this._courseScrollPane.removeAll();		
		this._courseCode.clear();
		this._courseHours.clear();
		this._courseGrade.clear();
		this._componentError.clear();		
	}

	public class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == _numOfCourseComboBox){
				
				try{
					int number = (int)(_numOfCourseComboBox.getSelectedItem()); 
					if(number < 0) throw new NumLimit("Number of Course cannot be negative!");
					_addCourses(number);
				}
				catch(NumLimit nex){
					JOptionPane.showMessageDialog(null, nex.getMessage());					
					_numOfCourseComboBox.setSelectedIndex(0);
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Error! Invalid number!");	
					_numOfCourseComboBox.setSelectedIndex(0);
				}
				
			} else if (e.getSource() == _calculateBtn) {
								
				try{
					if(!_componentError.isEmpty()){
						for( JComponent component : _componentError){
							component.setBackground(Color.WHITE);
						}
						_componentError.clear();
					}
					
					double currentHrsEarned = 0;
					double currentGPA = 0;
					
					if(_creditHrstxtField.getText().isEmpty() && _currentGPAtxtField.getText().isEmpty()){ 
						_creditHrstxtField.setText("0"); 
						_currentGPAtxtField.setText("0");
					}
					
					currentHrsEarned = Double.parseDouble(_creditHrstxtField.getText());
					currentGPA = Double.parseDouble(_currentGPAtxtField.getText());
										
					if(currentHrsEarned < 0 ){
						_componentError.add(_creditHrstxtField);
						throw new Exception("Error! Credit Hours Earned cannot be negative!");
					}
					
					if(currentGPA < 0){
						_componentError.add(_currentGPAtxtField);
						throw new Exception("Error! Current GPA cannot be negative!");
					}
					
					if(currentGPA > 0 && currentHrsEarned <= 0){
						_componentError.add(_creditHrstxtField);
						throw new Exception("Error! Credit hours earned conflicts with Current GPA!");
					}	
					
					if(currentGPA > 4.5){
						_currentGPAtxtField.setBackground(Color.PINK);
						_componentError.add(_currentGPAtxtField);
						throw new Exception("Error! Current GPA cannot be bigger than 4.5!");
					}
					
					for(int i = 0; i < _courseCode.size(); i++){						
						if(_courseCode.get(i).getText().isEmpty()) {
							_componentError.add(_courseCode.get(i));
							throw new Exception("Fill the Course Code please!");
						}
					}					
									
					double sumCreditPoints = 0;
					double sumHourCredit = 0;
					double hoursCredit = 0;
					double GPA = 0;
					
					for(int i = 0; i < _courseHours.size(); i++){
						hoursCredit = _courseHours.get(i).getSelectedIndex() + 1;
						sumHourCredit += hoursCredit;
						sumCreditPoints += _gradePointArray[_courseGrade.get(i).getSelectedIndex()] * hoursCredit;						
					}

					if(currentHrsEarned == 0 ){
						GPA = (sumCreditPoints) / (sumHourCredit);
					}else{
						GPA = (sumCreditPoints + (currentGPA * currentHrsEarned)) / (sumHourCredit + currentHrsEarned);
					}				
										
					_GPALabel.setText(String.format("Your GPA is: %.2f", GPA));
					
					if(!_componentError.isEmpty()){
						for( JComponent c : _componentError){
							c.setBackground(Color.WHITE);
						}
						_componentError.clear();
					}
					
					JOptionPane.showMessageDialog(null, _GPALabel.getText());
					
				}catch(Exception ex){
					_GPALabel.setText(String.format("Your GPA is:"));
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}							
			}
		}		
	}
	
	public class NumLimit extends Exception{
		public NumLimit(String msg) {
			super(msg);
		}
	}
}
