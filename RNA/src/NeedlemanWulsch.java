
public class NeedlemanWulsch {
	public static void main(String[] args){
		String first=args[0];
		String second=args[1];
		if(first.length()==0 || second.length()==0){
			String[] matching=new String[2];
			if(first.length()==0 && second.length()==0){
				matching[0]="";
				matching[1]="";
				System.out.println("Score: 0");
			}
			else if(first.length()==0){
				matching[0]="";
				matching[1]=second;
				for(int i=0; i<second.length(); i++){
					matching[0]="_"+matching[0];
				}
				System.out.println("Score: " + second.length());
			}
			else{
				matching[0]=first;
				matching[1]="";
				for(int i=0; i<first.length(); i++){
					matching[1]="_"+matching[1];
				}
				System.out.println("Score: "+first.length());
			}
			System.out.println(matching[0]);
			System.out.println(matching[1]);
		}
		//DOESN'T DEAL WITH EMPTY STRINGS
		else{
			first=" "+first;
			second=" "+second;
			int[][] distance=makeDistanceMatrix(first, second);
			System.out.println("Score: "+distance[first.length()-1][second.length()-1]);
			//printDistanceMatrix(first, second, distance);
			String[] matching=getPath(first, second, distance);
			System.out.println(matching[0]);
			System.out.println(matching[1]);
		}
	}

	private static String[] getPath(String first, String second, int[][] distance){
		String[] matching={"",""};
		int i=first.length()-1;
		int j=second.length()-1;
		while(i>0 && j>0){
			//diagonal
			if(distance[i-1][j-1] <= distance[i-1][j] && distance[i-1][j-1]<=distance[i][j-1]){
				matching[0]=first.charAt(i)+matching[0];
				matching[1]=second.charAt(j)+matching[1];
				i--;
				j--;
			}
			//up
			else if(distance[i-1][j] <= distance[i-1][j-1] && distance[i-1][j]<=distance[i][j-1]){
				matching[0]=first.charAt(i)+matching[0];
				matching[1]="_"+matching[1];
				i--;
				//left
			} else{
				matching[0]="_"+matching[0];
				matching[1]=second.charAt(j)+matching[1];
				j--;
			}
		}
		while(j>0){
			matching[0]="_"+matching[0];
			matching[1]=second.charAt(j)+matching[1];
			j--;
		}
		while(i>0){
			matching[0]=first.charAt(i)+matching[0];
			matching[1]="_"+matching[1];
			i--;
		}
		return matching;
	}

	private static int[][] makeDistanceMatrix(String first, String second){
		int gapWeight=1;
		int[][] distance=new int[first.length()][second.length()];
		distance[0][0]=0;
		for(int i=1; i<first.length(); i++){
			distance[i][0]=distance[i-1][0]+gapWeight;
		}
		for(int j=1; j<second.length(); j++){
			distance[0][j]=distance[0][j-1]+gapWeight;
		}
		for(int i=1; i<first.length(); i++){
			for(int j=1; j<second.length(); j++){
				distance[i][j]=Math.min(Math.min(distance[i-1][j-1]+
						match(first.charAt(i),second.charAt(j)), distance[i-1][j]+gapWeight),
						distance[i][j-1]+gapWeight);
			}
		}
		return distance;
	}

	private static int match(char one, char two){
		if(one==two) return 0;
		else return 1;
	}

	private static void printDistanceMatrix(String first, String second, int[][] distance){
		for (int i=0; i<first.length(); i++){
			for (int j=0; j<second.length(); j++){
				System.out.print(distance[i][j]+" ");
			}
			System.out.println();
		}
	}
}
