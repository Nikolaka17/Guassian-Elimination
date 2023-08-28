import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.ActionListener;
import javax.swing.event.ActionEvent;

public class Elimination{
	
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
		public void actionPerformed(ActionEvent e){
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
		public void changedUpdate(DocumentEvent e){
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
		public void removeUpdate(DocumentEvent e){
			
		}
		
		@Override
		public void insertUpdate(DocumentEvent e){
			
		}
	});
	
    frame.setSize(300, 300);
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
	frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
	frame.add(new JLabel("How many equations are there?"));
	frame.add(numbers);
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
	  
  }
}
