package fr.univcotedazur.polytech.si4.fsm.project;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import drink.Drink;
import drink.Ingredient;
import fr.univcotedazur.polytech.si4.fsm.project.factory.IFactoryStatemachine.SCInterfaceListener;
import preparation.coffee.CoffeePreparation;
import preparation.expresso.ExpressoPreparation;
import preparation.soup.SoupPreparation;
import preparation.tea.TeaPreparation;

public class DrinkFactoryControllerInterfaceImplementation implements SCInterfaceListener/*SCInterfaceOperationCallback*/ {
	DrinkFactoryMachine factory;
	float coins;
	float price;
	DecimalFormat df;
	CoffeePreparation coffee;
	ExpressoPreparation expresso;
	TeaPreparation tea;
	SoupPreparation soup;
	public DrinkFactoryControllerInterfaceImplementation(DrinkFactoryMachine fact) {
		coins =0;
		factory = fact;
		price = 1000;
		df = new DecimalFormat("0.00");
		coffee= new CoffeePreparation(fact);
		tea = new TeaPreparation(fact);
		expresso = new ExpressoPreparation(fact);
	    soup = new SoupPreparation(fact);
	}

	@Override
	public void onDoRestartRaised() {
		factory.messagesToUser.setText("<html>Choisissez votre boisson ");
		factory.messageForPayment.setVisible(false);
		factory.sizeSlider.setValue(1);
		factory.sugarSlider.setValue(1);
		factory.temperatureSlider.setValue(2);
		factory.progressBar.setValue(0);
		factory.theFSM.setIsPaid(false);
		factory.theFSM.setIsSelected(false);
		factory.theFSM.setIsValidate(true);
		coins = 0;
		factory.setPictureCup("./picts/vide2.jpg");
		factory.reactivateNFC();
		setEnableeButtons(true);
		factory.disableDrinkIndisponnible();
		factory.takeDrinkButton.setVisible(false);
	}
	@Override
	public void onDoPaymentByNFCRaised() {
		factory.messageForPayment.setVisible(true);
		factory.messageForPayment.setText("<html>Paiement par NFC");
		factory.theFSM.setIsPaid(true);

	}
	@Override
	public void onDoSelectionRaised() {
		price = (float)factory.calculPrixAvecReduction(factory.getNfcId(), factory.selection.getPrice())+factory.optionPanel.optionPrice();
		factory.messagesToUser.setText("<html>Vous avez choisi la boisson "+factory.selection.getName()+
				"<br/>Prix: "+df.format(price)+"€");
		factory.displayValidate();
		if(coins>= price)
			factory.theFSM.setIsPaid(true);

	}
	@Override
	public void onAddedCoinRaised() {
		factory.messageForPayment.setVisible(true);
		coins += factory.coin;
		factory.messageForPayment.setText("<html>Payment par espèce  "+df.format(coins)+"€");
		if(coins>= price)
			factory.theFSM.setIsPaid(true);
	}
	@Override
	public void onDoBackCoinRaised() {
		factory.messageForPayment.setText("<html>Vous avez payé par NFC<br> rendu  "+df.format(factory.coin)+"€");

	}
	@Override
	public void onDoMoneyBackRaised() {
		factory.messageForPayment.setText("<html> Vous avez payé "+df.format((coins>0)?coins:price)+"€");
		if (coins > price) 
			factory.messageForPayment.setText(factory.messageForPayment.getText()+"<br/> Rendu de "+df.format(coins-price)+"€");
		factory.theFSM.raiseMoneyBack();

	}


	@Override
	public void onDoStartPreparationRaised() {
		factory.messagesToUser.setText("<html>Debut de la préparation de  "+factory.selection.getName());
		setEnableeButtons(false);
	}

	@Override
	public void onDoPreparationRaised() {
		factory.ajouterPrixBoissonAClient(factory.getNfcId(), factory.selection.getPrice());
		factory.messagesToUser.setText("<html>Préparation en cours de "+factory.selection.getName());
		factory.messageForPayment.setText("");

		if(factory.selection.equals(Drink.COFFE)) {
			factory.decremente(Ingredient.DOSETTECAFE);
			factory.getProgressBar().setValue(0);
			coffee.prepare(factory.getSugarSize(), factory.getDrinkSize(), factory.getTemperature(),factory.optionPanel.getOptions());
		}else if(factory.selection.equals(Drink.EXPRESSO)) {
			factory.decremente(Ingredient.DOSEGRAINCAFE);
			factory.getProgressBar().setValue(0);
			expresso.prepare(factory.getSugarSize(), factory.getDrinkSize(), factory.getTemperature(),factory.optionPanel.getOptions());
		}else if(factory.selection.equals(Drink.TEA)) {
			factory.decremente(Ingredient.SACHETTHE);
			factory.getProgressBar().setValue(0);
			tea.prepare(factory.getSugarSize(), factory.getDrinkSize(), factory.getTemperature(),factory.optionPanel.getOptions());
		} else if(factory.selection.equals(Drink.SOUP)) {
			factory.decremente(Ingredient.DOSESOUPE);
			factory.getProgressBar().setValue(0);
			factory.validateButton.setVisible(false);
			factory.lblValidate.setVisible(false);
			soup.prepare(factory.getSugarSize(), factory.getDrinkSize(), factory.getTemperature(),factory.optionPanel.getOptions());

		}
	}

	@Override
	public void onDoRefundRaised() {
			factory.messagesToUser.setText("<html>Operation annulée<br/>Remboursement de "+df.format(coins)+"€");
			factory.theFSM.raiseRefunded();

	}

	@Override
	public void onDoCleanRaised() {
		factory.setPictureCup("./picts/vide2.jpg");
		factory.takeDrinkButton.setVisible(false);
		factory.messagesToUser.setText("<html>Nettoyage de la machine en cours");
		
	}

	@Override
	public void onDoTakeDrinkRaised() {
		factory.messagesToUser.setText("<html>Veuillez récuperer votre boisson");
		factory.takeDrinkButton.setVisible(true);
	}
	
	private void setEnableeButtons(boolean enabled) {
		factory.coffeeButton.setEnabled(enabled);
		factory.soupButton.setEnabled(enabled);
		factory.expressoButton.setEnabled(enabled);
		factory.teaButton.setEnabled(enabled);
		factory.icedTeaButton.setEnabled(enabled);
		factory.addCupButton.setEnabled(enabled);

	}
		
}


