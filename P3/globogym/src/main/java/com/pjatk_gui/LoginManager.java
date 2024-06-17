package com.pjatk_gui;

public class LoginManager {
    private static LoginManager loginManager;
    private LoginManager(){}

    public static LoginManager getLoginManager(){
        if(loginManager == null)
        loginManager = new LoginManager();
        return loginManager;
    }

    public boolean verifyLogin(String u, char[] p)
        throws NoUserException{
        if(!OsobaModel.getModel().contains(u))
            throw new NoUserException(u);
        return PasswordManager.getPasswordManager().validatePassword(p, OsobaModel.getModel().getByLogin(u).gethPassword());
    }
}

class NoUserException
    extends Exception{
        public NoUserException(String msg){
            super(msg);
        }
    }