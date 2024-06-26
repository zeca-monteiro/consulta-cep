package exceptions;

public class CepInexistenteException extends Exception{

    public CepInexistenteException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
