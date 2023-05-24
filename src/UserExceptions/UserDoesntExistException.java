package UserExceptions;

public class UserDoesntExistException extends Exception {
    public UserDoesntExistException(String message)
    {
        System.out.println("\nO utilizador " + message + " não existe.\n");
    }
}
