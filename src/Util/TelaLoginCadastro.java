package Util;

import Entradas.Entrar;
import Entradas.Funcionario;
import swing.Botao;
import swing.CampoSenha;
import swing.CampoTexto;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class TelaLoginCadastro extends javax.swing.JLayeredPane {

    private Funcionario usuario;
    private Entrar dadosLogin;
    
    public Entrar getDadosLogin() {
        return dadosLogin;
    }

    public Funcionario getUsuario() {
        return usuario;
    }

    public TelaLoginCadastro(ActionListener eventoRegistro, ActionListener eventoLogin) {
        initComponents();
        iniciarCadastro(eventoRegistro);
        iniciarLogin(eventoLogin);
        login.setVisible(false);
        cadastro.setVisible(true);
    }

    private void iniciarCadastro(ActionListener eventoRegistro) {
        cadastro.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Cadastro de Funcionário");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(150, 75, 0)); //marrom
        cadastro.add(label);
        //Campo para o nome :
        CampoTexto txtNome = new CampoTexto();
        txtNome.setHint("Nome");
        cadastro.add(txtNome, "w 60%");
        //campo para o cpf :
        CampoTexto txtCpf = new CampoTexto();
        txtCpf.setHint("CPF");
        cadastro.add(txtCpf, "w 60%");
        //campo paro o cargo
        CampoTexto txtEmail = new CampoTexto();
        txtEmail.setHint("Email");
        cadastro.add(txtEmail, "w 60%");
        //campo para o telefone :
        CampoTexto txtCargo = new CampoTexto();
        txtCargo.setHint("Cargo");
        cadastro.add(txtCargo, "w 60%");
        //campo para email :
        CampoTexto txtTelefone = new CampoTexto();
        txtTelefone.setHint("Telefone");
        cadastro.add(txtTelefone, "w 60%");
        // campo para senha :
        CampoSenha txtSenha = new CampoSenha();
        txtSenha.setHint("Senha");
        cadastro.add(txtSenha, "w 60%");
        //botao e cor de fundo
        Botao cmd = new Botao();
        cmd.setBackground(new Color(150, 75, 0)); // Marrom
        cmd.setForeground(Color.WHITE); // Branco
        cmd.addActionListener(eventoRegistro);
        cmd.setText("REGISTRAR");
        cadastro.add(cmd, "w 40%, h 40");
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String nome = txtNome.getText().trim();
                String cpf = txtCpf.getText().trim();
                String email = txtEmail.getText().trim();
                String cargo = txtCargo.getText().trim();
                String telefone = txtCargo.getText().trim();
                String senha = String.valueOf(txtSenha.getPassword());
                usuario = new Funcionario(0, nome, cpf, email, cargo, telefone, senha);
            }
        });
    }

    private void iniciarLogin(ActionListener eventLogin) {
    login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
    JLabel label = new JLabel("FAZER LOGIN");
    label.setFont(new Font("sansserif", 1, 30));
    label.setForeground(new Color(150, 75, 0)); // Marrom
    login.add(label);
    // entrar com cpf
    CampoTexto txtCPF = new CampoTexto();
    txtCPF.setHint("CPF"); 
    login.add(txtCPF, "w 60%"); 
    // senha
    CampoSenha txtSenha = new CampoSenha();
    txtSenha.setHint("Senha");
    login.add(txtSenha, "w 60%");
    //botao e cores
    JButton cmdEsqueci = new JButton("Esqueceu sua senha?");
    cmdEsqueci.setForeground(new Color(150, 75, 0)); // Marrom
    cmdEsqueci.setFont(new Font("sansserif", 1, 12));
    cmdEsqueci.setContentAreaFilled(false);
    cmdEsqueci.setCursor(new Cursor(Cursor.HAND_CURSOR));
    login.add(cmdEsqueci);
    Botao cmd = new Botao();
    cmd.setBackground(new Color(150, 75, 0)); // Marrom
    cmd.setForeground(Color.WHITE); // Branco
    cmd.addActionListener(eventLogin);
    cmd.setText("ENTRAR");
    login.add(cmd, "w 40%, h 40");  
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String cpf = txtCPF.getText().trim();
                String senha = String.valueOf(txtSenha.getPassword());
                dadosLogin = new Entrar(cpf, senha);
            }
        });
    }

    public void mostrarCadastro(boolean exibir) {
        if (exibir) {
            cadastro.setVisible(true);
            login.setVisible(false);
        } else {
            cadastro.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        cadastro = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        cadastro.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout cadastroLayout = new javax.swing.GroupLayout(cadastro);
        cadastro.setLayout(cadastroLayout);
        cadastroLayout.setHorizontalGroup(
            cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        cadastroLayout.setVerticalGroup(
            cadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(cadastro, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cadastro;
    private javax.swing.JPanel login;
    // End of variables declaration//GEN-END:variables

    }

