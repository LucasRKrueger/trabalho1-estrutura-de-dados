import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacaoTags {
	
	PilhaLista<String> tagsPilha = new PilhaLista<>();
	Lista<String> tagsSingleton = new Lista<>();
	Lista<String> tagsErradas = new Lista<>();
	Lista<String> tagsCorretas = new Lista<>();		
	Lista<String> tagsSingletonDefinidas = new Lista<>();
	
	String mensagem = "";
	String regexAbertura = "(\\<)([A-Za-z0-9_!]+)(.*?)(\\>)";
	String regexFechamento = "(\\<\\/)([A-Za-z0-9_!]+)(\\>)";
	String linhaAtual = "";
	String tagAbertura = "";
	String tagFechamento = "";
	
	Pattern patternAbertura;
	Pattern patternFechamento;
	Matcher matcherAbertura;
	Matcher matcherFechamento;
	
	public ValidacaoTags() {
		inserirTagsSemFechamento();
	}
	
	public String validarEstrutura(String html) {
		
		Scanner scanner = new Scanner(html);		
		
		while(scanner.hasNextLine()) {
			
			linhaAtual = scanner.nextLine();

			patternAbertura = Pattern.compile(regexAbertura);
			matcherAbertura = patternAbertura.matcher(linhaAtual);
			
			patternFechamento = Pattern.compile(regexFechamento);
			matcherFechamento = patternFechamento.matcher(linhaAtual);
			
			if(matcherAbertura.find()) {
				tagAbertura = matcherAbertura.group(2); //TAG SEM <>
				
				if(this.tagsSingletonDefinidas.buscar(tagAbertura) != null){					
					this.tagsSingleton.inserir(tagAbertura);
					this.tagsCorretas.inserir(tagAbertura);
				} else {
					this.tagsPilha.push(tagAbertura);
					mensagem += "Tag " + tagAbertura + " encontrada. \n";
				}
			}
			
			if(matcherFechamento.find()) {
				tagFechamento = matcherFechamento.group(2); //TAG SEM <>
				mensagem += "Tag " + tagFechamento + " encontrada. \n"; 
				
				if(tagFechamento.equalsIgnoreCase(this.tagsPilha.peek())) {
					this.tagsCorretas.inserir(this.tagsPilha.peek());
					this.tagsPilha.pop();
				} else {
					mensagem += "\n Tag inesperada encontrada." + " Tag: " + tagFechamento + " Esperado a tag: " + this.tagsPilha.peek() + " \n" ;
				}
			}
		}		
		
		scanner.close();
		
		mensagem += this.tagsPilha.estaVazia() ? "Arquivo válido" : "Arquivo Inválido";
		
		return mensagem;
	}
	
	public String retornarFrequenciaTags() {
		String texto = "";
		String tagAtual = "";
		int frequenciaTags = 0;
		ArrayList<String> tagsJaValidadas = new ArrayList<String>();
		
		for(int i = 0; i < tagsCorretas.obterComprimento(); i++) {
			
			if(tagNaoFoiValidada(tagsJaValidadas, tagsCorretas.getNo(i).getInfo())) {
				frequenciaTags = tagsCorretas.verificaFrequencia(this.tagsCorretas.getNo(i));
				
				tagAtual = tagsCorretas.getNo(i).getInfo();
				
				tagsJaValidadas.add(tagAtual);
				
				texto += " Tag " + tagAtual + " utilizada: " + frequenciaTags + " vezes \n";
			}	
		}		
		return texto;
	}
	
	private boolean tagNaoFoiValidada(List<String> tagsValidadas, String tagAtual) {		
		if(tagsValidadas == null) 
			return true;
		else {
			for(String tag : tagsValidadas) {
				if(tag.equalsIgnoreCase(tagAtual)) {
					return false;
				}
			}
			return true;
		}
	}
	
	public void inserirTagsSemFechamento() {
		tagsSingletonDefinidas.inserir("!DOCTYPE");
		tagsSingletonDefinidas.inserir("br");
		tagsSingletonDefinidas.inserir("col");
		tagsSingletonDefinidas.inserir("hr");
		tagsSingletonDefinidas.inserir("img");
		tagsSingletonDefinidas.inserir("input");
		tagsSingletonDefinidas.inserir("link");
		tagsSingletonDefinidas.inserir("param");
		tagsSingletonDefinidas.inserir("embed");
		tagsSingletonDefinidas.inserir("source");
		tagsSingletonDefinidas.inserir("meta");
		tagsSingletonDefinidas.inserir("command");
		tagsSingletonDefinidas.inserir("area");
		tagsSingletonDefinidas.inserir("base");
		tagsSingletonDefinidas.inserir("wbr");
		tagsSingletonDefinidas.inserir("track");
		tagsSingletonDefinidas.inserir("keygen");
	}
	
	public String getMessagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
