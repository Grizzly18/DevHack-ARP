package attepts;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Authorization {
	private int tryes = 3;
	private JLabel LockLabel = new JLabel("");
	private JFrame frame;
	private JTextField loginF;
	private JTextField passwordF;
	private JPanel MainPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authorization window = new Authorization();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authorization() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws FileNotFoundException
	 */


	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 300, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//String Data_Base[][] = { { "Mikhail", "Zhernevskiy" }, { "M", "Z" }, { "Mishelka", "Virmishelka" } };

		//String UserData[][] = new String[1][2];
		// ---------------------------------------------

		MainPanel.setBounds(10, 11, 280, 230);
		frame.getContentPane().add(MainPanel);
		MainPanel.setLayout(new CardLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 0));
		MainPanel.add(panel, "name_3415380014923800");
		panel.setLayout(null);

		loginF = new JTextField();
		loginF.setBounds(65, 60, 150, 20);
		panel.add(loginF);
		loginF.setColumns(10);

		passwordF = new JTextField();
		passwordF.setBounds(65, 91, 150, 20);
		panel.add(passwordF);
		passwordF.setColumns(10);

		JButton checkBtn = new JButton("Check");
		checkBtn.setFont(new Font("Swis721 Hv BT", Font.BOLD, 11));
		checkBtn.setForeground(new Color(0, 100, 0));
		checkBtn.setBackground(new Color(50, 205, 50));
		checkBtn.setBounds(96, 126, 89, 23);
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				UserData[0][0] = loginF.getText().trim();
//				UserData[0][1] = passwordF.getText().trim();
//				if ((UserData[0][0].equals(Data_Base[0][0]) && UserData[0][1].equals(Data_Base[0][1]))
//						|| (UserData[0][0].equals(Data_Base[1][0]) && UserData[0][1].equals(Data_Base[1][1]))
//						|| (UserData[0][0].equals(Data_Base[2][0]) && UserData[0][1].equals(Data_Base[2][1]))) {
				try {
					if (checkData("Base.txt", loginF.getText().trim(), passwordF.getText().trim(), 0)) {
						LockLabel.setIcon(new ImageIcon("C:\\projects\\GraphicJava\\unlock.png"));
					} else {
						tryes--;
						JOptionPane.showMessageDialog(MainPanel, "You lost 1 attept! You have " + tryes + " attepts!",
								"wrong", 0);
						passwordF.setText("");
						loginF.setText("");
						if (tryes == 0) {
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							JOptionPane.showMessageDialog(MainPanel, "Goodbye!", "Close", 1);
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							frame.setVisible(false);
						}
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		panel.add(checkBtn);

		JLabel Label = new JLabel("AUTHORISATION");
		Label.setForeground(new Color(50, 205, 50));
		Label.setBounds(65, 11, 150, 38);
		Label.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 15));
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Label);

		LockLabel.setIcon(new ImageIcon("C:\\projects\\GraphicJava\\lock.png"));
		LockLabel.setBounds(131, 191, 28, 28);
		panel.add(LockLabel);
		
		JButton registrateBtn = new JButton("Registrate Data");
		registrateBtn.setFont(new Font("Swis721 Hv BT", Font.BOLD, 11));
		registrateBtn.setBackground(new Color(50, 205, 50));
		registrateBtn.setForeground(new Color(0, 100, 0));
		registrateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					registrateData("Base.txt", loginF.getText().trim(), passwordF.getText().trim());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		registrateBtn.setBounds(65, 160, 150, 23);
		panel.add(registrateBtn);

		JPanel CongratulationsP = new JPanel();
		MainPanel.add(CongratulationsP, "name_3415477981265900");
	}
	@SuppressWarnings("resource")
	public boolean checkData(String file, String login, String password, int status) throws FileNotFoundException {
		FileReader fr = new FileReader(file);
		boolean flag = false;
		String bufer = "";
		Scanner scan = new Scanner(fr);
		String userLP[] = new String[2];
		String ownLP[] = new String[2];
		try {
		bufer  =scan.nextLine();
		//System.out.println(bufer);
		String[] sfile = bufer.split(" ");
		userLP[0] = login;
		userLP[1] = password;
		if (userLP[0].equals("") || userLP[1].equals("")) {
			flag = false;
			return flag;
		}
		//for (int i = 0; i < sfile.length;i++) {System.out.print(sfile[i] + "|");}
		//System.out.println(sfile.length);
		
		if (status == 0) {
		for (int i = 0; i < sfile.length; i++) {
			ownLP = sfile[i].split("\\.");
			//System.out.println(ownLP[0] + ownLP[1]);
			if (userLP[0].equals(ownLP[0]) && userLP[1].equals(ownLP[1])) {
				flag = true;
			}
		}
		}else {
			for (int i = 0; i < sfile.length; i++) {
				ownLP = sfile[i].split("\\.");
				//System.out.println(ownLP[0] + ownLP[1]);
				if (userLP[0].equals(ownLP[0]) || userLP[1].equals(ownLP[1])) {
					flag = true;
				}
			}
		}
		} catch (Exception e) {
			flag = false;
		}
//		while (scan.hasNextLine() == true) {
//			ownLP = scan.nextLine().split(" ");
//			if (userLP[0].equals(ownLP[0]) && userLP[1].equals(ownLP[1])) {
//				flag = true;
//			}
//		}
		scan.close();
		return flag;
	}
	public boolean checker(String a, String b) {
		boolean flag = false;
		//if (a.contains(".|\\&|\\*|\\-|\\@|\\#|\\^|\\!") && b.contains(".|\\&|\\*|\\-|\\@|\\#|\\^|\\!")) {
		if (a.contains("&") || a.contains("@")  || a.contains("|")  || a.contains("#")  || a.contains("¹")  || a.contains(".")
		||b.contains("&") || b.contains("@")  || b.contains("|")  || b.contains("#")  || b.contains("¹")  || b.contains(".")	) {
			flag = true;
		}
		
		return flag;
	}
	public void registrateData(String file, String login, String password) throws Exception {
		FileReader fr = new FileReader(file);
		FileWriter fw = new FileWriter(file, true);
		Scanner scan = new Scanner(fr);
		if (checkData("Base.txt", login, password, 1) == false && checker(login,password) == false && !(login.equals("")) && !(password.equals(""))) {
		fw.write(" " + login + "." + password);
		}
		else {
			JOptionPane.showMessageDialog(MainPanel, "login or password contains wrong symbols \nor base have those login and password", "error", 0);
		}
		loginF.setText("");
		passwordF.setText("");
		scan.close();
		fw.close();
	}
}
