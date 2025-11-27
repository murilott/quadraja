import styled from "styled-components";
export const UsuariosContainer = styled.section`
    max-width: 1400px;
    width: 100%;
    margin: 0 auto;
    &>.btn {
        display: block;
        margin: 30px auto;
    }
`;
export const UsuariosContent = styled.div`
    display: grid;
    justify-items: center;
    gap: 15px;
`;
export const UmUsuario = styled.div`
    max-width: 500px;
    width: 100%;
    background-color: var(--light);
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    gap: 10px;
    box-shadow: 0 4px 10px #00000057;
    padding: 15px;
    border-radius: 10px;
    h2 {
        font-size: 20px;
        color: var(--main);
    }
    h3 {
        margin: 0 auto;
        text-align: center;
    }
    p {
        margin-left: auto;
    }
`;