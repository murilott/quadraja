import { ReservasContainer, ReservasContent, UmaReserva } from "./ReservasStyled";

export function Reservas() {
    const user = JSON.parse(localStorage.getItem("user"));
    const key = `reservas_${user.email}`;
    const minhasReservas = JSON.parse(localStorage.getItem(key)) || [];


    return (
        <ReservasContainer>
            <ReservasContent>
                {minhasReservas && minhasReservas.length > 0 && (
                    minhasReservas.map((reserva) => (
                        <UmaReserva>
                            {reserva.quadra}
                        </UmaReserva>
                    ))
                )}
            </ReservasContent>
        </ReservasContainer>
    )
}