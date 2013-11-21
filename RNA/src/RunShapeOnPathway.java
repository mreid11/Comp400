import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

//4 output.txt outtest2.txt
public class RunShapeOnPathway {
	public static void main(String[] args) throws IOException{
		File file=new File(args[1]);
		File output=new File(args[2]);
		output.delete();
		Scanner in=new Scanner(file);
		String line=in.next();
		System.setOut(new PrintStream(new FileOutputStream(output, true)));
		String [] arguments={args[0],line};
		MakeShape.main(arguments);
		while(in.hasNext()){
			in.next();
			in.next();
			line=in.next();
			if(line.equals("O")) break;
			arguments[1]=line;
			MakeShape.main(arguments);
		}
		in.close();
	}
}
