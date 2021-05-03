import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Arquivo {
	public String validarArquivo(String caminhoArquivo) throws Exception{
        try {       	
        	File arquivo = new File(caminhoArquivo);     
        	
            if(!caminhoArquivo.toUpperCase().endsWith(".HTML")) {
            	throw new ExtensionException("Formato inválido!");
            }
            	
            FileReader leitorArquivo = new FileReader(arquivo.getAbsolutePath());
            BufferedReader bufferArquivo = new BufferedReader(leitorArquivo);

            String conteudoArquivo = "";
            String linha = bufferArquivo.readLine();
            while (linha != null) {
                conteudoArquivo += linha + "\n";
                linha = bufferArquivo.readLine();
            }
            
            bufferArquivo.close();

                return conteudoArquivo;
        } catch (ExtensionException e){
        	throw new Exception(e.getMessage());
        }
        catch (Exception e){
        	throw new Exception("Caminho inválido!");
        }
    }
}
