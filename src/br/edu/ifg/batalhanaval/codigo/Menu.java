package br.edu.ifg.batalhanaval.codigo;

/**
 *
 * @author Gustavo Ribeiro & Amanda Gomes
 * @date 19/05/2018
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo Ribeiro & Amanda Gomes
 * @date 12/05/2018
 */
public class Menu {

    // ATRIBUTOS;
    private Jogo jogo = new Jogo();
    private final ArrayList<Placar> placares = new ArrayList<>();
    private String informacoes;

    /**
     * Construtor padrao;
     */
    public Menu() {
        iniciaMenu();
    }

    private void iniciaMenu() {
        int opcao = montaMenu();
        caseMenu(opcao);
    }

    private int montaMenu() {
        String m = "Digite um número correspondente à opção\n\n";
        m += "1- Iniciar jogo\n";
        m += "2- Instruções\n";
        m += "3- Ranking\n";
        m += "Qualquer outra coisa para sair";

        return Integer.parseInt(JOptionPane.showInputDialog(m));
    }

    private void caseMenu(int opcao) {
        switch (opcao) {
            case 1:
                String nome = JOptionPane.showInputDialog("Digite o seu nome");
                int dificuldade = Integer.parseInt(JOptionPane.showInputDialog("Dificuldade\n\n1- Fácil\n2- Médio\n3- Difífil"));
                Placar placar = new Placar(nome, 0, dificuldade);
                switch (dificuldade) {
                    case 1:
                        jogo = new Jogo(10, placar);
                        adicionaPlacar(jogo.getPlacar());
                        break;
                    case 2:
                        jogo = new Jogo(20, placar);
                        adicionaPlacar(jogo.getPlacar());
                        break;
                    case 3:
                        jogo = new Jogo(30, placar);
                        adicionaPlacar(jogo.getPlacar());
                        break;
                    default:
                        jogo = new Jogo(20, placar);
                        adicionaPlacar(jogo.getPlacar());
                        break;
                }
                iniciaMenu();
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Instruções do jogo");
                iniciaMenu();
                break;
            case 3:
                JOptionPane.showMessageDialog(null, mostraPlacares());
                iniciaMenu();
                break;
            default:
                break;
        }
    }

    /**
     * Adiona um novo placar a lista de placares;
     *
     * @param placar
     */
    public void adicionaPlacar(Placar placar) {
        placares.add(placar);
    }

    /**
     * Mostra os placares;
     *
     * @return
     */
    public String mostraPlacares() {
        String m = "Placares\n\n";
        for (Placar aux : placares) {
            m += aux.toString();
        }
        return m;
    }

    /**
     * Retorna as isnformacoes do jogo;
     *
     * @return
     */
    public String getInformacoes() {
        return informacoes;
    }

    /**
     * Altera as informacoes do jogo;
     *
     * @param informacoes
     */
    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }

}
