import styled from "styled-components";
export const NavbarContainer = styled.section`
    background: var(--light);
    border-radius: 10px;
    padding: 20px 30px;
    color:var(--dark);
    width: 100%;
    max-width: 1400px;
    margin-top: 30px;
    box-shadow: 0 4px 10px #0000004b;
`;

export const NavbarContent = styled.div`
    display: grid;
    grid-template-columns: .4fr 1fr .4fr;
    justify-items: center;
    h5 {
        margin-right: auto;
    }
    &>button {
        margin-left: auto;
    }
    div {
        display: flex;
        gap: 20px;
        align-items: center;
        justify-content: center;
        button {
            font-size: 20px;
        }
        button:hover {
            color: var(--main);
        }
    }
    button img {
        transition: all.3s;
        max-width: 30px;
        &:hover {
            filter: invert(100%) drop-shadow(0 0 0.8px var(--main)) drop-shadow(0 0 0.8px var(--main)) drop-shadow(0 0 0.8px var(--main));
        }
    }
    .ativo {
        color: var(--main);
    }
`;