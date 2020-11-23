package fr.univcotedazur.polytech.si4.fsm.project;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import drink.Option;

public class OptionPanel extends JFrame {
	private static final long serialVersionUID = 2030629304432075314L;

	private JPanel optionPanel;
	private JLabel title;
	private JCheckBox chkMilk, chkMapple, chkIceCream, chkCroutons;
	private JButton validateButton;
	private List<JCheckBox> checkBoxs;
	private DrinkFactoryMachine drinkFactoryMachine;
	private Map<Option,Boolean> options;
 
	public OptionPanel(DrinkFactoryMachine drinkFactoryMachine){
		this.drinkFactoryMachine = drinkFactoryMachine;
		checkBoxs = new ArrayList<>();
		options = new HashMap<Option,Boolean>();
		options.put(Option.MILK,false);
		options.put(Option.ICE_CREAM,false);
		options.put(Option.BREAD_CROUTONS,false);
		options.put(Option.MAPLE_SYRUP,false);
		
		optionPanel = new JPanel();
		optionPanel.setBackground(Color.DARK_GRAY);
		optionPanel.setBounds(170, 150, 300, 300);
		optionPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		optionPanel.setLayout(null);
		optionPanel.setVisible(true);
		
		title = new JLabel("<html>Choisissez vos options");
	    title.setBounds(50,5, 200,50);    
	    title.setForeground(Color.WHITE);
		optionPanel.add(title);
		
		chkMilk = new JCheckBox(Option.MILK.getOption()+": "+Option.MILK.getPrice()+"0 €");
	    chkMapple = new JCheckBox(Option.MAPLE_SYRUP.getOption()+": "+Option.MAPLE_SYRUP.getPrice()+"0 €");
	    chkIceCream = new JCheckBox(Option.ICE_CREAM.getOption()+": "+Option.ICE_CREAM.getPrice()+"0 €");
	    chkCroutons = new JCheckBox(Option.BREAD_CROUTONS.getOption()+": "+Option.BREAD_CROUTONS.getPrice()+"0 €");
	    
	    chkMilk.setBackground(Color.DARK_GRAY);
	    chkMilk.setForeground(Color.WHITE);
	    

	    chkMapple.setBackground(Color.DARK_GRAY);
	    chkMapple.setForeground(Color.WHITE);

	    chkIceCream.setBackground(Color.DARK_GRAY);
	    chkIceCream.setForeground(Color.WHITE);
	    
	    chkCroutons.setBackground(Color.DARK_GRAY);
	    chkCroutons.setForeground(Color.WHITE);

	    optionPanel.add(chkIceCream);
	    optionPanel.add(chkMapple);
	    optionPanel.add(chkMilk);
	    optionPanel.add(chkCroutons);

	    
	    validateButton = new JButton("Pas d'options");
	    validateButton.setForeground(Color.WHITE);
	    validateButton.setBackground(Color.DARK_GRAY);
	    validateButton.setBounds(100,250, 115, 30);
	    optionPanel.add(validateButton);

	    optionPanel.setVisible(false);
	    
	    checkBoxs.add(chkIceCream);
	    checkBoxs.add(chkMapple);
	    checkBoxs.add(chkMilk);
		checkBoxs.add(chkCroutons);

	    
	    validateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				visibleAll(drinkFactoryMachine.contentPane.getComponents());
				optionPanel.setVisible(false);
				drinkFactoryMachine.theFSM.raiseSelected();
            	validateButton.setText("Pas d'options");
            	
			}
		});
	    chkCroutons.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {
            	setTextButton();
            	options.replace(Option.BREAD_CROUTONS, e.getStateChange()==1);
            	}    
         });
	    chkIceCream.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {
            	setTextButton();
            	options.replace(Option.ICE_CREAM, e.getStateChange()==1);
            	}    
         });
	    chkMapple.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {
            	setTextButton();
            	options.replace(Option.MAPLE_SYRUP, e.getStateChange()==1);

            	}    
         });
	    chkMilk.addItemListener(new ItemListener() {    
            public void itemStateChanged(ItemEvent e) {
            	setTextButton();
            	options.replace(Option.MILK, e.getStateChange()==1);

            	}    
         });
	}
	
	private void notVisibleAll(Component[] components) {
		for(int i = 0;i<components.length;i++) {
	    	if(!components[i].equals(optionPanel)) {
	    		components[i].setEnabled(false);
	    		if(components[i].getClass().equals(JPanel.class))
	    			notVisibleAll(((JPanel)components[i]).getComponents());
	    	}
	    }
	}

	private void visibleAll(Component[] components) {
		for(int i = 0;i<components.length;i++) {
	    		components[i].setEnabled(true);
	    		if(components[i].getClass().equals(JPanel.class))
	    			visibleAll(((JPanel)components[i]).getComponents());
	    	}
		drinkFactoryMachine.disableDrinkIndisponnible();
	}
	public void optionsForExpressoAndCoffee(String selection) {
		title.setText("<html>Choisissez vos options pour "+selection);
		notVisibleAll(drinkFactoryMachine.contentPane.getComponents());
		setAllSelectedFalse();
	    chkMilk.setBounds(80,50, 170,50); 
	    chkMapple.setBounds(80,100, 170,50);   
	    chkIceCream.setBounds(80,150, 175,50);    

		chkIceCream.setVisible(true);
		chkCroutons.setVisible(false);
	    chkMilk.setVisible(true);
	    chkMapple.setVisible(true);
	    optionPanel.setVisible(true);

	}
	public void optionsForTea(String selection) {
		title.setText("<html>Choisissez vos options pour "+selection);
		notVisibleAll(drinkFactoryMachine.contentPane.getComponents());
		setAllSelectedFalse();
		
	    chkMapple.setBounds(80,75, 170,50);   
		chkMilk.setBounds(80,125, 170,50);   
		
		chkIceCream.setVisible(false);
		chkCroutons.setVisible(false);
	    chkMilk.setVisible(true);
	    chkMapple.setVisible(true);
	    optionPanel.setVisible(true);

	}
	
	public void optionsForSoup(String selection) {
		title.setText("<html>Choisissez vos options pour "+selection);
		notVisibleAll(drinkFactoryMachine.contentPane.getComponents());
		setAllSelectedFalse();
		
	    chkCroutons.setBounds(80,100, 170,50);   
		
		
		chkIceCream.setVisible(false);
	    chkMilk.setVisible(false);
	    chkMapple.setVisible(false);
	    chkCroutons.setVisible(true);
	    optionPanel.setVisible(true);

	}
	public JPanel getOptionPanel() {
		return optionPanel;
	}
	private void setAllSelectedFalse() {
		for(JCheckBox box : checkBoxs)
			box.setSelected(false);
		 for(Map.Entry<Option, Boolean> option: options.entrySet()) 
			   options.replace(option.getKey(),false);
	}
	
	private void setTextButton() {
		 for(JCheckBox box : checkBoxs) {
			 if(box.isSelected()) {
				 validateButton.setText("Valider");
				 return;
			 }
		 }
		 validateButton.setText("Pas d'options");
	}
	
	public float optionPrice() {
		float optionPrice = 0;
		for(Map.Entry<Option, Boolean> option: options.entrySet()) {
		   if(option.getValue())
			   optionPrice += option.getKey().getPrice();
		}
		return optionPrice;
	}
	public  HashMap<Option,Boolean> getOptions() {
		return new HashMap<Option,Boolean>(options);
	}
}
