import { HomeContainer, HomeContent, Quadra } from "./HomeStyled";

const quadras = [{id: 1, name: "Quadra 1", alugado: true, local: "Pátio 1", price: 100, category: "Basquete"}, {id: 2, name: "Quadra 2", alugado: false, local: "Pátio 3", price: 200, category: "Vôlei"}]

export function Home() {
    return (
        <HomeContainer>
            <HomeContent>
                {quadras && quadras.length > 0 && (
                    quadras.map((quadra) => (
                        <Quadra>
                            <h2>{quadra.name}</h2>
                            <div>
                                <h4>{quadra.local}</h4>
                            <h3>{quadra.category}</h3>
                            </div>
                            <h6>{quadra.price}</h6>
                            <span>ALUGADO!</span>
                        </Quadra>
                    ))
                )}
            </HomeContent>
        </HomeContainer>
    );
}