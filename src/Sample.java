import java.util.ArrayList;

public class Sample {
	static ArrayList<Double> K = new ArrayList<Double>(),
			Na = new ArrayList<Double>(), CL = new ArrayList<Double>(),
			HCO3 = new ArrayList<Double>(), Endotoxin = new ArrayList<Double>(),
			Aniongap = new ArrayList<Double>(), PLA2 = new ArrayList<Double>(),
			SDH = new ArrayList<Double>(), gLDH = new ArrayList<Double>(),
			TPP = new ArrayList<Double>(), Breath_rate = new ArrayList<Double>(),
			PCV = new ArrayList<Double>(), Pulse_rate = new ArrayList<Double>(),
			Fibrinogen = new ArrayList<Double>(), Dimer = new ArrayList<Double>(),
			FibPerDim = new ArrayList<Double>();
	boolean result;

	public Sample(Training ob) {
		result = ob.result;	
		
		K.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[0]));
		DecisionTreeImpl.subSymptoms.add(K);

		Na.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[1]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.Na);

		CL.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[2]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.CL);

		HCO3.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[3]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.HCO3);

		Endotoxin.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[4]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.Endotoxin);

		Aniongap.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[5]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.Aniongap);

		PLA2.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[6]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.PLA2);

		
		SDH.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[7]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.SDH);

		
		gLDH.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[8]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.gLDH);

		
		TPP.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[9]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.TPP);

		Breath_rate.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[10]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.Breath_rate);

		PCV.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[11]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.PCV);

		Pulse_rate.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[12]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.Pulse_rate);

		Fibrinogen.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[13]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.Fibrinogen);

		Dimer.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[14]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.Dimer);


		FibPerDim.add(ob.attributes.get(DecisionTreeImpl.listOfAttribute[15]));
		DecisionTreeImpl.subSymptoms.add(DecisionTreeImpl.FibPerDim);

	}
}
