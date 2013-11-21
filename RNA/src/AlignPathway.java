import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;


public class AlignPathway {
	String[] firstPath;
	String[] secondPath;
	public AlignPathway(String[] first, String[] second) throws IOException{
		this.firstPath=first;
		this.secondPath=second;
		align(firstPath, secondPath);
	}
	public void align(String[] first, String[] second) throws IOException{
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("FinalAlignment.txt", true)));
		if(first.length==0 || second.length==0){
			//Make sure it is at least 1 so that you can put empty string
			String[][] matching=new String[2][Math.max(1, Math.max(first.length, second.length))];
			for(int i=0; i<Math.max(first.length, second.length); i++){
				matching[0][i]="";
				matching[1][i]="";
			}
			if(first.length==0 && second.length==0){
				matching[0][0]="";
				matching[1][0]="";
				out.println("Score: 0");
			}
			else if(first.length==0){
				matching[0][0]="";
				matching[1]=second;
				for(int i=0; i<second.length; i++){
					matching[0][i]="*"+matching[0][i];
				}
				out.println("Score: " + second.length);
			}
			else{
				matching[0]=first;
				matching[1][0]="";
				for(int i=0; i<first.length; i++){
					matching[1][i]="*"+matching[1][i];
				}
				out.println("Score: "+first.length);
			}
			for(int i=0; i<matching[0].length;i++){
				out.print(matching[0][i]+" ");
			}
			out.println();
			for(int i=0; i<matching[0].length;i++){
				out.print(matching[1][i]+" ");
			}
			
		}
		
		//DOESN'T DEAL WITH EMPTY STRINGS
		else{
			this.firstPath=new String[first.length+1];
			this.secondPath=new String[second.length+1];
			this.firstPath[0]=" ";
			for(int i=0; i<first.length; i++){
				this.firstPath[i+1]=first[i];
			}
			this.secondPath[0]=" ";
			for(int i=0; i<second.length; i++){
				this.secondPath[i+1]=second[i];
			}
			int[][] distance=makeDistanceMatrix(this.firstPath, this.secondPath);
			out.println("Score: "+distance[first.length-1][second.length-1]);
			//printDistanceMatrix(this.firstPath, this.secondPath, distance);
			ArrayList<ArrayList<String>> matching=getPath(this.firstPath, this.secondPath, distance);
			for(int i=0; i<matching.get(0).size(); i++){
				out.print(matching.get(0).get(i)+" ");
			}
			out.println();
			for(int i=0; i<matching.get(1).size(); i++){
				out.print(matching.get(1).get(i)+" ");
			}
			//System.out.println(matching[0]);
			//System.out.println(matching[1]);
		}
		out.close();
	}

	private static ArrayList<ArrayList<String>> getPath(String first[], String second[], int[][] distance){
		ArrayList<String> matchingFirst=new ArrayList<String>();
		ArrayList<String> matchingSecond=new ArrayList<String>();
		ArrayList<ArrayList<String>> matching=new ArrayList<ArrayList<String>>();
		matching.add(matchingFirst);
		matching.add(matchingSecond);
		int i=first.length-1;
		int j=second.length-1;
		while(i>0 && j>0){
			//diagonal
			if(distance[i-1][j-1] <= distance[i-1][j] && distance[i-1][j-1]<=distance[i][j-1]){
				matchingFirst.add(0, first[i]);
				matchingSecond.add(0,second[j]);
				i--;
				j--;
			}
			//up
			else if(distance[i-1][j] <= distance[i-1][j-1] && distance[i-1][j]<=distance[i][j-1]){
				matchingFirst.add(0, first[i]);
				matchingSecond.add(0,"*");
				i--;
				//left
			} else{
				matchingFirst.add(0,"*");
				matchingSecond.add(0,second[j]);
				j--;
			}
		}
		while(j>0){
			matchingFirst.add(0,"*");
			matchingSecond.add(0,second[j]);
			j--;
		}
		while(i>0){
			matchingFirst.add(0,first[i]);
			matchingSecond.add(0,"*");
			i--;
		}
		return matching;
	}

	private static int[][] makeDistanceMatrix(String[] first, String[] second){
		int gapWeight=1;
		int[][] distance=new int[first.length][second.length];
		distance[0][0]=0;
		for(int i=1; i<first.length; i++){
			distance[i][0]=distance[i-1][0]+gapWeight;
		}
		for(int j=1; j<second.length; j++){
			distance[0][j]=distance[0][j-1]+gapWeight;
		}
		for(int i=1; i<first.length; i++){
			for(int j=1; j<second.length; j++){
				distance[i][j]=Math.min(Math.min(distance[i-1][j-1]+
						match(first[i],second[j]), distance[i-1][j]+gapWeight),
						distance[i][j-1]+gapWeight);
			}
		}
		return distance;
	}

	private static int match(String one, String two){
		if(one.equals(two)) return 0;
		else return 1;
	}

	private static void printDistanceMatrix(String[] first, String[] second, int[][] distance){
		for (int i=0; i<first.length; i++){
			for (int j=0; j<second.length; j++){
				System.out.print(distance[i][j]+" ");
			}
			System.out.println();
		}
	}
}
