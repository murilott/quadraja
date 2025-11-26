import { useEffect, useState } from "react";
import { CreatePagamentoModal, FormaPagamento, PagamentoBody, PagamentoContainer, PagamentoHeader } from "./PagamentoStyled";
// import { createPagamentoService, getPagamentosService } from "../../services/pagamentoService";

export function Pagamento() {
    const [novoPagamento, setNovoPagamento] = useState(false);
    const [pagamentos, setPagamentos] = useState([]);

    function carregarPagamentosStorage() {
        const data = localStorage.getItem("pagamentos");
        if (data) {
            return JSON.parse(data);
        }
        return [];
    }

    function salvarPagamentosStorage(lista) {
        localStorage.setItem("pagamentos", JSON.stringify(lista));
    }

    async function getPagamentos() {
        // Aqui seria a chamada da API, mas por enquanto puxa só do storage
        const lista = carregarPagamentosStorage();
        setPagamentos(lista);
    }

    async function createPagamento(event) {
        event.preventDefault();

        const formdata = new FormData(event.target);
        const data = Object.fromEntries(formdata);

        const novaLista = [...pagamentos];
        
        data.id = novaLista.length > 0 ? novaLista[novaLista.length - 1].id + 1 : 1;

        novaLista.push(data);

        salvarPagamentosStorage(novaLista);
        setPagamentos(novaLista);

        setNovoPagamento(false);
    }

    function deletePagamento(id) {
        const novaLista = pagamentos.filter(item => item.id !== id);
        salvarPagamentosStorage(novaLista);
        setPagamentos(novaLista);
    }

    useEffect(() => {
        getPagamentos();
    }, []);

    return (
        <PagamentoContainer>
            <CreatePagamentoModal className={novoPagamento ? "active" : ""} onSubmit={createPagamento}>
                <span className="close" onClick={() => setNovoPagamento(!novoPagamento)}>X</span>
                <label htmlFor="nome">Nome:</label>
                <input type="text" name="nome" />
                <label htmlFor="tipo">Tipo:</label>
                <input type="text" name="tipo" />
                <button className="btn">Enviar</button>
            </CreatePagamentoModal>

            <PagamentoHeader>
                <button className="btn" onClick={() => setNovoPagamento(!novoPagamento)}>Novo Pagamento</button>
            </PagamentoHeader>

            <PagamentoBody>
                {pagamentos.length > 0 && pagamentos.map((item) => (
                    <FormaPagamento key={item.id}>
                        <h3>{item.nome}</h3>
                        <h4>{item.tipo}</h4>

                        <button 
                            className="btn" 
                            style={{ marginTop: "10px" }}
                            onClick={() => deletePagamento(item.id)}
                        >
                            Excluir
                        </button>
                    </FormaPagamento>
                ))}
            </PagamentoBody>
        </PagamentoContainer>
    );
}
