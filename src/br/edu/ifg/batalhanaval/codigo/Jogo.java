package br.edu.ifg.batalhanaval.codigo;

/**
 *
 * @author Gustavo Ribeiro & Amanda Gomes
 * @date 19/05/2018
 */
import java.util.ArrayList;

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
    private int pontosVidas;

    /**
     * Construtor padrao;
     */
    public Jogo() {
    }

    public Jogo(int dificuldade, Placar placar) {
        this.placar = placar;
        this.dificuldade = dificuldade;

        geraTabuleiro();
        geraNavios();
        geraBombas();
        geraAgua();
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

            if (tabuleiro.get(posicaoY).get(posicaoX) == null) {
                switch (tamanho) {
                    case 1:
                        i++;
                        tabuleiro.get(posicaoY).set(posicaoX, "1");
                        navios++;
                        break;
                    case 2:
                        if (verificaNavioDeTamanho2(posicaoY, posicaoX + 1, "")) {
                            tabuleiro.get(posicaoY).set(posicaoX, "21");
                            i++;
                            navios++;
                        } else if (verificaNavioDeTamanho2(posicaoY + 1, posicaoX, "v")) {
                            tabuleiro.get(posicaoY).set(posicaoX, "v21");
                            i++;
                            navios++;
                        }
                        break;
                    case 3:
                        if (verificaNavioDeTamanho3(posicaoY, posicaoX + 1, posicaoY, posicaoX + 2, "")) {
                            tabuleiro.get(posicaoY).set(posicaoX, "31");
                            i++;
                            navios++;
                        } else if (verificaNavioDeTamanho3(posicaoY + 1, posicaoX, posicaoY + 2, posicaoX, "v")) {
                            tabuleiro.get(posicaoY).set(posicaoX, "v31");
                            i++;
                            navios++;
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
    private boolean verificaNavioDeTamanho2(int posicaoY, int posicaoX, String tamanho) {
        if (posicaoY < tabuleiro.size() && posicaoX < tabuleiro.get(posicaoY).size()) {
            if (tabuleiro.get(posicaoY).get(posicaoX) == null) {
                tabuleiro.get(posicaoY).set(posicaoX, tamanho + "22");
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
    private boolean verificaNavioDeTamanho3(int posicaoY1, int posicaoX1, int posicaoY2, int posicaoX2, String tamanho) {
        if (posicaoY1 < tabuleiro.size() && posicaoX1 < tabuleiro.get(posicaoY1).size() && posicaoY2 < tabuleiro.size() && posicaoX2 < tabuleiro.get(posicaoY2).size()) {
            if (tabuleiro.get(posicaoY1).get(posicaoX1) == null && tabuleiro.get(posicaoY2).get(posicaoX2) == null) {
                // Segunda casa do navio;
                tabuleiro.get(posicaoY1).set(posicaoX1, tamanho + "32");
                navios++;
                // Terceira casa do navio;
                tabuleiro.get(posicaoY2).set(posicaoX2, tamanho + "33");
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
     *
     * @return
     */
    public int getVidas() {
        return vidas;
    }

    /**
     *
     * @param vidas
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public String jogar(int x, int y) {
        // bomba
        if (tabuleiro.get(x).get(y).equals("*")) {
            vidas--;
            if (placar.getPontos() - 10 > 0) {
                placar.setPontos(placar.getPontos() - 10);
            } else {
                placar.setPontos(0);
            }
            tabuleiro.get(x).set(y, "X");
            return "5";
        }

        // Agua
        if (tabuleiro.get(x).get(y).equals("-")) {
            tabuleiro.get(x).set(y, "X");
            return "6";
        }

        // Navio
        String navio = tabuleiro.get(x).get(y);
        if (navio.equals("1") || navio.equals("21") || navio.equals("22") || navio.equals("31") || navio.equals("32") || navio.equals("33") || navio.equals("v21") || navio.equals("v22") || navio.equals("v31") || navio.equals("v32") || navio.equals("v33")) {
            placar.setPontos(placar.getPontos() + 20);
            pontosVidas += 20;
            tabuleiro.get(x).set(y, "X");
            navios--;
            return navio;
        }

        return "7";
    }

    public String mostraTabuleiro(int x, int y) {
        // bomba
        if (tabuleiro.get(x).get(y).equals("*")) {
            return "5";
        }

        // Agua
        if (tabuleiro.get(x).get(y).equals("-")) {
            return "6";
        }

        // Navio
        String navio = tabuleiro.get(x).get(y);
        if (navio.equals("1") || navio.equals("21") || navio.equals("22") || navio.equals("31") || navio.equals("32") || navio.equals("33") || navio.equals("v21") || navio.equals("v22") || navio.equals("v31") || navio.equals("v32") || navio.equals("v33")) {
            return navio;
        }

        return "7";
    }

    public int getPontosVidas() {
        return pontosVidas;
    }

    public void setPontosVidas(int pontosVidas) {
        this.pontosVidas = pontosVidas;
    }

    public int getNavios() {
        return navios;
    }

    public void setNavios(int navios) {
        this.navios = navios;
    }

}
