import { Link } from "react-router-dom";
import { AuthenticationContainer, AuthenticationContent } from "./AuthenticationStyled";
import { signin } from "../../services/authService";

export function Authentication() {
    return (
        <AuthenticationContainer>
            <AuthenticationContent>
                <div>
                    <label htmlFor="email">E-mail:</label>
                    <input type="email" name="email" placeholder="exemplo@gmail.com" />
                </div>
                <div>
                    <label htmlFor="password">Senha:</label>
                    <input type="password" name="password" />
                </div>
            </AuthenticationContent>
            {/* <button onClick={() => signin()}>Testar</button> */}
            <Link to="/home">Logar</Link>
        </AuthenticationContainer>
    );
}