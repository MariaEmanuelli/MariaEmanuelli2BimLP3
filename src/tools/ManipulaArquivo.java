package tools;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManipulaArquivo {

    public ManipulaArquivo() {
    }

    
    public List<String> abrirArquivo(String caminho) {
         
        List<String> texto = null;
        try {
            //OpenFile
            File file = new File(caminho);
            if (file.exists()) {                
                FileReader arquivo = new FileReader(caminho);
                BufferedReader conteudoDoArquivo = new BufferedReader(arquivo);
                String linha = conteudoDoArquivo.readLine();
                texto = new ArrayList<String>();
                while (linha != null) {
                    texto.add(linha);
                    linha = conteudoDoArquivo.readLine();
                }
                conteudoDoArquivo.close();
            } else {
                return null;
            }
        } catch (Exception e) {//Catch exception if any
            return null;
        }
        return texto;
    }

    public int salvarArquivo(String caminho, List<String> texto) {
        try {
            // Create file 
            FileWriter arquivo = new FileWriter(caminho);
            BufferedWriter conteudoDoArquivo = new BufferedWriter(arquivo);
            for (int i = 0; i < texto.size(); i++) {
                conteudoDoArquivo.write(texto.get(i) + System.lineSeparator());
            }
            conteudoDoArquivo.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
            return 1; //houve erro
        }
        return 0;
    }
    
    public void novoArquivo(String caminho){
        String textoQueSeraEscrito = "";
		FileWriter arquivo;
		try {
		    arquivo = new FileWriter(new File(caminho));
		    arquivo.write(textoQueSeraEscrito);
	            arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
                }
    }
    
}
