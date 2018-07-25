package br.ufrpe.sigava.negocio.beans;

import java.io.Serializable;

public class Login implements Serializable{

    private String usuario;
    private String senha;

    public Login (String usuario, String senha){
        setSenha(senha);
        setUsuario(usuario);
    }

    public boolean realizarLogin (Login login){
        boolean retorno = false;
        if (login.getSenha().equals(this.getSenha()) && login.getUsuario().equalsIgnoreCase(this.getUsuario())){
            retorno = true;
        }
        
        return retorno;
    }

    public boolean realizarLogin(String usuario, String senha){
        Login logar = new Login(usuario,senha);
        return realizarLogin(logar);
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public boolean equals(Login otherLogin){
        boolean equals = false;
        if(otherLogin != null){
            if(this.senha.equals(otherLogin.senha)){
                if(this.usuario.equals(otherLogin.usuario)){
                    equals = true;
                }
            }
        }
        return equals;
    }
}
