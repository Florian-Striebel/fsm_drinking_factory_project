package fr.univcotedazur.polytech.si4.fsm.project;

import drink.Drink;
import drink.Ingredient;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ProgressBarUI;
import fr.univcotedazur.polytech.si4.fsm.project.factory.FactoryStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.factory.IFactoryStatemachine.SCInterfaceListener;
import preparation.DrinkSize;

public class DrinkFactoryMachine extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2030629304432075314L;
	private JPanel contentPane;
	public FactoryStatemachine theFSM;
	protected JLabel messagesToUser;
	protected JButton coffeeButton, expressoButton, teaButton, soupButton, money50centsButton, money10centsButton,
			money25centsButton, takeDrinkButton, validateButton;
	protected JSlider sugarSlider, sizeSlider, temperatureSlider;
	protected double coin;
	protected Drink selection;
	protected JProgressBar progressBar;
	protected JLabel labelForPictures;
	protected JLabel lblSugar, lblValidate;
	protected JTextField nfcUserId;
	protected GestionnaireDeRduction gReduc;
	protected GestionnaireDIngredient gIngredient;

	/**
	 * @wbp.nonvisual location=311,475
	 */
	// private final ImageIcon imageIcon = new ImageIcon();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrinkFactoryMachine frame = new DrinkFactoryMachine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getNfcId() {
		return nfcUserId.getText();
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public int getSugarSize() {
		return sugarSlider.getValue();
	}

	public DrinkSize getDrinkSize() {
		return DrinkSize.values()[sizeSlider.getValue()];
	}

	public int getTemperature() {
		return temperatureSlider.getValue();
	}

	public void reactivateNFC() {
		nfcUserId.setEditable(true);
	}

	public void setPictureCup(String photo) {
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File(photo));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (labelForPictures == null) {
			labelForPictures = new JLabel(new ImageIcon(myPicture));
		} else {
			labelForPictures.setIcon(new ImageIcon(myPicture));
		}
	}

	/**
	 * Create the frame.
	 */
	public DrinkFactoryMachine() {
		gIngredient = new GestionnaireDIngredient();
		gReduc = new GestionnaireDeRduction();
		theFSM = new FactoryStatemachine();
		TimerService timer = new TimerService();
		theFSM.setTimer(timer);
		DrinkFactoryControllerInterfaceImplementation imp = new DrinkFactoryControllerInterfaceImplementation(this);
		theFSM.getSCInterface().getListeners().add(imp);
		// theFSM.getSCInterface().setSCInterfaceOperationCallback(imp );
		theFSM.init();
		theFSM.enter();
		theFSM.runCycle();
		setForeground(Color.WHITE);
		setFont(new Font("Cantarell", Font.BOLD, 22));
		setBackground(Color.DARK_GRAY);
		setTitle("Drinking Factory Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		messagesToUser = new JLabel("<html>Choisissez votre boisson");
		messagesToUser.setForeground(Color.WHITE);
		messagesToUser.setHorizontalAlignment(SwingConstants.LEFT);
		messagesToUser.setVerticalAlignment(SwingConstants.TOP);
		messagesToUser.setToolTipText("message to the user");
		messagesToUser.setBackground(Color.WHITE);
		messagesToUser.setBounds(126, 34, 165, 175);
		contentPane.add(messagesToUser);

		JLabel lblCoins = new JLabel("Coins");
		lblCoins.setForeground(Color.WHITE);
		lblCoins.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoins.setBounds(538, 12, 44, 15);
		contentPane.add(lblCoins);

		coffeeButton = new JButton("Coffee");
		coffeeButton.setForeground(Color.WHITE);
		coffeeButton.setBackground(Color.DARK_GRAY);
		coffeeButton.setBounds(12, 34, 96, 25);
		contentPane.add(coffeeButton);

		lblValidate = new JLabel("Validate with this spicy");
		lblValidate.setForeground(Color.WHITE);
		lblValidate.setHorizontalAlignment(SwingConstants.CENTER);
		lblValidate.setVisible(false);
		lblValidate.setBounds(125, 200, 150, 25);
		contentPane.add(lblValidate);

		validateButton = new JButton("Validate");
		validateButton.setForeground(Color.WHITE);
		validateButton.setBackground(Color.DARK_GRAY);
		validateButton.setBounds(150, 220, 96, 25);
		validateButton.setVisible(false);
		contentPane.add(validateButton);

		expressoButton = new JButton("Expresso");
		expressoButton.setForeground(Color.WHITE);
		expressoButton.setBackground(Color.DARK_GRAY);
		expressoButton.setBounds(12, 71, 96, 25);
		contentPane.add(expressoButton);

		teaButton = new JButton("Tea");
		teaButton.setForeground(Color.WHITE);
		teaButton.setBackground(Color.DARK_GRAY);
		teaButton.setBounds(12, 108, 96, 25);
		contentPane.add(teaButton);

		soupButton = new JButton("Soup");
		soupButton.setForeground(Color.WHITE);
		soupButton.setBackground(Color.DARK_GRAY);
		soupButton.setBounds(12, 145, 96, 25);
		contentPane.add(soupButton);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setValue(10);
		progressBar.setForeground(Color.LIGHT_GRAY);
		progressBar.setBackground(Color.DARK_GRAY);
		progressBar.setBounds(12, 254, 622, 26);
		contentPane.add(progressBar);

		sugarSlider = new JSlider();
		sugarSlider.setValue(1);
		sugarSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sugarSlider.setBackground(Color.DARK_GRAY);
		sugarSlider.setForeground(Color.WHITE);
		sugarSlider.setPaintTicks(true);
		sugarSlider.setMinorTickSpacing(1);
		sugarSlider.setMajorTickSpacing(1);
		sugarSlider.setMaximum(4);
		sugarSlider.setBounds(301, 51, 200, 36);
		contentPane.add(sugarSlider);

		sizeSlider = new JSlider();
		sizeSlider.setPaintTicks(true);
		sizeSlider.setValue(1);
		sizeSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		sizeSlider.setBackground(Color.DARK_GRAY);
		sizeSlider.setForeground(Color.WHITE);
		sizeSlider.setMinorTickSpacing(1);
		sizeSlider.setMaximum(2);
		sizeSlider.setMajorTickSpacing(1);
		sizeSlider.setBounds(301, 125, 200, 36);
		contentPane.add(sizeSlider);

		temperatureSlider = new JSlider();
		temperatureSlider.setPaintLabels(true);
		temperatureSlider.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		temperatureSlider.setValue(2);
		temperatureSlider.setBackground(Color.DARK_GRAY);
		temperatureSlider.setForeground(Color.WHITE);
		temperatureSlider.setPaintTicks(true);
		temperatureSlider.setMajorTickSpacing(1);
		temperatureSlider.setMaximum(3);
		temperatureSlider.setBounds(301, 188, 200, 54);

		Hashtable<Integer, JLabel> temperatureTable = new Hashtable<Integer, JLabel>();
		temperatureTable.put(0, new JLabel("20°C"));
		temperatureTable.put(1, new JLabel("35°C"));
		temperatureTable.put(2, new JLabel("60°C"));
		temperatureTable.put(3, new JLabel("85°C"));
		for (JLabel l : temperatureTable.values()) {
			l.setForeground(Color.WHITE);
		}
		temperatureSlider.setLabelTable(temperatureTable);

		contentPane.add(temperatureSlider);

		JButton icedTeaButton = new JButton("Iced Tea");
		icedTeaButton.setForeground(Color.WHITE);
		icedTeaButton.setBackground(Color.DARK_GRAY);
		icedTeaButton.setBounds(12, 182, 96, 25);
		contentPane.add(icedTeaButton);

		lblSugar = new JLabel("Sugar");
		lblSugar.setForeground(Color.WHITE);
		lblSugar.setBackground(Color.DARK_GRAY);
		lblSugar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSugar.setBounds(380, 34, 44, 15);
		contentPane.add(lblSugar);

		JLabel lblSize = new JLabel("Size");
		lblSize.setForeground(Color.WHITE);
		lblSize.setBackground(Color.DARK_GRAY);
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setBounds(380, 113, 44, 15);
		contentPane.add(lblSize);

		JLabel lblTemperature = new JLabel("Temperature");
		lblTemperature.setForeground(Color.WHITE);
		lblTemperature.setBackground(Color.DARK_GRAY);
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setBounds(363, 173, 96, 15);
		contentPane.add(lblTemperature);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		lblCoins.setLabelFor(panel);
		panel.setBounds(538, 25, 96, 97);
		contentPane.add(panel);

		money50centsButton = new JButton("0.50 €");
		money50centsButton.setForeground(Color.WHITE);
		money50centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money50centsButton);

		money25centsButton = new JButton("0.25 €");
		money25centsButton.setForeground(Color.WHITE);
		money25centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money25centsButton);

		money10centsButton = new JButton("0.10 €");
		money10centsButton.setForeground(Color.WHITE);
		money10centsButton.setBackground(Color.DARK_GRAY);
		panel.add(money10centsButton);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(538, 154, 86, 70);
		contentPane.add(panel_1);

		nfcUserId = new JTextField(7);
		nfcUserId.setForeground(Color.WHITE);
		nfcUserId.setBackground(Color.DARK_GRAY);
		panel_1.add(nfcUserId);

		JButton nfcBiiiipButton = new JButton("biiip");
		nfcBiiiipButton.setForeground(Color.WHITE);
		nfcBiiiipButton.setBackground(Color.DARK_GRAY);
		panel_1.add(nfcBiiiipButton);

		JLabel lblNfc = new JLabel("NFC");
		lblNfc.setForeground(Color.WHITE);
		lblNfc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNfc.setBounds(541, 139, 41, 15);
		contentPane.add(lblNfc);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 292, 622, 15);
		contentPane.add(separator);

		JButton addCupButton = new JButton("Add cup");
		addCupButton.setForeground(Color.WHITE);
		addCupButton.setBackground(Color.DARK_GRAY);
		addCupButton.setBounds(45, 336, 96, 25);
		contentPane.add(addCupButton);

		takeDrinkButton = new JButton("Take your drink");
		takeDrinkButton.setVisible(false);
		takeDrinkButton.setForeground(Color.WHITE);
		takeDrinkButton.setBackground(Color.DARK_GRAY);
		takeDrinkButton.setBounds(20, 400, 150, 25);
		contentPane.add(takeDrinkButton);

		setPictureCup("./picts/vide2.jpg");
		labelForPictures.setBounds(175, 319, 286, 260);
		contentPane.add(labelForPictures);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.DARK_GRAY);
		panel_2.setBounds(538, 217, 96, 33);
		contentPane.add(panel_2);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setBackground(Color.DARK_GRAY);
		panel_2.add(cancelButton);

		// listeners
		addCupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setPictureCup("./picts/ownCup.jpg");
			}
		});
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theFSM.raiseCancel();
				theFSM.raiseDoAction();
			}
		});

		takeDrinkButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				theFSM.raiseTakeDrink();
			}
		});
		coffeeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((JButton) e.getSource()).isEnabled()) {
					selection = Drink.COFFE;
					theFSM.raiseSelected();
					theFSM.raiseDoAction();
				}
			}
		});
		expressoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((JButton) e.getSource()).isEnabled()) {
					selection = Drink.EXPRESSO;
					theFSM.raiseSelected();
					theFSM.raiseDoAction();
				}
			}
		});
		icedTeaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((JButton) e.getSource()).isEnabled()) {
					theFSM.raiseSelected();
					theFSM.raiseDoAction();
				}

			}
		});
		teaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((JButton) e.getSource()).isEnabled()) {
					selection = Drink.TEA;
					theFSM.raiseSelected();
					theFSM.raiseDoAction();
				}

			}
		});
		soupButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (((JButton) e.getSource()).isEnabled()) {
					selection = Drink.SOUP;
					theFSM.raiseSelected();
					theFSM.raiseDoAction();
				}

			}
		});
		validateButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				theFSM.setIsValidate(true);
				theFSM.raiseDoAction();
			}
		});

		money10centsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				coin = 0.1;
				theFSM.raiseAddCoin();
				theFSM.raiseDoAction();

			}
		});
		money25centsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				coin = 0.25;
				theFSM.raiseAddCoin();
				theFSM.raiseDoAction();

			}
		});
		money50centsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				coin = 0.5;
				theFSM.raiseAddCoin();
				theFSM.raiseDoAction();

			}
		});
		nfcBiiiipButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (nfcUserId.getText().isEmpty()) {
					messagesToUser.setText("bad read nfc");
				} else {
					nfcUserId.setEditable(false);
					theFSM.raisePaidNFC();
					theFSM.raiseDoAction();
				}
			}
		});

	}

	public void display() {
		if (selection.equals(Drink.SOUP)) {
			lblSugar.setText("spice");
			validateButton.setVisible(true);
			theFSM.setIsValidate(false);
			lblValidate.setVisible(true);

		} else {
			lblSugar.setText("sugar");
			validateButton.setVisible(false);
			theFSM.setIsValidate(true);
			lblValidate.setVisible(false);

		}

	}

	public double calculPrixAvecReduction(String nfcId, float price) {
		// TODO Auto-generated method stub
		return gReduc.calculPrixAvecReduction(nfcId, price);
	}

	public void ajouterPrixBoissonAClient(String nfcId, float price) {
		gReduc.ajouterPrixBoissonAClient(nfcId, price);

	}

	public void disableDrinkIndisponnible() {
		List<Ingredient> indisponnible = gIngredient.ListIngredientFini();
		if (indisponnible.contains(Ingredient.DOSETTECAFE)) {
			coffeeButton.setEnabled(false);
			coffeeButton.invalidate();
		}
		if (indisponnible.contains(Ingredient.DOSESOUPE)) {
			soupButton.setEnabled(false);
			soupButton.invalidate();
		}
		if (indisponnible.contains(Ingredient.DOSEGRAINCAFE)) {
			expressoButton.setEnabled(false);
			expressoButton.invalidate();
		}
		if (indisponnible.contains(Ingredient.SACHETTHE)) {
			teaButton.setEnabled(false);
			teaButton.invalidate();
		}
	}

	public void decremente(Ingredient i) {
		gIngredient.decremente(i);
	}

	public void ReenableDrink() {

	}

}
