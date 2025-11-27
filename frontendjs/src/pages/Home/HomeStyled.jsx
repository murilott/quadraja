import styled from "styled-components";

export const HomeContainer = styled.section`
    max-width: 1400px;
    width: 100%;
    margin: 40px auto 0;
    #nova {
        margin: 0 auto 20px;
        display: block;
    }
    .modal {
        position: fixed;
        top: 0;
        left: 0;
        z-index: 5;
        width: 100%;
        height: 100%;
        background-color: #0000002b;
        backdrop-filter: blur(2px);
        display: grid;
        justify-items: center;
        align-items: center;
        form {
            display: grid;
            justify-items: center;
            gap: 5px;
            width: 100%;
            max-width: 400px;
            padding: 20px;
            background-color: var(--light);
            box-shadow: 0 4px 10px #0000004c;
            border-radius: 10px;

            h2 {
                font-size: 1.7rem;
                font-weight: 600;
                color: var(--main);
            }
            input + label {
                margin-top: 10px;
            }
            label {
                font-size: 20px;
                margin-right: auto;
                margin-left: 32px;
            }

            input {
                border: none;
                box-shadow: 0 2px 4px #00000053;
                border-radius: 10px;
                padding: 5px;
                max-width: 300px;
                width: 100%;
                outline: none;
            }
            select {
                width: 100%;
                max-width: 300px;
                padding: 7px 5px;
                border: none;
                box-shadow: 0 2px 4px #00000053;
                border-radius: 10px;
                outline: none;
                margin-bottom: 10px;
            }
            .guardabtn {
                margin-top: 10px;
                display: flex;
                gap: 15px;
            }
        }
    }
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
    cursor: pointer;
    width: 100%;

    #excluir {
        position: absolute;
        top: 50%;
        right: 10px;
        transform: translateY(-50%);
        background-color: var(--dark);
        opacity: 0;
    }
    h2 {
        font-size: 25px;
        font-weight: 600;
        color: #5dd60c;
    }
    &:hover {
        box-shadow: 0 0 0 0 #00000052, 0 0 10px 100px var(--dark) inset;
        color: var(--light);
        #excluir {
            opacity: 1;
        }
    }
    &.alugado {
        background-color: #e6e6e6e2;
        #excluir {
            background-color: #e6e6e6e2;
        }

        cursor: default;
        h2 {
            color: red;
        }
        &:hover {
            box-shadow: 0 5px 15px 0 #00000052, 0 0 0 0 var(--dark) inset;
            color: var(--dark);
        }
    }
`;