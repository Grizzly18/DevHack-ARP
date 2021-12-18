

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SimpleVersionGame {
	private int tryes = 3;
	private JLabel LockLabel = new JLabel("");
	public JFrame Mframe;
	private JTextField loginF;
	private JTextField passwordF;
	public static JPanel MainPanel = new JPanel();
	public JPanel CongratulationsP;
	private boolean flag = false;
	public String pass = "";
	public String log = "";
	private static int status = 0;
	private static int QScores = 0;
	private static int level = 0;
	private static int Maxs = 10;
	private static int levelClick = 1;
	private static int Price = 1;
	private static int Qzones = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleVersionGame window = new SimpleVersionGame();
					window.Mframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SimpleVersionGame() {
		initializeMain();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws FileNotFoundException
	 */

	private void initializeMain() {
		Mframe = new JFrame();
		Mframe.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\projects\\JAVA\\ProjectJ\\tap.png"));
		Mframe.setTitle("Clicker by MZK");
		Mframe.setResizable(false);
		Mframe.setBounds(100, 100, 300, 280);
		Mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Mframe.getContentPane().setLayout(null);

		// String Data_Base[][] = { { "Mikhail", "Zhernevskiy" }, { "M", "Z" }, {
		// "Mishelka", "Virmishelka" } };

		// String UserData[][] = new String[1][2];
		// ---------------------------------------------

		MainPanel.setBounds(10, 11, 280, 230);
		Mframe.getContentPane().add(MainPanel);
		MainPanel.setLayout(new CardLayout(0, 0));
		JPanel entrancePanel = new JPanel();
		entrancePanel.setBackground(new Color(0, 128, 0));
		MainPanel.add(entrancePanel, "name_93510784989014");
		entrancePanel.setLayout(null);

		JLabel Label = new JLabel("AUTHORISATION");
		Label.setForeground(new Color(50, 205, 50));
		Label.setBounds(65, 11, 150, 38);
		Label.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 15));
		Label.setHorizontalAlignment(SwingConstants.CENTER);
		entrancePanel.add(Label);

		LockLabel.setIcon(new ImageIcon("C:\\projects\\GraphicJava\\lock.png"));
		LockLabel.setBounds(131, 191, 28, 28);
		entrancePanel.add(LockLabel);

		loginF = new JTextField();
		loginF.setBounds(65, 60, 150, 20);
		entrancePanel.add(loginF);
		loginF.setColumns(10);

		passwordF = new JTextField();
		passwordF.setBounds(65, 91, 150, 20);
		entrancePanel.add(passwordF);
		passwordF.setColumns(10);

		CongratulationsP = new JPanel();
		CongratulationsP.setBackground(SystemColor.controlHighlight);
		MainPanel.add(CongratulationsP, "name_93510834107091");
		CongratulationsP.setLayout(null);

		JTabbedPane ZoneWearer = new JTabbedPane(JTabbedPane.TOP);
		ZoneWearer.setBounds(0, 77, 280, 153);
		CongratulationsP.add(ZoneWearer);

		JPanel panel1 = new JPanel();
		ZoneWearer.addTab("1", null, panel1, null);
		panel1.setLayout(null);

		JLabel priceLbl1 = new JLabel("Price: " + Price);
		priceLbl1.setFont(new Font("Liberation Sans Narrow", Font.BOLD | Font.ITALIC, 18));
		priceLbl1.setBounds(150, 11, 89, 14);
		panel1.add(priceLbl1);

		JLabel clicklvlLbl1 = new JLabel("Level of click: " + levelClick);
		clicklvlLbl1.setFont(new Font("Liberation Sans Narrow", Font.BOLD | Font.ITALIC, 13));
		clicklvlLbl1.setBounds(150, 59, 89, 14);
		panel1.add(clicklvlLbl1);

		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(175, 238, 238));
		ZoneWearer.addTab("Locked", null, panel2, null);
		panel2.setLayout(null);
		
		JLabel priceLbl2 = new JLabel("Price: 1");
		priceLbl2.setFont(new Font("Liberation Sans Narrow", Font.BOLD | Font.ITALIC, 18));
		priceLbl2.setBounds(126, 11, 80, 14);
		panel2.add(priceLbl2);
		
		JLabel clicklvlLbl2 = new JLabel("Level of click: 1");
		clicklvlLbl2.setFont(new Font("Liberation Sans Narrow", Font.BOLD | Font.ITALIC, 13));
		clicklvlLbl2.setBounds(126, 34, 89, 14);
		panel2.add(clicklvlLbl2);
		
		JLabel DecorateLbl2 = new JLabel("");
		DecorateLbl2.setHorizontalAlignment(SwingConstants.CENTER);
		DecorateLbl2.setBackground(SystemColor.controlHighlight);
		DecorateLbl2.setBounds(0, 0, 275, 125);
		panel2.add(DecorateLbl2);

		JPanel panel3 = new JPanel();
		ZoneWearer.addTab("Locked", null, panel3, null);
		panel3.setLayout(null);

		JProgressBar mainBar = new JProgressBar();
		mainBar.setMaximum(Maxs);
		mainBar.setValue(status);
		mainBar.setForeground(SystemColor.textHighlight);
		mainBar.setBounds(134, 36, 146, 30);
		CongratulationsP.add(mainBar);

		JLabel levelLbl = new JLabel("Your level: " + level);
		levelLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 11));
		levelLbl.setBounds(134, 11, 136, 14);
		CongratulationsP.add(levelLbl);

		JLabel scoresLbl = new JLabel("Your Scores: " + QScores);
		scoresLbl.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 12));
		scoresLbl.setBounds(10, 36, 100, 30);
		CongratulationsP.add(scoresLbl);


		
        
		JButton ClickBtn2 = new JButton("");
		ClickBtn2.setIcon(new ImageIcon("interactive.png"));
		ClickBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				click(mainBar, scoresLbl, levelLbl);
				synhron(scoresLbl, levelLbl, priceLbl1, clicklvlLbl1, clicklvlLbl2, priceLbl2);
				
			}
		});
		ClickBtn2.setBounds(10, 34, 80, 80);
		panel2.add(ClickBtn2);
		
		JButton bustBtn1 = new JButton("Bust Click");
		bustBtn1.setBackground(SystemColor.controlShadow);
		bustBtn1.setFont(new Font("Liberation Sans Narrow", Font.BOLD | Font.ITALIC, 14));
		bustBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bust(panel1, priceLbl1, scoresLbl, clicklvlLbl1, 2, 1);
				synhron(scoresLbl, levelLbl, priceLbl1, clicklvlLbl1, clicklvlLbl2, priceLbl2);
			}
		});
		bustBtn1.setBounds(150, 25, 109, 23);
		panel1.add(bustBtn1);
		
		JButton bustBtn2 = new JButton("");
		bustBtn2.setIcon(new ImageIcon("renewable-energy.png"));
		bustBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bust(panel2, priceLbl2, scoresLbl, clicklvlLbl2, 2.5, 2);
				synhron(scoresLbl, levelLbl, priceLbl1, clicklvlLbl1, clicklvlLbl2, priceLbl2);
			}
		});
		bustBtn2.setBounds(225, 11, 40, 40);
		panel2.add(bustBtn2);
		
		JButton ClickBtn1 = new JButton("Click");
		ClickBtn1.setBackground(SystemColor.controlHighlight);
		ClickBtn1.setFont(new Font("Liberation Sans Narrow", Font.BOLD | Font.ITALIC, 20));
		ClickBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// status += levelClick;
                click(mainBar, scoresLbl, levelLbl);
                synhron(scoresLbl, levelLbl, priceLbl1, clicklvlLbl1, clicklvlLbl2, priceLbl2);
                if (Qzones >= 2) {
					ClickBtn2.setVisible(true);
					bustBtn2.setVisible(true);
					clicklvlLbl2.setVisible(true);
					priceLbl2.setVisible(true);
					DecorateLbl2.setVisible(false);
				}
			}
		});
		ClickBtn1.setBounds(10, 11, 130, 50);
		panel1.add(ClickBtn1);
		
		JButton StartBtn = new JButton("GO");
		StartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrancePanel.setVisible(false);
				CongratulationsP.setVisible(true);
				LockLabel.setIcon(new ImageIcon("lock.png"));
				try {
					int DAr[] = readGameFile(log + "_" + pass + ".txt");
					// for (int i = 0; i < DAr.length;i++) {System.out.print(DAr[i] + "|");}
					QScores = DAr[0];
					level = DAr[1];
					Maxs = DAr[2];
					levelClick = DAr[3];
					Price = DAr[4];
					Qzones = DAr[5];
					mainBar.setMaximum(Maxs);
					if (Qzones < 2) {
						ClickBtn2.setVisible(false);
						bustBtn2.setVisible(false);
						clicklvlLbl2.setVisible(false);
						priceLbl2.setVisible(false);
						DecorateLbl2.setIcon(new ImageIcon("lockBig.png"));
					}
					synhron(scoresLbl, levelLbl, priceLbl1, clicklvlLbl1, clicklvlLbl2, priceLbl2);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		StartBtn.setBackground(new Color(50, 205, 50));
		StartBtn.setFont(new Font("Swis721 Lt BT", Font.BOLD | Font.ITALIC, 11));
		StartBtn.setForeground(new Color(0, 128, 0));
		StartBtn.setBounds(96, 38, 89, 23);
		entrancePanel.add(StartBtn);
		StartBtn.setVisible(flag);

		JButton registrateBtn = new JButton("Registrate Data");
		registrateBtn.setFont(new Font("Swis721 Hv BT", Font.BOLD, 11));
		registrateBtn.setBackground(new Color(50, 205, 50));
		registrateBtn.setForeground(new Color(0, 100, 0));
		registrateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					log = loginF.getText().trim();
					pass = passwordF.getText().trim();
					if (registrateData("Base.txt", log, pass) == true) {
						PrintWriter fileName = new PrintWriter(log + "_" + pass + ".txt");
						fileName.println("0;0;10;1;1;1");
						// writeGameFile(log + "_" + pass +".txt", 0, 0, 0, 0);
						fileName.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		registrateBtn.setBounds(65, 160, 150, 23);
		entrancePanel.add(registrateBtn);

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
					log = loginF.getText().trim();
					pass = passwordF.getText().trim();
					if (checkData("Base.txt", log, pass, 0)) {
						LockLabel.setIcon(new ImageIcon("unlock.png"));
						flag = true;
						checkBtn.setEnabled(false);
						registrateBtn.setEnabled(false);
						loginF.setEnabled(false);
						passwordF.setEnabled(false);
						StartBtn.setVisible(flag);
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

							Mframe.dispatchEvent(new WindowEvent(Mframe, WindowEvent.WINDOW_CLOSING));
						}
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
		entrancePanel.add(checkBtn);



		JLabel DecorateLbl1 = new JLabel("");
		DecorateLbl1.setBackground(Color.WHITE);
		DecorateLbl1.setHorizontalAlignment(SwingConstants.CENTER);
		DecorateLbl1.setBounds(0, 0, 275, 125);
		panel1.add(DecorateLbl1);

		JButton closeBtn = new JButton("");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrancePanel.setVisible(true);
				CongratulationsP.setVisible(false);
				QScores = 0;
				level = 0;
				Maxs = 10;
				levelClick = 1;
				Price = 1;
				Qzones = 1;
				flag = false;
				checkBtn.setEnabled(true);
				registrateBtn.setEnabled(true);
				loginF.setEnabled(true);
				passwordF.setEnabled(true);
				loginF.setText("");
				passwordF.setText("");
				StartBtn.setVisible(flag);
			}
		});
		closeBtn.setIcon(new ImageIcon("C:\\projects\\JAVA\\ProjectJ\\cancel.png"));
		closeBtn.setBounds(10, 11, 16, 16);
		CongratulationsP.add(closeBtn);

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
			bufer = scan.nextLine().trim();
			// System.out.println(bufer);
			String[] sfile = bufer.split(" ");
			userLP[0] = login;
			userLP[1] = password;
			if (userLP[0].equals("") || userLP[1].equals("")) {
				flag = false;
				return flag;
			}
			// for (int i = 0; i < sfile.length;i++) {System.out.print(sfile[i] + "|");}
			// System.out.println(sfile.length);

			if (status == 0) {
				for (int i = 0; i < sfile.length; i++) {
					ownLP = sfile[i].split(";");
					// System.out.println(ownLP[0] + ownLP[1]);
					if (userLP[0].equals(ownLP[0]) && userLP[1].equals(ownLP[1])) {
						flag = true;
					}
				}
			} else {
				for (int i = 0; i < sfile.length; i++) {
					ownLP = sfile[i].split(";");
					// System.out.println(ownLP[0] + ownLP[1]);
					if (userLP[0].equals(ownLP[0])) {
						flag = true;
					}
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			// flag = true;
			flag = false;
		}
		scan.close();
		return flag;
	}

	public boolean checker(String a, String b) {
		boolean flag = false;
		// if (a.contains(".|\\&|\\*|\\-|\\@|\\#|\\^|\\!") &&
		// b.contains(".|\\&|\\*|\\-|\\@|\\#|\\^|\\!")) {
		if (a.contains("&") || a.contains("@") || a.contains("|") || a.contains("#") || a.contains("¹")
				|| a.contains(".") || b.contains("&") || b.contains("@") || b.contains("|") || b.contains("#")
				|| b.contains("¹") || b.contains(".")) {
			flag = true;
		}

		return flag;
	}

	public boolean registrateData(String file, String login, String password) throws Exception {
		boolean Rflag = true;
		FileWriter fw = new FileWriter(file, true);
		if (checkData("Base.txt", login, password, 1) == false && checker(login, password) == false
				&& !(login.equals("")) && !(password.equals(""))) {
			fw.write(" " + login + ";" + password);
		} else {
			JOptionPane.showMessageDialog(MainPanel,
					"login or password contains wrong symbols \nor base have those login and password", "error", 0);
			Rflag = false;
		}
		loginF.setText("");
		passwordF.setText("");
		fw.close();
		return Rflag;
	}

	public int[] readGameFile(String file) throws Exception {
		FileReader fr = new FileReader(file);
		Scanner scan = new Scanner(fr);
		String buferArray[] = scan.nextLine().split(";");
		int dataArray[] = new int[6];
		for (int i = 0; i < dataArray.length; i++) {
			dataArray[i] = Integer.parseInt(buferArray[i]);

		}
		scan.close();
		return dataArray;

	}

	public void writeGameFile(String file, int QScores, int level, int MaxSize, int LClicker, int PriceClicker,
			int QZone) throws Exception {
		FileWriter fr = new FileWriter(file);
		fr.write(String
				.valueOf(QScores + ";" + level + ";" + MaxSize + ";" + LClicker + ";" + PriceClicker + ";" + QZone));
		fr.close();

	}
	public void click(JProgressBar mainBar, JLabel scoresLbl, JLabel levelLbl) {
		status += levelClick;
		if (status >= mainBar.getMaximum()) {
			status = 0;
			Maxs += Maxs / 5;
			level++;
			QScores++;
			mainBar.setMaximum(Maxs);
			//scoresLbl.setText("Your Scores: " + QScores);
			//levelLbl.setText("Your level: " + level);

		}
		if (level >=10) {
			Qzones++;
		}
		mainBar.setValue(status);
		String filename = log + "_" + pass + ".txt";
		try {
			writeGameFile(filename, QScores, level, Maxs, levelClick, Price, Qzones);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void bust(JPanel panel, JLabel priceLbl, JLabel scoresLbl, JLabel clicklvlLbl, double cfc, int upper) {
		if (QScores >= Price) {
			QScores -= Price;
			levelClick+= upper;
			Price *= cfc;
			//priceLbl.setText("Price: " + Price);
			//scoresLbl.setText("Your Scores: " + QScores);
			//clicklvlLbl.setText("Level of Click:" + levelClick);
		} else {
			JOptionPane.showMessageDialog(panel, "You haven't scores to bust", "Error", 0);
		}
	}
	public void synhron (JLabel scoresLbl, JLabel levelLbl,JLabel priceLbl1, JLabel clicklvlLbl1, JLabel clicklvlLbl2,JLabel priceLbl2) {	//JLabel priceLbl3, JLabel clicklvlLbl3)
	priceLbl1.setText("Price: " + Price);
	priceLbl2.setText("Price: " + Price);
	clicklvlLbl1.setText("Level of Click:" + levelClick);
	clicklvlLbl2.setText("Level of Click:" + levelClick);
	scoresLbl.setText("Your Scores: " + QScores);
	levelLbl.setText("Your level: " + level);
	}
}
