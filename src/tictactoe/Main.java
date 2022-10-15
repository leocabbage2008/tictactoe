package tictactoe;
import java.awt.*;      
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {

   static boolean turn = true;
   static String winner = "";
   JButton buttons[] = new JButton[9];
   JPanel panel = new JPanel(new BorderLayout());
   JLabel label = new JLabel("");
   JLabel title = new JLabel("Tic Tac Toe");
   JPanel t = new JPanel(new GridLayout(3,1));
   JPanel r = new JPanel(new GridLayout(1,2));
   JPanel p = new JPanel(new GridLayout(3,3));
   JButton reset = new JButton("Reset");
   JButton exit = new JButton("Exit");
   
   public Main() {
//	   title
	   title.setFont(new Font("Serif", Font.ITALIC, 36));
	   title.setHorizontalAlignment(JLabel.CENTER);
	   t.add(title);
	   label.setFont(new Font("Serif", Font.BOLD, 20));
	   label.setHorizontalAlignment(JLabel.CENTER);
	   t.add(label);
//	   reset
	   exit.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {
			   System.exit(0);
		   }
	   });
	   reset.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) {			   
			   Component[] com = p.getComponents();
			   for (int a = 0; a < com.length; a++) {
				   com[a].setEnabled(true);
			   }
			   for(int i = 0; i < 9; i++) buttons[i].setText("");
			   label.setText("");
		   }
	   });
	   r.add(exit);
	   r.add(reset);
	   t.add(r);
	   
//	   grid
	   for(int i = 0; i <= 8; i++){
		   buttons[i] = new JButton("");
		   buttons[i].addActionListener(new buttonListener());
	   
		   p.add(buttons[i]);
	   }
//	   draw 
	   panel.add(p,BorderLayout.CENTER);
	   panel.add(t,BorderLayout.PAGE_START);
//	   add 
	   setContentPane(panel);
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setSize(600, 600);
	   setVisible(true);
   }
   
   public static void main(String[] args) {
	   new Main();
   }
   
   
   private class buttonListener implements ActionListener
   {
       @Override
       public void actionPerformed(ActionEvent e) {
    	   JButton b = (JButton) e.getSource();
    	   b.setFont(new Font("Monospaced", Font.ITALIC, 40 ));
    	   if(b.getText()=="") {    		   
    		   if(turn) {
    			   b.setText("x");
    			   turn=false;
    		   } else {
    			   b.setText("o");
    			   turn=true;
    		   }
    	   } else {
    		   label.setText("Click somewhere else!");
    	   }
    	   if (checkWinner()) {
    		   label.setText(winner + " has won!");
    		   disableButtons();
    	   } else if(filled()) {
    		   label.setText("It's a tie!");
    		   disableButtons();
    		   
    	   }
       }
    }
   public boolean checkWinner() {
	   if(same(0,1,2)||same(3,4,5)||same(6,7,8)||same(0,3,6)||same(1,4,7)||same(2,5,8)||same(0,4,8)||same(2,4,6)) return true;
	   return false;
   }
   public boolean same(int x, int y, int z) {
	   if(buttons[x].getText().equals(buttons[y].getText())&&buttons[y].getText().equals(buttons[z].getText())&&buttons[x].getText()!="") {
		   winner = buttons[x].getText();
		   return true;
	   }
	   return false;
   }
   public boolean filled() {
	   if(!checkWinner()) {
		   for(int i = 0; i < 9; ++i) {
			   if(buttons[i].getText()=="") return false;
		   }
	   }
	   return true;
   }
   public void disableButtons() {
	   Component[] com = p.getComponents();
	   for (int a = 0; a < com.length; a++) {
		   com[a].setEnabled(false);
	   }
   }
}
