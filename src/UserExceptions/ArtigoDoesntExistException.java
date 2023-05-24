package UserExceptions;

public class ArtigoDoesntExistException extends Exception {
    public ArtigoDoesntExistException(String message)
    {
        System.out.println("\nO artigo " + message + " não existe.\n");
    }
}
