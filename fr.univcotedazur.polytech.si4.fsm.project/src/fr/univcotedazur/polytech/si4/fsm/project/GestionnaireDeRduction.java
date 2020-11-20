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
			return 10 - drinkPaidByNfcForAUser.get(id).size() % 10;
		} else {
			System.out.println(id + "list" + drinkPaidByNfcForAUser.get(id));
			return 10;
		}
	}

	public double calculPrixAvecReduction(String id, double prixBoisson) {

		if (drinkPaidByNfcForAUser.get(id) != null && drinkPaidByNfcForAUser.get(id).size() % 10 == 0) {
			return Math.max(0, prixBoisson - calculPrixMoy10DerBoisson(drinkPaidByNfcForAUser.get(id)));
		} else {
			return prixBoisson;
		}

	}

	private double calculPrixMoy10DerBoisson(List<Double> prix) {
		Double moyenne = 0.0;
		for (int i = 10; i <= 1; i--) {
			moyenne = prix.get(prix.size() - i);
		}

		return moyenne / 10;
	}

	private void stockerModification() {
		try {
			String json = gson.toJson(drinkPaidByNfcForAUser);
			writter = new FileOutputStream(storage, false);
			writter.write(json.getBytes());
			writter.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
}
