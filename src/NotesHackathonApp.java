import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NotesHackathonApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotesHackathonApp window = new NotesHackathonApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public static File objFile;
	public static FileWriter objFileWriter;
	public static BufferedWriter objBufferWriter;
	private JTextField txtYourNotesHere;
	public String strFileName;

public boolean CreateNewFile(String strFilename) throws IOException {
		
		objFile = new File ("F:\\"+strFilename+".txt");
		
		if((objFile).exists())
			return false;
		else
		{
			objFile.createNewFile();
			return true;
		}
			
	}
	
	public void WriteContentToFile(String strFileName, String strContent) throws IOException {
		objFile = new File ("F:\\"+strFileName+".txt");
		objFileWriter = new FileWriter(objFile);
		objBufferWriter = new BufferedWriter(objFileWriter);
		objBufferWriter.write(strContent);
		objFileWriter.flush();
		objBufferWriter.flush();
		
	}
	
	/**
	 * Create the application.
	 */
	public NotesHackathonApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 890, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea txtrYourNotesHere = new JTextArea();
		txtrYourNotesHere.setText("Your Notes Here");
		txtrYourNotesHere.setFont(new Font("American Typewriter", Font.PLAIN, 14));
		txtrYourNotesHere.setBounds(6, 34, 878, 497);
		frame.getContentPane().add(txtrYourNotesHere);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(6, 6, 132, 22);
		frame.getContentPane().add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewNote = new JMenuItem("New Note");
		mntmNewNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean booleanStatus = false;
				strFileName = JOptionPane.showInputDialog(frame, "Enter File Name");
				try {
					booleanStatus = CreateNewFile(strFileName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(booleanStatus) {
					JOptionPane.showMessageDialog(frame, "File Created Successfully");
					JOptionPane.showMessageDialog(frame, "File Saved Successfully");
				}
				
				else {
					JOptionPane.showMessageDialog(frame, "File Already Exists");
				}
			}
		});
		mntmNewNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnFile.add(mntmNewNote);
		
		JMenuItem mntmSaveNote = new JMenuItem("Save Note");
		mntmSaveNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strContent;
				strContent = txtYourNotesHere.getText();
				if(!strContent.equals("")) {
					try {
						WriteContentToFile(strFileName, strContent);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(frame, "The file cannot be empty.");
				}
				//strContent.
				}
		});
		mntmSaveNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSaveNote);
	}
}
