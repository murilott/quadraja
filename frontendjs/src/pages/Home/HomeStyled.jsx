import styled from "styled-components";

export const HomeContainer = styled.section`
    max-width: 1400px;
    width: 100%;
    margin: 40px auto 0;
`;
export const HomeContent = styled.div`
    box-shadow: 0 6px 15px #00000052;
    border-radius: 15px;
    padding: 40px;
    width: 100%;
    display: grid;
    grid-template-columns: 1fr 1fr;
`;
export const Quadra = styled.div`
    background-color: var(--dark);
    color: var(--light);
    padding: 20px;
    border-radius: 10px;
    max-width: 400px;
    width: 100%;
`;