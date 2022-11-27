import exceptions.CandidatoInexistenteException;
import exceptions.TituloInexistenteException;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

public class SistemaEleitoralMapTest {

    @Test
    void garanteCadastroEleitorUnico() {
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();
        /**
         * @assertTrue garante que Jonas foi cadastrado
         * @assertFalse garante que Jonas não foi cadastrado outra vez, pois não podem existir dois eleitores com o mesmo nome
         */
            
        boolean cadastrou1 = sistema.cadastraEleitor("Jonas", "10");
        assertTrue(cadastrou1);
        boolean cadastrou2 = sistema.cadastraEleitor("Jonas", "10");
        assertFalse(cadastrou2);
    }

    @Test
    void pegaDadosCandidato() throws CandidatoInexistenteException {
        /**
         * @assertEquals Verifica se o número do candidato votado está correto
         * @assertTrue Verifica se o partido votado é o mesmo do partido cadastrado
         */
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();
        assertTrue(sistema.cadastraCandidato("Ed", 20, Partido.PARTIDO1));
        try {
            Candidato c = sistema.obterDadosDoCandidato("Ed");
            assertEquals(20, c.getNumero());
            assertTrue(c.getPartido() == Partido.PARTIDO1);
        } catch (CandidatoInexistenteException e) {
            fail("Não deveria lançar exceção");
        }
    }

    @Test
    void contagemDeVotos() throws TituloInexistenteException {
        /**
         * @fail o título existe, portanto o método não deve lançar exception
         * @assertEquals1 garante que o número inicial de votos é igual a 0
         * @assertEquals2 garante que o número de votos depois do método votar seja igual a 1
         */
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();
        try {
            sistema.cadastraEleitor("Jonas", "10");

            assertEquals(0, sistema.contarVotosParaCandidato(20));
            sistema.votar("10", 20);
            assertEquals(1, sistema.contarVotosParaCandidato(20));
        } catch (TituloInexistenteException e) {
            fail("Não existe um eleitor com esse título.");
        }
    }

    @Test
    void garanteCadastroCandidatoUnico() {

        /**
         * @assertTrue garante que Jonas foi cadastrado
         * @assertFalse garante que Jonas não foi cadastrado outra vez, pois não podem existir dois eleitores com o mesmo nome
         */
        SistemaEleitoralMap sistema = new SistemaEleitoralMap();

        boolean cadastrou1 = sistema.cadastraCandidato("Ed", 20, Partido.PARTIDO3);
        assertTrue(cadastrou1);
        boolean cadastrou2 = sistema.cadastraCandidato("Ed", 20, Partido.PARTIDO3);
        assertFalse(cadastrou2);
    }

}