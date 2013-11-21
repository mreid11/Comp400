import java.io.IOException;
import java.util.ArrayList;
//1, 3, 4, 5 are good both ways
//Outputs shape for folding pathway
public class MakeShape {
	//Accepts one argument [1-5] to specify the Shape type
	public static void main(String[] args) throws IOException{
		String line=args[1];
		ArrayList<Integer> groups=new ArrayList<Integer>();
		String shape="";
		String returnShape="";
		int curGroup=0;
		int numToPop=0;
		char last='(';
		if (line.charAt(0)=='('){
			shape=shape+"[";
			curGroup=1;
		}
		for (int i=1; i<line.length(); i++){
			if(line.charAt(i-1)=='.' && line.charAt(i)=='('){
				shape=shape+"_";
				shape=shape+"[";
				curGroup=1;
				//System.out.println(last);
			} else if(line.charAt(i-1)=='.' && line.charAt(i)==')'){
				numToPop=1;
				if(last==')'){
					shape=shape+"_";
				}
				//Added
				if(i==line.length()-1) shape=shape+"]";
			} else if(line.charAt(i-1)=='(' && line.charAt(i)=='.'){
				groups.add(curGroup);
				curGroup=0;
				//System.out.println(last);

				last='(';
			}
			else if(line.charAt(i-1)=='(' && line.charAt(i)=='('){
				curGroup++;
				//System.out.println(last);

			}
			else if(line.charAt(i-1)==')' && line.charAt(i)==')'){
				numToPop++;
				//System.out.println(last);

			}
			else if(line.charAt(i-1)==')' && line.charAt(i)=='('){
				while(numToPop>0){
					if(groups.get(groups.size()-1)>numToPop){
						shape=shape+"]";
						groups.set(groups.size()-1, groups.get(groups.size()-1)-numToPop);
						numToPop=0;
						int found=0;
						int j=shape.length()-2;
						//while true continue. break when found=0 and [. increase found if find ].
						while(true){
							/*if (j<0){
								shape=shape.substring(0,j+1)+"["
										+shape.substring(j+1,shape.length());
								break;
							}*/
							if(shape.charAt(j)==']'){
								found++;
							}
							if(shape.charAt(j)=='['){
								if(found==0){
									shape=shape.substring(0,j+1)+"["
											+shape.substring(j+1,shape.length());
									break;
								} else{
									found--;
								}
							}
							j--;
						}
						//while(found){

						//}
					}
					else{

						int numRemoved=groups.remove(groups.size()-1);
						numToPop=numToPop-numRemoved;
						shape=shape+"]";

					}
				}
				numToPop=0;
				//System.out.println(last);

				last=')';
				//shape=shape+"]";
				shape=shape+"[";
				curGroup=1;
				//System.out.println(last);

			}
			else if(line.charAt(i-1)==')' && line.charAt(i)=='.'){
				while(numToPop>0){
					if(groups.get(groups.size()-1)>numToPop){
						shape=shape+"]";
						groups.set(groups.size()-1, groups.get(groups.size()-1)-numToPop);
						numToPop=0;
						int found=0;
						int j=shape.length()-2;
						//while true continue. break when found=0 and [. increase found if find ].
						while(true){
							/*if (j<0){
								shape=shape.substring(0,j+1)+"["
										+shape.substring(j+1,shape.length());
								break;
							}*/
							if(shape.charAt(j)==']'){
								found++;
							}
							if(shape.charAt(j)=='['){
								if(found==0){
									shape=shape.substring(0,j+1)+"["
											+shape.substring(j+1,shape.length());
									break;
								} else{
									found--;
								}
							}
							j--;
						}
						//while(found){

						//}
					}
					else{

						int numRemoved=groups.remove(groups.size()-1);
						numToPop=numToPop-numRemoved;
						shape=shape+"]";

					}
				}
				numToPop=0;
				//System.out.println(last);

				last=')';
			}
			if(i==line.length()-1 && line.charAt(i)=='.'){
				shape=shape+"_";
			}
		}

		switch(Integer.parseInt(args[0])){
		//Most accurate-all loops and all unpaired
		case 1:{
			returnShape=shape;
			break;
		} case 2:{
			if(shape.length()<2){
				break;
			}
			ArrayList<Integer> centers=new ArrayList<Integer>();
			for (int i=0; i<shape.length()-1; i++){
				if(shape.charAt(i) == '[' && shape.charAt(i+1)==']'){
					centers.add(i);
				}
			}
			for (int k=centers.size()-1; k>=0; k--){
				//Find left and right edges of external loop
				int i=centers.get(k)-1;
				int j=i+3;
				if(i<0 || j>=shape.length()) {
					if(i<0){
						if(shape.charAt(0)=='_'){
							shape=shape.substring(1,shape.length());
						}
					}
					if(j>-shape.length()){
						if(shape.charAt(shape.length()-1) == '_'){
							shape=shape.substring(0,shape.length()-1);
						}
					}
					continue;
				}
				char left=shape.charAt(i);
				char right=shape.charAt(j);
				while(left!=']' && right!='['){
					if(left=='_') i--;
					if(right=='_') j++;
					if(i<0 || j>=shape.length()) {
						if(i<0){
							if(shape.charAt(0)=='_'){
								shape=shape.substring(1,shape.length());
							}
						}
						if(j>-shape.length()){
							if(shape.charAt(shape.length()-1) == '_'){
								shape=shape.substring(0,shape.length()-1);
							}
						}
						break;
					}
					left=shape.charAt(i);
					right=shape.charAt(j);
					if(left==']'||right=='[') break;
					i--;
					j++;
					if(i<0 || j>=shape.length()) {
						if(i<0){
							if(shape.charAt(0)=='_'){
								shape=shape.substring(1,shape.length());
							}
						}
						if(j>-shape.length()){
							if(shape.charAt(shape.length()-1) == '_'){
								shape=shape.substring(0,shape.length()-1);
							}
						}
						break;
					}
					left=shape.charAt(i);
					right=shape.charAt(j);

				}
				if(left==']' && right=='_'){
					j++;
				}
				if(right=='[' && left=='_'){
					i--;
				}
				if((j-1)>=0 && j<shape.length()-1 && shape.charAt(j-1)=='_'){
					shape=shape.substring(0,j-1)+shape.substring(j,shape.length());
				}
				if((i+2)<shape.length()-1 && (i+1)>=0 && shape.charAt(i+1)=='_'){
					shape=shape.substring(0,i+1)+shape.substring(i+2,shape.length());
				}

			}
			if(centers.size()>0){
				if(shape.charAt(shape.length()-1)=='_') shape=shape.substring(0,shape.length()-1);
				if(shape.charAt(0)=='_') shape=shape.substring(1,shape.length());
			}
			returnShape=shape;

			break;
			//Nesting pattern for all loop types but no unpaired
			//regions
		}case 3:{
			for (int i=0; i<shape.length(); i++){
				if(shape.charAt(i)!='_') returnShape=returnShape+shape.charAt(i);
			}
			break;
		} case 4:{
			if(shape.length()<2){
				break;
			}
			ArrayList<Integer> centers=new ArrayList<Integer>();
			for (int i=0; i<shape.length()-1; i++){
				if(shape.charAt(i) == '[' && shape.charAt(i+1)==']'){
					centers.add(i);
				}
			}
			boolean[] centerKeep = new boolean [centers.size()];
			boolean keep=true;
			for(int k=0; k<centers.size(); k++){
				keep=true;
				int i=centers.get(k)-1;
				int j=i+3;
				//PROBLEM WITH INDEX FRONT OR BACK

				while((i>=0 && j<shape.length())){
					char left=shape.charAt(i);
					char right=shape.charAt(j);
					if(left == '[' && right==']'){
						i--;
						j++;
					} else if (left=='_' && right=='_'){
						i--;
						j++;
					}
					else if (left==']' || right=='['){
						i++;
						j--;
						break;
					}
					else if (left=='['&&right=='_'){
						j++;
						//Moving on to another internal loop, all even
						if(shape.charAt(j)=='['){
							i++;
							j--;
							break;
							//Uneven, will condense shape
						} else {
							keep=false;
							break;
						}
					}
					//Uneven, will condense shape
					else if (left=='_'&&right==']'){
						i--;
						//Moving on to another interval loop, all even
						if(shape.charAt(i)==']'){
							i++;
							j--;
							break;
						} else {
							keep=false;
							break;
						}
					}
					//Uneven, will condense shape
					else {
						keep=false;
						break;
					}
				}
				centerKeep[k]=keep;
			}
			//Gets the index of the new centers without the _ regions
			for (int i=shape.length()-1; i>=0; i--){
				if(shape.charAt(i)!='_') returnShape=shape.charAt(i)+returnShape;
				else{
					int k=centers.size()-1;
					while(k>=0 && centers.get(k)>i){
						centers.set(k,centers.get(k)-1);
						k--;
					}
				}
			}
			//Only condenses shapes if the loop is uneven
			for(int k=0; k<centers.size();k++){
				if(centerKeep[k]==false){
					int i=centers.get(k)-1;
					int j=i+3;
					while((i>=0 && j<returnShape.length())){
						if(returnShape.charAt(i) == '[' && returnShape.charAt(j)==']'){
							String first=returnShape.substring(0, i);
							String second=returnShape.substring(i+1,j);
							String third=returnShape.substring(j+1,returnShape.length());
							returnShape=first+second+third;
							i--;
							j--;
						} else {
							break;
						}
					}
				}
			}

			break;
		} case 5:{
			for (int i=0; i<shape.length(); i++){
				if(shape.charAt(i)!='_') returnShape=returnShape+shape.charAt(i);
			}
			if(returnShape.length()<2){
				break;
			}
			ArrayList<Integer> centers=new ArrayList<Integer>();
			for (int i=0; i<returnShape.length()-1; i++){
				if(returnShape.charAt(i) == '[' && returnShape.charAt(i+1)==']'){
					centers.add(i);
				}
			}
			while(!centers.isEmpty()){
				//PROBLEM WITH INDEX FRONT OR BACK
				int i=centers.get(centers.size()-1)-1;
				int j=i+3;
				while((i>=0 && j<returnShape.length())){
					if(returnShape.charAt(i) == '[' && returnShape.charAt(j)==']'){
						String first=returnShape.substring(0, i);
						String second=returnShape.substring(i+1,j);
						String third=returnShape.substring(j+1,returnShape.length());
						returnShape=first+second+third;
						i--;
						j--;
					} else {
						break;
					}
				}
				centers.remove(centers.size()-1);
			}
			break;
		}
		}
		System.out.println(returnShape);
	}
}
