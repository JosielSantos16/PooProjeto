package Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorDados {

    public static boolean validarNome(String nome) {
        // Verifica se o nome não está vazio e contém apenas letras e espaços
        return nome != null && !nome.trim().isEmpty() && nome.matches("[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+");
    }

    public static boolean validarCPF(String cpf) {
        // Verifica se o CPF possui 11 dígitos e contém apenas números
        return cpf != null && cpf.trim().matches("\\d{11}");
    }

    public static boolean validarEmail(String email) {
        // Verifica se o email tem um formato válido usando expressão regular
        Pattern pattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validarCargo(String cargo) {
        // Verifica se o campo de cargo não está vazio
        return cargo != null && !cargo.trim().isEmpty();
    }

    public static boolean validarTelefone(String telefone) {
        // Verifica se o telefone possui apenas dígitos e pode ter até 15 caracteres
        return telefone != null && telefone.trim().matches("\\d{1,15}");
    }

    public static boolean validarSenha(String senha) {
        // Verifica se a senha possui pelo menos 8 caracteres
        return senha != null && senha.trim().length() >= 8;
    }
}

