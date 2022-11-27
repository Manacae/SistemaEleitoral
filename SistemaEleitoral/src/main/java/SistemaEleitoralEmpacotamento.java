import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SistemaEleitoralEmpacotamento {

    // serialização: gravando o objetos no arquivo binário "VotosGravados"
    public static void gravarArquivoBinario(String VotosGravados) {
      File arquivo = new File(VotosGravados);
      try {
        arquivo.delete();
        arquivo.createNewFile();

        ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arquivo));
        objOutput.writeObject(VotosGravados);
        objOutput.close();

      } catch(IOException erro) {
          System.out.printf("Erro: %s", erro.getMessage());
      }
    }

    // desserialização: recuperando os objetos gravados no arquivo binário "VotosRecuperados"
    public static ArrayList<Object> lerArquivoBinario(String VotosRecuperados) {
      ArrayList<Object> lista = new ArrayList();
      try {
        File arq = new File(VotosRecuperados);
        if (arq.exists()) {
           ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
           lista = (ArrayList<Object>)objInput.readObject();
           objInput.close();
        }
      } catch(IOException erro1) {
          System.out.printf("Erro: %s", erro1.getMessage());
      } catch(ClassNotFoundException erro2) {
          System.out.printf("Erro: %s", erro2.getMessage());
      }

      return(lista);
    }

  }