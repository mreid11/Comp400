import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class TwoPathwaysAlign {
	public static void main(String[] args) throws IOException{
		Scanner in=new Scanner(new File("out.txt"));
		Scanner in2=new Scanner(new File("out2.txt"));
		ArrayList<String> list=new ArrayList<String>();
		String line="";
		String[] first, second;
		int limit=10;
		int i=0;
		while(in.hasNext() && i<=limit){
			line=in.next();
			//if(line.equals("O")) break;
			list.add(line);
			//in.next();
			//in.next();
			i++;
		}
		first=new String[list.size()];
		for(int j=0; j<list.size(); j++){
			first[j]=list.get(j);
//			System.out.println(first[j]);
		}
//		System.out.print("\n\n\n\n\n\n\n");
		i=0;
		list=new ArrayList<String>();
		while(in2.hasNext() && i<=limit){
			line=in2.next();
			//if(line.equals("O")) break;
			list.add(line);
			//in2.next();
			//in2.next();
			i++;
		}
		second=new String[list.size()];
		for(int j=0; j<list.size(); j++){
			second[j]=list.get(j);
		}
		AlignPathway align=new AlignPathway(first,second);
		in.close();
		in2.close();
	}
}
