import exceptions.CandidatoInexistenteException;
import exceptions.TituloInexistenteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaEleitoralMap implements SistemaEleitoralInterface{

    private List<Voto> votos = new ArrayList<>();
    private Map<String, Candidato> candidatos = new HashMap<>();
    private Map<String, Eleitor> eleitores = new HashMap<>();

    public SistemaEleitoralMap() {
        this.eleitores = new HashMap<>();
        this.candidatos = new HashMap<>();
        this.votos = new ArrayList<>();
    }

    @Override
    public void votar(String numTitulo, int numeroVotado) throws TituloInexistenteException {
        if (this.eleitores.containsKey(numTitulo)) {
            votos.add(new Voto(numeroVotado));
        } else {
            throw new TituloInexistenteException("Não existe eleitor com o título: "+numTitulo);
        }
    }

    @Override
    public Candidato obterDadosDoCandidato(String nome) throws CandidatoInexistenteException{
        if (candidatos.containsKey(nome)) {
            return candidatos.get(nome);
        } else {
            throw new CandidatoInexistenteException("Não existe um candidato com esse nome.");
        }
    }

    @Override
    public int contarVotosParaCandidato(int numero) {
        int votosCandidato = 0;
        for (Voto voto : votos) {
            if (voto.getNumeroVotado() == numero) {
                votosCandidato++;
            }
        }
        return votosCandidato;
    }

    @Override
    public boolean cadastraCandidato(String nome, int numero, Partido partido) {
        if (this.candidatos.containsKey(nome)) {
            return false;
        } else {
            Candidato candidato = new Candidato(nome, numero, partido);
            this.candidatos.put(nome, candidato);
            return true;
        }
    }

    @Override
    public boolean cadastraEleitor(String nome, String titulo) {
        if (this.eleitores.containsKey(titulo)) {
            return false;
        } else {
            this.eleitores.put(titulo, new Eleitor(nome, titulo));
            return true;
        }
    }


}
