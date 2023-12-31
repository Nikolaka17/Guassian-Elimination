import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Elimination {
	public static JFrame frame;
	public static JButton confirm;
	public static JTextField n;
	public static JTextField[][] numbers;
  
	public static void main(String[] args) {
		frame = new JFrame();
		confirm = new JButton("Convert");
		n = new JTextField("3");
		numbers = new JTextField[3][4];
		for(int i = 0; i < numbers.length; i++){
			for(int j = 0; j < numbers[i].length; j++){
				numbers[i][j] = new JTextField("1");
			}
		}
		  
		confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				try{
					Equation[] input = new Equation[Integer.parseInt(n.getText())];
					for(int i = 0; i < input.length; i++){
						double[] coefs = new double[input.length];
						for(int j = 0; j < coefs.length; j++){
							coefs[j] = Double.parseDouble(numbers[i][j].getText());
						}
						input[i] = new Equation(coefs, Double.parseDouble(numbers[i][input.length].getText()));
					}
					Equation[] output = algorithm(input);
					StringBuilder sb = new StringBuilder();
					for(Equation eq: output){
						  sb.append(eq.toString() + "\n");
					}
					JOptionPane.showMessageDialog(frame, sb.toString(), "Result", JOptionPane.INFORMATION_MESSAGE);
				}catch(Exception e){
					JOptionPane.showMessageDialog(frame, "Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		n.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent de){
				try{
					if(Integer.parseInt(n.getText()) <= 0){
						throw new IllegalArgumentException();
					}
					setup(Integer.parseInt(n.getText()));
				}catch(Exception e){
					JOptionPane.showMessageDialog(frame, "Something went wrong with the ammount of equations", "Error", JOptionPane.ERROR_MESSAGE);
					n.setText("3");
					setup(3);
				}
			}
			
			@Override
			public void removeUpdate(DocumentEvent de){
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent de){
				
			}
		});
		  
		setup(3);
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void setup(int num){
		frame = new JFrame();
		numbers = new JTextField[num][num + 1];
		for(int i = 0; i < numbers.length; i++){
			for(int j = 0; j < numbers[i].length; j++){
				numbers[i][j] = new JTextField("1");
			}
		}
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		frame.add(new JLabel("How many equations are there?"));
		frame.add(n);
		for(int i = 0; i < numbers.length; i++){
			JPanel temp = new JPanel();
			for(int j = 0; j < numbers[i].length - 1; j++){
				temp.add(numbers[i][j]);
				if(j != numbers[i].length - 2){
					temp.add(new JLabel("x" + (j+1) + " + "));
				}else{
					temp.add(new JLabel("x" + (j + 1)));
				}
			}
			temp.add(new JLabel(" = "));
			temp.add(numbers[i][numbers[i].length - 1]);
			frame.add(temp);
		}
		frame.add(confirm);
	}
	
	public static Equation[] algorithm(Equation[] initial){
		Equation[] out = initial;
		for(int i = 0; i < out.length; i++){
			if(out[i].getCoefficients()[i] != 0){
				out[i] = out[i].divide(out[i].getCoefficients()[i]);
			}
			for(int j = i + 1; j < out.length; j++){
				out[j] = out[j].add(out[i].multiply(-out[j].getCoefficients()[i]));
			}
		}
		return out;
	}
}
