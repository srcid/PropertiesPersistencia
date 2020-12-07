package br.ufc.alcides;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LinesReader {

	public static void main(String[] args) {
		
		System.out.println(args[0]);
		Properties props = new Properties();
		InputStream inStream = null;
		File file = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		try {
			inStream = LinesReader.class.getClassLoader().getResourceAsStream("config.properties");
			file = new File(args[0]);
			fileReader = new FileReader(file);
			
			props.load(inStream);
			
			String sLinhaInicial = props.getProperty("linha_inicial", "1");
			String sLinhaFinal = props.getProperty("linha_final", "-1");
			
			int linhaInicial = Integer.parseInt(sLinhaInicial);
			int linhaFinal = Integer.parseInt(sLinhaFinal);
			
			bufferedReader = new BufferedReader(fileReader);
					
			if (linhaFinal < 0) {
				bufferedReader
				.lines()
				.skip(linhaInicial - 1)
				.forEach( l -> System.out.print(l) );
				
			} else {
				bufferedReader
				.lines()
				.limit(linhaFinal - 1)
				.skip(linhaInicial - 1)
				.forEach( l -> System.out.println(l) );
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			try {
				inStream.close();
				fileReader.close();
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
		}
		
	}

}
