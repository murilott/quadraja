import styled from "styled-components";
export const NavbarContainer = styled.section`
    background: var(--dark);
    border-radius: 10px;
    padding: 20px 30px;
    color:var(--light);
    width: 100%;
    max-width: 1400px;
    margin-top: 30px;
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
`;