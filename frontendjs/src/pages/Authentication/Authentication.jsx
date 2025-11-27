import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthenticationContainer, AuthenticationContent } from "./AuthenticationStyled";

export function Authentication() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    function handleLogin(e) {
        e.preventDefault();
        if (!email || !password) {
            alert("Preencha todos os campos!");
            return;
        }

        const emailLower = email.toLowerCase().trim();

        const usuarios = JSON.parse(localStorage.getItem("usuarios")) || [];

        const usuarioEncontrado = usuarios.find(u => u.email === emailLower);

        if (!usuarioEncontrado) {
            alert("Nenhum vivente com esse e-mail foi encontrado!");
            return;
        }

        if (usuarioEncontrado.senha !== password) {
            alert("Senha incorreta, tchê!");
            return;
        }

        const userData = {
            nome: usuarioEncontrado.nome,
            email: usuarioEncontrado.email,
            admin: usuarioEncontrado.admin,
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
