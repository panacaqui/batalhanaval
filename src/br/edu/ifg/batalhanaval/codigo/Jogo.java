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
public class Jogo {

    // ATRIBUTOS;
    private int vidas = 3;
    private int dificuldade = 20;
    private final ArrayList<ArrayList<String>> tabuleiro = new ArrayList<>();
    private int navios = 0;
    private Placar placar;

    /**
     * Construtor padrao;
     */
    public Jogo() {
    }

    public Jogo(int dificuldade, Placar placar) {
        this.placar = placar;
        this.dificuldade = dificuldade;
        montaTabuleiro();
        iniciaJogo();
    }

    /**
     * Funcao que inicializa o tabuleiro com o tamanho de 10x10;
     */
    private void geraTabuleiro() {
        for (int i = 0; i < 10; i++) {
            tabuleiro.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                tabuleiro.get(i).add(null);
            }
        }
    }

    /**
     * Funcao que gera os navios aleatoriamente no tabuleiro;
     */
    private void geraNavios() {
        for (int i = 0; i < 6;) {
            int posicaoY = (int) (Math.random() * 10 + 0);
            int posicaoX = (int) (Math.random() * 10 + 0);
            int tamanho = (int) (Math.random() * 3 + 1);

            // Verifica se a posicao tá ocupada;
            if (tabuleiro.get(posicaoY).get(posicaoX) == null) {
                // Primeira posicao do navio;
                tabuleiro.get(posicaoY).set(posicaoX, "O");
                navios++;
                // Verifica o tamanho dos navios;
                switch (tamanho) {
                    case 1:
                        i++;
                        break;
                    case 2:
                        if (verificaNavioDeTamanho2(posicaoY, posicaoX + 1, tamanho)) {
                            i++;
                        } else if (verificaNavioDeTamanho2(posicaoY + 1, posicaoX, tamanho)) {
                            i++;
                        } else if (verificaNavioDeTamanho2(posicaoY, posicaoX - 1, tamanho)) {
                            i++;
                        } else if (verificaNavioDeTamanho2(posicaoY - 1, posicaoX, tamanho)) {
                            i++;
                        }
                        break;
                    case 3:
                        if (verificaNavioDeTamanho3(posicaoY, posicaoX + 1, posicaoY, posicaoX + 2, tamanho)) {
                            i++;
                        } else if (verificaNavioDeTamanho3(posicaoY + 1, posicaoX, posicaoY + 2, posicaoX, tamanho)) {
                            i++;
                        } else if (verificaNavioDeTamanho3(posicaoY, posicaoX - 1, posicaoY, posicaoX - 2, tamanho)) {
                            i++;
                        } else if (verificaNavioDeTamanho3(posicaoY - 1, posicaoX, posicaoY - 2, posicaoX, tamanho)) {
                            i++;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Funcao que data a posicao do navio, verifica onde pode cria-lo no
     * tabuleiro e se onde puder, cria;
     *
     * @param posicaoY
     * @param posicaoX
     * @param tamanho
     * @return
     */
    private boolean verificaNavioDeTamanho2(int posicaoY, int posicaoX, int tamanho) {
        if (posicaoY < tabuleiro.size() && posicaoX < tabuleiro.get(posicaoY).size()) {
            if (tabuleiro.get(posicaoY).get(posicaoX) == null) {
                tabuleiro.get(posicaoY).set(posicaoX, "O");
                navios++;
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Funcao que data a posicao do navio, verifica onde pode cria-lo no
     * tabuleiro e se onde puder, cria;
     *
     * @param posicaoY1
     * @param posicaoX1
     * @param posicaoY2
     * @param posicaoX2
     * @param tamanho
     * @return
     */
    private boolean verificaNavioDeTamanho3(int posicaoY1, int posicaoX1, int posicaoY2, int posicaoX2, int tamanho) {
        if (posicaoY1 < tabuleiro.size() && posicaoX1 < tabuleiro.get(posicaoY1).size() && posicaoY2 < tabuleiro.size() && posicaoX2 < tabuleiro.get(posicaoY2).size()) {
            if (tabuleiro.get(posicaoY1).get(posicaoX1) == null && tabuleiro.get(posicaoY2).get(posicaoX2) == null) {
                // Segunda casa do navio;
                tabuleiro.get(posicaoY1).set(posicaoX1, "O");
                navios++;
                // Terceira casa do navio;
                tabuleiro.get(posicaoY2).set(posicaoX2, "O");
                navios++;
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Funcao de gera as bombas aleatoriamente no tabuleiro;
     */
    private void geraBombas() {
        for (int i = 0; i < dificuldade;) {
            int posicaoX = (int) (Math.random() * 10 + 0);
            int posicaoY = (int) (Math.random() * 10 + 0);

            // Verifica se a posicao tá ocupada;
            if (tabuleiro.get(posicaoX).get(posicaoY) == null) {
                tabuleiro.get(posicaoX).set(posicaoY, "*");
                i++;
            }
        }
    }

    /**
     * Funcao que insere agua em todos os campos vazios do tabuleiro;
     */
    private void geraAgua() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Verifica se a posicao tá ocupada;
                if (tabuleiro.get(i).get(j) == null) {
                    tabuleiro.get(i).set(j, "-");
                }
            }
        }
    }

    /**
     * Funcao que chama as outras funcoes para montar o tabuleiro;
     */
    private void montaTabuleiro() {
        geraTabuleiro();
        geraNavios();
        geraBombas();
        geraAgua();
    }

    /**
     *
     * @return
     */
    public int getDificuldade() {
        return dificuldade;
    }

    /**
     *
     * @param dificuldade
     */
    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    /**
     *
     * @return
     */
    public Placar getPlacar() {
        return placar;
    }

    /**
     *
     * @param placar
     */
    public void setPlacar(Placar placar) {
        this.placar = placar;
    }

    /**
     * Todo o jogo;
     */
    public void iniciaJogo() {
        while (vidas > 0) {
            if (navios == 0) {
                JOptionPane.showMessageDialog(null, "PARABENS! VOCÊ VENCEU!!!!");
                return;
            }

            String info = String.format("Jogador: %s - Pontos: %d Vidas: %d\n\n%s", placar.getJogador(), placar.getPontos(), vidas, mostraTabuleiro());
            int x = Integer.parseInt(JOptionPane.showInputDialog(info + "Posição Vertical"));
            int y = Integer.parseInt(JOptionPane.showInputDialog(info + "Posição Horizontal"));

            if (tabuleiro.get(x).get(y).equals("X")) {
                JOptionPane.showMessageDialog(null, "Você já marcou essa posição, por favor, selecione outra!");
            }

            if (tabuleiro.get(x).get(y).equals("*")) {
                vidas--;
                if (placar.getPontos() - 10 > 0) {
                    placar.setPontos(placar.getPontos() - 10);
                } else {
                    placar.setPontos(0);
                }
                tabuleiro.get(x).set(y, "X");
            }

            if (tabuleiro.get(x).get(y).equals("O")) {
                placar.setPontos(placar.getPontos() + 20);
                tabuleiro.get(x).set(y, "X");
                navios--;
            }

            if (tabuleiro.get(x).get(y).equals("-")) {
                tabuleiro.get(x).set(y, "X");
            }
        }
        JOptionPane.showMessageDialog(null, "GAME OVER!\nSuas vidas acabaram");
    }

    /**
     * Funcao que mostra o tabuleiro para a parte lógica;
     *
     * @return
     */
    private String mostraTabuleiro() {
        String tab = "";
        for (ArrayList<String> aux : tabuleiro) {
            for (String objeto : aux) {
                tab += objeto + "      ";
            }
            tab += "\n";
        }
        return tab;
    }
}
