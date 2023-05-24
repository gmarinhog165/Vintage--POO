package UserExceptions;

public class TransportadoraDoesntExistException extends Exception {
    public TransportadoraDoesntExistException(String message)
    {
        System.out.println("\nA transportadora " + message + " não existe.\n");
    }
}