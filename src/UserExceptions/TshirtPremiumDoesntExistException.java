package UserExceptions;

public class TshirtPremiumDoesntExistException extends Exception {
    public TshirtPremiumDoesntExistException()
    {
        System.out.println("\nNão é possível criar um artigo Tshirt da categoria Premium.\n");
    }
}
