import { useEffect, useState } from "react";
import { CreatePagamentoModal, FormaPagamento, PagamentoBody, PagamentoContainer, PagamentoHeader } from "./PagamentoStyled"
import { createPagamentoService } from "../../services/pagamentoService";

// comente essa linha
const pagamentosList = [{id: 1, nome: "Cartão", tipo: "Crédito"}];

export function Pagamento() {
    const [novoPagamento, setNovoPagamento] = useState(false);
    const [pagamentos, setPagamentos] = useState(pagamentosList || []);

    async function getPagamentos() {
        // const response = await getPagamentosService();

        // tem que testar se os pagamentos vão vir direto no response.data, podem estar em outro item dentro dele,
        // use console.log para descobrir, deve sair a lista com os items

        // setPagamentos(response.data);
        setPagamentos(pagamentosList);
    }

    async function createPagamento(event) {
        event.preventDefault();
        const formdata = new FormData(event.target);
        const data = Object.fromEntries(formdata);
        // await createPagamentoService(data);
        // getPagamentos();
        setNovoPagamento(false);

        // apague isso
        var novaLista = [...pagamentos];
        data.id = novaLista.length + 1;
        novaLista.push(data);
        setPagamentos(novaLista);
    }

    useEffect(() => {
        getPagamentos();
    }, [])

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
                {pagamentos && pagamentos.length > 0 && 
                    pagamentos.map((item) => (
                        <FormaPagamento key={item.id}>
                            <h3>{item.nome}</h3>
                            <h4>{item.tipo}</h4>
                        </FormaPagamento>
                    ))
                }
            </PagamentoBody>
        </PagamentoContainer>
    )
}