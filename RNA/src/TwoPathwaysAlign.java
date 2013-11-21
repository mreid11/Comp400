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
		int limit=Integer.MAX_VALUE;
		int i=0;
		String previous="";
		if(in.hasNext()) previous=in.nextLine();
		list.add(previous);
		//in.next(); in.next();
		while(in.hasNext() && i<=limit){
			line=in.nextLine();
			//if(line.equals("O")) break;
			if(!line.equals(previous)){
				list.add(line);
			}
			//in.next();
			//in.next();
			previous=line;
			i++;
		}
		first=new String[list.size()];
		for(int j=0; j<list.size(); j++){
			first[j]=list.get(j);
		}
		i=0;
		list=new ArrayList<String>();
		if(in.hasNext()) previous=in.nextLine();
		list.add(previous);
		//in.next(); in.next();
		while(in2.hasNext() && i<=limit){
			line=in2.nextLine();
			//if(line.equals("O")) break;
			if(!line.equals(previous)) list.add(line);
			//in2.next();
			//in2.next();
			previous=line;
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
