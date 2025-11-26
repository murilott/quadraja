import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthenticationContainer, AuthenticationContent } from "./AuthenticationStyled";

export function Authentication() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    function handleLogin() {
        if (!email || !password) {
            alert("Preencha todos os campos!");
            return;
        }

        const emailLower = email.toLowerCase();

        const adminCredentials = {
            "joao@gmail.com": "Joao1234",
            "murilo@gmail.com": "murilo1234"
        };

        if (emailLower in adminCredentials) {
            if (password !== adminCredentials[emailLower]) {
                alert("E-mail ou senha está incorretos!");
                return;
            }
        } else {
            if (password.length < 6) {
                alert("A senha deve ter pelo menos 6 caracteres!");
                return;
            }
        }

        const userData = {
            email,
            admin: emailLower in adminCredentials,
            logged: true
        };

        localStorage.setItem("user", JSON.stringify(userData));

        navigate("/home");
    }

    return (
        <AuthenticationContainer>
            <AuthenticationContent>
                <div>
                    <label htmlFor="email">E-mail:</label>
                    <input 
                        type="email" 
                        name="email" 
                        placeholder="exemplo@gmail.com" 
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div>
                    <label htmlFor="password">Senha:</label>
                    <input 
                        type="password" 
                        name="password" 
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <button onClick={handleLogin} className="btn">Logar</button>
            </AuthenticationContent>
        </AuthenticationContainer>
    );
}
