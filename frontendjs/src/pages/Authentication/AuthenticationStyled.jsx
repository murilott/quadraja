import styled from "styled-components";
export const AuthenticationContainer = styled.section`
    max-width: 1400px;
    width: 100%;
    margin: 0 auto;
`;
export const AuthenticationContent = styled.form`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: var(--light);
    border-radius: 10px;
    padding: 30px;
    box-shadow: 0 4px 10px #0000004e;
    display: grid;
    gap: 20px;
    max-width: 400px;
    width: 100%;
    justify-items: center;
    div {
        display: grid;
        gap: 5px;
        justify-items: center;
        text-align: center;
        width: 100%;
        max-width: 300px;
        label {
            font-size: 20px;
        }

        input {
            border: none;
            box-shadow: 0 2px 4px #00000053;
            border-radius: 10px;
            padding: 5px;
            max-width: 300px;
            width: 100%;
        }
    }
`;