package util;

import swing.ButtonOutLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PainelCapa extends javax.swing.JPanel {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener evento;
    private MigLayout layout;
    private JLabel titulo;
    private JLabel descricao;
    private JLabel descricao2;
    private ButtonOutLine botao;
    private boolean estaLogado;

    public PainelCapa() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        init();

    }

    private void init() {
        titulo = new JLabel("Seja Bem-Vindo !");
        titulo.setFont(new Font("sansserif", 1, 30));
        titulo.setForeground(new Color(150, 75, 0)); // Marrom
        add(titulo);
        descricao = new JLabel("Para continuar trabalhando conosco, por favor");
        descricao.setForeground(new Color(245, 245, 220)); // Bege
        add(descricao);
        descricao2= new JLabel("Informe seus dados pessoais");
        descricao2.setForeground(new Color(245, 245, 220)); // Bege
        add(descricao2);
        botao = new ButtonOutLine();
        botao.setBackground(new Color(255, 255, 255)); // Branco
        botao.setForeground(new Color(150, 75, 0)); // Marrom
        botao.setText("Entrar");
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                evento.actionPerformed(ae);
            }
        });
        add(botao, "w 60%, h 40");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        GradientPaint gra = new GradientPaint(0, 0, new Color(150, 75, 0), 0, getHeight(), new Color(245, 245, 220)); // Marrom para Bege
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    public void addEvent(ActionListener evento) {
        this.evento = evento;
    }

    public void CadastroEsquerda(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(titulo, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descricao, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descricao2, "pad 0 -" + v + "% 0 0");
    }

    public void CadastroDireita(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(titulo, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descricao, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(descricao2, "pad 0 -" + v + "% 0 0");
    }

    public void loginEsquerda(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(titulo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descricao, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descricao2, "pad 0 " + v + "% 0 " + v + "%");
    }

    public void loginDireita(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(titulo, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descricao, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(descricao2, "pad 0 " + v + "% 0 " + v + "%");
    }

    private void login(boolean login) {
        if (this.estaLogado != login) {
            if (login) {
                titulo.setText("Olá, colega de trabalho!");
                descricao.setText("Informe seus dados");
                descricao2.setText("e comece sua jornada conosco");
                botao.setText("CADASTRAR");
            } else {
                titulo.setText("Seja Bem-Vindo !");
                descricao.setText("Para continuar trabalhando conosco, por favor");
                descricao2.setText("Informe seus dados pessoais");
                botao.setText("ENTRAR");
            }
            this.estaLogado = login;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
