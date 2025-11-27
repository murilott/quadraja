import { Outlet, useNavigate, useLocation } from "react-router-dom";
import { NavbarContainer, NavbarContent } from "./NavbarStyled";

export function Navbar() {
    const navigate = useNavigate();
    const location = useLocation();

    const path = location.pathname;

    function handleLogout() {
        // limpa o usuário salvo
        localStorage.removeItem("user");

        // redireciona pro início
        navigate("/");
    }

    return (
        <>
            <NavbarContainer>
                <NavbarContent>
                    <img src="https://quadra7.esp.br/wp-content/uploads/QUADRA7.png" style={{maxWidth: 200 + "px"}} />

                    <div>
                        <button
                            className={path === "/home/" || path === "/home" ? "ativo" : ""}
                            onClick={() => navigate("/home/")}
                        >
                            Quadras
                        </button>

                        <button
                            className={path.includes("/home/reservas") ? "ativo" : ""}
                            onClick={() => navigate("/home/reservas")}
                        >
                            Reservas
                        </button>

                        <button
                            className={path.includes("/home/pagamento") ? "ativo" : ""}
                            onClick={() => navigate("/home/pagamento")}
                        >
                            Pagamento
                        </button>
                    </div>

                    <button onClick={handleLogout}><img src="https://www.svgrepo.com/show/506720/logout.svg" alt="" /></button>
                </NavbarContent>
            </NavbarContainer>

            <Outlet />
        </>
    );
}
