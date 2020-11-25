/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.coffee;

import fr.univcotedazur.polytech.si4.fsm.project.ITimer;
import fr.univcotedazur.polytech.si4.fsm.project.pooringredient.PoorIngredientStatemachine;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CoffeeStatemachine implements ICoffeeStatemachine {
	protected class SCInterfaceImpl implements SCInterface {
	
		private List<SCInterfaceListener> listeners = new LinkedList<SCInterfaceListener>();
		
		public List<SCInterfaceListener> getListeners() {
			return listeners;
		}
		private boolean isHot;
		
		
		public void raiseIsHot() {
			synchronized(CoffeeStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							isHot = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean prepare;
		
		
		public void raisePrepare() {
			synchronized(CoffeeStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							prepare = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean placePod;
		
		
		public boolean isRaisedPlacePod() {
			synchronized(CoffeeStatemachine.this) {
				return placePod;
			}
		}
		
		protected void raisePlacePod() {
			synchronized(CoffeeStatemachine.this) {
				placePod = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onPlacePodRaised();
				}
			}
		}
		
		private boolean placeCup;
		
		
		public boolean isRaisedPlaceCup() {
			synchronized(CoffeeStatemachine.this) {
				return placeCup;
			}
		}
		
		protected void raisePlaceCup() {
			synchronized(CoffeeStatemachine.this) {
				placeCup = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onPlaceCupRaised();
				}
			}
		}
		
		private boolean heating;
		
		
		public boolean isRaisedHeating() {
			synchronized(CoffeeStatemachine.this) {
				return heating;
			}
		}
		
		protected void raiseHeating() {
			synchronized(CoffeeStatemachine.this) {
				heating = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onHeatingRaised();
				}
			}
		}
		
		private boolean addingMilk;
		
		
		public boolean isRaisedAddingMilk() {
			synchronized(CoffeeStatemachine.this) {
				return addingMilk;
			}
		}
		
		protected void raiseAddingMilk() {
			synchronized(CoffeeStatemachine.this) {
				addingMilk = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onAddingMilkRaised();
				}
			}
		}
		
		private boolean preparationFinished;
		
		
		public boolean isRaisedPreparationFinished() {
			synchronized(CoffeeStatemachine.this) {
				return preparationFinished;
			}
		}
		
		protected void raisePreparationFinished() {
			synchronized(CoffeeStatemachine.this) {
				preparationFinished = true;
				for (SCInterfaceListener listener : listeners) {
					listener.onPreparationFinishedRaised();
				}
			}
		}
		
		private PoorIngredientStatemachine poorI;
		
		public synchronized PoorIngredientStatemachine getPoorI() {
			synchronized(CoffeeStatemachine.this) {
				return poorI;
			}
		}
		
		public void setPoorI(PoorIngredientStatemachine value) {
			synchronized(CoffeeStatemachine.this) {
				this.poorI = value;
			}
		}
		
		private boolean milk;
		
		public synchronized boolean getMilk() {
			synchronized(CoffeeStatemachine.this) {
				return milk;
			}
		}
		
		public void setMilk(boolean value) {
			synchronized(CoffeeStatemachine.this) {
				this.milk = value;
			}
		}
		
		private boolean userCup;
		
		public synchronized boolean getUserCup() {
			synchronized(CoffeeStatemachine.this) {
				return userCup;
			}
		}
		
		public void setUserCup(boolean value) {
			synchronized(CoffeeStatemachine.this) {
				this.userCup = value;
			}
		}
		
		protected void clearEvents() {
			isHot = false;
			prepare = false;
		}
		protected void clearOutEvents() {
		
		placePod = false;
		placeCup = false;
		heating = false;
		addingMilk = false;
		preparationFinished = false;
		}
		
	}
	
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_prepareIngredient,
		main_region_prepareIngredient_r1_positionningCup,
		main_region_prepareIngredient_r1_getPodCoffee,
		main_region_prepareIngredient_r1_cupPositionned,
		main_region_prepareIngredient_r2_beginHeating,
		main_region_prepareIngredient_r2_IsHot,
		main_region_DrinkDistribute,
		main_region_Ready,
		main_region_pooringIngredients,
		main_region_addMilk,
		$NullState$
	};
	
	private final State[] stateVector = new State[2];
	
	private int nextStateIndex;
	
	private ITimer timer;
	
	private final boolean[] timeEvents = new boolean[5];
	
	private BlockingQueue<Runnable> inEventQueue = new LinkedBlockingQueue<Runnable>();
	private boolean isRunningCycle = false;
	public CoffeeStatemachine() {
		sCInterface = new SCInterfaceImpl();
	}
	
	public synchronized void init() {
		this.initialized = true;
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		for (int i = 0; i < 2; i++) {
			stateVector[i] = State.$NullState$;
		}
		clearEvents();
		clearOutEvents();
		sCInterface.setMilk(false);
		
		sCInterface.setUserCup(false);
	}
	
	public synchronized void enter() {
		if (!initialized) {
			throw new IllegalStateException(
				"The state machine needs to be initialized first by calling the init() function."
			);
		}
		if (timer == null) {
			throw new IllegalStateException("timer not set.");
		}
		enterSequence_main_region_default();
	}
	
	public synchronized void runCycle() {
		if (!initialized)
			throw new IllegalStateException(
					"The state machine needs to be initialized first by calling the init() function.");
		
		if (isRunningCycle) {
			return;
		}
		isRunningCycle = true;
		
		clearOutEvents();
	
		Runnable task = getNextEvent();
		if (task == null) {
			task = getDefaultEvent();
		}
		
		while (task != null) {
			task.run();
			clearEvents();
			task = getNextEvent();
		}
		
		isRunningCycle = false;
	}
	
	protected synchronized void singleCycle() {
		for (nextStateIndex = 0; nextStateIndex < stateVector.length; nextStateIndex++) {
			switch (stateVector[nextStateIndex]) {
			case main_region_prepareIngredient_r1_positionningCup:
				main_region_prepareIngredient_r1_positionningCup_react(true);
				break;
			case main_region_prepareIngredient_r1_getPodCoffee:
				main_region_prepareIngredient_r1_getPodCoffee_react(true);
				break;
			case main_region_prepareIngredient_r1_cupPositionned:
				main_region_prepareIngredient_r1_cupPositionned_react(true);
				break;
			case main_region_prepareIngredient_r2_beginHeating:
				main_region_prepareIngredient_r2_beginHeating_react(true);
				break;
			case main_region_prepareIngredient_r2_IsHot:
				main_region_prepareIngredient_r2_IsHot_react(true);
				break;
			case main_region_DrinkDistribute:
				main_region_DrinkDistribute_react(true);
				break;
			case main_region_Ready:
				main_region_Ready_react(true);
				break;
			case main_region_pooringIngredients:
				main_region_pooringIngredients_react(true);
				break;
			case main_region_addMilk:
				main_region_addMilk_react(true);
				break;
			default:
				// $NullState$
			}
		}
	}
	
	protected Runnable getNextEvent() {
		if(!inEventQueue.isEmpty()) {
			return inEventQueue.poll();
		}
		return null;
	}
	
	protected Runnable getDefaultEvent() {
		return new Runnable() {
			@Override
			public void run() {
				singleCycle();
			}
		};
	}
	
	public synchronized void exit() {
		exitSequence_main_region();
	}
	
	/**
	 * @see IStatemachine#isActive()
	 */
	public synchronized boolean isActive() {
		return stateVector[0] != State.$NullState$||stateVector[1] != State.$NullState$;
	}
	
	/** 
	* Always returns 'false' since this state machine can never become final.
	*
	* @see IStatemachine#isFinal()
	*/
	public synchronized boolean isFinal() {
		return false;
	}
	/**
	* This method resets the incoming events (time events included).
	*/
	protected void clearEvents() {
		sCInterface.clearEvents();
		for (int i=0; i<timeEvents.length; i++) {
			timeEvents[i] = false;
		}
	}
	
	/**
	* This method resets the outgoing events.
	*/
	protected void clearOutEvents() {
		sCInterface.clearOutEvents();
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public synchronized boolean isStateActive(State state) {
	
		switch (state) {
		case main_region_prepareIngredient:
			return stateVector[0].ordinal() >= State.
					main_region_prepareIngredient.ordinal()&& stateVector[0].ordinal() <= State.main_region_prepareIngredient_r2_IsHot.ordinal();
		case main_region_prepareIngredient_r1_positionningCup:
			return stateVector[0] == State.main_region_prepareIngredient_r1_positionningCup;
		case main_region_prepareIngredient_r1_getPodCoffee:
			return stateVector[0] == State.main_region_prepareIngredient_r1_getPodCoffee;
		case main_region_prepareIngredient_r1_cupPositionned:
			return stateVector[0] == State.main_region_prepareIngredient_r1_cupPositionned;
		case main_region_prepareIngredient_r2_beginHeating:
			return stateVector[1] == State.main_region_prepareIngredient_r2_beginHeating;
		case main_region_prepareIngredient_r2_IsHot:
			return stateVector[1] == State.main_region_prepareIngredient_r2_IsHot;
		case main_region_DrinkDistribute:
			return stateVector[0] == State.main_region_DrinkDistribute;
		case main_region_Ready:
			return stateVector[0] == State.main_region_Ready;
		case main_region_pooringIngredients:
			return stateVector[0] == State.main_region_pooringIngredients;
		case main_region_addMilk:
			return stateVector[0] == State.main_region_addMilk;
		default:
			return false;
		}
	}
	
	/**
	* Set the {@link ITimer} for the state machine. It must be set
	* externally on a timed state machine before a run cycle can be correctly
	* executed.
	* 
	* @param timer
	*/
	public synchronized void setTimer(ITimer timer) {
		this.timer = timer;
	}
	
	/**
	* Returns the currently used timer.
	* 
	* @return {@link ITimer}
	*/
	public ITimer getTimer() {
		return timer;
	}
	
	public synchronized void timeElapsed(int eventID) {
		inEventQueue.add(new Runnable() {
			@Override
			public void run() {
				timeEvents[eventID] = true;
				singleCycle();
			}
		});
		runCycle();
	}
	
	public SCInterface getSCInterface() {
		return sCInterface;
	}
	
	public synchronized void raiseIsHot() {
		sCInterface.raiseIsHot();
	}
	
	public synchronized void raisePrepare() {
		sCInterface.raisePrepare();
	}
	
	public synchronized boolean isRaisedPlacePod() {
		return sCInterface.isRaisedPlacePod();
	}
	
	public synchronized boolean isRaisedPlaceCup() {
		return sCInterface.isRaisedPlaceCup();
	}
	
	public synchronized boolean isRaisedHeating() {
		return sCInterface.isRaisedHeating();
	}
	
	public synchronized boolean isRaisedAddingMilk() {
		return sCInterface.isRaisedAddingMilk();
	}
	
	public synchronized boolean isRaisedPreparationFinished() {
		return sCInterface.isRaisedPreparationFinished();
	}
	
	public synchronized PoorIngredientStatemachine getPoorI() {
		return sCInterface.getPoorI();
	}
	
	public synchronized void setPoorI(PoorIngredientStatemachine value) {
		sCInterface.setPoorI(value);
	}
	
	public synchronized boolean getMilk() {
		return sCInterface.getMilk();
	}
	
	public synchronized void setMilk(boolean value) {
		sCInterface.setMilk(value);
	}
	
	public synchronized boolean getUserCup() {
		return sCInterface.getUserCup();
	}
	
	public synchronized void setUserCup(boolean value) {
		sCInterface.setUserCup(value);
	}
	
	private boolean check_main_region_prepareIngredient_r1__choice_0_tr1_tr1() {
		return sCInterface.getUserCup();
	}
	
	private boolean check_main_region__choice_0_tr1_tr1() {
		return sCInterface.getMilk();
	}
	
	private void effect_main_region_prepareIngredient_r1__choice_0_tr1() {
		enterSequence_main_region_prepareIngredient_r1_cupPositionned_default();
	}
	
	private void effect_main_region_prepareIngredient_r1__choice_0_tr0() {
		enterSequence_main_region_prepareIngredient_r1_positionningCup_default();
	}
	
	private void effect_main_region__choice_0_tr1() {
		enterSequence_main_region_addMilk_default();
	}
	
	private void effect_main_region__choice_0_tr0() {
		enterSequence_main_region_DrinkDistribute_default();
	}
	
	/* Entry action for state 'positionningCup'. */
	private void entryAction_main_region_prepareIngredient_r1_positionningCup() {
		timer.setTimer(this, 0, (2 * 1000), false);
		
		sCInterface.raisePlaceCup();
	}
	
	/* Entry action for state 'getPodCoffee'. */
	private void entryAction_main_region_prepareIngredient_r1_getPodCoffee() {
		timer.setTimer(this, 1, (1 * 1000), false);
		
		sCInterface.raisePlacePod();
	}
	
	/* Entry action for state 'beginHeating'. */
	private void entryAction_main_region_prepareIngredient_r2_beginHeating() {
		sCInterface.raiseHeating();
	}
	
	/* Entry action for state 'DrinkDistribute'. */
	private void entryAction_main_region_DrinkDistribute() {
		timer.setTimer(this, 2, 100, false);
		
		sCInterface.raisePreparationFinished();
	}
	
	/* Entry action for state 'pooringIngredients'. */
	private void entryAction_main_region_pooringIngredients() {
		timer.setTimer(this, 3, 100, true);
		
		sCInterface.getPoorI().enter();
	}
	
	/* Entry action for state 'addMilk'. */
	private void entryAction_main_region_addMilk() {
		timer.setTimer(this, 4, (2 * 1000), false);
		
		sCInterface.raiseAddingMilk();
	}
	
	/* Exit action for state 'positionningCup'. */
	private void exitAction_main_region_prepareIngredient_r1_positionningCup() {
		timer.unsetTimer(this, 0);
	}
	
	/* Exit action for state 'getPodCoffee'. */
	private void exitAction_main_region_prepareIngredient_r1_getPodCoffee() {
		timer.unsetTimer(this, 1);
	}
	
	/* Exit action for state 'DrinkDistribute'. */
	private void exitAction_main_region_DrinkDistribute() {
		timer.unsetTimer(this, 2);
	}
	
	/* Exit action for state 'pooringIngredients'. */
	private void exitAction_main_region_pooringIngredients() {
		timer.unsetTimer(this, 3);
		
		sCInterface.getPoorI().exit();
	}
	
	/* Exit action for state 'addMilk'. */
	private void exitAction_main_region_addMilk() {
		timer.unsetTimer(this, 4);
	}
	
	/* 'default' enter sequence for state prepareIngredient */
	private void enterSequence_main_region_prepareIngredient_default() {
		enterSequence_main_region_prepareIngredient_r1_default();
		enterSequence_main_region_prepareIngredient_r2_default();
	}
	
	/* 'default' enter sequence for state positionningCup */
	private void enterSequence_main_region_prepareIngredient_r1_positionningCup_default() {
		entryAction_main_region_prepareIngredient_r1_positionningCup();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_prepareIngredient_r1_positionningCup;
	}
	
	/* 'default' enter sequence for state getPodCoffee */
	private void enterSequence_main_region_prepareIngredient_r1_getPodCoffee_default() {
		entryAction_main_region_prepareIngredient_r1_getPodCoffee();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_prepareIngredient_r1_getPodCoffee;
	}
	
	/* 'default' enter sequence for state cupPositionned */
	private void enterSequence_main_region_prepareIngredient_r1_cupPositionned_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_prepareIngredient_r1_cupPositionned;
	}
	
	/* 'default' enter sequence for state beginHeating */
	private void enterSequence_main_region_prepareIngredient_r2_beginHeating_default() {
		entryAction_main_region_prepareIngredient_r2_beginHeating();
		nextStateIndex = 1;
		stateVector[1] = State.main_region_prepareIngredient_r2_beginHeating;
	}
	
	/* 'default' enter sequence for state IsHot */
	private void enterSequence_main_region_prepareIngredient_r2_IsHot_default() {
		nextStateIndex = 1;
		stateVector[1] = State.main_region_prepareIngredient_r2_IsHot;
	}
	
	/* 'default' enter sequence for state DrinkDistribute */
	private void enterSequence_main_region_DrinkDistribute_default() {
		entryAction_main_region_DrinkDistribute();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_DrinkDistribute;
	}
	
	/* 'default' enter sequence for state Ready */
	private void enterSequence_main_region_Ready_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_Ready;
	}
	
	/* 'default' enter sequence for state pooringIngredients */
	private void enterSequence_main_region_pooringIngredients_default() {
		entryAction_main_region_pooringIngredients();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_pooringIngredients;
	}
	
	/* 'default' enter sequence for state addMilk */
	private void enterSequence_main_region_addMilk_default() {
		entryAction_main_region_addMilk();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_addMilk;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* 'default' enter sequence for region r1 */
	private void enterSequence_main_region_prepareIngredient_r1_default() {
		react_main_region_prepareIngredient_r1__entry_Default();
	}
	
	/* 'default' enter sequence for region r2 */
	private void enterSequence_main_region_prepareIngredient_r2_default() {
		react_main_region_prepareIngredient_r2__entry_Default();
	}
	
	/* Default exit sequence for state prepareIngredient */
	private void exitSequence_main_region_prepareIngredient() {
		exitSequence_main_region_prepareIngredient_r1();
		exitSequence_main_region_prepareIngredient_r2();
	}
	
	/* Default exit sequence for state positionningCup */
	private void exitSequence_main_region_prepareIngredient_r1_positionningCup() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_prepareIngredient_r1_positionningCup();
	}
	
	/* Default exit sequence for state getPodCoffee */
	private void exitSequence_main_region_prepareIngredient_r1_getPodCoffee() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_prepareIngredient_r1_getPodCoffee();
	}
	
	/* Default exit sequence for state cupPositionned */
	private void exitSequence_main_region_prepareIngredient_r1_cupPositionned() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state beginHeating */
	private void exitSequence_main_region_prepareIngredient_r2_beginHeating() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state IsHot */
	private void exitSequence_main_region_prepareIngredient_r2_IsHot() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
	}
	
	/* Default exit sequence for state DrinkDistribute */
	private void exitSequence_main_region_DrinkDistribute() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_DrinkDistribute();
	}
	
	/* Default exit sequence for state Ready */
	private void exitSequence_main_region_Ready() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state pooringIngredients */
	private void exitSequence_main_region_pooringIngredients() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_pooringIngredients();
	}
	
	/* Default exit sequence for state addMilk */
	private void exitSequence_main_region_addMilk() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_addMilk();
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_prepareIngredient_r1_positionningCup:
			exitSequence_main_region_prepareIngredient_r1_positionningCup();
			break;
		case main_region_prepareIngredient_r1_getPodCoffee:
			exitSequence_main_region_prepareIngredient_r1_getPodCoffee();
			break;
		case main_region_prepareIngredient_r1_cupPositionned:
			exitSequence_main_region_prepareIngredient_r1_cupPositionned();
			break;
		case main_region_DrinkDistribute:
			exitSequence_main_region_DrinkDistribute();
			break;
		case main_region_Ready:
			exitSequence_main_region_Ready();
			break;
		case main_region_pooringIngredients:
			exitSequence_main_region_pooringIngredients();
			break;
		case main_region_addMilk:
			exitSequence_main_region_addMilk();
			break;
		default:
			break;
		}
		
		switch (stateVector[1]) {
		case main_region_prepareIngredient_r2_beginHeating:
			exitSequence_main_region_prepareIngredient_r2_beginHeating();
			break;
		case main_region_prepareIngredient_r2_IsHot:
			exitSequence_main_region_prepareIngredient_r2_IsHot();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region r1 */
	private void exitSequence_main_region_prepareIngredient_r1() {
		switch (stateVector[0]) {
		case main_region_prepareIngredient_r1_positionningCup:
			exitSequence_main_region_prepareIngredient_r1_positionningCup();
			break;
		case main_region_prepareIngredient_r1_getPodCoffee:
			exitSequence_main_region_prepareIngredient_r1_getPodCoffee();
			break;
		case main_region_prepareIngredient_r1_cupPositionned:
			exitSequence_main_region_prepareIngredient_r1_cupPositionned();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region r2 */
	private void exitSequence_main_region_prepareIngredient_r2() {
		switch (stateVector[1]) {
		case main_region_prepareIngredient_r2_beginHeating:
			exitSequence_main_region_prepareIngredient_r2_beginHeating();
			break;
		case main_region_prepareIngredient_r2_IsHot:
			exitSequence_main_region_prepareIngredient_r2_IsHot();
			break;
		default:
			break;
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region_prepareIngredient_r1__choice_0() {
		if (check_main_region_prepareIngredient_r1__choice_0_tr1_tr1()) {
			effect_main_region_prepareIngredient_r1__choice_0_tr1();
		} else {
			effect_main_region_prepareIngredient_r1__choice_0_tr0();
		}
	}
	
	/* The reactions of state null. */
	private void react_main_region__choice_0() {
		if (check_main_region__choice_0_tr1_tr1()) {
			effect_main_region__choice_0_tr1();
		} else {
			effect_main_region__choice_0_tr0();
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_Ready_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_prepareIngredient_r1__entry_Default() {
		enterSequence_main_region_prepareIngredient_r1_getPodCoffee_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_prepareIngredient_r2__entry_Default() {
		enterSequence_main_region_prepareIngredient_r2_beginHeating_default();
	}
	
	/* The reactions of state null. */
	private void react_main_region__sync0() {
		enterSequence_main_region_pooringIngredients_default();
	}
	
	private boolean react() {
		return false;
	}
	
	private boolean main_region_prepareIngredient_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			did_transition = false;
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_prepareIngredient_r1_positionningCup_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[0]) {
				exitSequence_main_region_prepareIngredient_r1_positionningCup();
				enterSequence_main_region_prepareIngredient_r1_cupPositionned_default();
			} else {
				did_transition = false;
			}
		}
		return did_transition;
	}
	
	private boolean main_region_prepareIngredient_r1_getPodCoffee_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[1]) {
				exitSequence_main_region_prepareIngredient_r1_getPodCoffee();
				react_main_region_prepareIngredient_r1__choice_0();
			} else {
				did_transition = false;
			}
		}
		return did_transition;
	}
	
	private boolean main_region_prepareIngredient_r1_cupPositionned_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (((true && isStateActive(State.main_region_prepareIngredient_r2_IsHot)) && true)) {
				exitSequence_main_region_prepareIngredient();
				react_main_region__sync0();
			} else {
				did_transition = false;
			}
		}
		return did_transition;
	}
	
	private boolean main_region_prepareIngredient_r2_beginHeating_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.isHot) {
				exitSequence_main_region_prepareIngredient_r2_beginHeating();
				enterSequence_main_region_prepareIngredient_r2_IsHot_default();
				main_region_prepareIngredient_react(false);
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = main_region_prepareIngredient_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean main_region_prepareIngredient_r2_IsHot_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (((true && isStateActive(State.main_region_prepareIngredient_r1_cupPositionned)) && true)) {
				exitSequence_main_region_prepareIngredient();
				react_main_region__sync0();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = main_region_prepareIngredient_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean main_region_DrinkDistribute_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[2]) {
				exitSequence_main_region_DrinkDistribute();
				enterSequence_main_region_Ready_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_Ready_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (sCInterface.prepare) {
				exitSequence_main_region_Ready();
				enterSequence_main_region_prepareIngredient_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_pooringIngredients_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (((timeEvents[3]) && (sCInterface.getPoorI().isFinal()))) {
				exitSequence_main_region_pooringIngredients();
				react_main_region__choice_0();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_addMilk_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[4]) {
				exitSequence_main_region_addMilk();
				enterSequence_main_region_DrinkDistribute_default();
				react();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
}
