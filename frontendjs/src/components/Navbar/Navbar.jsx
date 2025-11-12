import { Outlet } from "react-router-dom";
import { NavbarContainer, NavbarContent } from "./NavbarStyled";

export function Navbar() {
    return (
        <>
            <NavbarContainer>
                <NavbarContent>
                    <h5>Logo Qudra</h5>
                    <div>
                        <button>Reservas</button>
                        <button>Pagamento</button>
                    </div>
                    <button>Logout</button>
                </NavbarContent>
            </NavbarContainer>
            <Outlet />
        </>
    );
}