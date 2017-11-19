import java.io.*;


public class treesearch{
	public static void main(String[] args){


		String filename = args[0];

		String line = null;

		try{
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			PrintStream fileOutput = new PrintStream(new File("output_file.txt"));

			System.setOut(fileOutput);

			line = bufferedReader.readLine();
			int order = Integer.parseInt(line);
			BPlusTree bPlusTree = new BPlusTree(order); 

			while((line=bufferedReader.readLine()) != null){
				String[] command = line.split("\\(");
				String operation = command[0].toLowerCase();
				String[] parameters = command[1].split(",");

				
				if(operation.equals("insert")){
					bPlusTree.insert(Double.parseDouble(parameters[0]), parameters[1].substring(0,parameters[1].length()-1));
				}

				else if(operation.equals("search") && parameters.length == 1){
					
					bPlusTree.search(Double.parseDouble(parameters[0].substring(0,parameters[0].length()-1)));
				}
				else{
					bPlusTree.search(Double.parseDouble(parameters[0]), Double.parseDouble(parameters[1].substring(0,parameters[1].length()-1)));
				}

			}
		}
		catch (FileNotFoundException ex){
			System.out.println("Unable to open " + filename );
		}
		catch(IOException ex){
			System.out.println("Error in reading the file " + filename );
			ex.printStackTrace();
		}


		return;
	}
}