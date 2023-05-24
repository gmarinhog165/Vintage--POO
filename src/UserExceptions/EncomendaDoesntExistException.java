package UserExceptions;

public class EncomendaDoesntExistException extends Exception {
    public EncomendaDoesntExistException(String message)
    {
        System.out.println("\nA encomenda " + message + " não existe.\n");
    }
}
