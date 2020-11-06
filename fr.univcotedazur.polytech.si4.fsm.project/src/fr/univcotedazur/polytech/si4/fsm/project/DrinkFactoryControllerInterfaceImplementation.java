package fr.univcotedazur.polytech.si4.fsm.project;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import drink.Drink;
import fr.univcotedazur.polytech.si4.fsm.project.factory.IFactoryStatemachine.SCInterfaceListener;
import preparation.coffee.CoffeePreparation;
import preparation.expresso.ExpressoPreparation;
import preparation.tea.TeaPreparation;

public class DrinkFactoryControllerInterfaceImplementation implements SCInterfaceListener/*SCInterfaceOperationCallback*/ {
	DrinkFactoryMachine factory;
	double coins;
	double price;
	DecimalFormat df;
	CoffeePreparation coffee;
	ExpressoPreparation expresso;
	TeaPreparation tea;
	public DrinkFactoryControllerInterfaceImplementation(DrinkFactoryMachine fact) {
		coins =0;
		factory = fact;
		price = 1000;
		df = new DecimalFormat("0.00");
		coffee= new CoffeePreparation(fact);
		tea = new TeaPreparation(fact);
		expresso = new ExpressoPreparation(fact);
	    
	}

	@Override
	public void onDoRestartRaised() {
		factory.messagesToUser.setText("<html>Choisissez votre boisson ");
		factory.sizeSlider.setValue(1);
		factory.sugarSlider.setValue(1);
		factory.temperatureSlider.setValue(2);
		factory.theFSM.setIsPaid(false);
		factory.theFSM.setIsSelected(false);

		coins = 0;
	}
	@Override
	public void onDoPaymentByNFCRaised() {
		factory.messagesToUser.setText("<html>Paiement par NFC");

	}
	@Override
	public void onDoSelectionRaised() {
		price = 0.5;
		factory.messagesToUser.setText("<html>Vous avez choisi la boisson "+factory.selection);
		if(coins>= price)
			factory.theFSM.setIsPaid(true);

	}
	@Override
	public void onAddedCoinRaised() {
		coins += factory.coin;
		if(coins <= price)
			factory.messagesToUser.setText("<html>Payment par espèce  "+df.format(coins)+"€");
		if(coins>= price)
			factory.theFSM.setIsPaid(true);
	}
	@Override
	public void onDoBackCoinRaised() {
		factory.messagesToUser.setText("<html>Vous avez payé par NFC<br> rendu  "+factory.coin);

	}
	@Override
	public void onDoMoneyBackRaised() {
		if (coins > price)
			factory.messagesToUser.setText("<html> Rendu de "+(coins-price)+"€");
		factory.theFSM.raiseMoneyBack();

	}
	@Override
	public void onDoStartPreparationRaised() {
		factory.messagesToUser.setText("<html>Debut de la préparation de  "+factory.selection);
		if(factory.selection.equals(Drink.COFFE.getName())) {
			factory.getProgressBar().setValue(0);
			coffee.prepare(factory.getSugarSize(), factory.getDrinkSize(), factory.getTemperature());
		}else if(factory.selection.equals(Drink.EXPRESSO.getName())) {
			factory.getProgressBar().setValue(0);
			expresso.prepare(factory.getSugarSize(), factory.getDrinkSize(), factory.getTemperature());
		}else if(factory.selection.equals(Drink.TEA.getName())) {
			factory.getProgressBar().setValue(0);
			expresso.prepare(factory.getSugarSize(), factory.getDrinkSize(), factory.getTemperature());
		}
	}

	@Override
	public void onDoRefundRaised() {
			factory.messagesToUser.setText("<html>Operation annulée<br/>Remboursement de "+df.format(coins)+"€");
			factory.theFSM.raiseRefunded();

	}

	@Override
	public void onDoCleanRaised() {
		factory.takeDrinkButton.setVisible(false);
		factory.messagesToUser.setText("<html>Nettoyage de la machine en cours");
		
	}

	@Override
	public void onDoTakeDrinkRaised() {
		factory.messagesToUser.setText("<html>Veuillez récuperer votre boisson");
		factory.takeDrinkButton.setVisible(true);
	}


	
		
}


