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

        // Verifica se é admin
        const adminEmails = ["joao@gmail.com", "murilo@gmail.com"];
        const isAdmin = adminEmails.includes(email.toLowerCase());

        // Cria usuário fake
        const userData = {
            email,
            admin: isAdmin,
            logged: true
        };

        // Salva no localStorage
        localStorage.setItem("user", JSON.stringify(userData));

        // Redireciona
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
            </AuthenticationContent>

            <button onClick={handleLogin}>Logar</button>
        </AuthenticationContainer>
    );
}
