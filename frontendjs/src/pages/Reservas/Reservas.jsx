import { ReservasContainer, ReservasContent, UmaReserva } from "./ReservasStyled";

export function Reservas() {
    const user = JSON.parse(localStorage.getItem("user"));
    const key = `reservas_${user.email}`;
    const minhasReservas = JSON.parse(localStorage.getItem(key)) || [];

    // transforma "2025-05-20" em Date real
    function parseISO(dateStr) {
        return new Date(dateStr + "T00:00:00");
    }

    // formata para dd/mm/yyyy
    function formatDate(dateStr) {
        const d = parseISO(dateStr);
        const dia = String(d.getDate()).padStart(2, "0");
        const mes = String(d.getMonth() + 1).padStart(2, "0");
        const ano = d.getFullYear();
        return `${dia}/${mes}/${ano}`;
    }

    const hojeISO = new Date().toISOString().split("T")[0];

    // ordena reservas pela data
    const reservasOrdenadas = [...minhasReservas].sort((a, b) => {
        return parseISO(a.dia) - parseISO(b.dia);
    });

    return (
        <ReservasContainer>
            <h3>Minhas Reservas:</h3>

            <ReservasContent>
                {reservasOrdenadas && reservasOrdenadas.length > 0 && (
                    reservasOrdenadas.map((reserva) => (
                        <UmaReserva
                            key={reserva.id}
                            className={reserva.dia === hojeISO ? "hoje" : ""}
                        >
                            <h2>{reserva.quadra}</h2>
                            <h4>Dia: <strong>{formatDate(reserva.dia)}</strong></h4>
                            <h4>Pago com: <strong>{reserva.pagamento}</strong></h4>
                        </UmaReserva>
                    ))
                )}
            </ReservasContent>
        </ReservasContainer>
    );
}
