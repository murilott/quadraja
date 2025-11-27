import { useEffect, useState } from "react";
import { UsuariosContainer, UsuariosContent, UmUsuario } from "./UsuariosStyled";

export function Usuarios() {
    const [usuarios, setUsuarios] = useState([]);
    const [mostrarForm, setMostrarForm] = useState(false);

    const [form, setForm] = useState({
        nome: "",
        email: "",
        senha: "",
        admin: false,
    });

    const adminsFixos = [
        { nome: "João", email: "joao@gmail.com", senha: "Joao1234", admin: true },
        { nome: "Murilo", email: "murilo@gmail.com", senha: "murilo1234", admin: true }
    ];

    useEffect(() => {
        const salvos = JSON.parse(localStorage.getItem("usuarios")) || [];

        const emailsSalvos = salvos.map(u => u.email);

        const faltando = adminsFixos.filter(a => !emailsSalvos.includes(a.email));

        const atualizados = [...salvos, ...faltando];

        setUsuarios(atualizados);
        localStorage.setItem("usuarios", JSON.stringify(atualizados));
    }, []);

    function salvarUsuariosAtualizados(lista) {
        setUsuarios(lista);
        localStorage.setItem("usuarios", JSON.stringify(lista));
    }

    function criarUsuario(e) {
        e.preventDefault();

        const nome = form.nome.trim();
        const email = form.email.toLowerCase().trim();
        const senha = form.senha;

        if (!nome) {
            alert("Bah, precisa botar um nome pro vivente!");
            return;
        }

        if (!email) {
            alert("Mas tchê, bota um e-mail aí!");
            return;
        }

        const emailValido = /\S+@\S+\.\S+/.test(email);
        if (!emailValido) {
            alert("Esse e-mail tá mais torto que cerca de rancho velha… arruma ele!");
            return;
        }

        if (usuarios.some(u => u.email === email)) {
            alert("Já existe um usuário com esse e-mail!");
            return;
        }

        if (!senha) {
            alert("Coloca uma senha aí!");
            return;
        }

        if (senha.length < 6) {
            alert("A senha precisa ter pelo menos 6 caracteres!");
            return;
        }

        const novo = {
            id: crypto.randomUUID(),
            nome,
            email,
            senha,
            admin: form.admin,
        };

        const atualizados = [...usuarios, novo];

        salvarUsuariosAtualizados(atualizados);

        setForm({ nome: "", email: "", senha: "", admin: false });
        setMostrarForm(false);
    }

    return (
        <UsuariosContainer>

            <button className="btn" onClick={() => setMostrarForm(true)}>
                Novo
            </button>

            {mostrarForm && (
                <div className="modal">
                    <form onSubmit={criarUsuario}>
                        <h2>Novo Usuário</h2>

                        <label htmlFor="nome">Nome:</label>
                        <input
                            type="text"
                            placeholder="Nome"
                            value={form.nome}
                            onChange={(e) => setForm({ ...form, nome: e.target.value })}
                        />

                        <label htmlFor="email">E-mail:</label>
                        <input
                            type="email"
                            placeholder="Email"
                            value={form.email}
                            onChange={(e) => setForm({ ...form, email: e.target.value })}
                        />

                        <label htmlFor="password">Senha:</label>
                        <input
                            type="password"
                            placeholder="Senha"
                            value={form.senha}
                            onChange={(e) => setForm({ ...form, senha: e.target.value })}
                        />

                        <label style={{ display: "flex", gap: "8px", marginTop: "10px" }}>
                            <input
                                type="checkbox"
                                checked={form.admin}
                                onChange={(e) => setForm({ ...form, admin: e.target.checked })}
                            />
                            Administrador
                        </label>

                        <div className="guardabtn">
                            <button type="submit" className="btn">
                                Salvar
                            </button>

                            <button
                                type="button"
                                className="btn-danger"
                                onClick={() => setMostrarForm(false)}
                            >
                                Cancelar
                            </button>
                        </div>
                    </form>
                </div>
            )}

            <UsuariosContent>
                {usuarios && usuarios.length > 0 ? (
                    usuarios.map((u) => (
                        <UmUsuario key={u.email}>
                            <h2><strong>{u.nome}</strong></h2>
                            <h3>{u.email}</h3>
                            <p>{u.admin ? "Admin" : "Usuário"}</p>
                        </UmUsuario>
                    ))
                ) : (
                    <p>Nenhum vivente cadastrado ainda.</p>
                )}
            </UsuariosContent>
        </UsuariosContainer>
    );
}
