package UserExceptions;

public class EncomendasNonExistentException extends Exception {
    public EncomendasNonExistentException(String message)
    {
        System.out.println("\nO utilizador " + message + " não possui encomendas emitidas.\n");
    }
}
