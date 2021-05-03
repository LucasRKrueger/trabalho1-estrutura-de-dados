
public class PilhaLista<T> implements Pilha<T> {

	private Lista<T> lista;

	public PilhaLista() {
		lista = new Lista<T>();
	}

	public void push(T info) {
		lista.inserir(info);
	}

	public T pop() {
		if (estaVazia())
			throw new PilhaVaziaException();

		T valor;
		valor = lista.getPrimeiro().getInfo();
		lista.retirar(valor);

		return valor;
	}

	public T peek() {
		if (estaVazia())
			throw new PilhaVaziaException();
		
		return lista.getPrimeiro().getInfo();
	}

	public boolean estaVazia() {
		return lista.estaVazia();
	}

	public void liberar() {
		while (!estaVazia()) {
			pop();
		}

	}

	public String toString() {
		String resultado = "";
		NoLista<T> p = lista.getPrimeiro();

		while(p!= null) {
			resultado = resultado + p.getInfo();
			if (p.getProximo() != null)
				resultado = resultado + ",";
			p = p.getProximo();
		}
		return resultado;
	}
}