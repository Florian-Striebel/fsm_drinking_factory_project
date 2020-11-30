/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.factory;

import fr.univcotedazur.polytech.si4.fsm.project.IStatemachine;
import fr.univcotedazur.polytech.si4.fsm.project.ITimerCallback;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public interface IFactoryStatemachine extends ITimerCallback,IStatemachine {
	public interface SCInterface {
	
		public void raiseDoAction();
		
		public void raisePaidNFC();
		
		public void raiseSelected();
		
		public void raiseAddCoin();
		
		public void raiseCancel();
		
		public void raiseMoneyBack();
		
		public void raiseRefunded();
		
		public void raisePreparationFinished();
		
		public void raiseTakeDrink();
		
		public void raiseStartBar();
		
		public void raiseAddCup();
		
		public void raiseAddStock();
		
		public void raiseStockAdded();
		
		public boolean isRaisedDoAddStock();
		
		public boolean isRaisedAddedCup();
		
		public boolean isRaisedDoTakeDrink();
		
		public boolean isRaisedDoRefund();
		
		public boolean isRaisedDoRestart();
		
		public boolean isRaisedDoClean();
		
		public boolean isRaisedDoPaymentByNFC();
		
		public boolean isRaisedDoBackCoin();
		
		public boolean isRaisedDoMoneyBack();
		
		public boolean isRaisedDoStartPreparation();
		
		public boolean isRaisedDoSelection();
		
		public boolean isRaisedAddedCoin();
		
		public boolean isRaisedDoPreparation();
		
		public boolean isRaisedIncreaseBar();
		
		public boolean getIsSelected();
		
		public void setIsSelected(boolean value);
		
		public boolean getIsPaid();
		
		public void setIsPaid(boolean value);
		
		public boolean getIsValidate();
		
		public void setIsValidate(boolean value);
		
		public long getTimeToUpdateBar();
		
		public void setTimeToUpdateBar(long value);
		
	public List<SCInterfaceListener> getListeners();
	}
	
	public interface SCInterfaceListener {
	
		public void onDoAddStockRaised();
		public void onAddedCupRaised();
		public void onDoTakeDrinkRaised();
		public void onDoRefundRaised();
		public void onDoRestartRaised();
		public void onDoCleanRaised();
		public void onDoPaymentByNFCRaised();
		public void onDoBackCoinRaised();
		public void onDoMoneyBackRaised();
		public void onDoStartPreparationRaised();
		public void onDoSelectionRaised();
		public void onAddedCoinRaised();
		public void onDoPreparationRaised();
		public void onIncreaseBarRaised();
		}
	
	public SCInterface getSCInterface();
	
}
