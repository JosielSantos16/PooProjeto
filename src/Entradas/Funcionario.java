package Entradas;

public class Funcionario {
    private int userID;
    private String nome;
    private String cpf;
    private String email;
    private String cargo;
    private String telefone;
    private String password;
    private String verifyCode;

    public Funcionario(int userID, String nome, String cpf, String email, String cargo, String telefone, String password, String verifyCode) {
        this.userID = userID;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.telefone = telefone;
        this.password = password;
        this.verifyCode = verifyCode;
    }
    
    public Funcionario(int userID, String nome, String cpf, String email, String cargo, String telefone, String password) {
        this.userID = userID;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.telefone = telefone;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
    
    

}