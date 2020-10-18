package fr.univcotedazur.polytech.si4.fsm.project;

import fr.univcotedazur.polytech.si4.fsm.project.factory.IFactoryStatemachine.SCInterfaceListener;

public class DrinkFactoryControllerInterfaceImplementation implements SCInterfaceListener/*SCInterfaceOperationCallback*/ {
	DrinkFactoryMachine factory;
	double coins;
	double price;
	boolean nfc;
	
	public DrinkFactoryControllerInterfaceImplementation(DrinkFactoryMachine fact) {
		coins =0;
		factory = fact;
		price = 0;
		nfc = false;
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
		nfc=true;
		if(price != 0) {
			factory.theFSM.raiseIsPaid();
		}

	}
	@Override
	public void onDoSelectionRaised() {
		factory.messagesToUser.setText("<html>Vous avez choisi la boisson "+factory.theFSM.getSelection());
		price = 50;
		if(coins >= price || nfc)
			factory.theFSM.raiseIsPaid();

	}
	@Override
	public void onAddedCoinRaised() {
		coins += factory.theFSM.getCoin();
		if(coins <= price || price==0)
			factory.messagesToUser.setText("<html>Payment par espèce  "+coins/100);
		if(coins>= price || price!=0 )
			factory.theFSM.raiseIsPaid();
	}
	@Override
	public void onDoBackCoinRaised() {
		factory.messagesToUser.setText("<html>Vous avez payé par NFC<br> rendu  "+factory.theFSM.getCoin());

	}
	@Override
	public void onDoMoneyBackRaised() {
		if (coins > price)
			factory.messagesToUser.setText("<html> rendu  "+((coins/100)-price/100));
		factory.theFSM.raiseMoneyBack();

	}
	@Override
	public void onDoStartPreparationRaised() {
		factory.messagesToUser.setText("<html>Debut de la préparation de  "+factory.theFSM.getSelection());

	}

	@Override
	public void onDoRefundRaised() {
		factory.messagesToUser.setText("<html>Operation annulée  ");
		factory.theFSM.raiseRefunded();


	}

	
		
}


