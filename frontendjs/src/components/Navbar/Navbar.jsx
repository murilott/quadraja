import { Outlet, useNavigate } from "react-router-dom";
import { NavbarContainer, NavbarContent } from "./NavbarStyled";

export function Navbar() {
    const navigate = useNavigate();

    return (
        <>
            <NavbarContainer>
                <NavbarContent>
                    <h5>Logo Qudra</h5>
                    <div>
                        <button>Reservas</button>
                        <button onClick={() => navigate("/home/pagamento")}>Pagamento</button>
                    </div>
                    <button>Logout</button>
                </NavbarContent>
            </NavbarContainer>
            <Outlet />
        </>
    );
}