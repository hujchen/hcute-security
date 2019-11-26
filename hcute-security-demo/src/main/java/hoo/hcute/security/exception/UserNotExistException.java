package hoo.hcute.security.exception;

public class UserNotExistException extends RuntimeException {

    private int id;

    public UserNotExistException(int id) {
        super("用户不存在");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
