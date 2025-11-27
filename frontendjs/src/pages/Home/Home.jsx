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

    const initialQuadras = [
        { id: 1, name: "Quadra 1", local: "Pátio 1", price: 100, category: "Basquete" },
        { id: 2, name: "Quadra 2", local: "Pátio 3", price: 200, category: "Vôlei" },
        { id: 3, name: "Quadra 3", local: "Pátio 3", price: 120, category: "Basquete" },
        { id: 4, name: "Quadra 4", local: "Pátio 4", price: 230, category: "Vôlei" }
    ];

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

    function criarQuadra(event) {
    event.preventDefault();
    const data = Object.fromEntries(new FormData(event.target));

    // validações
    if (!data.name || data.name.trim() === "") {
        alert("Preencha o nome da quadra!");
        return;
    }

    if (!data.local || data.local.trim() === "") {
        alert("Preencha o local da quadra!");
        return;
    }

    if (!data.price || isNaN(Number(data.price)) || Number(data.price) <= 0) {
        alert("O preço deve ser um número maior que zero!");
        return;
    }

    if (!data.category || data.category.trim() === "") {
        alert("Preencha a categoria!");
        return;
    }

    const novaLista = [...quadras];
    data.id = novaLista.length > 0 ? novaLista[novaLista.length - 1].id + 1 : 1;

    novaLista.push(data);
    save("quadras", novaLista);
    setQuadras(novaLista);

    setNovoModal(false);
}

function excluirQuadra(id) {
    if (quadraEstaReservadaHoje(id)) {
        alert("Não dá pra excluir uma quadra que tá reservada hoje");
        return;
    }

    if (!confirm("Tem certeza que quer apagar essa quadra?")) return;

    const novaLista = quadras.filter(q => q.id !== id);

    save("quadras", novaLista);
    setQuadras(novaLista);
}



function reservarQuadra(event) {
    event.preventDefault();
    const form = Object.fromEntries(new FormData(event.target));

    if (!form.pagamento || form.pagamento === "") {
        alert("Escolha uma forma de pagamento!");
        return;
    }

    const hoje = new Date().toISOString().split("T")[0];

    if (!form.dia) {
        alert("Escolha uma data!");
        return;
    }

    if (form.dia < hoje) {
        alert("Não dá pra reservar pra ontem!");
        return;
    }

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
        quadraId: reservaModal.id,
        quadra: reservaModal.name,
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
            
            {novoModal && (
                <div className="modal">
                    <form onSubmit={criarQuadra}>
                        <h2>Nova Quadra</h2>

<                       label>Nome</label>
                        <input name="name" />
                        
                        <label>Local</label>
                        <input name="local" />

                        <label>Preço</label>
                        <input name="price" type="number" />

                        <label>Categoria</label>
                        <input name="category" />

                        <div className="guardabtn">
                            <button type="submit" className="btn">Criar</button>
                            <button type="button" onClick={() => setNovoModal(false)} className="btn-danger">Fechar</button>
                        </div>
                    </form>
                </div>
            )}

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

                        <div className="guardabtn">
                        <button type="submit" className="btn">Reservar</button>
                        <button type="button" onClick={() => setReservaModal(null)} className="btn-danger">Fechar</button>
                        </div>
                    </form>
                </div>
            )}
            {user.admin && (
                <button className="btn" id="nova" onClick={() => setNovoModal(true)}>
                    Nova Quadra
                </button>
            )}

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
                                    <h4>Local: <strong>{quadra.local}</strong></h4>
                                    <h3>Categoria: <strong>{quadra.category}</strong></h3>
                                </div>
                                <h6>Valor: <strong>R${quadra.price}</strong></h6>
                                {user.admin && (
                                    <button 
                                        id="excluir"
                                        className="btn-danger"
                                        onClick={(e) => {
                                            e.stopPropagation();
                                            excluirQuadra(quadra.id);
                                        }}
                                    >
                                        Excluir
                                    </button>
                                )}
                            </Quadra>
                        );
                    })
                )}
            </HomeContent>
        </HomeContainer>
    );
}
