package fr.univcotedazur.polytech.si4.fsm.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

import drink.Ingredient;

public class GestionnaireDeRduction {
	protected Map<String, List<Double>> drinkPaidByNfcForAUser;
	protected File storage;
	protected FileOutputStream writter;
	protected Scanner lecture;
	protected Gson gson;

	public GestionnaireDeRduction() {
		String json = new String();
		drinkPaidByNfcForAUser = new HashMap<>();
		gson = new Gson();
		storage = new File("achat.txt");
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

		drinkPaidByNfcForAUser = gson.fromJson(json, Map.class);
		if (drinkPaidByNfcForAUser == null) {
			drinkPaidByNfcForAUser = new HashMap<>();
		}
	}

	public void ajouterPrixBoissonAClient(String id, double prix) {
		if (drinkPaidByNfcForAUser.containsKey(id)) {
			if(drinkPaidByNfcForAUser.get(id).size()>=10)
				drinkPaidByNfcForAUser.get(id).removeAll(drinkPaidByNfcForAUser.get(id));
			else
				drinkPaidByNfcForAUser.get(id).add(prix);
		} else {
			List<Double> list = new ArrayList<>();
			list.add(prix);
			drinkPaidByNfcForAUser.put(id, list);
		}
		System.out.println(id + " list size" + drinkPaidByNfcForAUser.get(id).size());
		stockerModification();
	}

	public int nombreDAchatAvantReduc(String id) {
		if (drinkPaidByNfcForAUser.containsKey(id)) {
			return 10 - drinkPaidByNfcForAUser.get(id).size();
		} else {
			System.out.println(id + "list" + drinkPaidByNfcForAUser.get(id));
			return 10;
		}
	}

	public double calculPrixAvecReduction(String id, double prixBoisson) {

		if (drinkPaidByNfcForAUser.get(id) != null && drinkPaidByNfcForAUser.get(id).size() == 10) {
			return Math.max(0, prixBoisson - calculPrixMoy10DerBoisson(drinkPaidByNfcForAUser.get(id)));
		} else {
			return prixBoisson;
		}

	}

	public String messageReduction(String id,double prix) {
		DecimalFormat df = new DecimalFormat("0.00");
		if(!drinkPaidByNfcForAUser.containsKey(id))
			return "<html>Il vous reste 10 achats<br>avant de profiter du programme fidélité";
		if(drinkPaidByNfcForAUser.get(id).size()+1<10) {
			return "<html>Il vous reste "+(nombreDAchatAvantReduc(id))+" achats<br>avant de profiter du programme fidélité";
		}
		else if(drinkPaidByNfcForAUser.get(id).size()+1==10) {
			return "<html>Félicitation,votre prochaine achat  est gratuit <br>(dans la limite de "+ df.format(calculPrixMoy10BoissonMessage(drinkPaidByNfcForAUser.get(id),prix))+
				"€<br>sinon une réduction sera appliqué)";
		}
		double prixReduc= calculPrixAvecReduction(id, prix);
		return prixReduc==0?"Achat gratuit":"<html>Réduction: <br>vous avez payé "+df.format(prixReduc)+"€";
	}
	private double calculPrixMoy10DerBoisson(List<Double> prix) {
		Double moyenne = 0.0;
		for (int i = 0; i < 10; i++) {
			moyenne += prix.get(i);
		}

		return moyenne / 10;
	}
	private double calculPrixMoy10BoissonMessage(List<Double> prix,double prix1) {
		List<Double> prix2 = new ArrayList<Double>(prix);
		prix2.add(prix1);
		return calculPrixMoy10DerBoisson(prix2);
	}
	
	private void stockerModification() {
		try {
			String json = gson.toJson(drinkPaidByNfcForAUser);
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
