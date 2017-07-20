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
import javax.swing.ButtonGroup;
import javax.swing.AbstractButton;
import java.awt.Font;

/**
 * Creates a GUI for the movie quiz and allows for user interaction. 
 * @author David Bizzocchi, Josh Aitken, Caitlin Heyn
 * @version 1.0 Summer 2017
 */
public class QuizDemo {

	/** Frame to display the game. **/
	private JFrame appDemo;

	/** Creates a button group for displaying the answers. **/
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/** label for the title of the GUI. **/
	private JLabel titleTxtLabel;
	//FIX ME
	/** Label for the question/score count. **/
	private JLabel counterLabel;
	/** Text area that displays the questions. **/
	private JTextArea questionTxt;
	/** Panel to hold the question and answer display. **/
	private JPanel panel;
	
	/** Button for the first answer "A". **/
	private JRadioButton rdBtnA;
	/** Button for the second answer "B". **/
	private JRadioButton rdBtnB;
	/** Button for the third answer "C". **/
	private JRadioButton rdBtnC;
	/** Button for the fourth answer "D". **/
	private JRadioButton rdBtnD;
	/** text for teh first answer "A". **/
	private JTextArea optTxtA;
	/** text for teh second answer "B". **/
	private JTextArea optTxtB;
	/** Text for the third answer "C". **/
	private JTextArea optTxtC;
	/** text for the fourth answer "D". **/
	private JTextArea optTxtD;
	/** Panel to hold the stats. **/
	private JPanel panel1;
	/** button to exit the game. **/
	private JButton exitBtn;
	/** button to confirm answer selected. **/
	private JButton submitBtn;
	/** Holds the char value for the correct answer 
	 * for the current question. **/
	private char correctAnswer;
	/** holds the integer value for the correct answer
	 * for the current question. **/
	private int correctCounter;
	/** the list of questions that can be asked. **/
	private MatchingQuestion question;
	

	/**
	 * Launch the application.
	 * @param args console input.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuizDemo window = new QuizDemo();
					window.appDemo.setVisible(true);
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
		question = new MatchingQuestion();
		initializeUI();
	}
	/**
	 * Creates a demo of the quiz using matching questions provied.
	 * @param q the questions to be used.
	 */
	public QuizDemo(final MatchingQuestion q) {
		question = q;
		initializeUI();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		appDemo = new JFrame();
		appDemo.setBounds(100, 100, 600, 599);
		appDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 50, 0, 0, 0, 75, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 
				1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		appDemo.getContentPane().setLayout(gridBagLayout);
		titleTxtLabel = new JLabel("Quiz Demo");
		GridBagConstraints gbcTitleTxtLabel = new GridBagConstraints();
		gbcTitleTxtLabel.insets = new Insets(0, 0, 5, 5);
		gbcTitleTxtLabel.gridx = 1;
		gbcTitleTxtLabel.gridy = 1;
		appDemo.getContentPane().add(titleTxtLabel, gbcTitleTxtLabel);
		questionTxt = new JTextArea();
		questionTxt.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		questionTxt.setEditable(false);
		questionTxt.setWrapStyleWord(true);
		questionTxt.setLineWrap(true);
		GridBagConstraints gbcQuestionTxt = new GridBagConstraints();
		gbcQuestionTxt.fill = GridBagConstraints.BOTH;
		gbcQuestionTxt.insets = new Insets(5, 45, 5, 0);
		gbcQuestionTxt.gridx = 1;
		gbcQuestionTxt.gridy = 3;
		appDemo.getContentPane().add(questionTxt, gbcQuestionTxt);
		counterLabel = new JLabel("Score: 0");
		GridBagConstraints gbcCounterLabel = new GridBagConstraints();
		gbcCounterLabel.insets = new Insets(0, 0, 5, 5);
		gbcCounterLabel.gridx = 3;
		gbcCounterLabel.gridy = 2;
		appDemo.getContentPane().add(counterLabel, gbcCounterLabel);
		panel = new JPanel();
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.insets = new Insets(0, 0, 5, 5);
		gbcPanel.fill = GridBagConstraints.BOTH;
		gbcPanel.gridx = 1;
		gbcPanel.gridy = 5;
		appDemo.getContentPane().add(panel, gbcPanel);
		GridBagLayout gblPanel = new GridBagLayout();
		gblPanel.columnWidths = new int[]{40, 0, 0};
		gblPanel.rowHeights = new int[]{25, 25, 25, 25, 0};
		gblPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gblPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 
				1.0, Double.MIN_VALUE};
		panel.setLayout(gblPanel);
		rdBtnA = new JRadioButton("A");
		buttonGroup.add(rdBtnA);
		GridBagConstraints gbcRdBtnA = new GridBagConstraints();
		gbcRdBtnA.insets = new Insets(0, 0, 5, 5);
		gbcRdBtnA.gridx = 0;
		gbcRdBtnA.gridy = 0;
		panel.add(rdBtnA, gbcRdBtnA);
		optTxtA = new JTextArea();
		optTxtA.setEditable(false);
		GridBagConstraints gbcOptTxtA = new GridBagConstraints();
		gbcOptTxtA.insets = new Insets(0, 0, 5, 0);
		gbcOptTxtA.fill = GridBagConstraints.BOTH;
		gbcOptTxtA.gridx = 1;
		gbcOptTxtA.gridy = 0;
		panel.add(optTxtA, gbcOptTxtA);
		rdBtnB = new JRadioButton("B");
		buttonGroup.add(rdBtnB);
		GridBagConstraints gbcRdBtnB = new GridBagConstraints();
		gbcRdBtnB.insets = new Insets(0, 0, 5, 5);
		gbcRdBtnB.gridx = 0;
		gbcRdBtnB.gridy = 1;
		panel.add(rdBtnB, gbcRdBtnB);
		optTxtB = new JTextArea();
		optTxtB.setEditable(false);
		GridBagConstraints gbcOptTxtB = new GridBagConstraints();
		gbcOptTxtB.insets = new Insets(0, 0, 5, 0);
		gbcOptTxtB.fill = GridBagConstraints.BOTH;
		gbcOptTxtB.gridx = 1;
		gbcOptTxtB.gridy = 1;
		panel.add(optTxtB, gbcOptTxtB);
		rdBtnC = new JRadioButton("C");
		buttonGroup.add(rdBtnC);
		GridBagConstraints gbcRdBtnC = new GridBagConstraints();
		gbcRdBtnC.insets = new Insets(0, 0, 5, 5);
		gbcRdBtnC.gridx = 0;
		gbcRdBtnC.gridy = 2;
		panel.add(rdBtnC, gbcRdBtnC);
		optTxtC = new JTextArea();
		optTxtC.setEditable(false);
		GridBagConstraints gbcOptTxtC = new GridBagConstraints();
		gbcOptTxtC.insets = new Insets(0, 0, 5, 0);
		gbcOptTxtC.fill = GridBagConstraints.BOTH;
		gbcOptTxtC.gridx = 1;
		gbcOptTxtC.gridy = 2;
		panel.add(optTxtC, gbcOptTxtC);
		rdBtnD = new JRadioButton("D");
		buttonGroup.add(rdBtnD);
		GridBagConstraints gbcRdBtnD = new GridBagConstraints();
		gbcRdBtnD.insets = new Insets(0, 0, 0, 5);
		gbcRdBtnD.gridx = 0;
		gbcRdBtnD.gridy = 3;
		panel.add(rdBtnD, gbcRdBtnD);
		optTxtD = new JTextArea();
		optTxtD.setEditable(false);
		GridBagConstraints gbcOptTxtD = new GridBagConstraints();
		gbcOptTxtD.fill = GridBagConstraints.BOTH;
		gbcOptTxtD.gridx = 1;
		gbcOptTxtD.gridy = 3;
		panel.add(optTxtD, gbcOptTxtD);
		panel1 = new JPanel();
		GridBagConstraints gbcPanel1 = new GridBagConstraints();
		gbcPanel1.insets = new Insets(0, 0, 5, 5);
		gbcPanel1.fill = GridBagConstraints.VERTICAL;
		gbcPanel1.gridx = 1;
		gbcPanel1.gridy = 7;
		appDemo.getContentPane().add(panel1, gbcPanel1);
		GridBagLayout gblPanel1 = new GridBagLayout();
		gblPanel1.columnWidths = new int[]{0, 0, 0};
		gblPanel1.rowHeights = new int[]{0, 0};
		gblPanel1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gblPanel1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel1.setLayout(gblPanel1);
		
		exitBtn = new JButton("Exit");
		GridBagConstraints gbcExitBtn = new GridBagConstraints();
		gbcExitBtn.insets = new Insets(0, 0, 0, 5);
		gbcExitBtn.gridx = 0;
		gbcExitBtn.gridy = 0;
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				System.exit(0);
			}
		});
		panel1.add(exitBtn, gbcExitBtn);
		submitBtn = new JButton("Submit");
		GridBagConstraints gbcSubmitBtn = new GridBagConstraints();
		gbcSubmitBtn.gridx = 1;
		gbcSubmitBtn.gridy = 0;
		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent e) {
				String info = "Incorrect";
				for (Enumeration<AbstractButton> buttons = 
						buttonGroup.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();
					if (button.isSelected()) {
						if (button.getText().charAt(0) == correctAnswer) { 
							info = "Correct!";
							correctCounter++;
							setCounter(correctCounter);
						}
						setNewQuestion();
					}
				}
				JOptionPane.showMessageDialog(null, info, "Result: ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		panel1.add(submitBtn, gbcSubmitBtn);
	}
	/**
	 * Initializes the UI.
	 */
	private void initializeUI() {
		initialize();
		ArrayList<String> options = new ArrayList<String>();
		options.add("Choice 1");
		options.add("Choice2");
		options.add("Choice3");
		options.add("Choice4");
		setAnswers(options);
		setDescription("What's your favorite movie?");
		setTitle("Quiz Demo");
	}
	/**
	 * Sets the answers for the current question.
	 * @param choices the answer options for the current question.
	 */
	public void setAnswers(final ArrayList<String> choices) {
		JTextArea[] options = {optTxtA, optTxtB, optTxtC, optTxtD};
		int i = 0;
		for (String movie : choices) {
			options[i++].setText(movie);
		}
	}
	/**
	 * Sets the description of the movie for the current question.
	 * @param description the description for the movie.
	 */
	public void setDescription(final String description) {
		questionTxt.setText(description);
	}
	/**
	 * sets the title of the application.
	 * @param title the title for the application.
	 */
	public void setTitle(final String title) {
		titleTxtLabel.setText(title);
	}
	/**
	 *  Sets the answer at the given position.
	 * @param selection the answer selected by the user.
	 */
	public void setAnswer(char selection) {
		selection = Character.toUpperCase(selection);
		if ((int) selection >= 65 && (int) selection <= 68) {
			correctAnswer = selection;
		}
	}
	/**
	 * Sets the counter for the number of questions so far.
	 * @param numberCorrect the number of questions to be displayed.
	 */
	private void setCounter(final int numberCorrect) {
		counterLabel.setText("Score: " + Integer.toString(numberCorrect));
	}
	/**
	 * Sets a new question for the quiz.
	 */
	public void runQuiz() {
		setNewQuestion();
	}
	/**
	 * Sets the next question after the previous is answered.
	 */
	private void setNewQuestion() {
		question = question.generateQuestion();
		setAnswers(question.getPossibleAnswers());
		setDescription(question.getMovieDesc());
		int answer = question.getAnswerIndex() + 64;
		setAnswer((char) answer);
	}
	/**
	 * Returns the demo so it can be displayed in another class.	
	 * @return the appDemo to be run.
	 */
	public JFrame getAppDemo() {
		return appDemo;
	}

}

