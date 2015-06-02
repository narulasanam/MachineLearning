import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class DecisionTreeImpl {

	public static String[] listOfAttribute = { "K", "Na", "CL", "HCO3",
			"Endotoxin", "Aniongap", "PLA2", "SDH", "GLDH", "TPP",
			"Breath_rate", "PCV", "Pulse_rate", "Fibrinogen", "Dimer",
			"FibPerDim" };

	HashMap<String, Integer> mapping;
	ArrayList<Training> sampleStore;
	LinkedHashMap<String, Node> thresholdtable;
	static ArrayList<Double> K, Na, CL, HCO3, Endotoxin, Aniongap, PLA2, SDH,
			gLDH, TPP, Breath_rate, PCV, Pulse_rate, Fibrinogen, Dimer,
			FibPerDim;

	static LinkedHashSet<ArrayList<Double>> symptoms = new LinkedHashSet<ArrayList<Double>>();

	static LinkedHashSet<ArrayList<Double>> subSymptoms = new LinkedHashSet<ArrayList<Double>>();

	Node currentNode;

	DecisionTreeImpl() {
		sampleStore = new ArrayList<Training>();
		initializeSets();
		thresholdtable = new LinkedHashMap<String, Node>();
		currentNode = new Node();
		mapping = new HashMap<String, Integer>();
		mapTheNodes();

	}

	private void mapTheNodes() {
		mapping.put("K", 0);
		mapping.put("Na", 1);
		mapping.put("CL", 2);
		mapping.put("HCO3", 3);
		mapping.put("Endotoxin", 4);
		mapping.put("Aniongap", 5);
		mapping.put("PLA2", 6);
		mapping.put("SDH", 7);
		mapping.put("GLDH", 8);
		mapping.put("TPP", 9);
		mapping.put("Breath_rate", 10);
		mapping.put("PCV", 11);
		mapping.put("Pulse_rate", 12);
		mapping.put("Fibrinogen", 13);
		mapping.put("Dimer", 14);
		mapping.put("FibPerDim", 15);

	}

	private void initializeSets() {
		K = new ArrayList<Double>();
		Na = new ArrayList<Double>();
		CL = new ArrayList<Double>();
		HCO3 = new ArrayList<Double>();
		Endotoxin = new ArrayList<Double>();
		Aniongap = new ArrayList<Double>();
		PLA2 = new ArrayList<Double>();
		SDH = new ArrayList<Double>();
		gLDH = new ArrayList<Double>();
		TPP = new ArrayList<Double>();
		Breath_rate = new ArrayList<Double>();
		PCV = new ArrayList<Double>();
		Pulse_rate = new ArrayList<Double>();
		Fibrinogen = new ArrayList<Double>();
		Dimer = new ArrayList<Double>();
		FibPerDim = new ArrayList<Double>();
	}

	public void startLearning(String path) {
		try {
			readTrainingdata(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// After reading the data now calculate threshold.
		calculateThresholds(symptoms, sampleStore);
		Node k = new Node();
		k = chooseBestAttribute(thresholdtable);
		if (k != null)
			k.setRootNode(k);

		else
			System.out.println("No examples in there");

		startBuildingTree(k, sampleStore);

	}

	private void startBuildingTree(Node k2, ArrayList<Training> sampleStore) {
		double currentThreshold = k2.getThreshold();

		// now I will get the root of the tree in first step.

		setCurrentNode(k2);

		Training ob1 = new Training();
		for (int i = 0; i < sampleStore.size(); i++) {
			ob1 = (Training) sampleStore.get(i);
			double value = ob1.attributes.get(listOfAttribute[k2.position]);

			// here i have made two sets of training data

			if (value < currentThreshold) {
				k2.Leftdata.add(ob1);
			} else {

				k2.Rightdata.add(ob1);
			}

		}
		calculateThresholdForBranches(k2.Rightdata);

		Node kRight = new Node();
		kRight = chooseBestAttribute(thresholdtable);

		// k2.rightChild = kRight;

		// System.out.println("Kright is :" +kRight);
		if (kRight != null) {

			kRight.parent = k2;
			k2.rightChild = kRight;

		} else {
			System.out.println("********end***********");
			Training ob2 = new Training();
			ob2 = k2.Rightdata.get(0);
			boolean res = ob2.result;

			Node message = new Node();
			message.parent = getCurrentNode();
			if (res) {
				message.disease = "Healthy";
				System.out.println("Healthy" + " parent is :"
						+ message.parent.disease);

			} else {
				message.disease = "Colic";
				System.out.println("Colic" + " parent is :"
						+ message.parent.disease);

			}
			k2.setRightChild(message);

			/*
			 * Node message = new Node(); message.disease = message.parent =
			 * getCurrentNode(); k2.setRightChild(message);
			 */
		}
		calculateThresholdForBranches(k2.Leftdata);

		Node kLeft = new Node();
		kLeft = chooseBestAttribute(thresholdtable);

		if (kLeft != null) {
			kLeft.parent = k2;
			k2.leftChild = kLeft;
		} else {
			System.out.println("********end***********");
			Training ob2 = new Training();
			ob2 = k2.Leftdata.get(0);
			boolean res = ob2.result;

			Node message = new Node();
			message.parent = getCurrentNode();
			if (res) {

				message.disease = "Healthy";
				System.out.println("Healthy" + " parent is :"
						+ message.parent.disease);
			} else {

				message.disease = "Colic";
				System.out.println("Colic" + " parent is :"
						+ message.parent.disease);
			}

			k2.setLeftChild(message);
			/*
			 * Node message = new Node(); message.disease = message.parent =
			 * getCurrentNode(); k2.setLeftChild(message);
			 */
		}
		// System.out.println("kLeft is :" +kLeft);

		if (kRight != null) {
			System.out.println("right is :" + kRight.disease + " parent is :"
					+ kRight.parent.disease);
			startBuildingTree(kRight, k2.Rightdata);
		}

		if (kLeft != null) {
			System.out.println("Left is :" + kLeft.disease + " parent is : "
					+ kLeft.parent.disease);
			startBuildingTree(kLeft, k2.Leftdata);
		}

	}

	private void calculateThresholdForBranches(ArrayList<Training> data) {
		// I am doing it for every value right now
		// clearing the old values,,,,,,,
		subSymptoms.clear();
		thresholdtable.clear();

		Training ob = new Training();
		for (int j = 0; j < data.size(); j++) {
			ob = data.get(j);
			Sample sample = new Sample(ob);
		}

		calculateThresholds(subSymptoms, data);
	}

	public void readTrainingdata(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String sCurrentline;
		String arrs[];
		while ((sCurrentline = br.readLine()) != null) {
			arrs = sCurrentline.split(",");
			Training obName = new Training(parseData(arrs));
			sampleStore.add(obName);
		}
		System.out.println(sampleStore.size());
	}

	private double[] parseData(String[] arrs) {
		double[] arr1 = new double[arrs.length];
		for (int i = 0; i < arrs.length; i++) {
			if (i < arrs.length - 1)
				arr1[i] = Double.parseDouble(arrs[i]);
			else {
				if (arrs[i].contains("colic"))
					arr1[i] = 0;
				else
					arr1[i] = 1;
			}

		}
		return arr1;
	}

	/*
	 * This function will calculate the thresholds for attributes so that they
	 * can be divided in two sets for calculating thresholds
	 */

	private void calculateThresholds(LinkedHashSet<ArrayList<Double>> symptoms,
			ArrayList<Training> Store) {

		double infoGain = 0, temp1 = 0;
		int totalNumP = 0, totalNumN = 0;

		// for calculating threshold we should check information gain value for
		// each.
		// 1. I am taking iteration over the symptoms
		// 2. I will take each treeset and then iterate whole 132 values
		// with each value.
		Iterator<ArrayList<Double>> it = symptoms.iterator();
		int counter = 0;
		while (it.hasNext()) {
			infoGain = 0;
			ArrayList<Double> ob = new ArrayList<Double>();
			ob = it.next();

			Iterator<Double> t = ob.iterator();
			while (t.hasNext() && counter < 16) {
				int numOfPForE1 = 0, numOfPForE2 = 0, numOfNForE1 = 0, numOfNForE2 = 0;
				// now take each value and see its information gain.
				double temp = t.next();
				Training ob1 = new Training();

				// I am taking one value from set and then comparing it with all
				// 132 values

				for (int i = 0; i < Store.size(); i++) {
					ob1 = (Training) Store.get(i);
					// This one is tricky I am getting all the values from
					// sample store (132)
					// for that i am relying on counter and training object
					double value = ob1.attributes.get(listOfAttribute[counter]);
					if (value >= temp) {
						if (ob1.result)
							numOfPForE1++;
						else
							numOfNForE1++;
					} else if (ob1.result)
						numOfPForE2++;
					else
						numOfNForE2++;
				}

				int totalElementForE1 = numOfPForE1 + numOfNForE1;
				int totalElementForE2 = numOfPForE2 + numOfNForE2;

				int totalNumOfP = numOfPForE1 + numOfPForE2;
				int totalNumOfN = numOfNForE1 + numOfNForE2;

				// now calculate information gain
				double E1 = calculateEntropy(numOfPForE1, numOfNForE1);
				double E2 = calculateEntropy(numOfPForE2, numOfNForE2);
				double E = calculateEntropy(totalNumOfP, totalNumOfN);

				double tempInfoGain = E
						- (((float) totalElementForE1 / 132) * E1 + ((float) totalElementForE2 / 132)
								* E2);

				if (tempInfoGain > infoGain) {
					infoGain = tempInfoGain;
					temp1 = temp;
				}
				totalNumP = totalNumOfP;
				totalNumN = totalNumOfN;

			}
			if (counter < 16) {
				Node obj = new Node(listOfAttribute[counter], infoGain, temp1,
						counter, totalNumP, totalNumN);
				thresholdtable.put(listOfAttribute[counter], obj);
				totalNumN = -1;
				totalNumP = -1;
			} else {
				// System.out.println("counter is greater than 16 so return");
				return;
			}
			counter++;

		}
	}

	private double calculateEntropy(double numOfP, double numOfN) {
		double factor1 = 0, factor2 = 0;
		if (numOfP == 0)
			factor1 = 0;
		else
			factor1 = Math.log10(numOfP / (numOfN + numOfP)) / Math.log10(2);

		if (numOfN == 0)
			factor2 = 0;
		else
			factor2 = ((Math.log10(numOfN / (numOfN + numOfP))) / Math.log10(2));

		if (numOfP == 0 && numOfN == 0)
			return 0;

		double first = -1 * (numOfP / (numOfN + numOfP)) * factor1;
		double second = -1 * (numOfN / (numOfN + numOfP)) * factor2;
		return (first + second);
	}

	public Node chooseBestAttribute(HashMap<String, Node> table) {
		//
		double best = 0;
		int index = 0;
		for (int i = 0; i < table.size(); i++) {

			if (best < table.get(listOfAttribute[i]).infoGain) {
				best = table.get(listOfAttribute[i]).infoGain;
				index = i;
			}
			/*
			 * System.out.println(table.get(listOfAttribute[i]).disease + "," +
			 * table.get(listOfAttribute[i]).infoGain);
			 */

		}

		if (index == -1 || best == 0) {

			return null;

		} else {
			// lookforcurrentnode index is not updating sanam
			if (table.get(listOfAttribute[index]).numOfP == 0) {
				System.out.println(getCurrentNode().disease);
				return null;
			} else if (table.get(listOfAttribute[index]).numOfN == 0) {
				System.out.println(getCurrentNode().disease);
				return null;
			} else {
				System.out.print("best is : " + best);
				System.out.print(" label is : "
						+ table.get(listOfAttribute[index]).disease);
				System.out.println(" threshold is : "
						+ table.get(listOfAttribute[index]).threshold);

				// System.out.println(symptoms.size());
				return table.get(listOfAttribute[index]);
			}
		}
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public void printTree(Node root) {

		// printing mistake
		Node t = new Node();
		t = root;
		System.out.println("Node is :" + t.disease);
		System.out.println(t.disease + " leftChild is :" + t.leftChild.disease);
		System.out.println(t.disease + " rightChild is :"
				+ t.rightChild.disease);
		printTree(t.getRightChild());
		// gldh print karwado......................

	}

	public void TestTree(String string) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(string));
		String sCurrentline;
		String arrs[];
		
		int hit = 0, miss = 0;
		while ((sCurrentline = br.readLine()) != null) {
			arrs = sCurrentline.split(",");
			Node root = new Node();
			root = root.rootNode;
			while (root != null) {
				String name = root.disease;
				int k = mapping.get(name);
				if (Double.parseDouble(arrs[k]) >= root.threshold) {
					if (root.rightChild != null)
						root = root.rightChild;
				} else {
					if (root.leftChild != null)
						root = root.leftChild;
				}
				if (root.disease.equalsIgnoreCase("colic")
						|| root.disease.equalsIgnoreCase("healthy")) {
					if (arrs[16].equalsIgnoreCase("colic.") && root.disease
							.equalsIgnoreCase("colic")
							|| (arrs[16].equalsIgnoreCase("healthy.") && root.disease
									.equalsIgnoreCase("healthy"))){
					
						hit++;
						break;
					}
					else{
						miss++;
						break;
					}

				}

			}
		}
		System.out.println("hits :" + hit);
		System.out.println("Miss :" + miss);

	}

}
