import java.io.IOException;


public class DecisionTreeMain {

	public static void main(String[] args) {
		DecisionTreeImpl ob = new DecisionTreeImpl();
		Node t = new Node();
		ob.startLearning("horseTrain.txt");
		try {
			ob.TestTree("horseTest.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
