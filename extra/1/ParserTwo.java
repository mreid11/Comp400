import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ParserTwo {
	public static void main(String[] args) throws IOException{
		Scanner in=new Scanner(new File(args[0]));
		String line=in.nextLine();
		boolean reachedEnd=false;
		boolean direction=true;
		boolean reachedBeginning=false;
		int cursor=0;
		int numToChange=0;
		ArrayList<String> asArray=new ArrayList<String>();
		for (int i=0; i<line.length();i++){
			asArray.add(""+line.charAt(i));
		}
		while(reachedEnd==false && reachedBeginning==false){
			while(asArray.get(cursor).equals(")") && !reachedEnd){
				asArray.remove(cursor);
				numToChange++;
				if(cursor==asArray.size()-1){
					direction=false;
					reachedEnd=true;
					asArray.set(cursor, "]");
				} else{
					if(!asArray.get(cursor).equals(")")){
						asArray.add(cursor, "]");
						direction=false;
					}
				}
			}
			while(asArray.get(cursor).equals("(") && numToChange>0){
				if(numToChange==1){
					asArray.set(cursor, "[");
					direction=true;
					numToChange--;
				} else{
					asArray.remove(cursor);
					cursor--;
					numToChange--;
				}
				if(reachedEnd==true){
					reachedBeginning=true;
				}
				if(asArray.get(cursor).equals("(") && numToChange>0){
					asArray.add(cursor+1,"[");
					while(!asArray.get(cursor).equals("(")){
						cursor--;
					}
				}
			}

			if(cursor==asArray.size()-1){
				reachedEnd=true;
				reachedBeginning=true;
			}
			if(direction==true){
				cursor++;
			}else{
				cursor--;
			}
		}

		for(int i=0; i<asArray.size(); i++){
			System.out.print(asArray.get(i));
		}
		in.close();
	}
}
