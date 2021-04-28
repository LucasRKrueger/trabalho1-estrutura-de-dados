
public class AvaliaTags {
	
	PilhaLista<String> tagsPilha = new PilhaLista<>();
	Lista<String> tagsSingleton = new Lista<>();
	Lista<String> tagsErradas = new Lista<>();
	Lista<String> tagsCorretas = new Lista<>();
		
	Lista<String> tagsSingletonDefinidas = new Lista<>();
	
	public AvaliaTags() {
		inserirTagsSemFechamento();
	}
	
	public void validarEstrutura(String html) {
		
	}
	
	public String retornarFrequenciaTags(Lista<String> tagsCorretas) {
		String texto = "";
		
		for(int i = 0; i < tagsCorretas.obterComprimento(); i++) {
			int frequenciaTags = tagsCorretas.verificaFrequencia(this.tagsCorretas.getNo(i));
			String tagAtual = tagsCorretas.getNo(i).getInfo();
			
			texto += "Tag " + tagAtual + "utilizada: " + frequenciaTags + " vezes";
		}
		
		return texto;
	}
	
	public void inserirTagsSemFechamento() {
		tagsSingletonDefinidas.inserir("!DOCTYPE");
		tagsSingletonDefinidas.inserir("html");
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
	}
}
