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
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    align-items: center;
    max-width: 500px;
    width: 100%;
    padding: 10px 20px;
    font-size: 20px;
    background-color: var(--light);
    border-radius: 10px;
    box-shadow: 0 3px 6px #00000053;
    h4 {
        color: var(--main);
        margin: 0 auto;
        text-align: center;
    }
    .btn {
        margin-left: auto;
        margin-top: 0;
    }
`;
export const CreatePagamentoModal = styled.form`
    /* opacity: 0; */
    /* height: 0; */
    max-width: 350px;
    padding: 25px;
    border-radius: 10px;
    transition: all.6s;
    top: 50%;
    right: 50%;
    transform: translate(50%, -50%) scale(0);
    position: fixed;
    background-color: var(--light);
    box-shadow: 0 3px 6px #00000053;

    .close {
        position: absolute;
        right: 15px;
        top: 10px;
        height: 25px;
        width: 25px;
        border-radius: 40px;
        padding-top: 2px;
        padding-left: 1px;
        box-shadow: 0 2px 5px #00000061;
        background-color: #fff;
        font-weight: 400;
        display: flex;
        transition: all.3s;
        justify-content: center;
        align-items: center;
        cursor: pointer;
        &:hover {
            background-color: #000;
            color: #fff;
        }
    }

    label {
        font-size: 20px;
        & + input {
            margin-bottom: 15px;
        }
    }

    input {
        border: none;
        box-shadow: 0 2px 4px #00000053;
        border-radius: 10px;
        padding: 5px;
    }
    .btn {
        display: block;
        margin: 0 auto;
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