package fr.univcotedazur.polytech.si4.fsm.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import drink.Ingredient;

public class GestionnaireDIngredient {
	
	public Map<String,Double> nbIngredients;	
	protected File storage;
	protected FileOutputStream writter;
	protected Scanner lecture;
	protected Gson gson;
	
	public GestionnaireDIngredient() {
		String json = new String();

		gson = new Gson();
		storage = new File("stock.txt");
		if (!storage.exists()) {
			try {
				storage.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			lecture = new Scanner(storage);
			while (lecture.hasNextLine()) {
				json += lecture.nextLine();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		nbIngredients = gson.fromJson(json, Map.class);
		

		if (nbIngredients == null) {
			nbIngredients = new HashMap<String, Double>();
			for(Ingredient i:Ingredient.values()) {
				nbIngredients.put(i.name(),(double) 0);
			}
		}
	}
	
	
	public void decremente(Ingredient i) {
		
		nbIngredients.put(i.name(), nbIngredients.get(i.name())-1);
		stockerModification();
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
		return  nbIngredients.get(i.name())==0;
	}
	
	public double  getStock(Ingredient i) {
		return nbIngredients.get(i.name());
	}
	
	public void setNbIngrédients(double val, Ingredient i) {
		nbIngredients.put(i.name(),val);
		stockerModification();
	}
	
	
	private void stockerModification() {
		try {
			String json = gson.toJson(nbIngredients);
			writter = new FileOutputStream(storage, false);
			writter.write(json.getBytes());
			writter.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
}
