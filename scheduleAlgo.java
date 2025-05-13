package proj;

public class scheduleAlgo {

	public static void main(String[] args) {
		int[][] schedule = {
				{2201, 3, 2, 1},
				{3401, 2, 3, 2},
				{1103, 1, 1, 3}
		};
		// schedule array structure : course code, duration, priority, arrival time
		
		double wait;
		double turnAround;

		fcfs(schedule); //algorithm call
		wait = calcWT(schedule); //waiting time for fcfs
		turnAround = calcTAT(schedule);
		System.out.println("FCFS Algorithm Output:");
		DisplayAlgo(wait,turnAround,schedule); //printing FCFS output
		
		prioritySched(schedule); //algorithm call
		wait = calcWT(schedule); //waiting time for ps
		turnAround = calcTAT(schedule); // turnaround time
		System.out.println("Priority Scheduling Output:");
		DisplayAlgo(wait,turnAround,schedule); //printing PriorityScheduling output
		
		
	}
	
	private static double calcTAT(int[][] schedule) {
		// average turnaround time calculation
		double total = 0.0, finish = 0.0;
		for(int i = 0; i < schedule.length; i++) {
			if(i==0) {
				finish += (schedule[i][1] + schedule[i][3]);
			} else {
				finish += schedule[i][1];
			}
			total += (finish - schedule[i][3]);
		}
		return total/schedule.length;
	}

	private static double calcWT(int[][] schedule) {
		// average waiting time calculation
		double total = 0.0, start = 0.0;
		for(int i = 0; i < schedule.length; i++) {
			if(i==0) {
				start += schedule[i][3];
			} else {
				start += schedule[i-1][1];
			}
			total += (start-schedule[i][3]);
		}
		return total/schedule.length;
	}

	private static void fcfs(int[][] schedule) {
		// first come first serve algorithm
		int[] temp;
		
		for(int i = 0; i < schedule.length; i++) {
			for(int j = 0; j< schedule.length - 1 ; j++) {
				if(schedule[j][3]>schedule[j+1][3]) {
					temp = schedule[j];
					schedule[j] = schedule[j+1];
					schedule[j+1] = temp;
				}
			}
		}
	}

	private static void prioritySched(int[][] schedule) {
		// Priority Scheduling Algorithm
		int[] temp;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j< schedule.length - 1 ; j++) {
				if(schedule[j][2]>schedule[j+1][2]) {
					temp = schedule[j];
					schedule[j] = schedule[j+1];
					schedule[j+1] = temp;
				}
			}
		}
	}
	private static void DisplayAlgo(double wait, double turnAround, int[][] schedule) {
		for(int i = 0; i < schedule.length; i++) {
			System.out.print("Course Code: " + schedule[i][0] + " ");
			System.out.print("Duration: " + schedule[i][1] + " ");
			System.out.print("Priority: " + schedule[i][2] + " ");
			System.out.print("Arrival Time: " + schedule[i][3]);
			System.out.println();
		}
		System.out.printf("Total average Waiting Time is: %.2f \n" , wait);
		System.out.printf("Total average TurnAround Time is: %.2f \n\n" , turnAround);

	}
}
