package br.edu.ifg.batalhanaval.codigo;

/**
 *
 * @author Gustavo Ribeiro & Amanda Gomes
 * @date 12/05/2018
 */
public class Placar {

    private String jogador;
    private int pontos;
    private int dificuldade;

    /**
     * Construtor padrao;
     */
    public Placar() {
    }

    /**
     *
     * @param jogador
     * @param pontos
     * @param dificuldade
     */
    public Placar(String jogador, int pontos, int dificuldade) {
        this.jogador = jogador;
        this.pontos = pontos;
        this.dificuldade = dificuldade;
    }

    /**
     *
     * @return
     */
    public String getJogador() {
        return jogador;
    }

    /**
     *
     * @param jogador
     */
    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    /**
     *
     * @return
     */
    public int getPontos() {
        return pontos;
    }

    /**
     *
     * @param pontos
     */
    public void setPontos(int pontos) {
        this.pontos = pontos;
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

    @Override
    public String toString() {
        return String.format("Jogador: %s - %d pontos (Dficuldade: %s)", jogador, pontos, dificuldade);
    }

}
