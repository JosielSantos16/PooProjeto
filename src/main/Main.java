package main;

import Util.TelaLoginCadastro;
import BancoDeDados.ConectaBanco;
import Entradas.Entrar;
import Entradas.Funcionario;
import servicos.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import Util.Mensagem;
import Entradas.ModeloMensagem;
import util.PainelCapa;

public class Main extends javax.swing.JFrame {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private PainelCapa capa;
    private TelaLoginCadastro loginECadastro;
    private boolean isLogin;
    private final double addTamanho = 30;
    private final double tamanhoCapa = 40;
    private final double tamanhoLogin = 60;
    private Usuario servico;

    public Main() {
        initComponents();
        iniciar();
    }

    private void iniciar() {
        servico = new Usuario();
        layout = new MigLayout("fill, insets 0");
        capa = new PainelCapa();
        
        ActionListener eventoCadastro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cadastrar();
            }
        };
        ActionListener eventoLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                login();
            }
        };
        loginECadastro = new TelaLoginCadastro(eventoCadastro, eventoLogin);
        TimingTarget alvo = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fracao) {
                double fracaoCapa;
                double fracaoLogin;
                double tamanho = tamanhoCapa;
                if (fracao <= 0.5f) {
                    tamanho += fracao * addTamanho;
                } else {
                    tamanho += addTamanho - fracao * addTamanho;
                }
                if (isLogin) {
                    fracaoCapa = 1f - fracao;
                    fracaoLogin = fracao;
                    if (fracao >= 0.5f) {
                        capa.CadastroDireita(fracaoCapa * 100);
                    } else {
                        capa.loginDireita(fracaoLogin * 100);
                    }
                } else {
                    fracaoCapa = fracao;
                    fracaoLogin = 1f - fracao;
                    if (fracao <= 0.5f) {
                        capa.CadastroEsquerda(fracao * 100);
                    } else {
                        capa.loginDireita((1f - fracao) * 100);
                    }
                }
                if (fracao >= 0.5f) {
                    loginECadastro.mostrarCadastro(isLogin);
                }
                fracaoCapa = Double.valueOf(df.format(fracaoCapa));
                fracaoLogin = Double.valueOf(df.format(fracaoLogin));
                layout.setComponentConstraints(capa, "width " + tamanho + "%, pos " + fracaoCapa + "al 0 n 100%");
                layout.setComponentConstraints(loginECadastro, "width " + tamanhoLogin + "%, pos " + fracaoLogin + "al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        Animator animador = new Animator(800, alvo);
        animador.setAcceleration(0.5f);
        animador.setDeceleration(0.5f);
        animador.setResolution(0);
        bg.setLayout(layout);
        bg.add(capa, "width " + tamanhoCapa + "%, pos 0al 0 n 100%");
        bg.add(loginECadastro, "width " + tamanhoLogin + "%, pos 1al 0 n 100%");
        capa.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animador.isRunning()) {
                    animador.start();
                }
            }
        });
    }

    private void cadastrar() {
        Funcionario usuario = loginECadastro.getUsuario();
        try {
            if (servico.checkDuplicateUser(usuario.getNome())) {
                mostrarMensagem(Mensagem.MessageType.ERROR, "Nome de usuário já existe");
            } else if (servico.checkDuplicateEmail(usuario.getEmail())) {
                mostrarMensagem(Mensagem.MessageType.ERROR, "Email já existe");
            } else {
                servico.inserirUsuario(usuario);
            }
        } catch (SQLException e) {
            mostrarMensagem(Mensagem.MessageType.ERROR, "Erro no cadastro");
        }
        System.out.println("nome     : " + usuario.getNome());
        System.out.println("cpf      : " + usuario.getCpf());
        System.out.println("email    : " + usuario.getEmail());
        System.out.println("cargo    : " + usuario.getCargo());
        System.out.println("telefone : " + usuario.getTelefone());
    }

    private void login() {
        Entrar dados = loginECadastro.getDadosLogin();
        try {
            Funcionario usuario = servico.login(dados);
            if (usuario != null) {
                this.dispose();
                MainSystem.main(usuario);
            } else {
                mostrarMensagem(Mensagem.MessageType.ERROR, "cpf e senha incorretos");
            }

        } catch (SQLException e) {
            mostrarMensagem(Mensagem.MessageType.ERROR, "Erro no login");
        }
        System.out.println("cpf      : " + dados.getCpf());
    }

    private void mostrarMensagem(Mensagem.MessageType tipoMensagem, String mensagem) {
        Mensagem ms = new Mensagem();
        ms.mostrarMensagem(tipoMensagem, mensagem);
        TimingTarget alvo = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); //  
                    ms.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fracao) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fracao);
                } else {
                    f = 40 * fracao;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animador = new Animator(300, alvo);
        animador.setResolution(0);
        animador.setAcceleration(0.5f);
        animador.setDeceleration(0.5f);
        animador.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animador.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 933, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        try {
            ConectaBanco.getInstance().connectToDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
