import exceptions.CandidatoInexistenteException;
import exceptions.TituloInexistenteException;

public interface SistemaEleitoralInterface {

    public void votar(String numTitulo, int numeroVotado) throws TituloInexistenteException;
    public Candidato obterDadosDoCandidato(String nome) throws CandidatoInexistenteException;
    public int contarVotosParaCandidato(int numero);
    public boolean cadastraCandidato(String nome, int numero, Partido partido);
    public boolean cadastraEleitor(String nome, String titulo);
}
