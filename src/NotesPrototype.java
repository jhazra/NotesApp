import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.TextComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class NotesPrototype extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static File objFile;
	public static FileWriter objFileWriter;
	public static BufferedWriter objBufferWriter;
	private JTextField txtYourNotesHere;
	public String strFileName;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotesPrototype frame = new NotesPrototype();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
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
	 * Create the frame.
	 */
	public NotesPrototype() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 550);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewNote = new JMenuItem("New Note");
		mntmNewNote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean booleanStatus = false;
				strFileName = JOptionPane.showInputDialog(rootPane, "Enter File Name");
				try {
					booleanStatus = CreateNewFile(strFileName);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(booleanStatus) {
					JOptionPane.showMessageDialog(rootPane, "File Created Successfully");
					JOptionPane.showMessageDialog(rootPane, "File Saved Successfully");
				}
				
				else {
					JOptionPane.showMessageDialog(rootPane, "File Already Exists");
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
				JOptionPane.showMessageDialog(rootPane, "File Saved Sucessfully");
				}
			}
			else {
				JOptionPane.showMessageDialog(rootPane, "The file cannot be empty.");
			}
			}
		});
		mntmSaveNote.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSaveNote);
		
		JMenuItem mntmAddToAn = new JMenuItem("Add to an Old Note");
		mntmAddToAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String OldFileName = JOptionPane.showInputDialog("Enter in the title of the note you wish to add to/edit.");
			//"F:\\"+OldFileName+".txt"
			try {
				String lines = null;
				//System.out.println("xyz");
				lines = new String(Files.readAllBytes(Paths.get("F:\\"+OldFileName+".txt")));
				//"F:\\"+OldFileName+".txt"
				//System.out.println("xyz");
				txtYourNotesHere.setText(lines);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		});
		mntmAddToAn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnFile.add(mntmAddToAn);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setToolTipText("");
		contentPane.add(panel, BorderLayout.CENTER);
		
		txtYourNotesHere = new JTextField();
		txtYourNotesHere.setText("Your Notes Here");
		panel.add(txtYourNotesHere);
		txtYourNotesHere.setColumns(10);
	}
}