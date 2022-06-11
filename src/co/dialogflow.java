package co;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.api.client.util.Maps;
import com.google.cloud.dialogflow.*;
import com.google.cloud.dialogflow.v2.Intent.Message;
import com.google.cloud.dialogflow.v2.Intent.Message.Text;
import com.google.cloud.dialogflow.v2.Intent.TrainingPhrase;
import com.google.cloud.dialogflow.v2.Intent.TrainingPhrase.Part;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2beta1.QueryResult;
import com.google.cloud.dialogflow.v2beta1.TextInput.Builder;
import com.google.cloud.dialogflow.v2beta1.*;
import com.google.cloud.dialogflow.v2beta1.QueryResult;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;
import java.awt.Color;

public class dialogflow extends JFrame {

	private JPanel contentPane;
	private JTextField txtFdfd;
	String a;
	// Better pull up
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dialogflow frame = new dialogflow();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	

/**
 * Returns the result of detect intent with texts as inputs.
 *
 * Using the same `session_id` between requests allows continuation of the conversation.
 *
 * @param projectId    Project/Agent Id.
 * @param texts        The text intents to be detected based on what a user says.
 * @param sessionId    Identifier of the DetectIntent session.
 * @param languageCode Language code of the query.
 * @return The QueryResult for each input text.
 */

/**
 * Returns the result of detect intent with texts as inputs.
 *
 * Using the same `session_id` between requests allows continuation of the conversation.
 *
 * @param projectId    Project/Agent Id.
 * @param texts        The text intents to be detected based on what a user says.
 * @param sessionId    Identifier of the DetectIntent session.
 * @param languageCode Language code of the query.
 * @return The QueryResult for each input text.
 */
public static Map<String, QueryResult> detectIntentTexts(
    String projectId,
    List<String> texts,
    String sessionId,
    String languageCode) throws Exception {
  Map<String, QueryResult> queryResults = Maps.newHashMap();
  // Instantiates a client
  try (SessionsClient sessionsClient = SessionsClient.create()) {
    // Set the session name using the sessionId (UUID) and projectID (my-project-id)
    SessionName session = SessionName.of(projectId, sessionId);
    System.out.println("Session Path: " + session.toString());

    // Detect intents for each text input
    for (String text : texts) {
      // Set the text (hello) and language code (en-US) for the query
      Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode);

      // Build the query with the TextInput
      QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

      // Performs the detect intent request
      DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

      // Display the query result
      QueryResult queryResult = response.getQueryResult();

      System.out.println("====================");
      System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
      System.out.format("Detected Intent: %s (confidence: %f)\n",
          queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
      System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());

      queryResults.put(text, queryResult);
    }
  }
  return queryResults;
}
	public dialogflow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 563);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		txtFdfd = new JTextField();
		txtFdfd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtFdfd.setText(null);
			}
		});
		txtFdfd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtFdfd.setText(null);
			}
		});
		txtFdfd.setHorizontalAlignment(SwingConstants.LEFT);
		txtFdfd.setText("Please enter your text here");
		txtFdfd.setFont(new Font("Tahoma", Font.PLAIN, 38));
		txtFdfd.setBounds(110, 402, 488, 111);
		contentPane.add(txtFdfd);
		txtFdfd.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("MIC");
		btnNewButton_1.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		btnNewButton_1.setBounds(10, 432, 80, 69);
		contentPane.add(btnNewButton_1);
		
		JTextArea txtrWelcomeToThe = new JTextArea();
		txtrWelcomeToThe.setBackground(Color.LIGHT_GRAY);
		txtrWelcomeToThe.setWrapStyleWord(true);
		txtrWelcomeToThe.setText("Welcome to the chat!");
		txtrWelcomeToThe.setBounds(10, 11, 799, 380);
		contentPane.add(txtrWelcomeToThe);
		
		JButton btnNewButton = new JButton("SEND");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 35));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				a=txtFdfd.getText();
				txtrWelcomeToThe.append("\n" +"User: " + a);
				detectIntentTexts("AIzaSyBYVIHDREZ6troK2eF7n8wsWNm1x4LUSO8" , "Hello", "123456789", "en-US" );
			}
		});
		btnNewButton.setBounds(621, 402, 188, 111);
		contentPane.add(btnNewButton);
	}
}
