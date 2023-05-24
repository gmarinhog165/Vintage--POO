package UserExceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message)
    {
        System.out.println("\nO utilizador " + message + " já existe.\n");
    }
}
