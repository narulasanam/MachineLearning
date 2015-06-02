import java.util.ArrayList;

public class Node {

	static Node rootNode;
	int position;
	String disease;
	double infoGain;
	double threshold;
	Node parent;
	Node leftChild;
	Node rightChild;
	public ArrayList<Training> Leftdata;
	public ArrayList<Training> Rightdata;
    int numOfP,numOfN;

	public Node() {
		//this(null,0,0,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE);
		}

	public Node(String d, double i, double t,int position,int totalnumofp, int totalnumofN) {

		this.disease = d;
		this.infoGain = i;
		this.threshold = t;
		this.position = position;
		parent = null;
		leftChild = null;
		rightChild =null;
		Leftdata = new ArrayList<Training>();
		Rightdata = new ArrayList<Training>();
		numOfP = totalnumofp;
		numOfN = totalnumofN;
	}

	public Node getRootNode() {
		return rootNode;
	}


	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public double getInfoGain() {
		return infoGain;
	}

	public void setInfoGain(double infoGain) {
		this.infoGain = infoGain;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	public int getNumOfP() {
		return numOfP;
	}

	public void setNumOfP(int numOfP) {
		this.numOfP = numOfP;
	}

	public int getNumOfN() {
		return numOfN;
	}

	public void setNumOfN(int numOfN) {
		this.numOfN = numOfN;
	}

	public ArrayList<Training> getLeftdata() {
		return Leftdata;
	}

	public void setLeftdata(ArrayList<Training> leftdata) {
		Leftdata = leftdata;
	}

	public ArrayList<Training> getRightdata() {
		return Rightdata;
	}

	public void setRightdata(ArrayList<Training> rightdata) {
		Rightdata = rightdata;
	}

}
