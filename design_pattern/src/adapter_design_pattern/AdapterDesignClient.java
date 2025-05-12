package adapter_design_pattern;

import
adapter_design_pattern.Adaptee.WeightMachineForBabies;
import
adapter_design_pattern.Adapter.WeightMachineAdapter;
import
adapter_design_pattern.Adapter.WeightMachineAdapterImpl;

public class AdapterDesignClient {

    public static void run() {

        System.out.println("AdapterDesignClient");

        WeightMachineAdapter weightMachineAdapter = new WeightMachineAdapterImpl(new WeightMachineForBabies());
        weightMachineAdapter.getWeightInKg();

    }
}
