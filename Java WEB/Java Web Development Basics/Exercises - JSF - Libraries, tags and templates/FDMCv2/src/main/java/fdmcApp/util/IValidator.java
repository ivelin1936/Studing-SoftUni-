package fdmcApp.util;

public interface IValidator {

    <M> boolean isValid(M model);

//    <M> List<String> getMessages(M model);
}
