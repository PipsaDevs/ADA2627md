import java.util.Collections;
import java.util.List;
import java.util.ArrayList;


public class SingleMachineProcesses {

    public static class Process {
        private int index;
        private int duration;

        public Process(int index, int duration) {
            this.index = index;
            this.duration = duration;
        }

        public int getIndex() {
            return index;
        }

        public int getDuration() {
            return duration;
        }

    }

    public static List<Integer> minimizeProcessesOrder(List<Integer> time) {
    
        List<Integer> jobOrder = new ArrayList<>();

        List<Integer> orderedTimes = new ArrayList<>();
        orderedTimes.addAll(time);

        Collections.sort(orderedTimes);

        for (int i : orderedTimes){
            int index = time.indexOf(i);
            time.set(index, -1);
            jobOrder.add(index);
        }

        return jobOrder;
    }

}