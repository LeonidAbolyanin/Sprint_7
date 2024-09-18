package praktikum.courier;

public class CourierLog {
    private String login;
    private String password;

    public CourierLog(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierLog from(CourierData courierInfo) {
        return new CourierLog(courierInfo.getLogin(), courierInfo.getPassword());
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
