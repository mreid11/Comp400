import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
//1 & 3 are good both ways
//Outputs shape for folding pathway
public class PathwayParser2 {
	//Accepts one argument [1-5] to specify the Shape type
	public static void main(String[] args) throws IOException{
		Scanner in=new Scanner(new File(args[1]));
		String line=in.nextLine();
		ArrayList<Integer> groups=new ArrayList<Integer>();
		String shape="";
		int curGroup=0;
		int numToPop=0;
		while (in.hasNext()){
			line=in.nextLine();
		}
		switch(Integer.parseInt(args[0])){
		//Most accurate-all loops and all unpaired
		case 1:{
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
			break;
		} case 2:{
			char last='.';
			if (line.charAt(0)=='('){
				shape=shape+"[";
				curGroup=1;
			}
			for (int i=1; i<line.length(); i++){
				if(line.charAt(i-1)=='.' && line.charAt(i)=='('){
					System.out.println(last+" ENTERED1 "+groups.size());
					if (last=='(' && groups.size()>1){
						shape=shape+"_";
					}
					shape=shape+"[";
					curGroup=1;
					last='(';
					//System.out.println(last);
				} else if(line.charAt(i-1)=='.' && line.charAt(i)==')'){
					numToPop=1;
					System.out.println("ENTERED2");
					if(last==')' && groups.size()>1){
						shape=shape+"_";
					}
					last=')';
				} else if(line.charAt(i-1)=='(' && line.charAt(i)=='.'){
					groups.add(curGroup);
					curGroup=0;
					//System.out.println(last);

					last='(';
				}
				else if(line.charAt(i-1)=='(' && line.charAt(i)=='('){
					curGroup++;
					//System.out.println(last);
					last='(';
				}
				else if(line.charAt(i-1)==')' && line.charAt(i)==')'){
					numToPop++;
					//System.out.println(last);
					last=')';
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
					System.out.println(last);
				}
			}
		} 
		break;
		//Nesting pattern for all loop types but no unpaired
		//regions
		case 3:{
			if (line.charAt(0)=='('){
				shape=shape+"[";
				curGroup=1;
			}
			for (int i=1; i<line.length(); i++){
				if(line.charAt(i-1)=='.' && line.charAt(i)=='('){
					shape=shape+"[";
					curGroup=1;
				} else if(line.charAt(i-1)=='.' && line.charAt(i)==')'){
					numToPop=1;
				} else if(line.charAt(i-1)=='(' && line.charAt(i)=='.'){
					groups.add(curGroup);
					curGroup=0;
				}
				else if(line.charAt(i-1)=='(' && line.charAt(i)=='('){
					curGroup++;
					//System.out.println("CUR"+curGroup);
				}
				else if(line.charAt(i-1)==')' && line.charAt(i)==')' && i!=line.length()-1){
					numToPop++;
					//System.out.println("POP"+numToPop);
				}
				else if((line.charAt(i-1)==')' && line.charAt(i)=='.') || (line.charAt(i)==')' && i==line.length()-1)){
					if(i==line.length()-1) numToPop++;
					//System.out.println("CUR"+curGroup+" POP "+numToPop);
					while(numToPop>0){
						if(groups.get(groups.size()-1)>numToPop){
							shape=shape+"]";
							groups.set(groups.size()-1, groups.get(groups.size()-1)-numToPop);
							numToPop=0;
							int found=0;
							int j=shape.length()-2;
							//while true continue. break when found=0 and [. increase found if find ].
							while(true){
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
						} else {
							int numRemoved=groups.remove(groups.size()-1);
							numToPop=numToPop-numRemoved;
							shape=shape+"]";
						}
					}
					numToPop=0;
				}
			}
			break;
		} case 4:{
			if (line.charAt(0)=='('){
				shape=shape+"[";
				curGroup=1;
			}
			for (int i=1; i<line.length(); i++){
				if(line.charAt(i-1)=='.' && line.charAt(i)=='('){
					shape=shape+"[";
					curGroup=1;
				} else if(line.charAt(i-1)=='.' && line.charAt(i)==')'){
					numToPop=1;
				} else if(line.charAt(i-1)=='(' && line.charAt(i)=='.'){
					groups.add(curGroup);
					curGroup=0;
				}
				else if(line.charAt(i-1)=='(' && line.charAt(i)=='('){
					curGroup++;
					System.out.println("CUR"+curGroup);
				}
				else if(line.charAt(i-1)==')' && line.charAt(i)==')'){
					numToPop++;
					System.out.println("POP"+numToPop);
				}
				else if(line.charAt(i-1)==')' && line.charAt(i)=='.'){
					System.out.println("CUR"+curGroup+" POP "+numToPop);
					int count=0;
					while(numToPop>0){

						if(count>0 && shape.charAt(shape.length()-1) == '['){
							//System.out.println("Entered");
							//System.out.println(shape);
							shape=(String) shape.subSequence(0, shape.length()-1);
							//System.out.println(shape);
						}
						int numRemoved=groups.remove(groups.size()-1);
						numToPop=numToPop-numRemoved;
						count++;
					}
					shape=shape+"]";
					numToPop=0;
				}
			}
			break;
		} case 5:{
			int level=0;
			if (line.charAt(0)=='('){
				shape=shape+"[";
				curGroup=1;
				level++;
			}
			for (int i=1; i<line.length(); i++){
				if(line.charAt(i-1)=='.' && line.charAt(i)=='('){
					if(level<2){
						shape=shape+"[";
					}
					level++;
					curGroup=1;

				} else if(line.charAt(i-1)=='.' && line.charAt(i)==')'){
					numToPop=1;
				} else if(line.charAt(i-1)=='(' && line.charAt(i)=='.'){
					groups.add(curGroup);
					curGroup=0;
				}
				else if(line.charAt(i-1)=='(' && line.charAt(i)=='('){
					curGroup++;
				}
				else if(line.charAt(i-1)==')' && line.charAt(i)==')'){
					numToPop++;
				}
				else if(line.charAt(i-1)==')' && line.charAt(i)=='.'){
					while(numToPop>0){
						level--;
						if(groups.get(groups.size()-1)>numToPop){
							groups.set(groups.size()-1, groups.get(groups.size()-1)-numToPop);
							numToPop=0;
							int found=0;
							int j=shape.length()-2;
							//while true continue. break when found=0 and [. increase found if find ].
							while(true){
								if(shape.charAt(j)==']'){
									found++;
								}
								if(shape.charAt(j)=='['){
									if(found==0){
										level++;
										shape=shape.substring(0,j+1)+"["
												+shape.substring(j+1,shape.length());
										break;
									} else{
										found--;
									}
								}
								j--;
							}
							if(level<2){
								shape=shape+"]";
							}
							//while(found){

							//}
						} else {
							int numRemoved=groups.remove(groups.size()-1);
							numToPop=numToPop-numRemoved;
							if(level<2){
								shape=shape+"]";
							}
						}
						System.out.println(level);
					}
					numToPop=0;
				}
			}
			break;
		}
		}
		System.out.println(shape);
		in.close();
	}
}
