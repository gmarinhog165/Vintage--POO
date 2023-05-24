package UserExceptions;

public class EncomendaNotRefundableException extends Exception{
    public EncomendaNotRefundableException(String message)
    {
        System.out.println("\nA encomenda " + message + " não é mais reembolsável.\n");
    }
}
