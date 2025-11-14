import styled from "styled-components";

export const HomeContainer = styled.section`
    max-width: 1400px;
    width: 100%;
    margin: 40px auto 0;
`;
export const HomeContent = styled.div`
    box-shadow: 0 4px 15px #0000003d;
    border-radius: 15px;
    padding: 60px 40px;
    width: 100%;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    justify-items: center;
    gap: 20px;
    
    @media only screen and (max-width:1055px) {
        grid-template-columns: 1fr 1fr;
    }
    @media only screen and (max-width:700px) {
        grid-template-columns: 1fr;
    }

`;
export const Quadra = styled.div`
    background-color: var(--light);
    color: var(--dark);
    box-shadow: 0 5px 15px 0 #00000052, 0 0 0 0 var(--dark) inset;
    padding: 20px;
    border-radius: 10px;
    max-width: 400px;
    transition: all.3s;
    position: relative;
    width: 100%;
    h2 {
        font-size: 25px;
        font-weight: 600;
        color: #5dd60c;
    }
    &:hover {
        box-shadow: 0 0 0 0 #00000052, 0 0 10px 100px var(--dark) inset;
        color: var(--light);
    }
    &.alugado {
        background-color: #e6e6e6e2;
        h2 {
            color: red;
        }
        &::before {
                content: "";
                width: 100%;
                height: 100%;
                background-color: #0000006a;
                display: block;
                position: absolute;
                top: 0;
                left: 0;
                border-radius: 10px;
                z-index: 2;
            }
        span {
            position: absolute;
            z-index: 3;
            top: 50%;
            right: 50%;
            transform: translate(50%, -50%);
            background-color: red;
            color: var(--light);
            padding: 3px 8px;
            border-radius: 10px;
            
        }
        &:hover {
            box-shadow: 0 0 0 0 #00000052, 0 0 0 0 var(--dark) inset;
        }
    }
`;