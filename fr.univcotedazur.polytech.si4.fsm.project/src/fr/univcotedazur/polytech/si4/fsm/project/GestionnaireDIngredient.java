package fr.univcotedazur.polytech.si4.fsm.project;

import java.util.ArrayList;
import java.util.List;

import drink.Ingredient;

public class GestionnaireDIngredient {
	
	public List<Integer> nbIngredients;
	
	public GestionnaireDIngredient() {
		nbIngredients = new ArrayList<>();
		for(int i=0;i<Ingredient.values().length;i++) {
			nbIngredients.add(2);
		}
	}
	
	
	public void decremente(Ingredient i) {
		
		nbIngredients.set(i.getPos(), nbIngredients.get(i.getPos())-1);
	}
	
	public List<Ingredient> ListIngredientFini(){
		List<Ingredient> ingredients= new ArrayList<>();
		for(Ingredient i: Ingredient.values()) {
			//System.out.println(i.name()+" "+nbIngredients.get(i.getPos()));
			if(RuptureIngredient(i)) ingredients.add(i);
		}
		return ingredients;
	}
	private boolean RuptureIngredient(Ingredient i) {
		return nbIngredients.get(i.getPos())==0;
	}
	
}
