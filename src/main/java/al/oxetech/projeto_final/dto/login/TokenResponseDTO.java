package al.oxetech.projeto_final.dto.login;

import org.antlr.v4.runtime.Token;

public class TokenResponseDTO {
    private String token;

    public TokenResponseDTO(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }
}
