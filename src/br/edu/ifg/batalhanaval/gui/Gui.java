package br.edu.ifg.batalhanaval.gui;

import br.edu.ifg.batalhanaval.codigo.Jogo;
import br.edu.ifg.batalhanaval.codigo.Placar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Gui extends JFrame {

    Color transparent = new Color(0, 0, 0, 0);

    // LOGICO
    private Jogo jogo;
    private final ArrayList<Placar> placares;

    // GERAL
    private final JPanel jlTitulo;
    private final JPanel jlCreditos;
    private final Handler handler;
    private final Icon iconMar;

    // TELA INICIAL
    private JPanel jpInicio;

    private JButton btIniciar;
    private JButton btInformacoes;
    private JButton btRanking;

    // TELA DO JOGADOR
    private JPanel jpTelaJogador;
    private JTextField tfNomeJogador;

    private JRadioButton rbFacil;
    private JRadioButton rbMedio;
    private JRadioButton rbDificil;
    private ButtonGroup rgDificuldade;

    private JButton btComecar;
    private JButton btVoltaJogador;

    // TELA INSTRUÇÕES
    private JPanel jpInformacoes;
    private JButton btVoltaInformacoes;

    // TELA RANKING
    private JPanel jpRanking;
    private JButton btVoltarRanking;

    // JOGO
    private JPanel jpTabuleiro;
    private JLabel jlJogador;
    private JLabel jLPontos;
    private JLabel jlVidas;
    private ArrayList<ArrayList<JButton>> botoes;
    private int[] posicaoClicada;
    private JButton btVoltarTabuleiro;
    private JButton btAjuda;
    private JButton btVoltarJogo;

    public Gui() {
        super("Batalha naval");

        placares = new ArrayList<>();
        jlTitulo = criaLabel("Batalha Naval", 50);
        jlCreditos = criaLabel("Todos os direitos reservados: Gustavo Ribeiro e Amanda Gomes", 15);
        handler = new Handler();
        iconMar = new ImageIcon(getClass().getResource("/br/edu/ifg/batalhanaval/imagens/mar.gif"));

        montaInicio();
    }

    public class jpanel extends JPanel {

        public jpanel(LayoutManager layout) {
            super(layout);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            Image background = new ImageIcon(getClass().getResource("/br/edu/ifg/batalhanaval/imagens/fundo.png")).getImage();
            g.drawImage(background, 0, 0, this);
        }
    }

    private void montaInicio() {
        jpInicio = new jpanel(new GridLayout(5, 1));

        jpInicio.add(jlTitulo);

        btIniciar = new JButton("Iniciar");
        jpInicio.add(criaPanelComBotao(btIniciar));

        btInformacoes = new JButton("Instruções");
        jpInicio.add(criaPanelComBotao(btInformacoes));

        btRanking = new JButton("Ranking");
        jpInicio.add(criaPanelComBotao(btRanking));

        jpInicio.add(jlCreditos);

        add(jpInicio);
    }

    private void montaTelaJogador() {
        jpTelaJogador = new jpanel(new GridLayout(6, 1));
        jpTelaJogador.setBackground(Color.WHITE);

        jpTelaJogador.add(jlTitulo);

        jpTelaJogador.add(criaLabel("Nome Jogador", 25));

        JPanel jpTfNomeJogador = new JPanel(new FlowLayout());
        jpTfNomeJogador.setBackground(transparent);
        tfNomeJogador = new JTextField();
        jpTfNomeJogador.add(tfNomeJogador);
        tfNomeJogador.setPreferredSize(new Dimension(265, 50));
        jpTelaJogador.add(jpTfNomeJogador);

        jpTelaJogador.add(criaLabel("Dificuldade", 25));

        rbFacil = new JRadioButton("Fácil");
        rbMedio = new JRadioButton("Médio");
        rbDificil = new JRadioButton("Difícil");

        JRadioButton[] radioButtons = {rbFacil, rbMedio, rbDificil};
        jpTelaJogador.add(criaRadioButton(radioButtons));

        rgDificuldade = new ButtonGroup();
        rgDificuldade.add(rbFacil);
        rgDificuldade.add(rbMedio);
        rgDificuldade.add(rbDificil);

        btVoltaJogador = new JButton("Voltar");
        btComecar = new JButton("Começar");
        JButton[] aux = {btVoltaJogador, btComecar};
        jpTelaJogador.add(criaPanelComBotoes(aux));

        add(jpTelaJogador);
    }

    private void montaTelaInformacoes() {
        jpInformacoes = new jpanel(new GridLayout(6, 1));

        jpInformacoes.add(jlTitulo);

        jpInformacoes.add(criaLabel("Infromações", 25));
        jpInformacoes.add(criaLabel("Aqui vem as informações", 15));

        jpInformacoes.add(criaLabel("Instruções", 25));
        jpInformacoes.add(criaLabel("Aqui vem as instruções", 15));

        btVoltaInformacoes = new JButton("Voltar");
        jpInformacoes.add(criaPanelComBotao(btVoltaInformacoes));

        add(jpInformacoes);
    }

    private void montaTelaRanking() {
        jpRanking = new jpanel(new GridLayout(5, 1));
        jpRanking.setBackground(Color.WHITE);

        jpRanking.add(jlTitulo);

        jpRanking.add(criaLabel("Placares", 25));

        JPanel titulos = new JPanel(new GridLayout(1, 3));
        titulos.setBackground(transparent);
        titulos.add(criaLabel("Jogador", 20));
        titulos.add(criaLabel("Pontos", 20));
        titulos.add(criaLabel("Dificuldade", 20));

        jpRanking.add(titulos);

        JPanel jpPlacares = new JPanel(new GridLayout(placares.size(), 3));
        jpPlacares.setBackground(transparent);
        for (Placar aux : placares) {
            jpPlacares.add(criaLabel(aux.getJogador(), 15));

            jpPlacares.add(criaLabel(aux.getPontos() + "", 15));

            String dificuldade = "Fácil";
            if (aux.getDificuldade() == 20) {
                dificuldade = "Normal";
            } else if (aux.getDificuldade() == 30) {
                dificuldade = "Difícil";
            }
            jpPlacares.add(criaLabel(dificuldade, 15));
        }
        jpRanking.add(jpPlacares);

        btVoltarRanking = new JButton("Voltar");
        jpRanking.add(criaPanelComBotao(btVoltarRanking));

        add(jpRanking);
    }

    private void geraBotoes() {
        botoes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            botoes.add(new ArrayList<>());
            for (int j = 0; j < 10; j++) {
                botoes.get(i).add(new JButton(i + " - " + j, iconMar));
                botoes.get(i).get(j).setPreferredSize(new Dimension(60, 60));
                botoes.get(i).get(j).addActionListener(new HandlerJogo());
            }
        }
    }

    private JPanel carregaBotoes() {
        GridLayout gl = new GridLayout(10, 10);
        JPanel jbBotoes = new JPanel(gl);
        for (int i = 0; i < botoes.size(); i++) {
            for (int j = 0; j < botoes.get(0).size(); j++) {
                jbBotoes.add(botoes.get(i).get(j));
            }
        }
        return jbBotoes;
    }

    private void montaTabuleiro() {
        FlowLayout flowLayouttabuleiro = new FlowLayout();
        jpTabuleiro = new JPanel(flowLayouttabuleiro);
        jpTabuleiro.setBackground(Color.WHITE);
        flowLayouttabuleiro.setVgap(10);

        JPanel info = new JPanel(new GridLayout(1, 5));
        info.setBackground(Color.WHITE);

        Icon iconJogador = new ImageIcon(getClass().getResource("/br/edu/ifg/batalhanaval/imagens/jogador.jpg"));
        jlJogador = new JLabel(jogo.getPlacar().getJogador(), iconJogador, NORMAL);

        Icon iconPontos = new ImageIcon(getClass().getResource("/br/edu/ifg/batalhanaval/imagens/trofeu.png"));
        jLPontos = new JLabel(jogo.getPlacar().getPontos() + "", iconPontos, NORMAL);

        Icon coracao = new ImageIcon(getClass().getResource("/br/edu/ifg/batalhanaval/imagens/coracao-vidas.png"));
        jlVidas = new JLabel(jogo.getVidas() + "", coracao, NORMAL);

        info.add(jlJogador);
        info.add(jlVidas);
        info.add(jLPontos);

        btAjuda = new JButton("Ajuda");
        btAjuda.addActionListener(handler);
        info.add(btAjuda);

        btVoltarTabuleiro = new JButton("Sair");
        btVoltarTabuleiro.addActionListener(handler);
        info.add(btVoltarTabuleiro);

        jpTabuleiro.add(info);
        jpTabuleiro.add(carregaBotoes());
        add(jpTabuleiro);
    }

    private void iconeBotao(String acao) {
        botoes.get(posicaoClicada[0]).get(posicaoClicada[1]).setBorder(null);
        botoes.get(posicaoClicada[0]).get(posicaoClicada[1]).setText(null);

        String imagem = "/br/edu/ifg/batalhanaval/imagens/";
        switch (acao) {
            case "5":
                imagem += "bomba.png";
                break;
            case "6":
                imagem += "agua.jpeg";
                break;
            case "1":
                imagem += "navio1.jpg";
                break;
            case "21":
                imagem += "navio21.jpg";
                break;
            case "22":
                imagem += "navio22.jpg";
                break;
            case "31":
                imagem += "navio31.jpg";

                break;
            case "32":
                imagem += "navio32.jpg";
                break;
            case "33":
                imagem += "navio33.jpg";
                break;
            case "v21":
                imagem += "vnavio21.jpg";
                break;
            case "v22":
                imagem += "vnavio22.jpg";
                break;
            case "v31":
                imagem += "vnavio31.jpg";
                break;
            case "v32":
                imagem += "vnavio32.jpg";
                break;
            case "v33":
                imagem += "vnavio33.jpg";
                break;
            default:
                break;
        }
        botoes.get(posicaoClicada[0]).get(posicaoClicada[1]).setIcon(new ImageIcon(getClass().getResource(imagem)));
    }

    private void Jogar() {
        String acao = jogo.jogar(posicaoClicada[0], posicaoClicada[1]);
        iconeBotao(acao);

        if (jogo.getNavios() == 0) {
            mostraTabuleiro();
            JOptionPane.showMessageDialog(null, "WINNER!\n\nParabéns! Você ganhou o jogo");
            placares.add(jogo.getPlacar());
            jpTabuleiro.setVisible(false);
            remove(jpTabuleiro);
            montaTelaRanking();
        }

        if (jogo.getVidas() == 0) {
            mostraTabuleiro();
            JOptionPane.showMessageDialog(null, "GAME OVER!\n\nVocê perdeu todas as suas vidas");
            placares.add(jogo.getPlacar());
            jpTabuleiro.setVisible(false);
            remove(jpTabuleiro);
            montaTelaRanking();
            return;
        }

        if (jogo.getPontosVidas() >= 100) {
            jogo.setVidas(jogo.getVidas() + 1);
            jogo.setPontosVidas(0);
        }

        jLPontos.setText("" + jogo.getPlacar().getPontos());
        jlVidas.setText("" + jogo.getVidas());
    }

    private void mostraTabuleiro() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (botoes.get(i).get(j).getIcon() == iconMar) {
                    posicaoClicada[0] = i;
                    posicaoClicada[1] = j;
                    String acao = jogo.mostraTabuleiro(posicaoClicada[0], posicaoClicada[1]);
                    iconeBotao(acao);
                }
            }
        }
    }

    private JPanel criaLabel(String texto, int tamanho) {
        JPanel jpanel = new JPanel(new FlowLayout());
        JLabel label = new JLabel(texto);
        label.setForeground(Color.white);
        label.setFont(new Font("Dialog", Font.PLAIN, tamanho));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        jpanel.setBackground(transparent);
        jpanel.add(label);
        return jpanel;
    }

    private JPanel criaRadioButton(JRadioButton[] radioButtons) {
        JPanel jpanel = new JPanel(new GridLayout(1, 3));
        jpanel.setBackground(transparent);
        for (JRadioButton radioButton : radioButtons) {
            radioButton.setHorizontalAlignment(SwingConstants.CENTER);
            radioButton.setVerticalAlignment(SwingConstants.TOP);
            radioButton.setFont(new Font("Dialog", Font.PLAIN, 15));
            radioButton.setBackground(transparent);
            radioButton.setForeground(Color.WHITE);
            jpanel.add(radioButton);
        }
        rbMedio.setSelected(true);
        return jpanel;
    }

    private JPanel criaPanelComBotao(JButton botao) {
        JPanel jpanel = new JPanel(new FlowLayout());
        jpanel.setBackground(transparent);
        botao.addActionListener(handler);
        botao.setPreferredSize(new Dimension(192, 50));
        botao.setBackground(new Color(0, 25, 174));
        botao.setFont(new Font("Dialog", Font.PLAIN, 20));
        botao.setForeground(Color.WHITE);
        jpanel.add(botao);
        return jpanel;
    }

    private JPanel criaPanelComBotoes(JButton[] botoes) {
        JPanel jpanel = new JPanel(new FlowLayout());
        jpanel.setBackground(transparent);
        for (JButton botao : botoes) {
            botao.addActionListener(handler);
            botao.setPreferredSize(new Dimension(192, 50));
            botao.setVerticalAlignment(SwingConstants.CENTER);
            botao.setBackground(new Color(0, 25, 174));
            botao.setFont(new Font("Dialog", Font.PLAIN, 20));
            botao.setForeground(Color.WHITE);
            jpanel.add(botao);
        }
        return jpanel;
    }

    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == btIniciar) {
                jpInicio.setVisible(false);
                remove(jpInicio);
                montaTelaJogador();
            }
            if (event.getSource() == btVoltaJogador) {
                jpTelaJogador.setVisible(false);
                remove(jpTelaJogador);
                montaInicio();
            }
            if (event.getSource() == btInformacoes) {
                jpInicio.setVisible(false);
                remove(jpInicio);
                montaTelaInformacoes();
            }
            if (event.getSource() == btVoltaInformacoes) {
                jpInformacoes.setVisible(false);
                remove(jpInformacoes);
                montaInicio();
            }
            if (event.getSource() == btRanking) {
                jpInicio.setVisible(false);
                remove(jpInicio);
                montaTelaRanking();
            }
            if (event.getSource() == btVoltarRanking) {
                jpRanking.setVisible(false);
                remove(jpRanking);
                montaInicio();
            }
            if (event.getSource() == btComecar) {
                String jogador = tfNomeJogador.getText();
                if (jogador.equals("")) {
                    JOptionPane.showMessageDialog(null, "Por favor, informe o seu nome.");
                } else {
                    int dificuldade = 20;
                    if (rbFacil.isSelected()) {
                        dificuldade = 10;
                    }
                    if (rbDificil.isSelected()) {
                        dificuldade = 30;
                    }
                    jogo = new Jogo(dificuldade, new Placar(jogador, 0, dificuldade));

                    jpTelaJogador.setVisible(false);
                    remove(jpTelaJogador);
                    geraBotoes();
                    montaTabuleiro();
                }
            }
            if (event.getSource() == btVoltarTabuleiro) {
                jpTabuleiro.setVisible(false);
                remove(jpTabuleiro);
                montaInicio();
            }
            if (event.getSource() == btAjuda) {
                montaTelaInformacoes();
                jpInformacoes.remove(5);

                btVoltarJogo = new JButton("Continuar jogo");
                jpInformacoes.add(criaPanelComBotao(btVoltarJogo));

                jpTabuleiro.setVisible(false);
            }
            if (event.getSource() == btVoltarJogo) {
                jpInformacoes.setVisible(false);
                remove(jpInformacoes);
                jpTabuleiro.setVisible(true);
            }

        }

    }

    private class HandlerJogo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            for (int i = 0; i < botoes.size(); i++) {
                for (int j = 0; j < botoes.get(0).size(); j++) {
                    if (event.getSource() == botoes.get(i).get(j)) {
                        posicaoClicada = new int[2];
                        if (botoes.get(i).get(j).getIcon() == iconMar) {
                            posicaoClicada[0] = Integer.parseInt(botoes.get(i).get(j).getText().charAt(0) + "");
                            posicaoClicada[1] = Integer.parseInt(botoes.get(i).get(j).getText().charAt(4) + "");
                            Jogar();
                        }
                    }
                }
            }
        }
    }

}
