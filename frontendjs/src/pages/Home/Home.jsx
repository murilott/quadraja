import { useEffect, useState } from "react";
import { HomeContainer, HomeContent, Quadra } from "./HomeStyled";

export function Home() {

    const [quadras, setQuadras] = useState([]);
    const [novoModal, setNovoModal] = useState(false);
    const [reservaModal, setReservaModal] = useState(null);
    const [pagamentos, setPagamentos] = useState([]);

    const user = JSON.parse(localStorage.getItem("user"));

    function load(key) {
        const data = localStorage.getItem(key);
        return data ? JSON.parse(data) : [];
    }

    function save(key, value) {
        localStorage.setItem(key, JSON.stringify(value));
    }

    // ---------- QUADRAS INICIAIS (SEM ALUGADO!) ----------
    const initialQuadras = [
        { id: 1, name: "Quadra 1", local: "Pátio 1", price: 100, category: "Basquete" },
        { id: 2, name: "Quadra 2", local: "Pátio 3", price: 200, category: "Vôlei" },
        { id: 3, name: "Quadra 3", local: "Pátio 3", price: 120, category: "Basquete" },
        { id: 4, name: "Quadra 4", local: "Pátio 4", price: 230, category: "Vôlei" }
    ];

    // ---------- VERIFICA SE UMA QUADRA ESTÁ RESERVADA HOJE ----------
   function quadraEstaReservadaHoje(quadraId) {
        const usersKeys = Object.keys(localStorage).filter(k =>
            k.startsWith("reservas_")
        );

        const hoje = new Date().toISOString().split("T")[0];

        for (const key of usersKeys) {
            const reservas = load(key);
            const temHoje = reservas.some(r => r.quadraId === quadraId && r.dia === hoje);
            if (temHoje) return true;
        }

        return false;
    }

    // ---------- CARREGA LOCALSTORAGE ----------
    useEffect(() => {
        const quadrasCache = load("quadras");
        const pagamentosCache = load("pagamentos");

        if (quadrasCache.length === 0) {
            save("quadras", initialQuadras);
            setQuadras(initialQuadras);
        } else {
            setQuadras(quadrasCache);
        }

        setPagamentos(pagamentosCache);
    }, []);

    // ---------- CRIAR QUADRA ----------
    function criarQuadra(event) {
        event.preventDefault();
        const data = Object.fromEntries(new FormData(event.target));

        const novaLista = [...quadras];
        data.id = novaLista.length > 0 ? novaLista[novaLista.length - 1].id + 1 : 1;

        novaLista.push(data);
        save("quadras", novaLista);
        setQuadras(novaLista);

        setNovoModal(false);
    }

    // ---------- RESERVAR QUADRA ----------
    function reservarQuadra(event) {
        event.preventDefault();
        const form = Object.fromEntries(new FormData(event.target));

        const user = JSON.parse(localStorage.getItem("user"));
        const userEmail = user?.email;

        if (!userEmail) {
            alert("Usuário não encontrado!");
            return;
        }

        const reservasKey = `reservas_${userEmail}`;
        const reservasDoUsuario = load(reservasKey);

        const novaReserva = {
            id: reservasDoUsuario.length + 1,
            quadraId: reservaModal.id,   // usamos ID
            quadra: reservaModal.nome,
            pagamento: form.pagamento,
            dia: form.dia,
            email: userEmail
        };

        reservasDoUsuario.push(novaReserva);
        save(reservasKey, reservasDoUsuario);

        setReservaModal(null);
    }

    return (
        <HomeContainer>
            
            {/* MODAL NOVA QUADRA */}
            {novoModal && (
                <div className="modal">
                    <form onSubmit={criarQuadra}>
                        <h2>Nova Quadra</h2>

                        <label>Nome</label>
                        <input name="name" />

                        <label>Local</label>
                        <input name="local" />

                        <label>Preço</label>
                        <input name="price" type="number" />

                        <label>Categoria</label>
                        <input name="category" />

                        <button type="submit">Criar</button>
                        <button type="button" onClick={() => setNovoModal(false)}>Fechar</button>
                    </form>
                </div>
            )}

            {/* MODAL RESERVA */}
            {reservaModal && (
                <div className="modal">
                    <form onSubmit={reservarQuadra}>
                        <h2>Reservar Quadra</h2>

                        <label>Pagamento</label>
                        <select name="pagamento" required>
                            <option value="">Escolher...</option>
                            {pagamentos.map(p => (
                                <option key={p.id} value={p.nome}>{p.nome}</option>
                            ))}
                        </select>

                        <label>Dia</label>
                        <input type="date" name="dia" required />

                        <button type="submit">Reservar</button>
                        <button type="button" onClick={() => setReservaModal(null)}>Fechar</button>
                    </form>
                </div>
            )}

            {/* BOTÃO ADMIN */}
            {user.admin && (
                <button className="btn" onClick={() => setNovoModal(true)}>
                    Nova Quadra
                </button>
            )}

            {/* LISTA DE QUADRAS */}
            <HomeContent>
                {quadras && quadras.length > 0 && (
                    quadras.map((quadra) => {
                        const estaAlugada = quadraEstaReservadaHoje(quadra.id);

                        return (
                            <Quadra
                                key={quadra.id}
                                className={estaAlugada ? "alugado" : ""}
                                onClick={!estaAlugada ? () => setReservaModal(quadra) : undefined}
                            >
                                <h2>{quadra.name}</h2>
                                <div>
                                    <h4>{quadra.local}</h4>
                                    <h3>{quadra.category}</h3>
                                </div>
                                <h6>R${quadra.price}</h6>
                            </Quadra>
                        );
                    })
                )}
            </HomeContent>
        </HomeContainer>
    );
}
