import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import exceptions.*;
import javax.swing.*;
import java.awt.*;


public class SistemaEleitoralGUI extends JFrame {

    public SistemaEleitoralGUI() {

        SistemaEleitoralMap sistema = new SistemaEleitoralMap();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel linha1;

        setTitle("Sistema Eleitoral");
        setSize(700, 400);
        setResizable(false);
        setVisible(true);

        linha1 = new JLabel("SISTEMA ELEITORAL", JLabel.CENTER);
        linha1.setForeground(Color.BLACK);
        linha1.setFont(new Font("Bahnschrift", Font.BOLD, 24));
        setLayout(new BorderLayout(2,3));

        JPanel listaBotoes = new JPanel();


        Button cadastrarEleitor = new Button("CADASTRAR ELEITOR");
        cadastrarEleitor.addActionListener(new btnCadEleitor(sistema));
        
        Button cadastrarCandidato = new Button("CADASTRAR CANDIDATO");
        cadastrarCandidato.addActionListener(new btnCadCandidato(sistema));

        Button votar = new Button("VOTAR");
        votar.addActionListener(new btnVotar(sistema));

        Button obterDados = new Button("DADOS");
        obterDados.addActionListener(new btnObterDados(sistema));

        listaBotoes.add(cadastrarCandidato);
        listaBotoes.add(cadastrarEleitor);
        listaBotoes.add(votar);
        listaBotoes.add(obterDados);

        setBackground(Color.WHITE);
        setFont(new Font("Bahnschrift", Font.BOLD, 20));
        setForeground(Color.BLACK);

        add(BorderLayout.CENTER, listaBotoes);
        add(linha1, BorderLayout.NORTH); //vai aparecer na parte de cima da tela
        setVisible(true);

    }

    private class btnCadEleitor implements ActionListener {
        
        SistemaEleitoralMap sistemaEleitoral;

        public btnCadEleitor(SistemaEleitoralMap sist) {
            this.sistemaEleitoral = sist;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String nome = JOptionPane.showInputDialog("DIGITE SEU NOME: ");
            String titulo = JOptionPane.showInputDialog("INSIRA SEU TÍTULO: ");

            sistemaEleitoral.cadastraEleitor(nome, titulo);

            System.out.println("OK");
        }
    }

    private class btnCadCandidato implements ActionListener {
        
        SistemaEleitoralMap sistemaEleitoral;

        public btnCadCandidato(SistemaEleitoralMap sist) {
            this.sistemaEleitoral = sist;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            Partido [] partidos = {
                Partido.PARTIDO1, Partido.PARTIDO2, Partido.PARTIDO3
            };  

            String nome = JOptionPane.showInputDialog("DIGITE SEU NOME: ");
            int numero = Integer.parseInt(JOptionPane.showInputDialog("INSIRA SEU NÚMERO DE CAMPANHA: "));
            Partido partido = partidos[JOptionPane.showOptionDialog(null, "ESCOLHA SEU PKTARTIDO: ", "", 0, JOptionPane.QUESTION_MESSAGE, null, partidos, Partido.PARTIDO1)];


            sistemaEleitoral.cadastraCandidato(nome, numero, partido);

            System.out.println("OK");
        }
    }

    private class btnVotar implements ActionListener {
        
        SistemaEleitoralMap sistemaEleitoral;

        public btnVotar(SistemaEleitoralMap sist) {
            this.sistemaEleitoral = sist;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String numTitulo = JOptionPane.showInputDialog("INSIRA O NÚMERO DO SEU TÍTULO: ");            
            int numeroVotado = Integer.parseInt(JOptionPane.showInputDialog("INSIRA O NÚMERO VOTADO: "));

            try {
                sistemaEleitoral.votar(numTitulo, numeroVotado);
            } catch (TituloInexistenteException exception){
                System.out.println(exception);
            }

            System.out.println("OK");
        }
    }

    private class btnObterDados implements ActionListener {
        
        SistemaEleitoralMap sistemaEleitoral;

        public btnObterDados(SistemaEleitoralMap sist) {
            this.sistemaEleitoral = sist;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            String nome = JOptionPane.showInputDialog("DIGITE O SEU NOME: ");            

            try {
                sistemaEleitoral.obterDadosDoCandidato(nome);

            } catch (CandidatoInexistenteException exception) {
                System.out.println(exception);
            }

            System.out.println(nome);
        }
    }

    public static void main (String []args) {
        JFrame window = new SistemaEleitoralGUI();

    }
    
}