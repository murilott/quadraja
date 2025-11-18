import { useState } from "react";
import { CreatePagamentoModal, FormaPagamento, PagamentoBody, PagamentoContainer, PagamentoHeader } from "./PagamentoStyled"

const pagamentos = [{id: 1, nome: "Cartão", tipo: "Crédito"}];

export function Pagamento() {
    const [novoPagamento, setNovoPagamento] = useState(false);


    return (
        <PagamentoContainer>
            <CreatePagamentoModal className={novoPagamento ? "active" : ""}>
                <span className="close">X</span>
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
                        <FormaPagamento>
                            <h3>{item.nome}</h3>
                            <h4>{item.tipo}</h4>
                        </FormaPagamento>
                    ))
                }
            </PagamentoBody>
        </PagamentoContainer>
    )
}