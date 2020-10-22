package fr.univcotedazur.polytech.si4.fsm.project;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import fr.univcotedazur.polytech.si4.fsm.project.factory.IFactoryStatemachine.SCInterfaceListener;

public class DrinkFactoryControllerInterfaceImplementation implements SCInterfaceListener/*SCInterfaceOperationCallback*/ {
	DrinkFactoryMachine factory;
	double coins;
	double price;
	DecimalFormat df;
	public DrinkFactoryControllerInterfaceImplementation(DrinkFactoryMachine fact) {
		coins =0;
		factory = fact;
		price = 1000;
		df = new DecimalFormat("0.00");
	}

	@Override
	public void onDoRestartRaised() {
		factory.messagesToUser.setText("<html>choix ");
		factory.sizeSlider.setValue(1);
		factory.sugarSlider.setValue(1);
		factory.temperatureSlider.setValue(2);
		coins = 0;
	}
	@Override
	public void onDoPaymentByNFCRaised() {
		factory.messagesToUser.setText("<html>Paiement par NFC");

	}
	@Override
	public void onDoSelectionRaised() {
		price = 0.5;
		factory.messagesToUser.setText("<html>Vous avez choisi la boisson "+factory.theFSM.getSelection());

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
		factory.messagesToUser.setText("<html>Debut de la préparation de  "+factory.theFSM.getSelection());

	}

	@Override
	public void onDoRefundRaised() {
			factory.messagesToUser.setText("<html>Operation annulée<br/>Remboursement de "+df.format(coins)+"€");
			factory.theFSM.raiseRefunded();

	}

	
		
}


