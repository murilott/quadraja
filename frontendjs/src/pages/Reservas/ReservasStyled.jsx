import styled from "styled-components";

export const ReservasContainer = styled.section`
    max-width: 1400px;
    width: 100%;
    margin: 0 auto;
    &>h3 {
        margin: 20px auto;
        max-width: fit-content;
        text-align: center;
        font-size: 2rem;
        color: var(--main);
        font-weight: 700;
    }
`;
export const ReservasContent = styled.div`
    display: grid;
    gap: 15px;
    justify-items: center;
`;
export const UmaReserva = styled.div`
    max-width: 400px;
    width: 100%;
    border-radius: 10px;
    padding: 15px;
    box-shadow: 0 4px 10px #00000045;

    background-color: var(--light);
    &.hoje {
        border: 2px solid var(--main);
        /* color: #fff; */
    }
`;