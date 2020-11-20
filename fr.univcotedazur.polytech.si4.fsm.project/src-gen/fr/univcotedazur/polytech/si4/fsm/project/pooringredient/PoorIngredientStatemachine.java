/** Generated by YAKINDU Statechart Tools code generator. */
package fr.univcotedazur.polytech.si4.fsm.project.pooringredient;

import fr.univcotedazur.polytech.si4.fsm.project.ITimer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PoorIngredientStatemachine implements IPoorIngredientStatemachine {
	protected class SCInterfaceImpl implements SCInterface {
	
		private boolean sugarFinishPoored;
		
		
		public void raiseSugarFinishPoored() {
			synchronized(PoorIngredientStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							sugarFinishPoored = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private boolean drinkFinishPoored;
		
		
		public void raiseDrinkFinishPoored() {
			synchronized(PoorIngredientStatemachine.this) {
				inEventQueue.add(
					new Runnable() {
						@Override
						public void run() {
							drinkFinishPoored = true;
							singleCycle();
						}
					}
				);
				runCycle();
			}
		}
		
		private long timeSugar;
		
		public synchronized long getTimeSugar() {
			synchronized(PoorIngredientStatemachine.this) {
				return timeSugar;
			}
		}
		
		public void setTimeSugar(long value) {
			synchronized(PoorIngredientStatemachine.this) {
				this.timeSugar = value;
			}
		}
		
		private long timeDrink;
		
		public synchronized long getTimeDrink() {
			synchronized(PoorIngredientStatemachine.this) {
				return timeDrink;
			}
		}
		
		public void setTimeDrink(long value) {
			synchronized(PoorIngredientStatemachine.this) {
				this.timeDrink = value;
			}
		}
		
		protected void clearEvents() {
			sugarFinishPoored = false;
			drinkFinishPoored = false;
		}
	}
	
	
	protected SCInterfaceImpl sCInterface;
	
	private boolean initialized = false;
	
	public enum State {
		main_region_poorIngredient,
		main_region_poorIngredient_r1_poorSugar,
		main_region_poorIngredient_r1_sugarPoored,
		main_region_poorIngredient_r2_poorDrink,
		main_region_poorIngredient_r2_drinkPoored,
		main_region__final_,
		$NullState$
	};
	
	private final State[] stateVector = new State[2];
	
	private int nextStateIndex;
	
	private ITimer timer;
	
	private final boolean[] timeEvents = new boolean[3];
	
	private BlockingQueue<Runnable> inEventQueue = new LinkedBlockingQueue<Runnable>();
	private boolean isRunningCycle = false;
	public PoorIngredientStatemachine() {
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
		sCInterface.setTimeSugar(0);
		
		sCInterface.setTimeDrink(0);
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
			case main_region_poorIngredient_r1_poorSugar:
				main_region_poorIngredient_r1_poorSugar_react(true);
				break;
			case main_region_poorIngredient_r1_sugarPoored:
				main_region_poorIngredient_r1_sugarPoored_react(true);
				break;
			case main_region_poorIngredient_r2_poorDrink:
				main_region_poorIngredient_r2_poorDrink_react(true);
				break;
			case main_region_poorIngredient_r2_drinkPoored:
				main_region_poorIngredient_r2_drinkPoored_react(true);
				break;
			case main_region__final_:
				main_region__final__react(true);
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
	* @see IStatemachine#isFinal()
	*/
	public synchronized boolean isFinal() {
		return (stateVector[0] == State.main_region__final_) && (stateVector[1] == State.$NullState$);
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
	}
	
	/**
	* Returns true if the given state is currently active otherwise false.
	*/
	public synchronized boolean isStateActive(State state) {
	
		switch (state) {
		case main_region_poorIngredient:
			return stateVector[0].ordinal() >= State.
					main_region_poorIngredient.ordinal()&& stateVector[0].ordinal() <= State.main_region_poorIngredient_r2_drinkPoored.ordinal();
		case main_region_poorIngredient_r1_poorSugar:
			return stateVector[0] == State.main_region_poorIngredient_r1_poorSugar;
		case main_region_poorIngredient_r1_sugarPoored:
			return stateVector[0] == State.main_region_poorIngredient_r1_sugarPoored;
		case main_region_poorIngredient_r2_poorDrink:
			return stateVector[1] == State.main_region_poorIngredient_r2_poorDrink;
		case main_region_poorIngredient_r2_drinkPoored:
			return stateVector[1] == State.main_region_poorIngredient_r2_drinkPoored;
		case main_region__final_:
			return stateVector[0] == State.main_region__final_;
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
	
	public synchronized void raiseSugarFinishPoored() {
		sCInterface.raiseSugarFinishPoored();
	}
	
	public synchronized void raiseDrinkFinishPoored() {
		sCInterface.raiseDrinkFinishPoored();
	}
	
	public synchronized long getTimeSugar() {
		return sCInterface.getTimeSugar();
	}
	
	public synchronized void setTimeSugar(long value) {
		sCInterface.setTimeSugar(value);
	}
	
	public synchronized long getTimeDrink() {
		return sCInterface.getTimeDrink();
	}
	
	public synchronized void setTimeDrink(long value) {
		sCInterface.setTimeDrink(value);
	}
	
	/* Entry action for state 'poorSugar'. */
	private void entryAction_main_region_poorIngredient_r1_poorSugar() {
		timer.setTimer(this, 0, sCInterface.getTimeSugar(), false);
	}
	
	/* Entry action for state 'poorDrink'. */
	private void entryAction_main_region_poorIngredient_r2_poorDrink() {
		timer.setTimer(this, 1, sCInterface.getTimeDrink(), false);
	}
	
	/* Entry action for state 'drinkPoored'. */
	private void entryAction_main_region_poorIngredient_r2_drinkPoored() {
		timer.setTimer(this, 2, 100, true);
	}
	
	/* Exit action for state 'poorSugar'. */
	private void exitAction_main_region_poorIngredient_r1_poorSugar() {
		timer.unsetTimer(this, 0);
	}
	
	/* Exit action for state 'poorDrink'. */
	private void exitAction_main_region_poorIngredient_r2_poorDrink() {
		timer.unsetTimer(this, 1);
	}
	
	/* Exit action for state 'drinkPoored'. */
	private void exitAction_main_region_poorIngredient_r2_drinkPoored() {
		timer.unsetTimer(this, 2);
	}
	
	/* 'default' enter sequence for state poorIngredient */
	private void enterSequence_main_region_poorIngredient_default() {
		enterSequence_main_region_poorIngredient_r1_default();
		enterSequence_main_region_poorIngredient_r2_default();
	}
	
	/* 'default' enter sequence for state poorSugar */
	private void enterSequence_main_region_poorIngredient_r1_poorSugar_default() {
		entryAction_main_region_poorIngredient_r1_poorSugar();
		nextStateIndex = 0;
		stateVector[0] = State.main_region_poorIngredient_r1_poorSugar;
	}
	
	/* 'default' enter sequence for state sugarPoored */
	private void enterSequence_main_region_poorIngredient_r1_sugarPoored_default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region_poorIngredient_r1_sugarPoored;
	}
	
	/* 'default' enter sequence for state poorDrink */
	private void enterSequence_main_region_poorIngredient_r2_poorDrink_default() {
		entryAction_main_region_poorIngredient_r2_poorDrink();
		nextStateIndex = 1;
		stateVector[1] = State.main_region_poorIngredient_r2_poorDrink;
	}
	
	/* 'default' enter sequence for state drinkPoored */
	private void enterSequence_main_region_poorIngredient_r2_drinkPoored_default() {
		entryAction_main_region_poorIngredient_r2_drinkPoored();
		nextStateIndex = 1;
		stateVector[1] = State.main_region_poorIngredient_r2_drinkPoored;
	}
	
	/* Default enter sequence for state null */
	private void enterSequence_main_region__final__default() {
		nextStateIndex = 0;
		stateVector[0] = State.main_region__final_;
	}
	
	/* 'default' enter sequence for region main region */
	private void enterSequence_main_region_default() {
		react_main_region__entry_Default();
	}
	
	/* 'default' enter sequence for region r1 */
	private void enterSequence_main_region_poorIngredient_r1_default() {
		react_main_region_poorIngredient_r1__entry_Default();
	}
	
	/* 'default' enter sequence for region r2 */
	private void enterSequence_main_region_poorIngredient_r2_default() {
		react_main_region_poorIngredient_r2__entry_Default();
	}
	
	/* Default exit sequence for state poorIngredient */
	private void exitSequence_main_region_poorIngredient() {
		exitSequence_main_region_poorIngredient_r1();
		exitSequence_main_region_poorIngredient_r2();
	}
	
	/* Default exit sequence for state poorSugar */
	private void exitSequence_main_region_poorIngredient_r1_poorSugar() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
		
		exitAction_main_region_poorIngredient_r1_poorSugar();
	}
	
	/* Default exit sequence for state sugarPoored */
	private void exitSequence_main_region_poorIngredient_r1_sugarPoored() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for state poorDrink */
	private void exitSequence_main_region_poorIngredient_r2_poorDrink() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
		
		exitAction_main_region_poorIngredient_r2_poorDrink();
	}
	
	/* Default exit sequence for state drinkPoored */
	private void exitSequence_main_region_poorIngredient_r2_drinkPoored() {
		nextStateIndex = 1;
		stateVector[1] = State.$NullState$;
		
		exitAction_main_region_poorIngredient_r2_drinkPoored();
	}
	
	/* Default exit sequence for final state. */
	private void exitSequence_main_region__final_() {
		nextStateIndex = 0;
		stateVector[0] = State.$NullState$;
	}
	
	/* Default exit sequence for region main region */
	private void exitSequence_main_region() {
		switch (stateVector[0]) {
		case main_region_poorIngredient_r1_poorSugar:
			exitSequence_main_region_poorIngredient_r1_poorSugar();
			break;
		case main_region_poorIngredient_r1_sugarPoored:
			exitSequence_main_region_poorIngredient_r1_sugarPoored();
			break;
		case main_region__final_:
			exitSequence_main_region__final_();
			break;
		default:
			break;
		}
		
		switch (stateVector[1]) {
		case main_region_poorIngredient_r2_poorDrink:
			exitSequence_main_region_poorIngredient_r2_poorDrink();
			break;
		case main_region_poorIngredient_r2_drinkPoored:
			exitSequence_main_region_poorIngredient_r2_drinkPoored();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region r1 */
	private void exitSequence_main_region_poorIngredient_r1() {
		switch (stateVector[0]) {
		case main_region_poorIngredient_r1_poorSugar:
			exitSequence_main_region_poorIngredient_r1_poorSugar();
			break;
		case main_region_poorIngredient_r1_sugarPoored:
			exitSequence_main_region_poorIngredient_r1_sugarPoored();
			break;
		default:
			break;
		}
	}
	
	/* Default exit sequence for region r2 */
	private void exitSequence_main_region_poorIngredient_r2() {
		switch (stateVector[1]) {
		case main_region_poorIngredient_r2_poorDrink:
			exitSequence_main_region_poorIngredient_r2_poorDrink();
			break;
		case main_region_poorIngredient_r2_drinkPoored:
			exitSequence_main_region_poorIngredient_r2_drinkPoored();
			break;
		default:
			break;
		}
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_poorIngredient_r1__entry_Default() {
		enterSequence_main_region_poorIngredient_r1_poorSugar_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region_poorIngredient_r2__entry_Default() {
		enterSequence_main_region_poorIngredient_r2_poorDrink_default();
	}
	
	/* Default react sequence for initial entry  */
	private void react_main_region__entry_Default() {
		enterSequence_main_region_poorIngredient_default();
	}
	
	/* The reactions of state null. */
	private void react_main_region__sync0() {
		enterSequence_main_region__final__default();
	}
	
	private boolean react() {
		return false;
	}
	
	private boolean main_region_poorIngredient_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			did_transition = false;
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
	private boolean main_region_poorIngredient_r1_poorSugar_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[0]) {
				exitSequence_main_region_poorIngredient_r1_poorSugar();
				enterSequence_main_region_poorIngredient_r1_sugarPoored_default();
			} else {
				did_transition = false;
			}
		}
		return did_transition;
	}
	
	private boolean main_region_poorIngredient_r1_sugarPoored_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (((true && isStateActive(State.main_region_poorIngredient_r2_drinkPoored)) && timeEvents[2])) {
				exitSequence_main_region_poorIngredient();
				react_main_region__sync0();
			} else {
				did_transition = false;
			}
		}
		return did_transition;
	}
	
	private boolean main_region_poorIngredient_r2_poorDrink_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (timeEvents[1]) {
				exitSequence_main_region_poorIngredient_r2_poorDrink();
				enterSequence_main_region_poorIngredient_r2_drinkPoored_default();
				main_region_poorIngredient_react(false);
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = main_region_poorIngredient_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean main_region_poorIngredient_r2_drinkPoored_react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			if (((timeEvents[2] && isStateActive(State.main_region_poorIngredient_r1_sugarPoored)) && true)) {
				exitSequence_main_region_poorIngredient();
				react_main_region__sync0();
			} else {
				did_transition = false;
			}
		}
		if (did_transition==false) {
			did_transition = main_region_poorIngredient_react(try_transition);
		}
		return did_transition;
	}
	
	private boolean main_region__final__react(boolean try_transition) {
		boolean did_transition = try_transition;
		
		if (try_transition) {
			did_transition = false;
		}
		if (did_transition==false) {
			did_transition = react();
		}
		return did_transition;
	}
	
}
