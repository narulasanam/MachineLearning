import java.util.LinkedHashMap;


class Training {

	// true for healthy and false for colic
	boolean result;
	LinkedHashMap<String, Double> attributes;

	Training() {
	}

	public Training(double att[]) {
		result = att[16] == 1;
		attributes = new LinkedHashMap<>();

		// put the values in a set. So that it will be helpful in calculating
		// threshold
		// but i also need all values to be present somewhere.
		DecisionTreeImpl.K.add(att[0]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.K);

		DecisionTreeImpl.Na.add(att[1]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.Na);

		DecisionTreeImpl.CL.add(att[2]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.CL);

		DecisionTreeImpl.HCO3.add(att[3]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.HCO3);

		DecisionTreeImpl.Endotoxin.add(att[4]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.Endotoxin);

		DecisionTreeImpl.Aniongap.add(att[5]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.Aniongap);

		DecisionTreeImpl.PLA2.add(att[6]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.PLA2);

		
		DecisionTreeImpl.SDH.add(att[7]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.SDH);

		
		DecisionTreeImpl.gLDH.add(att[8]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.gLDH);

		
		DecisionTreeImpl.TPP.add(att[9]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.TPP);

		DecisionTreeImpl.Breath_rate.add(att[10]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.Breath_rate);

		DecisionTreeImpl.PCV.add(att[11]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.PCV);

		DecisionTreeImpl.Pulse_rate.add(att[12]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.Pulse_rate);

		DecisionTreeImpl.Fibrinogen.add(att[13]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.Fibrinogen);

		DecisionTreeImpl.Dimer.add(att[14]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.Dimer);


		DecisionTreeImpl.FibPerDim.add(att[15]);
		DecisionTreeImpl.symptoms.add(DecisionTreeImpl.FibPerDim);

		for (int i = 0; i < att.length - 1; i++) {
			attributes.put(DecisionTreeImpl.listOfAttribute[i], att[i]);
		}
	}

}