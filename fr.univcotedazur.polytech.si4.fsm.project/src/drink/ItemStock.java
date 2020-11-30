package drink;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ItemStock {
	private Ingredient ingredient;
	int stock;
	JButton plus,minus;
	JLabel ingredientLabel,valStock;
	JPanel contentPane;
	public ItemStock(Ingredient i,int s) {
		ingredient=i;
		stock=s;
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBounds(12,28+(ingredient.getPos()+1)*50,300,30);
		//setContentPane(contentPane);
		//contentPane.setLayout(null);
		
		ingredientLabel = new JLabel("<html> "+ingredient.name().toLowerCase());
		ingredientLabel.setBounds(12,28+(ingredient.getPos()+1)*50,96,40);
		ingredientLabel.setForeground(Color.WHITE);
		ingredientLabel.setBackground(Color.DARK_GRAY);
		contentPane.add(ingredientLabel);
		
		plus = new JButton("+");
		plus.setForeground(Color.WHITE);
		plus.setBackground(Color.DARK_GRAY);
		plus.setBounds(120,28+(ingredient.getPos()+1)*50,96,20);
		contentPane.add(plus);
		
		valStock = new JLabel("<html> "+stock);
		valStock.setForeground(Color.WHITE);
		valStock.setBackground(Color.DARK_GRAY);
		valStock.setHorizontalAlignment(SwingConstants.CENTER);
		valStock.setBounds(90,28+(ingredient.getPos()+1)*50,96,40);
		contentPane.add(valStock);
		
		minus = new JButton("-");
		minus.setForeground(Color.WHITE);
		minus.setBackground(Color.DARK_GRAY);
		minus.setBounds(60,28+(ingredient.getPos()+1)*50,96,20);
		contentPane.add(minus);
		
		plus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stock<20) {
					stock++;
					valStock.setText("<html> "+stock);
					valStock.repaint();
				}
			}
		});
		
		minus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(stock>0) {
					stock--;
					valStock.setText("<html> "+(stock));
					valStock.repaint();
				}
			}
		});
		
	}
	
	public JPanel getStockPane(){
		return contentPane;
	}
	public int getStock() {
		return stock;
	}
	public Ingredient getIngredient() {
		return ingredient;
	}
}
