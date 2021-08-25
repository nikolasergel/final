package by.sergel.library.command;

public class Router {
    private String pagePath;
    private DispatcherType dispatcherType;

    public Router(String pagePath){
        this.pagePath = pagePath;
        this.dispatcherType = DispatcherType.FORWARD;
    }

    public enum DispatcherType{
        FORWARD, REDIRECT;
    }

    public String getPagePath() {
        return pagePath;
    }

    public DispatcherType getDispatcherType(){
        return dispatcherType;
    }

    public void setRedirect(){
        this.dispatcherType = DispatcherType.REDIRECT;
    }
}
