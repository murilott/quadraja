import React, { useEffect, useState } from 'react'
import { listQuadras } from './QuadraService';
import type { QuadraType } from './QuadraType';
import axios from 'axios';

type Page<T> = {
  content: T[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
};

// const BASE_URL = 'http://localhost:8082/quadras';
const BASE_URL = 'https://accursed-phantasm-5j9xqj59jqr2r6j-8083.app.github.dev/quadra/quadras';

function Quadra() {
  const [pageData, setPageData] = useState<Page<QuadraType> | null>(null);
  const [page, setPage] = useState(0);

  const fetchData = async (pageNumber: number) => {
    try {
      const response = await axios.get<Page<QuadraType>>(
        `${BASE_URL}?page=${pageNumber}&size=5`
      );
      setPageData(response.data);
    } catch (error) {
      console.error("Erro ao buscar quadras:", error);
    }
  };

  useEffect(() => {
    fetchData(page);
  }, [page]);

  return (
    <div style={{ padding: "20px" }}>
      <h2>Lista de Quadras</h2>

      <table
        border={1}
        cellPadding={8}
        cellSpacing={0}
        style={{ width: "100%", borderCollapse: "collapse" }}
      >
        <thead style={{ backgroundColor: "#f2f2f2" }}>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Local</th>
            <th>Preço</th>
            <th>Categoria</th>
          </tr>
        </thead>
        <tbody>
          {pageData && pageData.content.length > 0 ? (
            pageData.content.map((quadra) => (
              <tr key={quadra.id}>
                <td>{quadra.id}</td>
                <td>{quadra.name}</td>
                <td>{quadra.local}</td>
                <td>R$ {quadra.price.toFixed(2)}</td>
                <td>{quadra.category}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={5} style={{ textAlign: "center" }}>
                Nenhuma quadra encontrada
              </td>
            </tr>
          )}
        </tbody>
      </table>

      {pageData && (
        <div style={{ marginTop: "15px", textAlign: "center" }}>
          <button
            onClick={() => setPage((p) => Math.max(p - 1, 0))}
            // disabled={pageData.first}
          >
            Anterior
          </button>

          <span style={{ margin: "0 10px" }}>
            Página {pageData.number + 1} de {pageData.totalPages}
          </span>

          <button
            onClick={() =>
              setPage((p) => Math.min(p + 1, pageData.totalPages - 1))
            }
            // disabled={pageData.last}
          >
            Próxima
          </button>
        </div>
      )}
    </div>
  );
}

export default Quadra