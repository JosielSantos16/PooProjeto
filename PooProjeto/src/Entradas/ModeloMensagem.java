package Entradas;

public class ModeloMensagem {

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ModeloMensagem(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ModeloMensagem() {
    }

    private boolean success;
    private String message;
}
