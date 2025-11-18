import styled from "styled-components";

export const PagamentoContainer = styled.section`
    max-width: 1400px;
    width: 100%;
    margin: 50px auto 0;
    display: grid;
    gap: 30px;
    justify-items: center;
`;
export const PagamentoHeader = styled.header`
    display: flex;
    justify-content: center;
`;
export const PagamentoBody = styled.div`
    display: grid;
    gap: 10px;
    width: 100%;
    justify-items: center;
`;
export const FormaPagamento = styled.article`
    display: flex;
    justify-content: space-between;
    max-width: 500px;
    width: 100%;
    padding: 6px 20px;
    background-color: var(--light);
    border-radius: 10px;
    box-shadow: 0 3px 6px #00000053;
`;
export const CreatePagamentoModal = styled.form`
    opacity: 0;
    /* height: 0; */
    transform: scale(0);
    max-width: 350px;
    padding: 20px;
    border-radius: 10px;
    transition: all.6s;
    top: 50%;
    right: 50%;
    transform: translate(50%, -50%);
    position: fixed;
    box-shadow: 0 3px 6px #00000053;

    input {
        border: none;
        box-shadow: 0 2px 4px #00000053;
        border-radius: 10px;
        padding: 5px;
    }
    &.active {
        opacity: 1;
        transform: scale(1);
        /* height: 190px; */
        top: 50%;
        right: 50%;
        transform: translate(50%, -50%);
    }
`;