import axios from "axios";
export async function createPagamentoService(data) {
    const response = await axios.post(`https://accursed-phantasm-5j9xqj59jqr2r6j-8083.app.github.dev/pagamento/pagamentos`, data);
    return response;
}

export async function getPagamentosService() {
    const response = await axios.get(`https://accursed-phantasm-5j9xqj59jqr2r6j-8083.app.github.dev/pagamento/pagamentos`);
    return response;
}

