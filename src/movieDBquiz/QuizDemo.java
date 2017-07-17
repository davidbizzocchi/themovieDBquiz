package movieDBquiz;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.AbstractButton;
import java.awt.Font;

public class QuizDemo {

	public JFrame AppDemo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel titleTxtLabel;
	private JTextArea QuestionTxt;
	private JPanel panel;
	private JRadioButton rdBtnA;
	private JRadioButton rdBtnB;
	private JRadioButton rdBtnC;
	private JRadioButton rdBtnD;
	private JTextArea OptTxtA;
	private JTextArea OptTxtB;
	private JTextArea OptTxtC;
	private JTextArea OptTxtD;
	private JPanel panel_1;
	private JButton exitBtn;
	private JButton submitBtn;
	
	private char correctAnswer;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizDemo window = new QuizDemo();
					window.AppDemo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QuizDemo() {
		initializeUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		AppDemo = new JFrame();
		AppDemo.setBounds(100, 100, 600, 599);
		AppDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 50, 0, 0, 0, 75, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		AppDemo.getContentPane().setLayout(gridBagLayout);
		
		titleTxtLabel = new JLabel("Quiz Demo");
		GridBagConstraints gbc_titleTxtLabel = new GridBagConstraints();
		gbc_titleTxtLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleTxtLabel.gridx = 1;
		gbc_titleTxtLabel.gridy = 1;
		AppDemo.getContentPane().add(titleTxtLabel, gbc_titleTxtLabel);
		
		QuestionTxt = new JTextArea();
		QuestionTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		QuestionTxt.setEditable(false);
		QuestionTxt.setWrapStyleWord(true);
		QuestionTxt.setLineWrap(true);
		GridBagConstraints gbc_QuestionTxt = new GridBagConstraints();
		gbc_QuestionTxt.fill = GridBagConstraints.BOTH;
		gbc_QuestionTxt.insets = new Insets(5, 45, 5, 0);
		gbc_QuestionTxt.gridx = 1;
		gbc_QuestionTxt.gridy = 3;
		AppDemo.getContentPane().add(QuestionTxt, gbc_QuestionTxt);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 5;
		AppDemo.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{40, 0, 0};
		gbl_panel.rowHeights = new int[]{25, 25, 25, 25, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		rdBtnA = new JRadioButton("A");
		buttonGroup.add(rdBtnA);
		GridBagConstraints gbc_rdBtnA = new GridBagConstraints();
		gbc_rdBtnA.insets = new Insets(0, 0, 5, 5);
		gbc_rdBtnA.gridx = 0;
		gbc_rdBtnA.gridy = 0;
		panel.add(rdBtnA, gbc_rdBtnA);
		
		OptTxtA = new JTextArea();
		OptTxtA.setEditable(false);
		GridBagConstraints gbc_OptTxtA = new GridBagConstraints();
		gbc_OptTxtA.insets = new Insets(0, 0, 5, 0);
		gbc_OptTxtA.fill = GridBagConstraints.BOTH;
		gbc_OptTxtA.gridx = 1;
		gbc_OptTxtA.gridy = 0;
		panel.add(OptTxtA, gbc_OptTxtA);
		
		rdBtnB = new JRadioButton("B");
		buttonGroup.add(rdBtnB);
		GridBagConstraints gbc_rdBtnB = new GridBagConstraints();
		gbc_rdBtnB.insets = new Insets(0, 0, 5, 5);
		gbc_rdBtnB.gridx = 0;
		gbc_rdBtnB.gridy = 1;
		panel.add(rdBtnB, gbc_rdBtnB);
		
		OptTxtB = new JTextArea();
		OptTxtB.setEditable(false);
		GridBagConstraints gbc_OptTxtB = new GridBagConstraints();
		gbc_OptTxtB.insets = new Insets(0, 0, 5, 0);
		gbc_OptTxtB.fill = GridBagConstraints.BOTH;
		gbc_OptTxtB.gridx = 1;
		gbc_OptTxtB.gridy = 1;
		panel.add(OptTxtB, gbc_OptTxtB);
		
		rdBtnC = new JRadioButton("C");
		buttonGroup.add(rdBtnC);
		GridBagConstraints gbc_rdBtnC = new GridBagConstraints();
		gbc_rdBtnC.insets = new Insets(0, 0, 5, 5);
		gbc_rdBtnC.gridx = 0;
		gbc_rdBtnC.gridy = 2;
		panel.add(rdBtnC, gbc_rdBtnC);
		
		OptTxtC = new JTextArea();
		OptTxtC.setEditable(false);
		GridBagConstraints gbc_OptTxtC = new GridBagConstraints();
		gbc_OptTxtC.insets = new Insets(0, 0, 5, 0);
		gbc_OptTxtC.fill = GridBagConstraints.BOTH;
		gbc_OptTxtC.gridx = 1;
		gbc_OptTxtC.gridy = 2;
		panel.add(OptTxtC, gbc_OptTxtC);
		
		rdBtnD = new JRadioButton("D");
		buttonGroup.add(rdBtnD);
		GridBagConstraints gbc_rdBtnD = new GridBagConstraints();
		gbc_rdBtnD.insets = new Insets(0, 0, 0, 5);
		gbc_rdBtnD.gridx = 0;
		gbc_rdBtnD.gridy = 3;
		panel.add(rdBtnD, gbc_rdBtnD);
		
		OptTxtD = new JTextArea();
		OptTxtD.setEditable(false);
		GridBagConstraints gbc_OptTxtD = new GridBagConstraints();
		gbc_OptTxtD.fill = GridBagConstraints.BOTH;
		gbc_OptTxtD.gridx = 1;
		gbc_OptTxtD.gridy = 3;
		panel.add(OptTxtD, gbc_OptTxtD);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 7;
		AppDemo.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		exitBtn = new JButton("Exit");
		GridBagConstraints gbc_exitBtn = new GridBagConstraints();
		gbc_exitBtn.insets = new Insets(0, 0, 0, 5);
		gbc_exitBtn.gridx = 0;
		gbc_exitBtn.gridy = 0;
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		panel_1.add(exitBtn, gbc_exitBtn);
		
		submitBtn = new JButton("Submit");
		GridBagConstraints gbc_submitBtn = new GridBagConstraints();
		gbc_submitBtn.gridx = 1;
		gbc_submitBtn.gridy = 0;
		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String info = "Incorrect";
				for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();
					
					if(button.isSelected()){
						if( button.getText().charAt(0) == correctAnswer){
							info = "Correct!";
						}
					}
				}
				JOptionPane.showMessageDialog(null, info, "Result: ", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel_1.add(submitBtn, gbc_submitBtn);
	}
	
	private void initializeUI(){
		initialize();
		ArrayList<String> options = new ArrayList<String>();
		options.add("A talented New York City bartender takes a job at a bar in Jamaica and falls in love.");
		options.add("Choice2");
		options.add("Choice3");
		options.add("Choice4");
		setAnswers(options);
		setDescription("What's your favorite movie?");
		setTitle("Quiz Demo");
	}
	
	public void setAnswers(ArrayList<String> choices){
		JTextArea[] options = {OptTxtA, OptTxtB, OptTxtC, OptTxtD};
		int i = 0;
		for(String movie : choices){
			options[i++].setText(movie);
		}
	}
	
	public void setDescription(String description){
		QuestionTxt.setText(description);
	}
	
	public void setTitle(String title){
		titleTxtLabel.setText(title);
	}
	
	public void setAnswer(char selection){
		selection = Character.toUpperCase(selection);
		if( (int)selection >= 65 && (int)selection <= 68){
			correctAnswer = selection;
		}
	}

}

